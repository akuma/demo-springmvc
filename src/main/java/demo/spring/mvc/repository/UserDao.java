/* 
 * @(#)UserDao.java    Created on 2012-7-31
 * Copyright (c) 2012 Guomi. All rights reserved.
 */
package demo.spring.mvc.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import com.guomi.meazza.orm.mybatis.MyBatisBasicDao;
import com.guomi.meazza.orm.mybatis.MyBatisRepository;

import demo.spring.mvc.entity.User;

/**
 * User 的 MyBatis Mapper 类。
 * 
 * @author akuma
 */
@MyBatisRepository
public interface UserDao extends MyBatisBasicDao<User> {

    @MapKey("username")
    Map<String, User> findMapByUsername(String... usernames);

    /**
     * 使用 ID 列表进行删除操作。为了演示使用 list 参数而加。
     */
    void deleteByIdList(List<Integer> ids);

}
