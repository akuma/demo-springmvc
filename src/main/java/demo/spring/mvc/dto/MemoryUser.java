/*
 * @(#)MemoryUser.java    Created on 2010-7-14
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id: MemoryUser.java 92 2012-05-15 09:42:43Z wj.huang $
 */
package demo.spring.mvc.dto;

import java.io.Serializable;

/**
 * 用于保存在 Session 中的用户信息类。
 * 
 * @author wj.huang
 * @version $Revision: 92 $, $Date: 2012-05-15 17:42:43 +0800 (Tue, 15 May 2012) $
 */
public class MemoryUser implements Serializable {

    /**
     * MemoryUser 对象在 session 中的 key 值。
     */
    public static final String KEY = MemoryUser.class.getName();

    private static final long serialVersionUID = -6786128717270367146L;

    private Integer id;
    private String username;
    private String realName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
