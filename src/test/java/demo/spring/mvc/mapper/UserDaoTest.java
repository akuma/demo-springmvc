/* 
 * @(#)UserDaoTest.java    Created on 2012-8-1
 * Copyright (c) 2012 Guomi. All rights reserved.
 */
package demo.spring.mvc.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.guomi.meazza.test.BasicTestCase;
import com.guomi.meazza.util.Pagination;

import demo.spring.mvc.entity.User;
import demo.spring.mvc.repository.UserDao;
import demo.spring.mvc.repository2.UserBatchMapper;

/**
 * @author akuma
 */
public class UserDaoTest extends BasicTestCase {

    @Resource
    private UserDao userDao;
    @Resource
    private UserBatchMapper userBatchMapper;

    @Test
    public void testCrudOps() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(10));
        user.setPassword("123456");
        user.setRealName("测试用户");

        User user2 = new User();
        user2.setUsername(RandomStringUtils.randomAlphabetic(10));
        user2.setPassword("123456");
        user2.setRealName("测试用户");
        user2.setBirthday(new Date());

        userDao.insert(user);
        userDao.insert(user2);

        User dbUser = userDao.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());

        user.setUsername(RandomStringUtils.randomAlphabetic(10));
        user.setPassword("123abc");
        user.setRealName("测试用户2");
        userDao.update(user);

        dbUser = userDao.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());
        assertEquals(dbUser.getUsername(), user.getUsername());
        assertEquals(dbUser.getPassword(), user.getPassword());
        assertEquals(dbUser.getRealName(), user.getRealName());

        Map<Object, User> users = userBatchMapper.findMapByUsername(user.getUsername(), user2.getUsername());
        assertEquals(2, users.size());
        assertNotNull(users.get(user.getUsername()));
        assertNotNull(users.get(user2.getUsername()));

        // Update if possible
        String username = user.getUsername();
        user.setUsername(null);
        user.setPassword("123");
        user.setRealName("foobar");
        userDao.updateIfPossible(user);
        user.setUsername(username); // Reset username

        dbUser = userDao.find(user.getId());
        assertNotNull(dbUser);
        assertEquals(dbUser.getId(), user.getId());
        assertEquals(user.getUsername(), dbUser.getUsername());
        assertEquals(user.getPassword(), dbUser.getPassword());
        assertEquals(user.getRealName(), dbUser.getRealName());

        userDao.delete(user.getId());
        dbUser = userDao.find(user.getId());
        assertNull(dbUser);
    }

    @Test
    public void testFindWithPage() {
        User user = new User();
        user.setRealName("a");
        user.setPassword("a");

        Pagination page = new Pagination();
        page.setPageSize(2);
        page.setPageNum(30);

        List<User> users = userDao.findByParamWithPage(user, page);

        System.out.println(users);
    }

}
