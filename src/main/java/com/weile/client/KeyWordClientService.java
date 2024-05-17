package com.weile.client;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.weile.client.Response.KeysResp;
import com.weile.client.request.KeysRequest;
import io.github.qingmo.json.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    @Override
    public KeysResp getKeyWords(String keyWord){

        HttpResponse resp = HttpUtil.createPost(url).header("Authorization", apiKey)
                .form(BeanUtil.beanToMap(new KeysRequest(keyWord,1,1))).execute();
        return JSON.parseObject(resp.body(), KeysResp.class);
    }
}
