package com.weile.service;

import com.weile.domain.SeoHtml;
import freemarker.template.Template;

/**
 * @Author: xwl
 * @Date: 2024/5/12 15:54
 * @Description:
 **/
public interface GenerateSeoHtmlService {
    /**
     * 生成seo单页html
     * @param seoHtml
     */
    void generateSeoHtml(Template configuration, SeoHtml seoHtml);
}
