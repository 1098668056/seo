package com.weile.service.impl;


import com.weile.client.BaiduSiteClient;
import com.weile.config.ApiException;
import com.weile.config.MinIOConfigProperties;
import com.weile.domain.SeoHtml;
import com.weile.repository.SeoHtmlRepository;
import com.weile.service.FileStorageService;
import com.weile.service.GenerateSeoHtmlService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private static final String OUTPUT_PATH = "/static/output.html";

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

    @Override
    public String generateSeoHtml(SeoHtml seoHtml) {
        // 使用try-with-resources语句自动管理资源
        String url = "";
        try {
            Template template = configuration.getTemplate(TEMPLATE_PATH);

            Map<String, Object> params = new HashMap<>(16);
            params.put("title", seoHtml.getTitle());
            params.put("descriptions", seoHtml.getDescription());
            params.put("keywords", seoHtml.getKeywords());
            params.put("content", seoHtml.getContent());
            StringWriter out = new StringWriter();
            template.process(params, out);
            InputStream in = new ByteArrayInputStream(out.toString().getBytes());
            try {
                url = fileStorageService.uploadHtmlFile("", seoHtml.getFileName() + ".html", in);
            } catch (Exception e) {
                throw new ApiException("minio上传失败");
            }
            url = url.replace(minIOConfigProperties.getReadPath(),minIOConfigProperties.getAliasPath());
            try {
                baiduSiteClient.submitUrl(url);
            } catch (Exception e) {
                throw new ApiException("百度提交失败");
            }
            seoHtml.setUrl(url);
            seoHtmlRepository.save(seoHtml);
        } catch (Exception e) {
            throw new ApiException("生成静态文件失败");
        }
        return url;
}
}
