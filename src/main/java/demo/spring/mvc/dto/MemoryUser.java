/*
 * @(#)MemoryUser.java    Created on 2010-7-14
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.dto;

import java.io.Serializable;

/**
 * 用于保存在 Session 中的用户信息类。
 * 
 * @author akuma
 */
public class MemoryUser implements Serializable {

    /**
     * MemoryUser 对象在 session 中的 key 值。
     */
    public static final String KEY = "memoryUser";

    private static final long serialVersionUID = -6786128717270367146L;

    private long id;
    private String username;
    private String realName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
