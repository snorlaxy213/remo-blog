/*
Navicat MySQL Data Transfer

Source Server         : cmis
Source Server Version : 50640
Source Host           : 192.168.101.153:3306
Source Database       : remo_article

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-08-20 19:29:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL COMMENT '主键，自增',
  `articleId` bigint(20) NOT NULL COMMENT '文章id',
  `author` varchar(255) NOT NULL COMMENT '作者',
  `originalAuthor` varchar(255) NOT NULL COMMENT '文章原作者',
  `articleTitle` varchar(255) NOT NULL COMMENT '文章标题',
  `articleContent` longtext NOT NULL COMMENT '文章内容',
  `articleTags` varchar(255) NOT NULL COMMENT '文章标签',
  `articleType` varchar(255) NOT NULL COMMENT '文章类型',
  `articleCategories` varchar(255) NOT NULL COMMENT '文章分类',
  `publishDate` varchar(255) NOT NULL COMMENT '发布文章日期',
  `updateDate` varchar(255) NOT NULL COMMENT '更新文章日期',
  `articleUrl` varchar(255) NOT NULL COMMENT '文章url',
  `articleTabloid` varchar(255) NOT NULL COMMENT '文章摘要',
  `likes` int(11) NOT NULL COMMENT '文章喜欢数',
  `lastArticleId` bigint(20) DEFAULT NULL COMMENT '上一篇文章id',
  `nextArticleId` bigint(20) DEFAULT NULL COMMENT '下一篇文章id',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(45) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(45) DEFAULT NULL COMMENT '更新人',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
