package com.lmy.shopping.mapper;

import com.lmy.shopping.entity.Product;
import com.lmy.shopping.entity.ProductVO;
import com.lmy.shopping.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper extends GeneralDAO<Product> {

    List<ProductVO> queryProduct(); //查询所有最新上架时间的三个商品

    List<ProductVO> queryProductTop6(int cid); //查询某个一级类别下销量最高的6个商品

    List<ProductVO> queryProductRand(int randNum);//指定随机数随机查询

    List<ProductVO> queryProductByCategory3(
            @Param("start") int start,
            @Param("limit") int limit,
            @Param("cid") int cid,
            @Param("brand")String brand); //查詢三級分類下的所有商品（分頁查詢）


    List<String> queryBrandByCategory(int cid);


    List<String> queryBrandByKeyWord(String keyWord);

    List<ProductVO> queryProductByKeyWord(
            @Param("start") int start,
            @Param("limit") int limit,
            @Param("keyWord") String keyWord,
            @Param("brand")String brand);
}