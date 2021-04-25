package com.landian.mashangxiadan.controller;

import com.landian.mashangxiadan.pojo.ComplainInfo;
import com.landian.mashangxiadan.service.ComplainService;
import com.landian.mashangxiadan.utils.GetDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yu W
 * @date 2020/9/24 20:18
 */
@Controller
@RequestMapping("/complain")
public class ComplainController {
    @Autowired
    ComplainService complainService;

    /**
     * 查询所有投诉记录
     * @return
     */
    @RequestMapping("/listAll")
    @ResponseBody
    public List<ComplainInfo> listAll(){
        List<ComplainInfo> complainInfos = new ArrayList<>();
        complainInfos = complainService.listAll();
        return complainInfos;
    }

    /**
     * 根据id查询投诉信息
     * @param user_id
     * @return
     */
    @RequestMapping("/listById")
    @ResponseBody
    public List<ComplainInfo> listAllById(@RequestParam("user_id")String user_id){
        int id = Integer.valueOf(user_id);
        List<ComplainInfo> complainInfos = new ArrayList<>();
        complainInfos = complainService.listById(id);
        return complainInfos;
    }

    /**
     * 插入一条投诉信息
     * @param user_id
     * @param complain_content
     * @param complain_answer_id
     * @return
     */
    @RequestMapping("/insertComplainInfo")
    @ResponseBody
    public Map<String,String> insertComplainInfo(@RequestParam("user_id")String user_id,
                                                 @RequestParam("complain_content")String complain_content,
                                                 @RequestParam("complain_answer_id")String complain_answer_id){
        Map<String,String> map = new HashMap<>();
        int id = Integer.valueOf(user_id);
        int answer_id = Integer.valueOf(complain_answer_id);
        boolean flag = complainService.insertComplain(id,complain_content,answer_id, GetDateUtil.getDateTime());
        if(flag==true){
            map.put("result","success");
        }else{
            map.put("result","error");
        }
        return map;
    }
}
