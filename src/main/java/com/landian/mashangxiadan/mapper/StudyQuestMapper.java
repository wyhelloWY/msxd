package com.landian.mashangxiadan.mapper;

import com.landian.mashangxiadan.pojo.QuestAndAnswerInfo;
import com.landian.mashangxiadan.pojo.StudyQuestInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/10/9 14:54
 */
@Mapper
public interface StudyQuestMapper {
    /**
     * 查询所有问题以及回答
     * @return
     */
    List<StudyQuestInfo> listAll();

    /**
     * 查询所有未被回答的问题
     * @return
     */
    List<StudyQuestInfo> listByAnswerNull();

    /**
     * 查询同一个用户提出的所有问题
     * @param study_id
     * @return
     */
    List<StudyQuestInfo> listByQuest(int study_id);

    /**
     * 查询同一个用户回答的问题
     * @param study_answer_id
     * @return
     */
    List<StudyQuestInfo> listByAnswer(int study_answer_id);

    /**
     * 查询单个问题详情
     * @param id
     * @return
     */
    StudyQuestInfo selectOne(int id);
    /**
     * 查询所有回答通过关键字
     * @param key
     * @return
     */
    List<StudyQuestInfo> listBykeyWord(String key);

    /**
     * 提出问题
     * @param study_id
     * @param study_content
     * @param study_date
     * @return
     */
    int insertQuest(int study_id, String study_content, Date study_date);

    /**
     * 回答问题
     * @param id
     * @param study_answer_id
     * @param study_answer_content
     * @param study_answer_date
     * @return
     */
    int insertAnswer(int id,int study_answer_id, String study_answer_content, Date study_answer_date);

    /**
     * 问题状态是否解决
     * @param id
     * @param study_quest_statue
     * @return
     */
    int updateStatue(int id,int study_quest_statue);



}
