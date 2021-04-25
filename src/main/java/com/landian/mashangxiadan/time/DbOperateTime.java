package com.landian.mashangxiadan.time;

import com.landian.mashangxiadan.service.CheckInfoService;
import com.landian.mashangxiadan.utils.DbOperateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yu W
 * @date 2020/10/11 10:20
 */
@Component
public class DbOperateTime {
    /**
     * 日志记录
     */
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0 0 1 * * *")
    public void backUpData() throws Exception {
        String backName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())+".sql";
        logger.info("执行每日数据库备份"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        try {
            DbOperateUtil.dbBackUp("root","TestWy@123","ordercode","/root/mashangxiadan/",backName);
            logger.info("执行每日数据库备份完成"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }catch (Exception e){
            e.printStackTrace();
            logger.info("执行每日数据库备份失败"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }

    }
}
