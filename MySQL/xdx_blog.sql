/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : 127.0.0.1:3306
 Source Schema         : xdx_blog

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 17/07/2022 17:08:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xdx_article_catg
-- ----------------------------
DROP TABLE IF EXISTS `xdx_article_catg`;
CREATE TABLE `xdx_article_catg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `status` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ENABLE' COMMENT '是否启用（ENABLE 启用 DISABLE 禁用）',
  `sort` tinyint(2) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_at` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_article_catg
-- ----------------------------
INSERT INTO `xdx_article_catg` VALUES (1, 0, 'sssss', 'ENABLE', 0, '0000-00-00 00:00:00', 0, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (2, 0, 'ddd', 'ENABLE', 0, '0000-00-00 00:00:00', 0, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (3, 0, 'dasdas', 'ENABLE', 0, '0000-00-00 00:00:00', 0, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (4, 1, 'D', 'DISABLE', 1, '0000-00-00 00:00:00', 0, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (5, 0, 'ss', 'ENABLE', 0, '2022-06-22 22:35:45', 1, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (6, 0, 'ss', 'ENABLE', 0, '2022-07-04 21:48:35', 1, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (7, 1, 'ss', 'ENABLE', 0, '2022-07-04 21:48:39', 1, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (8, 2, '1', 'ENABLE', 1, '2022-07-11 22:34:40', 1, NULL, NULL);

-- ----------------------------
-- Table structure for xdx_article_detail
-- ----------------------------
DROP TABLE IF EXISTS `xdx_article_detail`;
CREATE TABLE `xdx_article_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` int(11) NOT NULL COMMENT '博客id',
  `html_context` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容-html',
  `markdown_context` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容-markdown',
  `create_at` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_article_detail
-- ----------------------------
INSERT INTO `xdx_article_detail` VALUES (1, 1, 'htmlContext', 'markdownContext', '2022-06-26 18:37:24', 1, '2022-07-02 15:10:25', 1);
INSERT INTO `xdx_article_detail` VALUES (2, 2, 'htmlContext', 'markdownContext', '2022-07-02 15:10:46', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (3, 3, 'htmlContext', 'markdownContext', '2022-07-02 15:10:47', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (4, 4, 'htmlContext', 'markdownContext', '2022-07-02 15:10:47', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (5, 5, 'htmlContext', 'markdownContext', '2022-07-02 15:10:47', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (6, 6, 'htmlContext', 'markdownContext', '2022-07-02 15:10:47', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (7, 7, 'htmlContext', 'markdownContext', '2022-07-02 15:10:48', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (8, 8, 'htmlContext', 'markdownContext', '2022-07-02 15:10:48', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (9, 9, 'htmlContext', 'markdownContext', '2022-07-02 15:10:48', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (10, 10, 'htmlContext', 'markdownContext', '2022-07-02 15:10:49', 1, NULL, NULL);

-- ----------------------------
-- Table structure for xdx_article_main
-- ----------------------------
DROP TABLE IF EXISTS `xdx_article_main`;
CREATE TABLE `xdx_article_main`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
  `article_desc` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章描述',
  `good_count` int(6) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `read_count` int(7) NOT NULL DEFAULT 0 COMMENT '阅读数',
  `catg_id` int(18) NULL DEFAULT NULL COMMENT '分类id',
  `label` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文章标签',
  `type` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'ORIGINAL' COMMENT '文章类型(ORIGINAL 原创, REPRINT 转载), TRANSLATE 翻译)',
  `draft` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'YES' COMMENT '是否草稿（YES是 NO否）',
  `top` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'YES' COMMENT '是否置顶（YES是 NO否）',
  `open` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'YES' COMMENT '是否公开（YES是 NO否）',
  `create_at` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_article_main
-- ----------------------------
INSERT INTO `xdx_article_main` VALUES (1, 'title', 'dddddd', 0, 0, 998, 'labedddl', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-06-26 18:37:24', 1, '2022-07-02 15:10:24', 1);
INSERT INTO `xdx_article_main` VALUES (2, 'titlddde', 'ddddd', 0, 0, 88, 'labdddel', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-07-02 15:10:46', 1, NULL, NULL);
INSERT INTO `xdx_article_main` VALUES (3, 'titlddde', 'ddddd', 0, 0, 88, 'labdddel', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-07-02 15:10:47', 1, NULL, NULL);
INSERT INTO `xdx_article_main` VALUES (4, 'titlddde', 'ddddd', 0, 0, 88, 'labdddel', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-07-02 15:10:47', 1, NULL, NULL);
INSERT INTO `xdx_article_main` VALUES (5, 'titlddde', 'ddddd', 0, 0, 88, 'labdddel', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-07-02 15:10:47', 1, NULL, NULL);
INSERT INTO `xdx_article_main` VALUES (6, 'titlddde', 'ddddd', 0, 0, 88, 'labdddel', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-07-02 15:10:47', 1, NULL, NULL);
INSERT INTO `xdx_article_main` VALUES (7, 'titlddde', 'ddddd', 0, 0, 88, 'labdddel', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-07-02 15:10:48', 1, NULL, NULL);
INSERT INTO `xdx_article_main` VALUES (8, 'titlddde', 'ddddd', 0, 0, 88, 'labdddel', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-07-02 15:10:48', 1, NULL, NULL);
INSERT INTO `xdx_article_main` VALUES (9, 'titlddde', 'ddddd', 0, 0, 88, 'labdddel', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-07-02 15:10:48', 1, NULL, NULL);
INSERT INTO `xdx_article_main` VALUES (10, 'titlddde', 'ddddd', 0, 0, 88, 'labdddel', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-07-02 15:10:49', 1, NULL, NULL);

-- ----------------------------
-- Table structure for xdx_menu
-- ----------------------------
DROP TABLE IF EXISTS `xdx_menu`;
CREATE TABLE `xdx_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'icon',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父id',
  `sort` int(2) NOT NULL DEFAULT 0 COMMENT '排序',
  `menu_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单url',
  `status` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ENABLE' COMMENT '是否启用（ENABLE 启用 DISABLE 禁用）',
  `create_at` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_menu
-- ----------------------------
INSERT INTO `xdx_menu` VALUES (38, '系统管理', 'setting', 0, 1, '/manager', 'ENABLE', '2022-07-02 17:14:37', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (40, '文章管理', 'bulb', 0, 2, '/manager', 'ENABLE', '2022-07-02 17:19:13', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (44, '用户管理', 'user', 38, 1, '/manager/user', 'ENABLE', '2022-07-02 18:32:44', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (45, '角色管理', 'info-circle', 38, 1, '/manager/roles', 'ENABLE', '2022-07-02 18:34:02', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (46, '菜单管理', 'menu', 38, 1, '/manager/menu', 'ENABLE', '2022-07-02 18:34:20', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (47, '博文管理', NULL, 40, 1, '/manager/article/article', 'ENABLE', '2022-07-02 18:38:30', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (48, '分类管理', NULL, 40, 1, '/manager/article/catg', 'ENABLE', '2022-07-02 18:39:38', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (49, '123123', 'info-circle', 0, 0, '312321', 'ENABLE', '2022-07-10 11:47:01', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (50, '写文章', 'info-circle', 40, 1, '/manager/article/write', 'ENABLE', '2022-07-14 22:00:06', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (51, '1231312', 'info-circle', 49, 1, '312321', 'ENABLE', '2022-07-14 22:52:36', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (52, '312321321', 'info-circle', 51, 1, '312321', 'ENABLE', '2022-07-14 22:52:41', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (53, '312321321222', 'info-circle', 52, 1, '312321', 'DISABLE', '2022-07-14 22:53:28', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (54, '312312321', 'info-circle', 52, 1, '312321', 'DISABLE', '2022-07-14 22:53:43', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (55, 'rrrr', 'info-circle', 52, 1, '312321', 'ENABLE', '2022-07-16 16:15:37', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (56, '312312', 'info-circle', 51, 1, '312321', 'ENABLE', '2022-07-17 10:49:44', 1, NULL, NULL);

-- ----------------------------
-- Table structure for xdx_role
-- ----------------------------
DROP TABLE IF EXISTS `xdx_role`;
CREATE TABLE `xdx_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `status` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ENABLE' COMMENT '是否启用（ENABLE 启用 DISABLE 禁用）',
  `create_at` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_role
-- ----------------------------
INSERT INTO `xdx_role` VALUES (1, '超级管理员', 'ENABLE', '2022-03-26 19:44:14', 1, NULL, NULL);
INSERT INTO `xdx_role` VALUES (2, '管理员', 'ENABLE', '2022-03-26 20:03:59', 1, NULL, NULL);
INSERT INTO `xdx_role` VALUES (3, '游客', 'ENABLE', '2022-04-10 15:45:21', 1, NULL, NULL);
INSERT INTO `xdx_role` VALUES (4, '邮寄', 'ENABLE', '2022-04-10 15:46:59', 1, NULL, NULL);
INSERT INTO `xdx_role` VALUES (5, 'ctu', 'ENABLE', '2022-06-20 20:48:11', 1, NULL, NULL);
INSERT INTO `xdx_role` VALUES (6, '管理员', 'ENABLE', '2022-06-20 22:06:01', 1, NULL, NULL);
INSERT INTO `xdx_role` VALUES (7, '管理员', 'ENABLE', '2022-06-20 22:06:02', 1, NULL, NULL);

-- ----------------------------
-- Table structure for xdx_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `xdx_role_menu_relation`;
CREATE TABLE `xdx_role_menu_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `create_at` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 186 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色菜单关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_role_menu_relation
-- ----------------------------
INSERT INTO `xdx_role_menu_relation` VALUES (172, 1, 47, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (173, 1, 40, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (174, 1, 48, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (175, 1, 50, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (176, 1, 38, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (177, 1, 44, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (178, 1, 45, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (179, 1, 46, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (180, 1, 49, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (181, 1, 51, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (182, 1, 52, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (183, 1, 53, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (184, 1, 54, '2022-07-14 22:59:09', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (185, 4, 48, '2022-07-16 22:52:50', 1, NULL, NULL);

-- ----------------------------
-- Table structure for xdx_user
-- ----------------------------
DROP TABLE IF EXISTS `xdx_user`;
CREATE TABLE `xdx_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名称',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `status` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ENABLE' COMMENT '是否启用（ENABLE 启用 DISABLE 禁用）',
  `salt` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '加密盐',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'md5密码',
  `create_at` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_user
-- ----------------------------
INSERT INTO `xdx_user` VALUES (1, '超级管理员', 1, 'ENABLE', 'dasd', '', '2022-03-26 20:38:52', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (2, '张三散的', 2, 'ENABLE', 'dasd', '', '2022-03-26 21:04:11', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (3, '张三丰', 1, 'ENABLE', 'dasd', 'dasda', '2022-03-26 21:04:12', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (4, '张三丰', 1, 'ENABLE', 'dasd', 'dasda', '2022-03-26 21:04:12', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (5, '张三丰', 1, 'ENABLE', 'dasd', 'dasda', '2022-03-26 21:04:12', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (6, '张三丰', 1, 'ENABLE', 'dasd', 'dasda', '2022-03-26 21:04:13', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (7, '张三丰', 1, 'ENABLE', 'dasd', 'dasda', '2022-03-26 21:04:13', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (8, '张三丰', 1, 'ENABLE', 'dasd', 'dasda', '2022-03-26 21:04:13', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (9, '张三丰', 2, 'ENABLE', 'dasd', '', '2022-03-26 21:04:14', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (10, '张三丰', 1, 'ENABLE', 'dasd', 'dasda', '2022-03-26 21:04:14', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (11, '张三丰', 1, 'ENABLE', 'dasd', 'dasda', '2022-03-26 21:04:24', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (12, '张三丰', 1, 'ENABLE', 'dasd', 'dasda', '2022-03-26 21:04:25', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (13, '张三丰', 1, 'ENABLE', 'dasd', 'dasda', '2022-03-26 21:04:25', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (14, '123', 1, 'ENABLE', 'dasd', 'dasda', '2022-04-10 11:17:52', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (15, '456', 2, 'ENABLE', 'dasd', 'dasda', '2022-04-10 11:18:46', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (16, '222', 2, 'ENABLE', 'dasd', 'dasda', '2022-04-10 11:27:26', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (17, '666', 2, 'ENABLE', 'dasd', 'dasda', '2022-04-10 11:28:27', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (18, 'hy b', 2, 'ENABLE', 'dasd', '', '2022-04-10 14:40:16', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (19, 'dasdff', 2, 'ENABLE', 'dasd', '', '2022-06-19 22:01:28', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (20, '张三丰', 2, 'ENABLE', 'dasd', '', '2022-06-20 22:05:57', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (21, '张三丰', 4, 'ENABLE', 'dasd', '', '2022-06-20 22:05:58', 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
