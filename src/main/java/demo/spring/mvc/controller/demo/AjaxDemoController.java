/* 
 * @(#)AjaxDemoController.java    Created on 2013-1-14
 * Copyright (c) 2013 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guomi.meazza.spring.mvc.ResponseMessage;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.entity.User;

/**
 * 演示 ajax 操作的 Controller。
 * 
 * @author akuma
 */
@Controller
@RequestMapping("/demo/ajax")
public class AjaxDemoController extends BasicController {

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "demo/ajax/index";
    }

    @RequestMapping("/sayHello")
    @ResponseBody
    public ResponseMessage sayHello(Model model) {
        ResponseMessage message = getResponseMessage(model);
        message.addAttribute("name", "二哥");
        return message;
    }

    @RequestMapping("/getUsers")
    @ResponseBody
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        User u1 = new User();
        u1.setId(10L);
        u1.setUsername("foo");
        u1.setPassword("123456");
        u1.setBirthday(new Date());

        User u2 = new User();
        u2.setId(20L);
        u2.setUsername("bar");
        u2.setPassword("123456");
        u2.setBirthday(new Date());

        users.add(u1);
        users.add(u2);

        return users;
    }

    @RequestMapping("/showPhoto")
    public String showPhoto(Model model) {
        try {
            // 为了可以看到 ajax loading div 的显示效果故意添加等待时间
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }

        addActionMessage("图片显示成功", model);
        return "demo/ajax/photo";
    }

    @RequestMapping("/testFormSubmit")
    @ResponseBody
    public ResponseMessage testFormSubmit(User user, Model model) throws IOException {
        if (StringUtils.isBlank(user.getUsername())) {
            addFieldError("username", "请输入登录名（采用浮动位置的方式提示）", model);
        }

        if (StringUtils.isBlank(user.getPassword())) {
            addFieldError("password", "请输入密码（采用固定位置的方式提示，不换行显示）", model);
        }

        if (StringUtils.isBlank(user.getRealName())) {
            addFieldError("realName", "请输入真实姓名（采用固定位置的方式提示，换行显示）", model);
        }

        if (hasErrors(model)) {
            return getResponseMessage(model);
        }

        addActionMessage("参数提交成功", model);

        return getResponseMessage(model);
    }

}
