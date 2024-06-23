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

    /**
     * 获取最新文章列表
     * @return
     */

    List<SeoHtml> getLatestSeoHtml();

    /**
     * 根据关键词查询列表
     * @param pageNum
     * @param keyWords
     * @return
     */
    Page<SeoHtmlVO> getAllSeoHtmlBykeyWords(int pageNum, String keyWords);

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    SeoHtml getSeoHtmlById(Long id);

    /**
     * 更新内容
     * @param seoHtml
     */

    void  updateSeoHtml(SeoHtml seoHtml);

    void addSoHtml(SeoHtml seoHtml);
    /**
     * 静态页面列表
     */
    Page<SeoHtmlVO> findPageByFileName(String fileName,int pageNum);
}
