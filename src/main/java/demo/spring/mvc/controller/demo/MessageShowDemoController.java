/* 
 * @(#)MessageShowDemoController.java    Created on 2013-1-15
 * Copyright (c) 2013 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller.demo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guomi.meazza.spring.mvc.ResponseMessage;

import demo.spring.mvc.controller.BasicController;

/**
 * 演示系统返回的提示信息的 Controller。
 * 
 * @author akuma
 */
@Controller
@RequestMapping("/demo/message")
public class MessageShowDemoController extends BasicController {

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "demo/message/index";
    }

    @RequestMapping("/showMessages")
    public String showMessages(Model model) {
        addActionError("这是一个 Action 出错信息，允许多个提示", model);
        addActionError("这是第二个 Action 出错信息", model);

        addActionMessage("这是一个 Action 成功提示，允许多个提示", model);
        addActionMessage("这是第二个 Action 成功提示", model);
        return "demo/message/messages";
    }

    @RequestMapping("/showActionError")
    @ResponseBody
    public ResponseMessage showActionError(Model model) throws IOException {
        addActionError("这是一个 Action 出错信息，允许多个提示", model);
        addActionError("这是第二个 Action 出错信息", model);
        return getResponseMessage(model);
    }

    @RequestMapping("/showActionMessage")
    @ResponseBody
    public ResponseMessage showActionMessage(Model model) throws IOException {
        addActionMessage("这是一个 Action 成功提示，允许多个提示", model);
        addActionMessage("这是第二个 Action 成功提示", model);
        return getResponseMessage(model);
    }

    @RequestMapping("/showServerException")
    public String showServerException() throws IOException {
        throw new RuntimeException("这是一个服务端异常信息");
    }

    @RequestMapping("/showServerError")
    public String showServerError(HttpServletResponse response) throws IOException {
        response.sendError(500, "这是一个服务器内部出错信息");
        return null;
    }

}
