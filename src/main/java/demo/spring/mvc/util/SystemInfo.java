/*
 * @(#)SystemInfo.java    Created on 2010-7-15
 * Copyright (c) 2012 Akuma. All rights reserved.
 */
package demo.spring.mvc.util;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统信息类。
 * 
 * @author akuma
 */
@Component
public class SystemInfo implements Serializable {

    private static final long serialVersionUID = -4059578153197605890L;

    private static final Logger logger = LoggerFactory.getLogger(SystemInfo.class);

    @Value("#{serverProperties['system.id']}")
    private String id;
    @Value("#{serverProperties['system.name']}")
    private String name;
    @Value("#{serverProperties['system.copyright']}")
    private String copyright;

    private String version;
    private String qualifier;
    private String timestamp;
    private String revision;
    private String fullVersion;
    private String serverMarker;

    public SystemInfo() {
    }

    /**
     * 初始化服务器标记。
     */
    @PostConstruct
    public void initServerMarker() {
        String hostname = null;
        String hostAddr = null;
        try {
            InetAddress address = InetAddress.getLocalHost();
            hostname = address.getHostName();
            hostAddr = address.getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("Get localhost info error", e);
        }

        hostname = StringUtils.defaultString(hostname);
        hostAddr = StringUtils.defaultString(hostAddr);
        if (hostAddr.length() > 0) {
            // 获取服务器本地 IP 地址的后两位，例：192.168.0.222 -> 0.222
            hostAddr = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.").matcher(hostAddr).replaceAll("");
        }

        fullVersion = StringUtils.join(new String[] { version, qualifier, timestamp, revision }, "-");
        serverMarker = StringUtils.join(new String[] { name, fullVersion }, "-") + "@" + hostname + "_" + hostAddr;
    }

    /**
     * 获取系统的 ID 标识。
     * 
     * @return 系统 ID 标识
     */
    public String getId() {
        return id;
    }

    /**
     * 获取系统名称。
     * 
     * @return 系统名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取系统版权信息。
     * 
     * @return 系统版权信息
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * 设置程序版本号。
     * 
     * @param version
     *            程序版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 设置程序状态。
     * 
     * @param qualifier
     *            程序状态，如：snapshot、release 等
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * 设置程序构建的时间戳。
     * 
     * @param timestamp
     *            程序构建的时间戳
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 设置程序的 SVN 版本号。
     * 
     * @param revision
     *            SVN 版本号
     */
    public void setRevision(String revision) {
        this.revision = revision;
    }

    /**
     * 获取系统完整的版本号，格式为：程序版本号-限定符-构建日期-SVN 版本号。<br>
     * 返回结果示例：1.0.0.0-snapshot-20110309-r13582
     * 
     * @return 系统完整的版本号
     */
    public String getFullVersion() {
        return fullVersion;
    }

    /**
     * 获取系统服务器标记，用以区分在集群环境下时应用属于哪台服务器。<br>
     * 返回结果示例：wincms-1.0.0.0-snapshot-20110309-r13582@app1_0.2
     * 
     * @return 系统服务器标记
     */
    public String getServerMarker() {
        return serverMarker;
    }

}
