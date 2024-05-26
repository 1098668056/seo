package com.weile.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.weile.domain.HtmlBehavior;
import com.weile.domain.SeoHtml;
import com.weile.domain.vo.SeoHtmlVO;
import com.weile.repository.HtmlBehaviorRepository;
import com.weile.repository.SeoHtmlRepository;
import com.weile.service.GenerateSeoHtmlService;
import com.weile.service.HtmlViewService;
import com.weile.service.SeoHtmlService;
import com.weile.utils.JpaUtil;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class SeoHtmlServiceImpl implements SeoHtmlService {

    @Resource
    private SeoHtmlRepository seoHtmlRepository;
    @Resource
    private HtmlBehaviorRepository htmlBehaviorRepository;
    @Resource
    private HtmlViewService htmlViewService;



    @Override
    public Page<SeoHtmlVO> getAllSeoHtml(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 5, Sort.Direction.DESC,"createTime");
        Page<SeoHtml> all = seoHtmlRepository.findAll(pageable);
        List<SeoHtmlVO> seoHtmlVos = BeanUtil.copyToList(all.getContent(), SeoHtmlVO.class);
        seoHtmlVos.forEach(o->{
           o.setSource(htmlBehaviorRepository.countByHtmlId(o.getId()));
        });
        return new PageImpl<>(seoHtmlVos, pageable, all.getTotalElements());
    }

    @Override
    public List<SeoHtml> getAllSeoHtmlRandom() {
        return seoHtmlRepository.findFirst5ByOrderByTitleDesc();
    }

    @Override
    public void updateSeo(Long id) {
        SeoHtml seoHtml = seoHtmlRepository.findById(id).orElse(null);
        if (seoHtml!=null&&seoHtml.getQueryCount()!=null){
            seoHtml.setQueryCount(seoHtml.getQueryCount()+1);
            seoHtmlRepository.save(seoHtml);
        }else if (seoHtml!=null&&seoHtml.getQueryCount()==null){
            seoHtml.setQueryCount(1L);
            seoHtmlRepository.save(seoHtml);
        }
    }

    /**
     * 保存单页访问
     *
     * @param htmlBehavior
     */
    @Async
    @Override
    public void saveBehavior(HtmlBehavior htmlBehavior) {
        htmlBehaviorRepository.save(htmlBehavior);
    }

    /**
     * 获取最新文章列表
     *
     * @return
     */
    @Override
    public List<SeoHtml> getLatestSeoHtml() {
        return seoHtmlRepository.findFirst5ByOrderByCreateTimeDesc();
    }

    /**
     * 根据关键词查询列表
     *
     * @param pageNum
     * @param keyWords
     * @return
     */
    @Override
    public Page<SeoHtmlVO> getAllSeoHtmlBykeyWords(int pageNum, String keyWords) {
        Pageable pageable = PageRequest.of(pageNum, 5, Sort.Direction.DESC,"createTime");
//        Page<SeoHtml> all = seoHtmlRepository.findAll(Example.of(SeoHtml.builder().keywords(keyWords).build()),pageable);
        Page<SeoHtml> all = seoHtmlRepository.findByKeywordsContainingOrderByCreateTimeDesc(keyWords, pageable);
        List<SeoHtmlVO> seoHtmlVos = BeanUtil.copyToList(all.getContent(), SeoHtmlVO.class);
        seoHtmlVos.forEach(o->{
            o.setSource(htmlBehaviorRepository.countByHtmlId(o.getId()));
        });
        return new PageImpl<>(seoHtmlVos, pageable, all.getTotalElements());
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @Override
    public SeoHtml getSeoHtmlById(Long id) {
        return seoHtmlRepository.getById(id);
    }

    /**
     * 更新内容
     *
     * @param seoHtml
     */
    @Override
    public void updateSeoHtml(SeoHtml seoHtml) {
        seoHtmlRepository.save(seoHtml);
        this.htmlViewService.delOrUpdateHtml(seoHtml.getId());

    }
}
