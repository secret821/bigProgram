package com.lmy.shopping.controller;

import com.lmy.shopping.entity.Users;
import com.lmy.shopping.service.UserAdderService;
import com.lmy.shopping.service.impl.UserAdderServiceImpl;
import com.lmy.shopping.service.impl.UserServiceImpl;
import com.lmy.shopping.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 关于用户管理的接口
 *
 * @Author LMY
 * @Date 2021/12/24 16:05
 * @Version V1.0
 */

@RestController
@RequestMapping(value = "/useraddr")
@Api(value = "接口", tags = "用户地址接口")
@CrossOrigin
public class UserAdderController {

    @Autowired
    private UserAdderServiceImpl  userAdderService;

    @ApiOperation("用户地址查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "userId", value = "用戶Id", required = true),
    })
    @GetMapping(value = "/list")
    public ResultVo checkLogin(int userId,@RequestHeader("token") String token) {
        ResultVo resultVo = userAdderService.selectUserAdder(userId);
        return resultVo;
    }


    @ApiOperation("默认地址修改")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "userId", value = "用戶Id", required = true),
            @ApiImplicitParam(dataType = "String", name = "addrId", value = "地址Id", required = true),
    })
    @GetMapping(value = "/updataStatus")
    public ResultVo updataAddr(String userId,String addrId,@RequestHeader("token") String token) {
        ResultVo resultVo = userAdderService.updataUserAdder(userId,addrId);
        return resultVo;
    }

}
