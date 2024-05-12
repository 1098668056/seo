package com.weile.service;


import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.weile.request.ContentRequest;
import com.weile.request.GptParamRequest;
import org.springframework.beans.factory.annotation.Value;
import java.util.*;

/**
 * @Author: xwl
 * @Date: 2024/5/12 18:04
 * @Description:
 **/
public class ProcessGptService {
    @Value(value = "gpt.url")
    private  String gptUrl = "https://oa.api2d.net/v1/chat/completions";
    @Value(value = "gpt.token")
    private  String gptToken ="Bearer fk190313-KdmEbwOxr9rUXQCkgcggHYKTFNtZqbbV";
    public   HttpResponse processGpt(){
        ContentRequest contentRequest = new ContentRequest();
        contentRequest.setRole("user");
        contentRequest.setContent("生成一篇卡盟200字文章");
        GptParamRequest gptParamRequest = new GptParamRequest();
        gptParamRequest.setMessages(Collections.singletonList(contentRequest));
        return   HttpUtil.createPost(gptUrl).header("Content-Type", "application/json")
                .header("Authorization", gptToken).setSSLProtocol("TLSv1.2")
                .body(JSONUtil.toJsonStr(gptParamRequest)).execute(true);
        //todo 调用htmlSeo接口将content传递生成
    }


}
