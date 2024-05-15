package com.weile.service.impl;

import com.weile.client.GptClient;
import com.weile.domain.SeoHtml;
import com.weile.service.GenerateSeoHtmlService;
import com.weile.service.HtmlViewService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author: xwl
 * @Date: 2024/5/14 13:39
 * @Description:
 **/
@Service
public class HtmlViewServiceImpl implements HtmlViewService {
    @Resource
    GenerateSeoHtmlService generateSeoHtmlService;
    @Resource
    GptClient gptClient;
    @Override
    public String onlyHtml(String fileName,String keyWords) {
        SeoHtml seoHtml = new SeoHtml();
        String content = gptClient.processGpt(keyWords, 200);
        seoHtml.setUrl(fileName);
        seoHtml.setDescription(content);
        seoHtml.setTitle(keyWords);
        seoHtml.setContent(content);
        seoHtml.setKeywords(keyWords);
        return generateSeoHtmlService.generateSeoHtml(seoHtml);
    }
}
