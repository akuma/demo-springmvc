/* 
 * @(#)UserService.java    Created on 2010-7-14
 * Copyright (c) 2,010 ZDSoft Networks, Inc. All rights reserved.
 * $Id: UserService.java 93 2012-05-16 01:24:15Z wj.huang $
 */
package demo.spring.mvc.service;

import java.io.Serializable;
import java.util.List;

import com.guomi.meazza.util.Pagination;

import demo.spring.mvc.entity.User;

/**
 * UserService 接口。
 * 
 * @author huangwj
 * @version $Revision: 93 $, $Date: 2012-05-16 09:24:15 +0800 (Wed, 16 May 2012) $
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
