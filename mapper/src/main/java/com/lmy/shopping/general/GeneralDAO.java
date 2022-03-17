package com.lmy.shopping.general;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * dao 基类
 * @param <T>
 */
public interface GeneralDAO<T> extends Mapper<T> , MySqlMapper<T> {
}
