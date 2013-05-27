/* 
 * @(#)AppSettingsTest.java    Created on 2013-5-25
 * Copyright (c) 2013 Guomi. All rights reserved.
 */
package demo.spring.mvc.util;

import javax.annotation.Resource;

import org.junit.Test;

import com.guomi.meazza.test.BasicTestCase;

/**
 * @author akuma
 */
public class AppSettingsTest extends BasicTestCase {

    @Resource
    private AppSettings appSettings;

    @Test
    public void testGetAssetPath() {
        System.out.println(appSettings.getAssetsPath());
    }

}
