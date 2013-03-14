/*
 * @(#)BasicController.java    Created on 2012-05-31
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.guomi.meazza.spring.mvc.AbstractController;

import demo.spring.mvc.dto.MemoryUser;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

/**
 * 系统中所有 Controller 类的基类。
 * 
 * @author akuma
 */
public abstract class BasicController extends AbstractController {

    /**
     * 获取已登录用户的信息。
     */
    protected MemoryUser getMemoryUser() {
        return (MemoryUser) RequestContextHolder.getRequestAttributes().getAttribute(MemoryUser.KEY,
                RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 覆盖此方法是为了实现使用注解方式注入 <code>exceptionMappings</code>。
     */
    @Resource
    @Override
    public void setExceptionMappings(Map<String, String> exceptionMappings) {
        this.exceptionMappings = exceptionMappings;
    }

    /**
     * 添加 Freemarker 模版页面调用 Java 静态方法的支持。<br>
     * 在 freemarker 页面上这样使用：${statics["com.guomi.meazza.util.StringUtils"].getRealLength("测试")}
     */
    protected void enableStaticsForTemplate(Model model) {
        model.addAttribute("statics", BeansWrapper.getDefaultInstance().getStaticModels());
    }

    /**
     * 添加 Freemarker 模版页面调用 Java 静态方法的支持。 <br>
     * 在 freemarker 页面上这样使用：${StringUtils.getRealLength("测试")}
     */
    protected void enableStaticsForTemplate(Class<?> clazz, Model model) {
        TemplateHashModel statics = null;
        try {
            TemplateHashModel staticModels = BeansWrapper.getDefaultInstance().getStaticModels();
            statics = (TemplateHashModel) staticModels.get(clazz.getName());
        } catch (TemplateModelException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        if (statics != null) {
            model.addAttribute(clazz.getSimpleName(), statics);
        }
    }

    /**
     * 添加 Freemarker 模版页面调用 Java 枚举类的支持。<br>
     * 在 freemarker 页面上这样使用：${enums["com.guomi.clearn.student.enums.UserType"].TEACHER.getText()}
     */
    protected void enableEnumsForTemplate(Model model) {
        model.addAttribute("enums", BeansWrapper.getDefaultInstance().getEnumModels());
    }

    /**
     * 添加 Freemarker 模版页面调用 Java 枚举类的支持。<br>
     * 在 freemarker 页面上这样使用：${UserType.TEACHER.getText()}
     */
    protected void enableEnumsForTemplate(Class<?> clazz, Model model) {
        TemplateHashModel enums = null;
        try {
            TemplateHashModel enumModels = BeansWrapper.getDefaultInstance().getEnumModels();
            enums = (TemplateHashModel) enumModels.get(clazz.getName());
        } catch (TemplateModelException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        if (enums != null) {
            model.addAttribute(clazz.getSimpleName(), enums);
        }
    }

}
