/* 
 * @(#)User.java    Created on 2013-03-13
 * Copyright (c) 2013 Akuma. All rights reserved.
 */
package demo.spring.mvc.entity;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.guomi.meazza.support.LongIdEntity;

/**
 * 表 demo_user 对应的实体类。
 * 
 * @author gmcg (Guomi Code Generator)
 */
public class User extends LongIdEntity {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String realName;
    private Date birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
