package com.lmy.shopping.controller;

import com.lmy.shopping.mapper.CategoryMapper;
import com.lmy.shopping.service.ProductCommentService;
import com.lmy.shopping.service.ProductService;
import com.lmy.shopping.service.impl.CategoryServiceImpl;
import com.lmy.shopping.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 关于商品的对外接口
 *
 * @Author LMY
 * @Date 2022/2/24 15:03
 * @Version V1.0
 */

@RestController
@RequestMapping(value = "product")
@CrossOrigin
@Api(value = "商品数据展示接口", tags = "商品业务接口")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCommentService productCommentService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @ApiOperation("首页推荐商品展示接口")
    @GetMapping(value = "recommendShow")
    public ResultVo indexRecommend(){
        ResultVo resultVo=productService.ListIndexProductRecommend();
        return resultVo;
    }

    @ApiOperation("商品基本信息接口")
    @GetMapping(value = "detail-info/{pid}")
    public ResultVo proBasicInfo(@PathVariable("pid") int  product_id){
        ResultVo resultVo=productService.ListBasicProInfo(product_id);
        return resultVo;
    }

    @ApiOperation("商品公共基本信息接口")
    @GetMapping(value = "param-info/{pid}")
    public ResultVo proBasicParamInfo(@PathVariable("pid") int  product_id){
        ResultVo resultVo=productService.queryProductParams(product_id);
        return resultVo;
    }


    @ApiOperation("商品分页查询评论信息接口")
    @GetMapping(value = "comments/{pid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true)
    })
    public ResultVo proComments(@PathVariable("pid") String  product_id,int pageNum,int limit){
        ResultVo resultVo=productCommentService.ListProductComments(pageNum,limit,product_id);
        return resultVo;
    }

    @ApiOperation("商品评价概述")
    @GetMapping(value = "commentNum/{pid}")
    public ResultVo ListProductRand(@PathVariable("pid") String  product_id){
        ResultVo resultVo=productCommentService.ListProductCommentsNum(product_id);
        return resultVo;
    }

    @ApiOperation("商品随机接口")
    @GetMapping(value = "randProduct/{num}")
    public ResultVo ListProductRand(@PathVariable("num") int  num){
        ResultVo resultVo=productService.ListProductRand(num);
        return resultVo;
    }

    @ApiOperation("根据商品三级分类查询接口")
    @GetMapping(value = "queryProduct/{cid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true),
            @ApiImplicitParam(dataType = "String",name = "brand", value = "品牌",required = false)
    })
    public ResultVo ListProductByCategoryId3(@PathVariable("cid") int  category_id,int pageNum,int limit,String brand){
        ResultVo resultVo=productService.queryProductByCategory3(category_id,pageNum,limit,brand);
        return resultVo;
    }

    @ApiOperation("根据商品关键字查询接口")
    @GetMapping(value = "queryProductByKeyWord/{keyWord}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true),
            @ApiImplicitParam(dataType = "String",name = "brand", value = "品牌",required = false)
    })
    public ResultVo ListProductByKeyWord(@PathVariable("keyWord") String keyWord,int pageNum,int limit,String brand){
        ResultVo resultVo=productService.queryProductByKeyWord(keyWord,pageNum,limit,brand);
        return resultVo;
    }

    @ApiOperation("查询商品品牌接口")
    @GetMapping(value = "queryBrand/{cid}")
    public ResultVo ListBrand(@PathVariable("cid") int cid){
        ResultVo resultVo=productService.queryBrand(cid);
        return resultVo;

    }
    @ApiOperation("查询商品品牌接口")
    @GetMapping(value = "queryBrandByKeyWord/{keyWord}")
    public ResultVo ListBrandByKeyWord(@PathVariable("keyWord") String keyWord){
        ResultVo resultVo=productService.queryBrandByKeyWord(keyWord);
        return resultVo;

    }
    @ApiOperation("查询類別接口")
    @GetMapping(value = "queryCategory/{cid}")
    public ResultVo queryCategory(@PathVariable("cid") int cid){
        ResultVo resultVo=categoryService.queryCategory(cid);
        return resultVo;
    }


    @ApiOperation("根据商品关键字查询接口")
    @GetMapping(value = "queryProduct")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true),
            @ApiImplicitParam(dataType = "String",name = "keyWord", value = "关键词",required = false)
    })
    public ResultVo ListProduct(String keyWord,int pageNum,int limit){
        ResultVo resultVo=productService.queryProduct(keyWord,pageNum,limit);
        return resultVo;
    }
}