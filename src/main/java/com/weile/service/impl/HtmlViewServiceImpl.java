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
    @Override
    public String onlyHtml(String fileName,String keyWords) {
        SeoHtml seoHtml = new SeoHtml();
        gptTypeClient.setGenerateContent(SpringUtil.getBean(PROMPTENUM.OPENAI_BEAN.getName()));
        String content = gptTypeClient.processGpt(keyWords, 200);
        TdkGenerateResp tdkGenerateResp = keyWordClient.generateTdk(keyWords, PROMPTENUM.TDK_DEPUTY.getName());
        String filterEnter = tdkGenerateResp.getData().replaceAll(PROMPTENUM.KEYWORD_REGEX_ENTER.getName(), "");
        System.out.println(filterEnter);
        //正则表达式解析
        Pattern pattern = Pattern.compile(PROMPTENUM.KEYWORD_REGEX.getName(),Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(tdkGenerateResp.getData());
        while (matcher.find()){
            System.out.println("matcher.group(1) = " + matcher.group(1));
            System.out.println("matcher.group(2) = " + matcher.group(2));
            if (PROMPTENUM.KEYWORD_PREFIX_TITLE.getName().equals(matcher.group(1))){seoHtml.setTitle(matcher.group(2));}
            if (PROMPTENUM.KEYWORD_PREFIX_META_DESCRIPTION.getName().equals(matcher.group(1))){seoHtml.setDescription(matcher.group(2));}
            if (PROMPTENUM.KEYWORD_PREFIX_META_KEYWORDS.getName().equals(matcher.group(1))){seoHtml.setKeywords(matcher.group(2));}
        }
        seoHtml.setFileName(fileName);
        seoHtml.setContent(content);
        seoHtml.setCreateTime(new Date());
        return generateSeoHtmlService.generateSeoHtml(seoHtml);
    }

    public static void main(String[] args) {
        String string = "\\n张三 \\\\n李四";
         String strings = string;
        System.out.println(strings);
//        System.out.println(PROMPTENUM.KEYWORD_REGEX.getName());
        String str = "\\n\\nKS自助下单平台-低价自助下单服务\\n\\nmeta description:\\n\\nKS自助下单平台提供业务低价自助下单服务,满足您的需求。广西、河南等地区均可使用,快来体验吧！\n\nmeta keywords:\\n\\nKS自助下单, 自助下单, KS自助下单平台, 业务下载, 低价评论, 赞在线, 卡盟在线, 自助下单网站";
    }
}
