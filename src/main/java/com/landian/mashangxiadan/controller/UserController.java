package com.landian.mashangxiadan.controller;

import com.landian.mashangxiadan.pojo.ResultMap;
import com.landian.mashangxiadan.pojo.UserInfo;
import com.landian.mashangxiadan.service.UserService;
import com.landian.mashangxiadan.utils.PageRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yu W
 * @date 2020/9/22 22:40
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;



    /**
     * 查询单个用户
     * @param user_id
     * @return
     */
    @RequestMapping("/selectOne")
    @ResponseBody
    public Map<String, Object> getNum(@RequestParam("user_id") String user_id){
        int id = Integer.valueOf(user_id);
        Map<String,Object> map = new HashMap<>();
        UserInfo userInfo = userService.selectUser(id);
        int nums = userInfo.getUser_devote();
        map.put("user_devote",nums);
        return map;
    }

    /**
     * 修改单个用户
     * @param id
     * @return
     */
    @RequestMapping("/selectUserById")
    public String userUpdate(@RequestParam(value = "id") String id, Model model){
        int user = Integer.valueOf(id);
        Map<String,Object> map = new HashMap<>();
        UserInfo userInfo = userService.selectUserById(user);
        model.addAttribute("user",userInfo);
        return "user-edit";
    }

    /**
     *
     * @param id
     * @param user_id
     * @param user_name
     * @param user_pass
     * @param user_academy
     * @param user_devote
     * @param user_type
     * @return
     */
    @PostMapping(value = "/updateUser")
    @ResponseBody
    public Map<String,String> updateUser(@RequestParam("id") String id,
                                         @RequestParam("user_id") String user_id,
                                         @RequestParam("user_name") String user_name,
                                         @RequestParam("user_pass") String user_pass,
                                         @RequestParam("user_academy") String user_academy,
                                         @RequestParam("user_devote") String user_devote,
                                         @RequestParam("user_type") String user_type){
        Map<String,String> map = new HashMap<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Integer.valueOf(id));
        userInfo.setUser_id(Integer.valueOf(user_id));
        userInfo.setUser_name(user_name);
        userInfo.setUser_pass(user_pass);
        userInfo.setUser_academy(user_academy);
        userInfo.setUser_devote(Integer.valueOf(user_devote));
        userInfo.setUser_type(Integer.valueOf(user_type));
        int flag = userService.updateManageUser(userInfo);
        if(flag>0){
                map.put("result","success");
        }else{
            map.put("result","error");
        }
        return map;
    }
    /**
     * 修改密码
     * @param user_id
     * @param user_pass
     * @return
     */
    @RequestMapping("/updatePass")
    @ResponseBody
    public Map<String,String> updatePass(@RequestParam("user_id") String user_id,
                                         @RequestParam("user_pass") String user_pass){

        Map<String,String> map = new HashMap<>();
        int id = Integer.valueOf(user_id);
        boolean flag = userService.updateUserPass(user_pass,id);
        if(flag==true){
            map.put("result","success");
        }else{
            map.put("result","error");
        }
        return map;
    }

    /**
     * 分页查询
     * @param num
     * @param size
     * @return
     */
    @RequestMapping(value="/findPage")
    @ResponseBody
    public List<UserInfo> findPage(@RequestParam(value = "page",required = false) int num,
                              @RequestParam(value = "limit",required = false)int size,
                                   @RequestParam(value = "key",required = false)String key) {
        List<UserInfo>  userInfos = new ArrayList<>();
        if(key ==null || key ==""){
            PageRequest pageQuery = new PageRequest();
            pageQuery.setPageNum(num);
            pageQuery.setPageSize(size);
            userInfos = userService.getUserInfo(pageQuery);

        }else{
            userInfos= userService.listBykeyWord(key);
        }

        return userInfos;
    }


    @RequestMapping(value="/deleteUser")
    @ResponseBody
    public ResultMap deleteUser(@RequestParam(value = "id",required = false)int id){
        ResultMap map  = new ResultMap();
        int flag = userService.deleteUser(id);
        if(flag>0){

            map.setCode(200);
            map.setMsg("成功");
        }else{
            map.setCode(201);
            map.setMsg("失败");
        }
        return map;
    }

    /**
     *
     * @param user_id
     * @param user_name
     * @param user_pass
     * @param user_academy
     * @param user_devote
     * @param user_type
     * @return
     */
    @PostMapping(value = "/addUser")
    @ResponseBody
    public Map<String,String> addUser(@RequestParam("user_id") String user_id,
                                         @RequestParam("user_name") String user_name,
                                         @RequestParam("user_pass") String user_pass,
                                         @RequestParam("user_academy") String user_academy,
                                         @RequestParam("user_devote") String user_devote,
                                         @RequestParam("user_type") String user_type){
        Map<String,String> map = new HashMap<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_id(Integer.valueOf(user_id));
        userInfo.setUser_name(user_name);
        userInfo.setUser_pass(user_pass);
        userInfo.setUser_sex(1);
        userInfo.setUser_academy(user_academy);
        userInfo.setUser_devote(Integer.valueOf(user_devote));
        userInfo.setUser_type(Integer.valueOf(user_type));
        boolean flag = userService.insertUser(userInfo);
        if(flag){
            map.put("result","success");
        }else{
            map.put("result","error");
        }
        return map;
    }

    @RequestMapping("/toAdd")
    public String toUser(){
        return "user-add";
    }


    /**
     * 登录接口
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,String> login(String user_name,String user_pass){
        System.out.println(user_name+user_pass);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user_name,user_pass);
        Map<String,String> map = new HashMap<>();
        try {
            subject.login(token);
            if(user_name.equals("admin")&&user_pass.equals("123456")){
                map.put("result","success");
            }else{
                map.put("result","error");
            }
        }catch (IncorrectCredentialsException e){
            map.put("result","error");
        }
        return map;
    }
}
