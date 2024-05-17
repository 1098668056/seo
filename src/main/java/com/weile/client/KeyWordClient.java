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
     * @param keyWord 行业词汇
     * @return 返回关键词详情
     */
    public KeysResp getKeyWords(String keyWord);
}
