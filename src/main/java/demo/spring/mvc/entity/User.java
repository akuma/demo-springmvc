/*
 * @(#)User.java    Created on 2012-3-13
 * Copyright (c) 2005-2012 shunwang. All rights reserved.
 * $Id: User.java 2 2012-04-05 08:03:20Z wj.huang $
 */
package demo.spring.mvc.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author wj.huang
 * @version $Revision: 2 $, $Date: 2012-04-05 16:03:20 +0800 (Thu, 05 Apr 2012) $
 */
public class User implements Serializable {

    private static final long serialVersionUID = 9160198451255754582L;

    private Integer id; // 字段 id 主键
    private String username; // 字段 username
    private String password; // 字段 password
    private String realName; // 字段 real_name
    private Date modifyTime; // 字段 modify_time
    private Date creationTime; // 字段 creation_time

    /**
     * 默认的用户对象构造方法。
     */
    public User() {
    }

    /**
     * 用户对象构造方法。
     * 
     * @param id
     *            UUID
     * @param username
     *            用户名
     * @param password
     *            密码
     * @param realName
     *            真实姓名
     */
    public User(Integer id, String username, String password, String realName) {
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

    public Date getModifyTime() {
        return (modifyTime == null ? null : new Date(modifyTime.getTime()));
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = (modifyTime == null ? null : new Date(modifyTime.getTime()));
    }

    public Date getCreationTime() {
        return (creationTime == null ? null : new Date(creationTime.getTime()));
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = (creationTime == null ? null : new Date(creationTime.getTime()));
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
