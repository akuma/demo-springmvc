/* 
 * @(#)UserType.java    Created on 2013-1-6
 * Copyright (c) 2013 Akuma. All rights reserved.
 */
package demo.spring.mvc.enums;

/**
 * 用户类型枚举类。
 * 
 * @author akuma
 */
public enum UserType {

    UNKNOWN(0), STUDENT(1), TEACHER(2), PARENT(3);

    int type;

    UserType(int type) {
        this.type = type;
    }

    public static UserType valueOf(int type) {
        switch (type) {
        case 2:
            return TEACHER;
        case 1:
            return STUDENT;
        case 3:
            return PARENT;
        default:
            return UNKNOWN;
        }
    }

    public String getText() {
        switch (this) {
        case TEACHER:
            return "教师";
        case STUDENT:
            return "学生";
        case PARENT:
            return "家长";
        default:
            return "未知";
        }
    }

    public int getValue() {
        return type;
    }

    public String getStringValue() {
        return String.valueOf(type);
    }

}
