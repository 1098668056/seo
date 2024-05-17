package com.weile.client;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import com.weile.client.Response.BaiduResp;
import com.weile.config.ApiException;
import io.github.qingmo.json.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<Boolean> submitUrl(String url) {
        BaiduResp baiduResp = null;

        HttpResponse response = HttpUtil.createPost(baiduUrl).body(url, "text/plain").execute(false);
        if (response.getStatus() != HttpStatus.HTTP_OK){
            throw new ApiException("百度推送失败");
        }
        baiduResp = JSON.parseObject(response.body(), BaiduResp.class);

        //todo 入库统计失败的条件
        return CompletableFuture.completedFuture(baiduResp.getSuccess() >= 1);
    }
}
