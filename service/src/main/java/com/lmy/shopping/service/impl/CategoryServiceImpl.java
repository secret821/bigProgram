package com.lmy.shopping.service.impl;

import com.lmy.shopping.entity.Category;
import com.lmy.shopping.entity.CategoryVO;
import com.lmy.shopping.mapper.CategoryMapper;
import com.lmy.shopping.service.CategoryService;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

/**
 * 类别业务实现类
 *
 * @Author LMY
 * @Date 2022/2/25 10:07
 * @Version V1.0
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResultVo listCategoriesPro() {
        HashMap<String, Object> indexMap = new HashMap<>();
        List<CategoryVO> categoryVOS = categoryMapper.selectFirstCategory();
        indexMap.put("categoryProTop6",categoryVOS);
        return new ResultVo(StatusCode.STATUS_OK,"查询成功",indexMap);
    }

    @Override
    public ResultVo queryCategory(int cid) {
        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",cid);
        List<Category> categories = categoryMapper.selectByExample(example);
        return new ResultVo(StatusCode.STATUS_OK,"success",categories.get(0));
    }
}
