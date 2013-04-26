/* 
 * @(#)CustomUserDao.java    Created on 2013-4-26
 * Copyright (c) 2013 Guomi. All rights reserved.
 */
package demo.spring.mvc.dao;

import com.guomi.meazza.orm.mybatis.MyBatisRepository;

import demo.spring.mvc.entity.User;

/**
 * 自定义的 UserDa 接口，为了测试 mybatis mapper 扩展。
 * 
 * @author akuma
 */
@MyBatisRepository
public interface CustomUserDao {

    User findByUsername(String username);

}
