package com.landian.mashangxiadan.service;

import com.landian.mashangxiadan.pojo.NormalQuestionInfo;
import com.landian.mashangxiadan.pojo.QuestAndAnswerInfo;
import com.landian.mashangxiadan.pojo.StudyQuestInfo;

import java.util.Date;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/10/9 20:19
 */
public interface StudyQuestService {
    /**
     * 查询所有问题以及回答
     * @return
     */
    List<StudyQuestInfo> listAll();


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
     * 查询所有回答通过关键字
     * @param key
     * @return
     */
    List<StudyQuestInfo> listBykeyWord(String key);

    /**
     * 查询单个问题详情
     * @param id
     * @return
     */
    StudyQuestInfo selectOne(int id);
    /**
     * 提出问题
     * @param study_id
     * @param study_content
     * @param study_date
     * @return
     */
    boolean insertQuest(int study_id, String study_content, Date study_date);

    /**
     * 回答问题
     * @param id
     * @param study_answer_id
     * @param study_answer_content
     * @param study_answer_date
     * @return
     */
    boolean insertAnswer(int id,int study_answer_id, String study_answer_content, Date study_answer_date);

    /**
     * 问题状态是否解决
     * @param id
     * @param study_quest_statue
     * @return
     */
    boolean updateStatue(int id,int study_quest_statue);

}
