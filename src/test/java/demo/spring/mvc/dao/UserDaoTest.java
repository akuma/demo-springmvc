/* 
 * @(#)UserDaoTest.java    Created on 2010-12-7
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;

import com.guomi.meazza.test.BasicTestCase;

import demo.spring.mvc.entity.User;

/**
 * @author akuma
 */
public class UserDaoTest extends BasicTestCase {

    @Resource
    private UserDao userDao;
    @Resource
    private SqlSessionTemplate sessionTemplate;

    @Test
    public void testCrudOps() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(12));
        user.setPassword("123456");
        user.setRealName("测试用户");

        userDao.insert(user);
        userDao.find(null);

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
        userDao.updateIfPossible(user);

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

        //userDao.inserts(user1, user2);
        userDao.insert(user1);
        userDao.insert(user2);

        // 现在还获取不到生成的 ID
        System.out.println(user1.getId());
        System.out.println(user2.getId());

        // 刷新 batch statements，生成的主键才能返回
        sessionTemplate.flushStatements();

        // 现在可以获得生成的 ID
        System.out.println(user1.getId());
        System.out.println(user2.getId());

        Map<Long, User> userMap = userDao.findMap(user1.getId(), user2.getId());
        assertTrue(userMap.size() == 2);

        List<User> userList = userDao.findByIds(user1.getId(), user2.getId());
        assertTrue(userList.size() == 2);

        // 测试分页方法
        //        Map<String, String> param = new HashMap<>();
        //        param.put("username", "%");
        //        Pagination page = new Pagination();
        //        page.setPageNum(2);
        //        User user = new User();
        //        user.setUsername("%");
        //        userDao.findByParamWithPage(user, page);
    }

}
