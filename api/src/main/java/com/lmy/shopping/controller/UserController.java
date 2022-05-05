package com.lmy.shopping.controller;


import com.lmy.shopping.entity.Users;
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
    @ApiOperation("管理员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(dataType = "string", name = "password", value = "用户密码", required = true)
    })
    @GetMapping(value = "/mlogin")
    public ResultVo checkMangerLogin(String username, String password) {
        ResultVo resultVo = userService.checkMangerLogin(username, password);
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
    public ResultVo userInfo(String userId,@RequestHeader("token")String token){
        ResultVo resultVo = userService.userInfo(userId);
        return resultVo;
    }
    @ApiOperation("根据id查询用户")
    @GetMapping("/userInfoById")
    public ResultVo userInfoById(String userId){
        ResultVo resultVo = userService.userInfo(userId);
        return resultVo;
    }
    @ApiOperation("用户信息展示接口")
    @GetMapping("/usersInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true),
            @ApiImplicitParam(dataType = "String",name = "username", value = "用户名",required = false)
    })
    public ResultVo ListUsersInfo(int pageNum,int limit,String username){
        ResultVo resultVo = userService.ListUsersInfo(pageNum,limit,username);
        return resultVo;
    }


    @ApiOperation("用户信息更新接口")
    @GetMapping("/updateUserStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "uid", value = "用户ID",required = true),
            @ApiImplicitParam(dataType = "int",name = "status", value = "状态",required = true),
    })
    public ResultVo updateUserStatus(int uid,int status){
        ResultVo resultVo = userService.updateUser(uid,status);
        return resultVo;
    }

    @ApiOperation("用户名更新接口")
    @PostMapping("/updateUserName")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "userId", value = "用户ID",required = true),
            @ApiImplicitParam(dataType = "String",name = "username", value = "用户名",required = true),
    })
    public ResultVo updateUserName(int userId,String nickname){
        ResultVo resultVo = userService.updateUserName(userId,nickname);
        return resultVo;
    }

    @ApiOperation("用户信息更新接口")
    @PostMapping("/updateUserInfo")
    public ResultVo UpdateUserInfo(@RequestBody Users user,@RequestHeader("token")String token){
        ResultVo resultVo = userService.updateUser(user);
        return resultVo;
    }

}
