/* 
 * @(#)CommonModelAttributeInterceptor.java    Created on 2012-6-6
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id$
 */
package demo.spring.mvc.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import nova.util.ServletUtils;

/**
 * @author wj.huang
 * @version $Revision: 1.0 $, $Date: 2012-6-6 下午4:17:16 $
 */
public class CommonModelAttributeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CommonModelAttributeInterceptor.class);

    private Map<String, Object> commonAttributes;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (!modelAndView.hasView()) {
            return;
        }

        String viewName = modelAndView.getViewName();
        boolean isRedirectView = (modelAndView.getView() instanceof RedirectView)
                || (viewName != null && viewName.startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX));

        logger.debug("{} {} a redirect uri.", request.getRequestURI(), isRedirectView ? "is" : "is not");

        // boolean isViewObject = modelAndView.getView() != null;
        // boolean viewNameStartsWithRedirect = viewName != null
        // && viewName.startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX);
        // logger.debug("isRedirectView: {}, isViewObject: {}, viewNameStartsWithRedirect: {}", new Object[] {
        // isRedirectView, isViewObject, viewNameStartsWithRedirect });

        // if ((isViewObject && !isRedirectView) || (!isViewObject && !viewNameStartsWithRedirect)) {
        if (!isRedirectView) {
            addCommonModelData(request, modelAndView);
        }
    }

    /**
     * 添加共同数据到请求对象中。
     * 
     * @param request
     *            http 请求对象
     * @param modelAndView
     *            Spring ModelAndView
     */
    public void addCommonModelData(HttpServletRequest request, ModelAndView modelAndView) {
        // 添加用户真实地址
        modelAndView.addObject("realRemoteAddr", getRealRemoteAddr(request));

        // 添加通用属性
        if (commonAttributes != null) {
            modelAndView.addAllObjects(commonAttributes);
        }
    }

    /**
     * 设置通用属性。这些属性会被设置到 <code>Request</code> 对象中，用于页面展现。
     * 
     * @param commonAttributes
     *            需要设置的通用属性 Map，key 是属性的名称，value 是属性值
     */
    public void setCommonAttributes(Map<String, Object> commonAttributes) {
        this.commonAttributes = commonAttributes;
    }

    /**
     * 获取用户真实的 IP 地址。
     * 
     * @param request
     *            http 请求对象
     * @return 用户真实的 IP 地址
     */
    private String getRealRemoteAddr(HttpServletRequest request) {
        return ServletUtils.getRealRemoteAddr(request);
    }

}
