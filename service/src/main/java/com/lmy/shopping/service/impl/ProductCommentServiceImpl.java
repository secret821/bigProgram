package com.lmy.shopping.service.impl;

import com.lmy.shopping.entity.ProductComments;
import com.lmy.shopping.entity.ProductCommentsVo;
import com.lmy.shopping.mapper.ProductCommentsMapper;
import com.lmy.shopping.service.ProductCommentService;
import com.lmy.shopping.vo.PageHelper;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 商品评论实现类
 *
 * @Author LMY
 * @Date 2022/3/1 10:08
 * @Version V1.0
 */

@Service
public class ProductCommentServiceImpl implements ProductCommentService {

    @Autowired
    private ProductCommentsMapper productCommentsMapper;


    @Override
    public ResultVo ListProductComments(int pageNum, int limit, String product_id) {
        HashMap<Object, Object> map = new HashMap<>();
        Example example = new Example(ProductComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", product_id)
                .andEqualTo("isShow", 1);
        //统计评论总数
        int count = productCommentsMapper.selectCountByExample(example);
        int pageCount = count % limit == 0 ? count / limit : count / limit + 1;
        int start = (pageNum - 1) * limit;
        List<ProductCommentsVo> list = productCommentsMapper.findAllProductComments(start, limit, product_id);
        return new ResultVo(StatusCode.STATUS_OK, "查询成功！", new PageHelper<ProductCommentsVo>(count, pageCount, list));
    }

    @Override
    public ResultVo ListProductCommentsNum(String product_id) {
        HashMap<Object, Object> map = new HashMap<>();
        Example example = new Example(ProductComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", product_id)
                .andEqualTo("isShow", 1);
        int count = productCommentsMapper.selectCountByExample(example);
        Example example1 = new Example(ProductComments.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("commType", 1)
                .andEqualTo("productId", product_id).andEqualTo("isShow", 1);
        int good = productCommentsMapper.selectCountByExample(example1);
        Example example2 = new Example(ProductComments.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("commType", 0)
                .andEqualTo("productId", product_id).andEqualTo("isShow", 1);
        int medium = productCommentsMapper.selectCountByExample(example2);
        Example example3 = new Example(ProductComments.class);
        Example.Criteria criteria3 = example3.createCriteria();
        criteria3.andEqualTo("commType", -1)
                .andEqualTo("productId", product_id).andEqualTo("isShow", 1);
        int bad = productCommentsMapper.selectCountByExample(example3);
        double percent = (Double.parseDouble(good + "") / Double.parseDouble(count + "")) * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        String percentValue = df.format(percent) + '%';
        map.put("count", count);
        map.put("medium", medium);
        map.put("bad", bad);
        map.put("good", good);
        map.put("percentValue", percentValue);
        return new ResultVo(StatusCode.STATUS_OK, "success", map);
    }

    @Override
    public ResultVo ListUserComment(int pageNum, int limit, String userId) {
        HashMap<Object, Object> map = new HashMap<>();
        Example example = new Example(ProductComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId)
                .andEqualTo("isShow", 1);
        //统计评论总数
        int count = productCommentsMapper.selectCountByExample(example);
        int pageCount = count % limit == 0 ? count / limit : count / limit + 1;
        int start = (pageNum - 1) * limit;
        List<ProductCommentsVo> list = productCommentsMapper.findProductCommentsByUid(start, limit, userId);
        return new ResultVo(StatusCode.STATUS_OK, "查询成功！", new PageHelper<ProductCommentsVo>(count, pageCount, list));
    }

    @Override
    public ResultVo addProductComment(ProductComments pm) {
        //设置评论时间
        pm.setSepcName(new Date());
        //设置默认回复状态 未回复
        pm.setReplyStatus(0);
        //设置是否显示 默认显示
        pm.setIsShow(1);
        int i = productCommentsMapper.insertUseGeneratedKeys(pm);
        if (i > 0) {
            return new ResultVo(StatusCode.STATUS_OK, "success", null);
        }
        return new ResultVo(StatusCode.STATUS_FAIL, "fail", null);
    }


    @Override
    public ResultVo updateCommentStatus(String commentId,int status) {
        ProductComments productComments = productCommentsMapper.selectByPrimaryKey(commentId);
        productComments.setIsShow(status);
        int i = productCommentsMapper.updateByPrimaryKeySelective(productComments);
        if (i>0){
            return new ResultVo(StatusCode.STATUS_OK,"success",null);
        }else {
            return new ResultVo(StatusCode.STATUS_FAIL,"fail",null);
        }


    }
}
