package com.weile.client;

/**
 * @Author: xwl
 * @Date: 2024/5/12 22:15
 * @Description:
 **/
public interface GptClient {
    /**
     * 通过关键词生成对应的内容
     * @param keyWords 关键词
     * @param contentLength 生成内容大小 大概多少字
     * @return
     */
    String  processGpt(String keyWords,Integer contentLength);
}
