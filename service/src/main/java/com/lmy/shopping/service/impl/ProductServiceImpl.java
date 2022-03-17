package com.lmy.shopping.service.impl;

import com.lmy.shopping.entity.*;
import com.lmy.shopping.mapper.ProductImgMapper;
import com.lmy.shopping.mapper.ProductMapper;
import com.lmy.shopping.mapper.ProductParamsMapper;
import com.lmy.shopping.mapper.ProductSkuMapper;
import com.lmy.shopping.service.ProductService;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

/**
 * 商品业务实现类
 *
 * @Author LMY
 * @Date 2022/2/24 15:02
 * @Version V1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImgMapper productImgMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;


    @Autowired
    private ProductParamsMapper productParamsMapper;

    @Override
    public ResultVo ListIndexProductRecommend() {
        HashMap<String, Object> indexMap = new HashMap<>();
        List<ProductVO> productVOS = productMapper.queryProduct();
        if (productVOS.size() < 3) {
            return new ResultVo(StatusCode.STATUS_FAIL, "后台商品数量不足", null);
        } else {
            indexMap.put("RecommendPro", productVOS);
            return new ResultVo(StatusCode.STATUS_OK, "推荐商品查询成功", indexMap);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVo ListBasicProInfo(int product_id) {
        HashMap<String, Object> proMap = new HashMap<>();
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", product_id)
                .andEqualTo("productStatus", 1);
        List<Product> products = productMapper.selectByExample(example);
        if (products.size() > 0) {
            Example example1 = new Example(ProductImg.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("itemId", product_id);
            List<ProductImg> productImgs = productImgMapper.selectByExample(example1);
            Example example2 = new Example(ProductSku.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("productId", product_id)
                    .andEqualTo("status", 1);
            List<ProductSku> productSkus = productSkuMapper.selectByExample(example2);
            proMap.put("product", products);
            proMap.put("productImgs", productImgs);
            proMap.put("productSkus", productSkus);
            return new ResultVo(StatusCode.STATUS_OK, "查询成功！", proMap);
        }
        return new ResultVo(StatusCode.STATUS_FAIL, "该商品不存在", null);
    }

    @Override
    public ResultVo ListProductRand(int root_category_id, int num) {
        return null;
    }

    @Override
    public ResultVo queryProductParams(int product_id) {
        HashMap<String, Object> proMap = new HashMap<>();
        Example example = new Example(ProductParams.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",product_id);
        List<ProductParams> productParams = productParamsMapper.selectByExample(example);
            if (productParams.size()>0){
                proMap.put("productParams",productParams);
                return new ResultVo(StatusCode.STATUS_OK,"success",proMap);
            }
        return null;
    }


    @Override
    public ResultVo ListProductRand(int num) {
        HashMap<String, Object> proMap = new HashMap<>();
        List<ProductVO> randProduct = productMapper.queryProductRand(5);
        proMap.put("randProduct",randProduct);
        return new ResultVo(StatusCode.STATUS_OK,"success",proMap);
    }
}
