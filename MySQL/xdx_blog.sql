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

 Date: 11/09/2022 15:21:38
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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_article_catg
-- ----------------------------
INSERT INTO `xdx_article_catg` VALUES (10, 0, 'Java', 'ENABLE', 0, '0000-00-00 00:00:00', 0, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (11, 10, 'Java中级', 'ENABLE', 1, '2022-08-01 22:32:26', 1, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (12, 0, '数据库', 'ENABLE', 1, '2022-08-07 22:37:21', 1, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (13, 1, 'ss', 'ENABLE', 0, '2022-08-13 16:29:19', 1, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (14, 12, '测试', 'ENABLE', 2, '2022-08-14 16:59:06', 1, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (15, 14, '312312', 'ENABLE', 1, '2022-08-14 17:18:32', 1, NULL, NULL);
INSERT INTO `xdx_article_catg` VALUES (16, 0, 'MQ', 'ENABLE', 0, '2022-09-05 09:45:20', 1, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_article_detail
-- ----------------------------
INSERT INTO `xdx_article_detail` VALUES (14, 14, '<br /> \n<h2><a id=\"_2\"></a>测试测试</h2>\n<p>这是一篇测试文章</p>\n', '<br/> \n\n## 测试测试\n\n这是一篇测试文章', '2022-08-01 22:37:55', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (15, 15, '<h3><a id=\"Dad_0\"></a>Dad</h3>\n', '### Dad', '2022-08-01 22:48:06', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (16, 16, '<p>12312321</p>\n', '12312321', '2022-08-01 22:52:33', 1, '2022-08-02 21:47:15', 1);
INSERT INTO `xdx_article_detail` VALUES (17, 17, '<p>1</p>\n', '1', '2022-08-02 21:48:55', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (18, 18, '<h2><a id=\"_0\"></a>第三方</h2>\n', '##  第三方', '2022-08-02 21:50:52', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (19, 19, '<h2><a id=\"_0\"></a>第三方</h2>\n', '##  第三方', '2022-08-02 21:51:06', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (20, 20, '<p>给恶趣味</p>\n', '给恶趣味', '2022-08-07 20:48:57', 1, '2022-08-07 22:47:29', 1);
INSERT INTO `xdx_article_detail` VALUES (21, 21, '<p>312312312</p>\n', '312312312', '2022-08-07 22:51:18', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (22, 22, '<p>312312312</p>\n', '312312312', '2022-08-07 22:51:20', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (23, 23, '<p>312312312</p>\n', '312312312', '2022-08-07 22:51:21', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (24, 24, '<p>312312312</p>\n', '312312312', '2022-08-07 22:51:21', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (25, 25, '<p>312312312</p>\n', '312312312', '2022-08-07 22:51:21', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (26, 26, '<p>312312312</p>\n', '312312312', '2022-08-07 22:51:21', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (27, 27, '<p>312</p>\n', '312', '2022-08-07 22:51:50', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (28, 28, '<p>312</p>\n', '312', '2022-08-07 22:51:52', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (29, 29, '<p>312</p>\n', '312', '2022-08-07 22:51:53', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (30, 30, '<p>312</p>\n', '312', '2022-08-07 22:51:54', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (31, 31, '<p>312</p>\n', '312', '2022-08-07 22:51:55', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (32, 32, '<p>打法师法师</p>\n', '打法师法师', '2022-08-13 15:27:52', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (33, 33, '<p>打法师法师</p>\n', '打法师法师', '2022-08-13 15:27:53', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (34, 34, 'htmlContext', 'markdownContext', '2022-08-13 16:43:57', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (35, 35, '<p>想吃v</p>\n', '想吃v', '2022-08-13 21:01:15', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (36, 36, '<p>新增z</p>\n', '新增z', '2022-08-13 21:03:25', 1, '2022-08-13 21:03:37', 1);
INSERT INTO `xdx_article_detail` VALUES (37, 37, '<p>asdasd</p>\n', 'asdasd', '2022-08-13 22:16:35', 1, '2022-08-13 22:16:38', 1);
INSERT INTO `xdx_article_detail` VALUES (38, 38, '<p>vc</p>\n', 'vc', '2022-08-14 11:20:40', 1, NULL, NULL);
INSERT INTO `xdx_article_detail` VALUES (39, 39, '<h2><a id=\"_0\"></a>一</h2>\n<p>这事第一段</p>\n<br />\n<h2><a id=\"_5\"></a>二</h2>\n<p><code>这事第二段</code></p>\n', '## 一\n这事第一段\n\n<BR/>\n\n## 二\n\n`这事第二段`\n\n', '2022-08-14 16:58:24', 1, '2022-08-14 17:18:05', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_article_main
-- ----------------------------
INSERT INTO `xdx_article_main` VALUES (14, '这是一篇测试文章', 'ddddd', 0, 0, 11, '11,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-01 22:37:55', 1, NULL, NULL);
INSERT INTO `xdx_article_main` VALUES (15, '这是新的测试文章22222222222222222222222222222222222222222222222222222222222222222222222222222', 'ddddd', 0, 0, 11, '222,', 'REPRINT', 'NO', 'NO', 'YES', '2022-08-01 22:48:06', 1, NULL, NULL);
INSERT INTO `xdx_article_main` VALUES (16, '这是信得测试问题11', 'dddddd', 0, 0, 11, '312,', 'TRANSLATE', 'YES', 'YES', 'YES', '2022-08-01 22:52:33', 1, '2022-08-02 21:47:15', 1);
INSERT INTO `xdx_article_main` VALUES (17, '和', 'ddddd', 0, 0, 10, '1,', 'ORIGINAL', 'YES', 'NO', 'NO', '2022-08-02 21:48:55', 1, '2022-08-02 21:48:55', NULL);
INSERT INTO `xdx_article_main` VALUES (18, '幸福v', 'ddddd', 0, 0, 10, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-02 21:50:52', 1, '2022-08-02 21:50:52', NULL);
INSERT INTO `xdx_article_main` VALUES (19, '幸福v', 'ddddd', 0, 0, 10, '1,', 'ORIGINAL', 'NO', 'YES', 'YES', '2022-08-02 21:51:06', 1, '2022-08-02 21:51:06', NULL);
INSERT INTO `xdx_article_main` VALUES (20, '过热', 'dddddd', 0, 0, 10, '12,45,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-07 20:48:57', 1, '2022-08-07 22:47:29', 1);
INSERT INTO `xdx_article_main` VALUES (21, '大苏打', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-07 22:51:18', 1, '2022-08-07 22:51:18', NULL);
INSERT INTO `xdx_article_main` VALUES (22, '大苏打', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-07 22:51:20', 1, '2022-08-07 22:51:20', NULL);
INSERT INTO `xdx_article_main` VALUES (23, '大苏打', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-07 22:51:21', 1, '2022-08-07 22:51:21', NULL);
INSERT INTO `xdx_article_main` VALUES (24, '大苏打', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-07 22:51:21', 1, '2022-08-07 22:51:21', NULL);
INSERT INTO `xdx_article_main` VALUES (25, '大苏打', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-07 22:51:21', 1, '2022-08-07 22:51:21', NULL);
INSERT INTO `xdx_article_main` VALUES (26, '大苏打', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-07 22:51:21', 1, '2022-08-07 22:51:21', NULL);
INSERT INTO `xdx_article_main` VALUES (27, '打算', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-07 22:51:50', 1, '2022-08-07 22:51:50', NULL);
INSERT INTO `xdx_article_main` VALUES (28, '打算', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'NO', 'YES', 'YES', '2022-08-07 22:51:52', 1, '2022-08-07 22:51:52', NULL);
INSERT INTO `xdx_article_main` VALUES (29, '打算', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'NO', 'YES', 'YES', '2022-08-07 22:51:53', 1, '2022-08-07 22:51:53', NULL);
INSERT INTO `xdx_article_main` VALUES (30, '打算', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'NO', 'YES', 'YES', '2022-08-07 22:51:54', 1, '2022-08-07 22:51:54', NULL);
INSERT INTO `xdx_article_main` VALUES (31, '打算', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'NO', 'YES', 'YES', '2022-08-07 22:51:55', 1, '2022-08-07 22:51:55', NULL);
INSERT INTO `xdx_article_main` VALUES (32, '这是一篇测试问题咩咩咩咩咩咩咩咩吗', 'ddddd', 0, 0, 11, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-13 15:27:52', 1, '2022-08-13 15:27:52', NULL);
INSERT INTO `xdx_article_main` VALUES (33, '这是一篇测试问题咩咩咩咩咩咩咩咩吗', 'ddddd', 0, 0, 11, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-13 15:27:53', 1, '2022-08-13 15:27:53', NULL);
INSERT INTO `xdx_article_main` VALUES (34, 'titlddde', 'ddddd', 0, 0, 88, 'labdddel', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-13 16:43:57', 1, '2022-08-13 16:43:57', NULL);
INSERT INTO `xdx_article_main` VALUES (35, '想吃 ', 'ddddd', 0, 0, 12, '1,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-13 21:01:15', 1, '2022-08-13 21:01:15', NULL);
INSERT INTO `xdx_article_main` VALUES (36, '新增', 'dddddd', 0, 0, 12, '123,', 'ORIGINAL', 'NO', 'YES', 'YES', '2022-08-13 21:03:25', 1, '2022-08-13 21:03:37', 1);
INSERT INTO `xdx_article_main` VALUES (37, 'dasdv', 'dddddd', 0, 0, 12, '2131,', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-13 22:16:35', 1, '2022-08-13 22:16:38', 1);
INSERT INTO `xdx_article_main` VALUES (38, '现场v', 'ddddd', 0, 0, NULL, '', 'ORIGINAL', 'YES', 'YES', 'YES', '2022-08-14 11:20:40', 1, '2022-08-14 11:20:40', NULL);
INSERT INTO `xdx_article_main` VALUES (39, '测试写文章22、8、4222222手动发发稿费地方广泛的的法国队会感到翻跟斗乳房个人观点土豆粉更多如果土地给对方更多如果发都让狗日的风格广泛大概如果让他给任何一条入户阳台借天涯让他hurt发红包', 'dddddd', 0, 0, 11, '测试,222,', 'ORIGINAL', 'NO', 'YES', 'YES', '2022-08-14 16:58:24', 1, '2022-08-14 17:18:05', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_menu
-- ----------------------------
INSERT INTO `xdx_menu` VALUES (38, '系统管理', 'setting', 0, 1, '/manager', 'ENABLE', '2022-07-02 17:14:37', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (40, '文章管理', 'bulb', 0, 2, '/manager', 'ENABLE', '2022-07-02 17:19:13', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (44, '用户管理', 'user', 38, 1, '/manager/user', 'ENABLE', '2022-07-02 18:32:44', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (45, '角色管理', 'info-circle', 38, 1, '/manager/roles', 'ENABLE', '2022-07-02 18:34:02', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (46, '菜单管理', 'menu', 38, 1, '/manager/menu', 'ENABLE', '2022-07-02 18:34:20', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (47, '博文管理', 'ordered-list', 40, 2, '/manager/article/article', 'ENABLE', '2022-07-02 18:38:30', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (48, '分类管理', 'table', 40, 1, '/manager/article/catg', 'ENABLE', '2022-07-02 18:39:38', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (49, '123123', 'info-circle', 0, 0, '312321', 'ENABLE', '2022-07-10 11:47:01', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (50, '写文章', 'edit', 40, 3, '/manager/article/write', 'ENABLE', '2022-07-14 22:00:06', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (51, '1231312', 'info-circle', 49, 1, '312321', 'ENABLE', '2022-07-14 22:52:36', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (52, '312321321', 'info-circle', 51, 1, '312321', 'ENABLE', '2022-07-14 22:52:41', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (53, '312321321222', 'info-circle', 52, 1, '312321', 'DISABLE', '2022-07-14 22:53:28', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (54, '312312321', 'info-circle', 52, 1, '312321', 'DISABLE', '2022-07-14 22:53:43', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (55, 'rrrr', 'info-circle', 52, 1, '312321', 'ENABLE', '2022-07-16 16:15:37', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (56, '312312', 'info-circle', 51, 1, '312321', 'ENABLE', '2022-07-17 10:49:44', 1, NULL, NULL);
INSERT INTO `xdx_menu` VALUES (57, '测试', 'down-circle', 0, 1, '/test', 'ENABLE', '2022-07-17 17:38:32', 1, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 217 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色菜单关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_role_menu_relation
-- ----------------------------
INSERT INTO `xdx_role_menu_relation` VALUES (185, 4, 48, '2022-07-16 22:52:50', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (202, 1, 47, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (203, 1, 40, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (204, 1, 50, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (205, 1, 48, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (206, 1, 38, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (207, 1, 44, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (208, 1, 45, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (209, 1, 46, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (210, 1, 49, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (211, 1, 51, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (212, 1, 52, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (213, 1, 53, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (214, 1, 54, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (215, 1, 55, '2022-09-05 10:18:19', 1, NULL, NULL);
INSERT INTO `xdx_role_menu_relation` VALUES (216, 1, 56, '2022-09-05 10:18:19', 1, NULL, NULL);

-- ----------------------------
-- Table structure for xdx_user
-- ----------------------------
DROP TABLE IF EXISTS `xdx_user`;
CREATE TABLE `xdx_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名称',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `status` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'ENABLE' COMMENT '是否启用（ENABLE 启用 DISABLE 禁用）',
  `salt` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '加密盐',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'md5密码',
  `create_at` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdx_user
-- ----------------------------
INSERT INTO `xdx_user` VALUES (1, '超级管理员', 1, 'ENABLE', 'be8c2350-01d2-4406-a2d1-53f93fe06bd8', '61267d3f0dc9628853cadc06577a455e', '2022-03-26 20:38:52', 1, NULL, NULL);
INSERT INTO `xdx_user` VALUES (2, '小道仙', 1, 'ENABLE', '59c9d75b-6bd6-4c09-9bf8-66a2d2f5a74c', '247a8bd3dc161f7c5924a986c643ada4', '2022-09-01 23:01:21', 1, '2022-09-11 12:28:33', 1);
INSERT INTO `xdx_user` VALUES (3, '超级管理员2', 1, 'ENABLE', '8973cd08-482e-4a35-a869-b8f9b3960d33', 'b8a81ef58fc68c14e259f4588a499ff9', '2022-09-05 09:37:13', 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
