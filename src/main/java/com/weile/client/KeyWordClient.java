package com.weile.client;

import com.weile.client.Response.KeysResp;
import com.weile.client.Response.TdkGenerateResp;

import java.util.List;

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
     KeysResp getKeyWords(String keyWord,Integer type);

    /**
     * 根据主词副词生成对应TDK
     * @param mainKeyWord
     * @param subKeyWord
     */
     TdkGenerateResp generateTdk(String mainKeyWord, String subKeyWord);

}
