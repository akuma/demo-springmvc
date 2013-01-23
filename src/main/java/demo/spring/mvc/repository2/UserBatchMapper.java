/* 
 * @(#)UserBatchMapper.java    Created on 2013-1-22
 * Copyright (c) 2013 Guomi. All rights reserved.
 */
package demo.spring.mvc.repository2;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import com.guomi.meazza.orm.mybatis.MyBatisRepository;

import demo.spring.mvc.entity.User;
import demo.spring.mvc.repository.BasicBatchMapper;
import demo.spring.mvc.repository.UserDao;

/**
 * User 的 MyBatis Mapper 类，和 {@link UserDao} 的区别是此 Mapper 支持 SQL 批量操作。
 * 
 * @author akuma
 */
@MyBatisRepository
public interface UserBatchMapper extends BasicBatchMapper<User> {

    @MapKey("username")
    Map<Object, User> findMapByUsername(Object... usernames);

    /**
     * 使用 ID 列表进行删除操作。为了演示使用 list 参数而加。
     */
    void deleteByIdList(List<Integer> ids);

}