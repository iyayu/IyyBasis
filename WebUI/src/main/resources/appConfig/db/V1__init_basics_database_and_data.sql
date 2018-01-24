/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : basics_database

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-24 20:57:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_user_account
-- ----------------------------
DROP TABLE IF EXISTS `system_user_account`;
CREATE TABLE `system_user_account` (
  `id` char(32) NOT NULL COMMENT '系统用户账号主键',
  `systemUserAccountCode` varchar(50) NOT NULL COMMENT '系统用户账号代码',
  `systemUserAccountName` varchar(50) NOT NULL COMMENT '系统用户账号名字',
  `passWord` varchar(52) NOT NULL COMMENT '密码',
  `salt` varchar(32) NOT NULL COMMENT '盐',
  `available` char(1) NOT NULL COMMENT '是否锁定, 1: 未锁定, 0: 锁定',
  PRIMARY KEY (`id`,`systemUserAccountCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_account
-- ----------------------------
INSERT INTO `system_user_account` VALUES ('30ffa394be4d11e7a0dd1c75086c29d6', 'admin', 'admin', '2584798b34b271af4637f968533484cd042e7bfdf7e6bb991d03', '1c8f35c92a97de7a7d6795e5e9624be8', '1');

-- ----------------------------
-- Table structure for system_user_account_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_account_role`;
CREATE TABLE `system_user_account_role` (
  `id` char(32) NOT NULL COMMENT '系统用户账号角色关联表主键',
  `systemUserAccountId` char(32) NOT NULL COMMENT '系统用户账号主键',
  `systemUserRoleId` char(32) NOT NULL COMMENT '系统用户角色主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_account_role
-- ----------------------------
INSERT INTO `system_user_account_role` VALUES ('03bd0425b01411e7b9041c75086c29d6', '30ffa394be4d11e7a0dd1c75086c29d6', 'ebc8a441c6f911e4b1370adc305c3f28');

-- ----------------------------
-- Table structure for system_user_info
-- ----------------------------
DROP TABLE IF EXISTS `system_user_info`;
CREATE TABLE `system_user_info` (
  `id` char(32) NOT NULL COMMENT '系统用户信息主键',
  `systemUserAccountId` char(32) NOT NULL COMMENT '系统用户账号主键',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `regTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `resTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注销时间',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`,`systemUserAccountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_info
-- ----------------------------
INSERT INTO `system_user_info` VALUES ('qweffa394be4d11e7a0dd1c75086c29d', '30ffa394be4d11e7a0dd1c75086c29d6', '13176905258', '2018-01-21 17:53:59', '2018-01-21 17:53:59', '23');

-- ----------------------------
-- Table structure for system_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_user_menu`;
CREATE TABLE `system_user_menu` (
  `id` char(32) NOT NULL COMMENT '系统用户菜单主键',
  `name` varchar(128) NOT NULL COMMENT '资源名称',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `parentId` char(32) NOT NULL COMMENT '父结点id',
  `queueNumber` bigint(255) NOT NULL COMMENT '排序号',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `describe` varchar(500) DEFAULT NULL COMMENT '说明',
  `available` char(1) NOT NULL COMMENT '是否锁定, 1: 未锁定, 0: 锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_menu
-- ----------------------------
INSERT INTO `system_user_menu` VALUES ('1', '根', null, '1', '0', null, '这是根节点请勿删除', '1');
INSERT INTO `system_user_menu` VALUES ('2fb3021ae97a11e798841c75086c29d0', '系统管理', null, '1', '2', 'fa fa-cogs', '', '1');
INSERT INTO `system_user_menu` VALUES ('2fb3021ae97a11e798841c75086c29d1', '账号管理', '/backstage/systemUserManage/userAccount.html', '2fb3021ae97a11e798841c75086c29d0', '1', 'fa fa-cogs', '', '1');
INSERT INTO `system_user_menu` VALUES ('2fb3021ae97a11e798841c75086c29d2', '角色权限', '/backstage/systemUserRole/rolePermission.html', '2fb3021ae97a11e798841c75086c29d0', '0', 'fa fa-cogs', '', '1');
INSERT INTO `system_user_menu` VALUES ('2fb3021ae97a11e798841c75086c29d3', '菜单管理', '/backstage/systemMenuManage/menuManage.html', '2fb3021ae97a11e798841c75086c29d0', '1', 'fa fa-cogs', '', '1');

-- ----------------------------
-- Table structure for system_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_user_permission`;
CREATE TABLE `system_user_permission` (
  `id` char(32) NOT NULL COMMENT '系统用户权限主键',
  `name` varchar(128) NOT NULL COMMENT '资源名称',
  `perCode` varchar(128) DEFAULT NULL COMMENT '权限代码字符串',
  `describe` varchar(500) DEFAULT NULL COMMENT '说明',
  `available` char(1) DEFAULT NULL COMMENT '是否锁定, 1: 未锁定, 0: 锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_permission
-- ----------------------------
INSERT INTO `system_user_permission` VALUES ('044c677ada5a11e79d803c52820df9a0', '系统管理', 'systemManage:url', null, '1');
INSERT INTO `system_user_permission` VALUES ('144c677ada5a11e79d803c52820df9a0', '添加系统用户账号', 'systemUserAccount:insert', null, '1');
INSERT INTO `system_user_permission` VALUES ('144c677ada5a11e79d803c52820df9a1', '删除系统用户账号', 'systemUserAccount:delete', null, '1');
INSERT INTO `system_user_permission` VALUES ('144c677ada5a11e79d803c52820df9a2', '修改系统用户账号', 'systemUserAccount:update', null, '1');
INSERT INTO `system_user_permission` VALUES ('144c677ada5a11e79d803c52820df9a3', '查看系统用户账号', 'systemUserAccount:list', null, '1');
INSERT INTO `system_user_permission` VALUES ('244c677ada5a11e79d803c52820df9a0', '查看系统用户菜单', 'systemUserMenu:list', null, '1');
INSERT INTO `system_user_permission` VALUES ('244c677ada5a11e79d803c52820df9a1', '删除系统用户菜单', 'systemUserMenu:delete', null, '1');
INSERT INTO `system_user_permission` VALUES ('244c677ada5a11e79d803c52820df9a2', '修改系统用户菜单', 'systemUserMenu:update', null, '1');
INSERT INTO `system_user_permission` VALUES ('244c677ada5a11e79d803c52820df9a3', '添加系统用户菜单', 'systemUserMenu:insert', null, '1');
INSERT INTO `system_user_permission` VALUES ('444c677ada5a11e79d803c52820df9a1', '添加系统用户组', 'systemUserGroup:insert', null, '1');
INSERT INTO `system_user_permission` VALUES ('444c677ada5a11e79d803c52820df9a2', '删除系统用户组', 'systemUserGroup:delete', null, '1');
INSERT INTO `system_user_permission` VALUES ('444c677ada5a11e79d803c52820df9a4', '修改系统用户组', 'systemUserGroup:update', null, '1');
INSERT INTO `system_user_permission` VALUES ('444c677ada5a11e79d803c52820df9a5', '查看系统用户组', 'systemUserGroup:list', null, '1');
INSERT INTO `system_user_permission` VALUES ('714ecff5d2bf11e7ad741c75086c29d1', '添加系统用户角色', 'systemUserRole:insert', null, '1');
INSERT INTO `system_user_permission` VALUES ('714ecff5d2bf11e7ad741c75086c29d2', '删除系统用户角色', 'systemUserRole:delete', null, '1');
INSERT INTO `system_user_permission` VALUES ('714ecff5d2bf11e7ad741c75086c29d3', '修改系统用户角色', 'systemUserRole:update', null, '1');
INSERT INTO `system_user_permission` VALUES ('714ecff5d2bf11e7ad741c75086c29d4', '查看系统用户角色', 'systemUserRole:list', null, '1');
INSERT INTO `system_user_permission` VALUES ('957ecff5d2bf11e7ad741c75086c29d1', '添加系统用户权限', 'systemUserPermission:insert', null, '1');
INSERT INTO `system_user_permission` VALUES ('957ecff5d2bf11e7ad741c75086c29d2', '删除系统用户权限', 'systemUserPermission:delete', null, '1');
INSERT INTO `system_user_permission` VALUES ('957ecff5d2bf11e7ad741c75086c29d3', '修改系统用户权限', 'systemUserPermission:update', null, '1');
INSERT INTO `system_user_permission` VALUES ('957ecff5d2bf11e7ad741c75086c29d4', '查看系统用户权限', 'systemUserPermission:list', null, '1');

-- ----------------------------
-- Table structure for system_user_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_user_permission_menu`;
CREATE TABLE `system_user_permission_menu` (
  `id` char(32) NOT NULL COMMENT '系统用户权限与菜单关联主键',
  `systemUserPermissionId` char(32) NOT NULL COMMENT '系统用户权限主键',
  `systemUserMenuId` char(32) NOT NULL COMMENT '系统用户菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_permission_menu
-- ----------------------------
INSERT INTO `system_user_permission_menu` VALUES ('a5ca2728010011e8be6754e1ade79044', '044c677ada5a11e79d803c52820df9a0', '2fb3021ae97a11e798841c75086c29d0');

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `id` char(32) NOT NULL COMMENT '系统用户角色',
  `name` varchar(128) NOT NULL COMMENT '角色名',
  `describe` varchar(500) DEFAULT '' COMMENT '描述',
  `available` char(1) NOT NULL DEFAULT '1' COMMENT '是否锁定, 1: 未锁定, 0: 锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES ('ebc8a441c6f911e4b1370adc305c3f28', '超级管理员', '拥有平台所有权限, 千万不要泄露', '1');

-- ----------------------------
-- Table structure for system_user_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role_permission`;
CREATE TABLE `system_user_role_permission` (
  `id` char(32) NOT NULL COMMENT '系统用户角色权限关联主键',
  `systemUserRoleId` char(32) NOT NULL COMMENT '系统用户角色主键',
  `systemUserPermissionId` char(32) NOT NULL COMMENT '系统用户权限主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_role_permission
-- ----------------------------
INSERT INTO `system_user_role_permission` VALUES ('38201e0eff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '044c677ada5a11e79d803c52820df9a0');
INSERT INTO `system_user_role_permission` VALUES ('382020a6ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '144c677ada5a11e79d803c52820df9a0');
INSERT INTO `system_user_role_permission` VALUES ('38202197ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '144c677ada5a11e79d803c52820df9a1');
INSERT INTO `system_user_role_permission` VALUES ('382021d5ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '144c677ada5a11e79d803c52820df9a2');
INSERT INTO `system_user_role_permission` VALUES ('3820220fff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '144c677ada5a11e79d803c52820df9a3');
INSERT INTO `system_user_role_permission` VALUES ('3820223eff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '244c677ada5a11e79d803c52820df9a0');
INSERT INTO `system_user_role_permission` VALUES ('38202280ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '244c677ada5a11e79d803c52820df9a1');
INSERT INTO `system_user_role_permission` VALUES ('382022acff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '244c677ada5a11e79d803c52820df9a2');
INSERT INTO `system_user_role_permission` VALUES ('382022d4ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '244c677ada5a11e79d803c52820df9a3');
INSERT INTO `system_user_role_permission` VALUES ('382022f8ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '444c677ada5a11e79d803c52820df9a1');
INSERT INTO `system_user_role_permission` VALUES ('38202321ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '444c677ada5a11e79d803c52820df9a2');
INSERT INTO `system_user_role_permission` VALUES ('38202345ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '444c677ada5a11e79d803c52820df9a4');
INSERT INTO `system_user_role_permission` VALUES ('38202371ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '444c677ada5a11e79d803c52820df9a5');
INSERT INTO `system_user_role_permission` VALUES ('38202395ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '714ecff5d2bf11e7ad741c75086c29d1');
INSERT INTO `system_user_role_permission` VALUES ('382023baff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '714ecff5d2bf11e7ad741c75086c29d2');
INSERT INTO `system_user_role_permission` VALUES ('382023deff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '714ecff5d2bf11e7ad741c75086c29d3');
INSERT INTO `system_user_role_permission` VALUES ('38202403ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '714ecff5d2bf11e7ad741c75086c29d4');
INSERT INTO `system_user_role_permission` VALUES ('38202448ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '957ecff5d2bf11e7ad741c75086c29d1');
INSERT INTO `system_user_role_permission` VALUES ('3820246cff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '957ecff5d2bf11e7ad741c75086c29d2');
INSERT INTO `system_user_role_permission` VALUES ('38202491ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '957ecff5d2bf11e7ad741c75086c29d3');
INSERT INTO `system_user_role_permission` VALUES ('382024b9ff7811e7a53754e1ade79044', 'ebc8a441c6f911e4b1370adc305c3f28', '957ecff5d2bf11e7ad741c75086c29d4');
