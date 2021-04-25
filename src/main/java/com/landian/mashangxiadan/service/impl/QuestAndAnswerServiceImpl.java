package com.landian.mashangxiadan.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landian.mashangxiadan.mapper.NormalQuestionMapper;
import com.landian.mashangxiadan.mapper.QuestAndAnswerMapper;
import com.landian.mashangxiadan.pojo.NormalQuestionInfo;
import com.landian.mashangxiadan.pojo.QuestAndAnswerInfo;
import com.landian.mashangxiadan.pojo.UserInfo;
import com.landian.mashangxiadan.service.QuestAndAnswerService;
import com.landian.mashangxiadan.utils.PageRequest;
import com.landian.mashangxiadan.utils.PageResult;
import com.landian.mashangxiadan.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yu W
 * @date 2020/9/22 22:43
 */
@Service
@Transactional
public class QuestAndAnswerServiceImpl implements QuestAndAnswerService {
    /**
     * 日志记录
     */
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    QuestAndAnswerMapper questAndAnswerMapper;

    @Autowired
    NormalQuestionMapper normalQuestionMapper;
    @Override
    public List<QuestAndAnswerInfo> listAll() {
        List<QuestAndAnswerInfo> questAndAnswerInfos = new ArrayList<>();
        try{
            questAndAnswerInfos = questAndAnswerMapper.listAll();
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询全部普通回答问题出现异常");
        }
        return questAndAnswerInfos;
    }

    @Override
    public List<NormalQuestionInfo> listAllNormalQuestion() {
        List<NormalQuestionInfo> normalQuestionInfos = new ArrayList<>();
        try {
            normalQuestionInfos = normalQuestionMapper.listAll();
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询全部常规回答问题出现异常");
        }
        return normalQuestionInfos;
    }

    @Override
    public List<NormalQuestionInfo> listAllNormalBykeyWord(String key) {
        List<NormalQuestionInfo> normalQuestionInfos = new ArrayList<>();
        try {
            normalQuestionInfos = normalQuestionMapper.listBykeyWord(key);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("根据关键字查询常规回答问题出现异常");
        }

        return normalQuestionInfos;
    }

    @Override
    public NormalQuestionInfo selectNormalQuest(int id) {
        return  normalQuestionMapper.selectOne(id);
    }

    @Override
    public List<QuestAndAnswerInfo> listByAnswerNull() {
        return questAndAnswerMapper.listByAnswerNull();
    }

    @Override
    public List<QuestAndAnswerInfo> listByQuest(int quest_id) {
        return questAndAnswerMapper.listByQuest(quest_id);
    }

    @Override
    public List<QuestAndAnswerInfo> listByAnswer(int answer_id) {
        return questAndAnswerMapper.listByAnswer(answer_id);
    }

    @Override
    public List<QuestAndAnswerInfo> listBykeyWord(String key) {
        return questAndAnswerMapper.listBykeyWord(key);
    }

    @Override
    public QuestAndAnswerInfo selectOne(int id) {
        return questAndAnswerMapper.selectOne(id);
    }

    @Override
    public boolean insertQuest(int quest_id, String quest_content, Date quest_date) {
        int flag = questAndAnswerMapper.insertQuest(quest_id,quest_content,quest_date);
        if(flag>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean insertAnswer(int id, int answer_id, String answer_content, Date answer_date) {


        int flag = questAndAnswerMapper.insertAnswer(id,answer_id,answer_content,answer_date);
        if(flag>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateStatue(int id, int quest_statue) {
        int flag = questAndAnswerMapper.updateStatue(id,quest_statue);
        if(flag>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<QuestAndAnswerInfo> selectQuestStatue(int quest_id) {
        return questAndAnswerMapper.selectQuestStatue(quest_id);
    }

    @Override
    public int updateAnswerId(int id, int answer_id, int quest_statue) {
        return questAndAnswerMapper.updateAnswerId(id,answer_id,quest_statue);
    }

    @Override
    public int selectOneQuestStatue(int id) {
        return questAndAnswerMapper.selectOneQuestStatue(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, (PageInfo<?>) getPageInfo(pageRequest));
    }
    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    @Override
    public List<QuestAndAnswerInfo> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<QuestAndAnswerInfo> sysMenus = questAndAnswerMapper.selectPage();
        return sysMenus;
    }

    @Override
    public List<NormalQuestionInfo> getNormalInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<NormalQuestionInfo> sysMenus = normalQuestionMapper.selectPage();
        return sysMenus;
    }

    @Override
    public int deleteQuest(int id) {
        return questAndAnswerMapper.deleteQuest(id);
    }

    @Override
    public int insertNormal(NormalQuestionInfo normalQuestionInfo) {
        return normalQuestionMapper.insertNormal(normalQuestionInfo);
    }

    @Override
    public int updateNormal(NormalQuestionInfo normalQuestionInfo) {
        return normalQuestionMapper.updateNormal(normalQuestionInfo);
    }

    @Override
    public int deleteNormal(int id) {
        return normalQuestionMapper.deleteNormal(id);
    }
}
