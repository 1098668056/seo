package com.weile.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: xwl
 * @Date: 2024/5/12 15:42
 * @Description:
 **/
@Entity
@Table(name = "html")
public class SeoHtml implements Serializable {
    @Id
    private Long id;
    private String title;
    private String description;
    private String keywords;
    private String content;
    private String url;
    private String fileName;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public SeoHtml(Long id, String title, String description, String keywords, String content, String url, String fileName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.content = content;
        this.url = url;
        this.fileName = fileName;
    }

    public SeoHtml() {
    }

    @Override
    public String toString() {
        return "SeoHtml{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", keywords='" + keywords + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
