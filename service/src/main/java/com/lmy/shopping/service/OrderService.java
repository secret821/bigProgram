package com.lmy.shopping.service;

import com.lmy.shopping.entity.Orders;

import java.sql.SQLException;
import java.util.Map;

/**
 * @desc
 * @Auther LMY233
 * @Date 2022/3/23
 */
public interface OrderService {

    //生成订单
    Map<String,String> addOrder(String cids, Orders order) throws SQLException;
}
