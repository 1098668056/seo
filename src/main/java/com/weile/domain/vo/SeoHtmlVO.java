package com.weile.domain.vo;

import com.weile.domain.SeoHtml;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: xwl
 * @Date: 2024/5/20 21:24
 * @Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SeoHtmlVO extends SeoHtml implements Serializable {
    private Long source;
    private String errorPath;
}
