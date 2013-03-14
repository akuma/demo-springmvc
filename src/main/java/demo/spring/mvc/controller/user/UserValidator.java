/* 
 * @(#)UserValidator.java    Created on 2012-6-5
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.guomi.meazza.util.StringUtils;

import demo.spring.mvc.entity.User;

/**
 * 用户信息验证类。
 * 
 * @author akuma
 */
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (StringUtils.isEmpty(user.getUsername())) {
            errors.rejectValue("username", "user.username.required", "请输入用户名");
        } else if (StringUtils.length(user.getUsername()) > 16) {
            errors.rejectValue("username", "user.username.length", "用户名长度应小于16个字符");
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            errors.rejectValue("password", "user.password.required", "请输入密码");
        } else if (StringUtils.length(user.getPassword()) < 6 || StringUtils.length(user.getPassword()) > 20) {
            errors.rejectValue("password", "user.password.lenght", "密码长度应大于6个字符，小于20个字符");
        }

        if (StringUtils.isEmpty(user.getRealName())) {
            errors.rejectValue("realName", "user.realName.required", "请输入真实姓名");
        } else if (StringUtils.length(user.getRealName()) > 64) {
            errors.rejectValue("realName", "user.realName.length", "真实姓名长度应小于64个字符");
        }

        if (user.getBirthday() == null) {
            errors.rejectValue("birthday", "user.birthday.required", "请输入格式正确的生日");
        }
    }

}
