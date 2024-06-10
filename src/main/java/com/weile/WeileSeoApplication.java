package com.weile;

import com.weile.config.WebSiteInfoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties(WebSiteInfoProperties.class)
public class WeileSeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeileSeoApplication.class, args);
    }

}
