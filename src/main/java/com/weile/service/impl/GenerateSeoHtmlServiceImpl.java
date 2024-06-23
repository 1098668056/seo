package com.weile.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.weile.client.BaiduSiteClient;
import com.weile.config.MinIOConfigProperties;
import com.weile.config.WebSiteInfoProperties;
import com.weile.domain.SeoHtml;
import com.weile.domain.WebSiteInfo;
import com.weile.repository.SeoHtmlRepository;
import com.weile.repository.WebSiteInfoRepository;
import com.weile.service.FileStorageService;
import com.weile.service.GenerateSeoHtmlService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xwl
 * @Date: 2024/5/12 15:48
 * @Description: 生成seo-html内容
 **/
@Service
public class GenerateSeoHtmlServiceImpl implements GenerateSeoHtmlService {
    private static final String TEMPLATE_PATH = "/SeoHtml.ftl";
    @Resource
    private  SeoHtmlRepository seoHtmlRepository;
    @Autowired
    private Configuration configuration;
    @Resource
    private FileStorageService fileStorageService;
    @Resource
    private MinIOConfigProperties minIOConfigProperties;
    @Resource
    private BaiduSiteClient baiduSiteClient;
    @Resource
    private WebSiteInfoRepository webSiteInfoRepository;
    /**
     * 生成seo单页html
     *
     * @param seoHtml
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String generateSeoHtml(SeoHtml seoHtml) {
        // 使用try-with-resources语句自动管理资源
        String url = "";

        Template template = null;

        try {
            template = configuration.getTemplate(TEMPLATE_PATH);

            // 封装参数
            Map<String, Object> params = getStringObjectMap(seoHtml);
            StringWriter out = new StringWriter();

            template.process(params, out);

            InputStream in = new ByteArrayInputStream(out.toString().getBytes());

            url = fileStorageService.uploadHtmlFile("", seoHtml.getId() + ".html", in);

            url = url.replace(minIOConfigProperties.getReadPath(), minIOConfigProperties.getAliasPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        baiduSiteClient.submitUrl(url);
        seoHtml.setUrl(url);
        //统计改词
        seoHtmlRepository.save(seoHtml);
        return url;
    }

    @NotNull
    private  Map<String, Object> getStringObjectMap(SeoHtml seoHtml) {
        SeoHtml seoHtmlDb = seoHtmlRepository.save(seoHtml);
        WebSiteInfo webSiteInfo = webSiteInfoRepository.getById(1L);
        SeoHtml before = seoHtmlRepository.findFirst1ByIdBeforeOrderByIdDesc(seoHtmlDb.getId()).orElse(new SeoHtml());
        Map<String, Object> params = new HashMap<>(16);
        params.put("title", seoHtml.getTitle());
        //获取第一个关键词进行展示
        params.put("titleFirst", seoHtml.getTitle().split("_")[0]);
        params.put("descriptions", seoHtml.getDescription());
        params.put("webSiteUrl", webSiteInfo.getUrl());
        params.put("webSiteCount", webSiteInfo.getCount());
        params.put("innerScript", webSiteInfo.getInnerScript());
        params.put("keywords", seoHtml.getKeywords());
        params.put("content", seoHtml.getContent());
        params.put("lastTitle", before.getTitle());
        params.put("lastUrl", before.getUrl());
        params.put("id",seoHtmlDb.getId().toString());
        params.put("createTime", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        params.put("showCount", RandomUtil.randomInt(10,1000));
        return params;
    }
}
