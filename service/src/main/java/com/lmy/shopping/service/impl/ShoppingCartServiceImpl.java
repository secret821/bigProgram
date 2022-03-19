package com.lmy.shopping.service.impl;

import com.lmy.shopping.entity.ShoppingCart;
import com.lmy.shopping.entity.ShoppingCartVo;
import com.lmy.shopping.mapper.ShoppingCartMapper;
import com.lmy.shopping.service.ShoppingCartService;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 购物车业务实现类
 *
 * @Author LMY
 * @Date 2022/3/2 10:39
 * @Version V1.0
 */

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public ResultVo addShoppingCart(ShoppingCart shoppingCart) {

        shoppingCart.setCartTime(sdf.format(new Date()));
        int insert = shoppingCartMapper.insert(shoppingCart);
        if (insert>0){
         return new ResultVo(StatusCode.STATUS_OK,"加购成功！",null);
        }else {
            return new ResultVo(StatusCode.STATUS_FAIL,"加购失败",null);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVo selectShoppingCartByUserId(int userId) {
        List<ShoppingCartVo> list = shoppingCartMapper.selectShoppingCartByUserId(userId);
        ResultVo resultVO = new ResultVo(StatusCode.STATUS_OK, "success", list);
        return resultVO;
    }

    @Override
    public ResultVo updateCartNum(int cartId, int cartNum) {
        int i = shoppingCartMapper.updateCartnumByCartid(cartId, cartNum);
        if(i>0){
            return  new ResultVo(StatusCode.STATUS_OK,"Success",null);
        }
        return  new ResultVo(StatusCode.STATUS_FAIL,"Fail",null);
    }

    @Override
    public ResultVo deleteCartByUserId(int cartId) {
        Example example=new Example(ShoppingCart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cartId",cartId);
        int i = shoppingCartMapper.deleteByExample(example);
        if(i>0){
            return  new ResultVo(StatusCode.STATUS_OK,"Success",null);
        }
        return  new ResultVo(StatusCode.STATUS_FAIL,"Fail",null);
    }
}
