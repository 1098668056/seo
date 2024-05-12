package com.weile.service;


import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.weile.Response.Choices;
import com.weile.Response.GptResp;
import com.weile.domain.SeoHtml;
import com.weile.request.ContentRequest;
import com.weile.request.GptParamRequest;
import io.github.qingmo.json.JSON;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: xwl
 * @Date: 2024/5/12 18:04
 * @Description:
 **/
public class ProcessGptService {
    @Value(value = "${gpt.url}")
    private  String gptUrl;
    @Value(value = "${gpt.token}")
    private  String gptToken;
    @Resource
    private GenerateSeoHtmlService generateSeoHtmlService;
    public  void  processGpt(){
        ContentRequest contentRequest = new ContentRequest();
        contentRequest.setRole("user");
        //todo 查询数据库词库数据表通过生成TDK
        contentRequest.setContent("生成一篇卡盟200字文章");
        GptParamRequest gptParamRequest = new GptParamRequest();
        gptParamRequest.setMessages(Collections.singletonList(contentRequest));
        HttpResponse respContent = HttpUtil.createPost(gptUrl).header("Content-Type", "application/json")
                .header("Authorization", gptToken).setSSLProtocol("TLSv1.2")
                .body(JSONUtil.toJsonStr(gptParamRequest)).execute(true);
        //todo 调用htmlSeo接口将content传递生成
        GptResp gptResp = JSON.parseObject(respContent.body(), GptResp.class);
        for (Choices choice : gptResp.getChoices()) {
            String content = choice.getMessage().getContent();
            SeoHtml seoHtml = new SeoHtml();
            seoHtml.setTitle("");
            seoHtml.setKeywords("");
            seoHtml.setContent(content);
            seoHtml.setUrl("");
            generateSeoHtmlService.generateSeoHtml(seoHtml);
        }

    }


}
