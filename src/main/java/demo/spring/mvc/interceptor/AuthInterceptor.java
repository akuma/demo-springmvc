/* 
 * @(#)AuthInterceptor.java    Created on 2012-6-7
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id$
 */
package demo.spring.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import demo.spring.mvc.dto.MemoryUser;
import nova.util.ServletUtils;

/**
 * 权限验证拦截器。
 * 
 * @author wj.huang
 * @version $Revision: 1.0 $, $Date: 2012-6-7 下午2:44:26 $
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(MemoryUser.KEY) != null) {
            return true;
        }

        if (ServletUtils.isAjaxRequest(request)) {
            // 这里特别需要注意两点，否则 jQuery 解析响应结果时会出现异常：
            // 1. 返回的响应消息中不能带有回车，所以使用 print() 方法，而不是 println()
            // 2. 属性和值要使用双引号，而不是单引号
            response.setContentType("application/json");
            response.getWriter().print("{\"script\":\"top.location.href=location.href\"}");
            response.getWriter().flush();
        } else {
            response.sendRedirect(ServletUtils.getWebsiteRoot(request));
        }

        return false;
    }

}
