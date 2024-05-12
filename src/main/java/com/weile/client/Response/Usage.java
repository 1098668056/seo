package com.weile.client.Response;

/**
 * @Author: xwl
 * @Date: 2024/5/12 21:23
 * @Description:
 **/
public class Usage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
    private int pre_token_count;
    private int pre_total;
    private int adjust_total;
    private int final_total;
    public void setPrompt_tokens(int prompt_tokens) {
        this.prompt_tokens = prompt_tokens;
    }
    public int getPrompt_tokens() {
        return prompt_tokens;
    }

    public void setCompletion_tokens(int completion_tokens) {
        this.completion_tokens = completion_tokens;
    }
    public int getCompletion_tokens() {
        return completion_tokens;
    }

    public void setTotal_tokens(int total_tokens) {
        this.total_tokens = total_tokens;
    }
    public int getTotal_tokens() {
        return total_tokens;
    }

    public void setPre_token_count(int pre_token_count) {
        this.pre_token_count = pre_token_count;
    }
    public int getPre_token_count() {
        return pre_token_count;
    }

    public void setPre_total(int pre_total) {
        this.pre_total = pre_total;
    }
    public int getPre_total() {
        return pre_total;
    }

    public void setAdjust_total(int adjust_total) {
        this.adjust_total = adjust_total;
    }
    public int getAdjust_total() {
        return adjust_total;
    }

    public void setFinal_total(int final_total) {
        this.final_total = final_total;
    }
    public int getFinal_total() {
        return final_total;
    }
}
