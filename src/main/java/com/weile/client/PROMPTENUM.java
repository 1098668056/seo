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
    WEB_SITE_URL("https://yhji.nsywnwnegtbse.top:54515/shop_777/"),
    /**
     * 副词
     */
    TDK_DEPUTY("自助下单"),
    /**
     * regex
     */
    KEYWORD_REGEX("(title|meta description|meta keywords)(.*?)(meta (description|keywords):|$))"),
    KEYWORD_PREFIX_TITLE("title"),
    KEYWORD_PREFIX_META_DESCRIPTION("meta description"),
    KEYWORD_PREFIX_META_KEYWORDS("meta keywords"),
    KEYWORD_REGEX_ENTER("\\n"),
    KEYWORD_PREFIX("https://"),
    /**
     * titleDemo
     */
    TITLE_DEMO("自助下单平台");

    private final String name;

    PROMPTENUM(String name) {
        this.name = name;
    }
}
