package com.weile.client.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/21 10:44
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KiMiResp implements Serializable {
    private String id;
    private String object;
    private Long created;
    private String model;
    private Usage usage;
    private List<Choices> choices;
}
