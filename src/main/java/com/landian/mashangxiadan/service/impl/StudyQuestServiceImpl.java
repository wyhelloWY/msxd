package com.landian.mashangxiadan.service.impl;

import com.landian.mashangxiadan.mapper.StudyQuestMapper;
import com.landian.mashangxiadan.pojo.StudyQuestInfo;
import com.landian.mashangxiadan.service.StudyQuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/10/9 20:20
 */
@Service
public class StudyQuestServiceImpl implements StudyQuestService {
    @Autowired
    StudyQuestMapper studyQuestMapper;
    /**
     * 日志记录
     */
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Override
    public List<StudyQuestInfo> listAll() {
        return studyQuestMapper.listAll();
    }

    @Override
    public List<StudyQuestInfo> listByAnswerNull() {
        return studyQuestMapper.listByAnswerNull();
    }

    @Override
    public List<StudyQuestInfo> listByQuest(int study_id) {
        return studyQuestMapper.listByQuest(study_id);
    }

    @Override
    public List<StudyQuestInfo> listByAnswer(int study_answer_id) {
        return studyQuestMapper.listByAnswer(study_answer_id);
    }

    @Override
    public List<StudyQuestInfo> listBykeyWord(String key) {
        return studyQuestMapper.listBykeyWord(key);
    }

    @Override
    public StudyQuestInfo selectOne(int id) {
        return studyQuestMapper.selectOne(id);
    }

    @Override
    public boolean insertQuest(int study_id, String study_content, Date study_date) {
        int flag = studyQuestMapper.insertQuest(study_id,study_content,study_date);
        if(flag>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean insertAnswer(int id, int study_answer_id, String study_answer_content, Date study_answer_date) {
        int flag = studyQuestMapper.insertAnswer(id, study_answer_id, study_answer_content, study_answer_date);
        if(flag>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateStatue(int id, int study_quest_statue) {
        int flag = studyQuestMapper.updateStatue(id, study_quest_statue);
        if(flag>0){
            return true;
        }else{
            return false;
        }
    }
}
