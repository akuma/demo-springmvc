/* 
 * @(#)AntPathMatcherTest.java    Created on 2012-7-15
 * Copyright (c) 2012 Guomi. All rights reserved.
 */
package demo.spring.mvc.misc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;

/**
 * 测试 Spring 的 {@link AntPathMatcher} 类。
 * 
 * @author akuma
 */
public class AntPathMatcherTest {

    @Test
    public void testPathMatch() {
        AntPathMatcher apm = new AntPathMatcher();
        assertTrue(apm.match("/user/*User.htm", "/user/listUser.htm"));
        assertTrue(apm.match("/user/*User*", "/user/listUser.htm"));
        assertFalse(apm.match("/user/*User", "/user/listUser.htm"));
        assertFalse(apm.match("/user/*Foo", "/user/listUser.htm"));
        assertFalse(apm.match("/foo/*User*", "/user/listUser.htm"));
        assertFalse(apm.match("/foo/*User*|/student/*Student*", "/student/listStudent.htm"));

        int n = 100000;
        long time = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            apm.match("/user/*User.htm", "/user/listUser.htm");
        }
        System.out.format("Path match %d times, elapsed %d ms.", n, (System.currentTimeMillis() - time));
    }

}
