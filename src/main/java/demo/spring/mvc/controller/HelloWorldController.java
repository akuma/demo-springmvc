/*
 * @(#)HelloWorldController.java    Created on 2012-3-12
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guomi.meazza.spring.mvc.JsonViewHelper;

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

    @RequestMapping("/jsonTest")
    public void jsonTest(HttpServletResponse response) throws IOException {
        String json = "{\"test\":\"哈哈哈，看看是否是中文。\"}";

        // response.setContentType("application/json");
        // response.getWriter().print(json);
        // response.getWriter().flush();

        Map<String, String> model = new HashMap<>();
        model.put("test", json);
        JsonViewHelper.render(model, response);
    }

}
