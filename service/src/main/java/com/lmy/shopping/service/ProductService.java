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
     * 商品指定分类随机查询
     * @param num 随机查询的数量
     * @return
     */
    ResultVo ListProductRand(int root_category_id,int num);

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
    ResultVo queryProductByCategory3(int category_id, int pageNum, int limit);
}
