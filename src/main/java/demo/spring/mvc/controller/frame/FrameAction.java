/*
 * @(#)FrameAction.java    Created on 2010-7-14
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id: FrameAction.java 95 2012-05-17 01:29:17Z wj.huang $
 */
package demo.spring.mvc.controller.frame;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.entity.User;
import demo.spring.mvc.service.UserService;

/**
 * 登录系统后的框架页面相关的 Action 类。
 * 
 * @author wj.huang
 * @version $Revision: 95 $, $Date: 2012-05-17 09:29:17 +0800 (Thu, 17 May 2012) $
 */
@Controller
@RequestMapping("/frame")
public class FrameAction extends BasicController {

    @Resource
    private UserService userService;

    /**
     * 显示登录后的框架首页。
     * 
     * @return view
     */
    @RequestMapping(value = "index.htm", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 显示左侧菜单列表页。
     * 
     * @return view
     */
    @RequestMapping(value = "mainMenu.htm", method = RequestMethod.GET)
    public String mainMenu() {
        return "mainMenu";
    }

    /**
     * 显示欢迎页面。
     * 
     * @return view
     */
    @RequestMapping(value = "welcome.htm", method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute(new Date());
        return "welcome";
    }

    /**
     * 显示密码修改页面。
     * 
     * @return view
     */
    @RequestMapping(value = "modifyPassword.htm", method = RequestMethod.GET)
    public String viewModifyPassword() {
        return "password";
    }

    /**
     * 处理密码修改操作。
     * 
     * @return success
     */
    @RequestMapping(value = "modifyPassword.htm", method = RequestMethod.POST)
    public String modifyPassword(String password0, String password1, Errors errors, Model model) {
        if (StringUtils.isEmpty(password0)) {
            errors.reject("请输入新密码");
        } else if (StringUtils.isEmpty(password1)) {
            errors.reject("请输入确认密码");
        } else if (!password0.equals(password1)) {
            errors.reject("密码和确认密码不一致");
        }

        if (errors.hasErrors()) {
            return null;
        }

        User user = new User();
        user.setId(getMemoryUser().getId());
        user.setPassword(password0);
        userService.modifyUserNotNull(user);
        model.addAttribute("messages", "修改密码成功");

        return null;
    }

    /**
     * 心跳方法，什么都不处理，只是为了保持用户 session 不过期。
     * 
     * @return success
     */
    @RequestMapping(value = "heartbeat.htm", method = RequestMethod.GET)
    @ResponseBody
    public void heartbeat() {
    }

}
