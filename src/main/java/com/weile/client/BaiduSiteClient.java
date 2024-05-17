package com.weile.client;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: xwl
 * @Date: 2024/5/16 13:23
 * @Description:
 **/
public interface BaiduSiteClient {
    public CompletableFuture<Boolean> submitUrl(String url);
}
