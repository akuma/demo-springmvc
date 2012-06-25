/* 
 * @(#)UserServiceImpl.java    Created on 2010-7-14
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id: UserServiceImpl.java 2 2012-04-05 08:03:20Z wj.huang $
 */
package demo.spring.mvc.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.guomi.meazza.dao.DataExistsException;
import com.guomi.meazza.util.Pagination;

import demo.spring.mvc.dao.UserDao;
import demo.spring.mvc.entity.User;
import demo.spring.mvc.service.UserService;

/**
 * UserServiceImpl 实现类。
 * 
 * @author huangwj
 * @version $Revision: 2 $, $Date: 2012-04-05 16:03:20 +0800 (Thu, 05 Apr 2012) $
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUser(Serializable id) {
        if (id == null) {
            return null;
        }

        return userDao.find(id);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> users = userDao.find(user);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<User> getUsers(User user) {
        if (user == null) {
            return Collections.emptyList();
        }

        return userDao.find(user);
    }

    @Override
    public List<User> getUsers(User user, Pagination page) {
        if (user == null || page == null) {
            return Collections.emptyList();
        }

        return userDao.find(user, page);
    }

    @Override
    public void addUser(User user) {
        if (user == null) {
            return;
        }

        User exists = getUserByUsername(user.getUsername());
        if (exists != null) {
            throw new DataExistsException("用户名[" + user.getUsername() + "]已经存在");
        }

        userDao.insert(user);
    }

    @Override
    public void modifyUser(User user) {
        if (user == null) {
            return;
        }

        User exists = getUserByUsername(user.getUsername());
        if (exists != null && !user.getId().equals(exists.getId())) {
            throw new DataExistsException("用户名[" + user.getUsername() + "]已经存在");
        }

        userDao.update(user);
    }

    @Override
    public void modifyUserNotNull(User user) {
        if (user == null) {
            return;
        }

        if (!StringUtils.isEmpty(user.getUsername())) {
            User exists = getUserByUsername(user.getUsername());
            if (exists != null && !user.getId().equals(exists.getId())) {
                throw new DataExistsException("用户名[" + user.getUsername() + "]已经存在");
            }
        }

        userDao.updateNotNull(user);
    }

    @Override
    public void removeUsers(Serializable... ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }

        userDao.delete(ids);
    }

}
