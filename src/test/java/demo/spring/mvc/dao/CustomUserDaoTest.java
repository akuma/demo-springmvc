/* 
 * @(#)CustomUserDaoTest.java    Created on 2013-4-26
 * Copyright (c) 2013 Guomi. All rights reserved.
 */
package demo.spring.mvc.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.guomi.meazza.test.BasicTestCase;

import demo.spring.mvc.entity.User;

/**
 * @author akuma
 */
public class CustomUserDaoTest extends BasicTestCase {

    @Resource
    private CustomUserDao customUserDao;

    @Test
    public void testFindByUsername() {
        User user = customUserDao.findByUsername("admin");
        System.out.println(user);
    }

}
