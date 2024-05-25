package com.weile.controller.v1;

import com.weile.client.PROMPTENUM;
import com.weile.domain.HtmlBehavior;
import com.weile.domain.vo.SeoHtmlVO;
import com.weile.service.SeoHtmlService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SeoHtmlController {
    @Resource
    private SeoHtmlService seoHtmlService;

    @GetMapping("/seo/index")
    public String getSeoHtmlList(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum, Model model){
        Page<SeoHtmlVO> allSeoHtml = seoHtmlService.getAllSeoHtml(pageNum);
        model.addAttribute("seoHtmlList",allSeoHtml.getContent());
        model.addAttribute("currentPage",(pageNum));
        model.addAttribute("totalPage",(allSeoHtml.getTotalPages()-1));
        //todo 增加推荐字段
        model.addAttribute("topFives",null);
        model.addAttribute("seoListRandom",seoHtmlService.getAllSeoHtmlRandom());
        model.addAttribute("latest",seoHtmlService.getLatestSeoHtml());
        return "desk/index";
    }
    @GetMapping("/seo/tage")
    public String getSeoHtmlList(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
                                 @RequestParam(value = "keyWords",defaultValue = "卡盟") String keyWords, Model model){
        Page<SeoHtmlVO> allSeoHtml = seoHtmlService.getAllSeoHtmlBykeyWords(pageNum,keyWords);
        model.addAttribute("seoHtmlList",allSeoHtml.getContent());
        model.addAttribute("currentPage",(pageNum));
        model.addAttribute("totalPage",(allSeoHtml.getTotalPages()-1));
        return "desk/tage";
    }

    /**
     * 统计页面浏览量
     * @param id
     */
    @GetMapping("/count/click/{id}")
    @ResponseBody
    @CrossOrigin
    public String count(@PathVariable(value = "id" )Long id, HttpServletRequest request){
        String userAgent = request.getHeader(PROMPTENUM.USER_AGENT.getName());
        String ip = request.getHeader(PROMPTENUM.REAL_IP.getName());
        seoHtmlService.saveBehavior(new HtmlBehavior(null,id,1L,1L,userAgent,ip));
        return PROMPTENUM.WEB_SITE_URL.getName();
    }

    @GetMapping("/seo/count/{id}")
    public void queryCount(@PathVariable(value = "id" )Long id){
        seoHtmlService.updateSeo(id);
    }


    @GetMapping("/article/list")
    public String getArticleList(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum, Model model){
        Page<SeoHtmlVO> allSeoHtml = seoHtmlService.getAllSeoHtml(pageNum);
        model.addAttribute("seoHtmlList",allSeoHtml.getContent());
        model.addAttribute("currentPage",(pageNum));
        model.addAttribute("totalPage",(allSeoHtml.getTotalPages()-1));
        return "desk/article";
    }
}
