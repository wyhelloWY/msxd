package com.landian.mashangxiadan.controller;

import com.landian.mashangxiadan.service.UploadService;
import com.landian.mashangxiadan.service.UserService;
import com.landian.mashangxiadan.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yu W
 * @date 2020/10/13 16:38
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;


    @RequestMapping(value = "/excel",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Map<String,String> upload(MultipartFile file) throws Exception {
        Map<String,String> map = new HashMap<>();
        boolean flag = uploadService.getExcel(file);
        String result = null;
        if(flag){
            map.put("result","success");
        }else{
            map.put("result","error");
        }
        return map;
    }
}
