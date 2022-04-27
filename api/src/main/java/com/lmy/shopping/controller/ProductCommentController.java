package com.lmy.shopping.controller;

import com.lmy.shopping.entity.ProductComments;
import com.lmy.shopping.service.impl.ProductCommentServiceImpl;
import com.lmy.shopping.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc  用户评论接口
 * @Auther LMY233
 * @Date 2022/4/9
 */

@RestController
@RequestMapping(value = "/productCom")
@Api(value = "接口", tags = "评论管理接口")
@CrossOrigin
public class ProductCommentController {

    @Autowired
    private ProductCommentServiceImpl productCommentService;

    @ApiOperation("用户评论展示接口")
    @GetMapping("/listComment/{userId}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true)
    })
    public ResultVo UserComments(@PathVariable("userId") String userId,int pageNum,int limit, @RequestHeader("token") String token) {
        ResultVo resultVo = productCommentService.ListUserComment(pageNum,limit,userId);
        return resultVo;
    }



    @ApiOperation("管理員评论展示接口")
    @GetMapping("/listComment")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true),
            @ApiImplicitParam(dataType = "String",name = "pid", value = "商品id",required = false)
    })
    public ResultVo UserComments(String pid,int pageNum,int limit) {
        ResultVo resultVo = productCommentService.ListUserComment2(pageNum,limit,pid);
        return resultVo;
    }



    @ApiOperation("修改用户评论状态接口")
    @PutMapping("/updateCommentStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "commentId", value = "评论Id",required = true),
            @ApiImplicitParam(dataType = "int",name = "status", value = "显示状态",required = true)
    })
    public ResultVo UserComments(String commentId, int status, @RequestHeader("token") String token) {
        ResultVo resultVo = productCommentService.updateCommentStatus(commentId,status);
        return resultVo;
    }

    @ApiOperation("管理员修改用户评论状态接口")
    @GetMapping("/mupdateCommentStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "commentId", value = "评论Id",required = true),
            @ApiImplicitParam(dataType = "int",name = "status", value = "显示状态",required = true)
    })
    public ResultVo UserComments(String commentId, int status) {
        ResultVo resultVo = productCommentService.updateCommentStatus(commentId,status);
        return resultVo;
    }
    @ApiOperation("用户评论接口")
    @PostMapping("/addComment")
    public ResultVo UserComments(@RequestBody ProductComments productComments, @RequestHeader("token") String token) {
        ResultVo resultVo = productCommentService.addProductComment(productComments);
        return resultVo;
    }
}
