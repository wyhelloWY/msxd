package com.landian.mashangxiadan.mapper;

import com.landian.mashangxiadan.pojo.QuestAndAnswerInfo;
import com.landian.mashangxiadan.pojo.UserInfo;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/22 14:55
 */
@Mapper
public interface QuestAndAnswerMapper {
    /**
     * 查询所有问题以及回答
     * @return
     */
    List<QuestAndAnswerInfo> listAll();

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
     * @param answer_id
     * @return
     */
    List<QuestAndAnswerInfo> listByAnswer(int answer_id);

    /**
     * 查询单个问题详情
     * @param id
     * @return
     */
    QuestAndAnswerInfo selectOne(int id);
    /**
     * 查询所有回答通过关键字
     * @param key
     * @return
     */
    List<QuestAndAnswerInfo> listBykeyWord(String key);

    /**
     * 提出问题
     * @param quest_id
     * @param quest_content
     * @param quest_date
     * @return
     */
    int insertQuest(int quest_id, String quest_content, Date quest_date);

    /**
     * 回答问题
     * @param id
     * @param answer_id
     * @param answer_content
     * @param answer_date
     * @return
     */
    int insertAnswer(int id,int answer_id, String answer_content, Date answer_date);

    /**
     * 问题状态是否解决
     * @param id
     * @param quest_statue
     * @return
     */
    int updateStatue(int id,int quest_statue);

    /**
     * 查询单个用户所有未被评价的问题以及回答
     * @param quest_id
     * @return
     */
    List<QuestAndAnswerInfo> selectQuestStatue(int quest_id);

    /**
     * 对当前问题进行锁定提交
     * @param id
     * @param  answer_id
     * @param quest_statue 6为此问题已经被锁定
     * @return
     */
    int updateAnswerId(int id,int answer_id,int quest_statue);

    /**
     * 查询单个问题的状态
     * @param id
     * @return
     */
    int selectOneQuestStatue(int id);


    List<QuestAndAnswerInfo> selectPage();

    /**
     * 删除id
     * @param id
     * @return
     */
    int deleteQuest(int id);
}
