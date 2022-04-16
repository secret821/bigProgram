package com.lmy.shopping.service;

import com.lmy.shopping.entity.Users;
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
     ResultVo checkUser(String name, String password);


    /**
     * 管理员登录校验
     * @param name
     * @param password
     * @return
     */
    ResultVo checkMangerLogin(String name, String password);
    /**
     * 用户注册
     * @param name 用户名
     * @param password  密码
     * @return
     */
     ResultVo userRegist(String name, String password);

    /**
     * 用户信息查询
     * @param uid
     * @return
     */
     ResultVo userInfo(String uid);

    /**
     * 用户信息查询
     * @return
     */

    ResultVo ListUsersInfo(int pageNum,int limit,String username);
    /**
     * 更新用户信息
     */
    ResultVo updateUser(Users user);


    /**
     * 修改用户状态
     * @param status
     * @return
     */
    ResultVo updateUser(int uid,int status);


    /**
     * 修改用户名
     * @param uid
     * @param username
     * @return
     */
    ResultVo updateUserName(int uid,String username);
}
