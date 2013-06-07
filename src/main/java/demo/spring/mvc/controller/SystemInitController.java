/* 
 * @(#)SystemInitController.java    Created on 2013-6-7
 * Copyright (c) 2013 Guomi. All rights reserved.
 */
package demo.spring.mvc.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.guomi.meazza.spring.mvc.FreeMarkerConfigController;

import demo.spring.mvc.util.AppInfo;
import demo.spring.mvc.util.AppSettings;
import freemarker.template.TemplateModelException;

/**
 * 对系统进行一些初始化处理的 Controller。
 * 
 * @author akuma
 */
@Controller
public class SystemInitController {

    @Resource
    private FreeMarkerConfigController freeMarkerConfigController;

    @Resource
    private AppInfo appInfo;
    @Resource
    private AppSettings appSettings;

    @PostConstruct
    public void init() throws TemplateModelException {
        // 添加 freemarker 页面需要用到的公共变量
        freeMarkerConfigController.addVariable("appInfo", appInfo);
        freeMarkerConfigController.addVariable("appSettings", appSettings);
    }

}
