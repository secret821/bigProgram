package com.lmy.shopping.controller;

import com.github.wxpay.sdk.WXPay;
import com.lmy.shopping.service.job.MyPayConfig;
import com.lmy.shopping.entity.Orders;
import com.lmy.shopping.service.impl.OrderServiceImpl;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc
 * @Auther LMY233
 * @Date 2022/3/23
 */
@RestController
@RequestMapping(value = "/order")
@CrossOrigin
@Api(value = "提供订单管理的接口", tags = "订单管理接口")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;


    @PostMapping("/add")
    @ApiOperation("添加订单接口")
    public ResultVo addOrder(String cids, @RequestBody Orders orders) {
        ResultVo resultVo = null;
        try {
            Map<String, String> orderInfo = orderService.addOrder(cids, orders);

            if (orderInfo.get("orderId") != null) {
                String orderId = orderInfo.get("orderId");
                HashMap<String, String> data = new HashMap<>();
                data.put("body", orderInfo.get("productNames"));  //商品描述
                data.put("out_trade_no", orderId);               //使用当前用户订单的编号作为当前支付交易的交易号
                data.put("fee_type", "CNY");                     //支付币种
                /* data.put("total_fee",orders.getActualAmount()*100+""); */         //支付金额
                data.put("total_fee", "1");
                data.put("trade_type", "NATIVE");                //交易类型
                data.put("notify_url", "http://lmyly.free.idcfengye.com/pay/callback");
                WXPay wxPay = new WXPay(new MyPayConfig());
                Map<String, String> resp = wxPay.unifiedOrder(data);
                orderInfo.put("payUrl", resp.get("code_url"));
                return new ResultVo(StatusCode.STATUS_OK, "订单提交成功，请支付！", orderInfo);
            } else {
                return new ResultVo(StatusCode.STATUS_FAIL, "订单提交失败！", null);
            }

        } catch (SQLException e) {
            return new ResultVo(StatusCode.STATUS_FAIL, "订单提交失败！", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo;
    }


    @GetMapping("/status/{oid}")
    @ApiOperation("查询订单状态接口")
    public ResultVo queryStatus(@PathVariable("oid") String orderId, @RequestHeader("token") String token) {
        ResultVo resultVo = orderService.queryOrderInfo(orderId);
        return resultVo;
    }

    @GetMapping("/list")
    @ApiOperation("订单查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "userId", value = "用户ID",required = true),
            @ApiImplicitParam(dataType = "string",name = "status", value = "订单状态",required = false),
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页条数",required = true)
    })
    public ResultVo list(@RequestHeader("token")String token,
                         String userId,String status,int pageNum,int limit){
        ResultVo resultVO = orderService.queryOrdersList(userId, status, pageNum, limit);
        return resultVO;
    }


    @GetMapping("/statusCount/{uid}")
    @ApiOperation("查询订单状态总数接口")
    public ResultVo queryStatusCount(@PathVariable("uid") String userId, @RequestHeader("token") String token) {
        ResultVo resultVo = orderService.queryOrdersCount(userId);
        return resultVo;
    }
    @GetMapping("/deleteOrder")
    @ApiOperation("删除过期未支付订单")
    public ResultVo deleteOrderById(String orderId, @RequestHeader("token") String token) {
        ResultVo resultVo = orderService.deleteOrderById(orderId);
        return resultVo;
    }
}
