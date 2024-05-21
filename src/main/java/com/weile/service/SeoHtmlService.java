package com.weile.service;

import com.weile.domain.HtmlBehavior;
import com.weile.domain.SeoHtml;
import com.weile.domain.vo.SeoHtmlVO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface SeoHtmlService {
    /**
     * 静态页面列表
     */
    Page<SeoHtmlVO> getAllSeoHtml(int pageNum);

    /**
     * 静态页面列表随机
     */
    List<SeoHtml> getAllSeoHtmlRandom();

    void updateSeo(Long id);

    /**
     * 保存单页访问
     * @param htmlBehavior 实体类
     */

    void saveBehavior(HtmlBehavior htmlBehavior);


}
