/*
 * @(#)BasicController.java    Created on 2012-05-31
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id: BasicController.java 5 2012-04-06 05:24:48Z wj.huang $
 */
package demo.spring.mvc.controller;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.guomi.meazza.spring.mvc.AbstractController;

import demo.spring.mvc.dto.MemoryUser;

/**
 * 系统中所有 Controller 类的基类。
 * 
 * @author wj.huang
 * @version $Revision: 5 $, $Date: 2012-04-06 13:24:48 +0800 (Fri, 06 Apr 2012) $
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
