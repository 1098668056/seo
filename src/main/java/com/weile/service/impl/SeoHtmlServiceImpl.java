package com.weile.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.weile.domain.HtmlBehavior;
import com.weile.domain.SeoHtml;
import com.weile.domain.vo.SeoHtmlVO;
import com.weile.repository.HtmlBehaviorRepository;
import com.weile.repository.SeoHtmlRepository;
import com.weile.service.SeoHtmlService;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SeoHtmlServiceImpl implements SeoHtmlService {

    @Resource
    private SeoHtmlRepository seoHtmlRepository;
    @Resource
    private HtmlBehaviorRepository htmlBehaviorRepository;



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
}
