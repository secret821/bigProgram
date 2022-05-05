package com.lmy.shopping.service.impl;

import com.lmy.shopping.entity.CategoryVO;
import com.lmy.shopping.entity.IndexImg;
import com.lmy.shopping.entity.ProductVO;
import com.lmy.shopping.entity.Users;
import com.lmy.shopping.mapper.CategoryMapper;
import com.lmy.shopping.mapper.IndexImgMapper;
import com.lmy.shopping.mapper.ProductMapper;
import com.lmy.shopping.service.IndexService;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 首页业务实现类
 *
 * @Author LMY
 * @Date 2022/2/23 10:22
 * @Version V1.0
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexImgMapper indexImgMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResultVo ListIndexImg() {
        HashMap<String, Object> indexMap = new HashMap<>();
        List<IndexImg> indexImgs = indexImgMapper.listIndexImage();
        if (indexImgs.size() == 0) {
            return new ResultVo(StatusCode.STATUS_FAIL, "后台图片查询出现错误", null);
        } else {
            indexMap.put("indexImgs", indexImgs);
            return new ResultVo(StatusCode.STATUS_OK, "success", indexMap);
        }
    }

    @Override
    public ResultVo ListIndexCategory() {
        HashMap<String, Object> indexMap = new HashMap<>();
        List<CategoryVO> categoryVOS = categoryMapper.selectAllCategory();
        indexMap.put("category", categoryVOS);
        return new ResultVo(StatusCode.STATUS_OK, "success", indexMap);
    }


    @Override
    public ResultVo ListImg() {
        HashMap<String, Object> indexMap = new HashMap<>();
        List<IndexImg> indexImgs = indexImgMapper.listImage();
        if (indexImgs.size() == 0) {
            return new ResultVo(StatusCode.STATUS_FAIL, "后台图片查询出现错误", null);
        } else {
            indexMap.put("indexImgs", indexImgs);
            return new ResultVo(StatusCode.STATUS_OK, "success", indexMap);
        }
    }

    @Override
    public ResultVo queryImgById(int imgId) {
        IndexImg indexImg = indexImgMapper.selectByPrimaryKey(imgId);
        return new ResultVo(StatusCode.STATUS_OK, "success", indexImg);
    }

    @Override
    public ResultVo updateStatus(int imgId, int status) {
        IndexImg indexImg = indexImgMapper.selectByPrimaryKey(imgId);
        indexImg.setStatus(status);
        int i = indexImgMapper.updateByPrimaryKeySelective(indexImg);
        if (i > 0) {
            return new ResultVo(StatusCode.STATUS_OK, "success", null);
        }
        return new ResultVo(StatusCode.STATUS_FAIL, "fail", null);
    }


    @Override
    public ResultVo updateImg(IndexImg indexImg) {
        Example example = new Example(IndexImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("imgId", indexImg.getImgId());
        IndexImg indexImg1 = indexImgMapper.selectOneByExample(example);
        indexImg1.setSeq(indexImg.getSeq());
        indexImg1.setImgUrl(indexImg.getImgUrl());
        indexImg1.setUpdateTime(new Date());
        int i = indexImgMapper.updateByPrimaryKeySelective(indexImg);
        if (i > 0) {
            return new ResultVo(StatusCode.STATUS_OK, "success", null);
        }
        return new ResultVo(StatusCode.STATUS_FAIL, "fail", null);
    }


    @Override
    public ResultVo queryStyle() {
        List<String> strings = indexImgMapper.selectOneRow();
        return new ResultVo(StatusCode.STATUS_OK, "success", strings);
    }
}
