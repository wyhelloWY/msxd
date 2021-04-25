package com.landian.mashangxiadan.mapper;

import com.landian.mashangxiadan.pojo.NormalQuestionInfo;
import com.landian.mashangxiadan.pojo.QuestAndAnswerInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 十个常规问题
 * @author Yu W
 * @date 2020/10/9 14:55
 */
@Mapper
public interface NormalQuestionMapper {
    /**
     * 查询所有问题以及回答
     * @return
     */
    List<NormalQuestionInfo> listAll();
    /**
     * 查询所有回答通过关键字
     * @param key
     * @return
     */
    List<NormalQuestionInfo> listBykeyWord(String key);
    /**
     * 查询单个问题详情
     * @param id
     * @return
     */
    NormalQuestionInfo selectOne(int id);

    /**
     * 插入常规问答
     * @param normalQuestionInfo
     * @return
     */
    int insertNormal(NormalQuestionInfo normalQuestionInfo);

    /**
     * 分页查询
     * @return
     */
    List<NormalQuestionInfo> selectPage();

    /**
     * 修改
     * @param normalQuestionInfo
     * @return
     */
    int updateNormal(NormalQuestionInfo normalQuestionInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteNormal(int id);
}
