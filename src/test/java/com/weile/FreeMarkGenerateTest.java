package com.weile;

import com.weile.domain.SeoHtml;
import com.weile.service.FileStorageService;
import com.weile.service.GenerateSeoHtmlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
    @Test
     public void generateHtml(){
        SeoHtml seoHtml = new SeoHtml();
        seoHtml.setUrl("testUrl");
        seoHtml.setKeywords("testWord");
        seoHtml.setTitle("testTitle");
        seoHtml.setContent("testContent");
        seoHtml.setDescription("testDescription");
        seoHtml.setUrl("4");
        generateSeoHtmlService.generateSeoHtml(seoHtml);
    }
}
