/*
 * @(#)HelloWorldController.java    Created on 2012-3-12
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guomi.meazza.spring.mvc.JsonViewHelper;

import demo.spring.mvc.entity.User;

/**
 * Hello World Controller。
 * 
 * @author akuma
 */
@Controller
public class HelloWorldController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
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

    @RequestMapping("/users")
    @ResponseBody
    public Object getUser() {
        Map<String, Object> results = new HashMap<>();
        List<User> users = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setUsername(RandomStringUtils.randomAlphabetic(10));
            user.setPassword("123456");
            user.setRealName(RandomStringUtils.randomAlphabetic(10));
            user.setBirthday(new Date());
            user.setModifyTime(new Date());
            user.setCreationTime(new Date());
            users.add(user);
        }
        results.put("users", users);
        return results;
    }

}
