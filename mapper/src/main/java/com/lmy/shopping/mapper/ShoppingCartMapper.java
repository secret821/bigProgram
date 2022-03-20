package com.lmy.shopping.mapper;

import com.lmy.shopping.entity.ShoppingCart;
import com.lmy.shopping.entity.ShoppingCartVo;
import com.lmy.shopping.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper extends GeneralDAO<ShoppingCart> {



    //购物车查询 根据用户
    List<ShoppingCartVo> selectShoppingCartByUserId(int userId);

    //修改商品数量
    int updateCartnumByCartid(@Param("cartId") int cartId,
                              @Param("cartNum") int cartNum);


    //购物车查询 根据商品id查询
    List<ShoppingCartVo> selectShoppingCartBycid(List<Integer> cids);
}