/*
 * @(#)HelloWorldController.java    Created on 2012-3-12
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id: HelloWorldController.java 92 2012-05-15 09:42:43Z wj.huang $
 */
package demo.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello World 控制器。
 * 
 * @author wj.huang
 * @version $Revision: 1.0 $, $Date: 2012-5-31 下午5:33:37 $
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
