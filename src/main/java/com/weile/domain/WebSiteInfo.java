package com.weile.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: xwl
 * @Date: 2024/6/16 14:51
 * @Description:
 **/
@Entity
@Table(name = "web_site_info")
public class WebSiteInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String keywords;
    private String description;
    private String indexScript;
    private String innerScript;
    private String url;
    private String count;
    private String host;
    private String templateName;
    private String icp;

    public WebSiteInfo() {
    }

    public WebSiteInfo(Long id, String title, String keywords, String description, String indexScript, String innerScript, String url, String count,String host,String templateName,String icp) {
        this.id = id;
        this.title = title;
        this.keywords = keywords;
        this.description = description;
        this.indexScript = indexScript;
        this.innerScript = innerScript;
        this.url = url;
        this.count = count;
        this.host =host;
        this.templateName =templateName;
        this.icp = icp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndexScript() {
        return indexScript;
    }

    public void setIndexScript(String indexScript) {
        this.indexScript = indexScript;
    }

    public String getInnerScript() {
        return innerScript;
    }

    public void setInnerScript(String innerScript) {
        this.innerScript = innerScript;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }

    public String getIcp() {
        return icp;
    }
}