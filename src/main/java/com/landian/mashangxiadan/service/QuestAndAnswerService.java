package com.landian.mashangxiadan.service;

import com.landian.mashangxiadan.pojo.NormalQuestionInfo;
import com.landian.mashangxiadan.pojo.QuestAndAnswerInfo;
import com.landian.mashangxiadan.utils.PageRequest;
import com.landian.mashangxiadan.utils.PageResult;

import java.util.Date;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/22 22:40
 */
public interface QuestAndAnswerService {
    /**
     * 查询所有问题以及回答
     * @return
     */
    List<QuestAndAnswerInfo> listAll();

    /**
     * 十个常规问题
     * @return
     */
    List<NormalQuestionInfo> listAllNormalQuestion();

    /**
     * 通过关键字查询十个常规问题
     * @param key
     * @return
     */
    List<NormalQuestionInfo> listAllNormalBykeyWord(String key);

    /**
     * 查询常规问题详情
     * @param id
     * @return
     */
    NormalQuestionInfo selectNormalQuest(int id);
    /**
     * 查询所有未被回答的问题
     * @return
     */
    List<QuestAndAnswerInfo> listByAnswerNull();

    /**
     * 查询同一个用户提出的所有问题
     * @param quest_id
     * @return
     */
    List<QuestAndAnswerInfo> listByQuest(int quest_id);

    /**
     * 查询同一个用户回答的问题
     * @param Answer_id
     * @return
     */
    List<QuestAndAnswerInfo> listByAnswer(int Answer_id);

    /**
     * 查询所有回答通过关键字
     * @param key
     * @return
     */
    List<QuestAndAnswerInfo> listBykeyWord(String key);

    /**
     * 查询单个问题详情
     * @param id
     * @return
     */
    QuestAndAnswerInfo selectOne(int id);
    /**
     * 提出问题
     * @param quest_id
     * @param quest_content
     * @param quest_date
     * @return
     */
    boolean insertQuest(int quest_id, String quest_content, Date quest_date);

    /**
     * 回答问题
     * @param id
     * @param answer_id
     * @param answer_content
     * @param answer_date
     * @return
     */
    boolean insertAnswer(int id,int answer_id, String answer_content, Date answer_date);

    /**
     * 问题状态是否解决
     * @param id
     * @param quest_statue
     * @return
     */
    boolean updateStatue(int id,int quest_statue);

    /**
     * 查询单个用户提出的所有未被评价的问题
     * @param quest_id
     * @return
     */
    List<QuestAndAnswerInfo> selectQuestStatue(int quest_id);

    /**
     * 锁定问题状态
     * @param id
     * @param answer_id
     * @param quest_statue
     * @return
     */
    int updateAnswerId(int id,int answer_id,int quest_statue);

    /**
     * 查询单个问题的状态
     * @param id
     * @return
     */
    int selectOneQuestStatue(int id);
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
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    List<QuestAndAnswerInfo> getPageInfo(PageRequest pageRequest);

    /**
     * 常规问题分页
     * @param pageRequest
     * @return
     */
    List<NormalQuestionInfo> getNormalInfo(PageRequest pageRequest);
    /**
     * 删除id
     * @param id
     * @return
     */
    int deleteQuest(int id);


    /**
     * 插入常规问答
     * @param normalQuestionInfo
     * @return
     */
    int insertNormal(NormalQuestionInfo normalQuestionInfo);

    /**
     * 修改
     * @param normalQuestionInfo
     * @return
     */
    int updateNormal(NormalQuestionInfo normalQuestionInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteNormal(int id);
}
