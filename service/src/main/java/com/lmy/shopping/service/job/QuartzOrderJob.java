package com.lmy.shopping.service.job;

import com.github.wxpay.sdk.WXPay;
import com.lmy.shopping.entity.Orders;
import com.lmy.shopping.mapper.OrdersMapper;
import com.lmy.shopping.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc 定時任務，请求wx平台接口，超时订单关闭取消支付，并且还原库存
 * @Auther LMY233
 * @Date 2022/4/1
 */
@Component
public class QuartzOrderJob {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderServiceImpl orderService;

    private WXPay wxPay = new WXPay(new MyPayConfig());

    @Scheduled(cron = "1/60 * * * * ?")
    public void CheckAndClose() {
        Example example=new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status","1");
        Date date=new Date(System.currentTimeMillis()-30*60*1000);
        criteria.andLessThan("createTime",date);
        List<Orders> orders = ordersMapper.selectByExample(example);

        for(int i=0; i<orders.size();i++){
            Orders orders1 = orders.get(i);
            HashMap<String,String> params=new HashMap<>();
            params.put("out_trade_no",orders1.getOrderId());
            try {
                Map<String, String> resp = wxPay.orderQuery(params);

                if("SUCCESS".equalsIgnoreCase(resp.get("trade_state"))){
                    //如果订单已经支付，则修改订单状态为"代发货/已支付"  status = 2

                    Orders updateOrder =new Orders();
                    updateOrder.setOrderId(orders1.getOrderId());
                    updateOrder.setStatus("2");
                    ordersMapper.updateByPrimaryKeySelective(updateOrder);
                }else if("NOTPAY".equalsIgnoreCase(resp.get("trade_state"))){
                    //如果确实未支付 则取消订单：
                    //向微信支付平台发送请求，关闭当前订单的支付链接
                    Map<String, String> map = wxPay.closeOrder(params);
                    orderService.closeOrder(orders1.getOrderId());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
