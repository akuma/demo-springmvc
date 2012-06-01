/* 
 * @(#)UserDao.java    Created on 2010-7-14
 * Copyright (c) 2,010 ZDSoft Networks, Inc. All rights reserved.
 * $Id: UserDao.java 93 2012-05-16 01:24:15Z wj.huang $
 */
package demo.spring.mvc.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import demo.spring.mvc.entity.User;
import nova.util.Pagination;

/**
 * UserDao 接口.
 * 
 * @author huangwj
 * @version $Revision: 93 $, $Date: 2012-05-16 09:24:15 +0800 (Wed, 16 May 2012) $
 */
public interface UserDao {

    /**
     * 获取User实体。
     * 
     * @param id
     *            user ID
     * @return user object
     */
    User find(Serializable id);

    /**
     * 获取User实体列表。
     * 
     * @param user
     *            user object
     * @return user list
     */
    List<User> find(User user);

    /**
     * 分页获取User实体列表。
     * 
     * @param user
     *            user object
     * @param page
     *            pagination
     * @return user list
     */
    List<User> find(User user, Pagination page);

    /**
     * 获取以主键为 key，User实体为 value 的 Map。
     * 
     * @param ids
     *            user IDs
     * @return user map
     */
    Map<Serializable, User> find(Serializable... ids);

    /**
     * 添加 User 实体。
     * 
     * @param users
     *            user objects
     */
    void insert(User... users);

    /**
     * 以主键ID为条件更新User，所有字段都将被更新。
     * 
     * @param users
     *            user objects
     * @return 更新到的 user 记录数
     */
    int update(User... users);

    /**
     * 以主键ID为条件更新User实体，所有非空字段都将被更新。
     * 
     * @param users
     *            user objects
     * @return 更新到的 user 记录数
     */
    int updateNotNull(User... users);

    /**
     * 根据主键ID 删除User实体。
     * 
     * @param ids
     *            user IDs
     * @return 更新到的 user 记录数
     */
    int delete(Serializable... ids);

}
