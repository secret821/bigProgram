package com.lmy.shopping.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * @desc
 * @Auther LMY233
 * @Date 2022/3/11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartVo {


    /**
     * 主键
     */
    private Integer cartId;

    /**
     * 商品ID
     */

    private String productId;

    /**
     * skuID
     */

    private String skuId;

    /**
     * 用户ID
     */

    private String userId;

    /**
     * 购物车商品数量
     */

    private String cartNum;

    /**
     * 添加购物车时间
     */

    private String cartTime;

    /**
     * 添加购物车时商品价格
     */

    private BigDecimal productPrice;

    /**
     * 选择的套餐的属性
     */

    private String skuProps;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品图片路径
     */

    private String productImg;


    /**
     * 原价
     */

    private double originalPrice;

    /**
     * 套餐名字
     */

    private String skuName;

    /**
     * 售价
     */

    private double sellPrice;

    /**
     * 库存
     */

    private   int stock;
}
