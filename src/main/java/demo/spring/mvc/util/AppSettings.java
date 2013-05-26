/*
 * @(#)AppSettings.java    Created on 2010-7-15
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.util;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统配置参数封装类。
 * 
 * @author akuma
 */
@Component
public class AppSettings implements Serializable {

    private static final long serialVersionUID = -4059578153197605890L;

    @Value("#{appProperties['app.environment']}")
    private String environment;
    @Value("#{appProperties['app.assetPath']}")
    private String assetPath;

    /**
     * 是否是开发环境。
     */
    public boolean isDev() {
        return "dev".equalsIgnoreCase(environment);
    }

    /**
     * 是否是测试环境。
     */
    public boolean isTest() {
        return "test".equalsIgnoreCase(environment);
    }

    /**
     * 是否是生产环境。
     */
    public boolean isProd() {
        return "prod".equalsIgnoreCase(environment);
    }

    /**
     * 获取资源文件路径前缀。例如：http://static.foo.bar/assets
     */
    public String getAssetPath() {
        return assetPath;
    }

}
