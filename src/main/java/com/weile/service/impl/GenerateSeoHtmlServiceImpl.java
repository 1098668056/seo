package com.weile.service.impl;


import com.weile.client.BaiduSiteClient;
import com.weile.config.MinIOConfigProperties;
import com.weile.domain.SeoHtml;
import com.weile.repository.SeoHtmlRepository;
import com.weile.service.FileStorageService;
import com.weile.service.GenerateSeoHtmlService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.*;
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


            Map<String, Object> params = new HashMap<>(16);
            params.put("title", seoHtml.getTitle());
            params.put("descriptions", seoHtml.getDescription());
            params.put("keywords", seoHtml.getKeywords());
            params.put("content", seoHtml.getContent());
            StringWriter out = new StringWriter();

            template.process(params, out);

            InputStream in = new ByteArrayInputStream(out.toString().getBytes());

            url = fileStorageService.uploadHtmlFile("", seoHtml.getFileName() + ".html", in);

            url = url.replace(minIOConfigProperties.getReadPath(), minIOConfigProperties.getAliasPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        baiduSiteClient.submitUrl(url);
        seoHtml.setUrl(url);
        seoHtmlRepository.save(seoHtml);
        return url;
    }
}
