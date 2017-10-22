/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : rfid_book

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-05-02 14:14:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` varchar(255) NOT NULL,
  `bookname` varchar(20) NOT NULL,
  `author` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `press` varchar(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `bookdesc` varchar(500) DEFAULT NULL,
  `booktypeid` int(11) DEFAULT NULL,
  `conditions` int(11) DEFAULT NULL,
  `borrower` varchar(255) DEFAULT NULL,
  `outtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('abc1996', '电路与电子学', '李晶皎、王文辉', '女', '电子工业出版社', '39', '计算机电路、模电', '2', '0', 'B00100002333000117905A3B', '2016-11-19 15:35:33');
INSERT INTO `book` VALUES ('asd2015', 'java语言程序设计（基础篇）', 'Y·Daniel Liang', '男', '机械工业出版社', '75.77', 'java基础', '1', '1', null, null);
INSERT INTO `book` VALUES ('asd2022', '数据结构', '我', '女', '华中科技大学出版社', '27.99', '耶耶耶', '3', '0', '0023F6000002000000300031', '2016-11-19 15:36:28');
INSERT INTO `book` VALUES ('asd3027', '鸟哥的LINUX私房菜', '鸟哥', '男', '人民邮电出版社', '88.88', '鸟哥大法', '1', '0', 'B00100002333000117905A3B', '2016-11-19 12:06:32');
INSERT INTO `book` VALUES ('qwe2733', '线性代数', '张宇', '女', '北京理工大学出版社', '29.8', '.。。', '3', '1', null, null);

-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `booktypename` varchar(20) NOT NULL,
  `booktypedesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booktype
-- ----------------------------
INSERT INTO `booktype` VALUES ('1', '计算机类', 'Computer science');
INSERT INTO `booktype` VALUES ('2', '电子学类', '电子');
INSERT INTO `booktype` VALUES ('3', '数学类', '数学相关');
INSERT INTO `booktype` VALUES ('4', '文学类', '文学书籍');
