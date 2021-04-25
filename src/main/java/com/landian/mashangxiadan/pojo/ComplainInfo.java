package com.landian.mashangxiadan.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author Yu W
 * @date 2020/9/22 14:37
 */
@Data
public class ComplainInfo {
    /**
     * id自增主键
     */
    private int id;

    /**
     * 投诉者id
     */
    private int user_id;

    /**
     * 投诉内容
     */
    private String complain_content;

    /**
     * 投诉的问题id
     */
    private int complain_answer_id;

    /**
     * 投诉时间
     */
    private Date complain_date;
}
