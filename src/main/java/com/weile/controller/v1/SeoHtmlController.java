package com.weile.controller.v1;

import com.weile.config.ApiException;
import com.weile.domain.HtmlBehavior;
import com.weile.domain.SeoHtml;
import com.weile.repository.HtmlBehaviorRepository;
import com.weile.repository.SeoHtmlRepository;
import com.weile.service.SeoHtmlService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@Controller
public class SeoHtmlController {
    @Resource
    private SeoHtmlService seoHtmlService;
    @Resource
    private HtmlBehaviorRepository htmlBehaviorRepository;

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
    public String count(@PathVariable(value = "id" )Long id, HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        String ip = request.getHeader("x-real-ip");
        try {
            htmlBehaviorRepository.save(new HtmlBehavior(null,id,1L,1L,userAgent,ip));
        } catch (Exception e) {
            throw new ApiException("未获取到类型");
        }
        return "http://www.test1111111111.com";
    }
}
