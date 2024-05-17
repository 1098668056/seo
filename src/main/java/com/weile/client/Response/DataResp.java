package com.weile.client.Response;

import cn.hutool.extra.tokenizer.Word;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/17 13:51
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResp {
    private int total;
    @JsonProperty("page_count")
    private int pageCount;
    @JsonProperty("page_index")
    private int pageIndex;
    @JsonProperty("page_size")
    private int pageSize;
    @JsonAlias("word")
    private List<WordResp> word;
}
