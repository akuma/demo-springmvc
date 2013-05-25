/*
 * @(#)LoginController.java    Created on 2010-7-14
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller.common;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.guomi.meazza.util.StringUtils;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.dto.CurrentUser;
import demo.spring.mvc.entity.User;
import demo.spring.mvc.service.UserService;

/**
 * 处理用户登入、登出系统的 Controller。
 * 
 * @author akuma
 */
@Controller
@RequestMapping("/")
public class LoginController extends BasicController {

    public static final String SESSION_KEY_NOT_LOGIN = "LoginController.NOT_LOGIN";

    @Resource
    private UserService userService;

    /**
     * 显示登录页面。
     */
    @RequestMapping({ "/", "/index" })
    public String index(WebRequest request, Model model) {
        if (getCurrentUser() != null) {
            return "redirect:/welcome";
        }

        // 获取用户尚未登录的标记，如果存在，则给出提示信息并清除该标记
        Boolean loginState = (Boolean) request.getAttribute(SESSION_KEY_NOT_LOGIN, RequestAttributes.SCOPE_SESSION);
        if (loginState != null && loginState.booleanValue()) {
            addActionError("已经超时请重新登录", model);
            request.removeAttribute(SESSION_KEY_NOT_LOGIN, RequestAttributes.SCOPE_SESSION);
        }

        return "index";
    }

    /**
     * 处理登录系统操作。
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, WebRequest request, Model model) {
        if (StringUtils.isBlank(user.getUsername())) {
            addActionError("请输入用户名", model);
            return "index";
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            model.addAttribute("username", user.getUsername());
            addActionError("请输入密码", model);
            return "index";
        }

        User dbUser = userService.getUserByUsername(user.getUsername());
        if (dbUser == null) {
            model.addAttribute("username", user.getUsername());
            addActionError("用户名不存在或密码错误", model);
            return "index";
        }

        String passwordEncoded = user.getPassword();
        String userPasswordEncoded = DigestUtils.sha1Hex(dbUser.getPassword());
        if (!passwordEncoded.equals(userPasswordEncoded)) {
            model.addAttribute("username", user.getUsername());
            addActionError("用户名不存在或密码错误", model);
            return "index";
        }

        CurrentUser currentUser = new CurrentUser();
        currentUser.setId(dbUser.getId());
        currentUser.setUsername(dbUser.getUsername());
        currentUser.setRealName(dbUser.getRealName());
        currentUser.saveSession(request);

        return "redirect:welcome";
    }

    /**
     * 处理退出系统操作并重定向到登录页面。
     */
    @RequestMapping("/logout")
    public String logout(ServletWebRequest request) {
        getCurrentUser().destorySession(request);
        return "redirect:/";
    }

}
