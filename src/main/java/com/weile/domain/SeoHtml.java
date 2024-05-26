package com.weile.domain;

import lombok.Builder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xwl
 * @Date: 2024/5/12 15:42
 * @Description:
 **/
@Entity
@Table(name = "html")
@DynamicUpdate
@SelectBeforeUpdate
public class SeoHtml implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title = "暂无文章";
    private String description;
    private String keywords;
    private String content;
    private String url = "http://www.mirror-era.cn";
    @Column(updatable = false)
    private String fileName;
    @Column(updatable = false)
    private Date createTime;
    private Long queryCount;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(Long queryCount) {
        this.queryCount = queryCount;
    }

    public SeoHtml(Long id, String title, String description, String keywords, String content, String url, String fileName, Date createTime, Long queryCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.content = content;
        this.url = url;
        this.fileName = fileName;
        this.createTime = createTime;
        this.queryCount = queryCount;
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
                ", createTime=" + createTime +
                ", queryCount=" + queryCount +
                '}';
    }
}
