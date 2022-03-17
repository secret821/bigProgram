package com.lmy.shopping.service.impl;


import com.lmy.shopping.entity.Users;
import com.lmy.shopping.mapper.UsersMapper;
import com.lmy.shopping.service.UserService;
import com.lmy.shopping.utils.Base64Utils;
import com.lmy.shopping.utils.MD5Utils;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import tk.mybatis.mapper.entity.Example;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * none
 *
 * @Author LMY
 * @Date 2021/12/24 15:40
 * @Version V1.0
 */

@Service
@Scope("singleton")
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper userMapper;

    @Override
    @Transactional
    public ResultVo userRegist(String name, String password) {
        //根据用户名查询对应user
        synchronized (this) {
            Example example=new Example(Users.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username",name);
            List<Users> users = userMapper.selectByExample(example);

            if (users.size()== 0) {
                //用户名不存在,将获取的密码加密
                String md5Pwd = MD5Utils.md5(password);
                Users user = new Users();
                user.setUsername(name);
                user.setPassword(md5Pwd);
                user.setUserImg("img/default.png");
                user.setUserRegtime(new Date());
                user.setUserModtime(new Date());
                //插入用户数据
                int i = userMapper.insert(user);
                if (i > 0) {
                    return new ResultVo(StatusCode.REGISTER_SUCCESS, "注册成功!", user);
                } else {
                    return new ResultVo(StatusCode.REGISTER_FAIL, "注册失败!", null);
                }
            } else {
                return new ResultVo(StatusCode.REGISTER_ALREADY_EXISTS, "当前用户名已被注册!", null);
            }
    }
    }

    @Override
    public ResultVo checkUser(String username, String password) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        List<Users> users = userMapper.selectByExample(example);

        if (users.size()==0){
            return new ResultVo(StatusCode.LOGIN_ALREADY_EXISTS,"登录失败，用户名不存在,请重试！",null);
        }else {
            String md5Pwd = MD5Utils.md5(password);
            if(md5Pwd.equals(users.get(0).getPassword())){
                //登录成功，携带token返回前端
                /*String token= Base64Utils.encode(username+"lmy1018");*/
                JwtBuilder builder= Jwts.builder();
                HashMap<String,Object> map = new HashMap<>();
                //主题，就是token中携带的数据
                String token = builder.setSubject(username)
                        //设置token的生成时间
                        .setIssuedAt(new Date())
                        //设置用户id为token  id
                        .setId(users.get(0).getUserId() + "/")
                        //map中可以存放用户的角色权限信息
                        .setClaims(map)
                        //设置token过期时间
                        .setExpiration(new Date(System.currentTimeMillis() + 1000))
                        //设置加密方式和加密密码
                        .signWith(SignatureAlgorithm.HS256, "LY&&LMY")
                        .compact();

                return new ResultVo(StatusCode.LOGIN_SUCCESS,token,users.get(0));
          }else {
                return new ResultVo(StatusCode.LOGIN_PASSWORD_MIS,"登录失败，密码错误,请重试！",null);
            }
        }
    }

}
