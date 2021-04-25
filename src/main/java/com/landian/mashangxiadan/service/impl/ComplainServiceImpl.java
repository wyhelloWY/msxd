package com.landian.mashangxiadan.service.impl;

import com.landian.mashangxiadan.mapper.ComplainMapper;
import com.landian.mashangxiadan.pojo.ComplainInfo;
import com.landian.mashangxiadan.service.ComplainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/24 20:13
 */
@Service
public class ComplainServiceImpl implements ComplainService {
    @Autowired
    ComplainMapper complainMapper;
    /**
     * 日志记录
     */
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());
    /**
     * 查询所有投诉记录
     * @return
     */
    @Override
    public List<ComplainInfo> listAll() {
        List<ComplainInfo> complainInfos = new ArrayList<>();
        try {
            complainInfos = complainMapper.listAll();
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询所有投诉记录出现异常");
        }
        return complainInfos;
    }

    /**
     * 查询一个用户id的所有投诉记录
     * @param user_id
     * @return
     */
    @Override
    public List<ComplainInfo> listById(int user_id) {
        List<ComplainInfo> complainInfos = new ArrayList<>();
        try {
            complainInfos = complainMapper.listById(user_id);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询单个用户的所有投诉记录出现异常");
        }
        return complainInfos;
    }

    /**
     * 插入一条投诉记录
     * @param user_id
     * @param complain_content
     * @param complain_answer_id
     * @param complain_date
     * @return
     */
    @Override
    public boolean insertComplain(int user_id, String complain_content, int complain_answer_id, Date complain_date) {
        int flag = 0;
        try {
            flag = complainMapper.insertComplain(user_id,complain_content,complain_answer_id,complain_date);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("插入投诉记录出现异常");
        }
        if(flag>0){
            return true;
        }else{
            return false;
        }

    }
}
