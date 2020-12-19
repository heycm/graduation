/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 127.0.0.1:3306
 Source Schema         : crim

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 13/06/2020 20:41:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crim_audit_record
-- ----------------------------
DROP TABLE IF EXISTS `crim_audit_record`;
CREATE TABLE `crim_audit_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '审核记录id',
  `audit_type` int(11) NOT NULL COMMENT '审核类型：0 注册审核 1 参与招聘会审核',
  `job_fair_id` int(11) DEFAULT NULL COMMENT '招聘会id',
  `campus_id` int(11) DEFAULT NULL COMMENT '校区id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `apply_time` datetime(0) NOT NULL COMMENT '申请审核时间',
  `audit_res` int(11) DEFAULT NULL COMMENT '审核结果：0 通过 1 拒绝 2 待审核',
  `audit_feedback` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核反馈意见',
  `audit_user` int(11) DEFAULT NULL COMMENT '审核人id',
  `audit_time` datetime(0) DEFAULT NULL COMMENT '审核时间',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态：0 已审核 1 未审核',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_audit_record
-- ----------------------------
INSERT INTO `crim_audit_record` VALUES (1, 0, NULL, NULL, 4, '2020-05-04 22:55:12', 0, '欢迎哟', 1, '2020-05-04 23:25:26', 4, '2020-05-04 22:55:12', 1, '2020-05-04 23:25:26', 0, 1);
INSERT INTO `crim_audit_record` VALUES (2, 0, NULL, NULL, 4, '2020-05-05 13:27:57', 0, '欢迎哟', 1, '2020-05-05 13:43:04', 4, '2020-05-05 13:27:57', 1, '2020-05-05 13:43:04', 0, 1);
INSERT INTO `crim_audit_record` VALUES (3, 1, 1, NULL, 4, '2020-05-05 17:31:03', 2, NULL, NULL, NULL, 4, '2020-05-05 17:31:03', 4, '2020-05-05 17:31:03', 0, 1);
INSERT INTO `crim_audit_record` VALUES (4, 1, 1, NULL, 4, '2020-05-05 17:38:19', 2, NULL, NULL, NULL, 4, '2020-05-05 17:35:41', 4, '2020-05-05 17:38:19', 0, 1);
INSERT INTO `crim_audit_record` VALUES (5, 1, 1, NULL, 4, '2020-05-05 20:58:22', 2, NULL, NULL, NULL, 4, '2020-05-05 20:58:22', 4, '2020-05-05 20:58:22', 0, 1);
INSERT INTO `crim_audit_record` VALUES (6, 1, 1, 2, 4, '2020-05-05 21:00:20', 0, 'ok', 1, '2020-05-05 21:45:39', 4, '2020-05-05 21:00:20', 1, '2020-05-05 21:45:39', 0, 1);
INSERT INTO `crim_audit_record` VALUES (7, 1, 1, 4, 4, '2020-05-05 21:10:11', 0, '还行', 1, '2020-05-05 21:46:39', 4, '2020-05-05 21:10:11', 1, '2020-05-05 21:46:39', 0, 1);
INSERT INTO `crim_audit_record` VALUES (8, 1, 1, 3, 4, '2020-05-05 21:10:21', 1, ' 不能太贪心哦', 1, '2020-05-05 22:00:06', 4, '2020-05-05 21:10:21', 1, '2020-05-05 22:00:06', 0, 1);
INSERT INTO `crim_audit_record` VALUES (9, 1, 1, 3, 4, '2020-05-05 22:02:00', NULL, NULL, NULL, NULL, 4, '2020-05-05 22:02:00', 4, '2020-05-05 22:02:00', 1, 1);
INSERT INTO `crim_audit_record` VALUES (10, 1, 1, 2, 4, '2020-05-10 00:25:11', 0, '通过测试', 1, '2020-05-10 00:34:39', 4, '2020-05-10 00:25:11', 4, '2020-05-10 00:34:39', 0, 0);
INSERT INTO `crim_audit_record` VALUES (11, 1, 1, 4, 4, '2020-05-10 00:25:42', 1, '通过测试', 1, '2020-05-10 00:29:53', 4, '2020-05-10 00:25:42', 4, '2020-05-10 00:29:53', 0, 1);
INSERT INTO `crim_audit_record` VALUES (12, 1, 4, 4, 4, '2020-05-10 00:32:24', 1, '拒绝测试', 1, '2020-05-10 00:35:04', 4, '2020-05-10 00:32:24', 4, '2020-05-10 00:35:04', 0, 0);
INSERT INTO `crim_audit_record` VALUES (13, 0, NULL, NULL, 17, '2020-05-12 15:19:06', 1, '信用代码不对', 1, '2020-06-06 14:46:30', 17, '2020-05-12 15:19:06', 17, '2020-06-06 14:46:30', 0, 0);
INSERT INTO `crim_audit_record` VALUES (14, 0, NULL, NULL, 3, '2020-05-12 17:16:04', 0, '', 1, '2020-05-12 17:16:52', 3, '2020-05-12 17:16:04', 3, '2020-05-12 17:16:52', 0, 0);
INSERT INTO `crim_audit_record` VALUES (15, 1, 1, 2, 3, '2020-05-12 17:17:43', 0, '通过', 1, '2020-05-12 22:23:33', 3, '2020-05-12 17:17:43', 3, '2020-05-12 22:23:33', 0, 0);
INSERT INTO `crim_audit_record` VALUES (16, 1, 4, 4, 3, '2020-05-12 17:21:48', 1, '拒绝拒绝', 1, '2020-05-12 22:23:41', 3, '2020-05-12 17:21:48', 3, '2020-05-12 22:23:41', 0, 0);
INSERT INTO `crim_audit_record` VALUES (17, 1, 5, 2, 3, '2020-06-05 21:16:38', NULL, NULL, NULL, NULL, 3, '2020-06-05 21:16:38', 3, '2020-06-05 21:16:38', 1, 0);
INSERT INTO `crim_audit_record` VALUES (18, 0, NULL, NULL, 17, '2020-06-06 14:47:26', 0, '允许', 1, '2020-06-06 14:48:08', 17, '2020-06-06 14:47:26', 17, '2020-06-06 14:48:08', 0, 0);

-- ----------------------------
-- Table structure for crim_building
-- ----------------------------
DROP TABLE IF EXISTS `crim_building`;
CREATE TABLE `crim_building`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '建筑id',
  `campus_id` int(11) DEFAULT NULL COMMENT '校区id',
  `building_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '建筑名称',
  `building_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '建筑描述',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_building
-- ----------------------------
INSERT INTO `crim_building` VALUES (1, 2, '逸夫楼', '主楼', 1, '2020-04-30 23:35:20', 1, '2020-04-30 23:35:20', NULL, 0);
INSERT INTO `crim_building` VALUES (2, 2, '西教', '', 1, '2020-05-23 15:50:51', 1, '2020-05-23 15:50:51', NULL, 0);
INSERT INTO `crim_building` VALUES (3, 3, '综合楼', '开发区校区北门入口正面面对的教学楼，升旗台前面', 1, '2020-05-23 15:56:32', 1, '2020-05-23 15:56:32', NULL, 0);
INSERT INTO `crim_building` VALUES (4, 3, '升旗广场', '开发区北门升旗广场', 1, '2020-05-23 15:58:38', 1, '2020-05-23 15:58:38', NULL, 0);

-- ----------------------------
-- Table structure for crim_career_talk
-- ----------------------------
DROP TABLE IF EXISTS `crim_career_talk`;
CREATE TABLE `crim_career_talk`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '宣讲会id',
  `job_fair_company_id` int(11) NOT NULL COMMENT '招聘会/企业 关联表id',
  `career_talk_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '宣讲会标题',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `career_talk_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '宣讲会描述/备注',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_career_talk
-- ----------------------------
INSERT INTO `crim_career_talk` VALUES (1, 1, '赤壁之战', '2020-05-13 09:00:00', '2020-05-13 11:00:00', '同学们请带上简历哟', 4, '2020-05-06 16:15:17', 4, '2020-05-06 18:25:15', NULL, 0);
INSERT INTO `crim_career_talk` VALUES (2, 2, '啊实打实', '2020-05-13 09:00:00', '2020-05-13 10:30:00', NULL, 4, '2020-05-06 17:24:24', 4, '2020-05-06 17:24:24', NULL, 1);
INSERT INTO `crim_career_talk` VALUES (3, 1, '赤壁之战', '2020-05-14 09:00:00', '2020-05-14 09:15:00', '啊实打实<br>as90du', 4, '2020-05-06 17:41:39', 4, '2020-05-06 17:41:39', NULL, 1);
INSERT INTO `crim_career_talk` VALUES (4, 2, '打完球', '2020-05-13 09:00:00', '2020-05-13 11:30:00', NULL, 4, '2020-05-06 18:15:16', 4, '2020-05-06 18:24:23', NULL, 1);
INSERT INTO `crim_career_talk` VALUES (5, 4, '中国电信集团2020秋季主题招聘会', '2020-06-10 09:00:00', '2020-06-10 11:00:00', '啊实打实大师', 3, '2020-05-12 22:28:55', 3, '2020-05-28 00:10:13', NULL, 0);

-- ----------------------------
-- Table structure for crim_career_talk_student
-- ----------------------------
DROP TABLE IF EXISTS `crim_career_talk_student`;
CREATE TABLE `crim_career_talk_student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生参加宣讲会记录id',
  `career_talk_id` int(11) NOT NULL COMMENT '宣讲会id',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_career_talk_student
-- ----------------------------
INSERT INTO `crim_career_talk_student` VALUES (1, 1, 1, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (4, 4, 1, 16, '2020-05-08 21:07:54', 16, '2020-05-08 21:07:54', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (5, 1, 1, 16, '2020-05-08 21:16:51', 16, '2020-05-08 21:16:51', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (6, 1, 1, 16, '2020-05-08 21:17:08', 16, '2020-05-08 21:17:08', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (7, 1, 1, 16, '2020-05-08 21:19:40', 16, '2020-05-08 21:19:40', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (8, 4, 1, 16, '2020-05-08 21:20:23', 16, '2020-05-08 21:20:23', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (9, 1, 1, 16, '2020-05-08 21:20:26', 16, '2020-05-08 21:20:26', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (10, 1, 1, 16, '2020-05-08 21:20:58', 16, '2020-05-08 21:20:58', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (11, 4, 1, 16, '2020-05-08 21:21:00', 16, '2020-05-08 21:21:00', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (12, 1, 1, 16, '2020-05-08 21:38:19', 16, '2020-05-08 21:38:19', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (13, 4, 1, 16, '2020-05-08 21:41:01', 16, '2020-05-08 21:41:01', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (14, 1, 1, 16, '2020-05-08 21:45:33', 16, '2020-05-08 21:45:33', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (15, 1, 1, 16, '2020-05-08 22:13:29', 16, '2020-05-08 22:13:29', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (16, 1, 1, 16, '2020-05-08 22:15:17', 16, '2020-05-08 22:15:17', NULL, 1);
INSERT INTO `crim_career_talk_student` VALUES (17, 4, 1, 16, '2020-05-08 22:15:21', 16, '2020-05-08 22:15:21', NULL, 0);
INSERT INTO `crim_career_talk_student` VALUES (18, 1, 1, 16, '2020-05-09 16:36:22', 16, '2020-05-09 16:36:22', NULL, 0);
INSERT INTO `crim_career_talk_student` VALUES (19, 5, 1, 16, '2020-05-12 22:29:22', 16, '2020-05-12 22:29:22', NULL, 0);

-- ----------------------------
-- Table structure for crim_chat
-- ----------------------------
DROP TABLE IF EXISTS `crim_chat`;
CREATE TABLE `crim_chat`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `type` int(11) NOT NULL COMMENT '消息类型：0 文本 1 文件',
  `from_id` int(11) NOT NULL COMMENT '发送者id',
  `to_id` int(11) NOT NULL COMMENT '接受者id',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容（文本内容/文件链接）',
  `err_code` int(11) DEFAULT NULL COMMENT '错误代码',
  `err_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '错误描述',
  `send_time` datetime(0) DEFAULT NULL COMMENT '发送时间',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) NOT NULL COMMENT '消息状态：0 已发送 1 未发送',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_chat
-- ----------------------------
INSERT INTO `crim_chat` VALUES (1, 0, 4, 8, 'hello', NULL, NULL, '2020-05-09 02:11:30', NULL, NULL, 4, '2020-05-09 02:41:15', 0, 0);
INSERT INTO `crim_chat` VALUES (2, 0, 4, 8, '你好', NULL, NULL, '2020-05-09 02:12:19', NULL, NULL, 4, '2020-05-09 02:41:15', 0, 0);
INSERT INTO `crim_chat` VALUES (3, 0, 8, 4, '<p>你好嚯嚯嚯</p>', 505001, '对方未在线', '2020-05-09 14:32:15', 16, '2020-05-09 14:32:15', 16, '2020-05-09 14:32:15', 0, 0);
INSERT INTO `crim_chat` VALUES (4, 0, 8, 4, '<p>如果您看到这条消息</p><p>请回复</p>', 505001, '对方未在线', '2020-05-09 14:33:34', 16, '2020-05-09 14:33:34', 16, '2020-05-09 14:33:34', 0, 0);
INSERT INTO `crim_chat` VALUES (5, 0, 4, 8, '<p>收到</p>', NULL, NULL, '2020-05-09 14:45:11', 4, '2020-05-09 14:45:11', 4, '2020-05-09 14:45:11', 0, 0);
INSERT INTO `crim_chat` VALUES (6, 0, 8, 4, '<p>水哦啊端技术</p>', NULL, NULL, '2020-05-09 14:45:22', 16, '2020-05-09 14:45:22', 16, '2020-05-09 14:45:22', 0, 0);
INSERT INTO `crim_chat` VALUES (7, 0, 4, 8, '<p>你才</p>', NULL, NULL, '2020-05-09 14:45:50', 4, '2020-05-09 14:45:50', 4, '2020-05-09 14:45:50', 0, 0);
INSERT INTO `crim_chat` VALUES (8, 0, 4, 8, '<p><br></p>', NULL, NULL, '2020-05-09 14:46:00', 4, '2020-05-09 14:46:00', 4, '2020-05-09 14:46:00', 0, 0);
INSERT INTO `crim_chat` VALUES (9, 0, 4, 8, '<p>去你的&nbsp;</p>', NULL, NULL, '2020-05-09 14:47:32', 4, '2020-05-09 14:47:32', 4, '2020-05-09 14:47:32', 0, 0);
INSERT INTO `crim_chat` VALUES (10, 0, 8, 4, '<p>你这BUG不是一般的多啊</p>', NULL, NULL, '2020-05-09 14:47:50', 16, '2020-05-09 14:47:50', 16, '2020-05-09 14:47:50', 0, 0);
INSERT INTO `crim_chat` VALUES (11, 0, 4, 8, '<p><br></p>', NULL, NULL, '2020-05-09 14:48:12', 4, '2020-05-09 14:48:12', 4, '2020-05-09 14:48:12', 0, 0);
INSERT INTO `crim_chat` VALUES (12, 0, 4, 8, '<p>我丢</p><p><br></p>', NULL, NULL, '2020-05-09 14:48:32', 4, '2020-05-09 14:48:32', 4, '2020-05-09 14:48:32', 0, 0);
INSERT INTO `crim_chat` VALUES (13, 0, 4, 8, '<p><br></p>', NULL, NULL, '2020-05-09 14:48:44', 4, '2020-05-09 14:48:44', 4, '2020-05-09 14:48:44', 0, 0);
INSERT INTO `crim_chat` VALUES (14, 0, 8, 4, '<p>我他么</p><p><br></p><p>无语了</p>', NULL, NULL, '2020-05-09 14:49:40', 16, '2020-05-09 14:49:40', 16, '2020-05-09 14:49:40', 0, 0);
INSERT INTO `crim_chat` VALUES (15, 0, 8, 4, '<p>bug真的很多</p><p>爱丽丝的航空</p><p>傲娇的</p>', NULL, NULL, '2020-05-09 14:50:04', 16, '2020-05-09 14:50:04', 16, '2020-05-09 14:50:04', 0, 0);
INSERT INTO `crim_chat` VALUES (16, 0, 8, 4, '<p>加速度和s</p><p>熬时间</p><p>奥苏</p><p>啊【四大</p>', NULL, NULL, '2020-05-09 14:50:18', 16, '2020-05-09 14:50:18', 16, '2020-05-09 14:50:18', 0, 0);
INSERT INTO `crim_chat` VALUES (17, 0, 4, 8, '<p>ca</p><p><br></p>', NULL, NULL, '2020-05-09 14:50:50', 4, '2020-05-09 14:50:50', 4, '2020-05-09 14:50:50', 0, 0);
INSERT INTO `crim_chat` VALUES (18, 0, 4, 8, '<p><br></p>', NULL, NULL, '2020-05-09 14:51:08', 4, '2020-05-09 14:51:08', 4, '2020-05-09 14:51:08', 0, 0);
INSERT INTO `crim_chat` VALUES (19, 0, 8, 4, '<p>我累了</p>', NULL, NULL, '2020-05-09 14:51:27', 16, '2020-05-09 14:51:27', 16, '2020-05-09 14:51:27', 0, 0);
INSERT INTO `crim_chat` VALUES (20, 0, 8, 4, '<p>你这毛病太多</p>', NULL, NULL, '2020-05-09 14:51:54', 16, '2020-05-09 14:51:54', 16, '2020-05-09 14:51:54', 0, 0);
INSERT INTO `crim_chat` VALUES (21, 0, 8, 4, '<p>换个编辑框</p>', NULL, NULL, '2020-05-09 14:52:11', 16, '2020-05-09 14:52:11', 16, '2020-05-09 14:52:11', 0, 0);
INSERT INTO `crim_chat` VALUES (22, 0, 8, 4, '老实交代为噢诶\n爱的\n啊【拍摄地\n啊【【】拍摄地\n啊【0我的\n啊【电商\n啊【0地【怕死的\n啊【0四【奥斯丁\naps9duasp0', NULL, NULL, '2020-05-09 14:57:02', 16, '2020-05-09 14:57:02', 16, '2020-05-09 14:57:02', 0, 0);
INSERT INTO `crim_chat` VALUES (23, 0, 8, 4, '我打设计费喷水电机<br>奥苏【嗲', NULL, NULL, '2020-05-09 14:59:09', 16, '2020-05-09 14:59:09', 16, '2020-05-09 14:59:09', 0, 0);
INSERT INTO `crim_chat` VALUES (24, 0, 4, 8, '我真的无语了', NULL, NULL, '2020-05-09 14:59:56', 4, '2020-05-09 14:59:56', 4, '2020-05-09 14:59:56', 0, 0);
INSERT INTO `crim_chat` VALUES (25, 0, 4, 8, '这是个什么鬼', NULL, NULL, '2020-05-09 15:00:07', 4, '2020-05-09 15:00:07', 4, '2020-05-09 15:00:07', 0, 0);
INSERT INTO `crim_chat` VALUES (26, 0, 4, 8, '丑，<br>的不行', NULL, NULL, '2020-05-09 15:01:09', 4, '2020-05-09 15:01:09', 4, '2020-05-09 15:01:09', 0, 0);
INSERT INTO `crim_chat` VALUES (27, 0, 4, 8, '试试<br>时候<br>试试<br>树敌', NULL, NULL, '2020-05-09 15:02:14', 4, '2020-05-09 15:02:14', 4, '2020-05-09 15:02:14', 0, 0);
INSERT INTO `crim_chat` VALUES (28, 0, 4, 8, '是<br>搜集的<br>平搜集的<br>是【哦对', NULL, NULL, '2020-05-09 15:05:19', 4, '2020-05-09 15:05:19', 4, '2020-05-09 15:05:19', 0, 0);
INSERT INTO `crim_chat` VALUES (29, 0, 4, 8, '是<br>搜集的<br>平搜集的<br>是【哦对', NULL, NULL, '2020-05-09 15:07:20', 4, '2020-05-09 15:07:20', 4, '2020-05-09 15:07:20', 0, 0);
INSERT INTO `crim_chat` VALUES (30, 0, 4, 8, '终于<br>哭了l<br>累了<br>笑了<br>那就这样吧<br>。。。。。', NULL, NULL, '2020-05-09 15:10:37', 4, '2020-05-09 15:10:37', 4, '2020-05-09 15:10:37', 0, 0);
INSERT INTO `crim_chat` VALUES (31, 0, 8, 4, '卡搜集的卡萨丁借记卡', 505001, '对方未在线', '2020-05-09 15:29:54', 16, '2020-05-09 15:29:54', 16, '2020-05-09 15:29:54', 0, 0);
INSERT INTO `crim_chat` VALUES (32, 0, 4, 8, '哈哈哈<br>', NULL, NULL, '2020-05-09 15:31:10', 4, '2020-05-09 15:31:10', 4, '2020-05-09 15:31:10', 0, 0);
INSERT INTO `crim_chat` VALUES (33, 0, 4, 8, '萨瓦迪卡', NULL, NULL, '2020-05-09 15:37:06', 4, '2020-05-09 15:37:06', 4, '2020-05-09 15:37:06', 0, 0);
INSERT INTO `crim_chat` VALUES (34, 0, 4, 8, '阿斯顿撒旦', NULL, NULL, '2020-05-09 15:59:27', 4, '2020-05-09 15:59:27', 4, '2020-05-09 15:59:27', 0, 0);
INSERT INTO `crim_chat` VALUES (35, 0, 4, 8, 'ca<br>', NULL, NULL, '2020-05-09 16:00:27', 4, '2020-05-09 16:00:27', 4, '2020-05-09 16:00:27', 0, 0);
INSERT INTO `crim_chat` VALUES (36, 0, 4, 8, '终于', NULL, NULL, '2020-05-09 16:00:38', 4, '2020-05-09 16:00:38', 4, '2020-05-09 16:00:38', 0, 0);
INSERT INTO `crim_chat` VALUES (37, 0, 4, 8, '我太难了', NULL, NULL, '2020-05-09 16:04:50', 4, '2020-05-09 16:04:50', 4, '2020-05-09 16:04:50', 0, 0);
INSERT INTO `crim_chat` VALUES (38, 0, 8, 4, '说来听听', NULL, NULL, '2020-05-09 16:05:16', 16, '2020-05-09 16:05:16', 16, '2020-05-09 16:05:16', 0, 0);
INSERT INTO `crim_chat` VALUES (39, 0, 4, 8, '说多都是泪<br>www<br>嘤嘤嘤怪准备要出现了', NULL, NULL, '2020-05-09 16:07:25', 4, '2020-05-09 16:07:25', 4, '2020-05-09 16:07:25', 0, 0);
INSERT INTO `crim_chat` VALUES (40, 0, 8, 4, '我吐了，正常点OK？', NULL, NULL, '2020-05-09 16:08:06', 16, '2020-05-09 16:08:06', 16, '2020-05-09 16:08:06', 0, 0);
INSERT INTO `crim_chat` VALUES (41, 0, 4, 8, 'okok', NULL, NULL, '2020-05-09 16:08:32', 4, '2020-05-09 16:08:32', 4, '2020-05-09 16:08:32', 0, 0);
INSERT INTO `crim_chat` VALUES (42, 0, 4, 8, '哎<br>累', NULL, NULL, '2020-05-09 16:08:52', 4, '2020-05-09 16:08:52', 4, '2020-05-09 16:08:52', 0, 0);
INSERT INTO `crim_chat` VALUES (43, 0, 8, 4, 'gun', NULL, NULL, '2020-05-09 16:09:02', 16, '2020-05-09 16:09:02', 16, '2020-05-09 16:09:02', 0, 0);
INSERT INTO `crim_chat` VALUES (44, 0, 8, 4, '四大', NULL, NULL, '2020-05-09 16:24:04', 16, '2020-05-09 16:24:04', 16, '2020-05-09 16:24:04', 0, 0);
INSERT INTO `crim_chat` VALUES (45, 0, 4, 8, '考虑到付款', 505001, '对方未在线', '2020-05-09 20:42:19', 4, '2020-05-09 20:42:19', 4, '2020-05-09 21:16:37', 0, 0);
INSERT INTO `crim_chat` VALUES (46, 0, 4, 8, '说的就是的', 505001, '对方未在线', '2020-05-09 20:42:34', 4, '2020-05-09 20:42:34', 4, '2020-05-09 21:16:37', 0, 0);
INSERT INTO `crim_chat` VALUES (47, 0, 8, 4, '好的呢', NULL, NULL, '2020-05-09 20:43:19', 16, '2020-05-09 20:43:19', 16, '2020-05-09 20:43:19', 0, 0);
INSERT INTO `crim_chat` VALUES (48, 0, 4, 8, '下单吧亲', NULL, NULL, '2020-05-09 20:44:48', 4, '2020-05-09 20:44:48', 4, '2020-05-09 20:44:48', 0, 0);
INSERT INTO `crim_chat` VALUES (49, 0, 8, 4, '我就不，pui', NULL, NULL, '2020-05-09 20:45:04', 16, '2020-05-09 20:45:04', 16, '2020-05-09 20:45:04', 0, 0);
INSERT INTO `crim_chat` VALUES (50, 0, 4, 8, '你是在演我？？？', NULL, NULL, '2020-05-09 20:46:19', 4, '2020-05-09 20:46:19', 4, '2020-05-09 20:46:19', 0, 0);
INSERT INTO `crim_chat` VALUES (51, 0, 8, 4, '是的呢亲', NULL, NULL, '2020-05-09 20:46:44', 16, '2020-05-09 20:46:44', 16, '2020-05-09 20:46:44', 0, 0);
INSERT INTO `crim_chat` VALUES (52, 0, 4, 8, '再见', NULL, NULL, '2020-05-09 20:48:28', 4, '2020-05-09 20:48:28', 4, '2020-05-09 20:48:28', 0, 0);
INSERT INTO `crim_chat` VALUES (53, 0, 8, 4, '好的呢', NULL, NULL, '2020-05-09 20:49:06', 16, '2020-05-09 20:49:06', 16, '2020-05-09 20:49:06', 0, 0);
INSERT INTO `crim_chat` VALUES (54, 0, 4, 8, '。。。', NULL, NULL, '2020-05-09 20:49:20', 4, '2020-05-09 20:49:20', 4, '2020-05-09 20:49:20', 0, 0);
INSERT INTO `crim_chat` VALUES (55, 0, 4, 8, '自行体会我的白眼神', NULL, NULL, '2020-05-09 20:49:45', 4, '2020-05-09 20:49:45', 4, '2020-05-09 20:49:45', 0, 0);
INSERT INTO `crim_chat` VALUES (56, 0, 8, 4, '拜拜', NULL, NULL, '2020-05-09 20:51:09', 16, '2020-05-09 20:51:09', 16, '2020-05-09 20:51:09', 0, 0);
INSERT INTO `crim_chat` VALUES (57, 0, 4, 8, '┏(＾0＾)┛', NULL, NULL, '2020-05-09 20:51:21', 4, '2020-05-09 20:51:21', 4, '2020-05-09 20:51:21', 0, 0);
INSERT INTO `crim_chat` VALUES (58, 0, 4, 8, '酷&nbsp;墨镜&nbsp;得意&nbsp;王家卫&nbsp;机智酷&nbsp;墨镜&nbsp;得意&nbsp;王家卫&nbsp;机智', NULL, NULL, '2020-05-09 20:51:41', 4, '2020-05-09 20:51:41', 4, '2020-05-09 20:51:41', 0, 0);
INSERT INTO `crim_chat` VALUES (59, 0, 8, 4, '对方', NULL, NULL, '2020-05-09 20:52:46', 16, '2020-05-09 20:52:46', 16, '2020-05-09 20:52:46', 0, 0);
INSERT INTO `crim_chat` VALUES (60, 0, 4, 8, '是', NULL, NULL, '2020-05-09 20:53:18', 4, '2020-05-09 20:53:18', 4, '2020-05-09 20:53:18', 0, 0);
INSERT INTO `crim_chat` VALUES (61, 0, 4, 8, '你', NULL, NULL, '2020-05-09 20:53:29', 4, '2020-05-09 20:53:29', 4, '2020-05-09 20:53:29', 0, 0);
INSERT INTO `crim_chat` VALUES (62, 0, 4, 8, '吗', NULL, NULL, '2020-05-09 20:53:35', 4, '2020-05-09 20:53:35', 4, '2020-05-09 20:53:35', 0, 0);
INSERT INTO `crim_chat` VALUES (63, 0, 8, 4, '不是走开', NULL, NULL, '2020-05-09 20:53:42', 16, '2020-05-09 20:53:42', 16, '2020-05-09 20:53:42', 0, 0);
INSERT INTO `crim_chat` VALUES (64, 0, 4, 8, '我<br>哈<br>哈<br>哈<br>哈<br>哈<br>了', NULL, NULL, '2020-05-09 20:54:09', 4, '2020-05-09 20:54:09', 4, '2020-05-09 20:54:09', 0, 0);
INSERT INTO `crim_chat` VALUES (65, 0, 4, 8, '是', NULL, NULL, '2020-05-09 20:55:04', 4, '2020-05-09 20:55:04', 4, '2020-05-09 20:55:04', 0, 0);
INSERT INTO `crim_chat` VALUES (66, 0, 4, 8, '阿萨飒飒的', NULL, NULL, '2020-05-09 20:55:09', 4, '2020-05-09 20:55:09', 4, '2020-05-09 20:55:09', 0, 0);
INSERT INTO `crim_chat` VALUES (67, 0, 4, 8, '书大户', NULL, NULL, '2020-05-09 20:58:57', 4, '2020-05-09 20:58:57', 4, '2020-05-09 20:58:57', 0, 0);
INSERT INTO `crim_chat` VALUES (68, 0, 4, 8, 'as多划算', NULL, NULL, '2020-05-09 20:59:03', 4, '2020-05-09 20:59:03', 4, '2020-05-09 20:59:03', 0, 0);
INSERT INTO `crim_chat` VALUES (69, 0, 8, 4, '的压碎<br>爱拍速冻', NULL, NULL, '2020-05-09 20:59:13', 16, '2020-05-09 20:59:13', 16, '2020-05-09 20:59:13', 0, 0);
INSERT INTO `crim_chat` VALUES (70, 0, 8, 4, '让国人', NULL, NULL, '2020-05-09 20:59:16', 16, '2020-05-09 20:59:16', 16, '2020-05-09 20:59:16', 0, 0);
INSERT INTO `crim_chat` VALUES (71, 0, 8, 4, '规范和法国恢复', NULL, NULL, '2020-05-09 20:59:19', 16, '2020-05-09 20:59:19', 16, '2020-05-09 20:59:19', 0, 0);
INSERT INTO `crim_chat` VALUES (72, 0, 8, 4, '欠人情翁群翁多群无', NULL, NULL, '2020-05-09 20:59:22', 16, '2020-05-09 20:59:22', 16, '2020-05-09 20:59:22', 0, 0);
INSERT INTO `crim_chat` VALUES (73, 0, 8, 4, '阿萨达', NULL, NULL, '2020-05-09 20:59:34', 16, '2020-05-09 20:59:34', 16, '2020-05-09 20:59:34', 0, 0);
INSERT INTO `crim_chat` VALUES (74, 0, 8, 4, '水电费水电费', NULL, NULL, '2020-05-09 20:59:38', 16, '2020-05-09 20:59:38', 16, '2020-05-09 20:59:38', 0, 0);
INSERT INTO `crim_chat` VALUES (75, 0, 8, 4, '收到', NULL, NULL, '2020-05-09 21:00:11', 16, '2020-05-09 21:00:11', 16, '2020-05-09 21:00:11', 0, 0);
INSERT INTO `crim_chat` VALUES (76, 0, 8, 4, '收到的', NULL, NULL, '2020-05-09 21:00:14', 16, '2020-05-09 21:00:14', 16, '2020-05-09 21:00:14', 0, 0);
INSERT INTO `crim_chat` VALUES (77, 0, 8, 4, '按时大大', NULL, NULL, '2020-05-09 21:00:16', 16, '2020-05-09 21:00:16', 16, '2020-05-09 21:00:16', 0, 0);
INSERT INTO `crim_chat` VALUES (78, 0, 4, 8, '按时大大', NULL, NULL, '2020-05-09 21:00:59', 4, '2020-05-09 21:00:59', 4, '2020-05-09 21:00:59', 0, 0);
INSERT INTO `crim_chat` VALUES (79, 0, 4, 8, '按时大大', NULL, NULL, '2020-05-09 21:01:04', 4, '2020-05-09 21:01:04', 4, '2020-05-09 21:01:04', 0, 0);
INSERT INTO `crim_chat` VALUES (80, 0, 4, 8, '收到的', NULL, NULL, '2020-05-09 21:01:07', 4, '2020-05-09 21:01:07', 4, '2020-05-09 21:01:07', 0, 0);
INSERT INTO `crim_chat` VALUES (81, 0, 4, 8, '啊实打实的', NULL, NULL, '2020-05-09 21:01:09', 4, '2020-05-09 21:01:09', 4, '2020-05-09 21:01:09', 0, 0);
INSERT INTO `crim_chat` VALUES (82, 0, 4, 8, '阿达萨达是', NULL, NULL, '2020-05-09 21:01:11', 4, '2020-05-09 21:01:11', 4, '2020-05-09 21:01:11', 0, 0);
INSERT INTO `crim_chat` VALUES (83, 0, 4, 8, '撒', NULL, NULL, '2020-05-09 21:02:04', 4, '2020-05-09 21:02:04', 4, '2020-05-09 21:02:04', 0, 0);
INSERT INTO `crim_chat` VALUES (84, 0, 8, 4, '爱是低价克里斯<br>爱搜点击<br>奥斯', NULL, NULL, '2020-05-09 21:02:12', 16, '2020-05-09 21:02:12', 16, '2020-05-09 21:02:12', 0, 0);
INSERT INTO `crim_chat` VALUES (85, 0, 8, 4, '按时大大', NULL, NULL, '2020-05-09 21:02:13', 16, '2020-05-09 21:02:13', 16, '2020-05-09 21:02:13', 0, 0);
INSERT INTO `crim_chat` VALUES (86, 0, 8, 4, '按时大大', NULL, NULL, '2020-05-09 21:02:16', 16, '2020-05-09 21:02:16', 16, '2020-05-09 21:02:16', 0, 0);
INSERT INTO `crim_chat` VALUES (87, 0, 8, 4, '阿萨大师', NULL, NULL, '2020-05-09 21:02:18', 16, '2020-05-09 21:02:18', 16, '2020-05-09 21:02:18', 0, 0);
INSERT INTO `crim_chat` VALUES (88, 0, 4, 8, '爱上大城市', 505001, '对方未在线', '2020-05-09 21:07:12', 4, '2020-05-09 21:07:12', 4, '2020-05-09 21:16:37', 0, 0);
INSERT INTO `crim_chat` VALUES (89, 0, 8, 4, '阿萨达', 505001, '对方未在线', '2020-05-09 21:12:47', 16, '2020-05-09 21:12:47', 16, '2020-05-09 21:29:20', 0, 0);
INSERT INTO `crim_chat` VALUES (90, 0, 4, 8, '阿萨德', NULL, NULL, '2020-05-09 21:13:00', 4, '2020-05-09 21:13:00', 4, '2020-05-09 21:13:00', 0, 0);
INSERT INTO `crim_chat` VALUES (91, 0, 4, 8, '&nbsp;撒', NULL, NULL, '2020-05-09 21:19:47', 4, '2020-05-09 21:19:47', 4, '2020-05-09 21:19:47', 0, 0);
INSERT INTO `crim_chat` VALUES (92, 0, 4, 8, '等我', 505001, '对方未在线', '2020-05-09 21:27:17', 4, '2020-05-09 21:27:17', 4, '2020-05-09 21:52:11', 0, 0);
INSERT INTO `crim_chat` VALUES (93, 0, 8, 3, '澳石油爱搜', 505001, '对方未在线', '2020-05-12 22:31:21', 16, '2020-05-12 22:31:21', 16, '2020-05-12 22:31:26', 0, 0);
INSERT INTO `crim_chat` VALUES (94, 0, 3, 8, 'ASDUHASUDH&nbsp;', 505001, '对方未在线', '2020-05-12 22:31:32', 3, '2020-05-12 22:31:32', 3, '2020-05-12 22:31:44', 0, 0);
INSERT INTO `crim_chat` VALUES (95, 0, 8, 3, 'ASJASD', NULL, NULL, '2020-05-12 22:31:49', 16, '2020-05-12 22:31:49', 16, '2020-05-12 22:31:49', 0, 0);
INSERT INTO `crim_chat` VALUES (96, 0, 3, 8, 'ASDPASUPD&nbsp;', NULL, NULL, '2020-05-12 22:31:53', 3, '2020-05-12 22:31:53', 3, '2020-05-12 22:31:53', 0, 0);
INSERT INTO `crim_chat` VALUES (97, 0, 3, 8, '张三，你好', NULL, NULL, '2020-05-12 22:40:51', 3, '2020-05-12 22:40:51', 3, '2020-05-12 22:40:51', 0, 0);
INSERT INTO `crim_chat` VALUES (98, 0, 8, 3, '你好呀', NULL, NULL, '2020-05-12 22:47:33', 16, '2020-05-12 22:47:33', 16, '2020-05-12 22:47:33', 0, 0);
INSERT INTO `crim_chat` VALUES (99, 0, 3, 8, '我们非常需要你~', NULL, NULL, '2020-05-12 22:48:29', 3, '2020-05-12 22:48:29', 3, '2020-05-12 22:48:29', 0, 0);
INSERT INTO `crim_chat` VALUES (100, 0, 8, 3, '我就不去<br>不去不去', NULL, NULL, '2020-05-12 22:48:53', 16, '2020-05-12 22:48:53', 16, '2020-05-12 22:48:53', 0, 0);
INSERT INTO `crim_chat` VALUES (101, 0, 3, 8, '这么拒绝我们真的好吗？？？', NULL, NULL, '2020-05-12 22:49:13', 3, '2020-05-12 22:49:13', 3, '2020-05-12 22:49:13', 0, 0);
INSERT INTO `crim_chat` VALUES (102, 0, 8, 3, '小朋友，你是否有很多问号？？？', NULL, NULL, '2020-05-12 22:49:29', 16, '2020-05-12 22:49:29', 16, '2020-05-12 22:49:29', 0, 0);
INSERT INTO `crim_chat` VALUES (103, 0, 3, 8, '蓁蓁同学，你好', 505001, '对方未在线', '2020-05-28 00:12:41', 3, '2020-05-28 00:12:41', 3, '2020-05-28 00:12:59', 0, 0);
INSERT INTO `crim_chat` VALUES (104, 0, 8, 3, '您好', NULL, NULL, '2020-05-28 00:13:25', 16, '2020-05-28 00:13:25', 16, '2020-05-28 00:13:25', 0, 0);
INSERT INTO `crim_chat` VALUES (105, 0, 3, 8, '我们看了你的简历，觉得你还不错，期待你来参加我们的宣讲会哦', NULL, NULL, '2020-05-28 00:14:57', 3, '2020-05-28 00:14:57', 3, '2020-05-28 00:14:57', 0, 0);
INSERT INTO `crim_chat` VALUES (106, 0, 3, 8, '蓁蓁同学，你好', NULL, NULL, '2020-05-28 00:16:49', 3, '2020-05-28 00:16:50', 3, '2020-05-28 00:16:50', 0, 0);
INSERT INTO `crim_chat` VALUES (107, 0, 8, 3, '您好呀', NULL, NULL, '2020-05-28 00:17:09', 16, '2020-05-28 00:17:09', 16, '2020-05-28 00:17:09', 0, 0);
INSERT INTO `crim_chat` VALUES (108, 0, 3, 8, '我们看了你的简历，觉得你还不错，期待你参与我们的招聘会哦', NULL, NULL, '2020-05-28 00:17:58', 3, '2020-05-28 00:17:58', 3, '2020-05-28 00:17:58', 0, 0);
INSERT INTO `crim_chat` VALUES (109, 0, 8, 3, '谢谢~<br>我也很心仪贵公司，期待您的到来', NULL, NULL, '2020-05-28 00:18:32', 16, '2020-05-28 00:18:32', 16, '2020-05-28 00:18:32', 0, 0);
INSERT INTO `crim_chat` VALUES (110, 0, 8, 3, '是是是', 505001, '对方未在线', '2020-06-05 21:15:17', 16, '2020-06-05 21:15:17', 16, '2020-06-05 21:17:46', 0, 0);
INSERT INTO `crim_chat` VALUES (111, 0, 8, 3, '啦啦啦', 505001, '对方未在线', '2020-06-05 21:15:33', 16, '2020-06-05 21:15:33', 16, '2020-06-05 21:17:46', 0, 0);
INSERT INTO `crim_chat` VALUES (112, 0, 8, 3, '实打实', 505001, '对方未在线', '2020-06-05 21:15:39', 16, '2020-06-05 21:15:39', 16, '2020-06-05 21:17:46', 0, 0);
INSERT INTO `crim_chat` VALUES (113, 0, 3, 8, '消息', 505001, '对方未在线', '2020-06-05 21:18:00', 3, '2020-06-05 21:18:00', 3, '2020-06-05 21:18:00', 1, 0);

-- ----------------------------
-- Table structure for crim_company
-- ----------------------------
DROP TABLE IF EXISTS `crim_company`;
CREATE TABLE `crim_company`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司名称',
  `logo_id` int(11) DEFAULT NULL COMMENT 'logo文件id',
  `link_man` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `legal_man` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '法人代表',
  `registered_capital` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '注册资本',
  `registered_date` date DEFAULT NULL COMMENT '成立日期',
  `registered_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '注册地址',
  `business_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '经营状态',
  `company_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司类型',
  `company_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司地址',
  `credit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '社会统一信用代码',
  `business_scope` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '经营范围',
  `company_desc` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司简介',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态： 0 注册审核通过 1 已提交注册审核 2 注册审核不通过',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_company
-- ----------------------------
INSERT INTO `crim_company` VALUES (1, 4, '中国移动', NULL, NULL, NULL, '张xx', '5000000.00万元人民币', '2020-05-04', '北京市', '在营', '上市股份有限公司', '北京市', '10000000000XXXXXXXXXX', NULL, NULL, 4, '2020-05-04 21:14:07', 4, '2020-05-04 21:14:07', NULL, 1);
INSERT INTO `crim_company` VALUES (2, 4, '中国移动', NULL, '张默默', '13888888888', '张xx', '5000000.00万元人民币', '2020-05-04', '北京市', '在营', '上市股份有限公司', '北京市', '10000000000XXXXXXXXXX', '经营范围<br>经营范围<br>经营范围<br>经营范围经营范围经营范围<br>经营范围<br>', '简介简介简介简介&nbsp;&nbsp;简介简介1234<br><br>bbq', 4, '2020-05-04 21:14:54', 1, '2020-05-10 00:29:53', 2, 1);
INSERT INTO `crim_company` VALUES (3, 17, '中国移动', NULL, '周吴郑王', '13878657039', '赵钱孙李', '50000.00万元人民币', '2020-05-12', '北京市朝阳区xx街道001号', '在营', '股份有限公司', '北京市朝阳区xx街道001号', '10000000000000XXXXXXXX', '计算机软硬件开发销售，计算机、电子、生物、医药、汽车技术领域内的四技服务，生物工程及生物制品研制、开发、销售，整流器生产、开发，计算机网络工程，电脑网络软件开发。<br>通讯设备的技术开发、技术咨询、技术服务。机械设备安装，通信设备、电子产品、电脑软硬件的销售。<br>计算机领域的技术开发、技术转让、技术咨询和技术服务，网络工程，计算机软件开发、销售、电脑维修服务。', '华为是谁？<br>华为创立于1987年，是全球领先的ICT（信息与通信）基础设施和智能终端提供商，我们致力于把数字世界带入每个人、每个家庭、每个组织，构建万物互联的智能世界。目前华为约有19.4万员工，业务遍及170多个国家和地区，服务30多亿人口。<br>谁拥有华为？<br>华为是一家100%由员工持有的民营企业。华为通过工会实行员工持股计划，参与人数为104,572人，参与人仅为公司员工，没有任何政府部门、机构持有华为股权。<br>', 17, '2020-05-12 15:11:02', 17, '2020-06-06 14:48:08', 0, 0);
INSERT INTO `crim_company` VALUES (4, 3, '中国电信集团', NULL, '蒋沈韩杨', '13777777777', '陈褚卫', '100万元', '2020-05-04', '广州', '开业', '责任有限公司', '广州', '91110000100017707H', '经营范围经营范围经营范围经营范围经营范围<br>经营范围经营范围经营范围经营范围经营范围<br><br>经营范围经营范围经营范围经营范围经营范围', '中国电信股份有限公司（「中国电信」或「本公司」，于中华人民共和国注册成立的股份有限公司，与其附属公司合称「本集团」）是一家全球大型的领先的全业务综合智能信息服务运营商，主要在中国提供固定及移动通信服务、互联网接入服务、信息服务，以及其他增值电信服务。于2019年底，本公司拥有约3.36亿移动用户、约1.53亿有线宽带用户及约1.11亿固定电话用户。本公司发行的H股及美国存托股份分别在香港联合交易所有限公司（「香港联交所」或「联交所」）和纽约证券交易所挂牌上市。', 3, '2020-05-12 17:15:48', 3, '2020-05-28 00:24:57', 0, 0);

-- ----------------------------
-- Table structure for crim_department
-- ----------------------------
DROP TABLE IF EXISTS `crim_department`;
CREATE TABLE `crim_department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '院系id',
  `campus_id` int(11) NOT NULL COMMENT '校区id：type表campus子节点id',
  `department_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '院系名称',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_department
-- ----------------------------
INSERT INTO `crim_department` VALUES (1, 2, '数学与信息科技学院', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_department` VALUES (2, 2, '工商管理学院', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_department` VALUES (3, 2, '城市建设学院', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_department` VALUES (4, 2, '财经学院', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_department` VALUES (5, 3, '教育学院', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_department` VALUES (6, 3, '艺术学院', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_department` VALUES (7, 4, '农学与生物科技学院', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `crim_department` VALUES (8, 2, '体育系', 1, '2020-04-30 01:33:10', 1, '2020-04-30 01:33:10', NULL, 1);
INSERT INTO `crim_department` VALUES (9, 2, '体育系', 1, '2020-04-30 01:50:20', 1, '2020-04-30 01:50:20', NULL, 1);
INSERT INTO `crim_department` VALUES (10, 4, '农学与生物科技学院', 1, '2020-04-30 01:53:07', 1, '2020-04-30 01:53:07', NULL, 0);

-- ----------------------------
-- Table structure for crim_education_experience
-- ----------------------------
DROP TABLE IF EXISTS `crim_education_experience`;
CREATE TABLE `crim_education_experience`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教育经历id',
  `resume_id` int(11) NOT NULL COMMENT '简历id',
  `school_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学校名称',
  `certificate` int(11) NOT NULL COMMENT '学位：\r\n0 初中及以下 \r\n1 中专/中技 \r\n2 高中 \r\n3 大专 \r\n4 本科 \r\n5 硕士 \r\n6 博士',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主修专业',
  `study_start` date NOT NULL COMMENT '开始日期',
  `study_end` date NOT NULL COMMENT '结束日期',
  `school_experience` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '在校经历',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_education_experience
-- ----------------------------
INSERT INTO `crim_education_experience` VALUES (1, 1, '啊实打实的哈里斯的', 2, '阿斯的煎熬食品级大师', '2020-05-01', '2020-08-01', '阿善动啊是好多<br>安排【电商【&nbsp;&nbsp;<br>阿萨德阿萨德啊&nbsp;a【】拍摄地阿斯啊<br>是a视频课', 16, '2020-05-08 00:37:48', 16, '2020-05-08 00:37:48', NULL, 0);
INSERT INTO `crim_education_experience` VALUES (2, 1, '啊杀死殴打回家啊', 6, '驱蚊器翁', '2020-01-01', '2021-12-01', '就啊asOK打算d阿萨德哈市&nbsp;&nbsp;&nbsp;阿萨德和静安寺&nbsp;阿斯&nbsp;撒', 16, '2020-05-08 00:38:24', 16, '2020-05-08 00:38:24', NULL, 1);

-- ----------------------------
-- Table structure for crim_expected_position
-- ----------------------------
DROP TABLE IF EXISTS `crim_expected_position`;
CREATE TABLE `crim_expected_position`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '期望职位id',
  `resume_id` int(11) NOT NULL COMMENT '简历id',
  `position_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望职位，如：UI设计师...',
  `expected_pay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望薪资，如：10-12K',
  `expected_trade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望行业，如：金融/互联网/制造业...',
  `expected_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望城市，如：北京/广州/武汉...',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_expected_position
-- ----------------------------
INSERT INTO `crim_expected_position` VALUES (1, 1, 'UI设计师', '10K', '互联网', '广州/南宁', 16, '2020-05-07 23:52:44', 16, '2020-05-07 23:53:14', NULL, 0);
INSERT INTO `crim_expected_position` VALUES (2, 1, 'JAVA开发', '12K', '互联网', '广州', 16, '2020-05-07 23:53:43', 16, '2020-05-07 23:53:43', NULL, 1);
INSERT INTO `crim_expected_position` VALUES (3, 1, '销售', '8K', '房地产', '重庆', 16, '2020-05-08 01:29:26', 16, '2020-05-08 01:29:26', NULL, 0);

-- ----------------------------
-- Table structure for crim_file
-- ----------------------------
DROP TABLE IF EXISTS `crim_file`;
CREATE TABLE `crim_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
  `file_uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件UUID',
  `file_suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件后缀',
  `file_size` int(11) NOT NULL COMMENT '文件大小（KB）',
  `file_type` int(11) NOT NULL COMMENT '文件类型：0 头像/logo 1 营业执照 2 其他文件 3 证件照',
  `file_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件地址',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态：0 挂载 1 空闲',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_file
-- ----------------------------
INSERT INTO `crim_file` VALUES (1, 1, '校徽', '6ece36e1-384a-4fac-ba79-96569dec9483.jpeg', 'jpeg', 54, 0, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/6ece36e1-384a-4fac-ba79-96569dec9483.jpeg', 1, '2020-04-29 02:20:16', 1, '2020-05-12 16:09:34', NULL, 0);
INSERT INTO `crim_file` VALUES (2, 1, '404', '521b82b2-5790-457d-b3a8-dfa8c7287f19.gif', 'gif', 19, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/521b82b2-5790-457d-b3a8-dfa8c7287f19.gif?Expires=1896082201&OSSAccessKeyId=LTAI4Fhi546AL87pHNV48eG7&Signature=DA3krE8ozIbADlBhnvPvwOWxb3g%3D', 1, '2020-05-03 17:30:03', 1, '2020-05-03 17:30:03', 0, 0);
INSERT INTO `crim_file` VALUES (3, 1, '2020显卡天梯', '902f47c3-75ed-480d-9059-cd40bb276268.jpg', 'jpg', 402861, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/902f47c3-75ed-480d-9059-cd40bb276268.jpg?Expires=1896082202&OSSAccessKeyId=LTAI4Fhi546AL87pHNV48eG7&Signature=KUzJrhmh%2BL6PegemtDFX7cvcZWM%3D', 1, '2020-05-03 17:30:03', 1, '2020-05-03 17:30:03', 1, 1);
INSERT INTO `crim_file` VALUES (4, 1, '0913160406何宇 - 综测', 'c657bfd1-2a91-45c2-966d-5ebda7a3e1d9.xls', 'xls', 43008, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/c657bfd1-2a91-45c2-966d-5ebda7a3e1d9.xls?Expires=1896082202&OSSAccessKeyId=LTAI4Fhi546AL87pHNV48eG7&Signature=j62nzKpugrVu1fqkkQhREk2wnfc%3D', 1, '2020-05-03 17:30:03', 1, '2020-05-03 17:30:03', 1, 1);
INSERT INTO `crim_file` VALUES (5, 1, 'JavaEE_API ', '00998ce2-f3c0-4d38-9676-683610fb2857.chm', 'chm', 6930265, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/00998ce2-f3c0-4d38-9676-683610fb2857.chm?Expires=1896082202&OSSAccessKeyId=LTAI4Fhi546AL87pHNV48eG7&Signature=E40dvRZWC07bCaQjm901VPpx62s%3D', 1, '2020-05-03 17:30:03', 1, '2020-05-03 17:30:03', 1, 1);
INSERT INTO `crim_file` VALUES (6, 1, 'JavaEE_API ', 'f9b7709a-ed05-4faa-903a-cf17cdf524f6.chw', 'chw', 1223248, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/f9b7709a-ed05-4faa-903a-cf17cdf524f6.chw?Expires=1896082203&OSSAccessKeyId=LTAI4Fhi546AL87pHNV48eG7&Signature=PWrr%2BVYI%2BIbZzaaz3MRRWEYRc4k%3D', 1, '2020-05-03 17:30:03', 1, '2020-05-03 17:30:03', 1, 1);
INSERT INTO `crim_file` VALUES (7, 4, '中国移动logo', 'a81c3c98-bb7f-4cc7-a815-0e19b786d5cc.jpg', 'jpg', 20, 0, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/a81c3c98-bb7f-4cc7-a815-0e19b786d5cc.jpg', 4, '2020-05-04 21:45:16', 4, '2020-05-09 20:48:50', NULL, 0);
INSERT INTO `crim_file` VALUES (8, 4, '营业执照', 'f9f61631-f7f4-44fe-bd08-5c627342e18e.jpeg', 'jpeg', 30, 1, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/f9f61631-f7f4-44fe-bd08-5c627342e18e.jpeg', 4, '2020-05-04 21:58:21', 4, '2020-05-04 22:01:17', NULL, 1);
INSERT INTO `crim_file` VALUES (9, 4, '营业执照', '457dd864-f00a-4c06-9e8c-ab7c41cf936b.jpeg', 'jpeg', 30, 1, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/457dd864-f00a-4c06-9e8c-ab7c41cf936b.jpeg', 4, '2020-05-04 21:58:35', 4, '2020-05-04 21:58:35', NULL, 1);
INSERT INTO `crim_file` VALUES (10, 4, '营业执照', '52be41b7-e7cc-4c75-82ad-b0e084937af3.jpeg', 'jpeg', 30, 1, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/52be41b7-e7cc-4c75-82ad-b0e084937af3.jpeg', 4, '2020-05-04 22:00:29', 4, '2020-05-04 22:00:29', NULL, 1);
INSERT INTO `crim_file` VALUES (11, 4, '营业执照', '12f69aff-98d1-4084-924b-21181ec8d701.jpeg', 'jpeg', 30, 1, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/12f69aff-98d1-4084-924b-21181ec8d701.jpeg', 4, '2020-05-04 22:55:09', 4, '2020-05-04 22:55:09', NULL, 0);
INSERT INTO `crim_file` VALUES (12, 8, '帅', 'b6833f20-9348-48f4-84e8-c76bfd010c24.jpeg', 'jpeg', 33, 0, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/b6833f20-9348-48f4-84e8-c76bfd010c24.jpeg', 8, '2020-05-07 00:12:02', 8, '2020-05-07 00:12:02', NULL, 0);
INSERT INTO `crim_file` VALUES (13, 8, '证件照', 'eb76fe66-bfee-4592-9c7a-1f39b66eedd3.jpg', 'jpg', 24, 3, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/eb76fe66-bfee-4592-9c7a-1f39b66eedd3.jpg', 16, '2020-05-08 01:37:36', 16, '2020-05-08 01:44:33', NULL, 0);
INSERT INTO `crim_file` VALUES (14, 17, '营业执照', 'dda1e64c-7cff-498e-8db5-0713bead2ac2.jpeg', 'jpeg', 30, 1, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/dda1e64c-7cff-498e-8db5-0713bead2ac2.jpeg', 17, '2020-05-12 15:18:56', 17, '2020-05-12 15:18:56', NULL, 0);
INSERT INTO `crim_file` VALUES (15, 3, '营业执照', '098e2c48-c635-45c2-8356-83076a2d3054.jpeg', 'jpeg', 30, 1, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/098e2c48-c635-45c2-8356-83076a2d3054.jpeg', 3, '2020-05-12 17:16:03', 3, '2020-05-12 17:16:03', NULL, 0);
INSERT INTO `crim_file` VALUES (16, 3, '中国电信', '321db090-b5d5-4edf-9b33-ec9d091dde0e.jpg', 'jpg', 29, 0, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/321db090-b5d5-4edf-9b33-ec9d091dde0e.jpg', 3, '2020-05-12 17:17:30', 3, '2020-05-12 22:40:32', NULL, 0);
INSERT INTO `crim_file` VALUES (17, 1, 'JWT组成图示', 'd326916c-5cc1-4a3e-b32b-db4a7a4e68de.png', 'png', 49, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/d326916c-5cc1-4a3e-b32b-db4a7a4e68de.png?Expires=1898946812&OSSAccessKeyId=LTAI4GEvxoPe8YRYEmzqvV6S&Signature=3jWY96R6FjMejnkwTLrUkmMtTAc%3D', 1, '2020-06-05 21:13:32', 1, '2020-06-05 21:13:32', 1, 0);

-- ----------------------------
-- Table structure for crim_forbidden_record
-- ----------------------------
DROP TABLE IF EXISTS `crim_forbidden_record`;
CREATE TABLE `crim_forbidden_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '禁用记录id',
  `forbidden_id` int(11) NOT NULL COMMENT '被禁账户',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '禁用原因',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(255) DEFAULT NULL COMMENT '状态',
  `is_delete` int(255) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crim_grade_class
-- ----------------------------
DROP TABLE IF EXISTS `crim_grade_class`;
CREATE TABLE `crim_grade_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `profession_id` int(11) NOT NULL COMMENT '专业id',
  `year_level_id` int(11) NOT NULL COMMENT '年级id：type表year_level子节点id',
  `grade_class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_grade_class
-- ----------------------------
INSERT INTO `crim_grade_class` VALUES (1, 1, 6, '计算机 1801班', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_grade_class` VALUES (2, 1, 6, '计算机 1802班', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_grade_class` VALUES (3, 2, 7, '网络 1901班', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_grade_class` VALUES (4, 1, 9, '科技1601班', 1, '2020-04-30 22:27:30', 1, '2020-04-30 22:27:30', NULL, 0);
INSERT INTO `crim_grade_class` VALUES (5, 1, 9, '科技1602班', 1, '2020-04-30 22:30:35', 1, '2020-04-30 22:30:35', NULL, 0);

-- ----------------------------
-- Table structure for crim_home_page
-- ----------------------------
DROP TABLE IF EXISTS `crim_home_page`;
CREATE TABLE `crim_home_page`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '社交主页id',
  `resume_id` int(11) NOT NULL COMMENT '简历id',
  `home_page_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '社交主页网址',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_home_page
-- ----------------------------
INSERT INTO `crim_home_page` VALUES (1, 1, 'https://www.cctv.cn', 16, '2020-05-08 01:22:22', 16, '2020-05-08 01:22:22', NULL, 1);
INSERT INTO `crim_home_page` VALUES (2, 1, 'https://www.cctv.cn', 16, '2020-05-08 01:27:46', 16, '2020-05-08 01:27:46', NULL, 0);
INSERT INTO `crim_home_page` VALUES (3, 1, 'https://www.cctv.com', 16, '2020-05-08 01:28:05', 16, '2020-05-08 01:28:05', NULL, 0);

-- ----------------------------
-- Table structure for crim_internship_experience
-- ----------------------------
DROP TABLE IF EXISTS `crim_internship_experience`;
CREATE TABLE `crim_internship_experience`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '实习经历id',
  `resume_id` int(11) NOT NULL COMMENT '简历id',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实习公司名称',
  `company_trade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '行业',
  `affiliated_dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实习所在部门',
  `position_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实习职位名称',
  `job_start` date NOT NULL COMMENT '实习开始日期',
  `job_end` date NOT NULL COMMENT '实习结束日期',
  `work_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实习工作内容',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_internship_experience
-- ----------------------------
INSERT INTO `crim_internship_experience` VALUES (1, 1, '啊实打实的', '啊打算输入法感受到 ', '是否是', '的否dsf', '2020-01-01', '2020-03-01', '是的覅是是的覅是&nbsp;胜多负少&nbsp;是的覅&nbsp;&nbsp;是的是的方法<br>阿斯U盾贵啊<br><br>阿斯对于&nbsp;', 16, '2020-05-08 00:07:43', 16, '2020-05-08 00:15:14', NULL, 0);
INSERT INTO `crim_internship_experience` VALUES (2, 1, '阿斯顿撒', '爱上大叔打所多', '阿萨德阿萨德 ', '啊是大大大', '2020-01-01', '2021-07-01', '爱上大叔打所大所啊的阿萨德阿萨德阿萨德阿萨德<br>爱豆iy手动我阿善动&nbsp;&nbsp;<br>奥圣诞节啊实打实地', 16, '2020-05-08 00:15:48', 16, '2020-05-08 00:15:48', NULL, 1);

-- ----------------------------
-- Table structure for crim_interview
-- ----------------------------
DROP TABLE IF EXISTS `crim_interview`;
CREATE TABLE `crim_interview`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '面试记录id',
  `company_id` int(11) NOT NULL COMMENT '企业id',
  `career_talk_id` int(11) NOT NULL COMMENT '宣讲会id',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位名称',
  `regular_pay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最终薪资：如 10-12K',
  `work_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作地点',
  `welfare` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最终福利',
  `is_last_choice` int(11) NOT NULL COMMENT '是否最终选择：0 否 1 是',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) NOT NULL COMMENT '状态：0 未开始 1 待回复 2 已通过 3 被拒绝',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_interview
-- ----------------------------
INSERT INTO `crim_interview` VALUES (1, 2, 4, 1, 'JAVA', '10K', '北京', NULL, 0, 16, '2020-05-08 21:07:54', 16, '2020-05-08 21:07:54', 0, 1);
INSERT INTO `crim_interview` VALUES (2, 2, 1, 1, 'JAVA', '10K', '北京', NULL, 0, 16, '2020-05-08 21:16:51', 16, '2020-05-08 21:16:51', 0, 1);
INSERT INTO `crim_interview` VALUES (3, 2, 1, 1, 'UI设计师', '12K', '广州', NULL, 0, 16, '2020-05-08 21:17:08', 16, '2020-05-08 21:17:08', 0, 1);
INSERT INTO `crim_interview` VALUES (4, 2, 1, 1, 'UI设计师', '12K', '广州', NULL, 0, 16, '2020-05-08 21:19:40', 16, '2020-05-08 21:19:40', 0, 1);
INSERT INTO `crim_interview` VALUES (5, 2, 4, 1, 'JAVA', '10K', '北京', NULL, 0, 16, '2020-05-08 21:20:23', 16, '2020-05-08 21:20:23', 0, 1);
INSERT INTO `crim_interview` VALUES (6, 2, 1, 1, 'UI设计师', '12K', '广州', NULL, 0, 16, '2020-05-08 21:20:26', 16, '2020-05-08 21:20:26', 0, 1);
INSERT INTO `crim_interview` VALUES (7, 2, 1, 1, 'JAVA', '10K', '北京', NULL, 0, 16, '2020-05-08 21:20:58', 16, '2020-05-08 21:20:58', 0, 1);
INSERT INTO `crim_interview` VALUES (8, 2, 4, 1, 'UI设计师', '12K', '广州', NULL, 0, 16, '2020-05-08 21:21:00', 16, '2020-05-08 21:21:00', 0, 1);
INSERT INTO `crim_interview` VALUES (9, 2, 1, 1, 'JAVA', '10K', '北京', NULL, 0, 16, '2020-05-08 21:38:19', 16, '2020-05-08 21:38:19', 0, 1);
INSERT INTO `crim_interview` VALUES (10, 2, 4, 1, 'UI设计师', '12K', '广州', NULL, 0, 16, '2020-05-08 21:41:01', 16, '2020-05-08 22:14:01', 0, 1);
INSERT INTO `crim_interview` VALUES (11, 2, 1, 1, 'UI设计师', '12K', '广州', NULL, 0, 16, '2020-05-08 21:45:33', 16, '2020-05-08 21:45:33', 0, 1);
INSERT INTO `crim_interview` VALUES (12, 2, 1, 1, 'JAVA', '10K', '北京', NULL, 0, 16, '2020-05-08 22:13:29', 16, '2020-05-08 22:14:16', 0, 1);
INSERT INTO `crim_interview` VALUES (13, 2, 1, 1, 'JAVA', '10K', '北京', NULL, 0, 16, '2020-05-08 22:15:17', 16, '2020-05-08 22:15:17', 0, 1);
INSERT INTO `crim_interview` VALUES (14, 2, 4, 1, 'UI设计师', '12K', '广州', NULL, 0, 16, '2020-05-08 22:15:21', 16, '2020-05-08 22:15:21', 0, 1);
INSERT INTO `crim_interview` VALUES (15, 2, 1, 1, 'JAVA', '10K', '北京', NULL, 0, 16, '2020-05-09 16:36:22', 16, '2020-05-09 16:36:22', 0, 1);
INSERT INTO `crim_interview` VALUES (16, 4, 5, 1, 'UI设计师', '10k', '广州', NULL, 0, 16, '2020-05-12 22:29:22', 16, '2020-05-12 22:29:22', 0, 0);

-- ----------------------------
-- Table structure for crim_job_fair
-- ----------------------------
DROP TABLE IF EXISTS `crim_job_fair`;
CREATE TABLE `crim_job_fair`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '招聘会id',
  `year_level_id` int(11) NOT NULL COMMENT '年度id：type表year_level子节点id',
  `quarter` int(11) NOT NULL COMMENT '季度：0 秋季 1 春季',
  `job_fair_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '招聘会标题',
  `job_fair_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '招聘会内容',
  `start_time` date NOT NULL COMMENT '开始日期',
  `end_time` date NOT NULL COMMENT '结束日期',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) NOT NULL COMMENT '状态：0 保存 1 已发布 -1 已结束',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_job_fair
-- ----------------------------
INSERT INTO `crim_job_fair` VALUES (1, 8, 1, '2020春季招聘会', '请编辑活动内容...<p>阿萨斯擦擦阿擦擦是阿萨达</p><p>按</p><p>奥平时就怕假设</p>', '2020-05-13', '2020-06-13', 1, '2020-05-02 23:17:53', 1, '2020-05-02 23:17:53', 1, 0);
INSERT INTO `crim_job_fair` VALUES (4, 7, 0, '2019秋季招聘会', '<p>洗啊户撒a上课效率想按实际来了南京爱普生呀呀呀十八届就噢噢as【 啊asjo\'osa</p><p>阿萨</p><ol><li>asdac as&nbsp;</li><li>as擦</li><li>aaw素材</li></ol><p><br></p><h2 style=\"text-align: center;\"><span style=\"font-weight: bold;\">活泼可爱</span></h2>', '2019-09-16', '2019-09-20', 1, '2020-05-02 23:24:16', 1, '2020-05-02 23:24:16', -1, 0);
INSERT INTO `crim_job_fair` VALUES (5, 8, 1, '测试', '<p>活动内容</p><p>测试测试</p><p>测试测试&nbsp;</p><p>测试测试测试测试</p><p>测试测试</p><ol><li>测试测试测试测试</li><li>测试测试</li><li>测试测试测试测试&nbsp;</li></ol>', '2020-06-08', '2020-06-10', 1, '2020-06-05 21:11:04', 1, '2020-06-05 21:11:04', 1, 0);
INSERT INTO `crim_job_fair` VALUES (6, 8, 1, '测试2', '请编辑活动内容...<p><br></p>', '2020-06-10', '2020-06-13', 1, '2020-06-06 17:11:23', 1, '2020-06-06 17:11:23', 1, 0);

-- ----------------------------
-- Table structure for crim_job_fair_company
-- ----------------------------
DROP TABLE IF EXISTS `crim_job_fair_company`;
CREATE TABLE `crim_job_fair_company`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '招聘会/企业 关联表id',
  `job_fair_id` int(11) NOT NULL COMMENT '招聘会id',
  `company_id` int(11) NOT NULL COMMENT '企业id',
  `campus_id` int(11) NOT NULL COMMENT '校区id：type表campus子节点id',
  `site_id` int(11) DEFAULT NULL COMMENT '分配场地id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) NOT NULL COMMENT '状态：0 已分配场地 1 未分配场地',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_job_fair_company
-- ----------------------------
INSERT INTO `crim_job_fair_company` VALUES (1, 1, 2, 2, 1, 1, '2020-05-05 21:45:39', 1, '2020-05-05 21:45:39', 0, 1);
INSERT INTO `crim_job_fair_company` VALUES (2, 1, 2, 4, NULL, 1, '2020-05-05 21:46:39', 1, '2020-05-05 21:46:39', 1, 1);
INSERT INTO `crim_job_fair_company` VALUES (3, 1, 2, 2, NULL, 1, '2020-05-10 00:34:39', 1, '2020-05-10 00:34:39', 1, 1);
INSERT INTO `crim_job_fair_company` VALUES (4, 1, 4, 2, 1, 1, '2020-05-12 22:23:33', 1, '2020-05-23 15:51:25', 0, 0);

-- ----------------------------
-- Table structure for crim_job_vacancy
-- ----------------------------
DROP TABLE IF EXISTS `crim_job_vacancy`;
CREATE TABLE `crim_job_vacancy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '招聘职位id',
  `company_id` int(11) NOT NULL COMMENT '公司id',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位名称',
  `work_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作地点',
  `regular_pay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正式薪资，如：10-12K',
  `experience` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '经验要求',
  `certificate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学历要求',
  `skill` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技能要求，以英文逗号\",\"间隔',
  `job_desc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职位描述',
  `welfare` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '福利待遇',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_job_vacancy
-- ----------------------------
INSERT INTO `crim_job_vacancy` VALUES (1, 2, 'JAVA', '北京', '10K', '100年', '本科', 'springboot,spring cloud,三十六计,熟读孙子兵法', '巴啦啦&nbsp;啊啥就&nbsp;安排速度<br>时间冻结啊哈<br>&nbsp;奥苏东坡&nbsp;阿苏与', NULL, NULL, NULL, 4, '2020-05-05 14:14:36', NULL, 0);
INSERT INTO `crim_job_vacancy` VALUES (2, 2, 'UI设计师', '广州', '12K', '应届生', '本科', 'es6,大前端', '厉害就行，非诚勿扰', NULL, 4, '2020-05-05 14:15:49', 4, '2020-05-05 14:15:49', NULL, 0);
INSERT INTO `crim_job_vacancy` VALUES (3, 4, 'UI设计师', '广州', '10k', '应届生', '本科', 'java,springboot', '描述描述', NULL, 3, '2020-05-12 22:26:33', 3, '2020-05-28 00:09:14', NULL, 0);
INSERT INTO `crim_job_vacancy` VALUES (4, 4, 'Java', '天津', '12k', '1-3年', '本科', 'linux', 'qweqweqwe&nbsp;', NULL, 3, '2020-05-12 22:27:29', 3, '2020-05-28 00:09:22', NULL, 0);
INSERT INTO `crim_job_vacancy` VALUES (5, 4, '在线客服', '背景', '7K', '不限', '不限', 'Excel,Word,PPT', '线上人工客服', NULL, 3, '2020-05-28 00:27:19', 3, '2020-05-28 00:27:19', NULL, 0);
INSERT INTO `crim_job_vacancy` VALUES (6, 4, '电话客服', '天津', '7K', '不限', '不限', '普通话二甲及以上', '电话人工客服', NULL, 3, '2020-05-28 00:28:30', 3, '2020-05-28 00:28:30', NULL, 0);

-- ----------------------------
-- Table structure for crim_mount
-- ----------------------------
DROP TABLE IF EXISTS `crim_mount`;
CREATE TABLE `crim_mount`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '挂载记录id',
  `file_id` int(255) NOT NULL COMMENT '文件id',
  `target_type` int(255) NOT NULL COMMENT '挂载目标类型：0 招聘会 1 宣讲会',
  `target_id` int(11) NOT NULL COMMENT '挂载目标id',
  `create_user` int(255) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(255) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(255) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(255) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_mount
-- ----------------------------
INSERT INTO `crim_mount` VALUES (1, 2, 0, 1, NULL, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for crim_permission
-- ----------------------------
DROP TABLE IF EXISTS `crim_permission`;
CREATE TABLE `crim_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `perm_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `perm_name_zh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名称 中文',
  `perm_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限描述',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态',
  `is_deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_permission
-- ----------------------------
INSERT INTO `crim_permission` VALUES (1, 'add', '增加', NULL, NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `crim_permission` VALUES (2, 'delete', '删除', NULL, NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `crim_permission` VALUES (3, 'update', '修改', NULL, NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `crim_permission` VALUES (4, 'select', '查询', NULL, NULL, NULL, NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for crim_profession
-- ----------------------------
DROP TABLE IF EXISTS `crim_profession`;
CREATE TABLE `crim_profession`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业id',
  `department_id` int(11) NOT NULL COMMENT '院系id',
  `profession_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业名称',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_profession
-- ----------------------------
INSERT INTO `crim_profession` VALUES (1, 1, '计算机科学与技术', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (2, 1, '网络工程', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (3, 1, '物联网工程', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (4, 2, '信息管理与信息系统', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (5, 2, '市场营销', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (6, 3, '土木工程', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (7, 4, '财务会计教育', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (8, 4, '财务管理', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (9, 5, '教育技术学', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (10, 6, '美术学', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (11, 7, '生物科学', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_profession` VALUES (12, 8, '体育教育', 1, '2020-04-30 01:35:38', 1, '2020-04-30 01:35:38', NULL, 0);
INSERT INTO `crim_profession` VALUES (13, 10, '海洋资源与环境', 1, '2020-04-30 01:53:21', 1, '2020-04-30 01:53:21', NULL, 0);
INSERT INTO `crim_profession` VALUES (14, 10, '植物科学与技术', 1, '2020-04-30 01:54:00', 1, '2020-04-30 01:54:00', NULL, 0);
INSERT INTO `crim_profession` VALUES (15, 1, '应用统计学', 1, '2020-04-30 03:59:16', 1, '2020-04-30 03:59:16', NULL, 0);
INSERT INTO `crim_profession` VALUES (16, 10, '某专业', 1, '2020-06-06 17:09:33', 1, '2020-06-06 17:09:33', NULL, 0);

-- ----------------------------
-- Table structure for crim_profession_year_level
-- ----------------------------
DROP TABLE IF EXISTS `crim_profession_year_level`;
CREATE TABLE `crim_profession_year_level`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '专业/年级 关联表id',
  `profession_id` int(11) NOT NULL COMMENT '专业id',
  `year_level_id` int(11) NOT NULL COMMENT '年级id：type表year_level子节点id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 已删除 1 未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crim_project_experience
-- ----------------------------
DROP TABLE IF EXISTS `crim_project_experience`;
CREATE TABLE `crim_project_experience`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目经历id',
  `resume_id` int(11) NOT NULL COMMENT '简历id',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目名称',
  `project_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目角色',
  `project_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目链接',
  `project_start` date NOT NULL COMMENT '项目开始时间',
  `project_end` date NOT NULL COMMENT '项目结束时间',
  `project_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目描述',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_project_experience
-- ----------------------------
INSERT INTO `crim_project_experience` VALUES (1, 1, '七味都气丸大青蛙多', '阿萨德ad ', 'aasdasdasdasdasdasdasdasd', '2020-01-01', '2020-05-01', '啊实打实<br>as排队阿婆<br>安排速度盘', 16, '2020-05-08 00:22:57', 16, '2020-05-08 00:22:57', NULL, 0);
INSERT INTO `crim_project_experience` VALUES (2, 1, '恶趣味', ' 为去', '驱蚊器我', '2020-01-01', '2020-04-01', '请问让我去请问请问请问&nbsp;&nbsp;驱蚊器且请问&nbsp;请问请问请问我<br>阿斯噗噗', 16, '2020-05-08 00:24:55', 16, '2020-05-08 00:25:14', NULL, 1);

-- ----------------------------
-- Table structure for crim_qualification
-- ----------------------------
DROP TABLE IF EXISTS `crim_qualification`;
CREATE TABLE `crim_qualification`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资格证书id',
  `resume_id` int(11) NOT NULL COMMENT '简历id',
  `qualification_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资格证书名称',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_qualification
-- ----------------------------
INSERT INTO `crim_qualification` VALUES (1, 1, 'CET-4', 16, '2020-05-08 01:12:10', 16, '2020-05-08 01:12:10', NULL, 1);
INSERT INTO `crim_qualification` VALUES (2, 1, 'CET-6', 16, '2020-05-08 01:12:10', 16, '2020-05-08 01:12:10', NULL, 0);
INSERT INTO `crim_qualification` VALUES (3, 1, '航空母舰驾驶证', 16, '2020-05-08 01:14:04', 16, '2020-05-08 01:14:04', NULL, 0);

-- ----------------------------
-- Table structure for crim_resume
-- ----------------------------
DROP TABLE IF EXISTS `crim_resume`;
CREATE TABLE `crim_resume`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '简历id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '求职者姓名',
  `sex` int(11) NOT NULL COMMENT '性别：0 男 1 女',
  `birth` date NOT NULL COMMENT '出生年月',
  `class_level` int(11) NOT NULL COMMENT '届别，如：2020',
  `job_status` int(11) NOT NULL COMMENT '求职状态：\r\n0 离职-随时到岗 \r\n1 在职-暂不考虑 \r\n2 在职-考虑机会 \r\n3 在职-月内到岗 ',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `weixin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '微信号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱号',
  `photo_id` int(11) DEFAULT NULL COMMENT '证件照id',
  `advantage` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '个人优势',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_resume
-- ----------------------------
INSERT INTO `crim_resume` VALUES (1, 8, '叶蓁蓁', 0, '2000-01-01', 2020, 0, '17878787878', 'weChat', '', NULL, '本人性格开朗，对待事情有较强的积极性和责任性，在工作上多次受到上级管理人员的认可，当然最重要的是能力。由于开始从事财务支持方面的工作，在职期间经过几个阶段的系统学习，在实践中积累了一定的工作经验，希望在提高自已的同时能加盟到贵公司。诚诚恳恳、执着的敬业精神是我最大的优点和财富，期待与您的面谈!', 16, '2020-05-07 22:21:28', 16, '2020-05-27 23:48:50', NULL, 0);

-- ----------------------------
-- Table structure for crim_role
-- ----------------------------
DROP TABLE IF EXISTS `crim_role`;
CREATE TABLE `crim_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `pid` int(11) DEFAULT NULL COMMENT '父节点',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_name_zh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称 中文',
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_role
-- ----------------------------
INSERT INTO `crim_role` VALUES (1, NULL, 'school', '学校', '学校账户角色', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role` VALUES (2, NULL, 'student', '学生', '学生账户角色', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role` VALUES (3, NULL, 'company', '企业', '企业账户角色', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role` VALUES (4, 1, 'school-child', '学校子账户', '学校账户子角色', NULL, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for crim_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `crim_role_permission`;
CREATE TABLE `crim_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色/权限 关联表id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `perm_id` int(11) NOT NULL COMMENT '权限id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_role_permission
-- ----------------------------
INSERT INTO `crim_role_permission` VALUES (1, 1, 1, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (2, 1, 2, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (3, 1, 3, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (4, 1, 4, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (5, 2, 1, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (6, 2, 2, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (7, 2, 3, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (8, 2, 4, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (9, 3, 1, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (10, 3, 2, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (11, 3, 3, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (12, 3, 4, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (13, 4, 1, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (14, 4, 2, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (15, 4, 3, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (16, 4, 4, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_role_permission` VALUES (17, 3, 1, NULL, '2020-04-28 22:01:50', NULL, '2020-04-28 22:01:50', NULL, 1);
INSERT INTO `crim_role_permission` VALUES (18, 3, 2, NULL, '2020-04-28 22:01:51', NULL, '2020-04-28 22:01:51', NULL, 1);
INSERT INTO `crim_role_permission` VALUES (19, 3, 3, NULL, '2020-04-28 22:01:51', NULL, '2020-04-28 22:01:51', NULL, 1);
INSERT INTO `crim_role_permission` VALUES (20, 3, 4, NULL, '2020-04-28 22:01:51', NULL, '2020-04-28 22:01:51', NULL, 1);

-- ----------------------------
-- Table structure for crim_school
-- ----------------------------
DROP TABLE IF EXISTS `crim_school`;
CREATE TABLE `crim_school`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `school_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学校名称',
  `school_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学校地址',
  `school_postal_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学校邮政编码',
  `school_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '学校简介',
  `create_user` int(255) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(255) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(255) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(255) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_school
-- ----------------------------
INSERT INTO `crim_school` VALUES (1, 1, '河北科技师范学院', '秦皇岛', '066004', '<p>\n</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>河北科技师范学院是一所具有硕士学位授予权的省属普通高等学校，坐落于滨海旅游城市——秦皇岛市。学校始建于1941年，1975年开始举办高等教育，1977年开始招收本科生，2006年获得硕士学位授予权。学校是教育部首批全国重点建设职教师资培养培训基地、科技部国家级科技特派员创业培训基地、农业部现代农业技术培训基地、中国科协首批全国科普教育基地，是河北省首批转型发展试点院校、河北省创新创业教育示范高校。</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>学校现有秦皇岛、昌黎、开发区3个校区，占地面积95万平方米（1424亩），建筑面积41.5万平方米，教学科研仪器设备总值2.43亿元，馆藏图书文献180.9万册、电子图书255.3万册，中外文纸质期刊864种，各种类型数据库(含子库)<span>60个</span>。有国家认证的电子生物标本馆1个，附属职业中学1所。</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>学校设有研究生部、动物科技学院、农学与生物科技学院、园艺科技学院、海洋资源与环境学院、城市建设学院、化学工程学院、机电工程学院、</span><span>食品科技学院、工商管理学院、数学与信息科技学院、财经学院、外国语学院、文法学院、教育学院、艺术学院、物理系、体育与健康学院、思想政治理论教学部、继续教育学院等20个二级教学单位，现有全日制本专科生约2.1万余人、各类硕士研究生604人。现有在职教职工1548人，专任教师1070人，具有硕士以上学位的专任教师919人，其中具有博士学位的教师220人；具有高级专业技术职务的教师496人，其中教授160人；有“双师型”教师521人。拥有国务院政府特殊津贴专家、省管优秀专家、省政府特殊津贴专家、省有突出贡献中青年专家、河北省模范教师等省级以上各类人才90余人，教育部及河北省教学指导委员会成员31人。</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>学校设有74个本科专业，涵盖农、工、理、文、法、教育、经济、管理、艺术等9大学科门类。现有7个省级重点学科和重点发展学科，拥有生物学、园艺学、化学、食品科学与工程、畜牧学、教育学等6个学术学位硕士授权一级学科，17个二级学科的学术学位硕士授权点；具有农业硕士、教育硕士、兽医硕士、机械硕士、体育硕士等5种专业学位14个授权领域。建有1个国家级专业综合改革试点专业，11个省级一流本科专业，3个省级本科教育创新高地、6个省级品牌特色专业；有1门国家级精品课程、8门省级精品课程，4门省级精品在线开放课程，先后获2项国家级教学成果奖、43项省级教学成果奖，其中一等奖7项。获批教育部首批卓越农林人才教育培养计划改革试点。</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>学校建有3个省级重点实验室和5个省级技术创新中心、3个省级产业技术研究院、6个省级实验教学示范中心、1个省级工程研究中心、2个国家农产品加工技术研发分中心、15个市级重点实验室和工程技术研究中心；建有国家科技基础条件平台——家养动物种质资源平台；设有省级产业技术创新战略联盟、省高校应用技术研发中心、河北省职业教育研究基地、河北省非物质文化遗产研究基地、冀东文化研究中心和秦皇岛市农业科学研究院、秦皇岛市经济社会发展研究中心、秦皇岛瀚海国际化语言风景研究中心、秦皇岛市地方立法研究咨询基地等。</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>近 三年学校承担各级各类纵向科研项目670项，其中国家级项目13项、省部级项目274项；被SCI、EI收录210篇，出版学术著作75部；获外来科研经费总计5493.05万元；获省部级科研奖励17项，其中一等奖3项；获得植物新品种权1个，获得授权专利87项，其中发明专利42项；获得软件著作权34项，制定省市地方标准和企业标准18项。</span></p><p><span>学校坚持</span><span></span><span>政产学研深度融合，建立产学研示范基地22个。有3支科技服务团队被授予“河北省高校李保国式科技服务团队”、1人被授予“河北省李保国式科教扶贫突出贡献者”称号。</span></p><p><span><span>学校先后与20多个国外高校及科研单位建立了科技教育合作关系，与意大利佩鲁贾外国人大学合作建有意大利语语言培训与测试中心。多次获得“河北省教育文化国际交流与合作先进集体”“河北省引进国外智力工作先进单位”等称号。</span><span>设立中美、中芬121交换生双学位项目等，为我校师生提供更多的国外学习交流、实习实训、科研及工作机会。</span></span></p><p><span>近3年，学生在全国大学生“挑战杯”赛、人文素质竞赛、创新创业大赛和各类学科竞赛中获得省级以上奖项510项，其中国家级213项；毕业生就业率连续稳定在90%以上，平均考研（公务员、选调生）录取率16%以上。</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>毕业生中涌现出一大批优秀人才，包括全国劳动模范、全国五一劳动奖章获得者、国家科技进步奖获得者、全国优秀教育工作者、全国百名优秀县委书记、省长特别奖获得者、省突出贡献奖获得者等。</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>学校校训：敏学、修身、乐业、创新。</span></p><p><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>办学地址：</span></span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>秦皇岛校区—河北省秦皇岛市海港区河北大街西段360号；</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>昌黎校区—河北省昌黎县城关镇四街河东113号；</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>开发区校区—河北省秦皇岛经济技术开发区秦皇西大街106号。</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>联系电话：0335-8058347。</span></p><p><span>&nbsp;</span></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>（数据统计截至2019年12月31日）</span></p><p><span><br></span></p><p><span><br></span></p><p>\n\n<b></b><i></i><u></u><sub></sub><sup></sup><strike></strike><br></p>', 1, '2020-04-28 23:12:10', 1, '2020-05-12 16:07:33', NULL, 0);

-- ----------------------------
-- Table structure for crim_site
-- ----------------------------
DROP TABLE IF EXISTS `crim_site`;
CREATE TABLE `crim_site`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '场地id',
  `building_id` int(11) NOT NULL COMMENT '建筑id',
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '场地名称',
  `site_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '场地描述',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) NOT NULL COMMENT '状态：0 空闲 1 已占用',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_site
-- ----------------------------
INSERT INTO `crim_site` VALUES (1, 1, 'A103', '', 1, '2020-04-30 23:38:34', 1, '2020-05-23 15:51:25', 1, 0);
INSERT INTO `crim_site` VALUES (2, 1, 'A104', NULL, NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO `crim_site` VALUES (3, 1, 'A104', '', 1, '2020-05-02 22:21:49', 1, '2020-05-23 15:50:03', 0, 0);
INSERT INTO `crim_site` VALUES (4, 2, 'X107', NULL, 1, '2020-05-23 15:51:09', 1, '2020-05-23 15:51:25', 0, 1);
INSERT INTO `crim_site` VALUES (5, 2, 'X108', '', 1, '2020-05-23 15:53:18', 1, '2020-05-23 15:53:18', 0, 0);
INSERT INTO `crim_site` VALUES (6, 3, '513', '', 1, '2020-05-23 15:57:10', 1, '2020-05-23 15:57:10', 0, 0);
INSERT INTO `crim_site` VALUES (7, 4, '01号区位', '开发区北门升旗广场 01 号区位', 1, '2020-05-23 15:59:36', 1, '2020-05-23 15:59:36', 0, 0);
INSERT INTO `crim_site` VALUES (8, 4, '02号区位', '开发区升旗广场02号区位', 1, '2020-05-23 16:00:13', 1, '2020-05-23 16:00:13', 0, 0);

-- ----------------------------
-- Table structure for crim_star
-- ----------------------------
DROP TABLE IF EXISTS `crim_star`;
CREATE TABLE `crim_star`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关注id',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `company_id` int(11) NOT NULL COMMENT '企业id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crim_student
-- ----------------------------
DROP TABLE IF EXISTS `crim_student`;
CREATE TABLE `crim_student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `grade_class_id` int(11) NOT NULL COMMENT '班级id',
  `student_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生学号',
  `student_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `student_sex` int(11) NOT NULL COMMENT '学生性别：0 男 1 女',
  `student_birth` date NOT NULL COMMENT '学生出生年月',
  `student_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生手机号',
  `student_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生邮箱号',
  `allow_phone_login` int(11) DEFAULT NULL COMMENT '是否允许使用手机号登录：0 是 1 否',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_student
-- ----------------------------
INSERT INTO `crim_student` VALUES (1, 8, 3, '0913190101', '叶蓁蓁', 1, '2000-01-01', '18931342529', 'yezhen@163.com', 0, 1, '2020-05-04 00:36:01', 16, '2020-05-27 23:46:04', NULL, 0);
INSERT INTO `crim_student` VALUES (2, 9, 3, '0913190102', '景舜', 0, '2000-02-26', NULL, NULL, NULL, 1, '2020-05-04 00:36:01', 1, '2020-05-04 13:01:34', NULL, 0);
INSERT INTO `crim_student` VALUES (3, 10, 4, '0911160101', '君悦怿', 0, '1997-01-16', NULL, NULL, NULL, 1, '2020-05-04 13:19:02', 1, '2020-05-04 13:19:02', NULL, 0);

-- ----------------------------
-- Table structure for crim_type
-- ----------------------------
DROP TABLE IF EXISTS `crim_type`;
CREATE TABLE `crim_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `pid` int(11) DEFAULT NULL COMMENT '父节点id',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名称',
  `type_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型说明',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_type
-- ----------------------------
INSERT INTO `crim_type` VALUES (1, NULL, 'campus', '校区父节点', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_type` VALUES (2, 1, '秦皇岛校区', '校区二级节点', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_type` VALUES (3, 1, '开发区校区', '校区二级节点', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_type` VALUES (4, 1, '昌黎校区', '校区二级节点', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_type` VALUES (5, NULL, 'year_level', '年级父节点', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_type` VALUES (6, 5, '2018', '年级二级节点', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_type` VALUES (7, 5, '2019', '年级二级节点', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_type` VALUES (8, 5, '2020', '年级二级节点', NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_type` VALUES (9, 5, '2016', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_type` VALUES (10, 5, '2017', NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_type` VALUES (11, NULL, 'building', '建筑父节点', NULL, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for crim_user
-- ----------------------------
DROP TABLE IF EXISTS `crim_user`;
CREATE TABLE `crim_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名（工号/学号/手机号）',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密盐值',
  `type` int(11) NOT NULL COMMENT '用户类型：0 学校 1 学生 2 企业',
  `pid` int(11) DEFAULT NULL COMMENT '父账户id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) NOT NULL COMMENT '账户状态：0 正常 1 审核 2 禁用',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_user
-- ----------------------------
INSERT INTO `crim_user` VALUES (1, '13888888888', 'a4cca5f88bf96dc0a66ba22aa9081594', '4eca9446d90d32c57fe974829ec30d63', 0, NULL, NULL, '2020-04-26 23:13:02', 1, '2020-04-29 17:35:26', 0, 0);
INSERT INTO `crim_user` VALUES (2, '13999999999', '3dd192f23b99a7e1ab92b7685e606913', 'b82a070dbbe86fa5b55f2fa959f1cef3', 2, NULL, NULL, '2020-04-26 23:29:31', NULL, '2020-04-26 23:29:31', 1, 0);
INSERT INTO `crim_user` VALUES (3, '13777777777', 'b52e531028d0e3280a8dc287c0497b71', 'bf558e070ca15afc5337041626d6606d', 2, NULL, NULL, '2020-04-26 23:36:08', 1, '2020-05-12 17:16:52', 0, 0);
INSERT INTO `crim_user` VALUES (4, '13878657039', '72fc1500eec96e1d1bd7a3ef296d2827', '2665033fe7849c3e933eff03cecc28c3', 2, NULL, NULL, '2020-04-28 22:01:50', 1, '2020-05-05 13:43:04', 1, 1);
INSERT INTO `crim_user` VALUES (5, 'child-one', '33db06fa7d025b4072a27e912ccbbe99', 'ef185e089b31aa17b6e8ded32b517a8c', 0, 1, 1, '2020-04-29 15:30:56', 1, '2020-04-29 17:42:58', 0, 0);
INSERT INTO `crim_user` VALUES (6, 'child-tow', '687d1e2fc2a2148a0a8691dc3ef662e5', 'b3a3dc080d5d1987a77b8d5535bfef52', 0, 1, 1, '2020-04-29 15:34:59', 1, '2020-04-29 15:34:59', 0, 0);
INSERT INTO `crim_user` VALUES (7, 'child-three', '69c310c0b157fa84eb2e498565d0691e', '2a7fc772f2cde8fde2d3db3393ae6d8d', 0, 1, 1, '2020-04-29 15:35:21', 1, '2020-04-29 15:35:21', 0, 0);
INSERT INTO `crim_user` VALUES (8, '0913190101', '0488bfdb2f8ac068005dffb3e4fb0511', '52155b77c238328565e389fe14e1e42e', 1, NULL, 8, '2020-05-07 14:30:02', 16, '2020-05-07 18:14:22', 0, 0);
INSERT INTO `crim_user` VALUES (9, '0913190102', '7508e189f15f58420f8fc916331fde97', '2e6ded1de0d58d9a28d3d4331db66f01', 1, NULL, 1, '2020-05-04 00:36:01', 1, '2020-05-04 12:23:58', 0, 0);
INSERT INTO `crim_user` VALUES (10, '0911160101', '513ee453d525398482b6372072e8aa31', '6fb17506995bae02708c7c9270be49a3', 1, NULL, 1, '2020-05-04 13:19:02', 1, '2020-05-04 13:19:02', 0, 0);
INSERT INTO `crim_user` VALUES (11, '18931342529', '9a8f65435ed1dfc22054bae7bacd1431', '8c92d9e060450da81c5157beaa75cd8a', 1, 8, 8, '2020-05-07 14:27:44', 8, '2020-05-07 14:27:44', 0, 1);
INSERT INTO `crim_user` VALUES (12, '18931342529', 'a06ef233a9a65a0d40b9282782c3a479', '092410536ab4b8fc317dfdb5e6e76729', 1, 8, 8, '2020-05-07 14:30:02', 12, '2020-05-07 16:03:18', 0, 1);
INSERT INTO `crim_user` VALUES (13, '18931342529', 'a06ef233a9a65a0d40b9282782c3a479', '092410536ab4b8fc317dfdb5e6e76729', 1, 8, 8, '2020-05-07 16:05:52', 8, '2020-05-07 16:05:52', 0, 1);
INSERT INTO `crim_user` VALUES (14, '13878657039', '07ba7ad2beddae59437cd60acc786e27', '23cabdb84b473cc309919e120b2e3e0c', 1, 8, 8, '2020-05-07 16:14:06', 14, '2020-05-07 16:42:13', 0, 1);
INSERT INTO `crim_user` VALUES (15, '18931342529', '07ba7ad2beddae59437cd60acc786e27', '23cabdb84b473cc309919e120b2e3e0c', 1, 8, 8, '2020-05-07 17:36:33', 8, '2020-05-07 17:36:33', 0, 1);
INSERT INTO `crim_user` VALUES (16, '18931342529', '0488bfdb2f8ac068005dffb3e4fb0511', '52155b77c238328565e389fe14e1e42e', 1, 8, 8, '2020-05-07 17:38:14', 16, '2020-05-07 18:14:21', 0, 0);
INSERT INTO `crim_user` VALUES (17, '13878657039', '3e51dd485e371944c1b13f451b9f9de6', '4c7db381614c05da3f5a8aa6549e8bf0', 2, NULL, NULL, '2020-05-12 14:54:32', 1, '2020-06-06 14:48:08', 0, 0);
INSERT INTO `crim_user` VALUES (18, '123456', '0c4f9e9f24e3c08aecce5afd59bf686e', 'abaa5043feb69c31f7a1d44bcead8e55', 0, 1, 1, '2020-06-06 08:05:59', 1, '2020-06-06 08:05:59', 0, 1);
INSERT INTO `crim_user` VALUES (19, '123456', '2375c231fd5418d626d4c38e336b29af', '18f0ed66677098fcc8443e8dea99147f', 0, 1, 1, '2020-06-06 08:08:02', 1, '2020-06-06 08:08:02', 0, 0);
INSERT INTO `crim_user` VALUES (20, '18076308369', 'e7e9bb18c1d8fde7b4366c503e30b552', 'fbb3e72af31d446ea51c0fd9513c911c', 2, NULL, NULL, '2020-06-06 16:48:16', NULL, '2020-06-06 16:48:16', 1, 0);
INSERT INTO `crim_user` VALUES (21, '13788015869', '53410f01fbf964f4f7264007fe1e6b15', '192ad2049f8887e512a4f96ca7f03ec8', 2, NULL, NULL, '2020-06-06 17:06:47', NULL, '2020-06-06 17:06:47', 1, 0);

-- ----------------------------
-- Table structure for crim_user_role
-- ----------------------------
DROP TABLE IF EXISTS `crim_user_role`;
CREATE TABLE `crim_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户/角色 关联表id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_user_role
-- ----------------------------
INSERT INTO `crim_user_role` VALUES (1, 1, 1, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_user_role` VALUES (2, 2, 3, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_user_role` VALUES (3, 3, 3, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_user_role` VALUES (4, 4, 3, NULL, '2020-04-28 22:01:50', NULL, '2020-04-28 22:01:50', NULL, 0);
INSERT INTO `crim_user_role` VALUES (5, 5, 1, 1, '2020-04-29 15:30:56', 1, '2020-05-12 16:20:55', NULL, 0);
INSERT INTO `crim_user_role` VALUES (6, 6, 4, 1, '2020-04-29 15:34:59', 1, '2020-05-12 16:20:49', NULL, 0);
INSERT INTO `crim_user_role` VALUES (7, 7, 4, 1, '2020-04-29 15:35:21', 1, '2020-04-29 15:35:21', NULL, 0);
INSERT INTO `crim_user_role` VALUES (8, 8, 2, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_user_role` VALUES (9, 9, 2, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_user_role` VALUES (10, 11, 2, 8, '2020-05-07 14:27:44', 8, '2020-05-07 14:27:44', NULL, 1);
INSERT INTO `crim_user_role` VALUES (11, 12, 2, 8, '2020-05-07 14:30:02', 8, '2020-05-07 14:30:02', NULL, 1);
INSERT INTO `crim_user_role` VALUES (12, 13, 2, 8, '2020-05-07 16:05:52', 8, '2020-05-07 16:05:52', NULL, 1);
INSERT INTO `crim_user_role` VALUES (13, 14, 2, 8, '2020-05-07 16:14:06', 8, '2020-05-07 16:14:06', NULL, 0);
INSERT INTO `crim_user_role` VALUES (14, 15, 2, 8, '2020-05-07 17:36:33', 8, '2020-05-07 17:36:33', NULL, 1);
INSERT INTO `crim_user_role` VALUES (15, 16, 2, 8, '2020-05-07 17:38:14', 8, '2020-05-07 17:38:14', NULL, 0);
INSERT INTO `crim_user_role` VALUES (16, 17, 3, NULL, '2020-05-12 14:54:32', NULL, '2020-05-12 14:54:32', NULL, 0);
INSERT INTO `crim_user_role` VALUES (17, 18, 4, 1, '2020-06-06 08:05:59', 1, '2020-06-06 08:05:59', NULL, 0);
INSERT INTO `crim_user_role` VALUES (18, 19, 4, 1, '2020-06-06 08:08:02', 1, '2020-06-06 08:08:02', NULL, 0);
INSERT INTO `crim_user_role` VALUES (19, 20, 3, NULL, '2020-06-06 16:48:16', NULL, '2020-06-06 16:48:16', NULL, 0);
INSERT INTO `crim_user_role` VALUES (20, 21, 3, NULL, '2020-06-06 17:06:47', NULL, '2020-06-06 17:06:47', NULL, 0);

-- ----------------------------
-- Table structure for crim_volunteer_experience
-- ----------------------------
DROP TABLE IF EXISTS `crim_volunteer_experience`;
CREATE TABLE `crim_volunteer_experience`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '志愿服务经历id',
  `resume_id` int(11) NOT NULL COMMENT '简历id',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '志愿服务项目名称',
  `service_duration` int(11) NOT NULL COMMENT '志愿服务总时长',
  `project_start` date NOT NULL COMMENT '志愿服务项目开始日期',
  `project_end` date NOT NULL COMMENT '志愿服务项目结束日期',
  `project_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '志愿服务项目描述',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_volunteer_experience
-- ----------------------------
INSERT INTO `crim_volunteer_experience` VALUES (1, 1, '对方水电费是', 345, '2020-02-01', '2021-03-01', '撒大声地&nbsp;视频课<br>a】手动&nbsp;<br>a【】手动', 16, '2020-05-08 00:53:53', 16, '2020-05-08 00:58:06', NULL, 0);
INSERT INTO `crim_volunteer_experience` VALUES (2, 1, '为当前无多群无', 666, '2020-03-01', '2021-11-01', '就阿萨德刚旧爱说得好<br>啊【破损嗲商品&nbsp;<br>啊【0四&nbsp;【', 16, '2020-05-08 00:54:59', 16, '2020-05-08 00:58:03', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
