package com.landian.mashangxiadan.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 学习社区
 * @author Yu W
 * @date 2020/10/7 19:52
 */
@Data
public class StudyQuestInfo {
    /**
     * id
     */
    private int id;
    /**
     * 学习账户id
     */
    private int study_id;
    /**
     * 学习内容
     */
    private String study_content;
    /**
     * 发布的学习时间
     */
    private Date study_date;
    /**
     * 回答id
     */
    private int study_answer_id;
    /**
     * 回答内容
     */
    private String study_answer_content;
    /**
     * 回答时间
     */
    private Date study_answer_date;
    /**
     * 回答状态
     */
    private int study_quest_statue;
}
