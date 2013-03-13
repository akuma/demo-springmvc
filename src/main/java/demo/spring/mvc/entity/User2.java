/*
 * @(#)User.java    Created on 2012-3-13
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户信息实体类。
 * 
 * @author akuma
 */
public class User2 implements Serializable {

    private static final long serialVersionUID = 9160198451255754582L;

    private Integer id; // 字段 id 主键
    private String username; // 字段 username
    private String password; // 字段 password
    private String realName; // 字段 real_name
    private Date birthday; // 字段 birthday
    private Date modifyTime; // 字段 modify_time
    private Date creationTime; // 字段 creation_time

    public User2() {
    }

    public User2(Integer id, String username, String password, String realName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
    }

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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
