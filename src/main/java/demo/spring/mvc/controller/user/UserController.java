/* 
 * @(#)UserController.java    Created on 2010-7-14
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.guomi.meazza.dao.DataExistsException;
import com.guomi.meazza.spring.mvc.ResponseMessage;
import com.guomi.meazza.util.Pagination;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.entity.User;
import demo.spring.mvc.service.UserService;

/**
 * 负责用户信息管理的 Controller。
 * 
 * @author akuma
 */
@Controller
@RequestMapping("/user")
public class UserController extends BasicController {

    @Resource
    private UserValidator userValidator;

    @Resource
    private UserService userService;

    /**
     * 显示用户列表。
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String listUser(User user, String pageNum, Model model) {
        Pagination page = new Pagination();
        page.setPageSize(10);
        page.setPageNum(NumberUtils.toInt(pageNum, 1));

        if (StringUtils.isBlank(user.getUsername())) {
            user.setUsername(null);
        }

        if (StringUtils.isBlank(user.getRealName())) {
            user.setRealName(null);
        }

        List<User> users = userService.getUsers(user, page);

        model.addAttribute("page", page);
        model.addAttribute("users", users);
        return "user/userList";
    }

    /**
     * 显示新增用户页面。
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String newUser() {
        return "user/user";
    }

    /**
     * 添加用户操作。
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addUser(User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return getResponseMessage(result);
        }

        try {
            userService.addUser(user);
            addActionMessage("新增用户成功", model);
        } catch (DataExistsException e) {
            addActionError(e.getMessage(), model);
        }

        return getResponseMessage(model);
    }

    /**
     * 显示修改用户页面。
     */
    @RequestMapping(value = "/modifyUser", method = RequestMethod.GET)
    public String loadUser(String userId, Model model, RedirectAttributes redirectAttributes) {
        if (userId == null) {
            addActionError("请选择您要修改的用户", redirectAttributes);
            return "redirect:userList";
        }

        User user = userService.getUser(userId);
        if (user == null) {
            addActionError("您选择的用户不存在或者已经被删除", redirectAttributes);
            return "redirect:userList";
        }

        model.addAttribute(user);

        return "user/user";
    }

    /**
     * 修改用户操作。
     */
    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage modifyUser(User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return getResponseMessage(result);
        }

        try {
            userService.modifyUser(user);
            addActionMessage("修改用户成功", model);
        } catch (DataExistsException e) {
            addActionError(e.getMessage(), model);
        }

        return getResponseMessage(model);
    }

    /**
     * 删除用户操作。
     */
    @RequestMapping(value = "/removeUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeUser(String userId, Model model) {
        if (StringUtils.isEmpty(userId)) {
            addActionError("请选择您要删除的用户", model);
            return getResponseMessage(model);
        }

        userService.removeUsers(userId);
        addActionMessage("删除用户成功", model);

        return getResponseMessage(model);
    }

}
