/*
SQLyog Professional v12.08 (64 bit)
MySQL - 8.0.17 : Database - ordercode
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ordercode` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ordercode`;

/*Table structure for table `ld_complain` */

DROP TABLE IF EXISTS `ld_complain`;

CREATE TABLE `ld_complain` (
  `id` int(10) NOT NULL COMMENT 'id',
  `user_id` int(10) DEFAULT NULL COMMENT '投诉者id',
  `complain_content` varchar(200) DEFAULT NULL COMMENT '投诉内容',
  `complain_answ_id` int(10) DEFAULT NULL COMMENT '投诉回答id',
  `complain_date` datetime DEFAULT NULL COMMENT '投诉日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ld_complain` */

/*Table structure for table `ld_ques_answ` */

DROP TABLE IF EXISTS `ld_ques_answ`;

CREATE TABLE `ld_ques_answ` (
  `id` int(10) NOT NULL COMMENT 'id',
  `quest_id` int(10) NOT NULL COMMENT '提问者学号',
  `quest_content` varchar(250) NOT NULL COMMENT '提问者内容',
  `quest_date` datetime NOT NULL COMMENT '提问时间',
  `answ_id` int(10) DEFAULT NULL COMMENT '回答者学号',
  `answ_content` varchar(500) DEFAULT NULL COMMENT '回答者内容',
  `answ_date` datetime DEFAULT NULL COMMENT '回答者时间',
  PRIMARY KEY (`id`,`quest_id`,`quest_content`,`quest_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ld_ques_answ` */

/*Table structure for table `ld_response_record` */

DROP TABLE IF EXISTS `ld_response_record`;

CREATE TABLE `ld_response_record` (
  `id` int(10) DEFAULT NULL COMMENT 'id',
  `answ_id` int(10) DEFAULT NULL COMMENT '回答id',
  `answ_date` datetime DEFAULT NULL COMMENT '回答时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ld_response_record` */

/*Table structure for table `ld_user` */

DROP TABLE IF EXISTS `ld_user`;

CREATE TABLE `ld_user` (
  `id` int(6) NOT NULL COMMENT 'id自增主键',
  `user_id` int(10) NOT NULL COMMENT '学号',
  `user_name` varchar(10) NOT NULL COMMENT '姓名',
  `user_pass` varchar(10) NOT NULL COMMENT '密码身份证后六位',
  `user_sex` int(2) NOT NULL COMMENT '性别',
  `user_academy` varchar(25) DEFAULT NULL COMMENT '学院',
  `user_devote` int(10) DEFAULT NULL COMMENT '总贡献点',
  `user_type` int(2) DEFAULT '1' COMMENT '用户类型1学生2党员',
  `user_login` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ld_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
