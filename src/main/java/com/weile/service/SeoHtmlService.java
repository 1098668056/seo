package com.weile.service;

import com.weile.domain.SeoHtml;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface SeoHtmlService {
    /**
     * 静态页面列表
     */
    Page<SeoHtml> getAllSeoHtml(int pageNum);

    /**
     * 静态页面列表随机
     */
    List<SeoHtml> getAllSeoHtmlRandom();

    void updateSeo(Long id);


}
