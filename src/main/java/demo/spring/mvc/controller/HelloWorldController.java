/*
 * @(#)HelloWorldController.java    Created on 2012-3-12
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello World 控制器。
 * 
 * @author akuma
 */
@Controller
public class HelloWorldController {

    /**
     * Say hello world.
     * 
     * @return success
     */
    @RequestMapping(value = "/hello.htm", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "Hello, Spring MVC!";
    }

}
