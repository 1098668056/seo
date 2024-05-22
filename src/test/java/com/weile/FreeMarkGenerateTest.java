package com.weile;

import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.http.HttpUtil;
import com.weile.domain.KeyWords;
import com.weile.domain.SeoHtml;
import com.weile.repository.KeyWordsRepository;
import com.weile.repository.SeoHtmlRepository;
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
    private KeyWordsRepository keyWordsRepository;
    @Resource
    private HtmlViewService htmlViewService;
    @Resource
    private SeoHtmlRepository seoHtmlRepository;

    @Test
    public void onlyTest()
    {
        List<KeyWords> all = keyWordsRepository.findAll();
            String pinyin = PinyinUtil.getPinyin("dy自助下单");
            String resultStr = pinyin.replaceAll(" ", "");
            String result = htmlViewService.onlyHtml("1", "ks自助下单");
            System.out.println("result = " + result);
    }
    @Test
    public void beforeTest()
    {
        SeoHtml first1ByIdAfterOrderById = seoHtmlRepository.findFirst1ByIdAfterOrderById(10L).orElse(new SeoHtml());
        System.out.println("first1ByIdAfterOrderById = " + first1ByIdAfterOrderById);
    }
    @Test
    public void afterTest() {
        SeoHtml first1ByIdAfterOrderById = seoHtmlRepository.findFirst1ByIdBeforeOrderByIdDesc(10L).orElse(new SeoHtml());
        System.out.println("first1ByIdAfterOrderById = " + first1ByIdAfterOrderById);

    }

    public static void main(String[] args) {
        String string = HttpUtil.get("https://www.baidu.com/s?ie=UTF-8&wd=代刷网");
        System.out.println(string);
    }

}
