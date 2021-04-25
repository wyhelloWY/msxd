package com.landian.mashangxiadan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landian.mashangxiadan.mapper.CheckMapper;
import com.landian.mashangxiadan.mapper.UserMapper;
import com.landian.mashangxiadan.pojo.CheckInfo;
import com.landian.mashangxiadan.pojo.UserInfo;
import com.landian.mashangxiadan.service.CheckInfoService;
import com.landian.mashangxiadan.utils.PageRequest;
import com.landian.mashangxiadan.utils.PageResult;
import com.landian.mashangxiadan.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/26 22:07
 */
@Service
public class CheckServiceImpl implements CheckInfoService {

    /**
     * 日志记录
     */
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    CheckMapper checkMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<CheckInfo> listAll() {
        List<CheckInfo> checkInfos = new ArrayList<>();
        try {
            checkInfos = checkMapper.listAll();
            for (CheckInfo checkInfo : checkInfos) {
                String user_name = userMapper.selectUserName(checkInfo.getUser_id());
                checkInfo.setUser_name(user_name);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询所有考核记录出错");
        }

        return checkInfos;
    }

    @Override
    public List<CheckInfo> listAllByDay() {
        List<CheckInfo> checkInfos = new ArrayList<>();
        try {
            checkInfos = checkMapper.selectAllDay();
            for (CheckInfo checkInfo : checkInfos) {
                String user_name = userMapper.selectUserName(checkInfo.getUser_id());
                checkInfo.setUser_name(user_name);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询每日考核记录出错");
        }
        return checkInfos;
    }

    @Override
    public List<CheckInfo> listAllByMonth() {
        List<CheckInfo> checkInfos = new ArrayList<>();
        try {
            checkInfos = checkMapper.selectAllTotal();
            for (CheckInfo checkInfo : checkInfos) {
                String user_name = userMapper.selectUserName(checkInfo.getUser_id());
                checkInfo.setUser_name(user_name);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询每月考核记录出错");
        }
        return checkInfos;
    }

    @Override
    public boolean insertCheckInfo(int user_id, int effective_nums, int everyday_nums, int total_effect_nums,
                                   int total_nums) {
        int flag = checkMapper.selectByUserID(user_id);
        boolean state = false;
        if (flag > 0) {
            checkMapper.updateCheckInfo(user_id, effective_nums, everyday_nums, total_effect_nums, total_nums);
            //修改
        } else {
            int statue = checkMapper.insertCheckInfo(user_id, effective_nums, everyday_nums, total_effect_nums,
                    total_nums);
            if (statue > 0) {
                state = true;
            }
        }
        logger.info("插入考核记录出错");

        return state;
    }

    @Override
    public boolean updateCheckInfo(int user_id, int effective_nums, int everyday_nums, int total_effect_nums,
                                   int total_nums) {
        int flag = checkMapper.selectByUserID(user_id);
        boolean state = false;
        if (flag > 0) {
            int statue = checkMapper.insertCheckInfo(user_id, effective_nums, everyday_nums, total_effect_nums,
                    total_nums);
            if (statue > 0) {
                state = true;
            }
        } else {
            checkMapper.insertCheckInfo(user_id, effective_nums, everyday_nums, total_effect_nums, total_nums);
        }
        return state;
    }

    @Override
    public void updateAllDay() {
        checkMapper.updateAllDay();
    }

    @Override
    public void insertAndUpdateCheck(int user_id) {
        int num =1;
        int flag = checkMapper.selectByUserID(user_id);
        //如果能够查询到这个用户 说明在数据库中存在记录 进行修改
        if(flag>0){
            //使数据自动+1
            checkMapper.updateEveryNumbers(user_id);
        }else{//如果查询不到这个用户 说明在数据库中不存在记录 进行插入
            //插入一次有效的相关回答
            checkMapper.insertCheckInfo(user_id, 1, 1, 1, 1);
        }
        userMapper.updateUserDevote(user_id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, (PageInfo<?>) getMonthInfo(pageRequest));
    }

    @Override
    public List<CheckInfo> getMonthInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<CheckInfo> sysMenus = checkMapper.selectPage();
        for (CheckInfo checkInfo : sysMenus) {
            String user_name = userMapper.selectUserName(checkInfo.getUser_id());
            checkInfo.setUser_name(user_name);
        }
        return sysMenus;
    }

    @Override
    public List<CheckInfo> selectDayPage(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<CheckInfo> sysMenus = checkMapper.selectDayPage();
        for (CheckInfo checkInfo : sysMenus) {
            String user_name = userMapper.selectUserName(checkInfo.getUser_id());
            checkInfo.setUser_name(user_name);
        }
        return sysMenus;
    }

}
