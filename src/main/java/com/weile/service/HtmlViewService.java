package com.weile.service;


/**
 * @Author: xwl
 * @Date: 2024/5/14 13:39
 * @Description:
 **/
public interface HtmlViewService {
    String onlyHtml(String fileName,String keyWords);
    String delOrUpdateHtml(Long id);
}
