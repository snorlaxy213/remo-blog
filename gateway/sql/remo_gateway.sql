/*
Navicat MySQL Data Transfer

Source Server         : remo
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : remo_gateway

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-02-04 20:25:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blacklist
-- ----------------------------
DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist` (
  `ID` int(11) NOT NULL COMMENT '主键id',
  `IP_ADDRES` varchar(255) DEFAULT NULL COMMENT 'ip地址',
  `RESTRICTION_TYPE` varchar(255) DEFAULT NULL COMMENT '限制类型',
  `AVAILABILITY` varchar(255) DEFAULT NULL COMMENT '是否可用',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
