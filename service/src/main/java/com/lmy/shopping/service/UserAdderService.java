package com.lmy.shopping.service;

import com.lmy.shopping.entity.UserAddr;
import com.lmy.shopping.general.GeneralDAO;
import com.lmy.shopping.vo.ResultVo;

/**
 * @desc
 * @Auther LMY233
 * @Date 2022/3/20
 */
public interface UserAdderService {

    ResultVo selectUserAdder(int userId);
}
