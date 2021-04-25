package com.landian.mashangxiadan.mapper;

import com.landian.mashangxiadan.pojo.ComplainInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/23 18:34
 */
@Mapper
public interface ComplainMapper {
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
    int insertComplain(int user_id, String complain_content, int complain_answer_id, Date complain_date);
}
