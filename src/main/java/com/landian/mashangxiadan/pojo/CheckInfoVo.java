package com.landian.mashangxiadan.pojo;

/**
 * @author Yu W
 * @date 2020/9/27 16:42
 */
public class CheckInfoVo {
    private int student_num;

    private int student_score;

    private String student_name;

    public int getStudent_num() {
        return student_num;
    }

    public void setStudent_num(int student_num) {
        this.student_num = student_num;
    }

    public int getStudent_score() {
        return student_score;
    }

    public void setStudent_score(int student_score) {
        this.student_score = student_score;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    @Override
    public String toString() {
        return "CheckInfoVo{" +
                "student_num=" + student_num +
                ", student_score=" + student_score +
                ", student_name='" + student_name + '\'' +
                '}';
    }
}
