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


    @Override
    public ResultVo queryAllCategory(int categoryLevel,int parent_id) {
        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryLevel",categoryLevel)
                .andEqualTo("parentId",parent_id);
        List<Category> categories = categoryMapper.selectByExample(example);
        return new ResultVo(StatusCode.STATUS_OK,"success",categories);
    }

    @Override
    public ResultVo deleteCategory(int cid) {
        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",cid);
        int i = categoryMapper.deleteByExample(example);
        if (i > 0) {
            return new ResultVo(StatusCode.STATUS_OK, "success", null);
        }
        return new ResultVo(StatusCode.STATUS_FAIL, "fail", null);
    }

    @Override
    public ResultVo updateCategory(Integer cid, Category category) {
        Category category1 = categoryMapper.selectByPrimaryKey(cid);
        category1.setCategoryName(category.getCategoryName());
        if (category.getCategorySlogan()!=null && category.getCategorySlogan()!=""){
            category1.setCategorySlogan(category.getCategorySlogan());
        }
        int i = categoryMapper.updateByPrimaryKeySelective(category1);
        if (i > 0) {
            return new ResultVo(StatusCode.STATUS_OK, "success", null);
        }
        return new ResultVo(StatusCode.STATUS_FAIL, "fail", null);
    }
}
