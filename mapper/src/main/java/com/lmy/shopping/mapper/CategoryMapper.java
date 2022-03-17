package com.lmy.shopping.mapper;

import com.lmy.shopping.entity.Category;
import com.lmy.shopping.entity.CategoryVO;
import com.lmy.shopping.general.GeneralDAO;

import java.util.List;

public interface CategoryMapper extends GeneralDAO<Category> {

    List<CategoryVO> selectAllCategory();    //1.连接查询

    List<CategoryVO> selectAllCategory2(int parentId);   //2.子查询：根据parentId查询子分类

    List<CategoryVO> selectFirstCategory(); //查询以及分类
}