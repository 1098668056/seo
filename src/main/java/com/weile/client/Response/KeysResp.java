package com.weile.client.Response;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xwl
 * @Date: 2024/5/17 13:48
 * @Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeysResp {
    private String errcode;
    private String errmsg;
    @JsonAlias("data")
    private DataResp data;
}
