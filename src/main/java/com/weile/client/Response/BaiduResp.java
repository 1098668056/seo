package com.weile.client.Response;

/**
 * @Author: xwl
 * @Date: 2024/5/16 13:28
 * @Description:
 **/
public class BaiduResp {
    private Long remain;
    private Long success;

    public BaiduResp(Long remain, Long success) {
        this.remain = remain;
        this.success = success;
    }

    public Long getRemain() {
        return remain;
    }

    public void setRemain(Long remain) {
        this.remain = remain;
    }

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }
}
