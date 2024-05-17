package com.weile.client;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: xwl
 * @Date: 2024/5/16 13:23
 * @Description:
 **/
public interface BaiduSiteClient {
    /**
     *
     * @param url 生成url链接
     * @return 异步返回是否提交成功
     */
    public CompletableFuture<Boolean> submitUrl(String url);
}
