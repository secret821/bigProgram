package com.lmy.shopping.service;


import com.lmy.shopping.entity.ShoppingCart;
import com.lmy.shopping.vo.ResultVo;

/**
 * 购物车业务接口
 *
 * @Author LMY
 * @Date 2022/3/2 10:37
 * @Version V1.0
 */
public interface ShoppingCartService {

    ResultVo addShoppingCart(ShoppingCart shoppingCart);


    ResultVo selectShoppingCartByUserId(int user_id);


    ResultVo updateCartNum(int cartId,int cartNum);

    ResultVo deleteCartByUserId(int cartId);
}
