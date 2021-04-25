package com.landian.mashangxiadan.time;

/**
 * @author Yu W
 * @date 2020/9/23 21:48
 */

import com.landian.mashangxiadan.service.CheckInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ClearDataJob {
    /**
     * 日志记录
     */
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    CheckInfoService checkInfoService;
    @Scheduled(cron = "0 0 0 * * *")
    public void clearDataJob() {
        logger.info("执行每日积分归零"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        try {
            checkInfoService.updateAllDay();
            logger.info("执行每日积分归零完成"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }catch (Exception e){
            e.printStackTrace();
            logger.info("每日积分归零出现异常");
        }


    }
}
