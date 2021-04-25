package com.landian.mashangxiadan.service;

import com.landian.mashangxiadan.pojo.CheckInfo;
import com.landian.mashangxiadan.pojo.UserInfo;
import com.landian.mashangxiadan.utils.PageRequest;
import com.landian.mashangxiadan.utils.PageResult;

import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/26 21:52
 */
public interface CheckInfoService {
    /**
     * 查询所有考核记录
     * @return
     */
    List<CheckInfo> listAll();

    /**
     * 查询排序出今日所有考核分数较高的人
     * @return
     */
    List<CheckInfo> listAllByDay();

    /**
     * 查询排序出总榜考核偏高的人
     * @return
     */
    List<CheckInfo> listAllByMonth();

    /**
     * 插入考核记录
     * @param user_id
     * @param effective_nums
     * @param everyday_nums
     * @param total_effect_nums
     * @param total_nums
     * @return
     */
    boolean insertCheckInfo(int user_id,int effective_nums,int everyday_nums,int total_effect_nums,int total_nums);

    /**
     * 修改考核记录
     * @param user_id
     * @param effective_nums
     * @param everyday_nums
     * @param total_effect_nums
     * @param total_nums
     * @return
     */
    boolean updateCheckInfo(int user_id,int effective_nums,int everyday_nums,int total_effect_nums,int total_nums);

    /**
     * 修改所有每日数据
     */
     void updateAllDay();

    /**
     * 关于考核记录插入修改的
     * @param user_id
     */
    void insertAndUpdateCheck(int user_id);


    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);

    /**
     * 月分页
     * @param pageRequest
     * @return
     */
    List<CheckInfo> getMonthInfo(PageRequest pageRequest);

    /**
     * 分页查询
     * @param pageRequest
     * @return
     */
    List<CheckInfo> selectDayPage(PageRequest pageRequest);
}
