package com.weile.controller.v1;

import com.weile.domain.User;
import com.weile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xwl
 * @Date: 2024/5/12 11:44
 * @Description:
 **/
@RestController
@RequestMapping("/test")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/getUser")
    public List<User>  getUserinfo(){
        return userRepository.findAll();
    }
}
