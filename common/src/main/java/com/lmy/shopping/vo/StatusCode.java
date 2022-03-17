package com.lmy.shopping.vo;

/**
 * 静态状态码
 *
 * @Author LMY
 * @Date 2022/2/9 15:46
 * @Version V1.0
 */

public class StatusCode {

    /**
     * 万物成功皆可状态码
     */
    public static final int STATUS_OK = 10000;

    /**
     * 万物失败皆可状态码
     */
    public static final int STATUS_FAIL = 99999;

    /**
     * 登录成功状态码
     */
    public static final int LOGIN_SUCCESS = 10001;

    /**
     * 登录失败状态码
     */
    public static final int LOGIN_FAIL = 10002;
    /**
     * 密码错误状态码
     */
    public static final int LOGIN_PASSWORD_MIS = 10003;

    /**
     * 登录用户名不存在状态码
     */
    public static final int LOGIN_ALREADY_EXISTS = 10004;
    /**
     * 注册成功状态码
     */
    public static final int REGISTER_SUCCESS = 10005;

    /**
     * 注册失败状态码
     */
    public static final int REGISTER_FAIL = 10006;

    /**
     * 注册用户名已存在状态码
     */
    public static final int REGISTER_ALREADY_EXISTS = 10007;

    /**
     * Token校验成功状态码
     * */
    public static final int TOKEN_SUCCESS = 10008;

    /**
     * Token过期状态码
     * */
    public static final int TOKEN_OVERDUE = 10009;

    /**
     * Token不合法状态码
     * */
    public static final int TOKEN_UN_LEGAL = 10010;

    /**
     * Token校验失败状态码
     * */
    public static final int TOKEN_FAIL = 10011;
}
