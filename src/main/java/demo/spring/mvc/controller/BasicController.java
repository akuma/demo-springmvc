/*
 * @(#)BasicController.java    Created on 2012-05-31
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.guomi.meazza.spring.mvc.AbstractController;

import demo.spring.mvc.dto.MemoryUser;

/**
 * 系统中所有 Controller 类的基类。
 * 
 * @author akuma
 */
public abstract class BasicController extends AbstractController {

    /**
     * 获取已登录用户的信息。
     * 
     * @return 已登录用户信息
     */
    protected MemoryUser getMemoryUser() {
        return (MemoryUser) RequestContextHolder.getRequestAttributes().getAttribute(MemoryUser.KEY,
                RequestAttributes.SCOPE_SESSION);
    }

}
