package com.lmy.shopping;

import com.lmy.shopping.entity.ProductImg;
import com.lmy.shopping.entity.ProductVO;
import com.lmy.shopping.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Console;
import java.util.List;

/**
 * @Author LMY
 * @Date 2022/2/24 14:44
 * @Version V1.0
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes =ApiApplication.class)
public class productDaoTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test1(){
        List<ProductVO> productVOS = productMapper.queryProduct();
        for (ProductVO p:productVOS){
            System.out.println(p);
            for (ProductImg p2:p.getProductImgs()){
                System.out.println(p2);
            }
        }
    }
}
