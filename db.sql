/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : db

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-05-02 14:04:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for db_admin
-- ----------------------------
DROP TABLE IF EXISTS `db_admin`;
CREATE TABLE `db_admin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_admin
-- ----------------------------
INSERT INTO `db_admin` VALUES ('admin', '123456');
INSERT INTO `db_admin` VALUES ('root', '123456');

-- ----------------------------
-- Table structure for db_student
-- ----------------------------
DROP TABLE IF EXISTS `db_student`;
CREATE TABLE `db_student` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `chinese` int(11) DEFAULT NULL,
  `math` int(11) DEFAULT NULL,
  `english` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_student
-- ----------------------------
INSERT INTO `db_student` VALUES ('20150101', '赵四', '', '0', '243', '0', '0', '243', '3');
INSERT INTO `db_student` VALUES ('20150201', '张', '女', '0', '10', '143', '100', '253', '2');
INSERT INTO `db_student` VALUES ('20150202', '王五', '', '0', '0', '143', '100', '243', '4');
INSERT INTO `db_student` VALUES ('20150302', '囡囡', '', '11', '100', '100', '100', '300', '1');
