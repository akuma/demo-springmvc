/* 
 * @(#)UserService.java    Created on 2010-7-14
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.service;

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
     */
    User getUser(Long id);

    /**
     * 根据用户名获取 User 信息。
     */
    User getUserByUsername(String username);

    /**
     * 获取 User 信息列表。
     */
    List<User> getUsers(User user);

    /**
     * 分页获取 User 信息列表。
     */
    List<User> getUsers(User user, Pagination page);

    /**
     * 添加 User 信息。
     */
    void addUser(User user);

    /**
     * 修改 User 信息。
     */
    void modifyUser(User user);

    /**
     * 修改 User 信息。
     */
    void modifyUserNotNull(User user);

    /**
     * 删除 User 信息。
     */
    void removeUsers(Long... ids);

}
