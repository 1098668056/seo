package com.weile.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xwl
 * @Date: 2024/5/25 20:15
 * @Description:
 **/
@Entity
@Table(name = "sys_menu")
public class SysMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String indexs;
    private String url;
    private String icon;
    private Integer parentId;
    private Integer isDeleted;
    private Date createTime;
    private Date updateTime;
    private String updateUser;
    private Integer orderRank;

    public SysMenu() {
    }

    public SysMenu(Long id, String title, String indexs, String url, String icon, Integer parentId, Integer isDeleted, Date createTime, Date updateTime, String updateUser, Integer orderRank) {
        this.id = id;
        this.title = title;
        this.indexs = indexs;
        this.url = url;
        this.icon = icon;
        this.parentId = parentId;
        this.isDeleted = isDeleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.orderRank = orderRank;
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

    public String getIndexs() {
        return indexs;
    }

    public void setIndexs(String indexs) {
        this.indexs = indexs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsDelete() {
        return isDeleted;
    }

    public void setIsDelete(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getOrderRank() {
        return orderRank;
    }

    public void setOrderRank(Integer orderRank) {
        this.orderRank = orderRank;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", indexs='" + indexs + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", parentId=" + parentId +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", orderRank=" + orderRank +
                '}';
    }
}
