package com.lmy.shopping.service;

import com.lmy.shopping.entity.Orders;
import com.lmy.shopping.vo.ResultVo;

import java.sql.SQLException;
import java.util.Map;

/**
 * @desc 订单业务接口
 * @Auther LMY233
 * @Date 2022/3/23
 */
public interface OrderService {

    //生成订单
    Map<String,String> addOrder(String cids, Orders order) throws SQLException;

    //更新订单状态
    int updateStatus(String orderId,String status);

    //查询订单状态
    ResultVo queryOrderInfo(String orderId);
    //关闭订单
    void closeOrder(String orderId);

}
