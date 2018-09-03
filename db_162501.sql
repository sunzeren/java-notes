/*
Navicat MySQL Data Transfer

Source Server         : spring
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : db_162501

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-03 13:06:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_company`
-- ----------------------------
DROP TABLE IF EXISTS `tb_company`;
CREATE TABLE `tb_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `legal_person` varchar(60) DEFAULT NULL,
  `registered_cipital` double(16,2) DEFAULT NULL,
  `registration_time` datetime DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_company
-- ----------------------------
INSERT INTO `tb_company` VALUES ('1', '阿里巴巴', '马云', '200000.00', '1999-09-09 06:00:00', '12345678', '杭州市');
INSERT INTO `tb_company` VALUES ('2', '百度', '李彦宏', '666666.00', '1996-06-06 01:05:00', '66666666', '北京市 6');
INSERT INTO `tb_company` VALUES ('3', '腾讯', '马化腾', '600000.00', '1998-08-08 00:04:10', '88552211', '深圳市');
INSERT INTO `tb_company` VALUES ('5', '小米', '雷军', '800000.00', '2010-10-10 18:02:52', '99966633', '武汉');
