package com.weile.client.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.pqc.asn1.SABERPrivateKey;

import java.io.Serializable;

/**
 * @Author: xwl
 * @Date: 2024/5/20 17:34
 * @Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TdkGenerateResp implements Serializable {
    private String errcode;
    private String errmsg;
    private String data;
}
