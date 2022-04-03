package com.lmy.shopping.controller;

import com.lmy.shopping.entity.ShoppingCart;
import com.lmy.shopping.service.impl.ShoppingCartServiceImpl;
import com.lmy.shopping.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("查询购物车的数据接口")
    @GetMapping(value="list")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "userId", value = "用户ID",required = true)
    })
    public ResultVo listShoppingCart(Integer userId, @RequestHeader("token")String token){
        ResultVo resultVo = shoppingCartService.selectShoppingCartByUserId(userId);
        return resultVo;
    }

    @ApiOperation("修改购物车数量信息")
    @PutMapping("/update/{cid}/{cnum}")
    public ResultVo updateNum(@PathVariable("cid") Integer cartId,
                              @PathVariable("cnum") Integer cartNum,
                              @RequestHeader("token") String token){
        ResultVo resultVO = shoppingCartService.updateCartNum(cartId, cartNum);
        return resultVO;
    }


    @ApiOperation("刪除购物车商品")
    @DeleteMapping("/delete/{cid}")
    public ResultVo updateNum(@PathVariable("cid") Integer cartId,
                              @RequestHeader("token") String token){
        ResultVo resultVO = shoppingCartService.deleteCartByCid(cartId);
        return resultVO;
    }

    @ApiOperation("查询购物车商品根据购物车")
    @GetMapping("/listBycids")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "cids", value = "购物车id",required = true)
    })
    public ResultVo listShoppingCartByCids(String cids,
                              @RequestHeader("token") String token){
        ResultVo resultVO = shoppingCartService.selectShoppingCartBycid(cids);
        return resultVO;
    }

    @ApiOperation("查询购物车商品数量根据用户id")
    @GetMapping("/countByUid/{uid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "uid", value = "用户id",required = true)
    })
    public ResultVo countShoppingByUid(@PathVariable("uid") String uid,
                                           @RequestHeader("token") String token){
        ResultVo resultVO = shoppingCartService.queryShoppingCartNum(uid);
        return resultVO;
    }

}
