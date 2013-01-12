/* 
 * @(#)BasicMapper.java    Created on 2012-8-21
 * Copyright (c) 2012 Guomi. All rights reserved.
 */
package demo.spring.mvc.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

/**
 * @author akuma
 */
public interface BasicMapper<T> {

    T find(Object id);

    List<T> findList(T entity);

    @MapKey("id")
    Map<Object, T> findMap(Object... ids);

    void insert(T entity);

    void update(T entity);

    void updateIfNecessary(T entity);

    void delete(Object... ids);

}
