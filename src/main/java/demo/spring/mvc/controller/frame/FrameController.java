/*
 * @(#)FrameController.java    Created on 2010-7-14
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id: FrameController.java 95 2012-05-17 01:29:17Z wj.huang $
 */
package demo.spring.mvc.controller.frame;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.controller.ResponseMessage;
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
public class FrameController extends BasicController {

    @Resource
    private UserService userService;

    /**
     * 显示登录后的框架首页。
     * 
     * @return view name
     */
    @RequestMapping({ "/", "index.htm" })
    public String index() {
        return "frame/index";
    }

    /**
     * 显示左侧菜单列表页。
     * 
     * @return view name
     */
    @RequestMapping("mainMenu.htm")
    public String mainMenu() {
        return "frame/mainMenu";
    }

    /**
     * 显示欢迎页面。
     * 
     * @param model
     *            Spring Model Object
     * @return view name
     */
    @RequestMapping("welcome.htm")
    public String welcome(Model model) {
        model.addAttribute("timeNow", new Date());
        return "frame/welcome";
    }

    /**
     * 显示密码修改页面。
     * 
     * @return view name
     */
    @RequestMapping("modifyPassword.htm")
    public String viewModifyPassword() {
        return "frame/password";
    }

    /**
     * 处理密码修改操作。
     * 
     * @param password0
     *            新密码
     * @param password1
     *            确认密码
     * @param model
     *            Spring Model Object
     * @return 响应消息
     */
    @RequestMapping(value = "modifyPassword.htm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage modifyPassword(String password0, String password1, Model model) {
        if (StringUtils.isEmpty(password0)) {
            addActionError("请输入新密码", model);
        } else if (StringUtils.isEmpty(password1)) {
            addActionError("请输入确认密码", model);
        } else if (!password0.equals(password1)) {
            addActionError("密码和确认密码不一致", model);
        }

        if (hasErrors(model)) {
            return getResponseMessage(model);
        }

        User user = new User();
        user.setId(getMemoryUser().getId());
        user.setPassword(password0);
        userService.modifyUserNotNull(user);
        addActionMessage("修改密码成功", model);

        return getResponseMessage(model);
    }

    /**
     * 心跳方法，什么都不处理，只是为了保持用户 session 不过期。
     */
    @RequestMapping("heartbeat.htm")
    @ResponseBody
    public void heartbeat() {
    }

}
