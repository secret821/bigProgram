package com.lmy.shopping.service;

import com.lmy.shopping.entity.ProductComments;
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
     *
     * @param pageNum    页码
     * @param limit      每页条数
     * @param product_id 商品id 可为空
     * @return
     */
    ResultVo ListUserComment2(int pageNum,int limit,String product_id);
    /**
     * 统计评价 类型 总数
     * @param product_id
     * @return
     */
    ResultVo ListProductCommentsNum(String product_id);

    /**
     * 查询用户评价
     * @param userId
     * @return
     */

    ResultVo ListUserComment(int pageNum,int limit,String userId);

    /**
     * 用户评论
     * @param productComments
     * @return
     */
    ResultVo addProductComment(ProductComments productComments);


    /**
     * 修改 用户评论显示状态 1 表示显示 0表示不显示
     * @param commentId
     * @param status
     * @return
     */
    ResultVo updateCommentStatus(String commentId, int status);
}
