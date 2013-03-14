/* 
 * @(#)UserDao.java    Created on 2013-03-13
 * Copyright (c) 2013 Guomi. All rights reserved.
 */
package demo.spring.mvc.dao;

import com.guomi.meazza.orm.mybatis.MyBatisBasicDao;
import com.guomi.meazza.orm.mybatis.MyBatisRepository;

import demo.spring.mvc.entity.User;

/**
 * 表 demo_user 对应的 DAO 接口。
 * 
 * @author gmcg (Guomi Code Generator)
 */
@MyBatisRepository
public interface UserDao extends MyBatisBasicDao<User> {
}
