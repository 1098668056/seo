package com.weile.controller.v1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: xwl
 * @Date: 2024/5/14 13:19
 * @Description:
 **/
@Controller
public class HtmlViewController {
    @GetMapping("/seo/{path}")
    public String getHtml(Model model, @PathVariable (value = "path") String url){
        return url;
    }
}
