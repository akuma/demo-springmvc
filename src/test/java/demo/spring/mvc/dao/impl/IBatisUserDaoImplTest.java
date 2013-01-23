/* 
 * @(#)IBatisUserDaoImplTest.java    Created on 2010-12-7
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

import demo.spring.mvc.dao.IBatisUserDao;
import demo.spring.mvc.entity.User;

/**
 * @author akuma
 */
public class IBatisUserDaoImplTest extends BasicTestCase {

    @Resource
    private IBatisUserDao iBatisUserDao;

    @Test
    public void testCrudOps() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(12));
        user.setPassword("123456");
        user.setRealName("测试用户");

        iBatisUserDao.insert(user);

        User dbUser = iBatisUserDao.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());

        user.setUsername(RandomStringUtils.randomAlphabetic(12));
        user.setPassword("123abc");
        user.setRealName("测试用户2");
        iBatisUserDao.update(user);

        dbUser = iBatisUserDao.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());
        assertEquals(dbUser.getUsername(), user.getUsername());
        assertEquals(dbUser.getPassword(), user.getPassword());
        assertEquals(dbUser.getRealName(), user.getRealName());

        // Update if necessary
        user.setUsername(null);
        user.setPassword(null);
        user.setRealName(null);
        iBatisUserDao.updateNotNull(user);

        dbUser = iBatisUserDao.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());
        assertFalse(dbUser.getUsername().equals(user.getUsername()));
        assertFalse(dbUser.getPassword().equals(user.getPassword()));
        assertFalse(dbUser.getRealName().equals(user.getRealName()));

        iBatisUserDao.delete(user.getId());
        dbUser = iBatisUserDao.find(user.getId());
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
        iBatisUserDao.insert(user1);
        iBatisUserDao.insert(user2);

        Map<Serializable, User> users = iBatisUserDao.find(user1.getId(), user2.getId());
        System.out.println(users);
    }

}
