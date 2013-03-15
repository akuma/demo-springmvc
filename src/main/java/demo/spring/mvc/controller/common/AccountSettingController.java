/* 
 * @(#)AccountSettingController.java    Created on 2013-3-13
 * Copyright (c) 2013 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guomi.meazza.spring.mvc.ResponseMessage;
import com.guomi.meazza.util.StringUtils;

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
            addFieldError("password0", "请输入新密码", model);
        } else if (password0.length() < 6 || password0.length() > 20) {
            addFieldError("password0", "密码长度必须在 6-20 之间", model);
        }

        if (StringUtils.isEmpty(password1)) {
            addFieldError("password1", "请输入确认密码", model);
        }

        if (hasErrors(model)) {
            return getResponseMessage(model);
        }

        if (!password0.equals(password1)) {
            addActionError("两次输入的密码不一致", model);
            return getResponseMessage(model);
        }

        User user = new User();
        user.setId(getMemoryUser().getId());
        user.setPassword(password0);
        userService.modifyUserIfPossible(user);
        addActionMessage("密码修改成功", model);

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
