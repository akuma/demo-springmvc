/* 
 * @(#)UserMapperTest.java    Created on 2012-8-1
 * Copyright (c) 2012 Guomi. All rights reserved.
 */
package demo.spring.mvc.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.guomi.meazza.test.BasicTestCase;

import demo.spring.mvc.entity.User;
import demo.spring.mvc.repository.UserMapper;

/**
 * @author akuma
 */
public class UserMapperTest extends BasicTestCase {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testCrudOps() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(12));
        user.setPassword("123456");
        user.setRealName("测试用户");

        userMapper.insert(user);

        User dbUser = userMapper.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());

        user.setUsername(RandomStringUtils.randomAlphabetic(12));
        user.setPassword("123abc");
        user.setRealName("测试用户2");
        userMapper.update(user);

        dbUser = userMapper.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());
        assertEquals(dbUser.getUsername(), user.getUsername());
        assertEquals(dbUser.getPassword(), user.getPassword());
        assertEquals(dbUser.getRealName(), user.getRealName());

        // // Update if necessary
        // user.setUsername(null);
        // user.setPassword(null);
        // user.setRealName(null);
        // userMapper.update(user);
        //
        // dbUser = userMapper.find(user.getId());
        // assertNotNull(dbUser);
        // assertEquals(dbUser.getId(), user.getId());
        // assertFalse(dbUser.getUsername().equals(user.getUsername()));
        // assertFalse(dbUser.getPassword().equals(user.getPassword()));
        // assertFalse(dbUser.getRealName().equals(user.getRealName()));

        userMapper.delete(user.getId());
        dbUser = userMapper.find(user.getId());
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

        // userMapper.inserts(user1, user2);
        userMapper.insert(user1);
        userMapper.insert(user2);

        Map<Object, User> users = userMapper.findMap(user1.getId(), user2.getId());
        System.out.println(users);
    }

}
