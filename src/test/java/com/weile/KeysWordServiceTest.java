package com.weile;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.weile.client.GenerateContent;
import com.weile.client.GptTypeClient;
import com.weile.client.KeyWordClient;
import com.weile.client.PROMPTENUM;
import com.weile.client.Response.KeysResp;
import com.weile.client.Response.TdkGenerateResp;
import com.weile.client.Response.WordResp;
import com.weile.domain.KeyWords;
import com.weile.repository.KeyWordsRepository;
import io.github.qingmo.json.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xwl
 * @Date: 2024/5/17 13:59
 * @Description:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class KeysWordServiceTest {
    @Resource
    private KeyWordClient keyWordClient;
    @Resource
    private KeyWordsRepository keyWordsRepository;
    @Resource
    private GptTypeClient gptTypeClient;
    @Test
    public void keysTest(){
        KeysResp resp = keyWordClient.getKeyWords("自助下单平台",1);
        System.out.println("resp = " + resp);
    }
    @Test
    public void getKey(){
        for (int i = 0; i < 5; i++) {
            System.out.println("采集"+i+"次");
            List<KeyWords> lists = new ArrayList<>();
            KeysResp resp = keyWordClient.getKeyWords("卡盟",1);
            for (WordResp wordResp : resp.getData().getWord()) {
                KeyWords keyWords = new KeyWords();
                keyWords.setKeyName(wordResp.getKeyword());
                keyWords.setScore(wordResp.getIndex());
                keyWords.setUseCount(1);
                lists.add(keyWords);
            }
            /**
             * 批量保存数据
             */
            keyWordsRepository.saveAll(lists);
        }
    }
    @Test
    public void notIn(){
        int i = keyWordsRepository.countByUseCountEquals(0);
        System.out.println("i = " + i);
    }
    @Test
    public void tdkGenerate(){
        TdkGenerateResp tdkGenerateResp = keyWordClient.generateTdk("卡盟", "dy下单");
        String data = tdkGenerateResp.getData();
        System.out.println("tdkGenerateResp.getData() = " + tdkGenerateResp.getData());

    }
    @Test
    public void  getContent(){
        gptTypeClient.setGenerateContent(SpringUtil.getBean(PROMPTENUM.KIMI_BEAN.getName()));
        String resp = gptTypeClient.processGpt("自助下单平台百度有哪些关键词呢", 100);
        System.out.println("resp = " + resp);
    }
    @Test
    public void first10(){
        List<KeyWords> key = keyWordsRepository.findTop8ByKeyNameContainingOrderByUseCountAsc("卡盟");
        System.out.println("key = " + key);


    }
    @Test
    public void batchGenerateHtml(){
    }

}
