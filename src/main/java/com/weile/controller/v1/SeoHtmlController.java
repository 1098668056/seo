package com.weile.controller.v1;

import com.weile.domain.SeoHtml;
import com.weile.repository.SeoHtmlRepository;
import com.weile.service.SeoHtmlService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class SeoHtmlController {
    @Resource
    private SeoHtmlService seoHtmlService;
    @Resource
    private SeoHtmlRepository seoHtmlRepository;

    @GetMapping("/seo/index")
    public String getSeoHtmlList(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum, Model model){
        Page<SeoHtml> allSeoHtml = seoHtmlService.getAllSeoHtml(pageNum);
        model.addAttribute("seoHtmlList",allSeoHtml.getContent());
        model.addAttribute("currentPage",(pageNum));
        model.addAttribute("totalPage",(allSeoHtml.getTotalPages()-1));
        //todo 增加推荐字段
        model.addAttribute("topFives",null);
        model.addAttribute("seoListRandom",seoHtmlService.getAllSeoHtmlRandom());
        return "index";
    }

    /**
     * 统计页面浏览量
     * @param id
     */
    @GetMapping("/count/click/{id}")
    @ResponseBody
    @CrossOrigin
    public String count(@PathVariable(value = "id" )Long id){
        //todo 加一操作
        System.out.println("id = " + id);

        return "http://www.test1111111111.com";
    }
}
