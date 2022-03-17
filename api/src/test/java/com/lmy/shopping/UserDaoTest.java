package com.lmy.shopping;


import com.lmy.shopping.entity.Users;
import com.lmy.shopping.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =ApiApplication.class)
public class UserDaoTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void test() {
        Users user=new Users();
        user.setUsername("aaaa");
        user.setPassword("12345");
        user.setUserImg("img/default.png");
        user.setUserRegtime(new Date());
        user.setUserRegtime(new Date());
        usersMapper.insert(user);
        System.out.println(usersMapper);
    }

}