package com.weile.client;

import com.weile.client.Response.KeysResp;

/**
 * @Author: xwl
 * @Date: 2024/5/17 10:42
 * @Description:
 **/
public interface KeyWordClient {
    /**
     * 通过5118提取关键词
     * @param keyWord
     * @return
     */
    public KeysResp getKeyWords(String keyWord);
}
