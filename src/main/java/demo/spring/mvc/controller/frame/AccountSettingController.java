/* 
 * @(#)AccountSettingController.java    Created on 2013-3-13
 * Copyright (c) 2013 Guomi. All rights reserved.
 */
package demo.spring.mvc.controller.frame;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guomi.meazza.spring.mvc.ResponseMessage;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.entity.User;
import demo.spring.mvc.service.UserService;

/**
 * 账户设置 Controller。
 * 
 * @author akuma
 */
@Controller
@RequestMapping("/settings")
public class AccountSettingController extends BasicController {

    @Resource
    private UserService userService;

    /**
     * 显示密码修改页面。
     */
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
    public String viewModifyPassword() {
        return "settings/password";
    }

    /**
     * 处理密码修改操作。
     */
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
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
    @RequestMapping("/heartbeat")
    @ResponseBody
    public void heartbeat() {
    }

}
