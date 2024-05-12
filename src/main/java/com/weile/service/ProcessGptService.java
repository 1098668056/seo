package com.weile.service;


import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
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
    private  static  String gptUrl = "https://stream.api2d.net/v1/chat/completions";
    @Value(value = "gpt.token")
    private static String gptToken ="Bearer fk190313-KdmEbwOxr9rUXQCkgcggHYKTFNtZqbbV";
    public  static  HttpResponse processGpt(){
        ContentRequest contentRequest = new ContentRequest();
        contentRequest.setRole("user");
        contentRequest.setContent("生成一篇200字文章");
        GptParamRequest gptParamRequest = new GptParamRequest();
        gptParamRequest.setMessages(Collections.singletonList(contentRequest));
        return   HttpUtil.createPost(gptUrl).header("Content-Type", "application/json")
                .header("Authorization", gptToken)
                .body(JSONUtil.toJsonStr(gptParamRequest)).execute(true);
    }

    public static void main(String[] args) {
        HttpResponse httpResponse = processGpt();
        JSONObject jsonObject = JSONUtil.parseObj(httpResponse.body());
        System.out.println(jsonObject.toString());
    }
}
