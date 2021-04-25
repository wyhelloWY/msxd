package com.landian.mashangxiadan.mapper;

import com.landian.mashangxiadan.pojo.CheckInfo;
import com.landian.mashangxiadan.pojo.QuestAndAnswerInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/23 22:16
 */
@Mapper
public interface CheckMapper {

    /**
     * 查询所有
     * @return
     */
    List<CheckInfo> listAll();


    /**
     * 查询单日所有考核信息并且排序
     * @return
     */
    List<CheckInfo> selectAllDay();

    /**
     * 查询总榜所有考核记录并且排序
     * @return
     */
    List<CheckInfo> selectAllTotal();

    /**
     * 查询单个考核信息
     * @param user_id
     * @return
     */
    CheckInfo selectOneCheck(int user_id);

    /**
     * 插入所有考核信息
     * @param user_id
     * @param effective_nums
     * @param everyday_nums
     * @param total_effect_nums
     * @param total_nums
     * @return
     */
    int insertCheckInfo(int user_id,int effective_nums,int everyday_nums,int total_effect_nums,int total_nums);

    /**
     * 修改今日考核点
     * @param user_id
     * @param effective_nums
     * @param everyday_nums
     * @param total_effect_nums
     * @param total_nums
     * @return
     */
    int updateCheckInfo(int user_id,int effective_nums,int everyday_nums,int total_effect_nums,int total_nums);

    /**
     * 修改今日考核点数
     * @param user_id
     * @param effective
     * @param everyday_nums
     * @return
     */
    int updateDayCheck(int user_id,int effective,int everyday_nums);

    /**
     * 查询用户是否存在
     * @param user_id
     * @return
     */
    int selectByUserID(int user_id);

    /**
     * 修改所有每日考核数据
     */
    void updateAllDay();

    /**
     * 使所有有效回答次数加1
     * @param user_id
     */
    void updateEveryNumbers(int user_id);

    /**
     * 月排行榜分页
     * @return
     */
    List<CheckInfo> selectPage();

    /**
     * 日排行榜分页
     * @return
     */
    List<CheckInfo> selectDayPage();

}
