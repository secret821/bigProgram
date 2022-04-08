package com.lmy.shopping.mapper;

import com.lmy.shopping.entity.OrderItem;
import com.lmy.shopping.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemMapper extends GeneralDAO<OrderItem> {

     List<OrderItem> listOrderItemsByOrderId(String orderId);
}