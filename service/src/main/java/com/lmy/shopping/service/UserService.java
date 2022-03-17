package com.lmy.shopping.service;

import com.lmy.shopping.vo.ResultVo;

/**
 * NONE
 *
 * @Author LMY
 * @Date 2021/12/24 15:39
 * @Version V1.0
 */


public interface UserService {

    /**
     *  用户登录校验
     * @param name 用户名
     * @param password  密码
     * @return
     */
    public ResultVo checkUser(String name, String password);

    /**
     * 用户注册
     * @param name 用户名
     * @param password  密码
     * @return
     */
    public ResultVo userRegist(String name, String password);
}
