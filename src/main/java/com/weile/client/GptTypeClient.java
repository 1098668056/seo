package com.weile.client;

import lombok.Data;
import org.springframework.stereotype.Service;



/**
 * @Author: xwl
 * @Date: 2024/5/21 11:08
 * @Description:
 **/
@Service
@Data
public class GptTypeClient {
    private GenerateContent generateContent;

    public String processGpt(String keyWords,Integer contentLength)
    {
        return generateContent.processGpt(keyWords,contentLength);
    }

}
