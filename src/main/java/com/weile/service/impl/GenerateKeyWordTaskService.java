package com.weile.service.impl;

import com.weile.client.KeyWordClient;
import com.weile.client.Response.KeysResp;
import com.weile.client.Response.WordResp;
import com.weile.config.ApiException;
import com.weile.domain.KeyWords;
import com.weile.repository.KeyWordsRepository;
import com.weile.service.GenerateKeyWordTask;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.Temperature;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    /**
     * 定时获取关键词任务
     */
//    @Scheduled(cron = "30 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void keyWordTask() {
        log.info("开始采集--------------");
        KeysResp resp = keyWordClient.getKeyWords("自助下单平台");
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
}
