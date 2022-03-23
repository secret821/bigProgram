package com.lmy.shopping.controller;

import com.lmy.shopping.entity.Orders;
import com.lmy.shopping.service.impl.OrderServiceImpl;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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
    public ResultVo add(String cids, @RequestBody Orders orders){
        ResultVo resultVo = null;
        try {
            resultVo = orderService.addOrder(cids, orders);
        } catch (SQLException e) {
          return  new ResultVo(StatusCode.STATUS_FAIL,"订单提交失败！",null);
        }
        return resultVo;
    }
}
