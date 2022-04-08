package com.lmy.shopping.service.impl;

import com.lmy.shopping.entity.*;
import com.lmy.shopping.mapper.OrderItemMapper;
import com.lmy.shopping.mapper.OrdersMapper;
import com.lmy.shopping.mapper.ProductSkuMapper;
import com.lmy.shopping.mapper.ShoppingCartMapper;
import com.lmy.shopping.service.OrderService;
import com.lmy.shopping.vo.PageHelper;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.annotation.Order;
import tk.mybatis.mapper.entity.Example;

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
    public Map<String, String> addOrder(String cids, Orders orders) throws SQLException {

        Map<String, String> map = new HashMap<>();

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

            map.put("orderId", OrderId);
            map.put("productNames", untitled);
            return map;

        }
        return null;
    }


    @Override
    public int updateStatus(String orderId, String status) {

        Orders order = new Orders();
        order.setOrderId(orderId);
        order.setStatus(status);
        int i = ordersMapper.updateByPrimaryKeySelective(order);
        return i;
    }

    @Override
    public ResultVo queryOrderInfo(String orderId) {
        Orders orders = ordersMapper.selectByPrimaryKey(orderId);
        return new ResultVo(StatusCode.STATUS_OK, "success", orders.getStatus());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void closeOrder(String orderId) {
        synchronized (this) {
            //  1.修改当前订单：status=6 已关闭  close_type=1 超时未支付
            Orders cancleOrder = new Orders();
            cancleOrder.setOrderId(orderId);
            cancleOrder.setStatus("6");  //已关闭
            cancleOrder.setCloseType(1); //关闭类型：超时未支付
            ordersMapper.updateByPrimaryKeySelective(cancleOrder);

            //  2.还原库存：先根据当前订单编号查询商品快照（skuid  buy_count）--->修改product_sku
            Example example1 = new Example(OrderItem.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("orderId", orderId);
            List<OrderItem> orderItems = orderItemMapper.selectByExample(example1);
            //还原库存
            for (int j = 0; j < orderItems.size(); j++) {
                OrderItem orderItem = orderItems.get(j);
                //修改
                ProductSku productSku = productSkuMapper.selectByPrimaryKey(orderItem.getSkuId());
                productSku.setStock(productSku.getStock() + orderItem.getBuyCounts());
                productSkuMapper.updateByPrimaryKey(productSku);
            }
        }

    }

    @Override
    public ResultVo queryOrdersList(String userId, String status, int pageNum, int limit) {
        //1.分页查询
        int start = (pageNum-1)*limit;
        List<OrdersVo> ordersVOS = ordersMapper.queryOrderList(userId, status, start, limit);

        //2.查询总记录数
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("userId",userId);
        if(status != null && !"".equals(status)){
            criteria.andLike("status",status);
        }
        int count = ordersMapper.selectCountByExample(example);

        //3.计算总页数
        int pageCount = count%limit==0?count/limit:count/limit+1;

        //4.封装数据
        PageHelper<OrdersVo> pageHelper = new PageHelper<>(count, pageCount, ordersVOS);
        return new ResultVo(StatusCode.STATUS_OK,"success",pageHelper);
    }

    @Override
    public ResultVo queryOrdersCount(String userId) {
        List<StatusCount> statusCounts = ordersMapper.queryOrdersCount(userId);
        return new ResultVo(StatusCode.STATUS_OK,"success",statusCounts);
    }

    @Override
    public ResultVo deleteOrderById(String orderId) {
        Orders orders=new Orders();
        orders.setOrderId(orderId);
        orders.setDeleteStatus(1);
        int i = ordersMapper.updateByPrimaryKeySelective(orders);
        if (i>0){
            return new ResultVo(StatusCode.STATUS_OK,"success",null);
        }
        return new ResultVo(StatusCode.STATUS_FAIL,"fail",null);
    }
}
