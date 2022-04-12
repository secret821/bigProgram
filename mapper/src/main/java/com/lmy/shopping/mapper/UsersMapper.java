package com.lmy.shopping.mapper;

import com.lmy.shopping.entity.Users;
import com.lmy.shopping.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper extends GeneralDAO<Users> {

    /**
     * 分页查询用户信息
     */
    List<Users> ListUserInfo(
            @Param("start") int start,
            @Param("limit") int limit,
            @Param("username") String username
    );
}