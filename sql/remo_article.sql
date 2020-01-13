/*
Navicat MySQL Data Transfer

Source Server         : remo
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : remo_article

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-01-10 14:39:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `articleId` bigint(20) NOT NULL COMMENT '文章id',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者',
  `originalAuthor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章原作者',
  `articleTitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章标题',
  `articleContent` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容',
  `articleTags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章标签',
  `articleType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章类型',
  `articleCategories` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文章分类',
  `publishDate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布文章日期',
  `updateDate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新文章日期',
  `articleUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章url',
  `articleTabloid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章摘要',
  `likes` int(11) DEFAULT NULL COMMENT '文章喜欢数',
  `lastArticleId` bigint(20) DEFAULT NULL COMMENT '上一篇文章id',
  `nextArticleId` bigint(20) DEFAULT NULL COMMENT '下一篇文章id',
  `createTime` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `createUser` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `updateUser` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '0', 'vino', 'nono', 'string', '测试', 'vino', 'string', 'string', 'string', 'string', 'string', 'string', '0', '0', '0', '2019-08-28 23:16:47.091000', 'vino', '2019-08-29 00:37:33.868000', 'vino', '0');
INSERT INTO `article` VALUES ('2', '0', 'string', 'string', 'string', 'string', 'string', 'string', 'string', '2019-12', 'string', 'string', 'string', '0', '0', '0', '2019-12-12 11:22:02.220000', 'vino', null, null, '0');

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `articleId` int(11) NOT NULL,
  `tagId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of article_tag
-- ----------------------------
INSERT INTO `article_tag` VALUES ('0', '2');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tagId` int(10) NOT NULL AUTO_INCREMENT,
  `tagDesc` varchar(100) DEFAULT NULL,
  `tagType` char(1) NOT NULL DEFAULT 'F' COMMENT 'F - Field\nC - Category',
  `parentId` int(10) DEFAULT NULL,
  `orderSeq` int(5) DEFAULT NULL,
  `createTime` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `createUser` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `updateUser` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`tagId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('2', '生活', 'F', null, '2', '2019-12-11 14:25:11.119000', 'vino', null, null, null);
