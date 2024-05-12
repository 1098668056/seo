package com.weile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WeileSeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeileSeoApplication.class, args);
    }

}
