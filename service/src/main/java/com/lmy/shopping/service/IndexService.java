package com.lmy.shopping.service;


import com.lmy.shopping.entity.IndexImg;
import com.lmy.shopping.vo.ResultVo;

import java.util.List;

/**
 * 首页业务
 *
 * @Author LMY
 * @Date 2022/2/23 10:19
 * @Version V1.0
 */

public interface IndexService {

    /**
     * 完成首页轮播图展示业务
     * @return
     */
    ResultVo ListIndexImg();

    /**
     * 完成首页分类展示业务
     * @return
     */
    ResultVo ListIndexCategory();


    /**
     * 管理员查查询
     * @return
     */
    ResultVo ListImg();

    /**
     * id 查询
     * @return
     */
    ResultVo queryImgById(int imgId);


    /**
     * 修改状态
     * @param imgId
     * @param status
     * @return
     */
    ResultVo updateStatus(int imgId,int status);
}
