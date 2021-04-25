package com.landian.mashangxiadan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Yu W
 * @date 2020/11/12 22:41
 */
@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/center")
    public String toUserIndex(){
        return "center";
    }
    @RequestMapping("/dayRankIndex")
    public String todayRank(){
        return "dayRank";
    }
    @RequestMapping("/AllIndex")
    public String tomonthRank(){
        return "monthRank";
    }

    @RequestMapping("/userIndex")
    public String userIndex(){
        return "userindex";
    }

    @RequestMapping("/monthIndex")
    public String month(){
        return "monthrank";
    }

    @RequestMapping("/dayIndex")
    public String dayIndex(){
        return "dayrank";
    }

    @RequestMapping("/questIndex")
    public String questIndex(){
        return "questions";
    }

    @RequestMapping("/normalIndex")
    public String normalIndex(){
        return "normal";
    }

    @RequestMapping("/toNormalAdd")
    public String toNormalAdd(){
        return "normal-add";
    }

}
