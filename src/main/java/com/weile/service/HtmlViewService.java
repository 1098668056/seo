package com.weile.service;


import com.google.common.util.concurrent.ListenableFutureTask;
import org.springframework.scheduling.annotation.AsyncResult;

/**
 * @Author: xwl
 * @Date: 2024/5/14 13:39
 * @Description:
 **/
public interface HtmlViewService {
    String onlyHtml(String fileName,String title,String keywords);

    /**
     * 增加异步返回删除或者修改静态文件接口
     * @param id
     * @return
     */
    void  delOrUpdateHtml(Long id);

    /**
     * 生成网站地图
     * @param count
     */
    void generateSiteMaps(Long count);
}
