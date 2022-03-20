package com.lmy.shopping.service.impl;

import com.lmy.shopping.entity.UserAddr;
import com.lmy.shopping.mapper.UserAddrMapper;
import com.lmy.shopping.service.UserAdderService;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @desc
 * @Auther LMY233
 * @Date 2022/3/20
 */
@Repository
public class UserAdderServiceImpl implements UserAdderService {

    @Autowired
    private UserAddrMapper userAddrMapper;
    @Override
    public ResultVo selectUserAdder(int userId) {
        Example example=new Example(UserAddr.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId).andEqualTo("status",1);
        List<UserAddr> userAddrs = userAddrMapper.selectByExample(example);

        return new ResultVo(StatusCode.STATUS_OK,"success",userAddrs);
    }
}
