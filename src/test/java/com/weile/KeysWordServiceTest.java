package com.weile;

import com.weile.client.KeyWordClient;
import com.weile.client.Response.KeysResp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
    @Test
    public void keysTest(){
        KeysResp resp = keyWordClient.getKeyWords("测试");
        System.out.println("resp = " + resp);
    }
}
