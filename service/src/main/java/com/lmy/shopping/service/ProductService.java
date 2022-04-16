package com.lmy.shopping.service;

import com.lmy.shopping.general.GeneralDAO;
import com.lmy.shopping.vo.ResultVo;

/**
 * @Author LMY
 * @Date 2022/2/24 15:01
 * @Version V1.0
 */
public interface ProductService {
    /**
     * 完成首页推荐业务
     * @return
     */
    ResultVo ListIndexProductRecommend();

    /**
     * 商品详情页 查询商品基本信息
     * @return
     */
    ResultVo ListBasicProInfo(int product_id);


    /**
     * 全商品随机查询
     * @param num 随机数
     * @return
     */
    ResultVo ListProductRand(int num);

    /**
     * 商品公共基本参数查询
     * @param product_id
     * @return
     */

    ResultVo queryProductParams(int product_id);

    /**
     * 三級分類下的商品查詢
     * @return
     */
    ResultVo queryProductByCategory3(int category_id, int pageNum, int limit,String brand);


    /**
     * 查询三级分类下的商品品牌
     * @param category_id
     * @return
     */
    ResultVo queryBrand(int category_id);



    /**
     * 根据关键词查询商品
     * @param keyWord
     * @param pageNum
     * @param limit
     * @param brand
     * @return
     */
    ResultVo queryProductByKeyWord(String keyWord,int pageNum, int limit,String brand);

    /**
     * 根据关键字查询的商品的品牌
     * @param keyWord
     * @return
     */
    ResultVo queryBrandByKeyWord(String keyWord);


    /**
     *
     */
    ResultVo ListAllProduct(String keyWord,int pageNum,int limit);

}
