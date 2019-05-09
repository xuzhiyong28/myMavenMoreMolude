/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50636
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50636
 File Encoding         : 65001

 Date: 09/05/2019 22:51:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `publishDate` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出版日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `title`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (1, 'Java编程思想', 98.50, '2005-01-02 00:00:00');
INSERT INTO `books` VALUES (2, 'HeadFirst设计模式', 55.70, '2010-11-09 00:00:00');
INSERT INTO `books` VALUES (3, '第一行Android代码', 69.90, '2015-06-23 00:00:00');
INSERT INTO `books` VALUES (4, 'C++编程思想', 88.50, '2004-01-09 00:00:00');
INSERT INTO `books` VALUES (5, 'HeadFirst Java', 55.70, '2013-12-17 00:00:00');
INSERT INTO `books` VALUES (6, '疯狂Android', 19.50, '2014-07-31 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
