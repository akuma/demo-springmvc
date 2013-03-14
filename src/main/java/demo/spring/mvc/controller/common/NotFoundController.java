/*
 * @(#)NotFoundController.java    Created on 2013-2-1
 * Copyright (c) 2013 Guomi. All rights reserved.
 */
package demo.spring.mvc.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 404 页面 Controller。
 * 
 * @author akuma
 */
@Controller
public class NotFoundController {

    @RequestMapping("/404")
    public String notFound() {
        return "common/404";
    }

}
