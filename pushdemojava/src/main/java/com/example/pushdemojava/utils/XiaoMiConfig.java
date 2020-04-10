package com.example.pushdemojava.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *  
 *  @ClassName：XiaoMiConfig
 *  @Description: TODO
 *  @Author: liuhm
 *  @Date: 2020/4/8 17:14
 */
@Configuration
@ConfigurationProperties(prefix = "xiaomi.push")
public class XiaoMiConfig {
    /**
     * appSecret
     */
    private String appSecret;

    /**
     * 包名
     */
    private String packageName;

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
