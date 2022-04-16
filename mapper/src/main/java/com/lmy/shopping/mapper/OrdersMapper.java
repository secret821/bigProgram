package com.lmy.shopping.mapper;

import com.lmy.shopping.entity.Orders;
import com.lmy.shopping.entity.OrdersVo;
import com.lmy.shopping.entity.StatusCount;
import com.lmy.shopping.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper extends GeneralDAO<Orders> {

    List<OrdersVo> queryOrderList(@Param("uid") String uid,
                                  @Param("status") String status,
                                  @Param("start")int start,
                                  @Param("limit")int limit);

    List<OrdersVo> queryOrderList2(@Param("orderId")String orderId,
                                   @Param("start")int start,
                                   @Param("limit")int limit);

    List<StatusCount> queryOrdersCount(String uid);
}