package com.weile.service.impl;

import com.weile.domain.SeoHtml;
import com.weile.repository.SeoHtmlRepository;
import com.weile.service.SeoHtmlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SeoHtmlServiceImpl implements SeoHtmlService {

    @Resource
    private SeoHtmlRepository seoHtmlRepository;


    @Override
    public Page<SeoHtml> getAllSeoHtml(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 5, Sort.Direction.DESC,"createTime");
        return seoHtmlRepository.findAll(pageable);
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
}
