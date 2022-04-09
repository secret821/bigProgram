package com.lmy.shopping.controller;

import com.lmy.shopping.entity.ProductComments;
import com.lmy.shopping.entity.Users;
import com.lmy.shopping.service.ProductCommentService;
import com.lmy.shopping.service.impl.ProductCommentServiceImpl;
import com.lmy.shopping.service.impl.UserServiceImpl;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

/**
 * 关于用户管理的接口
 *
 * @Author LMY
 * @Date 2021/12/24 16:05
 * @Version V1.0
 */

@RestController
@RequestMapping(value = "/user")
@Api(value = "接口", tags = "用户管理接口")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(dataType = "string", name = "password", value = "用户密码", required = true)
    })
    @GetMapping(value = "/login")
    public ResultVo checkLogin(String username, String password) {
        ResultVo resultVo = userService.checkUser(username, password);
        return resultVo;
    }

    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "username", value = "用户注册账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "password", value = "用户注册密码",required = true)
    })
    @PostMapping("/regist")
    public ResultVo regist(@RequestBody Users users){
        ResultVo resultVo = userService.userRegist(users.getUsername(), users.getPassword());
        return resultVo;

    }

    @ApiOperation("用户Token校验接口")
    @GetMapping("/userTokenCheck")
    public ResultVo userTokenCheck(@RequestHeader("token")String token){
        return new ResultVo(StatusCode.STATUS_OK,"success",null);
    }

    @ApiOperation("用户详细信息查询接口")
    @GetMapping("/userInfo")
    public ResultVo userTokenCheck(String userId,@RequestHeader("token")String token){
        ResultVo resultVo = userService.userInfo(userId);
        return resultVo;
    }

    @ApiOperation("用户信息更新接口")
    @PostMapping("/updateUserInfo")
    public ResultVo UpdateUserInfo(@RequestBody Users user,@RequestHeader("token")String token){
        ResultVo resultVo = userService.updateUser(user);
        return resultVo;
    }

}
