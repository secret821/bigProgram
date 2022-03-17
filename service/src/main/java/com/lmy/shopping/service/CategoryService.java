package com.lmy.shopping.service;

import com.lmy.shopping.vo.ResultVo;

/**
 * 类别业务接口
 *
 * @Author LMY
 * @Date 2022/2/25 10:05
 * @Version V1.0
 */

public interface CategoryService {

    public ResultVo listCategoriesPro();//查询第一层级类别下销量前六的商品业务
}
