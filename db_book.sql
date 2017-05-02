/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : db_book

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-05-02 14:04:56
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(20) NOT NULL,
  `author` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `press` varchar(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `bookdesc` varchar(500) DEFAULT NULL,
  `booktypeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '电路与电子学', '李晶皎、王文辉', '女', '电子工业出版社', '39', '计算机电路、模电', '2');
INSERT INTO `book` VALUES ('2', 'java语言程序设计（基础篇）', 'Y·Daniel Liang', '男', '机械工业出版社', '75', 'java基础', '1');
INSERT INTO `book` VALUES ('3', '线性代数', '张宇', '女', '北京理工大学出版社', '29.8', '', '3');
INSERT INTO `book` VALUES ('4', '鸟哥的LINUX私房菜', '鸟哥', '男', '人民邮电出版社', '88.88', '鸟哥大法', '1');

-- ----------------------------
-- Table structure for booktype
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `booktypename` varchar(20) NOT NULL,
  `booktypedesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booktype
-- ----------------------------
INSERT INTO `booktype` VALUES ('1', '计算机类', 'Computer science');
INSERT INTO `booktype` VALUES ('2', '电子学类', '电子');
INSERT INTO `booktype` VALUES ('3', '数学', '难山难');
