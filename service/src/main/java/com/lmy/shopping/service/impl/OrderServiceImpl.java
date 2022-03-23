package com.lmy.shopping.service.impl;

import com.lmy.shopping.entity.OrderItem;
import com.lmy.shopping.entity.Orders;
import com.lmy.shopping.entity.ProductSku;
import com.lmy.shopping.entity.ShoppingCartVo;
import com.lmy.shopping.mapper.OrderItemMapper;
import com.lmy.shopping.mapper.OrdersMapper;
import com.lmy.shopping.mapper.ProductSkuMapper;
import com.lmy.shopping.mapper.ShoppingCartMapper;
import com.lmy.shopping.service.OrderService;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

/**
 * @desc 订单实现类
 * @Auther LMY233
 * @Date 2022/3/23
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    //添加事务 出错回滚所有事务
    @Transactional
    public ResultVo addOrder(String cids, Orders orders) throws SQLException {
        //获得前台购物车数组
        String[] arr = cids.split(",");
        List<Integer> cidsList = new ArrayList<>();
        //遍历数组
        for (int i = 0; i < arr.length; i++) {
            cidsList.add(Integer.valueOf(arr[i]));
        }
        //查询数据所对应的购物车商品
        List<ShoppingCartVo> shoppingCartList = shoppingCartMapper.selectShoppingCartBycid(cidsList);

        boolean f = true;
        String untitled = "";
        //遍历购物车对象集合
        for (ShoppingCartVo shoppingCartVo : shoppingCartList) {
            //判断购物车加购数量是否大于该商品的库存数量
            if (Integer.parseInt(shoppingCartVo.getCartNum()) > shoppingCartVo.getStock()) {
                //库存充足
                f = false;
            }
            //拼接 所有的购物车商品的name
            untitled = untitled + shoppingCartVo.getProductName() + ",";
        }


        if (f) {
            //保存订单
            String OrderId = UUID.randomUUID().toString().replace("-", "");
            orders.setOrderId(OrderId);
            orders.setUntitled(untitled);
            orders.setCreateTime(new Date());
            orders.setStatus("1");
            int insert = ordersMapper.insert(orders);
            //保存订单快照
            for (ShoppingCartVo sc : shoppingCartList) {
                int cnum = Integer.parseInt(sc.getCartNum());
                String itemId = System.currentTimeMillis() + "" + (new Random().nextInt(89999) + 10000);
                OrderItem orderItem = new OrderItem(itemId, OrderId, sc.getProductId(), sc.getProductName(), sc.getProductImg(), sc.getSkuId(), sc.getSkuName(), new BigDecimal(sc.getSellPrice()), cnum, new BigDecimal(sc.getSellPrice() * cnum), new Date(), new Date(), 0);
                orderItemMapper.insert(orderItem);
            }

            //库存扣减
            for (ShoppingCartVo sc : shoppingCartList) {
                String skuId = sc.getSkuId();
                int stock = sc.getStock();
                int newStock = stock - Integer.parseInt(sc.getCartNum());
                ProductSku productSku = new ProductSku();
                productSku.setSkuId(skuId);
                productSku.setStock(newStock);
                productSkuMapper.updateByPrimaryKeySelective(productSku);
            }

            //删除对应购物车记录
            for (int cid : cidsList) {
                shoppingCartMapper.deleteByPrimaryKey(cid);
            }
            return new ResultVo(StatusCode.STATUS_OK, "下单成功", OrderId);

        }
        return new ResultVo(StatusCode.STATUS_FAIL, "库存不足，下单失败", null);
    }
}
