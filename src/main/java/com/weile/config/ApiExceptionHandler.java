package com.weile.config;


import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xwl
 * @Date: 2024/5/16 23:34
 * @Description:
 **/
@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * 简单全局异常返回
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable e) {
        //正常打印错误日志
        e.printStackTrace();
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            return new ResponseEntity<>(apiException.getMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
    @ExceptionHandler(NotLoginException.class)
    public String notLoginException(NotLoginException e, Model model) {
        log.error("用户未登录");
        return "redirect:/";
    }
}
