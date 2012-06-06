/* 
 * @(#)UserController.java    Created on 2010-7-14
 * Copyright (c) 2,010 ZDSoft Networks, Inc. All rights reserved.
 * $Id: UserController.java 95 2012-05-17 01:29:17Z wj.huang $
 */
package demo.spring.mvc.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.controller.ResponseMessage;
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
public class UserController extends BasicController {

    @Resource
    private UserValidator userValidator;

    @Resource
    private UserService userService;

    /**
     * 显示用户列表。
     * 
     * @param user
     *            用户查询信息
     * @param pagination
     *            分页对象
     * @param model
     *            Spring Model Object
     * @return view name
     */
    @RequestMapping("listUser.htm")
    public String listUser(User user, String pageNum, Model model) {
        Pagination page = new Pagination();
        page.setPageSize(10);
        page.setPageNum(NumberUtils.toInt(pageNum, 1));
        List<User> users = userService.getUsers(user, page);

        model.addAttribute("page", page);
        model.addAttribute("users", users);
        return "user/userList";
    }

    /**
     * 显示新增用户页面。
     * 
     * @return view name
     */
    @RequestMapping("newUser.htm")
    public String newUser() {
        return "user/user";
    }

    /**
     * 显示修改用户页面。
     * 
     * @param userId
     *            用户　ID
     * @param model
     *            Spring Model Object
     * @param redirectAttributes
     *            Spring RedirectAttributes Object
     * @return view name
     */
    @RequestMapping("loadUser.htm")
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
     * 添加用户操作。
     * 
     * @param user
     *            用户信息
     * @param result
     *            Spring BindingResult Object
     * @param model
     *            Spring Model Object
     * @return 响应消息
     */
    @RequestMapping(value = "addUser.htm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addUser(User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            // addActionError(result.getFieldError().getCode(), model);
            // addActionError(result.getGlobalError().getDefaultMessage(), model);
            // addActionError(result.getAllErrors().size() + "", model);
            return getResponseMessage(result);
        }

        // if (StringUtils.isEmpty(user.getUsername())) {
        // addActionError("用户名不能为空", model);
        // }
        //
        // if (hasErrors(model)) {
        // return getResponseMessage(model);
        // }

        try {
            userService.addUser(user);
            addActionMessage("新增用户成功", model);
        } catch (DataExistsException e) {
            addActionError(e.getMessage(), model);
        }

        return getResponseMessage(model);
    }

    /**
     * 修改用户操作。
     * 
     * @param user
     *            用户信息
     * @param model
     *            Spring Model Object
     * @return 响应消息
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
     * 删除用户操作。
     * 
     * @param userId
     *            用户 ID
     * @param model
     *            Spring Model Object
     * @return 响应消息
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
