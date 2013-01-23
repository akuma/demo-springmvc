/* 
 * @(#)BasicBatchMapper.java    Created on 2013-1-22
 * Copyright (c) 2013 Guomi. All rights reserved.
 */
package demo.spring.mvc.repository;

import com.guomi.meazza.orm.mybatis.MyBatisBasicDao;

/**
 * 执行批量 SQL 的 MyBatis Mapper 基础接口。
 * 
 * @author akuma
 */
public interface BasicBatchMapper<T> extends MyBatisBasicDao<T> {
}
