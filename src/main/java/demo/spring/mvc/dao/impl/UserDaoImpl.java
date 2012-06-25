/* 
 * @(#)UserDaoImpl.java    Created on 2010-7-14
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id: UserDaoImpl.java 2 2012-04-05 08:03:20Z wj.huang $
 */
package demo.spring.mvc.dao.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.guomi.meazza.orm.ibatis.IBatisBasicDao;
import com.guomi.meazza.util.Pagination;

import demo.spring.mvc.dao.UserDao;
import demo.spring.mvc.entity.User;

/**
 * UserDaoImpl 实现类。
 * 
 * @author huangwj
 * @version $Revision: 2 $, $Date: 2012-04-05 16:03:20 +0800 (Thu, 05 Apr 2012) $
 */
@Repository
public class UserDaoImpl extends IBatisBasicDao implements UserDao {

    @Override
    public User find(Serializable id) {
        return queryForObject("findUser", id);
    }

    @Override
    public List<User> find(User entity) {
        return queryForList("findUsersByUser", entity);
    }

    @Override
    public List<User> find(User entity, Pagination page) {
        return queryForList("countUsersByUser", "findUsersByUser", entity, page);
    }

    @Override
    public Map<Serializable, User> find(Serializable... ids) {
        return queryForMap("findUserMap", Arrays.asList(ids), "id");
    }

    @Override
    public void insert(User... users) {
        for (User user : users) {
            user.setModifyTime(new Date());
            user.setCreationTime(new Date());
        }

        insert("insertUser", Arrays.asList(users));
    }

    @Override
    public int update(User... users) {
        return update("updateUser", Arrays.asList(users));
    }

    @Override
    public int updateNotNull(User... users) {
        return update("updateUserNotNull", users);
    }

    @Override
    public int delete(Serializable... ids) {
        return delete("deleteUsers", Arrays.asList(ids));
    }

    @Override
    protected String statementNamespace() {
        return "user";
    }

}
