/*
 * @(#)LoginAction.java    Created on 2010-7-14
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id: LoginAction.java 95 2012-05-17 01:29:17Z wj.huang $
 */
package demo.spring.mvc.controller.frame;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.dto.MemoryUser;
import demo.spring.mvc.entity.User;
import demo.spring.mvc.service.UserService;

/**
 * 处理用户登入、登出系统的 Action。
 * 
 * @author wj.huang
 * @version $Revision: 95 $, $Date: 2012-05-17 09:29:17 +0800 (Thu, 17 May 2012) $
 */
@Controller
@RequestMapping("/")
public class LoginAction extends BasicController {

    public static final String SESSION_KEY_LOGINED = "demo.spring.mvc.controller.frame.LoginAction.KEY";

    @Resource
    private UserService userService;

    /**
     * 显示登录页面。
     * 
     * @return view
     */
    @RequestMapping(value = "index.htm", method = RequestMethod.GET)
    public String index(HttpSession session, Model model) {
        Boolean loginState = (Boolean) session.getAttribute(SESSION_KEY_LOGINED);
        if (loginState != null && loginState.booleanValue()) {
            model.addAttribute("已经超时请重新登录");
        }

        model.addAttribute(systemInfo);

        return "index";
    }

    /**
     * 处理登录系统操作。
     * 
     * @return view
     */
    @RequestMapping(value = "login.htm", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, Errors errors, HttpSession session) {
        if (StringUtils.isEmpty(user.getUsername())) {
            errors.reject("username.required", "请输入用户名");
            return "index";
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            errors.reject("password.required", "请输入密码");
            return "index";
        }

        User _user = userService.getUserByUsername(user.getUsername());
        if (_user == null) {
            errors.reject("username.password.wrong", "用户名不存在或密码错误");
            return "index";
        }

        String passwordEncoded = user.getUsername();
        String userPasswordEncoded = DigestUtils.shaHex(_user.getPassword());
        if (!passwordEncoded.equals(userPasswordEncoded)) {
            // errors.reject("username.password.wrong", "用户名不存在或密码错误");
            // return "index";
        }

        MemoryUser memoryUser = new MemoryUser();
        memoryUser.setId(_user.getId());
        memoryUser.setUsername(_user.getUsername());
        memoryUser.setRealName(_user.getRealName());

        session.setAttribute(MemoryUser.KEY, memoryUser);

        return "redirect:/frame";
    }

    /**
     * 处理退出系统操作并重定向到登录页面。
     * 
     * @return view
     */
    @RequestMapping(value = "logout.htm", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(MemoryUser.KEY);
        session.invalidate();
        return "redirect:/";
    }

}
