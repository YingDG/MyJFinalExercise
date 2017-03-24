/*
Navicat MySQL Data Transfer

Source Server         : ljg
Source Server Version : 50527
Source Host           : 172.27.42.6:3306
Source Database       : zdm

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-03-24 15:41:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user2
-- ----------------------------
DROP TABLE IF EXISTS `user2`;
CREATE TABLE `user2` (
  `id` int(11) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  KEY `id_foreign` (`id`),
  CONSTRAINT `id_foreign` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
