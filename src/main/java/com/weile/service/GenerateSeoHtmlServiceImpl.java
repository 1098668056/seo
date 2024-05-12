package com.weile.service;

import com.weile.domain.SeoHtml;
import com.weile.repository.SeoHtmlRepository;
import freemarker.template.Template;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
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
        try (StringWriter out = new StringWriter()) {
            Template template = configuration.getConfiguration().getTemplate("/SeoHtml.ftl");
            seoHtmlRepository.save(seoHtml);

            Map<String, Object> params = new HashMap<>(16);
            params.put("title", seoHtml.getTitle());
            params.put("description", seoHtml.getDescription());
            params.put("keywords", seoHtml.getKeywords());
            params.put("content", seoHtml.getContent());
            template.process(params, out);
            // 将StringWriter的内容转换为InputStream，不需要手动关闭StringWriter，因为它是Java String的内部机制
            InputStream in = new ByteArrayInputStream(out.toString().getBytes());
            // 在这里处理in，例如传递给其他方法或进行其他操作
            // 后期防止存储或者网络问题可以上传到阿里云的oss存储中
            // 注意：根据你的实际需求，你可能需要对下面的代码进行调整
        } catch (Exception e) {
            // 在catch块中处理异常，这里没有转换异常类型，因为RuntimeException已经足够泛化
            // 但是，我们保留原始异常作为cause，以保留异常栈的上下文信息
            throw new RuntimeException("Error processing SEO HTML.", e);
        }
    }
}
