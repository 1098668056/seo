package com.weile.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/21 10:37
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KMiRequest implements Serializable {
    private String model;
    private List<MessageCount> messages;
    private Double temperature;
    private Boolean stream = false;
}
