package com.lmy.shopping.mapper;

import com.lmy.shopping.entity.ProductSku;
import com.lmy.shopping.general.GeneralDAO;

import java.util.List;

public interface ProductSkuMapper extends GeneralDAO<ProductSku> {
    //查询商品中最低价格的sku套餐
    List<ProductSku> selectLowerestPriceByProductId(String product_id);
}