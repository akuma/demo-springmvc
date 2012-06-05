/*
 * @(#)LoginController.java    Created on 2010-7-14
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id: LoginController.java 95 2012-05-17 01:29:17Z wj.huang $
 */
package demo.spring.mvc.controller.frame;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class LoginController extends BasicController {

    public static final String SESSION_KEY_LOGINED = "demo.spring.mvc.controller.frame.LoginController.KEY";

    @Resource
    private UserService userService;

    /**
     * 显示登录页面。
     * 
     * @return view name
     */
    @RequestMapping(value = "index.htm", method = RequestMethod.GET)
    public String index(HttpSession session, Model model) {
        Boolean loginState = (Boolean) session.getAttribute(SESSION_KEY_LOGINED);
        if (loginState != null && loginState.booleanValue()) {
            addActionError("已经超时请重新登录", model);
        }

        model.addAttribute(systemInfo);

        return "index";
    }

    /**
     * 处理登录系统操作。
     * 
     * @return view name
     */
    @RequestMapping(value = "login.htm", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        if (StringUtils.isEmpty(user.getUsername())) {
            addActionError("请输入用户名", model);
            return "index";
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            addActionError("请输入密码", model);
            return "index";
        }

        User dbUser = userService.getUserByUsername(user.getUsername());
        if (dbUser == null) {
            addActionError("用户名不存在或密码错误", model);
            return "index";
        }

        String passwordEncoded = user.getPassword();
        String userPasswordEncoded = DigestUtils.shaHex(dbUser.getPassword());
        if (!passwordEncoded.equals(userPasswordEncoded)) {
            addActionError("用户名不存在或密码错误", model);
            return "index";
        }

        MemoryUser memoryUser = new MemoryUser();
        memoryUser.setId(dbUser.getId());
        memoryUser.setUsername(dbUser.getUsername());
        memoryUser.setRealName(dbUser.getRealName());
        session.setAttribute(MemoryUser.KEY, memoryUser);

        return "redirect:/frame";
    }

    /**
     * 处理退出系统操作并重定向到登录页面。
     * 
     * @return view name
     */
    @RequestMapping(value = "logout.htm", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(MemoryUser.KEY);
        session.invalidate();
        return "redirect:/";
    }

}
