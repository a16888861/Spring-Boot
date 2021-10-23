/*
 Navicat Premium Data Transfer

 Source Server         : MySQL-5.7.5(本地Docker)
 Source Server Type    : MySQL
 Source Server Version : 50705
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50705
 File Encoding         : 65001

 Date: 23/10/2021 23:13:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_role
-- ----------------------------
DROP TABLE IF EXISTS `blog_role`;
CREATE TABLE `blog_role` (
                             `id` varchar(32) NOT NULL COMMENT '标识符',
                             `code` varchar(6) NOT NULL COMMENT '角色代码',
                             `name` varchar(50) NOT NULL COMMENT '角色名称',
                             `en_name` varchar(50) NOT NULL COMMENT '角色英文名称',
                             `type` varchar(10) NOT NULL COMMENT '角色类型',
                             `status` varchar(10) NOT NULL COMMENT '状态',
                             `create_by` varchar(32) NOT NULL COMMENT '创建人',
                             `create_date` datetime NOT NULL COMMENT '创建时间',
                             `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
                             `update_date` datetime DEFAULT NULL COMMENT '修改时间',
                             `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
                             `del_flag` char(1) NOT NULL COMMENT '删除标志(0正常1删除)',
                             PRIMARY KEY (`id`) USING BTREE,
                             KEY `idx_0` (`status`,`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='系统-角色表';

-- ----------------------------
-- Records of blog_role
-- ----------------------------
BEGIN;
INSERT INTO `blog_role` VALUES ('1431876295237054464', '10000', '超级管理员', 'superAdmin', '1', '0', '1430109634181881856', '2021-08-29 15:08:06', NULL, NULL, '', '0');
INSERT INTO `blog_role` VALUES ('1437679670266036224', '10001', '管理员', 'admin', '1', '0', '1430109634181881856', '2021-09-14 15:28:39', NULL, NULL, '', '0');
INSERT INTO `blog_role` VALUES ('1437680664660017152', '10002', '注册用户', 'registerUser', '1', '0', '1430109634181881856', '2021-09-14 15:32:36', NULL, NULL, '', '0');
COMMIT;

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
                             `id` varchar(32) NOT NULL COMMENT '标识符',
                             `name` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
                             `password` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '密码',
                             `mail` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '邮箱',
                             `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
                             `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
                             `year` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '年份',
                             `status` char(1) CHARACTER SET utf8 NOT NULL COMMENT '用户状态(0正常1锁定2审批)',
                             `create_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
                             `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
                             `update_date` datetime DEFAULT NULL COMMENT '修改时间',
                             `del_flag` char(1) CHARACTER SET utf8 NOT NULL COMMENT '删除状态(0正常1删除)',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统-用户表';

-- ----------------------------
-- Records of blog_user
-- ----------------------------
BEGIN;
INSERT INTO `blog_user` VALUES ('1430109634181881856', '超级管理员', '°c2◊20a620&@582a@yÒ6!y2ya!ay$fÒ$', 'admin@mail.com', '18888888888', '1431876295237054464', '2021', '0', '', '2021-10-14 16:50:13', NULL, NULL, '0');
INSERT INTO `blog_user` VALUES ('1448571832049061888', '张泱森', '°c2◊20a620&@582a@yÒ6!y2ya!ay$fÒ$', '2271998412@qq.com', '18234125116', '1437680664660017152', '2021', '0', '123456789', '2021-10-14 16:50:13', NULL, NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_file_upload
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_upload`;
CREATE TABLE `sys_file_upload` (
                                   `id` varchar(32) NOT NULL COMMENT '标识符',
                                   `original_name` varchar(100) DEFAULT NULL COMMENT '文件原始名称',
                                   `file_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '文件名称',
                                   `file_size` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '文件大小',
                                   `file_suffix` varchar(5) CHARACTER SET utf8 DEFAULT NULL COMMENT '文件后缀',
                                   `file_type` varchar(5) DEFAULT NULL COMMENT '文件类型',
                                   `file_location` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '文件相对位置',
                                   `file_full_address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '文件完整位置',
                                   `ip` varchar(15) CHARACTER SET utf8 DEFAULT NULL COMMENT '上传者ip地址',
                                   `file_remark` varchar(15) CHARACTER SET utf8 DEFAULT NULL COMMENT '文件说明(备注)',
                                   `create_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
                                   `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                                   `update_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改人',
                                   `update_date` datetime DEFAULT NULL COMMENT '修改时间',
                                   `del_flag` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除状态(0正常1删除)',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统-文件上传';

-- ----------------------------
-- Records of sys_file_upload
-- ----------------------------
BEGIN;
INSERT INTO `sys_file_upload` VALUES ('1451895335561617408', 'blog.sql', '20211023205637557759.sql', '6776', 'sql', 'other', '/Volumes/Application/Download/upload/', '/Volumes/Application/Download/upload/20211023205637557759.sql', '0:0:0:0:0:0:0:1', NULL, '1430109634181881856', '2021-10-23 20:56:38', NULL, NULL, '0');
INSERT INTO `sys_file_upload` VALUES ('1451895340473147392', 'blog.sql', '20211023205638735784.sql', '6776', 'sql', 'other', '/Volumes/Application/Download/upload/', '/Volumes/Application/Download/upload/20211023205638735784.sql', '0:0:0:0:0:0:0:1', NULL, '1430109634181881856', '2021-10-23 20:56:39', NULL, NULL, '0');
INSERT INTO `sys_file_upload` VALUES ('1451895457552949248', '004.jpeg', '20211023205706649433.jpeg', '92315', 'jpeg', 'pic', '/Volumes/Application/Download/upload/', '/Volumes/Application/Download/upload/20211023205706649433.jpeg', '0:0:0:0:0:0:0:1', NULL, '1430109634181881856', '2021-10-23 20:57:07', NULL, NULL, '0');
INSERT INTO `sys_file_upload` VALUES ('1451895461755641856', '004.jpeg', '20211023205707651571.jpeg', '92315', 'jpeg', 'pic', '/Volumes/Application/Download/upload/', '/Volumes/Application/Download/upload/20211023205707651571.jpeg', '0:0:0:0:0:0:0:1', NULL, '1430109634181881856', '2021-10-23 20:57:08', NULL, NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
                           `id` varchar(32) NOT NULL COMMENT '标识符',
                           `ip` varchar(15) CHARACTER SET utf8 DEFAULT NULL COMMENT 'ip地址',
                           `module_name` varchar(50) DEFAULT NULL COMMENT '模块名',
                           `method` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作方法',
                           `operation` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作描述',
                           `take_up_time` mediumtext CHARACTER SET utf8 COMMENT '耗时ms',
                           `params` varchar(1000) CHARACTER SET utf8 DEFAULT NULL COMMENT '参数',
                           `year` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '年份',
                           `create_by` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作者',
                           `create_date` datetime DEFAULT NULL COMMENT '操作日期',
                           PRIMARY KEY (`id`) USING BTREE,
                           KEY `create_date_index` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统-日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES ('1451895336061902849', '0:0:0:0:0:0:0:1', 'controller', 'com.kali.blog.controller.file.SysFileUploadController.uploadFile()', '公共上传文件', '132', ' file:\"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@26831a6b\" filePath:\"/Volumes/Application/Download/upload/\"', '2021', '1430109634181881856', '2021-10-23 20:56:38');
INSERT INTO `sys_log` VALUES ('1451895340499476482', '0:0:0:0:0:0:0:1', 'controller', 'com.kali.blog.controller.file.SysFileUploadController.uploadFile()', '公共上传文件', '7', ' file:\"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@4823648d\" filePath:\"/Volumes/Application/Download/upload/\"', '2021', '1430109634181881856', '2021-10-23 20:56:39');
INSERT INTO `sys_log` VALUES ('1451895457579278338', '0:0:0:0:0:0:0:1', 'controller', 'com.kali.blog.controller.file.SysFileUploadController.uploadFile()', '公共上传文件', '7', ' file:\"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@31d2bcf2\" filePath:\"/Volumes/Application/Download/upload/\"', '2021', '1430109634181881856', '2021-10-23 20:57:07');
INSERT INTO `sys_log` VALUES ('1451895461790359554', '0:0:0:0:0:0:0:1', 'controller', 'com.kali.blog.controller.file.SysFileUploadController.uploadFile()', '公共上传文件', '8', ' file:\"org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@a73ba52\" filePath:\"/Volumes/Application/Download/upload/\"', '2021', '1430109634181881856', '2021-10-23 20:57:08');
INSERT INTO `sys_log` VALUES ('1451928267954343937', '0:0:0:0:0:0:0:1', 'controller', 'com.kali.blog.controller.file.SysFileUploadController.selectFileList()', '查询文件列表分页信息', '96', ' filePage:\"SysFileUploadVOPage(pageCurrent=0, pageSize=10, originalName=, fileName=, fileSize=, fileSuffix=, fileType=, fileLocation=, fileFullAddress=, ip=, fileRemark=, createBy=, createDate=null)\" bindingResult:\"org.springframework.validation.BeanPropertyBindingResult: 0 errors\"', '2021', '1430109634181881856', '2021-10-23 23:07:29');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
