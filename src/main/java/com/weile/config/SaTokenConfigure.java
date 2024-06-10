package com.weile.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> {
            //校验是否登录
            SaRouter.match("/**").check(o -> StpUtil.checkLogin());
        })).excludePathPatterns("/count/**","/admin/login/index","/static/*","/admin/login","/static/css/**", "/static/js/**", "/static/img/**", "/static/fonts/**", "/static/docs/**","/seo/**", "/favicon.ico");
    }

    /**
     * 获取所有的资源url进行拦截鉴权
     * @return
     */
    public List<String> getPath(){
      return null;
    }

}
