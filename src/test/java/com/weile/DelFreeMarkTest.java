package com.weile;

import com.weile.domain.SeoHtml;
import com.weile.repository.SeoHtmlRepository;
import com.weile.service.SeoHtmlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/6/22 14:59
 * @Description:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class DelFreeMarkTest {
    @Resource
    private SeoHtmlService seoHtmlService;
    @Resource
    private SeoHtmlRepository seoHtmlRepository;
    @Test
    public void delFreeMarkContent(){
        List<SeoHtml> all = seoHtmlRepository.findAll();
        for (SeoHtml seoHtml : all) {
            System.out.println(seoHtml.getUrl());
            seoHtmlService.updateSeoHtml(seoHtml);
        }

    }
}
