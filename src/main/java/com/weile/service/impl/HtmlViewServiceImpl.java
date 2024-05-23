package com.weile.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.weile.client.GenerateContent;
import com.weile.client.GptTypeClient;
import com.weile.client.KeyWordClient;
import com.weile.client.PROMPTENUM;
import com.weile.client.Response.TdkGenerateResp;
import com.weile.domain.SeoHtml;
import com.weile.service.GenerateSeoHtmlService;
import com.weile.service.HtmlViewService;
import com.weile.utils.LongTailWordUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xwl
 * @Date: 2024/5/14 13:39
 * @Description:
 **/
@Service
public class HtmlViewServiceImpl implements HtmlViewService {
    @Resource
    private GenerateSeoHtmlService generateSeoHtmlService;
    @Resource
    private GptTypeClient gptTypeClient;
    @Resource
    private KeyWordClient keyWordClient;
    @Resource
    private LongTailWordUtils longTailWordUtils;
    @Override
    public String onlyHtml(String fileName,String keyWords) {
        SeoHtml seoHtml = new SeoHtml();
        gptTypeClient.setGenerateContent(SpringUtil.getBean(PROMPTENUM.OPENAI_BEAN.getName()));
        String content = gptTypeClient.processGpt(keyWords, 200);
        seoHtml.setTitle(longTailWordUtils.generateTitle(keyWords));
        seoHtml.setDescription(content.split("。")[0]);
        seoHtml.setKeywords(longTailWordUtils.generateKeyWords(keyWords));
        seoHtml.setFileName(fileName);
        seoHtml.setContent(content);
        seoHtml.setCreateTime(new Date());
        return generateSeoHtmlService.generateSeoHtml(seoHtml);
    }
}
