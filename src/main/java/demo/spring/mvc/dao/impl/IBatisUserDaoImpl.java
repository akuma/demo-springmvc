/* 
 * @(#)IBatisUserDaoImpl.java    Created on 2010-7-14
 * Copyright (c) 2012 Akuma. All rights reserved.
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

import demo.spring.mvc.dao.IBatisUserDao;
import demo.spring.mvc.entity.User;

/**
 * IBatisUserDaoImpl 实现类。
 * 
 * @author akuma
 */
@Repository
public class IBatisUserDaoImpl extends IBatisBasicDao implements IBatisUserDao {

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
