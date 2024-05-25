package com.weile.service;


import org.springframework.scheduling.annotation.AsyncResult;

/**
 * @Author: xwl
 * @Date: 2024/5/14 13:39
 * @Description:
 **/
public interface HtmlViewService {
    String onlyHtml(String fileName,String keyWords);

    /**
     * 增加异步返回删除或者修改静态文件接口
     * @param id
     * @return
     */
    AsyncResult<String> delOrUpdateHtml(Long id);
}
