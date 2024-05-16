package com.weile.service.impl;

import cn.hutool.core.io.resource.ClassPathResource;
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
//            seoHtmlRepository.save(seoHtml);

            Map<String, Object> params = new HashMap<>(16);
            params.put("title", seoHtml.getTitle());
            params.put("descriptions", seoHtml.getDescription());
            params.put("keywords", seoHtml.getKeywords());
            params.put("content", seoHtml.getContent());
            StringWriter out = new StringWriter();
            template.process(params, out);
            InputStream in = new ByteArrayInputStream(out.toString().getBytes());
            url = fileStorageService.uploadHtmlFile("", seoHtml.getFileName() + ".html", in);
            url = url.replace(minIOConfigProperties.getReadPath(),minIOConfigProperties.getAliasPath());

            baiduSiteClient.submitUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            // 由于日志已经记录了异常信息，这里可以选择不重新抛出异常，或者抛出一个自定义的异常来处理业务逻辑。
//             throw new CustomException("Error processing SEO HTML.", e);
        }
        return url;
}
}
