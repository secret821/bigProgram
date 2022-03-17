package com.lmy.shopping;


import com.lmy.shopping.entity.Category;
import com.lmy.shopping.entity.CategoryVO;
import com.lmy.shopping.mapper.CategoryMapper;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =ApiApplication.class)
public class CategoryDaoTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void test() {
        Category category=new Category();
        category.setCategoryId(1);
        List<Category> categories = categoryMapper.selectAll();

        System.out.println(categories);
    /*    Iterator<Category> it = categories.iterator();
        while(it.hasNext()){//判断是否有迭代元素
            System.out.println(it.next());//输出迭代出的元素
        }*/

     /*   for(int i =0;i<categories.size();i++){
            System.out.println(categories.get(i));
        }*/

        /*  for(Category category1 :categories){
            System.out.println(category1);
        }*/

     /*   ListIterator<Category> categoryListIterator = categories.listIterator();
        while (categoryListIterator.hasNext())
        System.out.println(categoryListIterator.next());*/
    }


    @Test
    public  void test2(){
        Category category=new Category();
        category.setCategoryName("测试");
        category.setCategoryLevel(1);
        category.setParentId(1);
        categoryMapper.insert(category);
        System.out.println(category);
    }


    @Test
    public  void test3(){
        Category category=new Category();
        category.setCategoryName("测试2");
        category.setCategoryLevel(1);
        category.setParentId(1);
        categoryMapper.insertUseGeneratedKeys(category);
        System.out.println(category);
    }

    @Test
    public  void test4(){
        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryLevel",1);
        criteria.andLike("categoryName","%坚果%");
        List<Category> categories = categoryMapper.selectByExample(example);
        for(Category category:categories){
            System.out.println(category);
        }
    }


    @Test
    public  void test5(){
        int pageNum = 2;
        int pageSize = 10;
        int start = (pageNum-1)*pageSize;

        RowBounds rowBounds = new RowBounds(start,pageSize);
        List<Category> categories = categoryMapper.selectByRowBounds(new Category(), rowBounds);
        for (Category category: categories) {
            System.out.println(category);
        }

        //查询总记录数
        int i = categoryMapper.selectCount(new Category());
        System.out.println(i);
    }


    @Test
    public  void test6(){
        int pageNum = 2;
        int pageSize = 10;
        int start = (pageNum-1)*pageSize;
        RowBounds rowBounds = new RowBounds(start,pageSize);

        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryName","点心");
        List<Category> categories = categoryMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (Category category: categories) {
            System.out.println(category);
        }
    }

    @Test
    public  void test7(){
        List<CategoryVO> categoryVOS = categoryMapper.selectAllCategory();
            for (CategoryVO c1 :categoryVOS){
                System.out.println(c1);
                for (CategoryVO c2:c1.getCategories()){
                    System.out.println(c2);
                    for (CategoryVO c3:c2.getCategories()){
                        System.out.println(c3);
                    }
                }
            }
    }
    @Test
    public  void test8(){
        List<CategoryVO> categoryVOS = categoryMapper.selectAllCategory2(0);
        for (CategoryVO c1 :categoryVOS){
            System.out.println(c1);
            for (CategoryVO c2:c1.getCategories()){
                System.out.println(c2);
                for (CategoryVO c3:c2.getCategories()){
                    System.out.println(c3);
                }
            }
        }
    }
    @Test
    public void test9(){
        List<CategoryVO> categoryVOS = categoryMapper.selectFirstCategory();
        for (CategoryVO c1:categoryVOS){
            System.out.println(c1);
        }
    }
}