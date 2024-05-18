package com.weile;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.weile.domain.KeyWords;
import com.weile.domain.SeoHtml;
import com.weile.repository.KeyWordsRepository;
import com.weile.service.FileStorageService;
import com.weile.service.GenerateSeoHtmlService;
import com.weile.service.HtmlViewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/14 11:27
 * @Description:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class FreeMarkGenerateTest {
    @Resource
    private GenerateSeoHtmlService generateSeoHtmlService;
    @Resource
    private FileStorageService fileStorageService;
    @Resource
    private KeyWordsRepository keyWordsRepository;
    @Resource
    private HtmlViewService htmlViewService;

    @Test
    public void onlyTest()
    {
        List<KeyWords> all = keyWordsRepository.findAll();
            String pinyin = PinyinUtil.getPinyin("代刷网");
            String resultStr = pinyin.replaceAll(" ", "");
            String result = htmlViewService.onlyHtml(resultStr, "代刷网");
            System.out.println("result = " + result);
    }

}
