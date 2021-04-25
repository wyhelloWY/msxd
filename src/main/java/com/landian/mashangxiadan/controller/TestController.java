package com.landian.mashangxiadan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yu W
 * @date 2020/9/21 21:01
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String hello(){
        return "Test Success";
    }
}
