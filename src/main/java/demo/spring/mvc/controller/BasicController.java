/*
 * @(#)BasicController.java    Created on 2012-05-31
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id: BasicController.java 5 2012-04-06 05:24:48Z wj.huang $
 */
package demo.spring.mvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import demo.spring.mvc.dto.MemoryUser;
import demo.spring.mvc.util.SystemInfo;
import nova.util.ServletUtils;

/**
 * 系统中所有 Controller 类的基类。
 * 
 * @author wj.huang
 * @version $Revision: 5 $, $Date: 2012-04-06 13:24:48 +0800 (Fri, 06 Apr 2012) $
 */
public abstract class BasicController implements ValidationSupport {

    protected Logger logger = LoggerFactory.getLogger(getClass()); // 日志对象

    private static final String ACTION_ERRORS = "actionErrors";
    private static final String ACTION_MESSAGES = "actionMessages";
    private static final String FIELD_ERRORS = "fieldErrors";

    @Resource
    protected SystemInfo systemInfo;

    public BasicController() {
    }

    /**
     * 获取已登录用户的信息。
     * 
     * @return 已登录用户信息
     */
    public MemoryUser getMemoryUser() {
        return (MemoryUser) RequestContextHolder.getRequestAttributes().getAttribute(MemoryUser.KEY,
                RequestAttributes.SCOPE_SESSION);
    }

    /**
     * 获取用户的 IP 地址。
     * 
     * @return 用户 IP 地址
     */
    public String getRealRemoteAddr(HttpServletRequest httpRequest) {
        return ServletUtils.getRealRemoteAddr(httpRequest);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Override
    public Collection<String> getActionMessages(Model model) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) model.asMap().get(ACTION_MESSAGES);
        if (CollectionUtils.isEmpty(messages)) {
            messages = new ArrayList<String>(0);
        }

        return Collections.unmodifiableCollection(messages);
    }

    @Override
    public Collection<String> getActionErrors(Model model) {
        @SuppressWarnings("unchecked")
        List<String> errors = (List<String>) model.asMap().get(ACTION_ERRORS);
        if (CollectionUtils.isEmpty(errors)) {
            errors = new ArrayList<String>(0);
        }

        return Collections.unmodifiableCollection(errors);
    }

    @Override
    public Map<String, Collection<String>> getFieldErrors(Model model) {
        @SuppressWarnings("unchecked")
        Map<String, Collection<String>> errorsMap = (Map<String, Collection<String>>) model.asMap().get(FIELD_ERRORS);
        if (CollectionUtils.isEmpty(errorsMap)) {
            errorsMap = new LinkedHashMap<String, Collection<String>>(0);
        }

        return Collections.unmodifiableMap(errorsMap);
    }

    @Override
    public void addActionMessage(String aMessage, Model model) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) model.asMap().get(ACTION_MESSAGES);
        if (CollectionUtils.isEmpty(messages)) {
            messages = new ArrayList<String>();
            model.addAttribute(ACTION_MESSAGES, messages);
        }

        messages.add(aMessage);
    }

    public void addActionMessage(String aMessage, RedirectAttributes model) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) model.asMap().get(ACTION_MESSAGES);
        if (CollectionUtils.isEmpty(messages)) {
            messages = new ArrayList<String>();
            model.addFlashAttribute(ACTION_MESSAGES, messages);
        }

        messages.add(aMessage);
    }

    @Override
    public void addActionError(String anErrorMessage, Model model) {
        @SuppressWarnings("unchecked")
        List<String> errors = (List<String>) model.asMap().get(ACTION_ERRORS);
        if (CollectionUtils.isEmpty(errors)) {
            errors = new ArrayList<String>();
            model.addAttribute(ACTION_ERRORS, errors);
        }

        errors.add(anErrorMessage);
    }

    public void addActionError(String anErrorMessage, RedirectAttributes model) {
        @SuppressWarnings("unchecked")
        List<String> errors = (List<String>) model.asMap().get(ACTION_ERRORS);
        if (CollectionUtils.isEmpty(errors)) {
            errors = new ArrayList<String>();
            model.addFlashAttribute(ACTION_ERRORS, errors);
        }

        errors.add(anErrorMessage);
    }

    @Override
    public void addFieldError(String fieldName, String errorMessage, Model model) {
        @SuppressWarnings("unchecked")
        Map<String, List<String>> errorsMap = (Map<String, List<String>>) model.asMap().get(FIELD_ERRORS);
        if (CollectionUtils.isEmpty(errorsMap)) {
            errorsMap = new LinkedHashMap<String, List<String>>();
            model.addAttribute(FIELD_ERRORS, errorsMap);
        }

        List<String> errors = errorsMap.get(fieldName);
        if (CollectionUtils.isEmpty(errors)) {
            errors = new ArrayList<String>();
            errorsMap.put(fieldName, errors);
        }

        errors.add(errorMessage);
    }

    @Override
    public boolean hasActionMessages(Model model) {
        return !getActionMessages(model).isEmpty();
    }

    @Override
    public boolean hasActionErrors(Model model) {
        return !getActionErrors(model).isEmpty();
    }

    @Override
    public boolean hasFieldErrors(Model model) {
        return !getFieldErrors(model).isEmpty();
    }

    @Override
    public boolean hasErrors(Model model) {
        return hasActionErrors(model) || hasFieldErrors(model);
    }

    public ResponseMessage getResponseMessage(Model model) {
        ResponseMessage message = new ResponseMessage();
        message.actionMessages = getActionMessages(model);
        message.actionErrors = getActionErrors(model);
        message.fieldErrors = getFieldErrors(model);
        return message;
    }

    public static class ResponseMessage {

        private Collection<String> actionMessages;
        private Collection<String> actionErrors;
        private Map<String, Collection<String>> fieldErrors;

        public Collection<String> getActionErrors() {
            return actionErrors;
        }

        public void setActionErrors(Collection<String> actionErrors) {
            this.actionErrors = actionErrors;
        }

        public Collection<String> getActionMessages() {
            return actionMessages;
        }

        public void setActionMessages(Collection<String> actionMessages) {
            this.actionMessages = actionMessages;
        }

        public Map<String, Collection<String>> getFieldErrors() {
            return fieldErrors;
        }

        public void setFieldErrors(Map<String, Collection<String>> fieldErrors) {
            this.fieldErrors = fieldErrors;
        }

    }

}
