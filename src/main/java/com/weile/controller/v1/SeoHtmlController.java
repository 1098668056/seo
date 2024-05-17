package com.weile.controller.v1;

import com.weile.service.SeoHtmlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class SeoHtmlController {
    @Resource
    SeoHtmlService seoHtmlService;

    @GetMapping("/seo/list")
    public String getSeoHtmlList(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum, Model model){
        model.addAttribute("seoHtmlList", seoHtmlService.getAllSeoHtml(pageNum));
        model.addAttribute("currentPage",pageNum);
        return "index";
    }

    @GetMapping("/seo/listRandom")
    public String getSeoHtmlListRandom(Model model){
        Model seoHtmlList = model.addAttribute("seoHtmlList", seoHtmlService.getAllSeoHtmlRandom());
        return "seo_listRandom";
    }
}
