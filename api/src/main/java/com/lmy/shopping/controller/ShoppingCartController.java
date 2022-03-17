package com.lmy.shopping.controller;

import com.lmy.shopping.entity.ShoppingCart;
import com.lmy.shopping.service.impl.ShoppingCartServiceImpl;
import com.lmy.shopping.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车接口
 *
 * @Author LMY
 * @Date 2022/3/2 10:48
 * @Version V1.0
 */

@RestController
@RequestMapping(value = "shoppingCart")
@CrossOrigin
@Api(value = "购物车数据", tags = "购物车业务接口")
public class ShoppingCartController {

    @Autowired
    public ShoppingCartServiceImpl shoppingCartService;

    @ApiOperation("添加购物车接口")
    @PostMapping(value="add")
    public ResultVo addShoppingCart(@RequestBody ShoppingCart shoppingCart,@RequestHeader("token")String token){
        ResultVo resultVo = shoppingCartService.addShoppingCart(shoppingCart);
        return resultVo;
    }
}
