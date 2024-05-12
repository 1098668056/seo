package com.weile.client.request;

import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/12 18:21
 * @Description:
 **/
public class GptParamRequest  {
    private String model = "gpt-3.5-turbo";
    private List<ContentRequest> messages;
    private Boolean safe_mode = false;

    public GptParamRequest() {
    }

    public GptParamRequest(String model, List<ContentRequest> messages, Boolean safe_mode) {
        this.model = model;
        this.messages = messages;
        this.safe_mode = safe_mode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ContentRequest> getMessages() {
        return messages;
    }

    public void setMessages(List<ContentRequest> messages) {
        this.messages = messages;
    }

    public Boolean getSafe_mode() {
        return safe_mode;
    }

    public void setSafe_mode(Boolean safe_mode) {
        this.safe_mode = safe_mode;
    }

    @Override
    public String toString() {
        return "GptParamRequest{" +
                "model='" + model + '\'' +
                ", messages=" + messages +
                ", safe_mode=" + safe_mode +
                '}';
    }
}
