package com.weile.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xwl
 * @Date: 2024/5/17 13:30
 * @Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeysRequest {
    private String keyword;
    private int page_index;
    private int page_size;

}
