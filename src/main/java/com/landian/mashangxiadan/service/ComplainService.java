package com.landian.mashangxiadan.service;

import com.landian.mashangxiadan.pojo.ComplainInfo;

import java.util.Date;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/24 20:10
 */
public interface ComplainService {
    /**
     * 查询所有投诉记录
     * @return
     */
    List<ComplainInfo> listAll();

    /**
     * 查询一个用户投诉别人的所有记录
     * @param user_id
     * @return
     */
    List<ComplainInfo> listById(int user_id);

    /**
     * 插入一条投诉
     * @param user_id
     * @param complain_content
     * @param complain_answer_id
     * @param complain_date
     * @return
     */
    boolean insertComplain(int user_id, String complain_content, int complain_answer_id, Date complain_date);
}
