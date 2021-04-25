package com.landian.mashangxiadan.pojo;

import lombok.Data;


import javax.persistence.Transient;
/**
 * @author Yu W
 * @date 2020/9/23 22:07
 */
@Data
public class CheckInfo {
    /**
     * 用户id
     */
    private int user_id;
    /**
     * 每日有效回答次数
     */
    private short effective_nums;

    /**
     * 每日回答次数
     */
    private short everyday_nums;
    /**
     * 总回答次数
     */
    private short total_nums;

    /**
     * 总回答有效次数
     */
    private short total_effect_nums;

    @Transient
    private String user_name;

    @Transient
    private String student_name;

    @Transient
    private int  student_score;

    @Transient
    private int student_num;
}
