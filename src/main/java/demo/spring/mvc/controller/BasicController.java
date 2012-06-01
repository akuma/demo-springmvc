/*
 * @(#)BasicController.java    Created on 2012-05-31
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id: BasicController.java 5 2012-04-06 05:24:48Z wj.huang $
 */
package demo.spring.mvc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import demo.spring.mvc.dto.MemoryUser;
import demo.spring.mvc.util.SystemInfo;
import nova.util.Pagination;
import nova.util.ServletUtils;

/**
 * 系统中所有 Controller 类的基类。
 * 
 * @author wj.huang
 * @version $Revision: 5 $, $Date: 2012-04-06 13:24:48 +0800 (Fri, 06 Apr 2012) $
 */
public abstract class BasicController {

    protected Logger logger = LoggerFactory.getLogger(getClass()); // 日志对象

    @Resource
    protected HttpServletRequest httpRequest; // request 对象
    @Resource
    protected HttpSession httpSession; // session 对象

    @Resource
    protected SystemInfo systemInfo;

    private Pagination page = new Pagination();

    public BasicController() {
    }

    protected void addMessage(String messageCode, Model model) {
        model.asMap().get("messages");
    }

    protected void addMessage(String messageCode, String defaultMesssage, Model model) {
    }

    /**
     * 获取已登录用户的信息。
     * 
     * @return 已登录用户信息
     */
    public MemoryUser getMemoryUser() {
        return (MemoryUser) httpSession.getAttribute(MemoryUser.KEY);
    }

    /**
     * 获取用户的 IP 地址。
     * 
     * @return 用户 IP 地址
     */
    public String getRealRemoteAddr() {
        return ServletUtils.getRealRemoteAddr(httpRequest);
    }

    /**
     * 获取当前的分页对象，包含了当前访问页面的页号、页大小等信息。
     * 
     * @return 当前分页对象
     */
    public Pagination getPage() {
        return page;
    }

    /**
     * 设置页号信息。
     * 
     * @param pageNum
     *            页号
     */
    public void setPageNum(int pageNum) {
        page.setPageNum(pageNum);
    }

    /**
     * 获取当前页号。
     * 
     * @return 当前页号
     */
    public int getPageNum() {
        return page.getPageNum();
    }

}
