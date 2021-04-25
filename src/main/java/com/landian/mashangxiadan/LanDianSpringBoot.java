package com.landian.mashangxiadan;


import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Yu W
 * @date 2020/9/21 20:59
 */
@MapperScan("com.landian.mashangxiadan.mapper")
@SpringBootApplication
@EnableScheduling
public class LanDianSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(LanDianSpringBoot.class, args);
    }

}
