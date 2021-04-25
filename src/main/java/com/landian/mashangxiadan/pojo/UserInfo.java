package com.landian.mashangxiadan.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

/**
 * @author Yu W
 * @date 2020/9/22 14:33
 */
@Data
public class UserInfo {
    /**
     *id自增主键
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**
     *学号
     */
    private int user_id;
    /**
     * 姓名
     */
    private String user_name;

    /**
     *密码身份证后六位
     */
    private String user_pass;

    /**
     *性别
     */
    private int user_sex;
    /**
     *学院
     */
    private String user_academy;

    /**
     *总贡献点
     */
    private int user_devote;
    /**
     *用户类型1学生2党员
     */
    private int user_type;
    /**
     *最后一次登录时间
     */
    private Date user_login;
}
