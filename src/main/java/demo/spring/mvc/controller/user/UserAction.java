/* 
 * @(#)UserAction.java    Created on 2010-7-14
 * Copyright (c) 2,010 ZDSoft Networks, Inc. All rights reserved.
 * $Id: UserAction.java 95 2012-05-17 01:29:17Z wj.huang $
 */
package demo.spring.mvc.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.entity.User;
import demo.spring.mvc.service.UserService;
import nova.dao.DataExistsException;
import nova.util.Pagination;

/**
 * 负责用户信息管理的 Action 类。
 * 
 * @author huangwj
 * @version $Revision: 95 $, $Date: 2012-05-17 09:29:17 +0800 (Thu, 17 May 2012) $
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BasicController {

    @Resource
    private UserService userService;

    /**
     * 显示 User 列表.
     * 
     * @return view name
     */
    @RequestMapping(value = "listUser.htm")
    public String listUser(@ModelAttribute User user, @ModelAttribute("page") Pagination pagination, Model model) {
        pagination.setPageSize(10);
        List<User> users = userService.getUsers(user, pagination);
        model.addAttribute("users", users);
        return "user/userList";
    }

    /**
     * 显示新增 User 页面 .
     * 
     * @return view name
     */
    @RequestMapping(value = "newUser.htm")
    public String newUser(@ModelAttribute User user) {
        return "user/user";
    }

    /**
     * 显示修改 User 页面.
     * 
     * @return view name
     */
    @RequestMapping(value = "loadUser.htm")
    public String loadUser(String userId, Model model, RedirectAttributes redirectAttributes) {
        if (userId == null) {
            addActionError("请选择您要修改的用户", redirectAttributes);
            return "redirect:listUser.htm";
        }

        User user = userService.getUser(userId);
        if (user == null) {
            addActionError("您选择的用户不存在或者已经被删除", redirectAttributes);
            return "redirect:listUser.htm";
        }

        model.addAttribute(user);

        return "user/user";
    }

    /**
     * 添加 User 操作.
     * 
     * @return view name
     */
    @RequestMapping(value = "addUser.htm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addUser(User user, Model model) {
        if (StringUtils.isEmpty(user.getUsername())) {
            addActionError("用户名不能为空", model);
        }

        if (hasErrors(model)) {
            return getResponseMessage(model);
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
     * 修改 User 操作.
     * 
     * @return view name
     */
    @RequestMapping(value = "modifyUser.htm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage modifyUser(User user, Model model) {
        if (StringUtils.isEmpty(user.getUsername())) {
            addActionError("用户名不能为空", model);
        }

        if (hasErrors(model)) {
            return getResponseMessage(model);
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
     * 删除 User 操作.
     * 
     * @return view name
     */
    @RequestMapping(value = "removeUser.htm", method = RequestMethod.POST)
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
