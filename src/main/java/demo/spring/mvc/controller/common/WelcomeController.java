/*
 * @(#)WelcomeController.java    Created on 2010-7-14
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller.common;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.service.UserService;

/**
 * 登录系统后的框架页面相关的 Controller。
 * 
 * @author akuma
 */
@Controller
@RequestMapping("/")
public class WelcomeController extends BasicController {

    @Resource
    private UserService userService;

    /**
     * 显示欢迎页面。
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute("timeNow", new Date());
        return "welcome";
    }

}
