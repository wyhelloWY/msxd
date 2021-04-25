package com.landian.mashangxiadan.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author Yu W
 * @date 2020/9/22 14:46
 */
@Data
public class QuestAndAnswerInfo {
    /**
     *id自增主键
     */
    private int id;
    /**
     *提问学号
     */
    private int quest_id;
    /**
     * 提问内容
     */
    private String quest_content;

    /**
     *提问时间
     */
    private Date quest_date;

    /**
     *回答者id
     */
    private int answer_id;
    /**
     *回答内容
     */
    private String answer_content;

    /**
     *回答时间
     */
    private Date answer_date;

    /**
     * 提问状态
     */
    private int quest_statue;

}
