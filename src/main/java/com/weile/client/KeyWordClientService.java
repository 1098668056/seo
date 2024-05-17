package com.weile.client;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.math.MathUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.weile.client.Response.KeysResp;
import com.weile.client.request.KeysRequest;
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
    @Resource
    private KeyWordsRepository keyWordsRepository;
    @Override
    public KeysResp getKeyWords(String keyWord){

        long count = keyWordsRepository.count();
        long result = count / 100L;

        HttpResponse resp = HttpUtil.createPost(url).header("Authorization", apiKey)
                .form(BeanUtil.beanToMap(new KeysRequest(keyWord, (int) result,100))).execute();
        return JSON.parseObject(resp.body(), KeysResp.class);
    }
}
