package com.lmy.shopping.mapper;

import com.lmy.shopping.entity.ProductComments;
import com.lmy.shopping.entity.ProductCommentsVo;
import com.lmy.shopping.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCommentsMapper extends GeneralDAO<ProductComments> {


    /**
     *  分页查询评论信息
     * @param start 起始数据
     * @param limit 每页数据量
     * @param product_id 商品id
     * @return
     */
    List<ProductCommentsVo> findAllProductComments(
            @Param("start") int start,
            @Param("limit") int limit,
            @Param("product_id") String product_id);



    List<ProductCommentsVo> findProductCommentsByUid(
            @Param("start") int start,
            @Param("limit") int limit,
            @Param("userId") String userId);

    List<ProductCommentsVo> findAllProductComments2(@Param("start") int start,
                                                    @Param("limit") int limit,
                                                    @Param("product_id") String product_id);
}