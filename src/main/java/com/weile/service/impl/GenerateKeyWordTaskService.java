package com.weile.service.impl;

import com.weile.client.KeyWordClient;
import com.weile.client.Response.KeysResp;
import com.weile.client.Response.WordResp;
import com.weile.config.ApiException;
import com.weile.domain.KeyWords;
import com.weile.repository.KeyWordsRepository;
import com.weile.service.GenerateKeyWordTask;
import com.weile.service.HtmlViewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.Temperature;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.awt.print.PrinterAbortException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/17 14:51
 * @Description:
 **/
@Service
@Slf4j
public class GenerateKeyWordTaskService implements GenerateKeyWordTask {
    @Resource
    private KeyWordClient keyWordClient;
    @Resource
    private KeyWordsRepository keyWordsRepository;
    @Resource
    private HtmlViewService htmlViewService;

    /**
     * 定时获取关键词任务
     */
//    @Scheduled(cron = "30 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void keyWordTask() {
        log.info("开始采集--------------");
        KeysResp resp = keyWordClient.getKeyWords("自助下单平台",0);
        List<KeyWords> keyWords = new ArrayList<>();
        if (resp.getData().getWord().isEmpty()){
            throw new ApiException("暂无关键词");
        }
        for (WordResp wordResp : resp.getData().getWord()) {
            KeyWords keyWord = new KeyWords();
            keyWord.setKeyName(wordResp.getKeyword());
            keyWord.setScore(wordResp.getIndex());
            keyWords.add(keyWord);
        }
        //todo db
        keyWordsRepository.saveAll(keyWords);
        log.info("采集结束--------------");
    }
    @Scheduled(cron = "0 */5 * * * *")
    public void autoHtmlSeo(){
        List<KeyWords> keywords = keyWordsRepository.findFirst3ByUseCountEquals(0);
        List<String> titleSb = new ArrayList<>();
        List<String> keyWords = new ArrayList<>();
        for (KeyWords keyword : keywords) {
            keyword.setUseCount(1);
            titleSb.add(keyword.getKeyName());
            keyWords.add(keyword.getKeyName());
        }
        String resultTitle = String.join("_", titleSb);
        String resultKetWords = String.join(",", keyWords);
        System.out.println("开始生成文章="+titleSb);
        htmlViewService.onlyHtml("keywords",resultTitle,resultKetWords);
        keyWordsRepository.saveAll(keywords);
    }
}
