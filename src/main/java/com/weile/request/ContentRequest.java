package com.weile.request;

/**
 * @Author: xwl
 * @Date: 2024/5/12 18:23
 * @Description:
 **/
public class ContentRequest {
    private String role;
    private String content;

    public ContentRequest() {
    }

    public ContentRequest(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContentRequest{" +
                "role='" + role + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
