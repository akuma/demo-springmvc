/* 
 * @(#)UserAction.java    Created on 2010-7-14
 * Copyright (c) 2,010 ZDSoft Networks, Inc. All rights reserved.
 * $Id: UserAction.java 95 2012-05-17 01:29:17Z wj.huang $
 */
package demo.spring.mvc.controller.user;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.entity.User;
import demo.spring.mvc.service.UserService;
import nova.dao.DataExistsException;

/**
 * 负责用户信息管理的 Action 类。
 * 
 * @author huangwj
 * @version $Revision: 95 $, $Date: 2012-05-17 09:29:17 +0800 (Thu, 17 May 2012) $
 */
@Component
@Scope("prototype")
public class UserAction extends BasicController {

    private User user = new User();

    @Resource
    private UserService userService;

    /**
     * 显示 User 列表.
     * 
     * @return target string
     */
    public String listUser() {
        // users = userService.getUsers(user, getPage());
        return null;
    }

    /**
     * 显示新增 User 页面 .
     * 
     * @return target string
     */
    public String newUser() {
        return null;
    }

    /**
     * 显示查看 User 页面.
     * 
     * @return target string
     */
    public String viewUser() {
        if (user.getId() == null) {
            // addActionError("请选择您要查看的用户");
            return null;
        }

        user = userService.getUser(user.getId());
        if (user == null) {
            // addActionError("您选择的用户不存在或者已经被删除");
            return null;
        }

        return null;
    }

    /**
     * 显示修改 User 页面.
     * 
     * @return target string
     */
    public String loadUser() {
        if (user.getId() == null) {
            // addActionError("请选择您要修改的用户");
            return null;
        }

        user = userService.getUser(user.getId());
        if (user == null) {
            // addActionError("您选择的用户不存在或者已经被删除");
            return null;
        }

        return null;
    }

    /**
     * 添加 User 操作.
     * 
     * @return target string
     */
    public String addUser() {
        userService.addUser(user);
        // addActionMessage("新增用户成功");

        return null;
    }

    /**
     * 修改 User 操作.
     * 
     * @return target string
     */
    public String modifyUser() {
        try {
            userService.modifyUser(user);
            // addActionMessage("修改用户成功");
        } catch (DataExistsException e) {
            // addActionError(e.getMessage());
        }

        return null;
    }

    /**
     * 删除 User 操作.
     * 
     * @return target string
     */
    public String removeUser() {
        userService.removeUsers(user.getId());
        // addActionMessage("删除用户成功");

        return null;
    }

}
