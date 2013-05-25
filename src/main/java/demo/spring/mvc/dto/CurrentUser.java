/*
 * @(#)CurrentUser.java    Created on 2010-7-14
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.dto;

import com.guomi.meazza.support.AbstractCurrentUser;

/**
 * 用于保存在 Session 中的用户信息类。
 * 
 * @author akuma
 */
public class CurrentUser extends AbstractCurrentUser<Long> {

    private static final long serialVersionUID = -6786128717270367146L;

    private String username;
    private String realName;

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
