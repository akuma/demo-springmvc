/* 
 * @(#)UserValidator.java    Created on 2012-6-5
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.controller.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

        if (StringUtils.isBlank(user.getUsername())) {
            errors.reject("user.username.required", "用户名不能为空");
        } else if (StringUtils.length(user.getUsername()) > 16) {
            errors.reject("user.username.length", "用户名长度应小于16个字符");
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            errors.reject("user.password.required", "密码不能为空");
        } else if (StringUtils.length(user.getPassword()) < 6 || StringUtils.length(user.getPassword()) > 20) {
            errors.reject("user.password.length", "密码长度应大于6个字符，小于20个字符");
        }

        if (StringUtils.isEmpty(user.getRealName())) {
            errors.reject("user.realname.required", "真实姓名不能为空");
        } else if (StringUtils.length(user.getRealName()) > 64) {
            errors.reject("user.realname.length", "真实姓名长度应小于64个字符");
        }

        if (user.getBirthday() == null) {
            errors.reject("user.birthday.required", "请输入符合格式的生日");
        }
    }

}
