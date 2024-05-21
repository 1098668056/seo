package com.weile.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.weile.client.GenerateContent;
import com.weile.client.KeyWordClient;
import com.weile.client.PROMPTENUM;
import com.weile.client.Response.TdkGenerateResp;
import com.weile.domain.SeoHtml;
import com.weile.repository.KeyWordsRepository;
import com.weile.service.GenerateSeoHtmlService;
import com.weile.service.HtmlViewService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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
    GenerateSeoHtmlService generateSeoHtmlService;
    @Resource
    private KeyWordsRepository keyWordsRepository;
    @Resource
    GenerateContent generateContent;
    @Resource
    private KeyWordClient keyWordClient;
    @Override
    public String onlyHtml(String fileName,String keyWords) {
        SeoHtml seoHtml = new SeoHtml();
        String content = generateContent.processGpt(keyWords, 200);
        TdkGenerateResp tdkGenerateResp = keyWordClient.generateTdk(keyWords, PROMPTENUM.TDK_DEPUTY.getName());
        String data = tdkGenerateResp.getData();
        //正则表达式解析
        Pattern pattern = Pattern.compile(PROMPTENUM.KEYWORD_REGEX.getName(), Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()){
            if (PROMPTENUM.KEYWORD_PREFIX_TITLE.getName().equals(matcher.group(1))){seoHtml.setTitle(matcher.group(2));}
            if (PROMPTENUM.KEYWORD_PREFIX_META_DESCRIPTION.getName().equals(matcher.group(1))){seoHtml.setDescription(matcher.group(2));}
            if (PROMPTENUM.KEYWORD_PREFIX_META_KEYWORDS.getName().equals(matcher.group(1))){seoHtml.setKeywords(matcher.group(2));}
        }
        seoHtml.setFileName(fileName);
        seoHtml.setContent(content);
        seoHtml.setCreateTime(new Date());
        return generateSeoHtmlService.generateSeoHtml(seoHtml);
    }
}
