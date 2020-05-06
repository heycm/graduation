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

 Date: 07/05/2020 00:00:34
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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_audit_record
-- ----------------------------
INSERT INTO `crim_audit_record` VALUES (1, 0, NULL, NULL, 4, '2020-05-04 22:55:12', 0, '欢迎哟', 1, '2020-05-04 23:25:26', 4, '2020-05-04 22:55:12', 1, '2020-05-04 23:25:26', 0, 0);
INSERT INTO `crim_audit_record` VALUES (2, 0, NULL, NULL, 4, '2020-05-05 13:27:57', 0, '欢迎哟', 1, '2020-05-05 13:43:04', 4, '2020-05-05 13:27:57', 1, '2020-05-05 13:43:04', 0, 0);
INSERT INTO `crim_audit_record` VALUES (3, 1, 1, NULL, 4, '2020-05-05 17:31:03', 2, NULL, NULL, NULL, 4, '2020-05-05 17:31:03', 4, '2020-05-05 17:31:03', 0, 1);
INSERT INTO `crim_audit_record` VALUES (4, 1, 1, NULL, 4, '2020-05-05 17:38:19', 2, NULL, NULL, NULL, 4, '2020-05-05 17:35:41', 4, '2020-05-05 17:38:19', 0, 1);
INSERT INTO `crim_audit_record` VALUES (5, 1, 1, NULL, 4, '2020-05-05 20:58:22', 2, NULL, NULL, NULL, 4, '2020-05-05 20:58:22', 4, '2020-05-05 20:58:22', 0, 1);
INSERT INTO `crim_audit_record` VALUES (6, 1, 1, 2, 4, '2020-05-05 21:00:20', 0, 'ok', 1, '2020-05-05 21:45:39', 4, '2020-05-05 21:00:20', 1, '2020-05-05 21:45:39', 0, 0);
INSERT INTO `crim_audit_record` VALUES (7, 1, 1, 4, 4, '2020-05-05 21:10:11', 0, '还行', 1, '2020-05-05 21:46:39', 4, '2020-05-05 21:10:11', 1, '2020-05-05 21:46:39', 0, 0);
INSERT INTO `crim_audit_record` VALUES (8, 1, 1, 3, 4, '2020-05-05 21:10:21', 1, ' 不能太贪心哦', 1, '2020-05-05 22:00:06', 4, '2020-05-05 21:10:21', 1, '2020-05-05 22:00:06', 0, 0);
INSERT INTO `crim_audit_record` VALUES (9, 1, 1, 3, 4, '2020-05-05 22:02:00', NULL, NULL, NULL, NULL, 4, '2020-05-05 22:02:00', 4, '2020-05-05 22:02:00', 1, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_building
-- ----------------------------
INSERT INTO `crim_building` VALUES (1, 2, '逸夫楼', '主楼', 1, '2020-04-30 23:35:20', 1, '2020-04-30 23:35:20', NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_career_talk
-- ----------------------------
INSERT INTO `crim_career_talk` VALUES (1, 1, '赤壁之战', '2020-05-13 09:00:00', '2020-05-13 11:00:00', '同学们请带上简历哟', 4, '2020-05-06 16:15:17', 4, '2020-05-06 18:25:15', NULL, 0);
INSERT INTO `crim_career_talk` VALUES (2, 2, '啊实打实', '2020-05-13 09:00:00', '2020-05-13 10:30:00', NULL, 4, '2020-05-06 17:24:24', 4, '2020-05-06 17:24:24', NULL, 1);
INSERT INTO `crim_career_talk` VALUES (3, 1, '赤壁之战', '2020-05-14 09:00:00', '2020-05-14 09:15:00', '啊实打实<br>as90du', 4, '2020-05-06 17:41:39', 4, '2020-05-06 17:41:39', NULL, 1);
INSERT INTO `crim_career_talk` VALUES (4, 2, '打完球', '2020-05-13 09:00:00', '2020-05-13 11:30:00', NULL, 4, '2020-05-06 18:15:16', 4, '2020-05-06 18:24:23', NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_career_talk_student
-- ----------------------------
INSERT INTO `crim_career_talk_student` VALUES (1, 1, 1, NULL, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for crim_chat
-- ----------------------------
DROP TABLE IF EXISTS `crim_chat`;
CREATE TABLE `crim_chat`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `type` int(11) DEFAULT NULL COMMENT '消息类型：0 文本 1 文件',
  `from` int(11) DEFAULT NULL COMMENT '发送者id',
  `to` int(11) DEFAULT NULL COMMENT '接受者id',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息内容（文本内容/文件链接）',
  `err_code` int(11) DEFAULT NULL COMMENT '错误代码',
  `err_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '错误描述',
  `send_time` datetime(0) DEFAULT NULL COMMENT '发送时间',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '消息状态：0 已发送 1 未发送',
  `is_deleted` int(11) DEFAULT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_company
-- ----------------------------
INSERT INTO `crim_company` VALUES (1, 4, '中国移动', NULL, NULL, NULL, '张xx', '5000000.00万元人民币', '2020-05-04', '北京市', '在营', '上市股份有限公司', '北京市', '10000000000XXXXXXXXXX', NULL, NULL, 4, '2020-05-04 21:14:07', 4, '2020-05-04 21:14:07', NULL, 1);
INSERT INTO `crim_company` VALUES (2, 4, '中国移动', NULL, '张默默', '13888888888', '张xx', '5000000.00万元人民币', '2020-05-04', '北京市', '在营', '上市股份有限公司', '北京市', '10000000000XXXXXXXXXX', '经营范围<br>经营范围<br>经营范围<br>经营范围经营范围经营范围<br>经营范围<br>', '简介简介简介简介&nbsp;&nbsp;简介简介1234<br><br>bbq', 4, '2020-05-04 21:14:54', 1, '2020-05-05 13:43:04', 0, 0);

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
  `remuse_id` int(11) NOT NULL COMMENT '简历id',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crim_expected_position
-- ----------------------------
DROP TABLE IF EXISTS `crim_expected_position`;
CREATE TABLE `crim_expected_position`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '期望职位id',
  `resume_id` int(11) DEFAULT NULL COMMENT '简历id',
  `position_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '期望职位，如：UI设计师...',
  `expected_pay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '期望薪资，如：10-12K',
  `expected_trade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '期望行业，如：金融/互联网/制造业...',
  `expected_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '期望城市，如：北京/广州/武汉...',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `is_deleted` int(11) DEFAULT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `file_type` int(11) NOT NULL COMMENT '文件类型：0 头像/logo 1 营业执照 2 其他文件',
  `file_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件地址',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人id',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态：0 挂载 1 空闲',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除：0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_file
-- ----------------------------
INSERT INTO `crim_file` VALUES (1, 1, '捕获', '69821e7f-f70f-4f7d-abb9-6e629d37a693.PNG', 'PNG', 34739, 0, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/69821e7f-f70f-4f7d-abb9-6e629d37a693.PNG', 1, '2020-04-29 02:20:16', 1, '2020-05-03 12:47:34', NULL, 0);
INSERT INTO `crim_file` VALUES (2, 1, '404', '521b82b2-5790-457d-b3a8-dfa8c7287f19.gif', 'gif', 19877, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/521b82b2-5790-457d-b3a8-dfa8c7287f19.gif?Expires=1896082201&OSSAccessKeyId=LTAI4Fhi546AL87pHNV48eG7&Signature=DA3krE8ozIbADlBhnvPvwOWxb3g%3D', 1, '2020-05-03 17:30:03', 1, '2020-05-03 17:30:03', 0, 0);
INSERT INTO `crim_file` VALUES (3, 1, '2020显卡天梯', '902f47c3-75ed-480d-9059-cd40bb276268.jpg', 'jpg', 402861, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/902f47c3-75ed-480d-9059-cd40bb276268.jpg?Expires=1896082202&OSSAccessKeyId=LTAI4Fhi546AL87pHNV48eG7&Signature=KUzJrhmh%2BL6PegemtDFX7cvcZWM%3D', 1, '2020-05-03 17:30:03', 1, '2020-05-03 17:30:03', 1, 1);
INSERT INTO `crim_file` VALUES (4, 1, '0913160406何宇 - 综测', 'c657bfd1-2a91-45c2-966d-5ebda7a3e1d9.xls', 'xls', 43008, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/c657bfd1-2a91-45c2-966d-5ebda7a3e1d9.xls?Expires=1896082202&OSSAccessKeyId=LTAI4Fhi546AL87pHNV48eG7&Signature=j62nzKpugrVu1fqkkQhREk2wnfc%3D', 1, '2020-05-03 17:30:03', 1, '2020-05-03 17:30:03', 1, 1);
INSERT INTO `crim_file` VALUES (5, 1, 'JavaEE_API ', '00998ce2-f3c0-4d38-9676-683610fb2857.chm', 'chm', 6930265, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/00998ce2-f3c0-4d38-9676-683610fb2857.chm?Expires=1896082202&OSSAccessKeyId=LTAI4Fhi546AL87pHNV48eG7&Signature=E40dvRZWC07bCaQjm901VPpx62s%3D', 1, '2020-05-03 17:30:03', 1, '2020-05-03 17:30:03', 1, 1);
INSERT INTO `crim_file` VALUES (6, 1, 'JavaEE_API ', 'f9b7709a-ed05-4faa-903a-cf17cdf524f6.chw', 'chw', 1223248, 2, 'http://crim-file.oss-cn-beijing.aliyuncs.com/files/f9b7709a-ed05-4faa-903a-cf17cdf524f6.chw?Expires=1896082203&OSSAccessKeyId=LTAI4Fhi546AL87pHNV48eG7&Signature=PWrr%2BVYI%2BIbZzaaz3MRRWEYRc4k%3D', 1, '2020-05-03 17:30:03', 1, '2020-05-03 17:30:03', 1, 1);
INSERT INTO `crim_file` VALUES (7, 4, '捕获', '4b0cf7c5-4928-4fef-bf2d-178cd56d3527.PNG', 'PNG', 33, 0, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/4b0cf7c5-4928-4fef-bf2d-178cd56d3527.PNG', 4, '2020-05-04 21:45:16', 4, '2020-05-06 23:12:12', NULL, 0);
INSERT INTO `crim_file` VALUES (8, 4, '营业执照', 'f9f61631-f7f4-44fe-bd08-5c627342e18e.jpeg', 'jpeg', 30, 1, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/f9f61631-f7f4-44fe-bd08-5c627342e18e.jpeg', 4, '2020-05-04 21:58:21', 4, '2020-05-04 22:01:17', NULL, 1);
INSERT INTO `crim_file` VALUES (9, 4, '营业执照', '457dd864-f00a-4c06-9e8c-ab7c41cf936b.jpeg', 'jpeg', 30, 1, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/457dd864-f00a-4c06-9e8c-ab7c41cf936b.jpeg', 4, '2020-05-04 21:58:35', 4, '2020-05-04 21:58:35', NULL, 1);
INSERT INTO `crim_file` VALUES (10, 4, '营业执照', '52be41b7-e7cc-4c75-82ad-b0e084937af3.jpeg', 'jpeg', 30, 1, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/52be41b7-e7cc-4c75-82ad-b0e084937af3.jpeg', 4, '2020-05-04 22:00:29', 4, '2020-05-04 22:00:29', NULL, 1);
INSERT INTO `crim_file` VALUES (11, 4, '营业执照', '12f69aff-98d1-4084-924b-21181ec8d701.jpeg', 'jpeg', 30, 1, 'https://crim-img.oss-cn-beijing.aliyuncs.com/images/12f69aff-98d1-4084-924b-21181ec8d701.jpeg', 4, '2020-05-04 22:55:09', 4, '2020-05-04 22:55:09', NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crim_interview
-- ----------------------------
DROP TABLE IF EXISTS `crim_interview`;
CREATE TABLE `crim_interview`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '面试记录id',
  `company_id` int(11) NOT NULL COMMENT '企业id',
  `career_talk_id` int(11) NOT NULL COMMENT '宣讲会id',
  `job_vacancy_id` int(11) NOT NULL COMMENT '职位id',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `regular_pay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最终薪资：如 10-12K',
  `welfare` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最终福利',
  `is_last_choice` int(11) NOT NULL COMMENT '是否最终选择：0 否 1 是',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_user` int(11) DEFAULT NULL COMMENT '最新修改人',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '最新修改时间',
  `status` int(11) NOT NULL COMMENT '状态：0 未开始 1 待回复 2 已通过 3 被拒绝',
  `is_deleted` int(11) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_job_fair
-- ----------------------------
INSERT INTO `crim_job_fair` VALUES (1, 8, 0, 'as阿萨大师', '请编辑活动内容...<p>阿萨斯擦擦阿擦擦是阿萨达</p><p>按</p><p>奥平时就怕假设</p>', '2020-05-13', '2020-06-13', 1, '2020-05-02 23:17:53', 1, '2020-05-02 23:17:53', 1, 0);
INSERT INTO `crim_job_fair` VALUES (4, 8, 0, '阿萨达', '<p>洗啊户撒a上课效率想按实际来了南京爱普生呀呀呀十八届就噢噢as【 啊asjo\'osa</p><p>阿萨</p><ol><li>asdac as&nbsp;</li><li>as擦</li><li>aaw素材</li></ol><p><br></p><h2 style=\"text-align: center;\"><span style=\"font-weight: bold;\">活泼可爱</span></h2>', '2020-05-05', '2020-06-24', 1, '2020-05-02 23:24:16', 1, '2020-05-02 23:24:16', 1, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_job_fair_company
-- ----------------------------
INSERT INTO `crim_job_fair_company` VALUES (1, 1, 2, 2, 1, 1, '2020-05-05 21:45:39', 1, '2020-05-05 21:45:39', 0, 0);
INSERT INTO `crim_job_fair_company` VALUES (2, 1, 2, 4, NULL, 1, '2020-05-05 21:46:39', 1, '2020-05-05 21:46:39', 1, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_job_vacancy
-- ----------------------------
INSERT INTO `crim_job_vacancy` VALUES (1, 2, 'JAVA', '北京', '10K', '100年', '本科', 'springboot,spring cloud,三十六计,熟读孙子兵法', '巴啦啦&nbsp;啊啥就&nbsp;安排速度<br>时间冻结啊哈<br>&nbsp;奥苏东坡&nbsp;阿苏与', NULL, NULL, NULL, 4, '2020-05-05 14:14:36', NULL, 0);
INSERT INTO `crim_job_vacancy` VALUES (2, 2, 'UI设计师', '广州', '12K', '应届生', '本科', 'es6,大前端', '厉害就行，非诚勿扰', NULL, 4, '2020-05-05 14:15:49', 4, '2020-05-05 14:15:49', NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for crim_resume
-- ----------------------------
DROP TABLE IF EXISTS `crim_resume`;
CREATE TABLE `crim_resume`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '简历id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '求职者姓名',
  `sex` int(11) NOT NULL COMMENT '性别：0 男 1 女',
  `brith` date NOT NULL COMMENT '出生年月',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `crim_school` VALUES (1, 1, '河北科技师范学院', '秦皇岛', '066004', '请输入简介...<p>asjdk</p><p>奥斯哦</p><p>阿伟【0&nbsp;</p><p><br></p><p><br></p><p><br></p><h1><span style=\"background-color: rgb(194, 79, 74);\">AAAAAAAAAAAAAAAAAA<span style=\"font-weight: normal;\"></span></span></h1><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p>', 1, '2020-04-28 23:12:10', 1, '2020-04-29 00:12:37', NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_site
-- ----------------------------
INSERT INTO `crim_site` VALUES (1, 1, 'A103', '', 1, '2020-04-30 23:38:34', 1, '2020-04-30 23:38:34', 1, 0);
INSERT INTO `crim_site` VALUES (2, 1, 'A104', NULL, NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO `crim_site` VALUES (3, 1, 'A104', '', 1, '2020-05-02 22:21:49', 1, '2020-05-02 22:21:49', 0, 0);

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
INSERT INTO `crim_student` VALUES (1, 8, 3, '0913190101', '张三', 0, '2000-01-01', NULL, NULL, NULL, 1, '2020-05-04 00:36:01', 1, '2020-05-04 00:36:01', NULL, 0);
INSERT INTO `crim_student` VALUES (2, 9, 3, '0913190102', '李四', 1, '2000-02-26', NULL, NULL, NULL, 1, '2020-05-04 00:36:01', 1, '2020-05-04 13:01:34', NULL, 0);
INSERT INTO `crim_student` VALUES (3, 10, 4, '0911160101', '张辽', 0, '1997-01-16', NULL, NULL, NULL, 1, '2020-05-04 13:19:02', 1, '2020-05-04 13:19:02', NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_user
-- ----------------------------
INSERT INTO `crim_user` VALUES (1, '13888888888', 'a4cca5f88bf96dc0a66ba22aa9081594', '4eca9446d90d32c57fe974829ec30d63', 0, NULL, NULL, '2020-04-26 23:13:02', 1, '2020-04-29 17:35:26', 0, 0);
INSERT INTO `crim_user` VALUES (2, '13999999999', '3dd192f23b99a7e1ab92b7685e606913', 'b82a070dbbe86fa5b55f2fa959f1cef3', 2, NULL, NULL, '2020-04-26 23:29:31', NULL, '2020-04-26 23:29:31', 1, 0);
INSERT INTO `crim_user` VALUES (3, '13777777777', 'b52e531028d0e3280a8dc287c0497b71', 'bf558e070ca15afc5337041626d6606d', 2, NULL, NULL, '2020-04-26 23:36:08', NULL, '2020-04-26 23:36:08', 1, 0);
INSERT INTO `crim_user` VALUES (4, '13878657039', '72fc1500eec96e1d1bd7a3ef296d2827', '2665033fe7849c3e933eff03cecc28c3', 2, NULL, NULL, '2020-04-28 22:01:50', 1, '2020-05-05 13:43:04', 0, 0);
INSERT INTO `crim_user` VALUES (5, 'child-one', '33db06fa7d025b4072a27e912ccbbe99', 'ef185e089b31aa17b6e8ded32b517a8c', 0, 1, 1, '2020-04-29 15:30:56', 1, '2020-04-29 17:42:58', 0, 0);
INSERT INTO `crim_user` VALUES (6, 'child-tow', '687d1e2fc2a2148a0a8691dc3ef662e5', 'b3a3dc080d5d1987a77b8d5535bfef52', 0, 1, 1, '2020-04-29 15:34:59', 1, '2020-04-29 15:34:59', 0, 0);
INSERT INTO `crim_user` VALUES (7, 'child-three', '69c310c0b157fa84eb2e498565d0691e', '2a7fc772f2cde8fde2d3db3393ae6d8d', 0, 1, 1, '2020-04-29 15:35:21', 1, '2020-04-29 15:35:21', 0, 0);
INSERT INTO `crim_user` VALUES (8, '0913190101', '9a8f65435ed1dfc22054bae7bacd1431', '8c92d9e060450da81c5157beaa75cd8a', 1, NULL, 1, '2020-05-04 00:36:01', 1, '2020-05-04 12:28:21', 0, 0);
INSERT INTO `crim_user` VALUES (9, '0913190102', '7508e189f15f58420f8fc916331fde97', '2e6ded1de0d58d9a28d3d4331db66f01', 1, NULL, 1, '2020-05-04 00:36:01', 1, '2020-05-04 12:23:58', 0, 0);
INSERT INTO `crim_user` VALUES (10, '0911160101', '513ee453d525398482b6372072e8aa31', '6fb17506995bae02708c7c9270be49a3', 1, NULL, 1, '2020-05-04 13:19:02', 1, '2020-05-04 13:19:02', 0, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crim_user_role
-- ----------------------------
INSERT INTO `crim_user_role` VALUES (1, 1, 1, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_user_role` VALUES (2, 2, 3, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_user_role` VALUES (3, 3, 3, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_user_role` VALUES (4, 4, 3, NULL, '2020-04-28 22:01:50', NULL, '2020-04-28 22:01:50', NULL, 0);
INSERT INTO `crim_user_role` VALUES (5, 5, 4, 1, '2020-04-29 15:30:56', 1, '2020-04-29 15:30:56', NULL, 0);
INSERT INTO `crim_user_role` VALUES (6, 6, 4, 1, '2020-04-29 15:34:59', 1, '2020-04-29 15:34:59', NULL, 0);
INSERT INTO `crim_user_role` VALUES (7, 7, 4, 1, '2020-04-29 15:35:21', 1, '2020-04-29 15:35:21', NULL, 0);
INSERT INTO `crim_user_role` VALUES (8, 8, 2, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `crim_user_role` VALUES (9, 9, 2, NULL, NULL, NULL, NULL, NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
