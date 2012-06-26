/* 
 * @(#)UserService.java    Created on 2010-7-14
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.service;

import java.io.Serializable;
import java.util.List;

import com.guomi.meazza.util.Pagination;

import demo.spring.mvc.entity.User;

/**
 * UserService 接口。
 * 
 * @author akuma
 */
public interface UserService {

    /**
     * 获取 User 信息。
     * 
     * @param id
     *            user ID
     * @return user object
     */
    User getUser(Serializable id);

    /**
     * 根据用户名获取 User 信息。
     * 
     * @param username
     *            用户名
     * @return user object
     */
    User getUserByUsername(String username);

    /**
     * 获取 User 信息列表。
     * 
     * @param user
     *            user object
     * @return user list
     */
    List<User> getUsers(User user);

    /**
     * 分页获取 User 信息列表。
     * 
     * @param user
     *            user object
     * @param page
     *            pagination
     * @return user list
     */
    List<User> getUsers(User user, Pagination page);

    /**
     * 添加 User 信息。
     * 
     * @param user
     *            user object
     */
    void addUser(User user);

    /**
     * 修改 User 信息。
     * 
     * @param user
     *            user object
     */
    void modifyUser(User user);

    /**
     * 修改 User 信息。
     * 
     * @param user
     *            user object
     */
    void modifyUserNotNull(User user);

    /**
     * 删除 User 信息。
     * 
     * @param ids
     *            user IDs
     */
    void removeUsers(Serializable... ids);

}
