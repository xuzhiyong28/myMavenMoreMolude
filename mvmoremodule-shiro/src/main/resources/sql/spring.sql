/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50636
 Source Host           : localhost:3306
 Source Schema         : spring

 Target Server Type    : MySQL
 Target Server Version : 50636
 File Encoding         : 65001

 Date: 31/01/2019 20:27:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for learn_resource
-- ----------------------------
DROP TABLE IF EXISTS `learn_resource`;
CREATE TABLE `learn_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址链接',
  `group_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1891 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of learn_resource
-- ----------------------------
INSERT INTO `learn_resource` VALUES (999, '官方SpriongBoot例子', '官方SpriongBoot例子', 'https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples', 1);
INSERT INTO `learn_resource` VALUES (1000, '龙果学院', 'Spring Boot 教程系列学习', 'http://www.roncoo.com/article/detail/124661', 1);
INSERT INTO `learn_resource` VALUES (1001, '嘟嘟_0', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_0.html', 2);
INSERT INTO `learn_resource` VALUES (1002, '嘟嘟_1', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_1.html', 2);
INSERT INTO `learn_resource` VALUES (1003, '嘟嘟_2', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_2.html', 2);
INSERT INTO `learn_resource` VALUES (1004, '嘟嘟_3', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_3.html', 2);
INSERT INTO `learn_resource` VALUES (1005, '嘟嘟_4', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_4.html', 2);
INSERT INTO `learn_resource` VALUES (1006, '嘟嘟_5', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_5.html', 2);
INSERT INTO `learn_resource` VALUES (1007, '嘟嘟_6', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_6.html', 2);
INSERT INTO `learn_resource` VALUES (1008, '嘟嘟_7', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_7.html', 2);
INSERT INTO `learn_resource` VALUES (1009, '嘟嘟_8', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_8.html', 2);
INSERT INTO `learn_resource` VALUES (1010, '嘟嘟_9', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_9.html', 2);
INSERT INTO `learn_resource` VALUES (1011, '嘟嘟_11', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_11.html', 2);
INSERT INTO `learn_resource` VALUES (1012, '嘟嘟_12', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_12.html', 2);
INSERT INTO `learn_resource` VALUES (1013, '嘟嘟_13', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_13.html', 2);
INSERT INTO `learn_resource` VALUES (1014, '嘟嘟_14', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_14.html', 2);
INSERT INTO `learn_resource` VALUES (1015, '嘟嘟_15', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_15.html', 2);
INSERT INTO `learn_resource` VALUES (1016, '嘟嘟_16', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_16.html', 2);
INSERT INTO `learn_resource` VALUES (1017, '嘟嘟_17', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_17.html', 2);
INSERT INTO `learn_resource` VALUES (1018, '嘟嘟_18', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_18.html', 2);
INSERT INTO `learn_resource` VALUES (1019, '嘟嘟_20', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_20.html', 2);
INSERT INTO `learn_resource` VALUES (1020, '嘟嘟_21', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_21.html', 2);
INSERT INTO `learn_resource` VALUES (1021, '嘟嘟_22', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_22.html', 2);
INSERT INTO `learn_resource` VALUES (1022, '嘟嘟_23', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_23.html', 2);
INSERT INTO `learn_resource` VALUES (1023, '嘟嘟_24', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_24.html', 2);
INSERT INTO `learn_resource` VALUES (1024, '嘟嘟_25', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_25.html', 2);
INSERT INTO `learn_resource` VALUES (1025, '嘟嘟_26', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_26.html', 2);
INSERT INTO `learn_resource` VALUES (1026, '嘟嘟_27', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_27.html', 2);
INSERT INTO `learn_resource` VALUES (1027, '嘟嘟_29', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_29.html', 2);
INSERT INTO `learn_resource` VALUES (1028, '嘟嘟_30', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_30.html', 2);
INSERT INTO `learn_resource` VALUES (1029, '嘟嘟_31', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_31.html', 2);
INSERT INTO `learn_resource` VALUES (1030, '嘟嘟_32', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_32.html', 2);
INSERT INTO `learn_resource` VALUES (1031, '嘟嘟_33', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_33.html', 2);
INSERT INTO `learn_resource` VALUES (1032, '嘟嘟_34', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_34.html', 2);
INSERT INTO `learn_resource` VALUES (1033, '嘟嘟_35', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_35.html', 2);
INSERT INTO `learn_resource` VALUES (1034, '嘟嘟_36', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_36.html', 2);
INSERT INTO `learn_resource` VALUES (1035, '嘟嘟_38', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_38.html', 2);
INSERT INTO `learn_resource` VALUES (1036, '嘟嘟_39', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_39.html', 2);
INSERT INTO `learn_resource` VALUES (1037, '嘟嘟_40', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_40.html', 2);
INSERT INTO `learn_resource` VALUES (1038, '嘟嘟_41', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_41.html', 2);
INSERT INTO `learn_resource` VALUES (1039, '嘟嘟_42', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_42.html', 2);
INSERT INTO `learn_resource` VALUES (1040, '嘟嘟_43', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_43.html', 2);
INSERT INTO `learn_resource` VALUES (1041, '嘟嘟_44', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_44.html', 2);
INSERT INTO `learn_resource` VALUES (1042, '嘟嘟_45', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_45.html', 2);
INSERT INTO `learn_resource` VALUES (1043, '嘟嘟_47', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_47.html', 2);
INSERT INTO `learn_resource` VALUES (1044, '嘟嘟_48', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_48.html', 2);
INSERT INTO `learn_resource` VALUES (1045, '嘟嘟_49', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_49.html', 2);
INSERT INTO `learn_resource` VALUES (1046, '嘟嘟_50', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_50.html', 2);
INSERT INTO `learn_resource` VALUES (1047, '嘟嘟_51', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_51.html', 2);
INSERT INTO `learn_resource` VALUES (1048, '嘟嘟_52', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_52.html', 2);
INSERT INTO `learn_resource` VALUES (1049, '嘟嘟_53', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_53.html', 2);
INSERT INTO `learn_resource` VALUES (1050, '嘟嘟_54', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_54.html', 2);
INSERT INTO `learn_resource` VALUES (1051, '嘟嘟_56', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_56.html', 2);
INSERT INTO `learn_resource` VALUES (1052, '嘟嘟_57', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_57.html', 2);
INSERT INTO `learn_resource` VALUES (1053, '嘟嘟_58', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_58.html', 2);
INSERT INTO `learn_resource` VALUES (1054, '嘟嘟_59', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_59.html', 2);
INSERT INTO `learn_resource` VALUES (1055, '嘟嘟_60', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_60.html', 2);
INSERT INTO `learn_resource` VALUES (1056, '嘟嘟_61', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_61.html', 2);
INSERT INTO `learn_resource` VALUES (1057, '嘟嘟_62', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_62.html', 2);
INSERT INTO `learn_resource` VALUES (1058, '嘟嘟_63', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_63.html', 2);
INSERT INTO `learn_resource` VALUES (1059, '嘟嘟_65', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_65.html', 2);
INSERT INTO `learn_resource` VALUES (1060, '嘟嘟_66', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_66.html', 2);
INSERT INTO `learn_resource` VALUES (1061, '嘟嘟_67', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_67.html', 2);
INSERT INTO `learn_resource` VALUES (1062, '嘟嘟_68', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_68.html', 2);
INSERT INTO `learn_resource` VALUES (1063, '嘟嘟_69', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_69.html', 2);
INSERT INTO `learn_resource` VALUES (1064, '嘟嘟_70', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_70.html', 2);
INSERT INTO `learn_resource` VALUES (1065, '嘟嘟_71', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_71.html', 2);
INSERT INTO `learn_resource` VALUES (1066, '嘟嘟_72', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_72.html', 2);
INSERT INTO `learn_resource` VALUES (1067, '嘟嘟_74', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_74.html', 2);
INSERT INTO `learn_resource` VALUES (1068, '嘟嘟_75', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_75.html', 2);
INSERT INTO `learn_resource` VALUES (1069, '嘟嘟_76', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_76.html', 2);
INSERT INTO `learn_resource` VALUES (1070, '嘟嘟_77', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_77.html', 2);
INSERT INTO `learn_resource` VALUES (1071, '嘟嘟_78', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_78.html', 2);
INSERT INTO `learn_resource` VALUES (1072, '嘟嘟_79', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_79.html', 2);
INSERT INTO `learn_resource` VALUES (1073, '嘟嘟_80', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_80.html', 2);
INSERT INTO `learn_resource` VALUES (1074, '嘟嘟_81', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_81.html', 2);
INSERT INTO `learn_resource` VALUES (1075, '嘟嘟_83', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_83.html', 2);
INSERT INTO `learn_resource` VALUES (1076, '嘟嘟_84', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_84.html', 2);
INSERT INTO `learn_resource` VALUES (1077, '嘟嘟_85', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_85.html', 2);
INSERT INTO `learn_resource` VALUES (1078, '嘟嘟_86', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_86.html', 2);
INSERT INTO `learn_resource` VALUES (1079, '嘟嘟_87', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_87.html', 2);
INSERT INTO `learn_resource` VALUES (1080, '嘟嘟_88', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_88.html', 2);
INSERT INTO `learn_resource` VALUES (1081, '嘟嘟_89', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_89.html', 2);
INSERT INTO `learn_resource` VALUES (1082, '嘟嘟_90', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_90.html', 2);
INSERT INTO `learn_resource` VALUES (1083, '嘟嘟_92', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_92.html', 2);
INSERT INTO `learn_resource` VALUES (1084, '嘟嘟_93', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_93.html', 2);
INSERT INTO `learn_resource` VALUES (1085, '嘟嘟_94', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_94.html', 2);
INSERT INTO `learn_resource` VALUES (1086, '嘟嘟_95', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_95.html', 2);
INSERT INTO `learn_resource` VALUES (1087, '嘟嘟_96', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_96.html', 2);
INSERT INTO `learn_resource` VALUES (1088, '嘟嘟_97', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_97.html', 2);
INSERT INTO `learn_resource` VALUES (1089, '嘟嘟_98', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_98.html', 2);
INSERT INTO `learn_resource` VALUES (1090, '嘟嘟_99', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_99.html', 2);
INSERT INTO `learn_resource` VALUES (1091, '嘟嘟_101', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_101.html', 2);
INSERT INTO `learn_resource` VALUES (1092, '嘟嘟_102', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_102.html', 2);
INSERT INTO `learn_resource` VALUES (1093, '嘟嘟_103', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_103.html', 2);
INSERT INTO `learn_resource` VALUES (1094, '嘟嘟_104', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_104.html', 2);
INSERT INTO `learn_resource` VALUES (1095, '嘟嘟_105', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_105.html', 2);
INSERT INTO `learn_resource` VALUES (1096, '嘟嘟_106', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_106.html', 2);
INSERT INTO `learn_resource` VALUES (1097, '嘟嘟_107', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_107.html', 2);
INSERT INTO `learn_resource` VALUES (1098, '嘟嘟_108', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_108.html', 2);
INSERT INTO `learn_resource` VALUES (1099, '嘟嘟_110', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_110.html', 2);
INSERT INTO `learn_resource` VALUES (1100, '嘟嘟_111', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_111.html', 2);
INSERT INTO `learn_resource` VALUES (1101, '嘟嘟_112', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_112.html', 2);
INSERT INTO `learn_resource` VALUES (1102, '嘟嘟_113', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_113.html', 2);
INSERT INTO `learn_resource` VALUES (1103, '嘟嘟_114', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_114.html', 2);
INSERT INTO `learn_resource` VALUES (1104, '嘟嘟_115', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_115.html', 2);
INSERT INTO `learn_resource` VALUES (1105, '嘟嘟_116', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_116.html', 2);
INSERT INTO `learn_resource` VALUES (1106, '嘟嘟_117', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_117.html', 2);
INSERT INTO `learn_resource` VALUES (1107, '嘟嘟_119', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_119.html', 2);
INSERT INTO `learn_resource` VALUES (1108, '嘟嘟_120', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_120.html', 2);
INSERT INTO `learn_resource` VALUES (1109, '嘟嘟_121', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_121.html', 2);
INSERT INTO `learn_resource` VALUES (1110, '嘟嘟_122', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_122.html', 2);
INSERT INTO `learn_resource` VALUES (1111, '嘟嘟_123', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_123.html', 2);
INSERT INTO `learn_resource` VALUES (1112, '嘟嘟_124', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_124.html', 2);
INSERT INTO `learn_resource` VALUES (1113, '嘟嘟_125', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_125.html', 2);
INSERT INTO `learn_resource` VALUES (1114, '嘟嘟_126', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_126.html', 2);
INSERT INTO `learn_resource` VALUES (1115, '嘟嘟_128', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_128.html', 2);
INSERT INTO `learn_resource` VALUES (1116, '嘟嘟_129', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_129.html', 2);
INSERT INTO `learn_resource` VALUES (1117, '嘟嘟_130', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_130.html', 2);
INSERT INTO `learn_resource` VALUES (1118, '嘟嘟_131', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_131.html', 2);
INSERT INTO `learn_resource` VALUES (1119, '嘟嘟_132', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_132.html', 2);
INSERT INTO `learn_resource` VALUES (1120, '嘟嘟_133', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_133.html', 2);
INSERT INTO `learn_resource` VALUES (1121, '嘟嘟_134', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_134.html', 2);
INSERT INTO `learn_resource` VALUES (1122, '嘟嘟_135', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_135.html', 2);
INSERT INTO `learn_resource` VALUES (1123, '嘟嘟_137', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_137.html', 2);
INSERT INTO `learn_resource` VALUES (1124, '嘟嘟_138', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_138.html', 2);
INSERT INTO `learn_resource` VALUES (1125, '嘟嘟_139', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_139.html', 2);
INSERT INTO `learn_resource` VALUES (1126, '嘟嘟_140', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_140.html', 2);
INSERT INTO `learn_resource` VALUES (1127, '嘟嘟_141', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_141.html', 2);
INSERT INTO `learn_resource` VALUES (1128, '嘟嘟_142', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_142.html', 2);
INSERT INTO `learn_resource` VALUES (1129, '嘟嘟_143', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_143.html', 2);
INSERT INTO `learn_resource` VALUES (1130, '嘟嘟_144', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_144.html', 2);
INSERT INTO `learn_resource` VALUES (1131, '嘟嘟_146', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_146.html', 2);
INSERT INTO `learn_resource` VALUES (1132, '嘟嘟_147', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_147.html', 2);
INSERT INTO `learn_resource` VALUES (1133, '嘟嘟_148', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_148.html', 2);
INSERT INTO `learn_resource` VALUES (1134, '嘟嘟_149', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_149.html', 2);
INSERT INTO `learn_resource` VALUES (1135, '嘟嘟_150', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_150.html', 2);
INSERT INTO `learn_resource` VALUES (1136, '嘟嘟_151', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_151.html', 2);
INSERT INTO `learn_resource` VALUES (1137, '嘟嘟_152', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_152.html', 2);
INSERT INTO `learn_resource` VALUES (1138, '嘟嘟_153', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_153.html', 2);
INSERT INTO `learn_resource` VALUES (1139, '嘟嘟_155', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_155.html', 2);
INSERT INTO `learn_resource` VALUES (1140, '嘟嘟_156', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_156.html', 2);
INSERT INTO `learn_resource` VALUES (1141, '嘟嘟_157', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_157.html', 2);
INSERT INTO `learn_resource` VALUES (1142, '嘟嘟_158', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_158.html', 2);
INSERT INTO `learn_resource` VALUES (1143, '嘟嘟_159', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_159.html', 2);
INSERT INTO `learn_resource` VALUES (1144, '嘟嘟_160', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_160.html', 2);
INSERT INTO `learn_resource` VALUES (1145, '嘟嘟_161', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_161.html', 2);
INSERT INTO `learn_resource` VALUES (1146, '嘟嘟_162', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_162.html', 2);
INSERT INTO `learn_resource` VALUES (1147, '嘟嘟_164', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_164.html', 2);
INSERT INTO `learn_resource` VALUES (1148, '嘟嘟_165', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_165.html', 2);
INSERT INTO `learn_resource` VALUES (1149, '嘟嘟_166', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_166.html', 2);
INSERT INTO `learn_resource` VALUES (1150, '嘟嘟_167', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_167.html', 2);
INSERT INTO `learn_resource` VALUES (1151, '嘟嘟_168', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_168.html', 2);
INSERT INTO `learn_resource` VALUES (1152, '嘟嘟_169', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_169.html', 2);
INSERT INTO `learn_resource` VALUES (1153, '嘟嘟_170', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_170.html', 2);
INSERT INTO `learn_resource` VALUES (1154, '嘟嘟_171', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_171.html', 2);
INSERT INTO `learn_resource` VALUES (1155, '嘟嘟_173', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_173.html', 2);
INSERT INTO `learn_resource` VALUES (1156, '嘟嘟_174', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_174.html', 2);
INSERT INTO `learn_resource` VALUES (1157, '嘟嘟_175', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_175.html', 2);
INSERT INTO `learn_resource` VALUES (1158, '嘟嘟_176', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_176.html', 2);
INSERT INTO `learn_resource` VALUES (1159, '嘟嘟_177', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_177.html', 2);
INSERT INTO `learn_resource` VALUES (1160, '嘟嘟_178', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_178.html', 2);
INSERT INTO `learn_resource` VALUES (1161, '嘟嘟_179', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_179.html', 2);
INSERT INTO `learn_resource` VALUES (1162, '嘟嘟_180', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_180.html', 2);
INSERT INTO `learn_resource` VALUES (1163, '嘟嘟_182', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_182.html', 2);
INSERT INTO `learn_resource` VALUES (1164, '嘟嘟_183', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_183.html', 2);
INSERT INTO `learn_resource` VALUES (1165, '嘟嘟_184', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_184.html', 2);
INSERT INTO `learn_resource` VALUES (1166, '嘟嘟_185', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_185.html', 2);
INSERT INTO `learn_resource` VALUES (1167, '嘟嘟_186', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_186.html', 2);
INSERT INTO `learn_resource` VALUES (1168, '嘟嘟_187', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_187.html', 2);
INSERT INTO `learn_resource` VALUES (1169, '嘟嘟_188', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_188.html', 2);
INSERT INTO `learn_resource` VALUES (1170, '嘟嘟_189', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_189.html', 2);
INSERT INTO `learn_resource` VALUES (1171, '嘟嘟_191', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_191.html', 2);
INSERT INTO `learn_resource` VALUES (1172, '嘟嘟_192', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_192.html', 2);
INSERT INTO `learn_resource` VALUES (1173, '嘟嘟_193', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_193.html', 2);
INSERT INTO `learn_resource` VALUES (1174, '嘟嘟_194', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_194.html', 2);
INSERT INTO `learn_resource` VALUES (1175, '嘟嘟_195', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_195.html', 2);
INSERT INTO `learn_resource` VALUES (1176, '嘟嘟_196', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_196.html', 2);
INSERT INTO `learn_resource` VALUES (1177, '嘟嘟_197', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_197.html', 2);
INSERT INTO `learn_resource` VALUES (1178, '嘟嘟_198', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_198.html', 2);
INSERT INTO `learn_resource` VALUES (1179, '嘟嘟_200', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_200.html', 2);
INSERT INTO `learn_resource` VALUES (1180, '嘟嘟_201', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_201.html', 2);
INSERT INTO `learn_resource` VALUES (1181, '嘟嘟_202', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_202.html', 2);
INSERT INTO `learn_resource` VALUES (1182, '嘟嘟_203', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_203.html', 2);
INSERT INTO `learn_resource` VALUES (1183, '嘟嘟_204', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_204.html', 2);
INSERT INTO `learn_resource` VALUES (1184, '嘟嘟_205', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_205.html', 2);
INSERT INTO `learn_resource` VALUES (1185, '嘟嘟_206', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_206.html', 2);
INSERT INTO `learn_resource` VALUES (1186, '嘟嘟_207', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_207.html', 2);
INSERT INTO `learn_resource` VALUES (1187, '嘟嘟_209', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_209.html', 2);
INSERT INTO `learn_resource` VALUES (1188, '嘟嘟_210', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_210.html', 2);
INSERT INTO `learn_resource` VALUES (1189, '嘟嘟_211', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_211.html', 2);
INSERT INTO `learn_resource` VALUES (1190, '嘟嘟_212', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_212.html', 2);
INSERT INTO `learn_resource` VALUES (1191, '嘟嘟_213', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_213.html', 2);
INSERT INTO `learn_resource` VALUES (1192, '嘟嘟_214', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_214.html', 2);
INSERT INTO `learn_resource` VALUES (1193, '嘟嘟_215', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_215.html', 2);
INSERT INTO `learn_resource` VALUES (1194, '嘟嘟_216', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_216.html', 2);
INSERT INTO `learn_resource` VALUES (1195, '嘟嘟_218', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_218.html', 2);
INSERT INTO `learn_resource` VALUES (1196, '嘟嘟_219', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_219.html', 2);
INSERT INTO `learn_resource` VALUES (1197, '嘟嘟_220', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_220.html', 2);
INSERT INTO `learn_resource` VALUES (1198, '嘟嘟_221', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_221.html', 2);
INSERT INTO `learn_resource` VALUES (1199, '嘟嘟_222', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_222.html', 2);
INSERT INTO `learn_resource` VALUES (1200, '嘟嘟_223', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_223.html', 2);
INSERT INTO `learn_resource` VALUES (1201, '嘟嘟_224', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_224.html', 2);
INSERT INTO `learn_resource` VALUES (1202, '嘟嘟_225', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_225.html', 2);
INSERT INTO `learn_resource` VALUES (1203, '嘟嘟_227', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_227.html', 2);
INSERT INTO `learn_resource` VALUES (1204, '嘟嘟_228', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_228.html', 2);
INSERT INTO `learn_resource` VALUES (1205, '嘟嘟_229', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_229.html', 2);
INSERT INTO `learn_resource` VALUES (1206, '嘟嘟_230', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_230.html', 2);
INSERT INTO `learn_resource` VALUES (1207, '嘟嘟_231', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_231.html', 2);
INSERT INTO `learn_resource` VALUES (1208, '嘟嘟_232', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_232.html', 2);
INSERT INTO `learn_resource` VALUES (1209, '嘟嘟_233', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_233.html', 2);
INSERT INTO `learn_resource` VALUES (1210, '嘟嘟_234', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_234.html', 2);
INSERT INTO `learn_resource` VALUES (1211, '嘟嘟_236', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_236.html', 2);
INSERT INTO `learn_resource` VALUES (1212, '嘟嘟_237', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_237.html', 2);
INSERT INTO `learn_resource` VALUES (1213, '嘟嘟_238', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_238.html', 2);
INSERT INTO `learn_resource` VALUES (1214, '嘟嘟_239', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_239.html', 2);
INSERT INTO `learn_resource` VALUES (1215, '嘟嘟_240', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_240.html', 2);
INSERT INTO `learn_resource` VALUES (1216, '嘟嘟_241', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_241.html', 2);
INSERT INTO `learn_resource` VALUES (1217, '嘟嘟_242', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_242.html', 2);
INSERT INTO `learn_resource` VALUES (1218, '嘟嘟_243', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_243.html', 2);
INSERT INTO `learn_resource` VALUES (1219, '嘟嘟_245', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_245.html', 2);
INSERT INTO `learn_resource` VALUES (1220, '嘟嘟_246', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_246.html', 2);
INSERT INTO `learn_resource` VALUES (1221, '嘟嘟_247', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_247.html', 2);
INSERT INTO `learn_resource` VALUES (1222, '嘟嘟_248', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_248.html', 2);
INSERT INTO `learn_resource` VALUES (1223, '嘟嘟_249', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_249.html', 2);
INSERT INTO `learn_resource` VALUES (1224, '嘟嘟_250', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_250.html', 2);
INSERT INTO `learn_resource` VALUES (1225, '嘟嘟_251', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_251.html', 2);
INSERT INTO `learn_resource` VALUES (1226, '嘟嘟_252', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_252.html', 2);
INSERT INTO `learn_resource` VALUES (1227, '嘟嘟_254', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_254.html', 2);
INSERT INTO `learn_resource` VALUES (1228, '嘟嘟_255', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_255.html', 2);
INSERT INTO `learn_resource` VALUES (1229, '嘟嘟_256', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_256.html', 2);
INSERT INTO `learn_resource` VALUES (1230, '嘟嘟_257', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_257.html', 2);
INSERT INTO `learn_resource` VALUES (1231, '嘟嘟_258', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_258.html', 2);
INSERT INTO `learn_resource` VALUES (1232, '嘟嘟_259', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_259.html', 2);
INSERT INTO `learn_resource` VALUES (1233, '嘟嘟_260', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_260.html', 2);
INSERT INTO `learn_resource` VALUES (1234, '嘟嘟_261', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_261.html', 2);
INSERT INTO `learn_resource` VALUES (1235, '嘟嘟_263', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_263.html', 2);
INSERT INTO `learn_resource` VALUES (1236, '嘟嘟_264', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_264.html', 2);
INSERT INTO `learn_resource` VALUES (1237, '嘟嘟_265', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_265.html', 2);
INSERT INTO `learn_resource` VALUES (1238, '嘟嘟_266', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_266.html', 2);
INSERT INTO `learn_resource` VALUES (1239, '嘟嘟_267', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_267.html', 2);
INSERT INTO `learn_resource` VALUES (1240, '嘟嘟_268', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_268.html', 2);
INSERT INTO `learn_resource` VALUES (1241, '嘟嘟_269', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_269.html', 2);
INSERT INTO `learn_resource` VALUES (1242, '嘟嘟_270', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_270.html', 2);
INSERT INTO `learn_resource` VALUES (1243, '嘟嘟_272', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_272.html', 2);
INSERT INTO `learn_resource` VALUES (1244, '嘟嘟_273', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_273.html', 2);
INSERT INTO `learn_resource` VALUES (1245, '嘟嘟_274', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_274.html', 2);
INSERT INTO `learn_resource` VALUES (1246, '嘟嘟_275', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_275.html', 2);
INSERT INTO `learn_resource` VALUES (1247, '嘟嘟_276', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_276.html', 2);
INSERT INTO `learn_resource` VALUES (1248, '嘟嘟_277', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_277.html', 2);
INSERT INTO `learn_resource` VALUES (1249, '嘟嘟_278', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_278.html', 2);
INSERT INTO `learn_resource` VALUES (1250, '嘟嘟_279', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_279.html', 2);
INSERT INTO `learn_resource` VALUES (1251, '嘟嘟_281', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_281.html', 2);
INSERT INTO `learn_resource` VALUES (1252, '嘟嘟_282', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_282.html', 2);
INSERT INTO `learn_resource` VALUES (1253, '嘟嘟_283', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_283.html', 2);
INSERT INTO `learn_resource` VALUES (1254, '嘟嘟_284', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_284.html', 2);
INSERT INTO `learn_resource` VALUES (1255, '嘟嘟_285', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_285.html', 2);
INSERT INTO `learn_resource` VALUES (1256, '嘟嘟_286', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_286.html', 2);
INSERT INTO `learn_resource` VALUES (1257, '嘟嘟_287', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_287.html', 2);
INSERT INTO `learn_resource` VALUES (1258, '嘟嘟_288', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_288.html', 2);
INSERT INTO `learn_resource` VALUES (1259, '嘟嘟_290', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_290.html', 2);
INSERT INTO `learn_resource` VALUES (1260, '嘟嘟_291', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_291.html', 2);
INSERT INTO `learn_resource` VALUES (1261, '嘟嘟_292', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_292.html', 2);
INSERT INTO `learn_resource` VALUES (1262, '嘟嘟_293', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_293.html', 2);
INSERT INTO `learn_resource` VALUES (1263, '嘟嘟_294', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_294.html', 2);
INSERT INTO `learn_resource` VALUES (1264, '嘟嘟_295', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_295.html', 2);
INSERT INTO `learn_resource` VALUES (1265, '嘟嘟_296', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_296.html', 2);
INSERT INTO `learn_resource` VALUES (1266, '嘟嘟_297', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_297.html', 2);
INSERT INTO `learn_resource` VALUES (1267, '嘟嘟_299', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_299.html', 2);
INSERT INTO `learn_resource` VALUES (1268, '嘟嘟_300', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_300.html', 2);
INSERT INTO `learn_resource` VALUES (1269, '嘟嘟_301', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_301.html', 2);
INSERT INTO `learn_resource` VALUES (1270, '嘟嘟_302', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_302.html', 2);
INSERT INTO `learn_resource` VALUES (1271, '嘟嘟_303', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_303.html', 2);
INSERT INTO `learn_resource` VALUES (1272, '嘟嘟_304', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_304.html', 2);
INSERT INTO `learn_resource` VALUES (1273, '嘟嘟_305', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_305.html', 2);
INSERT INTO `learn_resource` VALUES (1274, '嘟嘟_306', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_306.html', 2);
INSERT INTO `learn_resource` VALUES (1275, '嘟嘟_308', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_308.html', 2);
INSERT INTO `learn_resource` VALUES (1276, '嘟嘟_309', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_309.html', 2);
INSERT INTO `learn_resource` VALUES (1277, '嘟嘟_310', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_310.html', 2);
INSERT INTO `learn_resource` VALUES (1278, '嘟嘟_311', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_311.html', 2);
INSERT INTO `learn_resource` VALUES (1279, '嘟嘟_312', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_312.html', 2);
INSERT INTO `learn_resource` VALUES (1280, '嘟嘟_313', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_313.html', 2);
INSERT INTO `learn_resource` VALUES (1281, '嘟嘟_314', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_314.html', 2);
INSERT INTO `learn_resource` VALUES (1282, '嘟嘟_315', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_315.html', 2);
INSERT INTO `learn_resource` VALUES (1283, '嘟嘟_317', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_317.html', 2);
INSERT INTO `learn_resource` VALUES (1284, '嘟嘟_318', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_318.html', 2);
INSERT INTO `learn_resource` VALUES (1285, '嘟嘟_319', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_319.html', 2);
INSERT INTO `learn_resource` VALUES (1286, '嘟嘟_320', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_320.html', 2);
INSERT INTO `learn_resource` VALUES (1287, '嘟嘟_321', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_321.html', 2);
INSERT INTO `learn_resource` VALUES (1288, '嘟嘟_322', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_322.html', 2);
INSERT INTO `learn_resource` VALUES (1289, '嘟嘟_323', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_323.html', 2);
INSERT INTO `learn_resource` VALUES (1290, '嘟嘟_324', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_324.html', 2);
INSERT INTO `learn_resource` VALUES (1291, '嘟嘟_326', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_326.html', 2);
INSERT INTO `learn_resource` VALUES (1292, '嘟嘟_327', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_327.html', 2);
INSERT INTO `learn_resource` VALUES (1293, '嘟嘟_328', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_328.html', 2);
INSERT INTO `learn_resource` VALUES (1294, '嘟嘟_329', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_329.html', 2);
INSERT INTO `learn_resource` VALUES (1295, '嘟嘟_330', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_330.html', 2);
INSERT INTO `learn_resource` VALUES (1296, '嘟嘟_331', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_331.html', 2);
INSERT INTO `learn_resource` VALUES (1297, '嘟嘟_332', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_332.html', 2);
INSERT INTO `learn_resource` VALUES (1298, '嘟嘟_333', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_333.html', 2);
INSERT INTO `learn_resource` VALUES (1299, '嘟嘟_335', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_335.html', 2);
INSERT INTO `learn_resource` VALUES (1300, '嘟嘟_336', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_336.html', 2);
INSERT INTO `learn_resource` VALUES (1301, '嘟嘟_337', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_337.html', 2);
INSERT INTO `learn_resource` VALUES (1302, '嘟嘟_338', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_338.html', 2);
INSERT INTO `learn_resource` VALUES (1303, '嘟嘟_339', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_339.html', 2);
INSERT INTO `learn_resource` VALUES (1304, '嘟嘟_340', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_340.html', 2);
INSERT INTO `learn_resource` VALUES (1305, '嘟嘟_341', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_341.html', 2);
INSERT INTO `learn_resource` VALUES (1306, '嘟嘟_342', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_342.html', 2);
INSERT INTO `learn_resource` VALUES (1307, '嘟嘟_344', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_344.html', 2);
INSERT INTO `learn_resource` VALUES (1308, '嘟嘟_345', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_345.html', 2);
INSERT INTO `learn_resource` VALUES (1309, '嘟嘟_346', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_346.html', 2);
INSERT INTO `learn_resource` VALUES (1310, '嘟嘟_347', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_347.html', 2);
INSERT INTO `learn_resource` VALUES (1311, '嘟嘟_348', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_348.html', 2);
INSERT INTO `learn_resource` VALUES (1312, '嘟嘟_349', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_349.html', 2);
INSERT INTO `learn_resource` VALUES (1313, '嘟嘟_350', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_350.html', 2);
INSERT INTO `learn_resource` VALUES (1314, '嘟嘟_351', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_351.html', 2);
INSERT INTO `learn_resource` VALUES (1315, '嘟嘟_353', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_353.html', 2);
INSERT INTO `learn_resource` VALUES (1316, '嘟嘟_354', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_354.html', 2);
INSERT INTO `learn_resource` VALUES (1317, '嘟嘟_355', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_355.html', 2);
INSERT INTO `learn_resource` VALUES (1318, '嘟嘟_356', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_356.html', 2);
INSERT INTO `learn_resource` VALUES (1319, '嘟嘟_357', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_357.html', 2);
INSERT INTO `learn_resource` VALUES (1320, '嘟嘟_358', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_358.html', 2);
INSERT INTO `learn_resource` VALUES (1321, '嘟嘟_359', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_359.html', 2);
INSERT INTO `learn_resource` VALUES (1322, '嘟嘟_360', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_360.html', 2);
INSERT INTO `learn_resource` VALUES (1323, '嘟嘟_362', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_362.html', 2);
INSERT INTO `learn_resource` VALUES (1324, '嘟嘟_363', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_363.html', 2);
INSERT INTO `learn_resource` VALUES (1325, '嘟嘟_364', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_364.html', 2);
INSERT INTO `learn_resource` VALUES (1326, '嘟嘟_365', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_365.html', 2);
INSERT INTO `learn_resource` VALUES (1327, '嘟嘟_366', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_366.html', 2);
INSERT INTO `learn_resource` VALUES (1328, '嘟嘟_367', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_367.html', 2);
INSERT INTO `learn_resource` VALUES (1329, '嘟嘟_368', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_368.html', 2);
INSERT INTO `learn_resource` VALUES (1330, '嘟嘟_369', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_369.html', 2);
INSERT INTO `learn_resource` VALUES (1331, '嘟嘟_371', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_371.html', 2);
INSERT INTO `learn_resource` VALUES (1332, '嘟嘟_372', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_372.html', 2);
INSERT INTO `learn_resource` VALUES (1333, '嘟嘟_373', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_373.html', 2);
INSERT INTO `learn_resource` VALUES (1334, '嘟嘟_374', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_374.html', 2);
INSERT INTO `learn_resource` VALUES (1335, '嘟嘟_375', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_375.html', 2);
INSERT INTO `learn_resource` VALUES (1336, '嘟嘟_376', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_376.html', 2);
INSERT INTO `learn_resource` VALUES (1337, '嘟嘟_377', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_377.html', 2);
INSERT INTO `learn_resource` VALUES (1338, '嘟嘟_378', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_378.html', 2);
INSERT INTO `learn_resource` VALUES (1339, '嘟嘟_380', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_380.html', 2);
INSERT INTO `learn_resource` VALUES (1340, '嘟嘟_381', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_381.html', 2);
INSERT INTO `learn_resource` VALUES (1341, '嘟嘟_382', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_382.html', 2);
INSERT INTO `learn_resource` VALUES (1342, '嘟嘟_383', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_383.html', 2);
INSERT INTO `learn_resource` VALUES (1343, '嘟嘟_384', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_384.html', 2);
INSERT INTO `learn_resource` VALUES (1344, '嘟嘟_385', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_385.html', 2);
INSERT INTO `learn_resource` VALUES (1345, '嘟嘟_386', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_386.html', 2);
INSERT INTO `learn_resource` VALUES (1346, '嘟嘟_387', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_387.html', 2);
INSERT INTO `learn_resource` VALUES (1347, '嘟嘟_389', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_389.html', 2);
INSERT INTO `learn_resource` VALUES (1348, '嘟嘟_390', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_390.html', 2);
INSERT INTO `learn_resource` VALUES (1349, '嘟嘟_391', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_391.html', 2);
INSERT INTO `learn_resource` VALUES (1350, '嘟嘟_392', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_392.html', 2);
INSERT INTO `learn_resource` VALUES (1351, '嘟嘟_393', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_393.html', 2);
INSERT INTO `learn_resource` VALUES (1352, '嘟嘟_394', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_394.html', 2);
INSERT INTO `learn_resource` VALUES (1353, '嘟嘟_395', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_395.html', 2);
INSERT INTO `learn_resource` VALUES (1354, '嘟嘟_396', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_396.html', 2);
INSERT INTO `learn_resource` VALUES (1355, '嘟嘟_398', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_398.html', 2);
INSERT INTO `learn_resource` VALUES (1356, '嘟嘟_399', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_399.html', 2);
INSERT INTO `learn_resource` VALUES (1357, '嘟嘟_400', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_400.html', 2);
INSERT INTO `learn_resource` VALUES (1358, '嘟嘟_401', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_401.html', 2);
INSERT INTO `learn_resource` VALUES (1359, '嘟嘟_402', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_402.html', 2);
INSERT INTO `learn_resource` VALUES (1360, '嘟嘟_403', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_403.html', 2);
INSERT INTO `learn_resource` VALUES (1361, '嘟嘟_404', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_404.html', 2);
INSERT INTO `learn_resource` VALUES (1362, '嘟嘟_405', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_405.html', 2);
INSERT INTO `learn_resource` VALUES (1363, '嘟嘟_407', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_407.html', 2);
INSERT INTO `learn_resource` VALUES (1364, '嘟嘟_408', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_408.html', 2);
INSERT INTO `learn_resource` VALUES (1365, '嘟嘟_409', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_409.html', 2);
INSERT INTO `learn_resource` VALUES (1366, '嘟嘟_410', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_410.html', 2);
INSERT INTO `learn_resource` VALUES (1367, '嘟嘟_411', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_411.html', 2);
INSERT INTO `learn_resource` VALUES (1368, '嘟嘟_412', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_412.html', 2);
INSERT INTO `learn_resource` VALUES (1369, '嘟嘟_413', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_413.html', 2);
INSERT INTO `learn_resource` VALUES (1370, '嘟嘟_414', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_414.html', 2);
INSERT INTO `learn_resource` VALUES (1371, '嘟嘟_416', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_416.html', 2);
INSERT INTO `learn_resource` VALUES (1372, '嘟嘟_417', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_417.html', 2);
INSERT INTO `learn_resource` VALUES (1373, '嘟嘟_418', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_418.html', 2);
INSERT INTO `learn_resource` VALUES (1374, '嘟嘟_419', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_419.html', 2);
INSERT INTO `learn_resource` VALUES (1375, '嘟嘟_420', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_420.html', 2);
INSERT INTO `learn_resource` VALUES (1376, '嘟嘟_421', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_421.html', 2);
INSERT INTO `learn_resource` VALUES (1377, '嘟嘟_422', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_422.html', 2);
INSERT INTO `learn_resource` VALUES (1378, '嘟嘟_423', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_423.html', 2);
INSERT INTO `learn_resource` VALUES (1379, '嘟嘟_425', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_425.html', 2);
INSERT INTO `learn_resource` VALUES (1380, '嘟嘟_426', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_426.html', 2);
INSERT INTO `learn_resource` VALUES (1381, '嘟嘟_427', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_427.html', 2);
INSERT INTO `learn_resource` VALUES (1382, '嘟嘟_428', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_428.html', 2);
INSERT INTO `learn_resource` VALUES (1383, '嘟嘟_429', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_429.html', 2);
INSERT INTO `learn_resource` VALUES (1384, '嘟嘟_430', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_430.html', 2);
INSERT INTO `learn_resource` VALUES (1385, '嘟嘟_431', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_431.html', 2);
INSERT INTO `learn_resource` VALUES (1386, '嘟嘟_432', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_432.html', 2);
INSERT INTO `learn_resource` VALUES (1387, '嘟嘟_434', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_434.html', 2);
INSERT INTO `learn_resource` VALUES (1388, '嘟嘟_435', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_435.html', 2);
INSERT INTO `learn_resource` VALUES (1389, '嘟嘟_436', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_436.html', 2);
INSERT INTO `learn_resource` VALUES (1390, '嘟嘟_437', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_437.html', 2);
INSERT INTO `learn_resource` VALUES (1391, '嘟嘟_438', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_438.html', 2);
INSERT INTO `learn_resource` VALUES (1392, '嘟嘟_439', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_439.html', 2);
INSERT INTO `learn_resource` VALUES (1393, '嘟嘟_440', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_440.html', 2);
INSERT INTO `learn_resource` VALUES (1394, '嘟嘟_441', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_441.html', 2);
INSERT INTO `learn_resource` VALUES (1395, '嘟嘟_443', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_443.html', 2);
INSERT INTO `learn_resource` VALUES (1396, '嘟嘟_444', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_444.html', 2);
INSERT INTO `learn_resource` VALUES (1397, '嘟嘟_445', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_445.html', 2);
INSERT INTO `learn_resource` VALUES (1398, '嘟嘟_446', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_446.html', 2);
INSERT INTO `learn_resource` VALUES (1399, '嘟嘟_447', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_447.html', 2);
INSERT INTO `learn_resource` VALUES (1400, '嘟嘟_448', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_448.html', 2);
INSERT INTO `learn_resource` VALUES (1401, '嘟嘟_449', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_449.html', 2);
INSERT INTO `learn_resource` VALUES (1402, '嘟嘟_450', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_450.html', 2);
INSERT INTO `learn_resource` VALUES (1403, '嘟嘟_452', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_452.html', 2);
INSERT INTO `learn_resource` VALUES (1404, '嘟嘟_453', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_453.html', 2);
INSERT INTO `learn_resource` VALUES (1405, '嘟嘟_454', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_454.html', 2);
INSERT INTO `learn_resource` VALUES (1406, '嘟嘟_455', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_455.html', 2);
INSERT INTO `learn_resource` VALUES (1407, '嘟嘟_456', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_456.html', 2);
INSERT INTO `learn_resource` VALUES (1408, '嘟嘟_457', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_457.html', 2);
INSERT INTO `learn_resource` VALUES (1409, '嘟嘟_458', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_458.html', 2);
INSERT INTO `learn_resource` VALUES (1410, '嘟嘟_459', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_459.html', 2);
INSERT INTO `learn_resource` VALUES (1411, '嘟嘟_461', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_461.html', 2);
INSERT INTO `learn_resource` VALUES (1412, '嘟嘟_462', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_462.html', 2);
INSERT INTO `learn_resource` VALUES (1413, '嘟嘟_463', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_463.html', 2);
INSERT INTO `learn_resource` VALUES (1414, '嘟嘟_464', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_464.html', 2);
INSERT INTO `learn_resource` VALUES (1415, '嘟嘟_465', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_465.html', 2);
INSERT INTO `learn_resource` VALUES (1416, '嘟嘟_466', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_466.html', 2);
INSERT INTO `learn_resource` VALUES (1417, '嘟嘟_467', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_467.html', 2);
INSERT INTO `learn_resource` VALUES (1418, '嘟嘟_468', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_468.html', 2);
INSERT INTO `learn_resource` VALUES (1419, '嘟嘟_470', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_470.html', 2);
INSERT INTO `learn_resource` VALUES (1420, '嘟嘟_471', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_471.html', 2);
INSERT INTO `learn_resource` VALUES (1421, '嘟嘟_472', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_472.html', 2);
INSERT INTO `learn_resource` VALUES (1422, '嘟嘟_473', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_473.html', 2);
INSERT INTO `learn_resource` VALUES (1423, '嘟嘟_474', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_474.html', 2);
INSERT INTO `learn_resource` VALUES (1424, '嘟嘟_475', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_475.html', 2);
INSERT INTO `learn_resource` VALUES (1425, '嘟嘟_476', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_476.html', 2);
INSERT INTO `learn_resource` VALUES (1426, '嘟嘟_477', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_477.html', 2);
INSERT INTO `learn_resource` VALUES (1427, '嘟嘟_479', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_479.html', 2);
INSERT INTO `learn_resource` VALUES (1428, '嘟嘟_480', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_480.html', 2);
INSERT INTO `learn_resource` VALUES (1429, '嘟嘟_481', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_481.html', 2);
INSERT INTO `learn_resource` VALUES (1430, '嘟嘟_482', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_482.html', 2);
INSERT INTO `learn_resource` VALUES (1431, '嘟嘟_483', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_483.html', 2);
INSERT INTO `learn_resource` VALUES (1432, '嘟嘟_484', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_484.html', 2);
INSERT INTO `learn_resource` VALUES (1433, '嘟嘟_485', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_485.html', 2);
INSERT INTO `learn_resource` VALUES (1434, '嘟嘟_486', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_486.html', 2);
INSERT INTO `learn_resource` VALUES (1435, '嘟嘟_488', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_488.html', 2);
INSERT INTO `learn_resource` VALUES (1436, '嘟嘟_489', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_489.html', 2);
INSERT INTO `learn_resource` VALUES (1437, '嘟嘟_490', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_490.html', 2);
INSERT INTO `learn_resource` VALUES (1438, '嘟嘟_491', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_491.html', 2);
INSERT INTO `learn_resource` VALUES (1439, '嘟嘟_492', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_492.html', 2);
INSERT INTO `learn_resource` VALUES (1440, '嘟嘟_493', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_493.html', 2);
INSERT INTO `learn_resource` VALUES (1441, '嘟嘟_494', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_494.html', 2);
INSERT INTO `learn_resource` VALUES (1442, '嘟嘟_495', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_495.html', 2);
INSERT INTO `learn_resource` VALUES (1443, '嘟嘟_497', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_497.html', 2);
INSERT INTO `learn_resource` VALUES (1444, '嘟嘟_498', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_498.html', 2);
INSERT INTO `learn_resource` VALUES (1445, '嘟嘟_499', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_499.html', 2);
INSERT INTO `learn_resource` VALUES (1446, '嘟嘟_500', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_500.html', 2);
INSERT INTO `learn_resource` VALUES (1447, '嘟嘟_501', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_501.html', 2);
INSERT INTO `learn_resource` VALUES (1448, '嘟嘟_502', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_502.html', 2);
INSERT INTO `learn_resource` VALUES (1449, '嘟嘟_503', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_503.html', 2);
INSERT INTO `learn_resource` VALUES (1450, '嘟嘟_504', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_504.html', 2);
INSERT INTO `learn_resource` VALUES (1451, '嘟嘟_506', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_506.html', 2);
INSERT INTO `learn_resource` VALUES (1452, '嘟嘟_507', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_507.html', 2);
INSERT INTO `learn_resource` VALUES (1453, '嘟嘟_508', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_508.html', 2);
INSERT INTO `learn_resource` VALUES (1454, '嘟嘟_509', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_509.html', 2);
INSERT INTO `learn_resource` VALUES (1455, '嘟嘟_510', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_510.html', 2);
INSERT INTO `learn_resource` VALUES (1456, '嘟嘟_511', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_511.html', 2);
INSERT INTO `learn_resource` VALUES (1457, '嘟嘟_512', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_512.html', 2);
INSERT INTO `learn_resource` VALUES (1458, '嘟嘟_513', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_513.html', 2);
INSERT INTO `learn_resource` VALUES (1459, '嘟嘟_515', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_515.html', 2);
INSERT INTO `learn_resource` VALUES (1460, '嘟嘟_516', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_516.html', 2);
INSERT INTO `learn_resource` VALUES (1461, '嘟嘟_517', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_517.html', 2);
INSERT INTO `learn_resource` VALUES (1462, '嘟嘟_518', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_518.html', 2);
INSERT INTO `learn_resource` VALUES (1463, '嘟嘟_519', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_519.html', 2);
INSERT INTO `learn_resource` VALUES (1464, '嘟嘟_520', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_520.html', 2);
INSERT INTO `learn_resource` VALUES (1465, '嘟嘟_521', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_521.html', 2);
INSERT INTO `learn_resource` VALUES (1466, '嘟嘟_522', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_522.html', 2);
INSERT INTO `learn_resource` VALUES (1467, '嘟嘟_524', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_524.html', 2);
INSERT INTO `learn_resource` VALUES (1468, '嘟嘟_525', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_525.html', 2);
INSERT INTO `learn_resource` VALUES (1469, '嘟嘟_526', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_526.html', 2);
INSERT INTO `learn_resource` VALUES (1470, '嘟嘟_527', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_527.html', 2);
INSERT INTO `learn_resource` VALUES (1471, '嘟嘟_528', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_528.html', 2);
INSERT INTO `learn_resource` VALUES (1472, '嘟嘟_529', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_529.html', 2);
INSERT INTO `learn_resource` VALUES (1473, '嘟嘟_530', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_530.html', 2);
INSERT INTO `learn_resource` VALUES (1474, '嘟嘟_531', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_531.html', 2);
INSERT INTO `learn_resource` VALUES (1475, '嘟嘟_533', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_533.html', 2);
INSERT INTO `learn_resource` VALUES (1476, '嘟嘟_534', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_534.html', 2);
INSERT INTO `learn_resource` VALUES (1477, '嘟嘟_535', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_535.html', 2);
INSERT INTO `learn_resource` VALUES (1478, '嘟嘟_536', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_536.html', 2);
INSERT INTO `learn_resource` VALUES (1479, '嘟嘟_537', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_537.html', 2);
INSERT INTO `learn_resource` VALUES (1480, '嘟嘟_538', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_538.html', 2);
INSERT INTO `learn_resource` VALUES (1481, '嘟嘟_539', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_539.html', 2);
INSERT INTO `learn_resource` VALUES (1482, '嘟嘟_540', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_540.html', 2);
INSERT INTO `learn_resource` VALUES (1483, '嘟嘟_542', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_542.html', 2);
INSERT INTO `learn_resource` VALUES (1484, '嘟嘟_543', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_543.html', 2);
INSERT INTO `learn_resource` VALUES (1485, '嘟嘟_544', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_544.html', 2);
INSERT INTO `learn_resource` VALUES (1486, '嘟嘟_545', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_545.html', 2);
INSERT INTO `learn_resource` VALUES (1487, '嘟嘟_546', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_546.html', 2);
INSERT INTO `learn_resource` VALUES (1488, '嘟嘟_547', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_547.html', 2);
INSERT INTO `learn_resource` VALUES (1489, '嘟嘟_548', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_548.html', 2);
INSERT INTO `learn_resource` VALUES (1490, '嘟嘟_549', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_549.html', 2);
INSERT INTO `learn_resource` VALUES (1491, '嘟嘟_551', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_551.html', 2);
INSERT INTO `learn_resource` VALUES (1492, '嘟嘟_552', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_552.html', 2);
INSERT INTO `learn_resource` VALUES (1493, '嘟嘟_553', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_553.html', 2);
INSERT INTO `learn_resource` VALUES (1494, '嘟嘟_554', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_554.html', 2);
INSERT INTO `learn_resource` VALUES (1495, '嘟嘟_555', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_555.html', 2);
INSERT INTO `learn_resource` VALUES (1496, '嘟嘟_556', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_556.html', 2);
INSERT INTO `learn_resource` VALUES (1497, '嘟嘟_557', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_557.html', 2);
INSERT INTO `learn_resource` VALUES (1498, '嘟嘟_558', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_558.html', 2);
INSERT INTO `learn_resource` VALUES (1499, '嘟嘟_560', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_560.html', 2);
INSERT INTO `learn_resource` VALUES (1500, '嘟嘟_561', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_561.html', 2);
INSERT INTO `learn_resource` VALUES (1501, '嘟嘟_562', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_562.html', 2);
INSERT INTO `learn_resource` VALUES (1502, '嘟嘟_563', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_563.html', 2);
INSERT INTO `learn_resource` VALUES (1503, '嘟嘟_564', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_564.html', 2);
INSERT INTO `learn_resource` VALUES (1504, '嘟嘟_565', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_565.html', 2);
INSERT INTO `learn_resource` VALUES (1505, '嘟嘟_566', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_566.html', 2);
INSERT INTO `learn_resource` VALUES (1506, '嘟嘟_567', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_567.html', 2);
INSERT INTO `learn_resource` VALUES (1507, '嘟嘟_569', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_569.html', 2);
INSERT INTO `learn_resource` VALUES (1508, '嘟嘟_570', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_570.html', 2);
INSERT INTO `learn_resource` VALUES (1509, '嘟嘟_571', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_571.html', 2);
INSERT INTO `learn_resource` VALUES (1510, '嘟嘟_572', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_572.html', 2);
INSERT INTO `learn_resource` VALUES (1511, '嘟嘟_573', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_573.html', 2);
INSERT INTO `learn_resource` VALUES (1512, '嘟嘟_574', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_574.html', 2);
INSERT INTO `learn_resource` VALUES (1513, '嘟嘟_575', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_575.html', 2);
INSERT INTO `learn_resource` VALUES (1514, '嘟嘟_576', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_576.html', 2);
INSERT INTO `learn_resource` VALUES (1515, '嘟嘟_578', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_578.html', 2);
INSERT INTO `learn_resource` VALUES (1516, '嘟嘟_579', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_579.html', 2);
INSERT INTO `learn_resource` VALUES (1517, '嘟嘟_580', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_580.html', 2);
INSERT INTO `learn_resource` VALUES (1518, '嘟嘟_581', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_581.html', 2);
INSERT INTO `learn_resource` VALUES (1519, '嘟嘟_582', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_582.html', 2);
INSERT INTO `learn_resource` VALUES (1520, '嘟嘟_583', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_583.html', 2);
INSERT INTO `learn_resource` VALUES (1521, '嘟嘟_584', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_584.html', 2);
INSERT INTO `learn_resource` VALUES (1522, '嘟嘟_585', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_585.html', 2);
INSERT INTO `learn_resource` VALUES (1523, '嘟嘟_587', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_587.html', 2);
INSERT INTO `learn_resource` VALUES (1524, '嘟嘟_588', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_588.html', 2);
INSERT INTO `learn_resource` VALUES (1525, '嘟嘟_589', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_589.html', 2);
INSERT INTO `learn_resource` VALUES (1526, '嘟嘟_590', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_590.html', 2);
INSERT INTO `learn_resource` VALUES (1527, '嘟嘟_591', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_591.html', 2);
INSERT INTO `learn_resource` VALUES (1528, '嘟嘟_592', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_592.html', 2);
INSERT INTO `learn_resource` VALUES (1529, '嘟嘟_593', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_593.html', 2);
INSERT INTO `learn_resource` VALUES (1530, '嘟嘟_594', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_594.html', 2);
INSERT INTO `learn_resource` VALUES (1531, '嘟嘟_596', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_596.html', 2);
INSERT INTO `learn_resource` VALUES (1532, '嘟嘟_597', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_597.html', 2);
INSERT INTO `learn_resource` VALUES (1533, '嘟嘟_598', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_598.html', 2);
INSERT INTO `learn_resource` VALUES (1534, '嘟嘟_599', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_599.html', 2);
INSERT INTO `learn_resource` VALUES (1535, '嘟嘟_600', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_600.html', 2);
INSERT INTO `learn_resource` VALUES (1536, '嘟嘟_601', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_601.html', 2);
INSERT INTO `learn_resource` VALUES (1537, '嘟嘟_602', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_602.html', 2);
INSERT INTO `learn_resource` VALUES (1538, '嘟嘟_603', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_603.html', 2);
INSERT INTO `learn_resource` VALUES (1539, '嘟嘟_605', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_605.html', 2);
INSERT INTO `learn_resource` VALUES (1540, '嘟嘟_606', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_606.html', 2);
INSERT INTO `learn_resource` VALUES (1541, '嘟嘟_607', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_607.html', 2);
INSERT INTO `learn_resource` VALUES (1542, '嘟嘟_608', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_608.html', 2);
INSERT INTO `learn_resource` VALUES (1543, '嘟嘟_609', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_609.html', 2);
INSERT INTO `learn_resource` VALUES (1544, '嘟嘟_610', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_610.html', 2);
INSERT INTO `learn_resource` VALUES (1545, '嘟嘟_611', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_611.html', 2);
INSERT INTO `learn_resource` VALUES (1546, '嘟嘟_612', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_612.html', 2);
INSERT INTO `learn_resource` VALUES (1547, '嘟嘟_614', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_614.html', 2);
INSERT INTO `learn_resource` VALUES (1548, '嘟嘟_615', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_615.html', 2);
INSERT INTO `learn_resource` VALUES (1549, '嘟嘟_616', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_616.html', 2);
INSERT INTO `learn_resource` VALUES (1550, '嘟嘟_617', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_617.html', 2);
INSERT INTO `learn_resource` VALUES (1551, '嘟嘟_618', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_618.html', 2);
INSERT INTO `learn_resource` VALUES (1552, '嘟嘟_619', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_619.html', 2);
INSERT INTO `learn_resource` VALUES (1553, '嘟嘟_620', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_620.html', 2);
INSERT INTO `learn_resource` VALUES (1554, '嘟嘟_621', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_621.html', 2);
INSERT INTO `learn_resource` VALUES (1555, '嘟嘟_623', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_623.html', 2);
INSERT INTO `learn_resource` VALUES (1556, '嘟嘟_624', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_624.html', 2);
INSERT INTO `learn_resource` VALUES (1557, '嘟嘟_625', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_625.html', 2);
INSERT INTO `learn_resource` VALUES (1558, '嘟嘟_626', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_626.html', 2);
INSERT INTO `learn_resource` VALUES (1559, '嘟嘟_627', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_627.html', 2);
INSERT INTO `learn_resource` VALUES (1560, '嘟嘟_628', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_628.html', 2);
INSERT INTO `learn_resource` VALUES (1561, '嘟嘟_629', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_629.html', 2);
INSERT INTO `learn_resource` VALUES (1562, '嘟嘟_630', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_630.html', 2);
INSERT INTO `learn_resource` VALUES (1563, '嘟嘟_632', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_632.html', 2);
INSERT INTO `learn_resource` VALUES (1564, '嘟嘟_633', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_633.html', 2);
INSERT INTO `learn_resource` VALUES (1565, '嘟嘟_634', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_634.html', 2);
INSERT INTO `learn_resource` VALUES (1566, '嘟嘟_635', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_635.html', 2);
INSERT INTO `learn_resource` VALUES (1567, '嘟嘟_636', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_636.html', 2);
INSERT INTO `learn_resource` VALUES (1568, '嘟嘟_637', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_637.html', 2);
INSERT INTO `learn_resource` VALUES (1569, '嘟嘟_638', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_638.html', 2);
INSERT INTO `learn_resource` VALUES (1570, '嘟嘟_639', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_639.html', 2);
INSERT INTO `learn_resource` VALUES (1571, '嘟嘟_641', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_641.html', 2);
INSERT INTO `learn_resource` VALUES (1572, '嘟嘟_642', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_642.html', 2);
INSERT INTO `learn_resource` VALUES (1573, '嘟嘟_643', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_643.html', 2);
INSERT INTO `learn_resource` VALUES (1574, '嘟嘟_644', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_644.html', 2);
INSERT INTO `learn_resource` VALUES (1575, '嘟嘟_645', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_645.html', 2);
INSERT INTO `learn_resource` VALUES (1576, '嘟嘟_646', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_646.html', 2);
INSERT INTO `learn_resource` VALUES (1577, '嘟嘟_647', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_647.html', 2);
INSERT INTO `learn_resource` VALUES (1578, '嘟嘟_648', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_648.html', 2);
INSERT INTO `learn_resource` VALUES (1579, '嘟嘟_650', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_650.html', 2);
INSERT INTO `learn_resource` VALUES (1580, '嘟嘟_651', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_651.html', 2);
INSERT INTO `learn_resource` VALUES (1581, '嘟嘟_652', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_652.html', 2);
INSERT INTO `learn_resource` VALUES (1582, '嘟嘟_653', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_653.html', 2);
INSERT INTO `learn_resource` VALUES (1583, '嘟嘟_654', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_654.html', 2);
INSERT INTO `learn_resource` VALUES (1584, '嘟嘟_655', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_655.html', 2);
INSERT INTO `learn_resource` VALUES (1585, '嘟嘟_656', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_656.html', 2);
INSERT INTO `learn_resource` VALUES (1586, '嘟嘟_657', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_657.html', 2);
INSERT INTO `learn_resource` VALUES (1587, '嘟嘟_659', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_659.html', 2);
INSERT INTO `learn_resource` VALUES (1588, '嘟嘟_660', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_660.html', 2);
INSERT INTO `learn_resource` VALUES (1589, '嘟嘟_661', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_661.html', 2);
INSERT INTO `learn_resource` VALUES (1590, '嘟嘟_662', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_662.html', 2);
INSERT INTO `learn_resource` VALUES (1591, '嘟嘟_663', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_663.html', 2);
INSERT INTO `learn_resource` VALUES (1592, '嘟嘟_664', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_664.html', 2);
INSERT INTO `learn_resource` VALUES (1593, '嘟嘟_665', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_665.html', 2);
INSERT INTO `learn_resource` VALUES (1594, '嘟嘟_666', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_666.html', 2);
INSERT INTO `learn_resource` VALUES (1595, '嘟嘟_668', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_668.html', 2);
INSERT INTO `learn_resource` VALUES (1596, '嘟嘟_669', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_669.html', 2);
INSERT INTO `learn_resource` VALUES (1597, '嘟嘟_670', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_670.html', 2);
INSERT INTO `learn_resource` VALUES (1598, '嘟嘟_671', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_671.html', 2);
INSERT INTO `learn_resource` VALUES (1599, '嘟嘟_672', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_672.html', 2);
INSERT INTO `learn_resource` VALUES (1600, '嘟嘟_673', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_673.html', 2);
INSERT INTO `learn_resource` VALUES (1601, '嘟嘟_674', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_674.html', 2);
INSERT INTO `learn_resource` VALUES (1602, '嘟嘟_675', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_675.html', 2);
INSERT INTO `learn_resource` VALUES (1603, '嘟嘟_677', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_677.html', 2);
INSERT INTO `learn_resource` VALUES (1604, '嘟嘟_678', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_678.html', 2);
INSERT INTO `learn_resource` VALUES (1605, '嘟嘟_679', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_679.html', 2);
INSERT INTO `learn_resource` VALUES (1606, '嘟嘟_680', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_680.html', 2);
INSERT INTO `learn_resource` VALUES (1607, '嘟嘟_681', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_681.html', 2);
INSERT INTO `learn_resource` VALUES (1608, '嘟嘟_682', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_682.html', 2);
INSERT INTO `learn_resource` VALUES (1609, '嘟嘟_683', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_683.html', 2);
INSERT INTO `learn_resource` VALUES (1610, '嘟嘟_684', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_684.html', 2);
INSERT INTO `learn_resource` VALUES (1611, '嘟嘟_686', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_686.html', 2);
INSERT INTO `learn_resource` VALUES (1612, '嘟嘟_687', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_687.html', 2);
INSERT INTO `learn_resource` VALUES (1613, '嘟嘟_688', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_688.html', 2);
INSERT INTO `learn_resource` VALUES (1614, '嘟嘟_689', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_689.html', 2);
INSERT INTO `learn_resource` VALUES (1615, '嘟嘟_690', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_690.html', 2);
INSERT INTO `learn_resource` VALUES (1616, '嘟嘟_691', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_691.html', 2);
INSERT INTO `learn_resource` VALUES (1617, '嘟嘟_692', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_692.html', 2);
INSERT INTO `learn_resource` VALUES (1618, '嘟嘟_693', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_693.html', 2);
INSERT INTO `learn_resource` VALUES (1619, '嘟嘟_695', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_695.html', 2);
INSERT INTO `learn_resource` VALUES (1620, '嘟嘟_696', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_696.html', 2);
INSERT INTO `learn_resource` VALUES (1621, '嘟嘟_697', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_697.html', 2);
INSERT INTO `learn_resource` VALUES (1622, '嘟嘟_698', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_698.html', 2);
INSERT INTO `learn_resource` VALUES (1623, '嘟嘟_699', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_699.html', 2);
INSERT INTO `learn_resource` VALUES (1624, '嘟嘟_700', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_700.html', 2);
INSERT INTO `learn_resource` VALUES (1625, '嘟嘟_701', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_701.html', 2);
INSERT INTO `learn_resource` VALUES (1626, '嘟嘟_702', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_702.html', 2);
INSERT INTO `learn_resource` VALUES (1627, '嘟嘟_704', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_704.html', 2);
INSERT INTO `learn_resource` VALUES (1628, '嘟嘟_705', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_705.html', 2);
INSERT INTO `learn_resource` VALUES (1629, '嘟嘟_706', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_706.html', 2);
INSERT INTO `learn_resource` VALUES (1630, '嘟嘟_707', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_707.html', 2);
INSERT INTO `learn_resource` VALUES (1631, '嘟嘟_708', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_708.html', 2);
INSERT INTO `learn_resource` VALUES (1632, '嘟嘟_709', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_709.html', 2);
INSERT INTO `learn_resource` VALUES (1633, '嘟嘟_710', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_710.html', 2);
INSERT INTO `learn_resource` VALUES (1634, '嘟嘟_711', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_711.html', 2);
INSERT INTO `learn_resource` VALUES (1635, '嘟嘟_713', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_713.html', 2);
INSERT INTO `learn_resource` VALUES (1636, '嘟嘟_714', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_714.html', 2);
INSERT INTO `learn_resource` VALUES (1637, '嘟嘟_715', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_715.html', 2);
INSERT INTO `learn_resource` VALUES (1638, '嘟嘟_716', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_716.html', 2);
INSERT INTO `learn_resource` VALUES (1639, '嘟嘟_717', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_717.html', 2);
INSERT INTO `learn_resource` VALUES (1640, '嘟嘟_718', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_718.html', 2);
INSERT INTO `learn_resource` VALUES (1641, '嘟嘟_719', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_719.html', 2);
INSERT INTO `learn_resource` VALUES (1642, '嘟嘟_720', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_720.html', 2);
INSERT INTO `learn_resource` VALUES (1643, '嘟嘟_722', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_722.html', 2);
INSERT INTO `learn_resource` VALUES (1644, '嘟嘟_723', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_723.html', 2);
INSERT INTO `learn_resource` VALUES (1645, '嘟嘟_724', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_724.html', 2);
INSERT INTO `learn_resource` VALUES (1646, '嘟嘟_725', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_725.html', 2);
INSERT INTO `learn_resource` VALUES (1647, '嘟嘟_726', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_726.html', 2);
INSERT INTO `learn_resource` VALUES (1648, '嘟嘟_727', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_727.html', 2);
INSERT INTO `learn_resource` VALUES (1649, '嘟嘟_728', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_728.html', 2);
INSERT INTO `learn_resource` VALUES (1650, '嘟嘟_729', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_729.html', 2);
INSERT INTO `learn_resource` VALUES (1651, '嘟嘟_731', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_731.html', 2);
INSERT INTO `learn_resource` VALUES (1652, '嘟嘟_732', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_732.html', 2);
INSERT INTO `learn_resource` VALUES (1653, '嘟嘟_733', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_733.html', 2);
INSERT INTO `learn_resource` VALUES (1654, '嘟嘟_734', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_734.html', 2);
INSERT INTO `learn_resource` VALUES (1655, '嘟嘟_735', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_735.html', 2);
INSERT INTO `learn_resource` VALUES (1656, '嘟嘟_736', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_736.html', 2);
INSERT INTO `learn_resource` VALUES (1657, '嘟嘟_737', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_737.html', 2);
INSERT INTO `learn_resource` VALUES (1658, '嘟嘟_738', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_738.html', 2);
INSERT INTO `learn_resource` VALUES (1659, '嘟嘟_740', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_740.html', 2);
INSERT INTO `learn_resource` VALUES (1660, '嘟嘟_741', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_741.html', 2);
INSERT INTO `learn_resource` VALUES (1661, '嘟嘟_742', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_742.html', 2);
INSERT INTO `learn_resource` VALUES (1662, '嘟嘟_743', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_743.html', 2);
INSERT INTO `learn_resource` VALUES (1663, '嘟嘟_744', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_744.html', 2);
INSERT INTO `learn_resource` VALUES (1664, '嘟嘟_745', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_745.html', 2);
INSERT INTO `learn_resource` VALUES (1665, '嘟嘟_746', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_746.html', 2);
INSERT INTO `learn_resource` VALUES (1666, '嘟嘟_747', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_747.html', 2);
INSERT INTO `learn_resource` VALUES (1667, '嘟嘟_749', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_749.html', 2);
INSERT INTO `learn_resource` VALUES (1668, '嘟嘟_750', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_750.html', 2);
INSERT INTO `learn_resource` VALUES (1669, '嘟嘟_751', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_751.html', 2);
INSERT INTO `learn_resource` VALUES (1670, '嘟嘟_752', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_752.html', 2);
INSERT INTO `learn_resource` VALUES (1671, '嘟嘟_753', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_753.html', 2);
INSERT INTO `learn_resource` VALUES (1672, '嘟嘟_754', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_754.html', 2);
INSERT INTO `learn_resource` VALUES (1673, '嘟嘟_755', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_755.html', 2);
INSERT INTO `learn_resource` VALUES (1674, '嘟嘟_756', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_756.html', 2);
INSERT INTO `learn_resource` VALUES (1675, '嘟嘟_758', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_758.html', 2);
INSERT INTO `learn_resource` VALUES (1676, '嘟嘟_759', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_759.html', 2);
INSERT INTO `learn_resource` VALUES (1677, '嘟嘟_760', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_760.html', 2);
INSERT INTO `learn_resource` VALUES (1678, '嘟嘟_761', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_761.html', 2);
INSERT INTO `learn_resource` VALUES (1679, '嘟嘟_762', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_762.html', 2);
INSERT INTO `learn_resource` VALUES (1680, '嘟嘟_763', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_763.html', 2);
INSERT INTO `learn_resource` VALUES (1681, '嘟嘟_764', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_764.html', 2);
INSERT INTO `learn_resource` VALUES (1682, '嘟嘟_765', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_765.html', 2);
INSERT INTO `learn_resource` VALUES (1683, '嘟嘟_767', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_767.html', 2);
INSERT INTO `learn_resource` VALUES (1684, '嘟嘟_768', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_768.html', 2);
INSERT INTO `learn_resource` VALUES (1685, '嘟嘟_769', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_769.html', 2);
INSERT INTO `learn_resource` VALUES (1686, '嘟嘟_770', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_770.html', 2);
INSERT INTO `learn_resource` VALUES (1687, '嘟嘟_771', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_771.html', 2);
INSERT INTO `learn_resource` VALUES (1688, '嘟嘟_772', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_772.html', 2);
INSERT INTO `learn_resource` VALUES (1689, '嘟嘟_773', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_773.html', 2);
INSERT INTO `learn_resource` VALUES (1690, '嘟嘟_774', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_774.html', 2);
INSERT INTO `learn_resource` VALUES (1691, '嘟嘟_776', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_776.html', 2);
INSERT INTO `learn_resource` VALUES (1692, '嘟嘟_777', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_777.html', 2);
INSERT INTO `learn_resource` VALUES (1693, '嘟嘟_778', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_778.html', 2);
INSERT INTO `learn_resource` VALUES (1694, '嘟嘟_779', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_779.html', 2);
INSERT INTO `learn_resource` VALUES (1695, '嘟嘟_780', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_780.html', 2);
INSERT INTO `learn_resource` VALUES (1696, '嘟嘟_781', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_781.html', 2);
INSERT INTO `learn_resource` VALUES (1697, '嘟嘟_782', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_782.html', 2);
INSERT INTO `learn_resource` VALUES (1698, '嘟嘟_783', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_783.html', 2);
INSERT INTO `learn_resource` VALUES (1699, '嘟嘟_785', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_785.html', 2);
INSERT INTO `learn_resource` VALUES (1700, '嘟嘟_786', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_786.html', 2);
INSERT INTO `learn_resource` VALUES (1701, '嘟嘟_787', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_787.html', 2);
INSERT INTO `learn_resource` VALUES (1702, '嘟嘟_788', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_788.html', 2);
INSERT INTO `learn_resource` VALUES (1703, '嘟嘟_789', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_789.html', 2);
INSERT INTO `learn_resource` VALUES (1704, '嘟嘟_790', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_790.html', 2);
INSERT INTO `learn_resource` VALUES (1705, '嘟嘟_791', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_791.html', 2);
INSERT INTO `learn_resource` VALUES (1706, '嘟嘟_792', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_792.html', 2);
INSERT INTO `learn_resource` VALUES (1707, '嘟嘟_794', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_794.html', 2);
INSERT INTO `learn_resource` VALUES (1708, '嘟嘟_795', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_795.html', 2);
INSERT INTO `learn_resource` VALUES (1709, '嘟嘟_796', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_796.html', 2);
INSERT INTO `learn_resource` VALUES (1710, '嘟嘟_797', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_797.html', 2);
INSERT INTO `learn_resource` VALUES (1711, '嘟嘟_798', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_798.html', 2);
INSERT INTO `learn_resource` VALUES (1712, '嘟嘟_799', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_799.html', 2);
INSERT INTO `learn_resource` VALUES (1713, '嘟嘟_800', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_800.html', 2);
INSERT INTO `learn_resource` VALUES (1714, '嘟嘟_801', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_801.html', 2);
INSERT INTO `learn_resource` VALUES (1715, '嘟嘟_803', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_803.html', 2);
INSERT INTO `learn_resource` VALUES (1716, '嘟嘟_804', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_804.html', 2);
INSERT INTO `learn_resource` VALUES (1717, '嘟嘟_805', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_805.html', 2);
INSERT INTO `learn_resource` VALUES (1718, '嘟嘟_806', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_806.html', 2);
INSERT INTO `learn_resource` VALUES (1719, '嘟嘟_807', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_807.html', 2);
INSERT INTO `learn_resource` VALUES (1720, '嘟嘟_808', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_808.html', 2);
INSERT INTO `learn_resource` VALUES (1721, '嘟嘟_809', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_809.html', 2);
INSERT INTO `learn_resource` VALUES (1722, '嘟嘟_810', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_810.html', 2);
INSERT INTO `learn_resource` VALUES (1723, '嘟嘟_812', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_812.html', 2);
INSERT INTO `learn_resource` VALUES (1724, '嘟嘟_813', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_813.html', 2);
INSERT INTO `learn_resource` VALUES (1725, '嘟嘟_814', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_814.html', 2);
INSERT INTO `learn_resource` VALUES (1726, '嘟嘟_815', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_815.html', 2);
INSERT INTO `learn_resource` VALUES (1727, '嘟嘟_816', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_816.html', 2);
INSERT INTO `learn_resource` VALUES (1728, '嘟嘟_817', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_817.html', 2);
INSERT INTO `learn_resource` VALUES (1729, '嘟嘟_818', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_818.html', 2);
INSERT INTO `learn_resource` VALUES (1730, '嘟嘟_819', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_819.html', 2);
INSERT INTO `learn_resource` VALUES (1731, '嘟嘟_821', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_821.html', 2);
INSERT INTO `learn_resource` VALUES (1732, '嘟嘟_822', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_822.html', 2);
INSERT INTO `learn_resource` VALUES (1733, '嘟嘟_823', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_823.html', 2);
INSERT INTO `learn_resource` VALUES (1734, '嘟嘟_824', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_824.html', 2);
INSERT INTO `learn_resource` VALUES (1735, '嘟嘟_825', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_825.html', 2);
INSERT INTO `learn_resource` VALUES (1736, '嘟嘟_826', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_826.html', 2);
INSERT INTO `learn_resource` VALUES (1737, '嘟嘟_827', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_827.html', 2);
INSERT INTO `learn_resource` VALUES (1738, '嘟嘟_828', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_828.html', 2);
INSERT INTO `learn_resource` VALUES (1739, '嘟嘟_830', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_830.html', 2);
INSERT INTO `learn_resource` VALUES (1740, '嘟嘟_831', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_831.html', 2);
INSERT INTO `learn_resource` VALUES (1741, '嘟嘟_832', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_832.html', 2);
INSERT INTO `learn_resource` VALUES (1742, '嘟嘟_833', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_833.html', 2);
INSERT INTO `learn_resource` VALUES (1743, '嘟嘟_834', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_834.html', 2);
INSERT INTO `learn_resource` VALUES (1744, '嘟嘟_835', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_835.html', 2);
INSERT INTO `learn_resource` VALUES (1745, '嘟嘟_836', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_836.html', 2);
INSERT INTO `learn_resource` VALUES (1746, '嘟嘟_837', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_837.html', 2);
INSERT INTO `learn_resource` VALUES (1747, '嘟嘟_839', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_839.html', 2);
INSERT INTO `learn_resource` VALUES (1748, '嘟嘟_840', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_840.html', 2);
INSERT INTO `learn_resource` VALUES (1749, '嘟嘟_841', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_841.html', 2);
INSERT INTO `learn_resource` VALUES (1750, '嘟嘟_842', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_842.html', 2);
INSERT INTO `learn_resource` VALUES (1751, '嘟嘟_843', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_843.html', 2);
INSERT INTO `learn_resource` VALUES (1752, '嘟嘟_844', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_844.html', 2);
INSERT INTO `learn_resource` VALUES (1753, '嘟嘟_845', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_845.html', 2);
INSERT INTO `learn_resource` VALUES (1754, '嘟嘟_846', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_846.html', 2);
INSERT INTO `learn_resource` VALUES (1755, '嘟嘟_848', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_848.html', 2);
INSERT INTO `learn_resource` VALUES (1756, '嘟嘟_849', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_849.html', 2);
INSERT INTO `learn_resource` VALUES (1757, '嘟嘟_850', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_850.html', 2);
INSERT INTO `learn_resource` VALUES (1758, '嘟嘟_851', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_851.html', 2);
INSERT INTO `learn_resource` VALUES (1759, '嘟嘟_852', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_852.html', 2);
INSERT INTO `learn_resource` VALUES (1760, '嘟嘟_853', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_853.html', 2);
INSERT INTO `learn_resource` VALUES (1761, '嘟嘟_854', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_854.html', 2);
INSERT INTO `learn_resource` VALUES (1762, '嘟嘟_855', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_855.html', 2);
INSERT INTO `learn_resource` VALUES (1763, '嘟嘟_857', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_857.html', 2);
INSERT INTO `learn_resource` VALUES (1764, '嘟嘟_858', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_858.html', 2);
INSERT INTO `learn_resource` VALUES (1765, '嘟嘟_859', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_859.html', 2);
INSERT INTO `learn_resource` VALUES (1766, '嘟嘟_860', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_860.html', 2);
INSERT INTO `learn_resource` VALUES (1767, '嘟嘟_861', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_861.html', 2);
INSERT INTO `learn_resource` VALUES (1768, '嘟嘟_862', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_862.html', 2);
INSERT INTO `learn_resource` VALUES (1769, '嘟嘟_863', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_863.html', 2);
INSERT INTO `learn_resource` VALUES (1770, '嘟嘟_864', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_864.html', 2);
INSERT INTO `learn_resource` VALUES (1771, '嘟嘟_866', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_866.html', 2);
INSERT INTO `learn_resource` VALUES (1772, '嘟嘟_867', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_867.html', 2);
INSERT INTO `learn_resource` VALUES (1773, '嘟嘟_868', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_868.html', 2);
INSERT INTO `learn_resource` VALUES (1774, '嘟嘟_869', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_869.html', 2);
INSERT INTO `learn_resource` VALUES (1775, '嘟嘟_870', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_870.html', 2);
INSERT INTO `learn_resource` VALUES (1776, '嘟嘟_871', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_871.html', 2);
INSERT INTO `learn_resource` VALUES (1777, '嘟嘟_872', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_872.html', 2);
INSERT INTO `learn_resource` VALUES (1778, '嘟嘟_873', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_873.html', 2);
INSERT INTO `learn_resource` VALUES (1779, '嘟嘟_875', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_875.html', 2);
INSERT INTO `learn_resource` VALUES (1780, '嘟嘟_876', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_876.html', 2);
INSERT INTO `learn_resource` VALUES (1781, '嘟嘟_877', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_877.html', 2);
INSERT INTO `learn_resource` VALUES (1782, '嘟嘟_878', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_878.html', 2);
INSERT INTO `learn_resource` VALUES (1783, '嘟嘟_879', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_879.html', 2);
INSERT INTO `learn_resource` VALUES (1784, '嘟嘟_880', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_880.html', 2);
INSERT INTO `learn_resource` VALUES (1785, '嘟嘟_881', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_881.html', 2);
INSERT INTO `learn_resource` VALUES (1786, '嘟嘟_882', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_882.html', 2);
INSERT INTO `learn_resource` VALUES (1787, '嘟嘟_884', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_884.html', 2);
INSERT INTO `learn_resource` VALUES (1788, '嘟嘟_885', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_885.html', 2);
INSERT INTO `learn_resource` VALUES (1789, '嘟嘟_886', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_886.html', 2);
INSERT INTO `learn_resource` VALUES (1790, '嘟嘟_887', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_887.html', 2);
INSERT INTO `learn_resource` VALUES (1791, '嘟嘟_888', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_888.html', 2);
INSERT INTO `learn_resource` VALUES (1792, '嘟嘟_889', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_889.html', 2);
INSERT INTO `learn_resource` VALUES (1793, '嘟嘟_890', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_890.html', 2);
INSERT INTO `learn_resource` VALUES (1794, '嘟嘟_891', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_891.html', 2);
INSERT INTO `learn_resource` VALUES (1795, '嘟嘟_893', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_893.html', 2);
INSERT INTO `learn_resource` VALUES (1796, '嘟嘟_894', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_894.html', 2);
INSERT INTO `learn_resource` VALUES (1797, '嘟嘟_895', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_895.html', 2);
INSERT INTO `learn_resource` VALUES (1798, '嘟嘟_896', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_896.html', 2);
INSERT INTO `learn_resource` VALUES (1799, '嘟嘟_897', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_897.html', 2);
INSERT INTO `learn_resource` VALUES (1800, '嘟嘟_898', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_898.html', 2);
INSERT INTO `learn_resource` VALUES (1801, '嘟嘟_899', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_899.html', 2);
INSERT INTO `learn_resource` VALUES (1802, '嘟嘟_900', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_900.html', 2);
INSERT INTO `learn_resource` VALUES (1803, '嘟嘟_902', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_902.html', 2);
INSERT INTO `learn_resource` VALUES (1804, '嘟嘟_903', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_903.html', 2);
INSERT INTO `learn_resource` VALUES (1805, '嘟嘟_904', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_904.html', 2);
INSERT INTO `learn_resource` VALUES (1806, '嘟嘟_905', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_905.html', 2);
INSERT INTO `learn_resource` VALUES (1807, '嘟嘟_906', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_906.html', 2);
INSERT INTO `learn_resource` VALUES (1808, '嘟嘟_907', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_907.html', 2);
INSERT INTO `learn_resource` VALUES (1809, '嘟嘟_908', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_908.html', 2);
INSERT INTO `learn_resource` VALUES (1810, '嘟嘟_909', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_909.html', 2);
INSERT INTO `learn_resource` VALUES (1811, '嘟嘟_911', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_911.html', 2);
INSERT INTO `learn_resource` VALUES (1812, '嘟嘟_912', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_912.html', 2);
INSERT INTO `learn_resource` VALUES (1813, '嘟嘟_913', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_913.html', 2);
INSERT INTO `learn_resource` VALUES (1814, '嘟嘟_914', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_914.html', 2);
INSERT INTO `learn_resource` VALUES (1815, '嘟嘟_915', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_915.html', 2);
INSERT INTO `learn_resource` VALUES (1816, '嘟嘟_916', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_916.html', 2);
INSERT INTO `learn_resource` VALUES (1817, '嘟嘟_917', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_917.html', 2);
INSERT INTO `learn_resource` VALUES (1818, '嘟嘟_918', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_918.html', 2);
INSERT INTO `learn_resource` VALUES (1819, '嘟嘟_920', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_920.html', 2);
INSERT INTO `learn_resource` VALUES (1820, '嘟嘟_921', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_921.html', 2);
INSERT INTO `learn_resource` VALUES (1821, '嘟嘟_922', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_922.html', 2);
INSERT INTO `learn_resource` VALUES (1822, '嘟嘟_923', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_923.html', 2);
INSERT INTO `learn_resource` VALUES (1823, '嘟嘟_924', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_924.html', 2);
INSERT INTO `learn_resource` VALUES (1824, '嘟嘟_925', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_925.html', 2);
INSERT INTO `learn_resource` VALUES (1825, '嘟嘟_926', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_926.html', 2);
INSERT INTO `learn_resource` VALUES (1826, '嘟嘟_927', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_927.html', 2);
INSERT INTO `learn_resource` VALUES (1827, '嘟嘟_929', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_929.html', 2);
INSERT INTO `learn_resource` VALUES (1828, '嘟嘟_930', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_930.html', 2);
INSERT INTO `learn_resource` VALUES (1829, '嘟嘟_931', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_931.html', 2);
INSERT INTO `learn_resource` VALUES (1830, '嘟嘟_932', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_932.html', 2);
INSERT INTO `learn_resource` VALUES (1831, '嘟嘟_933', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_933.html', 2);
INSERT INTO `learn_resource` VALUES (1832, '嘟嘟_934', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_934.html', 2);
INSERT INTO `learn_resource` VALUES (1833, '嘟嘟_935', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_935.html', 2);
INSERT INTO `learn_resource` VALUES (1834, '嘟嘟_936', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_936.html', 2);
INSERT INTO `learn_resource` VALUES (1835, '嘟嘟_938', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_938.html', 2);
INSERT INTO `learn_resource` VALUES (1836, '嘟嘟_939', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_939.html', 2);
INSERT INTO `learn_resource` VALUES (1837, '嘟嘟_940', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_940.html', 2);
INSERT INTO `learn_resource` VALUES (1838, '嘟嘟_941', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_941.html', 2);
INSERT INTO `learn_resource` VALUES (1839, '嘟嘟_942', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_942.html', 2);
INSERT INTO `learn_resource` VALUES (1840, '嘟嘟_943', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_943.html', 2);
INSERT INTO `learn_resource` VALUES (1841, '嘟嘟_944', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_944.html', 2);
INSERT INTO `learn_resource` VALUES (1842, '嘟嘟_945', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_945.html', 2);
INSERT INTO `learn_resource` VALUES (1843, '嘟嘟_947', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_947.html', 2);
INSERT INTO `learn_resource` VALUES (1844, '嘟嘟_948', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_948.html', 2);
INSERT INTO `learn_resource` VALUES (1845, '嘟嘟_949', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_949.html', 2);
INSERT INTO `learn_resource` VALUES (1846, '嘟嘟_950', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_950.html', 2);
INSERT INTO `learn_resource` VALUES (1847, '嘟嘟_951', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_951.html', 2);
INSERT INTO `learn_resource` VALUES (1848, '嘟嘟_952', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_952.html', 2);
INSERT INTO `learn_resource` VALUES (1849, '嘟嘟_953', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_953.html', 2);
INSERT INTO `learn_resource` VALUES (1850, '嘟嘟_954', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_954.html', 2);
INSERT INTO `learn_resource` VALUES (1851, '嘟嘟_956', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_956.html', 2);
INSERT INTO `learn_resource` VALUES (1852, '嘟嘟_957', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_957.html', 2);
INSERT INTO `learn_resource` VALUES (1853, '嘟嘟_958', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_958.html', 2);
INSERT INTO `learn_resource` VALUES (1854, '嘟嘟_959', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_959.html', 2);
INSERT INTO `learn_resource` VALUES (1855, '嘟嘟_960', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_960.html', 2);
INSERT INTO `learn_resource` VALUES (1856, '嘟嘟_961', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_961.html', 2);
INSERT INTO `learn_resource` VALUES (1857, '嘟嘟_962', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_962.html', 2);
INSERT INTO `learn_resource` VALUES (1858, '嘟嘟_963', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_963.html', 2);
INSERT INTO `learn_resource` VALUES (1859, '嘟嘟_965', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_965.html', 2);
INSERT INTO `learn_resource` VALUES (1860, '嘟嘟_966', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_966.html', 2);
INSERT INTO `learn_resource` VALUES (1861, '嘟嘟_967', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_967.html', 2);
INSERT INTO `learn_resource` VALUES (1862, '嘟嘟_968', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_968.html', 2);
INSERT INTO `learn_resource` VALUES (1863, '嘟嘟_969', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_969.html', 2);
INSERT INTO `learn_resource` VALUES (1864, '嘟嘟_970', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_970.html', 2);
INSERT INTO `learn_resource` VALUES (1865, '嘟嘟_971', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_971.html', 2);
INSERT INTO `learn_resource` VALUES (1866, '嘟嘟_972', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_972.html', 2);
INSERT INTO `learn_resource` VALUES (1867, '嘟嘟_974', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_974.html', 2);
INSERT INTO `learn_resource` VALUES (1868, '嘟嘟_975', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_975.html', 2);
INSERT INTO `learn_resource` VALUES (1869, '嘟嘟_976', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_976.html', 2);
INSERT INTO `learn_resource` VALUES (1870, '嘟嘟_977', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_977.html', 2);
INSERT INTO `learn_resource` VALUES (1871, '嘟嘟_978', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_978.html', 2);
INSERT INTO `learn_resource` VALUES (1872, '嘟嘟_979', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_979.html', 2);
INSERT INTO `learn_resource` VALUES (1873, '嘟嘟_980', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_980.html', 2);
INSERT INTO `learn_resource` VALUES (1874, '嘟嘟_981', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_981.html', 2);
INSERT INTO `learn_resource` VALUES (1875, '嘟嘟_983', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_983.html', 2);
INSERT INTO `learn_resource` VALUES (1876, '嘟嘟_984', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_984.html', 2);
INSERT INTO `learn_resource` VALUES (1877, '嘟嘟_985', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_985.html', 2);
INSERT INTO `learn_resource` VALUES (1878, '嘟嘟_986', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_986.html', 2);
INSERT INTO `learn_resource` VALUES (1879, '嘟嘟_987', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_987.html', 2);
INSERT INTO `learn_resource` VALUES (1880, '嘟嘟_988', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_988.html', 2);
INSERT INTO `learn_resource` VALUES (1881, '嘟嘟_989', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_989.html', 2);
INSERT INTO `learn_resource` VALUES (1882, '嘟嘟_990', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_990.html', 2);
INSERT INTO `learn_resource` VALUES (1883, '嘟嘟_992', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_992.html', 2);
INSERT INTO `learn_resource` VALUES (1884, '嘟嘟_993', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_993.html', 2);
INSERT INTO `learn_resource` VALUES (1885, '嘟嘟_994', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_994.html', 2);
INSERT INTO `learn_resource` VALUES (1886, '嘟嘟_995', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_995.html', 2);
INSERT INTO `learn_resource` VALUES (1887, '嘟嘟_996', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_996.html', 2);
INSERT INTO `learn_resource` VALUES (1888, '嘟嘟_997', 'Maven实战', 'https://www.cnblogs.com/changer01/p/7911805_997.html', 2);
INSERT INTO `learn_resource` VALUES (1889, '嘟嘟_998', 'springCloud微服务', 'https://www.cnblogs.com/changer01/p/7911805_998.html', 2);
INSERT INTO `learn_resource` VALUES (1890, '嘟嘟_999', 'spring企业级++', 'https://www.cnblogs.com/changer01/p/7911805_999.html', 2);

-- ----------------------------
-- Table structure for learn_resource_group
-- ----------------------------
DROP TABLE IF EXISTS `learn_resource_group`;
CREATE TABLE `learn_resource_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of learn_resource_group
-- ----------------------------
INSERT INTO `learn_resource_group` VALUES (1, 'spring专栏', 'spring专栏');
INSERT INTO `learn_resource_group` VALUES (2, 'mybatis专栏', 'mybatis专栏');

-- ----------------------------
-- Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `privilege_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `privilege_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限URL',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_privilege
-- ----------------------------
INSERT INTO `sys_privilege` VALUES (1, 'user:index', 'http://www.baidu.com');
INSERT INTO `sys_privilege` VALUES (2, 'user:add', 'http://www.baidu.com');
INSERT INTO `sys_privilege` VALUES (3, 'user:update', 'http://www.baidu.com');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `enabled` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效标志',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '1', 1, '2018-05-16 14:16:51');
INSERT INTO `sys_role` VALUES (2, '普通用户', '1', 1, '2018-05-15 14:17:00');

-- ----------------------------
-- Table structure for sys_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_privilege`;
CREATE TABLE `sys_role_privilege`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `privilege_id` bigint(20) NOT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_privilege
-- ----------------------------
INSERT INTO `sys_role_privilege` VALUES (1, 1);
INSERT INTO `sys_role_privilege` VALUES (1, 2);
INSERT INTO `sys_role_privilege` VALUES (1, 3);
INSERT INTO `sys_role_privilege` VALUES (2, 1);
INSERT INTO `sys_role_privilege` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `head_img` blob NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'xuzhiyong', 'bf6946e90e8cc17800236a4655ddd58a', 'jip0574@qq.com', '福建顶点软件有限公司', NULL, '2018-06-07 11:55:24');
INSERT INTO `sys_user` VALUES (2, 'gaoyonshun', '123456', 'gaoyongshun@qq.com', '普通客户', NULL, '2018-05-22 10:42:33');
INSERT INTO `sys_user` VALUES (3, '咪咕', '123456', 'jip0574@qq.com', '咪咕音乐', NULL, '2018-05-16 18:28:34');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (1, 7);
INSERT INTO `sys_user_role` VALUES (1, 8);

SET FOREIGN_KEY_CHECKS = 1;
