package com.weile.client;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.weile.client.Response.GptResp;
import com.weile.client.request.ContentRequest;
import com.weile.client.request.GptParamRequest;
import io.github.qingmo.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @Author: xwl
 * @Date: 2024/5/12 22:15
 * @Description:
 **/
@Service(value = "openai")
@Slf4j
public class OpenAiService implements GenerateContent {
    @Value(value = "${gpt.url}")
    private  String gptUrl;
    @Value(value = "${gpt.token}")
    private  String gptToken;
    @Override
    public  String  processGpt(String keyWords,Integer contentLength){
        ContentRequest contentRequest = new ContentRequest();
        contentRequest.setRole("user");
        //todo 查询数据库词库数据表通过生成TDK
        contentRequest.setContent(keyWords+PROMPTENUM.OPENAI_DESC.getName());
        GptParamRequest gptParamRequest = new GptParamRequest();
        gptParamRequest.setMessages(Collections.singletonList(contentRequest));
        HttpResponse respContent = HttpUtil.createPost(gptUrl).header("Content-Type", "application/json")
                .header("Authorization", gptToken).setSSLProtocol("TLSv1.2")
                .body(JSONUtil.toJsonStr(gptParamRequest)).execute(true);
        //todo 调用htmlSeo接口将content传递生成
        GptResp gptResp = JSON.parseObject(respContent.body(), GptResp.class);
        log.info("gptResp:{}",gptResp);
        return gptResp.getChoices().get(0).getMessage().getContent();
    }
}
