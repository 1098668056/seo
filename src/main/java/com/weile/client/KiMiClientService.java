package com.weile.client;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.weile.client.Response.KiMiResp;
import com.weile.client.request.KMiRequest;
import com.weile.client.request.MessageCount;
import io.github.qingmo.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

/**
 * @Author: xwl
 * @Date: 2024/5/21 10:41
 * @Description:
 **/
@Service(value = "kimi")
@Slf4j
@Primary
public class KiMiClientService implements GptClient {
    @Value("${kimi.key}")
    private String key;
    @Value("${kimi.url}")
    private String url;
    /**
     * 通过关键词生成对应的内容
     *
     * @param keyWords      关键词
     * @param contentLength 生成内容大小 大概多少字
     * @return
     */
    @Override
    public String processGpt(String keyWords, Integer contentLength) {
        List<MessageCount> list = new ArrayList<>();
        MessageCount message = new MessageCount();
        message.setRole("user");
        message.setContent(keyWords+"。大概"+contentLength+"字");
        list.add(message);
        HttpResponse resp = HttpUtil.createPost(url).header("Authorization", key)
                .body(JSONUtil.toJsonStr(new KMiRequest("moonshot-v1-8k", list, 0.3, false))).execute();
        log.info("kimiResp{}",resp);
        return JSON.parseObject(resp.body(), KiMiResp.class).getChoices().get(0).getMessage().getContent();
    }
}
