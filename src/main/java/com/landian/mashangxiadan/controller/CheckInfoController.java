package com.landian.mashangxiadan.controller;

import com.landian.mashangxiadan.pojo.CheckInfo;
import com.landian.mashangxiadan.pojo.CheckInfoVo;
import com.landian.mashangxiadan.service.CheckInfoService;
import com.landian.mashangxiadan.utils.PageRequest;
import com.landian.mashangxiadan.utils.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/26 22:39
 */
@Controller
@RequestMapping("/check")
public class CheckInfoController {
    /**
     * 日志记录
     */
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    CheckInfoService checkInfoService;

    /**
     * 根据参数看看进行评价
     * @param decide
     * @return
     */
    @RequestMapping("/listAllByKey")
    @ResponseBody
    public List<CheckInfo> listAll(@RequestParam("decide") String decide){
        List<CheckInfo> list = new ArrayList<>();

        int flag = Integer.valueOf(decide);
        if(flag==0){
            list = checkInfoService.listAllByDay();
            for(CheckInfo checkInfo :list){
                checkInfo.setStudent_name(checkInfo.getUser_name());
                checkInfo.setStudent_score(checkInfo.getEffective_nums());
                checkInfo.setStudent_num(checkInfo.getUser_id());
            }
        }else if(flag==1){
            list = checkInfoService.listAllByMonth();
            for(CheckInfo checkInfo :list){
                checkInfo.setStudent_name(checkInfo.getUser_name());
                checkInfo.setStudent_score(checkInfo.getTotal_effect_nums());
                checkInfo.setStudent_num(checkInfo.getUser_id());
            }
        }else{
            list = checkInfoService.listAll();
        }

        logger.info("查看考核记录");

        return list;
    }
    /**
     * 分页查询
     * @param num
     * @param size
     * @return
     */
    @RequestMapping(value="/findMonthPage")
    @ResponseBody
    public List<CheckInfo> findMonthPage(@RequestParam(value = "page",required = false) int num,
                           @RequestParam(value = "limit",required = false)int size) {
        PageRequest pageQuery = new PageRequest();
        pageQuery.setPageNum(num);
        pageQuery.setPageSize(size);
        List<CheckInfo> pageResult = checkInfoService.getMonthInfo(pageQuery);
        for(CheckInfo checkInfo :pageResult){
            checkInfo.setStudent_name(checkInfo.getUser_name());
            checkInfo.setStudent_score(checkInfo.getTotal_effect_nums());
            checkInfo.setStudent_num(checkInfo.getUser_id());
        }
        return pageResult;
    }

    /**
     * 分页查询
     * @param num
     * @param size
     * @return
     */
    @RequestMapping(value="/findDayPage")
    @ResponseBody
    public List<CheckInfo> findDayPage(@RequestParam(value = "page",required = false) int num,
                                    @RequestParam(value = "limit",required = false)int size) {
        PageRequest pageQuery = new PageRequest();
        pageQuery.setPageNum(num);
        pageQuery.setPageSize(size);
        List<CheckInfo> pageResult = checkInfoService.selectDayPage(pageQuery);
        for(CheckInfo checkInfo :pageResult){
            checkInfo.setStudent_name(checkInfo.getUser_name());
            checkInfo.setStudent_score(checkInfo.getEffective_nums());
            checkInfo.setStudent_num(checkInfo.getUser_id());
        }
        return pageResult;
    }

}
