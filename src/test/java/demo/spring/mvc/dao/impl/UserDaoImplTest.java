/* 
 * @(#)UserDaoImplTest.java    Created on 2010-12-7
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guomi.meazza.test.BasicTestCase;
import com.guomi.meazza.util.Pagination;

import demo.spring.mvc.dao.UserDao;
import demo.spring.mvc.entity.User;

/**
 * @author akuma
 */
public class UserDaoImplTest extends BasicTestCase {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImplTest.class);

    @Resource
    private UserDao userDao;

    /**
     * test.
     */
    @Test
    public void testFindSerializable() {
        try {
            throw new Exception("Test exception occurred", new SQLException("SQL test exception occurred",
                    "This is just a SQL test exception."));
        } catch (Exception ex) {
            // logger.error(ex);
            logger.error("An exception occurred", ex);
        }
    }

    /**
     * test.
     */
    @Test
    public void testFindUser() {
    }

    /**
     * test.
     */
    @Test
    public void testFindUserPagination() {
        User user = new User();
        user.setUsername("huangwj");
        user.setRealName("黄维佳");

        Pagination page = new Pagination();
        page.setPageNum(2);

        List<User> userList = userDao.find(user, page);
        System.out.println(userList);
    }

    /**
     * test.
     */
    @Test
    public void testFindSerializableArray() {
    }

    /**
     * test.
     */
    @Test
    public void testInsertUserArray() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(12));
        user.setPassword("123456");
        user.setRealName("测试用户");

        userDao.insert(user);
    }

    /**
     * test.
     */
    @Test
    public void testUpdateUserArray() {
    }

    /**
     * test.
     */
    @Test
    public void testUpdateNotNull() {
    }

    /**
     * test.
     */
    @Test
    public void testDeleteSerializableArray() {
    }

}
