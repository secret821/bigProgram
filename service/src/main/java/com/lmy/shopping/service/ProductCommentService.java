package com.lmy.shopping.service;

import com.lmy.shopping.vo.ResultVo;

/**
 * 商品评论业务
 *
 * @Author LMY
 * @Date 2022/3/1 10:07
 * @Version V1.0
 */
public interface ProductCommentService {
    /**
     *
     * @param pageNum    页码
     * @param limit      每页条数
     * @param product_id 商品id
     * @return
     */
    ResultVo ListProductComments(int pageNum,int limit,String product_id);

    /**
     * 统计评价 类型 总数
     * @param product_id
     * @return
     */
    ResultVo ListProductCommentsNum(String product_id);
}
