package com.qfedu.fmmall;

import com.qfedu.fmmall.dao.CategoryMapper;
import com.qfedu.fmmall.dao.ProductCommentsMapper;
import com.qfedu.fmmall.dao.ProductMapper;
import com.qfedu.fmmall.dao.ShoppingCartMapper;
import com.qfedu.fmmall.entity.*;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
class ApiApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductCommentsMapper productCommentsMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Test
    void contextLoads() {
        List<CategoryVO> categoryVOS = categoryMapper.selectAllCategories2(0);
        for(CategoryVO c1 : categoryVOS){
            System.out.println(c1);
            for(CategoryVO c2 : c1.getCategories()){
                System.out.println("\t"+c2);
                for(CategoryVO c3 : c2.getCategories()){
                    System.out.println("\t\t"+c3);
                }
            }
        }
    }

    @Test
    public void testRecommand(){
        List<ProductVO> productVOS = productMapper.selectRecommendProducts();
        for(ProductVO p:productVOS){
            System.out.println(p);
        }
    }

    @Test
    public void testSelectFirstLevelCategory(){
        List<CategoryVO> categoryVOS = categoryMapper.selectFirstLevelCategories();
        for (CategoryVO c:categoryVOS) {
            System.out.println(c);
        }
    }

    @Test
    public void testSelectCommonts(){
//        List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.selectCommontsByProductId("3");
//        for (ProductCommentsVO p: productCommentsVOS){
//            System.out.println(p);
//        }
    }

    @Test
    public void testShopCart(){
        String cids = "1,8";
        String[] arr = cids.split(",");
        List<Integer> cidsList = new ArrayList<>();
        for (int i = 0; i <arr.length ; i++) {
            cidsList.add(Integer.parseInt(arr[i]));
        }
        List<ShoppingCartVO> list = shoppingCartMapper.selectShopcartByCids(cidsList);
        for (ShoppingCartVO sc: list) {
            System.out.println(sc);
        }
    }

}
