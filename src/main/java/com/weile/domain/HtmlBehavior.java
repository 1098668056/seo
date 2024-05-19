package com.weile.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: xwl
 * @Date: 2024/5/19 15:22
 * @Description:
 **/
@Entity
@Table(name = "behavior")
public class HtmlBehavior implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long htmlId;
    private Long clickNum;
    private Long showNum;
    private String uaAgent;
    private String ip;

    public HtmlBehavior() {
    }

    public HtmlBehavior(Long id,Long htmlId, Long clickNum, Long showNum, String uaAgent, String ip) {
        this.htmlId = htmlId;
        this.clickNum = clickNum;
        this.showNum = showNum;
        this.uaAgent = uaAgent;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHtmlId() {
        return htmlId;
    }

    public void setHtmlId(Long htmlId) {
        this.htmlId = htmlId;
    }

    public Long getClickNum() {
        return clickNum;
    }

    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
    }

    public Long getShowNum() {
        return showNum;
    }

    public void setShowNum(Long showNum) {
        this.showNum = showNum;
    }

    public String getUaAgent() {
        return uaAgent;
    }

    public void setUaAgent(String uaAgent) {
        this.uaAgent = uaAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
