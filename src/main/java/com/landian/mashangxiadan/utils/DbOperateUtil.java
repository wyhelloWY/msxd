package com.landian.mashangxiadan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 数据库备份
 * @author Yu W
 * @date 2020/9/25 18:59
 */
public class DbOperateUtil {
    private final static Logger logger =  LoggerFactory.getLogger(DbOperateUtil.class);
    /**
     * 备份数据库
     * @param root
     * @param pwd
     * @param dbName
     * @param backPath
     * @param backName
     * @throws Exception
     */
    public static void dbBackUp(String root,String pwd,String dbName,String backPath,String backName) throws Exception {
        String pathSql = backPath+backName;
        File fileSql = new File(pathSql);
        //创建备份sql文件
        if (!fileSql.exists()){
            fileSql.createNewFile();
        }
        //mysqldump -hlocalhost -uroot -p520560 db > /home/back.sql
        StringBuffer sb = new StringBuffer();
        sb.append("mysqldump");
        sb.append(" -hlocalhost");
        sb.append(" -u"+root);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" >");
        sb.append(pathSql);
        logger.info("cmd命令为："+sb.toString());
        Runtime runtime = Runtime.getRuntime();
        logger.info("开始备份："+dbName);
        System.out.println("开始备份："+dbName);
        Process process = runtime.exec("cmd /c"+sb.toString());
        logger.info("备份成功");
    }

    /**
     * 数据还原
     * @param root
     * @param pwd
     * @param dbName
     * @param filePath
     */
    public static void dbRestore(String root,String pwd,String dbName,String filePath){
        StringBuilder sb = new StringBuilder();
        sb.append("mysql");
        sb.append(" -h127.0.0.1");
        sb.append(" -u"+root);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" <");
        sb.append(filePath);
        logger.info("cmd命令为："+sb.toString());

        Runtime runtime = Runtime.getRuntime();
        logger.info("开始还原");
        try {
            Process process = runtime.exec("cmd /c"+sb.toString());
            InputStream is = process.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is,"utf8"));
            String line = null;
            while ((line=bf.readLine())!=null){
                System.out.println(line);
            }
            is.close();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("数据还原成功");
    }

}
