package com.weile.service.impl;

import com.weile.domain.SeoHtml;
import com.weile.repository.SeoHtmlRepository;
import com.weile.service.GenerateSeoHtmlService;
import freemarker.template.Template;
import org.springframework.scheduling.annotation.Async;
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
@Transactional(rollbackFor = Exception.class)
public class GenerateSeoHtmlServiceImpl implements GenerateSeoHtmlService {
    private static final String TEMPLATE_PATH = "/SeoHtml.ftl";
    private static final String OUTPUT_PATH = "/output.html";

    @Resource
    private  SeoHtmlRepository seoHtmlRepository;
    @Resource
    private Template configuration;

    /**
     * 生成seo单页html
     *
     * @param seoHtml
     */
    @Async
    @Override
    public void generateSeoHtml(SeoHtml seoHtml) {
        // 使用try-with-resources语句自动管理资源
        try {
            Template template = configuration.getConfiguration().getTemplate(TEMPLATE_PATH);
            seoHtmlRepository.save(seoHtml);

            Map<String, Object> params = new HashMap<>(16);
            params.put("title", seoHtml.getTitle());
            params.put("description", seoHtml.getDescription());
            params.put("keywords", seoHtml.getKeywords());
            params.put("content", seoHtml.getContent());

            File outputFile = new File(getClass().getResource(OUTPUT_PATH).toURI());
            try (Writer writer = new FileWriter(outputFile)) {
                template.process(params, writer);
            }
        } catch (Exception e) {
            // 由于日志已经记录了异常信息，这里可以选择不重新抛出异常，或者抛出一个自定义的异常来处理业务逻辑。
//             throw new CustomException("Error processing SEO HTML.", e);
        }
}
}
