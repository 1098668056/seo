package com.weile.controller.v1;

import com.weile.domain.SeoHtml;
import com.weile.service.SeoHtmlService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class SeoHtmlController {
    @Resource
    private SeoHtmlService seoHtmlService;

    @GetMapping("/seo/index")
    public String getSeoHtmlList(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum, Model model){
        Page<SeoHtml> allSeoHtml = seoHtmlService.getAllSeoHtml(pageNum);
        model.addAttribute("seoHtmlList",allSeoHtml.getContent());
        model.addAttribute("currentPage",(pageNum));
        model.addAttribute("totalPage",(allSeoHtml.getTotalPages()));
        //todo 增加推荐字段
        model.addAttribute("topFives",null);
        model.addAttribute("seoListRandom",seoHtmlService.getAllSeoHtmlRandom());
        return "index";
    }
}
