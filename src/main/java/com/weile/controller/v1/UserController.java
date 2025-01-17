package com.weile.controller.v1;


import cn.dev33.satoken.stp.StpUtil;
import com.weile.config.ApiException;
import com.weile.domain.SeoHtml;
import com.weile.domain.SysMenu;
import com.weile.domain.User;
import com.weile.domain.vo.MenuShowVO;
import com.weile.repository.SysMeunRepository;
import com.weile.repository.UserRepository;
import com.weile.service.SeoHtmlService;
import com.weile.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/12 11:44
 * @Description:
 **/
@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Resource
    private UserService userService;
    @Resource
    private SeoHtmlService seoHtmlService;
    @GetMapping("/login/index")
    public String  loginIndex(Model model){
        return "admin/login";
    }
    @GetMapping("/index_v1.html")
    public String  loginView(Model model){
        return "admin/index_v1";
    }
    @GetMapping("/home.html")
    public String  homeView(Model model){
        List<MenuShowVO> menuList = userService.getMenuList();
        model.addAttribute("menus",menuList);
        return "admin/home";
    }
    @RequestMapping("/login")
    public String  loginIn(@RequestParam String username, @RequestParam String password, Model model){
        if (StringUtils.isBlank(username) && StringUtils.isBlank(password)){return "admin/login";}
        User user = userRepository.findByNameIsAndPasswordIs(username,password).orElseThrow(()->new ApiException("name or password is error"));
        model.addAttribute("user",user);
        StpUtil.login(user.getId());
        return "redirect:home.html";
    }
    @GetMapping("/logOut")
    public String  logOut(Model model){
        StpUtil.logout();
        return "admin/login";
    }
}
