package com.weile.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.weile.client.GptClient;
import com.weile.domain.SeoHtml;
import com.weile.repository.KeyWordsRepository;
import com.weile.service.GenerateSeoHtmlService;
import com.weile.service.HtmlViewService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/14 13:39
 * @Description:
 **/
@Service
public class HtmlViewServiceImpl implements HtmlViewService {
    @Resource
    GenerateSeoHtmlService generateSeoHtmlService;
    @Resource
    private KeyWordsRepository keyWordsRepository;
    @Resource
    GptClient gptClient;
    @Override
    public String onlyHtml(String fileName,String keyWords) {
        SeoHtml seoHtml = new SeoHtml();
        String content = gptClient.processGpt(keyWords, 200);
        seoHtml.setUrl(fileName);
        seoHtml.setFileName(fileName);
        String[] desc = content.split("。");
        seoHtml.setDescription(desc[0]);
        seoHtml.setTitle(keyWords);
        seoHtml.setContent(content);
        List<String> firstByKeyName = keyWordsRepository.findFirstByKeyName(keyWords);
        if (firstByKeyName != null && !firstByKeyName.isEmpty()){
            int index = RandomUtil.randomInt(0, firstByKeyName.size());
            String keyWord = firstByKeyName.get(index);
            StringBuilder sb = new StringBuilder();
            sb.append(keyWords);
            sb.append(",");
            sb.append(keyWord);
            keyWord = sb.toString();
        }
        seoHtml.setKeywords(keyWords);
        return generateSeoHtmlService.generateSeoHtml(seoHtml);
    }
}
