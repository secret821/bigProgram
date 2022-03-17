package com.lmy.shopping.mapper;

import com.lmy.shopping.entity.ProductImg;
import com.lmy.shopping.general.GeneralDAO;

import java.util.List;

public interface ProductImgMapper extends GeneralDAO<ProductImg> {

    //查询主图
    List<ProductImg> queryProductImg(int product_id);

    //查询该商品的所有图片
    List<ProductImg> queryProductImgs(int product_id);
}