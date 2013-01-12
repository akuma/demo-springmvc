/* 
 * @(#)UserDaoImplTest.java    Created on 2010-12-7
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.guomi.meazza.test.BasicTestCase;

import demo.spring.mvc.dao.UserDao;
import demo.spring.mvc.entity.User;

/**
 * @author akuma
 */
public class UserDaoImplTest extends BasicTestCase {

    @Resource
    private UserDao userDao;

    @Test
    public void testCrudOps() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(12));
        user.setPassword("123456");
        user.setRealName("测试用户");

        userDao.insert(user);

        User dbUser = userDao.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());

        user.setUsername(RandomStringUtils.randomAlphabetic(12));
        user.setPassword("123abc");
        user.setRealName("测试用户2");
        userDao.update(user);

        dbUser = userDao.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());
        assertEquals(dbUser.getUsername(), user.getUsername());
        assertEquals(dbUser.getPassword(), user.getPassword());
        assertEquals(dbUser.getRealName(), user.getRealName());

        // Update if necessary
        user.setUsername(null);
        user.setPassword(null);
        user.setRealName(null);
        userDao.updateNotNull(user);

        dbUser = userDao.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());
        assertFalse(dbUser.getUsername().equals(user.getUsername()));
        assertFalse(dbUser.getPassword().equals(user.getPassword()));
        assertFalse(dbUser.getRealName().equals(user.getRealName()));

        userDao.delete(user.getId());
        dbUser = userDao.find(user.getId());
        assertNull(dbUser);
    }

    @Test
    public void testBatchOps() {
        // Batch insert
        User user1 = new User();
        user1.setUsername(RandomStringUtils.randomAlphabetic(12));
        user1.setPassword("123456");
        user1.setRealName("测试用户");

        User user2 = new User();
        user2.setUsername(RandomStringUtils.randomAlphabetic(12));
        user2.setPassword("123456");
        user2.setRealName("测试用户");

        // userDao.inserts(user1, user2);
        userDao.insert(user1);
        userDao.insert(user2);

        Map<Serializable, User> users = userDao.find(user1.getId(), user2.getId());
        System.out.println(users);
    }

}
