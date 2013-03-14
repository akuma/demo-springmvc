/* 
 * @(#)DemoIndexController.java    Created on 2013-3-14
 * Copyright (c) 2013 Guomi. All rights reserved.
 */
package demo.spring.mvc.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 开发框架使用演示首页。
 * 
 * @author akuma
 */
@Controller
@RequestMapping("/demo")
public class DemoIndexController {

    @RequestMapping
    public String index() {
        return "demo/index";
    }

}
