package com.landian.mashangxiadan.controller;

import com.landian.mashangxiadan.component.NoRepeatSubmit;
import com.landian.mashangxiadan.pojo.NormalQuestionInfo;
import com.landian.mashangxiadan.pojo.QuestAndAnswerInfo;
import com.landian.mashangxiadan.pojo.StudyQuestInfo;
import com.landian.mashangxiadan.service.CheckInfoService;
import com.landian.mashangxiadan.service.QuestAndAnswerService;
import com.landian.mashangxiadan.service.StudyQuestService;
import com.landian.mashangxiadan.utils.GetDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author Yu W
 * @date 2020/10/9 20:26
 */
@Controller
@RequestMapping("/study")
public class StudyQuestController {
    @Autowired
    CheckInfoService checkInfoService;
    /**
     * 查询所有问题
     */
    @Autowired
    StudyQuestService studyQuestService;

    @Autowired
    QuestAndAnswerService questAndAnswerService;
    @RequestMapping("/listAll")
    @ResponseBody
    public List<StudyQuestInfo> listAll(@RequestParam("user_type") String user_type){
        int type = Integer.valueOf(user_type);
        List<StudyQuestInfo> questAndAnswerInfos = new ArrayList<>();
        if(type == 2){
            questAndAnswerInfos = studyQuestService.listByAnswerNull();
        }else{

            questAndAnswerInfos = studyQuestService.listAll();
        }

        return questAndAnswerInfos;
    }


    /**
     * 查询一个用户提出的所有问题
     * @param quest_id
     * @return
     */
    @RequestMapping("/listQuestions")
    @ResponseBody
    public List<StudyQuestInfo> listAllQuest(@RequestParam("quest_id") String quest_id){
        int id = Integer.valueOf(quest_id);
        List<StudyQuestInfo> questAndAnswerInfos = new ArrayList<>();
        questAndAnswerInfos = studyQuestService.listByQuest(id);
        return  questAndAnswerInfos;
    }

    /**
     * 查询一个用户回答的所有问题
     * @param answer_id
     * @return
     */
    @RequestMapping("/listAnswers")
    @ResponseBody
    public List<StudyQuestInfo> listAnswers(@RequestParam("answer_id") String answer_id){
        int id = Integer.valueOf(answer_id);
        List<StudyQuestInfo> questAndAnswerInfos = new ArrayList<>();
        questAndAnswerInfos = studyQuestService.listByAnswer(id);
        return  questAndAnswerInfos;
    }
    /**
     * 根据关键字查询
     * @param key
     * @return
     */
    @RequestMapping("/listByKey")
    @ResponseBody
    public List<StudyQuestInfo> listAllByKey(@RequestParam("key") String key){
        List<StudyQuestInfo> questAndAnswerInfos = new ArrayList<>();
        questAndAnswerInfos = studyQuestService.listBykeyWord(key);
        return questAndAnswerInfos;
    }

    /**
     * 查询问题详情
     * @param quest_id
     * @return
     */
    @RequestMapping("/selectQuest")
    @ResponseBody
    @NoRepeatSubmit
    public StudyQuestInfo selectQuestion(@RequestParam("quest_id")String quest_id){
        int id = Integer.valueOf(quest_id);
        StudyQuestInfo questAndAnswerInfo= studyQuestService.selectOne(id);
        if(questAndAnswerInfo.getStudy_answer_content()==null){
            questAndAnswerInfo.setStudy_answer_content("暂无回答");
        }
        return questAndAnswerInfo;
    }

    /**
     * 提交问题
     * @param quest_id
     * @param quest_content
     * @return
     */
    @RequestMapping("/postQuest")
    @ResponseBody
    @NoRepeatSubmit
    public Map<String,String> postQuestion(@RequestParam("quest_id")String quest_id,
                                           @RequestParam("quest_content")String quest_content,
                                            @RequestParam("quest_type")String quest_type){
        Map<String,String> map = new HashMap<>();
        int user_id = Integer.valueOf(quest_id);
        int type = Integer.valueOf(quest_type);
        if(type==1){
            boolean flag = studyQuestService.insertQuest(user_id,quest_content, GetDateUtil.getDateTime());
            if(flag ==true){
                map.put("result","success");
            }else{
                map.put("result","error");
            }
        }else{
            boolean flag = questAndAnswerService.insertQuest(user_id,quest_content, GetDateUtil.getDateTime());
            if(flag ==true){
                map.put("result","success");
            }else{
                map.put("result","error");
            }
        }


        return map;
    }



    /**
     * 提价回答
     * @param id 回答的问题id
     * @param answer_id   回答者
     * @param answer_content 回答内容
     * @return
     */
    @RequestMapping("/postAnswer")
    @ResponseBody
    @NoRepeatSubmit
    public Map<String,String> postAnswer(@RequestParam("questionId")String id,
                                         @RequestParam("answer_id")String answer_id,
                                         @RequestParam("answer_content")String answer_content){
        System.out.println(answer_id);
        int question_id = Integer.valueOf(id);
        int answ_id = Integer.valueOf(answer_id);
        Map<String,String> map = new HashMap<>();
        boolean flag = studyQuestService.insertAnswer(question_id,answ_id,answer_content, GetDateUtil.getDateTime());
        if(flag ==true){
            map.put("result","success");
        }else{
            map.put("result","error");
        }
        return map;
    }
}
