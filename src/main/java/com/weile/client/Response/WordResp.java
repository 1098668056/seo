package com.weile.client.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xwl
 * @Date: 2024/5/17 13:57
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordResp {
    private String keyword;
    private int index;
    @JsonProperty("mobile_index")
    private int mobileIndex;
    @JsonProperty("haosou_index")
    private int haosouIndex;
    @JsonProperty("douyin_index")
    private int douyinIndex;
    @JsonProperty("toutiao_index")
    private int toutiaoIndex;
    @JsonProperty("long_keyword_count")
    private int longKeywordCount;
    @JsonProperty("bidword_company_count")
    private int bidwordCompanyCount;
    @JsonProperty("page_url")
    private String pageUrl;
    @JsonProperty("bidword_kwc")
    private int bidwordKwc;
    @JsonProperty("bidword_pcpv")
    private int bidwordPcpv;
    @JsonProperty("bidword_wisepv")
    private int bidwordWisepv;
    @JsonProperty("sem_reason")
    private String semReason;
    @JsonProperty("sem_price")
    private String semPrice;
    @JsonProperty("sem_recommend_price_avg")
    private double semRecommendPriceAvg;
    @JsonProperty("google_index")
    private int googleIndex;
    @JsonProperty("kuaishou_index")
    private int kuaishouIndex;
    @JsonProperty("weibo_index")
    private int weiboIndex;
}
