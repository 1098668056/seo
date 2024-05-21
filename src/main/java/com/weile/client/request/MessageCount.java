package com.weile.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @Author: xwl
 * @Date: 2024/5/21 10:39
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageCount implements Serializable {
    private  String role;
    private  String content;
}
