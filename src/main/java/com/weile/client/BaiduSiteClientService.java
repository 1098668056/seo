package com.weile.client;

import cn.hutool.http.HttpUtil;
import com.weile.client.Response.BaiduResp;
import io.github.qingmo.json.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: xwl
 * @Date: 2024/5/16 13:24
 * @Description:
 **/
@Service
public class BaiduSiteClientService implements  BaiduSiteClient{
    @Value("${baidu.token}")
    private String baiduUrl;
    @Async
    @Override
    public Boolean submitUrl(String url) {
        String body = HttpUtil.createPost(baiduUrl).body(url, "text/plain").execute(true).body();
        BaiduResp baiduResp = JSON.parseObject(body, BaiduResp.class);
        //todo 入库统计失败的条件
        return baiduResp.getSuccess() >= 1;
    }
}
