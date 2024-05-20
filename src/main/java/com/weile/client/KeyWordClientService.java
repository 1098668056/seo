package com.weile.client;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.math.MathUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.weile.client.Response.KeysResp;
import com.weile.client.Response.TdkGenerateResp;
import com.weile.client.request.KeysRequest;
import com.weile.client.request.TdkGenerateRequest;
import com.weile.repository.KeyWordsRepository;
import io.github.qingmo.json.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: xwl
 * @Date: 2024/5/17 13:20
 * @Description:
 **/
@Service
public class KeyWordClientService implements KeyWordClient {
    @Value("${5118.url}")
    private String url;
    @Value("${5118.api.key}")
    private String apiKey;
    @Value("${5118.Tdk.url}")
    private String tdkUrl;
    @Value("${5118.Tdk.key}")
    private String tdkKey;
    @Resource
    private KeyWordsRepository keyWordsRepository;
    @Override
    public KeysResp getKeyWords(String keyWord,Integer type){

        long count = keyWordsRepository.countByUseCountEquals(type);
        long result = 0L;
        if (count == 0){
             result = 1L;
        }else {
            result  = (count / 100)+1;
        }
        HttpResponse resp = HttpUtil.createPost(url).header("Authorization", apiKey)
                .form(BeanUtil.beanToMap(new KeysRequest(keyWord, (int) result,100))).execute();
        return JSON.parseObject(resp.body(), KeysResp.class);
    }

    @Override
    public TdkGenerateResp generateTdk(String mainKeyWord, String subKeyWord) {
        HttpResponse resp = HttpUtil.createPost(tdkUrl).header("Authorization", tdkKey)
                .form(BeanUtil.beanToMap(new TdkGenerateRequest(mainKeyWord, subKeyWord))).execute();
        return JSON.parseObject(resp.body(), TdkGenerateResp.class);
    }
}
