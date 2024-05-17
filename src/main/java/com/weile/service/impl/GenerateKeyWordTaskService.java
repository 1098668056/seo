package com.weile.service.impl;

import com.weile.client.KeyWordClient;
import com.weile.client.Response.KeysResp;
import com.weile.client.Response.WordResp;
import com.weile.config.ApiException;
import com.weile.domain.KeyWords;
import com.weile.repository.KeyWordsRepository;
import com.weile.service.GenerateKeyWordTask;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/17 14:51
 * @Description:
 **/
public class GenerateKeyWordTaskService implements GenerateKeyWordTask {
    @Resource
    private KeyWordClient keyWordClient;
    @Resource
    private KeyWordsRepository keyWordsRepository;

    /**
     * 定时获取关键词任务
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void keyWordTask() {
        KeysResp resp = keyWordClient.getKeyWords("dsw");
        List<KeyWords> keyWords = new ArrayList<>();
        if (resp.getData().getWord().isEmpty()){
            throw new ApiException("暂无关键词");
        }
        for (WordResp wordResp : resp.getData().getWord()) {
            KeyWords keyWord = new KeyWords();
            keyWord.setKeys(wordResp.getKeyword());
            keyWord.setScore(wordResp.getIndex());
            keyWords.add(keyWord);
        }
        //todo db
        keyWordsRepository.saveAll(keyWords);

    }
}
