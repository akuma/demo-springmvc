/* 
 * @(#)FreemarkerDemoController.java    Created on 2013-1-7
 * Copyright (c) 2013 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guomi.meazza.util.StringUtils;

import demo.spring.mvc.controller.BasicController;
import demo.spring.mvc.enums.UserType;

/**
 * 测试 Freemarker 的 Controller。
 * 
 * @author akuma
 */
@Controller
@RequestMapping("/demo/freemarker")
public class FreemarkerDemoController extends BasicController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "测试信息~");

        return "demo/freemarker/index";
    }

    @RequestMapping("/simple")
    public String simple(Model model) {
        model.addAttribute("message", "测试信息~");

        // 添加 Freemarker 模版页面调用 Java 静态方法的支持
        enableStaticsForTemplate(StringUtils.class, model); // 只开启 StringUtis 类
        enableStaticsForTemplate(model); // 开启所有类

        // 添加 Freemarker 模版页面调用 Java 枚举类的支持
        enableEnumsForTemplate(UserType.class, model); // 只开启 UserType 类
        enableEnumsForTemplate(model); // 开启所有枚举类

        return "demo/freemarker/simple";
    }

}
