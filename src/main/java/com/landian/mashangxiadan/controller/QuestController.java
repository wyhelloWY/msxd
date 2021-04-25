package com.landian.mashangxiadan.controller;

import com.landian.mashangxiadan.component.NoRepeatSubmit;
import com.landian.mashangxiadan.pojo.NormalQuestionInfo;
import com.landian.mashangxiadan.pojo.QuestAndAnswerInfo;
import com.landian.mashangxiadan.pojo.ResultMap;
import com.landian.mashangxiadan.pojo.UserInfo;
import com.landian.mashangxiadan.service.CheckInfoService;
import com.landian.mashangxiadan.service.QuestAndAnswerService;
import com.landian.mashangxiadan.utils.GetDateUtil;
import com.landian.mashangxiadan.utils.PageRequest;
import com.landian.mashangxiadan.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Yu W
 * @date 2020/9/22 22:40
 */
@Controller
@RequestMapping("/questAndAnswer")
public class QuestController {

    @Autowired
    CheckInfoService checkInfoService;
    /**
     * 查询所有问题
     */
    @Autowired
    QuestAndAnswerService questAndAnswerService;
    @RequestMapping("/listAll")
    @ResponseBody
    @NoRepeatSubmit
    public List<QuestAndAnswerInfo> listAll(@RequestParam("user_type") String user_type){
        int type = Integer.valueOf(user_type);
        List<QuestAndAnswerInfo> questAndAnswerInfos = new ArrayList<>();
        if(type == 2){
            questAndAnswerInfos = questAndAnswerService.listByAnswerNull();
        }else{

            questAndAnswerInfos = questAndAnswerService.listAll();
        }

        return questAndAnswerInfos;
    }

    /**
     * 查询所有常规回答问题
     * @param user_type
     * @return
     */
    @RequestMapping("/listNormalQuest")
    @ResponseBody
    @NoRepeatSubmit
    public List<Object> listNormalQuest(@RequestParam("user_type") String user_type){
        int type = Integer.valueOf(user_type);
        List<Object> questAndAnswerInfos = new ArrayList<>();

        if(type == 2){
            questAndAnswerInfos = Collections.singletonList(questAndAnswerService.listByAnswerNull());
        }else{

            questAndAnswerInfos = Collections.singletonList(questAndAnswerService.listAllNormalQuestion());
        }

        return questAndAnswerInfos;
    }

    /**
     * 关键字查询常规问题
     * @param key
     * @return
     */
    @RequestMapping("/listNormalByKey")
    @ResponseBody
    @NoRepeatSubmit
    public List<Object> listByKey(@RequestParam("key") String key,
                                              @RequestParam("user_type") String user_type){
        int type = Integer.valueOf(user_type);
        List<Object> questAndAnswerInfos = new ArrayList<>();
        //根据关键字返回全部普通问题
        if(type == 2){
            questAndAnswerInfos = Collections.singletonList(questAndAnswerService.listBykeyWord(key));
        }else{
            questAndAnswerInfos = Collections.singletonList(questAndAnswerService.listAllNormalBykeyWord(key));
        }

        return questAndAnswerInfos;
    }


    /**
     * 查询问题详情
     * @param quest_id
     * @return
     */
    @RequestMapping("/selectNormalQuest")
    @ResponseBody
    @NoRepeatSubmit
    public NormalQuestionInfo selectNormalQuestion(@RequestParam("quest_id")String quest_id){
        int id = Integer.valueOf(quest_id);
        NormalQuestionInfo questAndAnswerInfo= questAndAnswerService.selectNormalQuest(id);
        if(questAndAnswerInfo.getAnswer_content()==null){
            questAndAnswerInfo.setAnswer_content("暂无回答");
        }
        return questAndAnswerInfo;
    }
    /**
     * 查询一个用户提出的所有问题
     * @param quest_id
     * @return
     */
    @RequestMapping("/listQuestions")
    @ResponseBody
    @NoRepeatSubmit
    public List<QuestAndAnswerInfo> listAllQuest(@RequestParam("quest_id") String quest_id){
        int id = Integer.valueOf(quest_id);
        List<QuestAndAnswerInfo> questAndAnswerInfos = new ArrayList<>();
        questAndAnswerInfos = questAndAnswerService.listByQuest(id);
        return  questAndAnswerInfos;
    }

    /**
     * 查询一个用户回答的所有问题
     * @param answer_id
     * @return
     */
    @RequestMapping("/listAnswers")
    @ResponseBody
    @NoRepeatSubmit
    public List<QuestAndAnswerInfo> listAnswers(@RequestParam("answer_id") String answer_id){
        int id = Integer.valueOf(answer_id);
        List<QuestAndAnswerInfo> questAndAnswerInfos = new ArrayList<>();
        questAndAnswerInfos = questAndAnswerService.listByAnswer(id);
        return  questAndAnswerInfos;
    }
    /**
     * 根据关键字查询
     * @param key
     * @return
     */
    @RequestMapping("/listByKey")
    @ResponseBody
    @NoRepeatSubmit
    public List<QuestAndAnswerInfo> listAllByKey(@RequestParam("key") String key){
        List<QuestAndAnswerInfo> questAndAnswerInfos = new ArrayList<>();
        questAndAnswerInfos = questAndAnswerService.listBykeyWord(key);
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
    public QuestAndAnswerInfo selectQuestion(@RequestParam("quest_id")String quest_id){
        int id = Integer.valueOf(quest_id);
        QuestAndAnswerInfo questAndAnswerInfo= questAndAnswerService.selectOne(id);
        if(questAndAnswerInfo.getAnswer_content()==null){
            questAndAnswerInfo.setAnswer_content("暂无回答");
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
                                           @RequestParam("quest_content")String quest_content){
        Map<String,String> map = new HashMap<>();
        int user_id = Integer.valueOf(quest_id);
        if(quest_content!=null&&quest_content!=""){
            boolean flag = questAndAnswerService.insertQuest(user_id,quest_content, GetDateUtil.getDateTime());
            if(flag ==true){
                map.put("result","success");
            }else{
                map.put("result","error");
            }
        }else{
            map.put("result","error");
        }
        return map;
    }

    /**
     * 对问题进行评价
     * 0:问题刚刚初始化 未有回答
     * 1:回答已经回答 还未进行评价
     * 2：问题已经评价 状态为未解决
     * 3:问题已经解决 状态为解决
     * @param id
     * @param answer_id
     * @param quest_statue
     * @return
     */
    @RequestMapping("/assessQuest")
    @ResponseBody
    @NoRepeatSubmit
    public Map<String,String> UpdateSatue(@RequestParam("id")String id,
                                           @RequestParam("answer_id")String answer_id,
                                           @RequestParam("quest_statue")String quest_statue){
        System.out.println(answer_id);
        Map<String,String> map = new HashMap<>();
        int quest_id = Integer.valueOf(id);
        int answer = Integer.valueOf(answer_id);
        int statue = Integer.valueOf(quest_statue);
        boolean flag = questAndAnswerService.updateStatue(quest_id,statue);
        //如果状态为3 是已经解决状态 则数据库对相应回答用户考核结果+1
        if(statue == 3){
            checkInfoService.insertAndUpdateCheck(answer);
        }
        if(flag ==true){
            map.put("result","success");
        }else{
            map.put("result","error");
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
        QuestAndAnswerInfo questAndAnswerInfo = questAndAnswerService.selectOne(question_id);
        int answer = questAndAnswerInfo.getAnswer_id();
        System.out.println(answer);
        //判断answer是否为0 如果为0则此问题还未被人回答
        if(questAndAnswerInfo.getAnswer_id()==0) {
            boolean flag = questAndAnswerService.insertAnswer(question_id, answ_id, answer_content, GetDateUtil.getDateTime());
            if (flag == true) {
                map.put("result", "success");
            } else {
                map.put("result", "error");
            }
        }else if(questAndAnswerInfo.getAnswer_id()==answ_id){
            boolean flag = questAndAnswerService.insertAnswer(question_id, answ_id, answer_content, GetDateUtil.getDateTime());
            if (flag == true) {
                map.put("result", "success");
            } else {
                map.put("result", "error");
            }
        }else{
            map.put("result","exist");
        }


        return map;
    }

    @RequestMapping("/selectNotAccess")
    @ResponseBody
    public List<QuestAndAnswerInfo> selectNotAccess(@RequestParam("quest_id")String quest_id){
        List<QuestAndAnswerInfo> questAndAnswerInfos = new ArrayList<>();
        int user_id = Integer.valueOf(quest_id);
        questAndAnswerInfos = questAndAnswerService.selectQuestStatue(user_id);
        return questAndAnswerInfos;
    }

    /**
     * 提交我要接单接口
     * @param id
     * @param quest_id
     * @return
     */
    @RequestMapping("/firstAnswer")
    @ResponseBody
    public Map<String,String> getFirstAnswer(@RequestParam("id")String id,
                                             @RequestParam("quest_id")String quest_id){
        int question = Integer.valueOf(id);
        int user_id = Integer.valueOf(quest_id);
        Map<String,String> map = new HashMap<>();
        int statue = questAndAnswerService.selectOneQuestStatue(question);
        QuestAndAnswerInfo questAndAnswerInfo = questAndAnswerService.selectOne(question);
        int answer = questAndAnswerInfo.getAnswer_id();
        //问题已经被锁定
        if(statue ==6){
            map.put("result","lead");
        }else if(statue == 0&&questAndAnswerInfo.getQuest_id()!=user_id) {
            int flag = questAndAnswerService.updateAnswerId(question, user_id, 6);
            if (flag > 0) {
                map.put("result", "success");
            } else {
                map.put("result", "error");
            }
            System.out.println(map);
        }else{
            map.put("result","error");
        }
        return map;
    }
    /**
     * 分页查询
     * @param num
     * @param size
     * @return
     */
    @RequestMapping(value="/findQuestPage")
    @ResponseBody
    public List<QuestAndAnswerInfo> findPage(@RequestParam(value = "page",required = false) int num,
                                             @RequestParam(value = "limit",required = false)int size,
                                             @RequestParam(value = "key",required = false)String key) {
        List<QuestAndAnswerInfo>  questAndAnswerInfos = new ArrayList<>();
        if(key ==null || key ==""){
            PageRequest pageQuery = new PageRequest();
            pageQuery.setPageNum(num);
            pageQuery.setPageSize(size);
            questAndAnswerInfos = questAndAnswerService.getPageInfo(pageQuery);

        }else{
            questAndAnswerInfos = questAndAnswerService.listBykeyWord(key);
        }
        return questAndAnswerInfos;
    }

    @RequestMapping("/deleteQuest")
    @ResponseBody
    public ResultMap deleteQuest(@RequestParam("id")String id){
        ResultMap resultMap = new ResultMap();
        int flag = questAndAnswerService.deleteQuest(Integer.valueOf(id));
        if(flag > 0){
            resultMap.setMsg("成功");
            resultMap.setCode(200);
        }else{
            resultMap.setMsg("失败");
            resultMap.setCode(201);
        }
        return resultMap;
    }



    /**
     * 分页查询常规问题
     * @param num
     * @param size
     * @return
     */
    @RequestMapping(value="/findNoramlPage")
    @ResponseBody
    public List<NormalQuestionInfo> findNoramlPage(@RequestParam(value = "page",required = false) int num,
                                             @RequestParam(value = "limit",required = false)int size,
                                             @RequestParam(value = "key",required = false)String key) {
        List<NormalQuestionInfo>  questAndAnswerInfos = new ArrayList<>();
        if(key ==null || key ==""){
            PageRequest pageQuery = new PageRequest();
            pageQuery.setPageNum(num);
            pageQuery.setPageSize(size);
            questAndAnswerInfos = questAndAnswerService.getNormalInfo(pageQuery);

        }else{
            questAndAnswerInfos = questAndAnswerService.listAllNormalBykeyWord(key);
        }
        return questAndAnswerInfos;
    }

    @RequestMapping(value="/selectNoramlDetail")
    public String toNoraml(@RequestParam("id")String id, Model model){
        NormalQuestionInfo normalQuestionInfo = questAndAnswerService.selectNormalQuest(Integer.valueOf(id));
        model.addAttribute("quest",normalQuestionInfo);
        return "normal-edit";
    }
    @RequestMapping(value="/updateNoraml")
    @ResponseBody
    public ResultMap updateNormal(@RequestParam("id")String id,
                               @RequestParam("quest_content")String quest_content,
                               @RequestParam("answer_content")String answer_content){
        NormalQuestionInfo normalQuestionInfo = new NormalQuestionInfo();
        normalQuestionInfo.setId(Integer.valueOf(id));
        normalQuestionInfo.setQuest_content(quest_content);
        normalQuestionInfo.setAnswer_content(answer_content);
        int flag = questAndAnswerService.updateNormal(normalQuestionInfo);
        ResultMap resultMap = new ResultMap();
        if(flag > 0){
            resultMap.setMsg("成功");
            resultMap.setCode(200);
        }else{
            resultMap.setMsg("失败");
            resultMap.setCode(201);
        }
        return resultMap;
    }


    @RequestMapping(value="/addNoraml")
    @ResponseBody
    public ResultMap addNoraml(
                                  @RequestParam("quest_content")String quest_content,
                                  @RequestParam("answer_content")String answer_content){
        NormalQuestionInfo normalQuestionInfo = new NormalQuestionInfo();
        normalQuestionInfo.setQuest_id(20000000);
        normalQuestionInfo.setQuest_content(quest_content);
        normalQuestionInfo.setAnswer_id(20000000);
        normalQuestionInfo.setAnswer_content(answer_content);
        normalQuestionInfo.setQuest_statue(3);
        normalQuestionInfo.setQuest_date(new Date());
        normalQuestionInfo.setAnswer_date(new Date());
        int flag = questAndAnswerService.insertNormal(normalQuestionInfo);
        ResultMap resultMap = new ResultMap();
        if(flag > 0){
            resultMap.setMsg("成功");
            resultMap.setCode(200);
        }else{
            resultMap.setMsg("失败");
            resultMap.setCode(201);
        }
        return resultMap;
    }

    @RequestMapping(value="/deleteNormal")
    @ResponseBody
    public ResultMap deleteNormal(@RequestParam("id")String id){
        ResultMap resultMap = new ResultMap();
        int flag = questAndAnswerService.deleteNormal(Integer.valueOf(id));
        if(flag > 0){
            resultMap.setMsg("成功");
            resultMap.setCode(200);
        }else{
            resultMap.setMsg("失败");
            resultMap.setCode(201);
        }
        return resultMap;
    }

}
