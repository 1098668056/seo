package com.weile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/seo/**")
                .addResourceLocations("classpath:/desk");
        registry.addResourceHandler("/admin/**")
                .addResourceLocations("classpath:/admin");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/static/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/static/fonts/**")
                .addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/static/img/**")
                .addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/static/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/favicon.ico");
    }
}