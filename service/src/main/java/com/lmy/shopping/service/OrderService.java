package com.lmy.shopping.service;

import com.lmy.shopping.entity.Orders;
import com.lmy.shopping.vo.ResultVo;

import java.sql.SQLException;

/**
 * @desc
 * @Auther LMY233
 * @Date 2022/3/23
 */
public interface OrderService {

    //生成订单
    ResultVo addOrder(String cids, Orders order) throws SQLException;
}
