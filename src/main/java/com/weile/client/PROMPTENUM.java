package com.weile.client;


import lombok.Getter;

/**
 * @Author: xwl
 * @Date: 2024/5/21 13:26
 * @Description:
 **/
@Getter
public enum PROMPTENUM {
    /**
     * 月之暗面的描述
     */
    KIMI_DESC("大概200字，不要输出提示词"),
    /**
     * openAI描述
     */
    OPENAI_DESC("大概200字"),
    /**
     * 月之暗面的bean
     */
    KIMI_BEAN("kimi"),
    /**
     * openai的bean
     */
    OPENAI_BEAN("openai"),
    /**
     * USER-AGENT
     */
    USER_AGENT("User-Agent"),
    /**
     * x-real-ip
     */
    REAL_IP("x-real-ip"),
    /**
     * webSite
     */
    WEB_SITE_URL("http://jxjfdl.cn");

    private final String name;

    PROMPTENUM(String name) {
        this.name = name;
    }
}
