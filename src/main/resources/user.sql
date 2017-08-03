/*
Navicat MySQL Data Transfer

Source Server         : ljg
Source Server Version : 50527
Source Host           : 172.27.42.6:3306
Source Database       : zdm

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-03-24 15:41:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
