package com.weile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.Serializable;

/**
 * @Author: xwl
 * @Date: 2024/6/10 15:50
 * @Description:
 **/
@ConfigurationProperties(prefix = "web.site")
@Data
public class WebSiteInfoProperties implements Serializable {
    private String title;
    private String keywords;
    private String description;
    private String url;
    private String count;
}
