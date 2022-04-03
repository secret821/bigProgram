package com.lmy.shopping.service;


import com.lmy.shopping.entity.ShoppingCart;
import com.lmy.shopping.vo.ResultVo;

import java.util.List;

/**
 * 购物车业务接口
 *
 * @Author LMY
 * @Date 2022/3/2 10:37
 * @Version V1.0
 */
public interface ShoppingCartService {

    /**
     * 添加购物车
     * @param shoppingCart
     * @return
     */
    ResultVo addShoppingCart(ShoppingCart shoppingCart);

    /**
     * 通过uid查询购物车信息
     * @param user_id
     * @return
     */
    ResultVo selectShoppingCartByUserId(int user_id);

    /**
     * 更新购物车的商品数量，动态更新
     * @param cartId
     * @param cartNum
     * @return
     */
    ResultVo updateCartNum(int cartId,int cartNum);

    /**
     * 刪除购物车通过cid
     * @param cartId
     * @return
     */
    ResultVo deleteCartByCid(int cartId);

    /**
     * 根据cid查询购物车信息
     * @param cids
     * @return
     */
    ResultVo selectShoppingCartBycid(String cids);

    /**
     * 查询购物车的数量根据用户id
     * @param uid
     * @return
     */
    ResultVo queryShoppingCartNum(String uid);
}
