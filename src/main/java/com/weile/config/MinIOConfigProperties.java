package com.weile.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;


@ConfigurationProperties(prefix = "minio")  // 文件上传 配置前缀file.oss
public class MinIOConfigProperties implements Serializable {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String endpoint;
    private String readPath;
    private String aliasPath;

    public MinIOConfigProperties(String accessKey, String secretKey, String bucket, String endpoint, String readPath, String aliasPath) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucket = bucket;
        this.endpoint = endpoint;
        this.readPath = readPath;
        this.aliasPath = aliasPath;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getReadPath() {
        return readPath;
    }

    public void setReadPath(String readPath) {
        this.readPath = readPath;
    }

    public String getAliasPath() {
        return aliasPath;
    }

    public void setAliasPath(String aliasPath) {
        this.aliasPath = aliasPath;
    }

    public MinIOConfigProperties() {
    }
}
