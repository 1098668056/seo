package com.weile;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.weile.client.GptClient;
import com.weile.client.GptType;
import com.weile.client.KeyWordClient;
import com.weile.client.Response.KeysResp;
import com.weile.client.Response.TdkGenerateResp;
import com.weile.client.Response.WordResp;
import com.weile.domain.KeyWords;
import com.weile.repository.KeyWordsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private GptType gptType;
    @Resource
    private GptClient  gptClient;
    @Test
    public void keysTest(){
        KeysResp resp = keyWordClient.getKeyWords("自助下单平台",1);
        System.out.println("resp = " + resp);
    }
    @Test
    public void key3(){
        List<String> result = keyWordsRepository.findFirstByKeyName("自助下单平台");
        int index = RandomUtil.randomInt(0, result.size());
        String keyName = result.get(index);
        System.out.println("keyName = " + keyName);

    }
    @Test
    public void getKey(){
        for (int i = 0; i < 5; i++) {
            System.out.println("采集"+i+"次");
            List<KeyWords> lists = new ArrayList<>();
            KeysResp resp = keyWordClient.getKeyWords("自助下单平台",1);
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
        TdkGenerateResp tdkGenerateResp = keyWordClient.generateTdk("自助下单平台", "dy下单");
        System.out.println("tdkGenerateResp = " + tdkGenerateResp);
        String data = tdkGenerateResp.getData();
        String[] split = data.split("keywords:/\n/\n");

    }

    public static void main(String[] args) {
       String str =  "title:\n\n自助下单平台-便捷dy下单服务\n\nmeta description:\n\n寻找专业的自助下单平台？在这里您可以享受便捷的dy下单服务,快速提升dy号人气和业务效果。\n\nmeta keywords:\n\n自助下单平台,dy下单,dy粉丝下单,dy业务下单,dy号自助下单,dy评论下单,dy赞下单,dy秒刷服务";
        // 定义正则表达式模式
        Pattern pattern = Pattern.compile("(title|meta description|meta keywords):\\s*\\n\\n(.*?)(?=\\n\\n(meta (description|keywords):|$))", Pattern.CASE_INSENSITIVE);

        // 创建matcher对象
        Matcher matcher = pattern.matcher(str);

        // 进行查找并输出结果
        while (matcher.find()) {
            String key = matcher.group(1); // 提取关键字：title, meta description, meta keywords
            String value = matcher.group(2); // 提取对应的值
            System.out.println(key + ": " + value);
        }
    }
    @Test
    public void  getContent(){
        gptType.setGptClient(SpringUtil.getBean("openai"));
        String resp = gptType.processGpt("dy粉丝下单", 100);
        System.out.println("resp = " + resp);
    }

}
