package com.lmy.shopping.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 商品信息包装类
 *
 * @Author LMY
 * @Date 2022/2/24 14:30
 * @Version V1.0
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVO {

    /**
     * 商品主键id
     */


    private String productId;

    /**
     * 商品名称 商品名称
     */

    private String productName;

    /**
     * 分类外键id 分类id
     */

    private Integer categoryId;

    /**
     * 一级分类外键id 一级分类id，
     * <p>
     * 用于优化查询
     */

    private Integer rootCategoryId;

    /**
     * 销量 累计销售
     */

    private Integer soldNum;

    /**
     * 默认是1，表示正常状态, -1表示
     * <p>
     * 删除, 0下架 默认是1，表示正常状态, -1表示删除, 0下架
     */

    private Integer productStatus;

    /**
     * 创建时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 商品内容 商品内容
     */
    private String content;

    /**
     * 商品所对应的图片集合
     */
    private List<ProductImg> productImgs;


    /**
     * 商品对应的套餐集合
     */
    private List<ProductSku> productSkus;

    /**
     * 商品分类名称
     */

    private  String categoryName;

    /**
     * 商品一级分类名称
     */
    private  String categoryFirstName;

    /**
     * 商品图片
     */
    private  String imgUrl;

}

