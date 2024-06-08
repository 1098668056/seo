package com.weile.service.impl;


import cn.hutool.extra.spring.SpringUtil;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.weile.client.GptTypeClient;
import com.weile.client.PROMPTENUM;
import com.weile.config.ApiException;
import com.weile.domain.SeoHtml;
import com.weile.repository.SeoHtmlRepository;
import com.weile.service.GenerateSeoHtmlService;
import com.weile.service.HtmlViewService;
import com.weile.utils.LongTailWordUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: xwl
 * @Date: 2024/5/14 13:39
 * @Description:
 **/
@Service
public class HtmlViewServiceImpl implements HtmlViewService {
    @Resource
    private GenerateSeoHtmlService generateSeoHtmlService;
    @Resource
    private GptTypeClient gptTypeClient;
    @Resource
    private LongTailWordUtils longTailWordUtils;
    @Resource
    private SeoHtmlRepository seoHtmlRepository;
    @Override
    public String onlyHtml(String fileName,String title,String keyWords) {
        SeoHtml seoHtml = new SeoHtml();
        gptTypeClient.setGenerateContent(SpringUtil.getBean(PROMPTENUM.OPENAI_BEAN.getName()));
        String content = gptTypeClient.processGpt(keyWords.split(",")[0], 200);
        seoHtml.setTitle(title);
        seoHtml.setDescription(content.split("。")[0]);
        seoHtml.setKeywords(keyWords);
        seoHtml.setFileName(fileName);
        seoHtml.setContent(content);
        seoHtml.setCreateTime(new Date());
        return generateSeoHtmlService.generateSeoHtml(seoHtml);
    }

    @Async
    @Override
    public void delOrUpdateHtml(Long id) {
        SeoHtml seoHtml = seoHtmlRepository.findById(id).orElseThrow(()->new ApiException("id不存在"));
        generateSeoHtmlService.generateSeoHtml(seoHtml);
    }

    /**
     * 生成网站地图
     *
     * @param count
     */
    @Override
    public void generateSiteMaps(Long count) {

    }
}
