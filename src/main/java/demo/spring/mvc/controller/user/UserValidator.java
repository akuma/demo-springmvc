/* 
 * @(#)UserValidator.java    Created on 2012-6-5
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id$
 */
package demo.spring.mvc.controller.user;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import demo.spring.mvc.entity.User;

/**
 * 用户信息验证类。
 * 
 * @author wj.huang
 * @version $Revision: 1.0 $, $Date: 2012-6-5 下午7:34:47 $
 */
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (StringUtils.isEmpty(user.getUsername())) {
            errors.reject("user.username.required", "用户名不能为空");
        }
    }

}
