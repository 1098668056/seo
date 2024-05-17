package com.weile;

import cn.hutool.core.util.RandomUtil;
import com.weile.client.KeyWordClient;
import com.weile.client.Response.KeysResp;
import com.weile.repository.KeyWordsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.awt.geom.RectangularShape;
import java.util.List;
import java.util.Random;

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
    @Test
    public void keysTest(){
        KeysResp resp = keyWordClient.getKeyWords("自助下单平台");
        System.out.println("resp = " + resp);
    }
    @Test
    public void key3(){
        List<String> result = keyWordsRepository.findFirstByKeyName("自助下单平台");
        int index = RandomUtil.randomInt(0, result.size());
        String keyName = result.get(index);
        System.out.println("keyName = " + keyName);

    }
}
