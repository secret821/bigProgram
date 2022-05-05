package com.lmy.shopping.mapper;

import com.lmy.shopping.entity.IndexImg;
import com.lmy.shopping.general.GeneralDAO;

import java.util.List;

public interface IndexImgMapper extends GeneralDAO<IndexImg> {

    List<IndexImg> listIndexImage();

    List<IndexImg> listImage();

    List<String> selectOneRow();
 }