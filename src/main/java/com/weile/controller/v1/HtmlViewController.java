package com.weile.controller.v1;

import com.weile.service.HtmlViewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: xwl
 * @Date: 2024/5/14 13:19
 * @Description:
 **/
@Controller
public class HtmlViewController {
    @Resource
    private HtmlViewService htmlViewService;
    @Deprecated
    @GetMapping("/seo/{path}")
    public String getHtml(Model model, @PathVariable (value = "path") String url){
        return url;
    }
    @GetMapping(value = "/html/{fileName}/{key}")
    @ResponseBody
    public String generateSeoHtml(@PathVariable("fileName") String fileName, @PathVariable("key") String keyWord){
        System.out.println(keyWord);
        return htmlViewService.onlyHtml(fileName,"",keyWord);
    }
}
