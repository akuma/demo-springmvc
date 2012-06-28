/* 
 * @(#)AuthInterceptor.java    Created on 2012-6-7
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.guomi.meazza.util.ServletUtils;

import demo.spring.mvc.controller.frame.LoginController;
import demo.spring.mvc.dto.MemoryUser;

/**
 * 权限验证拦截器。
 * 
 * @author akuma
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(MemoryUser.KEY) != null) {
            return true;
        }

        if (ServletUtils.isAjaxRequest(request)) {
            printJsonResponse(response);
        } else {
            response.sendRedirect(ServletUtils.getWebsiteRoot(request));
        }

        // 设置用户尚未登录的标记到 Session 中，用于首页提示用户：已经超时请重新登录
        request.getSession().setAttribute(LoginController.SESSION_KEY_NOT_LOGIN, true);

        return false;
    }

    /**
     * 输出 json 格式的刷新页面的字符串到响应中。
     * 
     * @param response
     *            HTTP 响应对象
     * @throws IOException
     *             IO 操作异常时抛出
     */
    private void printJsonResponse(HttpServletResponse response) throws IOException {
        // 这里特别需要注意两点，否则 jQuery 解析响应结果时会出现异常：
        // 1. 返回的响应消息中不能带有回车，所以使用 print() 方法，而不是 println()
        // 2. 属性和值要使用双引号，而不是单引号
        response.setContentType("application/json");
        response.getWriter().print("{\"script\":\"top.location.href=location.href\"}");
        response.getWriter().flush();
    }

}
