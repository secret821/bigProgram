package com.lmy.shopping.controller;

import com.lmy.shopping.entity.IndexImg;
import com.lmy.shopping.service.CategoryService;
import com.lmy.shopping.service.IndexService;
import com.lmy.shopping.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 首页业务Controller
 *
 * @Author LMY
 * @Date 2022/2/23 10:29
 * @Version V1.0
 */

@RestController
@CrossOrigin
@RequestMapping(value="index")
@Api(value = "首页数据展示接口", tags = "首页业务接口")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private CategoryService categoryService;



    @ApiOperation("轮播图展示接口")
    @GetMapping(value = "imgShow")
    public ResultVo indexImageShow(){
        ResultVo resultVo = indexService.ListIndexImg();
        return resultVo;
    }

    @ApiOperation("管理员查询")
    @GetMapping(value = "img")
    public ResultVo indexImage(){
        ResultVo resultVo = indexService.ListImg();
        return resultVo;
    }

    @ApiOperation("分类菜单展示接口")
    @GetMapping(value = "categoryShow")
    public ResultVo indexCategory(){
        ResultVo resultVo=indexService.ListIndexCategory();
        return resultVo;
    }
    @ApiOperation("一级分类下商品展示接口")
    @GetMapping(value="categoryProShow")
    public  ResultVo indexFirstCategoryProTop6(){
        ResultVo resultVo=categoryService.listCategoriesPro();
        return resultVo;
    }

    @ApiOperation("通过ID查询")
    @GetMapping(value = "queryById")
    public ResultVo queryImage(int imgId){
        ResultVo resultVo = indexService.queryImgById(imgId);
        return resultVo;
    }

    @ApiOperation("查询图片样式")
    @GetMapping(value = "queryStyle")
    public ResultVo queryStyle(){
        ResultVo resultVo = indexService.queryStyle();
        return resultVo;
    }

    @ApiOperation("修改图片的状态")
    @PutMapping(value = "updateImgStatus")
    public ResultVo updateStatus(int imgId,int status){
        ResultVo resultVo = indexService.updateStatus(imgId,status);
        return resultVo;
    }


    @ApiOperation("修改图片")
    @PutMapping(value = "updateImg")
    public ResultVo updateImge(@RequestBody IndexImg indexImg){
        ResultVo resultVo = indexService.updateImg(indexImg);
        return resultVo;
    }
}