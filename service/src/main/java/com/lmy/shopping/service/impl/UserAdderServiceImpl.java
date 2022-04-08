package com.lmy.shopping.service.impl;

import com.lmy.shopping.entity.UserAddr;
import com.lmy.shopping.mapper.UserAddrMapper;
import com.lmy.shopping.service.UserAdderService;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
        Example example = new Example(UserAddr.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId).andEqualTo("status", 1);
        List<UserAddr> userAddrs = userAddrMapper.selectByExample(example);

        return new ResultVo(StatusCode.STATUS_OK, "success", userAddrs);
    }


    @Transactional
    @Override
    public ResultVo updataUserAdder(String uid, String addrId) {
        Example example = new Example(UserAddr.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", uid)
                .andEqualTo("commonAddr", 1)
                .andEqualTo("status",1);
        List<UserAddr> userAddrs = userAddrMapper.selectByExample(example);
        if (userAddrs.size()>0){
            userAddrs.get(0).setCommonAddr(0);
            userAddrMapper.updateByPrimaryKeySelective(userAddrs.get(0));
        }
        UserAddr userAddr1 = userAddrMapper.selectByPrimaryKey(addrId);
        if (userAddr1!=null && userAddr1.getStatus()==1){
            userAddr1.setCommonAddr(1);
            userAddrMapper.updateByPrimaryKeySelective(userAddr1);
            return new ResultVo(StatusCode.STATUS_OK,"success",null);
        }
       return new ResultVo(StatusCode.STATUS_FAIL,"fail",null);
    }
}
