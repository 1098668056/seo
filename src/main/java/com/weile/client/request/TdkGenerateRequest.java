package com.weile.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xwl
 * @Date: 2024/5/20 17:30
 * @Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TdkGenerateRequest {
    private String keywords;
    private String adverb;
}
