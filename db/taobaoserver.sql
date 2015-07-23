/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : taobaoserver

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-07-23 15:18:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_menu`
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `orders` double(10,2) DEFAULT NULL,
  `isbutton` int(1) DEFAULT '0',
  `btnflag` varchar(64) DEFAULT NULL,
  `pid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES ('1', '系统管理', null, '1.00', '0', null, '0');
INSERT INTO `admin_menu` VALUES ('2', '菜单管理', '/admin/menu/index', '2.00', '0', null, '1');
INSERT INTO `admin_menu` VALUES ('3', '角色管理', '/admin/role/index', '3.00', '0', null, '1');
INSERT INTO `admin_menu` VALUES ('4', '管理员管理', '/admin/user', '11.00', '0', null, '1');
INSERT INTO `admin_menu` VALUES ('5', '增加管理员', '/admin/user/doadd', null, '1', 'userdoadd', '4');
INSERT INTO `admin_menu` VALUES ('6', '修改管理员', '/admin/user/doupdate', null, '1', 'userdoupdate', '4');
INSERT INTO `admin_menu` VALUES ('7', '删除管理员', '/admin/user/dodel', null, '1', 'userdodel', '4');
INSERT INTO `admin_menu` VALUES ('8', '管理员查询', '/admin/user/dopage', null, '1', 'userdopage', '4');
INSERT INTO `admin_menu` VALUES ('9', '查看菜单', '/admin/menu/list', null, '1', 'menulist', '2');
INSERT INTO `admin_menu` VALUES ('10', '修改菜单', '/admin/menu/doupdate', null, '1', 'menudoupdate', '2');
INSERT INTO `admin_menu` VALUES ('11', '添加菜单', '/admin/menu/doadd', null, '1', 'menudoadd', '2');
INSERT INTO `admin_menu` VALUES ('12', '删除菜单', '/admin/menu/dodel', null, '1', 'menudodel', '2');
INSERT INTO `admin_menu` VALUES ('13', '添加角色', '/admin/role/doadd', null, '1', 'roledoadd', '3');
INSERT INTO `admin_menu` VALUES ('14', '修改角色', '/admin/role/doupdate', null, '1', 'roledoupdate', '3');
INSERT INTO `admin_menu` VALUES ('15', '删除角色', '/admin/role/setstatus', null, '1', 'rolesetstatus', '3');
INSERT INTO `admin_menu` VALUES ('16', '分页查看角色', '/admin/role/dopage', null, '1', 'roledopage', '3');
INSERT INTO `admin_menu` VALUES ('17', '查看某个角色', '/admin/role/get', null, '1', 'roleget', '3');
INSERT INTO `admin_menu` VALUES ('18', '密码重置', '/admin/user/setpassword', null, '1', 'usersetpassword', '4');
INSERT INTO `admin_menu` VALUES ('19', '账单管理', '', null, '0', '', '0');
INSERT INTO `admin_menu` VALUES ('20', '充值申请管理', '/admin/deposit', null, '0', '', '19');
INSERT INTO `admin_menu` VALUES ('21', '取款申请管理', '/admin/applydraw', null, '0', '', '19');
INSERT INTO `admin_menu` VALUES ('22', '取款分页查询', '/admin/applydraw/dopage', null, '1', 'admin_applydraw_dopage', '21');
INSERT INTO `admin_menu` VALUES ('23', '充值管理分页查询', '/admin/deposit/dopage', null, '1', 'admin_deposit_dopage', '20');
INSERT INTO `admin_menu` VALUES ('24', '取款拒绝', '/admin/applydraw/doarefuse', null, '1', 'admin_applydraw_doarefuse', '21');
INSERT INTO `admin_menu` VALUES ('25', '取款同意', '/admin/applydraw/doagree', null, '1', 'admin_applydraw_doagree', '21');
INSERT INTO `admin_menu` VALUES ('26', '充值同意', '/admin/deposit/doagree', null, '1', 'admin_deposit_doagree', '20');
INSERT INTO `admin_menu` VALUES ('27', '充值拒绝', '/admin/deposit/doarefuse', null, '1', 'admin_deposit_doarefuse', '20');
INSERT INTO `admin_menu` VALUES ('28', '前台用户管理', '/admin/siteuser', null, '0', '', '29');
INSERT INTO `admin_menu` VALUES ('29', '用户列表', '/admin/siteuser', null, '0', '', '0');
INSERT INTO `admin_menu` VALUES ('30', '前台用户分页查看', '/admin/siteuser/dopage', null, '1', 'admin_siteuser_dopage', '28');
INSERT INTO `admin_menu` VALUES ('32', '设置赞助用户点数', '/admin/siteuser/supplypoint', null, '1', 'admin_siteuser_supplypoint', '28');
INSERT INTO `admin_menu` VALUES ('39', '赞助用户记录', '/admin/log/COMPANY_SUPPLY', null, '0', '', '19');
INSERT INTO `admin_menu` VALUES ('40', '赞助用户记录分页', '/admin/log/dopage/COMPANY_SUPPLY', null, '1', 'admin_log_dopage_COMPANY_SUPPLY', '39');
INSERT INTO `admin_menu` VALUES ('41', '公司收支情况', '/admin/company_account', null, '0', '', '19');
INSERT INTO `admin_menu` VALUES ('42', '营销管理', '', null, '0', '', '0');
INSERT INTO `admin_menu` VALUES ('43', '邮件邮箱系统', '', null, '0', '', '42');
INSERT INTO `admin_menu` VALUES ('44', '邮箱模板管理', '/admin/mail/templete', null, '0', '', '43');
INSERT INTO `admin_menu` VALUES ('45', '邮件管理', '/admin/mail/content', null, '0', '', '43');
INSERT INTO `admin_menu` VALUES ('46', '创建邮件模板', '/admin/mail/templete/doadd', null, '1', 'admin_mail_templete_doadd', '44');
INSERT INTO `admin_menu` VALUES ('47', '修改邮件模板', '/admin/mail/templete/doupdate', null, '1', 'admin_mail_templete_doupdate', '44');
INSERT INTO `admin_menu` VALUES ('48', '删除邮件模板', '/admin/mail/templete/dodel', null, '1', 'admin_mail_templete_dodel', '44');
INSERT INTO `admin_menu` VALUES ('49', '分页查看邮件模板', '/admin/mail/templete/dopage', null, '1', 'admin_mail_templete_dopage', '44');
INSERT INTO `admin_menu` VALUES ('50', '分页查看邮件内容', '/admin/mail/content/dopage', null, '1', 'admin_mail_content_dopage', '45');
INSERT INTO `admin_menu` VALUES ('51', '增加邮件', '/admin/mail/content/doadd', null, '1', 'admin_mail_content_doadd', '45');
INSERT INTO `admin_menu` VALUES ('52', '修改邮件内容', '/admin/mail/content/doupdate', null, '1', 'admin_mail_content_doupdate', '45');
INSERT INTO `admin_menu` VALUES ('53', '删除邮件内容', '/admin/mail/content/dodel', null, '1', 'admin_mail_content_dodel', '45');
INSERT INTO `admin_menu` VALUES ('54', '查看邮件详情', '/admin/mail/content/get', null, '1', 'admin_mail_content_get', '45');
INSERT INTO `admin_menu` VALUES ('55', '查看邮件模板详情', '/admin/mail/templete/get', null, '1', 'admin_mail_templete_get', '44');
INSERT INTO `admin_menu` VALUES ('56', '工具箱', '', null, '0', '', '0');
INSERT INTO `admin_menu` VALUES ('57', '图片库', '/admin/tool/pics', null, '0', '', '56');
INSERT INTO `admin_menu` VALUES ('58', '分页查看图片', '/admin/tool/pics/dopage', null, '1', 'admin_tool_pics_dopage', '57');
INSERT INTO `admin_menu` VALUES ('59', '上传图片', '/admin/tool/pics/upload', null, '1', 'admin_tool_pics_upload', '57');
INSERT INTO `admin_menu` VALUES ('60', '删除图片', '/admin/tool/pics/dodel', null, '1', 'admin_tool_pics_del', '57');
INSERT INTO `admin_menu` VALUES ('61', '通讯录管理', '/admin/mail/addresslist', null, '0', '', '43');
INSERT INTO `admin_menu` VALUES ('62', '分页查看通讯录', '/admin/mail/addresslist/dopage', null, '1', 'admin_mail_addresslist_dopage', '61');
INSERT INTO `admin_menu` VALUES ('65', '增加通讯录联系人', '/admin/mail/addresslist/doadd', null, '1', 'admin_mail_addresslist_doadd', '61');
INSERT INTO `admin_menu` VALUES ('66', '修改通讯录联系人', '/admin/mail/addresslist/doupdate', null, '1', 'admin_mail_addresslist_doupdate', '61');
INSERT INTO `admin_menu` VALUES ('67', '删除通讯录联系人', '/admin/mail/addresslist/dodel', null, '1', 'admin_mail_addresslist_dodel', '61');
INSERT INTO `admin_menu` VALUES ('68', '发送邮件管理', '/admin/mail/sendertask', null, '0', '', '43');
INSERT INTO `admin_menu` VALUES ('69', '分页查看发送邮件任务', '/admin/mail/sendertask/dopage', null, '1', 'admin_mail_sendertask_dopage', '68');
INSERT INTO `admin_menu` VALUES ('70', '创建发送邮件任务', '/admin/mail/sendertask/doadd', null, '1', 'admin_mail_sendertask_doadd', '68');
INSERT INTO `admin_menu` VALUES ('71', '修改发送邮件任务', '/admin/mail/sendertask/doupdate', null, '1', 'admin_mail_sendertask_doupdate', '68');
INSERT INTO `admin_menu` VALUES ('72', '删除发生邮件', '/admin/mail/sendertask/dodel', null, '1', 'admin_mail_sendertask_dodel', '68');
INSERT INTO `admin_menu` VALUES ('73', '发送邮件', '/admin/mail/sendertask/dosend', null, '1', 'admin_mail_sendertask_dosend', '68');
INSERT INTO `admin_menu` VALUES ('74', '查看邮件发送情况', '/admin/mail/sendertask/detailpage', null, '1', 'admin_mail_sendertask_detailpage', '68');
INSERT INTO `admin_menu` VALUES ('75', '查看任务详情', '/admin/mail/sendertask/detail', null, '1', 'admin_mail_sendertask_dotail', '68');
INSERT INTO `admin_menu` VALUES ('76', '获取任务数据', '/admin/mail/sendertask/get', null, '1', 'admin_mail_sendertask_get', '68');
INSERT INTO `admin_menu` VALUES ('77', '分页查看有效的通讯录', '/admin/mail/addresslist/dopage', null, '1', 'admin_mail_addresslist_dopage_valid', '68');
INSERT INTO `admin_menu` VALUES ('78', '分页查看有效内容', '/admin/mail/content/dopage', null, '1', 'admin_mail_content_dopage_valid', '68');
INSERT INTO `admin_menu` VALUES ('79', '分页查看有效邮件模板', '/admin/mail/templete/dopage', null, '1', 'admin_mail_templete_dopage_valid', '45');
INSERT INTO `admin_menu` VALUES ('80', '产品管理', '', null, '0', '', '0');
INSERT INTO `admin_menu` VALUES ('81', '任务点卡管理', '/admin/products/point_card', '10.00', '0', '', '80');
INSERT INTO `admin_menu` VALUES ('82', '分页查看点卡', '/admin/products/point_card/dopage', null, '1', 'adminProductsPoint_cardDopage', '81');
INSERT INTO `admin_menu` VALUES ('83', '增加点卡', '/admin/products/point_card/doadd', null, '1', 'adminProductsPoint_cardDoadd', '81');
INSERT INTO `admin_menu` VALUES ('84', '修改点卡', '/admin/products/point_card/doupdate', null, '1', 'adminProductsPoint_cardDoupdate', '81');
INSERT INTO `admin_menu` VALUES ('85', '删除点卡', '/admin/products/point_card/dodel', null, '1', 'adminProductsPoint_cardDodel', '81');
INSERT INTO `admin_menu` VALUES ('86', '前台订单管理', '/admin/order', null, '0', '', '29');
INSERT INTO `admin_menu` VALUES ('87', '订单查看', '/admin/order/dopage', null, '1', 'admin_oeder_dopage', '86');
INSERT INTO `admin_menu` VALUES ('88', '订单详情', '/admin/order/detail', null, '1', 'admin_oeder_detail', '86');
INSERT INTO `admin_menu` VALUES ('89', '前台任务列表', '/admin/task', null, '0', '', '29');
INSERT INTO `admin_menu` VALUES ('90', '任务分页查看', '/admin/task/dopage', null, '1', 'admin_tasks_dopage', '89');
INSERT INTO `admin_menu` VALUES ('91', '任务详情', '/admin/task/detail', null, '1', 'admin_task_detail', '89');
INSERT INTO `admin_menu` VALUES ('92', '公司收支分页查看', '/admin/company_account/dopage', null, '1', 'admin_company_account_dopage', '41');
INSERT INTO `admin_menu` VALUES ('93', '故事管理', '', null, '0', '', '0');
INSERT INTO `admin_menu` VALUES ('94', '神话故事', '/admin/MYTH/index', null, '0', '', '93');
INSERT INTO `admin_menu` VALUES ('95', '增加神话故事', '/admin/MYTH/doadd', null, '1', 'admin_MYTH_doadd', '94');
INSERT INTO `admin_menu` VALUES ('96', '修改神话故事', '/admin/MYTH/doupdate', null, '1', 'admin_MYTH_doupdate', '94');
INSERT INTO `admin_menu` VALUES ('97', '设置神话故事状态', '/admin/MYTH/setstatus', null, '1', 'admin_MYTH_setstatus', '94');
INSERT INTO `admin_menu` VALUES ('98', '分页查看神话故事', '/admin/MYTH/dopage', null, '1', 'admin_MYTH_dopage', '94');
INSERT INTO `admin_menu` VALUES ('99', '神话故事详情', '/admin/MYTH/detail', null, '1', 'admin_MYTH_detail', '94');
INSERT INTO `admin_menu` VALUES ('100', '删除神话故事', '/admin/MYTH/delete', null, '1', 'admin_MYTH_delete', '94');

-- ----------------------------
-- Table structure for `admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('8', '系统管理员', '1');
INSERT INTO `admin_role` VALUES ('9', '技术管理组', '1');
INSERT INTO `admin_role` VALUES ('10', 'test', '4');
INSERT INTO `admin_role` VALUES ('11', 'test', '4');
INSERT INTO `admin_role` VALUES ('12', 'test', '4');
INSERT INTO `admin_role` VALUES ('13', 'test', '4');
INSERT INTO `admin_role` VALUES ('14', 'test', '4');
INSERT INTO `admin_role` VALUES ('15', '财务管理', '1');
INSERT INTO `admin_role` VALUES ('16', '主管', '1');

-- ----------------------------
-- Table structure for `admin_user`
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `real_name` varchar(60) NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', '01jiangwei01', '96e79218965eb72c92a549dd5a330112', '管理员', '1');
INSERT INTO `admin_user` VALUES ('2', 'lisuli', '96e79218965eb72c92a549dd5a330112', '李素丽', '1');
INSERT INTO `admin_user` VALUES ('3', 'lisuli', '96e79218965eb72c92a549dd5a330112', '李素丽', '4');
INSERT INTO `admin_user` VALUES ('4', 'l1', '96e79218965eb72c92a549dd5a330112', '李1', '1');
INSERT INTO `admin_user` VALUES ('5', 'l2', '96e79218965eb72c92a549dd5a330112', '李', '1');
INSERT INTO `admin_user` VALUES ('6', 'l3', null, '李三', '1');
INSERT INTO `admin_user` VALUES ('7', 'l4', '96e79218965eb72c92a549dd5a330112', '李4', '4');
INSERT INTO `admin_user` VALUES ('8', 'l5', '96e79218965eb72c92a549dd5a330112', '李4', '4');
INSERT INTO `admin_user` VALUES ('9', 'l6', '96e79218965eb72c92a549dd5a330112', '李4', '4');
INSERT INTO `admin_user` VALUES ('10', 'l7', '96e79218965eb72c92a549dd5a330112', '李7', '4');
INSERT INTO `admin_user` VALUES ('11', 'l8', '96e79218965eb72c92a549dd5a330112', '李7', '4');
INSERT INTO `admin_user` VALUES ('12', 'l9', '96e79218965eb72c92a549dd5a330112', '李9', '1');
INSERT INTO `admin_user` VALUES ('13', 'l8', '96e79218965eb72c92a549dd5a330112', '李8', '1');

-- ----------------------------
-- Table structure for `apply_draw_log`
-- ----------------------------
DROP TABLE IF EXISTS `apply_draw_log`;
CREATE TABLE `apply_draw_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `third_order_no` varchar(50) DEFAULT NULL,
  `amount` decimal(16,2) NOT NULL,
  `user_id` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `status` varchar(20) NOT NULL,
  `auditor_id` int(10) DEFAULT NULL,
  `auditor_name` varchar(30) DEFAULT NULL,
  `review_time` datetime DEFAULT NULL,
  `refuse_reason` varchar(100) DEFAULT NULL,
  `account_no` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply_draw_log
-- ----------------------------
INSERT INTO `apply_draw_log` VALUES ('1', '12345678901234512', '12.00', '2', '2015-04-13 14:14:09', 'APPROVE', '1', '管理员', '2015-04-13 14:15:17', null, '01jangwei01@163.com');

-- ----------------------------
-- Table structure for `business_exception`
-- ----------------------------
DROP TABLE IF EXISTS `business_exception`;
CREATE TABLE `business_exception` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `type` varchar(30) NOT NULL,
  `class_path` varchar(100) NOT NULL,
  `method_name` varchar(100) NOT NULL,
  `param_string` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business_exception
-- ----------------------------

-- ----------------------------
-- Table structure for `caozuoma_log`
-- ----------------------------
DROP TABLE IF EXISTS `caozuoma_log`;
CREATE TABLE `caozuoma_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  `value` varchar(50) NOT NULL,
  `code` varchar(8) NOT NULL,
  `create_time` datetime NOT NULL,
  `active_time` datetime DEFAULT NULL,
  `exp_time` datetime NOT NULL,
  `enabled` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of caozuoma_log
-- ----------------------------

-- ----------------------------
-- Table structure for `company_account`
-- ----------------------------
DROP TABLE IF EXISTS `company_account`;
CREATE TABLE `company_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `sell_points` double(10,2) NOT NULL,
  `sell_points_money` double(10,2) NOT NULL,
  `get_money` double(10,2) NOT NULL,
  `get_points` double(10,2) NOT NULL,
  `supply_points` double(10,2) NOT NULL,
  `deposit_money` double(10,2) NOT NULL,
  `draw_money` double(10,2) NOT NULL,
  `createtime` datetime NOT NULL,
  `reason_type` varchar(20) NOT NULL,
  `ref_id` int(10) DEFAULT NULL,
  `reason` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company_account
-- ----------------------------
INSERT INTO `company_account` VALUES ('1', '0.00', '0.00', '0.00', '0.00', '0.00', '200.00', '0.00', '2015-04-13 10:22:43', 'DEPOSIT', '1', '用户【1】充值【200.00】元');
INSERT INTO `company_account` VALUES ('2', '10.00', '10.00', '0.00', '0.00', '0.00', '200.00', '0.00', '2015-04-13 13:57:40', 'SellPoint', '1', '卖出【10】点,获得收入【10.00】元');
INSERT INTO `company_account` VALUES ('3', '10.00', '10.00', '0.00', '0.50', '0.00', '200.00', '0.00', '2015-04-13 13:58:31', 'ORDERSURE', '2', '订单确定，公司收入【0.50】点');
INSERT INTO `company_account` VALUES ('4', '10.00', '10.00', '0.00', '0.50', '0.00', '200.00', '12.00', '2015-04-13 14:15:17', 'DRAW', '1', '用户【2】取款【12.00】元');
INSERT INTO `company_account` VALUES ('5', '10.00', '10.00', '0.00', '1.00', '0.00', '200.00', '12.00', '2015-04-13 14:28:23', 'ORDERSURE', '3', '订单确定，公司收入【0.50】点');
INSERT INTO `company_account` VALUES ('6', '10.00', '10.00', '0.00', '2.00', '0.00', '200.00', '12.00', '2015-04-13 14:51:26', 'ORDERSURE', '4', '订单确定，公司收入【1.00】点');

-- ----------------------------
-- Table structure for `deposit_apply_log`
-- ----------------------------
DROP TABLE IF EXISTS `deposit_apply_log`;
CREATE TABLE `deposit_apply_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `third_order_no` varchar(50) NOT NULL,
  `amount` decimal(16,2) NOT NULL,
  `user_id` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `status` varchar(20) NOT NULL,
  `auditor_id` int(10) DEFAULT NULL,
  `auditor_name` varchar(30) DEFAULT NULL,
  `review_time` datetime DEFAULT NULL,
  `refuse_reason` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNo` (`third_order_no`) USING HASH,
  KEY `admin_user_id` (`auditor_id`),
  KEY `userId` (`user_id`) USING HASH,
  CONSTRAINT `admin_user_id` FOREIGN KEY (`auditor_id`) REFERENCES `admin_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deposit_apply_log
-- ----------------------------
INSERT INTO `deposit_apply_log` VALUES ('1', '123456789012345678', '200.00', '1', '2015-04-13 10:15:32', 'APPROVE', '1', '管理员', '2015-04-13 10:22:43', null);

-- ----------------------------
-- Table structure for `log4j_log`
-- ----------------------------
DROP TABLE IF EXISTS `log4j_log`;
CREATE TABLE `log4j_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class` varchar(150) DEFAULT NULL,
  `method` varchar(150) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `loglevel` varchar(20) DEFAULT NULL,
  `logmsg` varchar(1024) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log4j_log
-- ----------------------------

-- ----------------------------
-- Table structure for `log_info`
-- ----------------------------
DROP TABLE IF EXISTS `log_info`;
CREATE TABLE `log_info` (
  `id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `log_type` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_info
-- ----------------------------

-- ----------------------------
-- Table structure for `mail_address_list`
-- ----------------------------
DROP TABLE IF EXISTS `mail_address_list`;
CREATE TABLE `mail_address_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `status` varchar(30) NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `crete_time` datetime NOT NULL,
  `come_from` varchar(50) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_address_list
-- ----------------------------
INSERT INTO `mail_address_list` VALUES ('1', 'xu', '01jiangwei01@163.com', 'DEL', '1', '2015-01-17 17:12:04', 'QQ', 'male');
INSERT INTO `mail_address_list` VALUES ('2', '1', '01jiangwei01@163.com', 'NORMAL', '1', '2015-01-17 17:25:36', 'QQ', 'female');
INSERT INTO `mail_address_list` VALUES ('3', '12', 'ee@163.com', 'NORMAL', '1', '2015-01-17 17:07:46', 'QQ', 'male');
INSERT INTO `mail_address_list` VALUES ('4', '赵祥', 'zhaoxiang@ucredit.com', 'NORMAL', '1', '2015-01-21 19:12:37', 'QQ', 'male');

-- ----------------------------
-- Table structure for `mail_content`
-- ----------------------------
DROP TABLE IF EXISTS `mail_content`;
CREATE TABLE `mail_content` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `templete_id` int(10) NOT NULL,
  `content` longtext,
  `title` varchar(100) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `status` varchar(50) NOT NULL,
  `updateUserId` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_content
-- ----------------------------
INSERT INTO `mail_content` VALUES ('1', '1', '内容1', '标题1-1', '2014-12-24 19:23:02', 'DELETE', '1');
INSERT INTO `mail_content` VALUES ('2', '1', '内容2', '标题2-1', '2014-12-24 19:43:50', 'DELETE', '1');
INSERT INTO `mail_content` VALUES ('3', '1', '<p>内容3<img alt=\"\" src=\"http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150116174648995.jpg\" style=\"height:911px; width:610px\" /></p>\n', '标题3', '2015-01-16 17:49:46', 'NORMAL', '1');
INSERT INTO `mail_content` VALUES ('4', '1', '<p>内容5555</p>\n', '标题4', '2015-01-06 18:52:58', 'NORMAL', '1');
INSERT INTO `mail_content` VALUES ('5', '1', '<p>欢迎注册聚来宝</p>\n', '聚来宝注册', '2015-01-06 18:53:13', 'NORMAL', '1');

-- ----------------------------
-- Table structure for `mail_sender_ref_address_list`
-- ----------------------------
DROP TABLE IF EXISTS `mail_sender_ref_address_list`;
CREATE TABLE `mail_sender_ref_address_list` (
  `id` int(11) NOT NULL,
  `mail_sender_task_id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mail_address_list_id` int(11) NOT NULL,
  `emai_user_name` varchar(50) NOT NULL,
  `gender` char(10) NOT NULL,
  `status` char(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_sender_ref_address_list
-- ----------------------------
INSERT INTO `mail_sender_ref_address_list` VALUES ('3', '2', '01jiangwei01@163.com', '2', '1', 'female', 'SUCCESS');
INSERT INTO `mail_sender_ref_address_list` VALUES ('4', '2', 'ee@163.com', '3', '12', 'male', 'SUCCESS');
INSERT INTO `mail_sender_ref_address_list` VALUES ('5', '3', '01jiangwei01@163.com', '2', '1', 'female', 'SUCCESS');
INSERT INTO `mail_sender_ref_address_list` VALUES ('6', '3', 'ee@163.com', '3', '12', 'male', 'SUCCESS');
INSERT INTO `mail_sender_ref_address_list` VALUES ('7', '4', '01jiangwei01@163.com', '2', '1', 'female', 'SUCCESS');
INSERT INTO `mail_sender_ref_address_list` VALUES ('8', '4', 'ee@163.com', '3', '12', 'male', 'SUCCESS');
INSERT INTO `mail_sender_ref_address_list` VALUES ('9', '5', '01jiangwei01@163.com', '2', '1', 'female', 'SUCCESS');
INSERT INTO `mail_sender_ref_address_list` VALUES ('13', '6', 'zhaoxiang@ucredit.com', '4', '赵祥', 'male', 'SUCCESS');

-- ----------------------------
-- Table structure for `mail_sender_task`
-- ----------------------------
DROP TABLE IF EXISTS `mail_sender_task`;
CREATE TABLE `mail_sender_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_user` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` longtext NOT NULL,
  `status` varchar(20) NOT NULL,
  `content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_sender_task
-- ----------------------------
INSERT INTO `mail_sender_task` VALUES ('2', '1', '2015-01-21 11:15:38', '聚来宝注册', '<p>欢迎注册聚来宝</p>\n', 'ISEXECUTED', '1');
INSERT INTO `mail_sender_task` VALUES ('3', '1', '2015-01-21 11:16:30', '聚来宝注册', '<p>欢迎注册聚来宝</p>\n', 'ISEXECUTED', '1');
INSERT INTO `mail_sender_task` VALUES ('4', '1', '2015-01-21 11:24:42', '标题4', '<p>内容5555</p>\n', 'ISEXECUTED', '1');
INSERT INTO `mail_sender_task` VALUES ('5', '1', '2015-01-21 14:14:36', '标题3', '<p>内容3<img alt=\"\" src=\"http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150116174648995.jpg\" style=\"height:911px; width:610px\" /></p>\n', 'ISEXECUTED', '1');
INSERT INTO `mail_sender_task` VALUES ('6', '1', '2015-01-21 19:13:24', '标题3', '<p>内容3<img alt=\"\" src=\"http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150116174648995.jpg\" style=\"height:911px; width:610px\" /></p>\n', 'ISEXECUTED', '3');

-- ----------------------------
-- Table structure for `mail_templete`
-- ----------------------------
DROP TABLE IF EXISTS `mail_templete`;
CREATE TABLE `mail_templete` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `templete_name` varchar(100) NOT NULL,
  `templete_path` varchar(100) NOT NULL,
  `update_time` datetime NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `updateUserId` int(10) DEFAULT NULL,
  `templete_type` varchar(100) DEFAULT NULL,
  `templete_desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mail_templete
-- ----------------------------
INSERT INTO `mail_templete` VALUES ('1', '测试模板', '/templete/mail/v1', '2015-01-06 13:44:31', 'NORMAL', '1', 'JU_LAI_BAO_TUI_GUANG_V1', '1');
INSERT INTO `mail_templete` VALUES ('2', '模板2', '/admin/123233', '2014-12-24 19:44:25', 'DELETE', '1', null, null);

-- ----------------------------
-- Table structure for `notifications`
-- ----------------------------
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notifications
-- ----------------------------

-- ----------------------------
-- Table structure for `operate_log`
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `operate_time` datetime NOT NULL,
  `operate_type` varchar(50) NOT NULL,
  `before_value` varchar(100) DEFAULT NULL,
  `after_value` varchar(100) NOT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `isused` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operate_log
-- ----------------------------
INSERT INTO `operate_log` VALUES ('1', '2', '2015-03-13 22:31:23', 'REG_EMAIL', null, '02jiangwei02@163.com', null, '0');
INSERT INTO `operate_log` VALUES ('2', '3', '2015-03-16 22:15:43', 'ACTIVE_PHONE', null, '15210578828', null, '0');

-- ----------------------------
-- Table structure for `pics`
-- ----------------------------
DROP TABLE IF EXISTS `pics`;
CREATE TABLE `pics` (
  `id` int(11) NOT NULL DEFAULT '0',
  `pic_path` varchar(200) NOT NULL,
  `status` varchar(20) NOT NULL,
  `pic_name` varchar(100) NOT NULL,
  `pic_desc` varchar(400) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_user` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pics
-- ----------------------------
INSERT INTO `pics` VALUES ('3', 'http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150116174648995.jpg', 'NORMAL', '阿里测试', '阿里测试', '2015-01-16 17:46:49', '1');

-- ----------------------------
-- Table structure for `point_card`
-- ----------------------------
DROP TABLE IF EXISTS `point_card`;
CREATE TABLE `point_card` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `points` int(10) NOT NULL,
  `money` double(10,2) NOT NULL,
  `title` varchar(50) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `orders` double(10,0) DEFAULT '0',
  `status` varchar(10) NOT NULL,
  `admin_user_id` int(11) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of point_card
-- ----------------------------
INSERT INTO `point_card` VALUES ('1', '10', '10.00', '10元卡', null, '99', 'NORMAL', '0', '2015-02-27 14:12:33');
INSERT INTO `point_card` VALUES ('2', '55', '50.00', '50元卡', null, '98', 'NORMAL', '1', '2015-02-27 15:15:00');
INSERT INTO `point_card` VALUES ('3', '120', '100.00', '100元卡', null, '97', 'NORMAL', '1', '2015-02-27 15:16:03');
INSERT INTO `point_card` VALUES ('4', '1100', '1000.00', '1000元卡', null, '100', 'DELERATE', '1', '2015-02-27 15:16:38');
INSERT INTO `point_card` VALUES ('5', '100', '100.00', '1000', null, '100', 'DELERATE', '1', '2015-02-27 15:16:59');

-- ----------------------------
-- Table structure for `rel_admin_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `rel_admin_user_role`;
CREATE TABLE `rel_admin_user_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `admin_user_id` int(10) DEFAULT NULL,
  `admin_role_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_admin_user_role
-- ----------------------------
INSERT INTO `rel_admin_user_role` VALUES ('8', '1', '8');
INSERT INTO `rel_admin_user_role` VALUES ('9', '2', '16');
INSERT INTO `rel_admin_user_role` VALUES ('10', '3', '16');
INSERT INTO `rel_admin_user_role` VALUES ('11', '6', '9');
INSERT INTO `rel_admin_user_role` VALUES ('12', '7', '15');
INSERT INTO `rel_admin_user_role` VALUES ('13', '8', '15');
INSERT INTO `rel_admin_user_role` VALUES ('14', '9', '15');
INSERT INTO `rel_admin_user_role` VALUES ('15', '10', '16');
INSERT INTO `rel_admin_user_role` VALUES ('16', '11', '16');

-- ----------------------------
-- Table structure for `rel_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `rel_role_menu`;
CREATE TABLE `rel_role_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `roleid` int(10) DEFAULT NULL,
  `menuid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=460 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_role_menu
-- ----------------------------
INSERT INTO `rel_role_menu` VALUES ('20', '10', '19');
INSERT INTO `rel_role_menu` VALUES ('21', '11', '19');
INSERT INTO `rel_role_menu` VALUES ('22', '12', '19');
INSERT INTO `rel_role_menu` VALUES ('23', '13', '19');
INSERT INTO `rel_role_menu` VALUES ('24', '14', '19');
INSERT INTO `rel_role_menu` VALUES ('131', '9', '19');
INSERT INTO `rel_role_menu` VALUES ('132', '9', '20');
INSERT INTO `rel_role_menu` VALUES ('133', '9', '23');
INSERT INTO `rel_role_menu` VALUES ('134', '9', '26');
INSERT INTO `rel_role_menu` VALUES ('135', '9', '27');
INSERT INTO `rel_role_menu` VALUES ('136', '9', '21');
INSERT INTO `rel_role_menu` VALUES ('137', '9', '22');
INSERT INTO `rel_role_menu` VALUES ('138', '9', '24');
INSERT INTO `rel_role_menu` VALUES ('139', '9', '25');
INSERT INTO `rel_role_menu` VALUES ('140', '9', '1');
INSERT INTO `rel_role_menu` VALUES ('141', '9', '2');
INSERT INTO `rel_role_menu` VALUES ('142', '9', '9');
INSERT INTO `rel_role_menu` VALUES ('143', '9', '10');
INSERT INTO `rel_role_menu` VALUES ('144', '9', '11');
INSERT INTO `rel_role_menu` VALUES ('145', '9', '12');
INSERT INTO `rel_role_menu` VALUES ('146', '9', '3');
INSERT INTO `rel_role_menu` VALUES ('147', '9', '13');
INSERT INTO `rel_role_menu` VALUES ('148', '9', '14');
INSERT INTO `rel_role_menu` VALUES ('149', '9', '15');
INSERT INTO `rel_role_menu` VALUES ('150', '9', '16');
INSERT INTO `rel_role_menu` VALUES ('151', '9', '17');
INSERT INTO `rel_role_menu` VALUES ('152', '9', '4');
INSERT INTO `rel_role_menu` VALUES ('153', '9', '5');
INSERT INTO `rel_role_menu` VALUES ('154', '9', '6');
INSERT INTO `rel_role_menu` VALUES ('155', '9', '7');
INSERT INTO `rel_role_menu` VALUES ('156', '9', '8');
INSERT INTO `rel_role_menu` VALUES ('157', '9', '18');
INSERT INTO `rel_role_menu` VALUES ('158', '15', '19');
INSERT INTO `rel_role_menu` VALUES ('159', '15', '20');
INSERT INTO `rel_role_menu` VALUES ('160', '15', '23');
INSERT INTO `rel_role_menu` VALUES ('161', '15', '26');
INSERT INTO `rel_role_menu` VALUES ('162', '15', '27');
INSERT INTO `rel_role_menu` VALUES ('163', '15', '21');
INSERT INTO `rel_role_menu` VALUES ('164', '15', '22');
INSERT INTO `rel_role_menu` VALUES ('165', '15', '24');
INSERT INTO `rel_role_menu` VALUES ('166', '15', '25');
INSERT INTO `rel_role_menu` VALUES ('307', '16', '19');
INSERT INTO `rel_role_menu` VALUES ('308', '16', '20');
INSERT INTO `rel_role_menu` VALUES ('309', '16', '23');
INSERT INTO `rel_role_menu` VALUES ('310', '16', '26');
INSERT INTO `rel_role_menu` VALUES ('311', '16', '27');
INSERT INTO `rel_role_menu` VALUES ('312', '16', '21');
INSERT INTO `rel_role_menu` VALUES ('313', '16', '22');
INSERT INTO `rel_role_menu` VALUES ('314', '16', '24');
INSERT INTO `rel_role_menu` VALUES ('315', '16', '25');
INSERT INTO `rel_role_menu` VALUES ('316', '16', '41');
INSERT INTO `rel_role_menu` VALUES ('317', '16', '92');
INSERT INTO `rel_role_menu` VALUES ('318', '16', '29');
INSERT INTO `rel_role_menu` VALUES ('319', '16', '28');
INSERT INTO `rel_role_menu` VALUES ('320', '16', '30');
INSERT INTO `rel_role_menu` VALUES ('321', '16', '86');
INSERT INTO `rel_role_menu` VALUES ('322', '16', '87');
INSERT INTO `rel_role_menu` VALUES ('323', '16', '88');
INSERT INTO `rel_role_menu` VALUES ('324', '16', '89');
INSERT INTO `rel_role_menu` VALUES ('325', '16', '90');
INSERT INTO `rel_role_menu` VALUES ('326', '16', '91');
INSERT INTO `rel_role_menu` VALUES ('327', '16', '42');
INSERT INTO `rel_role_menu` VALUES ('328', '16', '43');
INSERT INTO `rel_role_menu` VALUES ('329', '16', '44');
INSERT INTO `rel_role_menu` VALUES ('330', '16', '46');
INSERT INTO `rel_role_menu` VALUES ('331', '16', '47');
INSERT INTO `rel_role_menu` VALUES ('332', '16', '48');
INSERT INTO `rel_role_menu` VALUES ('333', '16', '49');
INSERT INTO `rel_role_menu` VALUES ('334', '16', '55');
INSERT INTO `rel_role_menu` VALUES ('335', '16', '45');
INSERT INTO `rel_role_menu` VALUES ('336', '16', '50');
INSERT INTO `rel_role_menu` VALUES ('337', '16', '51');
INSERT INTO `rel_role_menu` VALUES ('338', '16', '52');
INSERT INTO `rel_role_menu` VALUES ('339', '16', '53');
INSERT INTO `rel_role_menu` VALUES ('340', '16', '54');
INSERT INTO `rel_role_menu` VALUES ('341', '16', '79');
INSERT INTO `rel_role_menu` VALUES ('342', '16', '61');
INSERT INTO `rel_role_menu` VALUES ('343', '16', '62');
INSERT INTO `rel_role_menu` VALUES ('344', '16', '65');
INSERT INTO `rel_role_menu` VALUES ('345', '16', '66');
INSERT INTO `rel_role_menu` VALUES ('346', '16', '67');
INSERT INTO `rel_role_menu` VALUES ('347', '16', '68');
INSERT INTO `rel_role_menu` VALUES ('348', '16', '69');
INSERT INTO `rel_role_menu` VALUES ('349', '16', '70');
INSERT INTO `rel_role_menu` VALUES ('350', '16', '71');
INSERT INTO `rel_role_menu` VALUES ('351', '16', '72');
INSERT INTO `rel_role_menu` VALUES ('352', '16', '73');
INSERT INTO `rel_role_menu` VALUES ('353', '16', '74');
INSERT INTO `rel_role_menu` VALUES ('354', '16', '75');
INSERT INTO `rel_role_menu` VALUES ('355', '16', '76');
INSERT INTO `rel_role_menu` VALUES ('356', '16', '77');
INSERT INTO `rel_role_menu` VALUES ('357', '16', '78');
INSERT INTO `rel_role_menu` VALUES ('358', '16', '56');
INSERT INTO `rel_role_menu` VALUES ('359', '16', '57');
INSERT INTO `rel_role_menu` VALUES ('360', '16', '58');
INSERT INTO `rel_role_menu` VALUES ('361', '16', '59');
INSERT INTO `rel_role_menu` VALUES ('362', '16', '60');
INSERT INTO `rel_role_menu` VALUES ('363', '16', '80');
INSERT INTO `rel_role_menu` VALUES ('364', '16', '81');
INSERT INTO `rel_role_menu` VALUES ('365', '16', '82');
INSERT INTO `rel_role_menu` VALUES ('366', '16', '1');
INSERT INTO `rel_role_menu` VALUES ('367', '16', '4');
INSERT INTO `rel_role_menu` VALUES ('368', '16', '8');
INSERT INTO `rel_role_menu` VALUES ('369', '8', '19');
INSERT INTO `rel_role_menu` VALUES ('370', '8', '20');
INSERT INTO `rel_role_menu` VALUES ('371', '8', '23');
INSERT INTO `rel_role_menu` VALUES ('372', '8', '26');
INSERT INTO `rel_role_menu` VALUES ('373', '8', '27');
INSERT INTO `rel_role_menu` VALUES ('374', '8', '21');
INSERT INTO `rel_role_menu` VALUES ('375', '8', '22');
INSERT INTO `rel_role_menu` VALUES ('376', '8', '24');
INSERT INTO `rel_role_menu` VALUES ('377', '8', '25');
INSERT INTO `rel_role_menu` VALUES ('378', '8', '39');
INSERT INTO `rel_role_menu` VALUES ('379', '8', '40');
INSERT INTO `rel_role_menu` VALUES ('380', '8', '41');
INSERT INTO `rel_role_menu` VALUES ('381', '8', '92');
INSERT INTO `rel_role_menu` VALUES ('382', '8', '29');
INSERT INTO `rel_role_menu` VALUES ('383', '8', '28');
INSERT INTO `rel_role_menu` VALUES ('384', '8', '30');
INSERT INTO `rel_role_menu` VALUES ('385', '8', '32');
INSERT INTO `rel_role_menu` VALUES ('386', '8', '86');
INSERT INTO `rel_role_menu` VALUES ('387', '8', '87');
INSERT INTO `rel_role_menu` VALUES ('388', '8', '88');
INSERT INTO `rel_role_menu` VALUES ('389', '8', '89');
INSERT INTO `rel_role_menu` VALUES ('390', '8', '90');
INSERT INTO `rel_role_menu` VALUES ('391', '8', '91');
INSERT INTO `rel_role_menu` VALUES ('392', '8', '42');
INSERT INTO `rel_role_menu` VALUES ('393', '8', '43');
INSERT INTO `rel_role_menu` VALUES ('394', '8', '44');
INSERT INTO `rel_role_menu` VALUES ('395', '8', '46');
INSERT INTO `rel_role_menu` VALUES ('396', '8', '47');
INSERT INTO `rel_role_menu` VALUES ('397', '8', '48');
INSERT INTO `rel_role_menu` VALUES ('398', '8', '49');
INSERT INTO `rel_role_menu` VALUES ('399', '8', '55');
INSERT INTO `rel_role_menu` VALUES ('400', '8', '45');
INSERT INTO `rel_role_menu` VALUES ('401', '8', '50');
INSERT INTO `rel_role_menu` VALUES ('402', '8', '51');
INSERT INTO `rel_role_menu` VALUES ('403', '8', '52');
INSERT INTO `rel_role_menu` VALUES ('404', '8', '53');
INSERT INTO `rel_role_menu` VALUES ('405', '8', '54');
INSERT INTO `rel_role_menu` VALUES ('406', '8', '79');
INSERT INTO `rel_role_menu` VALUES ('407', '8', '61');
INSERT INTO `rel_role_menu` VALUES ('408', '8', '62');
INSERT INTO `rel_role_menu` VALUES ('409', '8', '65');
INSERT INTO `rel_role_menu` VALUES ('410', '8', '66');
INSERT INTO `rel_role_menu` VALUES ('411', '8', '67');
INSERT INTO `rel_role_menu` VALUES ('412', '8', '68');
INSERT INTO `rel_role_menu` VALUES ('413', '8', '69');
INSERT INTO `rel_role_menu` VALUES ('414', '8', '70');
INSERT INTO `rel_role_menu` VALUES ('415', '8', '71');
INSERT INTO `rel_role_menu` VALUES ('416', '8', '72');
INSERT INTO `rel_role_menu` VALUES ('417', '8', '73');
INSERT INTO `rel_role_menu` VALUES ('418', '8', '74');
INSERT INTO `rel_role_menu` VALUES ('419', '8', '75');
INSERT INTO `rel_role_menu` VALUES ('420', '8', '76');
INSERT INTO `rel_role_menu` VALUES ('421', '8', '77');
INSERT INTO `rel_role_menu` VALUES ('422', '8', '78');
INSERT INTO `rel_role_menu` VALUES ('423', '8', '56');
INSERT INTO `rel_role_menu` VALUES ('424', '8', '57');
INSERT INTO `rel_role_menu` VALUES ('425', '8', '58');
INSERT INTO `rel_role_menu` VALUES ('426', '8', '59');
INSERT INTO `rel_role_menu` VALUES ('427', '8', '60');
INSERT INTO `rel_role_menu` VALUES ('428', '8', '80');
INSERT INTO `rel_role_menu` VALUES ('429', '8', '81');
INSERT INTO `rel_role_menu` VALUES ('430', '8', '82');
INSERT INTO `rel_role_menu` VALUES ('431', '8', '83');
INSERT INTO `rel_role_menu` VALUES ('432', '8', '84');
INSERT INTO `rel_role_menu` VALUES ('433', '8', '85');
INSERT INTO `rel_role_menu` VALUES ('434', '8', '93');
INSERT INTO `rel_role_menu` VALUES ('435', '8', '94');
INSERT INTO `rel_role_menu` VALUES ('436', '8', '95');
INSERT INTO `rel_role_menu` VALUES ('437', '8', '96');
INSERT INTO `rel_role_menu` VALUES ('438', '8', '97');
INSERT INTO `rel_role_menu` VALUES ('439', '8', '98');
INSERT INTO `rel_role_menu` VALUES ('440', '8', '99');
INSERT INTO `rel_role_menu` VALUES ('441', '8', '100');
INSERT INTO `rel_role_menu` VALUES ('442', '8', '1');
INSERT INTO `rel_role_menu` VALUES ('443', '8', '2');
INSERT INTO `rel_role_menu` VALUES ('444', '8', '9');
INSERT INTO `rel_role_menu` VALUES ('445', '8', '10');
INSERT INTO `rel_role_menu` VALUES ('446', '8', '11');
INSERT INTO `rel_role_menu` VALUES ('447', '8', '12');
INSERT INTO `rel_role_menu` VALUES ('448', '8', '3');
INSERT INTO `rel_role_menu` VALUES ('449', '8', '13');
INSERT INTO `rel_role_menu` VALUES ('450', '8', '14');
INSERT INTO `rel_role_menu` VALUES ('451', '8', '15');
INSERT INTO `rel_role_menu` VALUES ('452', '8', '16');
INSERT INTO `rel_role_menu` VALUES ('453', '8', '17');
INSERT INTO `rel_role_menu` VALUES ('454', '8', '4');
INSERT INTO `rel_role_menu` VALUES ('455', '8', '5');
INSERT INTO `rel_role_menu` VALUES ('456', '8', '6');
INSERT INTO `rel_role_menu` VALUES ('457', '8', '7');
INSERT INTO `rel_role_menu` VALUES ('458', '8', '8');
INSERT INTO `rel_role_menu` VALUES ('459', '8', '18');

-- ----------------------------
-- Table structure for `story_article`
-- ----------------------------
DROP TABLE IF EXISTS `story_article`;
CREATE TABLE `story_article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_title` varchar(128) NOT NULL,
  `add_time` datetime NOT NULL,
  `add_user_id` int(11) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_user_id` int(11) NOT NULL,
  `story_time` varchar(64) NOT NULL COMMENT '故事发生时间',
  `story_summary` varchar(256) NOT NULL,
  `hit_times` int(11) DEFAULT '0' COMMENT '浏览数',
  `praise_number` int(11) DEFAULT '0' COMMENT '喜欢这篇文章的人数',
  `tiresome_number` int(11) DEFAULT '0' COMMENT '讨厌这篇文章的人数',
  `from_book_name` varchar(256) DEFAULT NULL COMMENT '出自哪本书',
  `status` varchar(16) NOT NULL COMMENT '状态',
  `type` varchar(16) NOT NULL,
  PRIMARY KEY (`article_id`),
  KEY `story_type` (`type`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of story_article
-- ----------------------------
INSERT INTO `story_article` VALUES ('1', '盘古开天', '2015-07-23 15:03:23', '1', '2015-07-23 15:03:28', '1', '上古时期', '传说混沌未开的时候，在混沌的中央，有一块浑圆就像是一枚鸡蛋。这块浑圆里有着一个巨人。', '0', '0', '0', '三五历纪、山海经、广博物志等', 'NORMAL', 'MYTH');
INSERT INTO `story_article` VALUES ('2', '女娲造人', '2015-07-23 15:07:06', '1', '2015-07-23 15:07:50', '1', '上古时期', '在盘古肉身化为大地之后，大地上出现了很多种生物．多了很多年之后。。。', '1', '0', '0', '山海经', 'NORMAL', 'MYTH');
INSERT INTO `story_article` VALUES ('3', '炎帝击石生火', '2015-07-23 15:09:12', '1', '2015-07-23 15:09:17', '1', '上古时期', '人类使用最早的火全都是天然火（大多是雷电击在大树上所产生的火）．有个人便想人类总不能一直依靠天然火吧。。。', '0', '0', '0', '山海经', 'NORMAL', 'MYTH');
INSERT INTO `story_article` VALUES ('4', '神农试百草', '2015-07-23 15:09:52', '1', '2015-07-23 15:09:57', '1', '上古时期', '生老病死是最自然不过的事了，不过因为”病”而早早离开这个世界的人又实在值得同情', '0', '0', '0', '山海经', 'NORMAL', 'MYTH');
INSERT INTO `story_article` VALUES ('5', '精卫填海', '2015-07-23 15:10:50', '1', '2015-07-23 15:10:54', '1', '上古时期', '炎帝有一个善良，可爱的小女儿，名字叫精卫．她从小就有一个梦想，....', '0', '0', '0', '山海经', 'NORMAL', 'MYTH');
INSERT INTO `story_article` VALUES ('6', '刑天舞干戈', '2015-07-23 15:12:01', '1', '2015-07-23 15:12:05', '1', '上古时期', '义和起兵造反，炎帝被困荒山之中．兵变的事被炎帝最忠诚的战将刑天知道后。。。', '0', '0', '0', '山海经', 'NORMAL', 'MYTH');
INSERT INTO `story_article` VALUES ('7', '黄帝战蚩尤', '2015-07-23 15:13:00', '1', '2015-07-23 15:13:03', '1', '上古时期', '华夏被分为五个大部落。这五个部落中以黄帝和蚩尤的部落势力最大', '0', '0', '0', '山海经', 'NORMAL', 'MYTH');
INSERT INTO `story_article` VALUES ('8', '共工怒触不周山', '2015-07-23 15:14:36', '1', '2015-07-23 15:14:41', '1', '上古时期', '共工一头撞向不周仙山。。。。', '0', '0', '0', '山海经', 'NORMAL', 'MYTH');
INSERT INTO `story_article` VALUES ('9', '女娲补天', '2015-07-23 15:15:38', '1', '2015-07-23 15:15:42', '1', '上古时期', '女娲四处收集五色石，再以五色石炼制成补天石', '0', '0', '0', '山海经', 'NORMAL', 'MYTH');
INSERT INTO `story_article` VALUES ('10', '后羿射日', '2015-07-23 15:16:48', '1', '2015-07-23 15:16:53', '1', '上古时期', '天上同时出现十个太阳．后羿为解决大旱问题，于是。。。。', '1', '1', '0', '山海经', 'NORMAL', 'MYTH');

-- ----------------------------
-- Table structure for `story_article_desc`
-- ----------------------------
DROP TABLE IF EXISTS `story_article_desc`;
CREATE TABLE `story_article_desc` (
  `article_id` int(11) DEFAULT NULL,
  `article_content` longtext,
  KEY `shenhua_id` (`article_id`),
  CONSTRAINT `shenhua_id` FOREIGN KEY (`article_id`) REFERENCES `story_article` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of story_article_desc
-- ----------------------------
INSERT INTO `story_article_desc` VALUES ('1', '<div>话说最早的天地本是合在一起的，宇宙本是一团混沌。在这片混沌中沉睡着一个人，他的名字叫做盘古。</div>\n\n<div>他的身边放着一把样子古怪的斧头。直到有一天盘古忽然醒来，但是觉得自己所在的空间是那么的压抑，于是拿起身旁的斧头对着周围的空间一气乱挥，也不知道挥了多久，终于把天和地给明确的划分出来了。但是天与地分开没多久就又开始慢慢合拢，盘古就用自己的身体来支撑着天与地，就这样支撑了很久很久，天与地的位置已经被固定住了。但是伟大的盘古却因疲劳过度，累死了。他死后身体开始变化，骨骼变成了山脉和丘陵，肉身变成了平原和盆地，血和汗水变成了江海，毛发变成了森林，草原和沼泽。他的元神到了天与地的中心处，却因疲倦睡去。</div>\n');
INSERT INTO `story_article_desc` VALUES ('2', '<div>&nbsp; 在盘古肉身化为大地之后，大地上出现了很多种生物．多了很多年之后，大地上出现了一位同样伟大的人物－－女娲．女娲发现大地虽然有了很多生灵，但是它们缺少着创造能力，于是女娲便用黄泥按照自己的摸样捏出了很多小人，因为女娲觉得创造的这些人应该和自己有点区别才是，所以把泥人的尾巴改成了两条后肢（双足）。之后用施放了法术将这些小人变成有生命的活人，并且这些人都具有自己独立的思想。</div>\n');
INSERT INTO `story_article_desc` VALUES ('3', '<div>&nbsp; 人类使用最早的火全都是天然火（大多是雷电击在大树上所产生的火）．有个人便想人类总不能一直依靠天然火吧，所以决定自己要创造一种取火的方法，在一次狩猎中，他一失手未将石制的标枪命中猎物，不过他发现标枪与地上的石头相击时产生了火花，于是他就捡了很多那里的石头来试验，结果成功的取得了火．也因为他发现了取火的方法，所以各个部落都一致拥他为王，这个人就是以火德为王的炎帝．之后他又创造了耕种方法并且教会了人类耕种，所以他又被称为神农。</div>\n');
INSERT INTO `story_article_desc` VALUES ('4', '<div>生老病死是最自然不过的事了，不过因为&rdquo;病&rdquo;而早早离开这个世界的人又实在值得同情．神农觉得自己有保护好自己的子民的职责，所以他踏遍神州大地寻找治疗病患的药草，找到后他都先自己服用来确定有用与否或者有毒与否．最后终于找到了可以治疗很多常见病患的药草．神农试百草：生老病死是最自然不过的事了，不过因为&rdquo;病&rdquo;而早早离开这个世界的人又实在值得同情．神农觉得自己有保护好自己的子民的职责，所以他踏遍神州大地寻找治疗病患的药草，找到后他都先自己服用来确定有用与否或者有毒与否．最后终于找到了可以治疗很多常见病患的药草。</div>\n');
INSERT INTO `story_article_desc` VALUES ('5', '<div>炎帝有一个善良，可爱的小女儿，名字叫精卫．她从小就有一个梦想，希望见道无边的大海．小女孩一天天张大，也一天比一天漂亮．终于在她认为自己有能力找到大海的时候，她悄悄的离开了部落．最终她找到了大海，她为大海的伟大而陶醉，正当她奔向大海的时候，大海同样卷着巨浪奔向精卫．最后，大海把精卫带走了搜索，带着她离开了这个世界．精卫死前那一刻才醒悟过来．精卫的灵魂因为愤怒而化做一只青鸟，精卫为了报复大海，决定把大海填平，于是就日以继夜的含着西山的石头去填海。</div>\n');
INSERT INTO `story_article_desc` VALUES ('6', '<div>炎帝得知心爱的小女儿死去的消息，悲痛的得了一种奇怪的病，全身无力，这件事被早想篡位的义和知道后，义和便起兵造反，炎帝被困荒山之中．兵变的事被炎帝最忠诚的战将刑天知道后，刑天立即带兵前往平乱．刑天的部下虽然勇猛，但是义和兵众实在太多，刑天的部队全部被灭．刑天一怒之下以自己的血祭战戈，战戈得饮主人之血不但变的锋利无比，而且威力无边．刑天凭一人一戈将数万叛军悉数杀尽，义和也死在了刑天的战戈下．刑天成功的救下了炎帝，但是自己却因身受多处致命伤，最终全身血淋淋的站着死去。</div>\n');
INSERT INTO `story_article_desc` VALUES ('7', '<div>也许应验了那句话，天下分久必合，合久必分。华夏大地陷入了分裂的局面，战事连连，百姓生活在水生火热之中。华夏被分为五个大部落。这五个部落中以黄帝和蚩尤的部落势力最大，黄帝因不愿看到百姓因战争而受苦，便想停止这种战争局面，要想叫无休止的战争停止谈何容易？最后只得使用以暴制暴的方式来解决．先后分别消灭并收服了那三个较弱的部落。最后集中全力在逐鹿与蚩尤展开决战。蚩尤兵败逃跑。黄帝命应龙（应龙：龙族的一类，长着翅膀的龙．在机缘巧合下被黄帝收服。）前往追杀。应龙不负期望将蚩尤杀死并割下蚩尤的头颅献给黄帝</div>\n');
INSERT INTO `story_article_desc` VALUES ('8', '<div>黄帝战胜蚩尤多年以后，黄帝的后裔颛顼登位。颛顼颁布了众多法典将华夏大地治理的很繁荣。法典给大部分人带来了利益，但是也侵害一小部分人的利益。共工就是被侵害的最严重一人，他纠结了很多人一起起兵造反。一路攻城掠地所向无敌，直逼不周山（王城所在）。祝融身为大将，带兵下山迎战。祝融武艺与法术厉害无比，而带兵之道更是远胜与共工，共工一方全军覆没。仅剩共工一人。共工宁死不投降，最后盛怒之下一头撞向不周仙山．共工法力何等之强？这一撞可不得了，竟将不周山撞翻。这不周山不仅仅是王城所在，也是擎天柱．天门敞开，洪水泛滥，生灵涂炭。</div>\n');
INSERT INTO `story_article_desc` VALUES ('9', '<div>&nbsp; &nbsp; 共工撞翻不周山，洞开天门的事被女娲测算到后便从九重天下凡来制止来自天门的洪水。女娲四处收集五色石，再以五色石炼制成补天石，用补天石将天门补上。</div>\n');
INSERT INTO `story_article_desc` VALUES ('10', '<div>尧帝在位时，天下大旱，原因很简单，天上同时出现十个太阳．后羿为解决大旱问题，自己锻炼出一把威力无限的巨弓．后羿在弓上注入法力．然后携弓登上高山，以自己法力凝聚成无形之箭，引弓射日．本想把十个太阳全射下来，但是想到如果全都射下来，大地将陷入无止境的黑暗，所以便留了一个太阳在天上。</div>\n');

-- ----------------------------
-- Table structure for `sub_task_info`
-- ----------------------------
DROP TABLE IF EXISTS `sub_task_info`;
CREATE TABLE `sub_task_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `task_key` varchar(30) NOT NULL,
  `type` varchar(20) NOT NULL,
  `amount` double(10,2) NOT NULL,
  `status` varchar(10) NOT NULL,
  `priority` double(10,0) NOT NULL DEFAULT '0',
  `benefit_persion` varchar(10) NOT NULL,
  `benefit_type` varchar(10) NOT NULL,
  `desc` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_task_info
-- ----------------------------
INSERT INTO `sub_task_info` VALUES ('1', 'GOOD_COMMENT_TIME_LIMIT', 'BASIC', '0.00', 'NORMAL', '100', 'FREE', 'POINT', '好评时效');
INSERT INTO `sub_task_info` VALUES ('2', 'GOOD_COMMENT_CONTENT', 'BASIC', '0.00', 'NORMAL', '99', 'FREE', 'POINT', '好评内容');
INSERT INTO `sub_task_info` VALUES ('3', 'NEED_WANGWANG_TALK', 'APPRECIATION', '0.50', 'NORMAL', '98', 'RECEIVER', 'POINT', '需要旺旺聊天');
INSERT INTO `sub_task_info` VALUES ('4', 'ZHI_DING_SHOU_HUO_DI_ZHI', 'APPRECIATION', '0.50', 'NORMAL', '97', 'RECEIVER', 'POINT', '指定收货地址');
INSERT INTO `sub_task_info` VALUES ('5', 'ZHI_DING_JIE_SHOU_REN', 'APPRECIATION', '0.50', 'NORMAL', '97', 'PLATFORM', 'POINT', '指定接手人');
INSERT INTO `sub_task_info` VALUES ('6', 'PI_LIANG_FA_BU', 'APPRECIATION', '0.50', 'NORMAL', '96', 'PLATFORM', 'POINT', '批量发布');
INSERT INTO `sub_task_info` VALUES ('7', 'NO_REPEAT_TASK', 'APPRECIATION', '0.50', 'NORMAL', '95', 'PLATFORM', 'POINT', '一天内禁止重复接手');

-- ----------------------------
-- Table structure for `task_basic`
-- ----------------------------
DROP TABLE IF EXISTS `task_basic`;
CREATE TABLE `task_basic` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `task_order_id` int(11) NOT NULL,
  `taobao_xiaohao` varchar(50) NOT NULL,
  `user_qq` varchar(255) NOT NULL,
  `product_title` varchar(100) NOT NULL,
  `product_link` varchar(500) NOT NULL,
  `guarantee_price` double(10,2) NOT NULL,
  `commission` double(11,2) NOT NULL,
  `status` varchar(20) NOT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `receiver_time` datetime DEFAULT NULL,
  `receiver_qq` varchar(30) DEFAULT NULL,
  `receiver_alipay` varchar(50) DEFAULT NULL,
  `task_complete_time` datetime DEFAULT NULL,
  `task_end_time` datetime DEFAULT NULL,
  `pay_pingtai_points` double(10,2) NOT NULL DEFAULT '0.00',
  `pay_pingtai_money` double(10,2) NOT NULL DEFAULT '0.00',
  `pay_receiver_points` double(10,2) NOT NULL,
  `pay_receiver_money` double(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_basic
-- ----------------------------
INSERT INTO `task_basic` VALUES ('1', '1', '2015-04-13 13:58:31', '2', '02jiangwei02', '346745719', '二次版本', 'baidu.com', '10.00', '2.00', 'Creater_Sure', '2', '2015-04-13 14:04:33', '222222222', '02jiangwei02', '2015-04-13 14:07:21', '2015-04-13 14:09:28', '0.00', '0.00', '0.00', '12.00');
INSERT INTO `task_basic` VALUES ('2', '1', '2015-04-13 14:28:23', '3', '02jiangwei02', '346745719', '发布新任务2', 'http://taobao.com2222', '10.00', '2.00', 'Wait_For_Receive', null, null, null, null, null, null, '0.50', '0.00', '0.50', '12.00');
INSERT INTO `task_basic` VALUES ('3', '1', '2015-04-13 14:51:26', '4', '02jiangwei02', '346745719', '测试产品', 'taobao.com', '10.00', '2.00', 'Wait_For_Receive', null, null, null, null, null, null, '0.50', '0.00', '1.00', '12.00');
INSERT INTO `task_basic` VALUES ('4', '1', '2015-04-13 14:51:26', '4', '02jiangwei02', '346745719', '测试产品', 'taobao.com', '10.00', '2.00', 'Wait_For_Receive', null, null, null, null, null, null, '0.50', '0.00', '1.00', '12.00');

-- ----------------------------
-- Table structure for `task_basic_log`
-- ----------------------------
DROP TABLE IF EXISTS `task_basic_log`;
CREATE TABLE `task_basic_log` (
  `id` int(10) NOT NULL,
  `task_basic_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `user_type` varchar(10) NOT NULL,
  `task_state` varchar(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `receiver_ip` varchar(30) DEFAULT NULL,
  `task_basic_creater_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `task_basic` (`task_basic_id`),
  CONSTRAINT `task_basic` FOREIGN KEY (`task_basic_id`) REFERENCES `task_basic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_basic_log
-- ----------------------------
INSERT INTO `task_basic_log` VALUES ('1', '1', '1', 'CREATER', 'Wait_For_Receive', '2015-04-13 13:58:31', null, '1');
INSERT INTO `task_basic_log` VALUES ('2', '1', '2', 'RECEIVER', 'Have_Bean_Received', '2015-04-13 14:04:33', '127.0.0.1', '1');
INSERT INTO `task_basic_log` VALUES ('3', '1', '2', 'RECEIVER', 'Receive_Complete', '2015-04-13 14:07:21', null, '1');
INSERT INTO `task_basic_log` VALUES ('4', '1', '1', 'CREATER', 'Creater_Sure', '2015-04-13 14:09:28', null, '1');
INSERT INTO `task_basic_log` VALUES ('5', '2', '1', 'CREATER', 'Wait_For_Receive', '2015-04-13 14:28:23', null, '1');
INSERT INTO `task_basic_log` VALUES ('6', '3', '1', 'CREATER', 'Wait_For_Receive', '2015-04-13 14:51:26', null, '1');
INSERT INTO `task_basic_log` VALUES ('7', '4', '1', 'CREATER', 'Wait_For_Receive', '2015-04-13 14:51:26', null, '1');

-- ----------------------------
-- Table structure for `task_order`
-- ----------------------------
DROP TABLE IF EXISTS `task_order`;
CREATE TABLE `task_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `taobao_xiaohao` varchar(50) NOT NULL,
  `user_qq` varchar(20) NOT NULL,
  `product_title` varchar(100) NOT NULL,
  `product_link` varchar(300) NOT NULL,
  `guarantee_price` double(10,2) NOT NULL,
  `commission` double(11,2) NOT NULL DEFAULT '0.00',
  `status` varchar(20) NOT NULL,
  `repeate_times` int(10) NOT NULL,
  `pay_pingtai_points` double(10,2) NOT NULL DEFAULT '0.00',
  `pay_pingtai_money` double(10,2) NOT NULL DEFAULT '0.00',
  `every_task_pay_pingtai_points` double(10,2) NOT NULL DEFAULT '0.00',
  `every_task_pay_pingtai_money` double(10,2) NOT NULL,
  `every_task_pay_receiver_points` double(10,2) NOT NULL,
  `every_task_pay_receiver_money` double(10,2) NOT NULL,
  `basicPingtaiGainPoint` double(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_order
-- ----------------------------
INSERT INTO `task_order` VALUES ('1', '1', '2015-04-13 10:23:59', '02jiangwei02', '346745719', '第二版测试1', 'baidu.com', '10.00', '2.00', 'CANCEL', '1', '0.50', '0.00', '0.00', '0.00', '0.00', '12.00', '0.50');
INSERT INTO `task_order` VALUES ('2', '1', '2015-04-13 13:58:28', '02jiangwei02', '346745719', '二次版本', 'baidu.com', '10.00', '2.00', 'SURE', '1', '0.50', '0.00', '0.00', '0.00', '0.00', '12.00', '0.50');
INSERT INTO `task_order` VALUES ('3', '1', '2015-04-13 14:28:20', '02jiangwei02', '346745719', '发布新任务2', 'http://taobao.com2222', '10.00', '2.00', 'SURE', '1', '0.50', '0.00', '0.50', '0.00', '0.50', '12.00', '0.50');
INSERT INTO `task_order` VALUES ('4', '1', '2015-04-13 14:50:46', '02jiangwei02', '346745719', '测试产品', 'taobao.com', '10.00', '2.00', 'SURE', '2', '1.00', '0.00', '0.50', '0.00', '1.00', '12.00', '0.50');

-- ----------------------------
-- Table structure for `task_order_sub_task_info`
-- ----------------------------
DROP TABLE IF EXISTS `task_order_sub_task_info`;
CREATE TABLE `task_order_sub_task_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `task_key` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `amount` double(20,2) NOT NULL,
  `benefit_persion` varchar(20) NOT NULL,
  `benefit_type` varchar(20) NOT NULL,
  `task_order_id` int(11) NOT NULL,
  `input_value` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `task_order_id` (`task_order_id`),
  CONSTRAINT `task_order_id` FOREIGN KEY (`task_order_id`) REFERENCES `task_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_order_sub_task_info
-- ----------------------------
INSERT INTO `task_order_sub_task_info` VALUES ('1', 'GOOD_COMMENT_TIME_LIMIT', 'BASIC', '0.00', 'FREE', 'POINT', '1', 'THIRTYMMinuteLater');
INSERT INTO `task_order_sub_task_info` VALUES ('2', 'GOOD_COMMENT_CONTENT', 'BASIC', '0.00', 'FREE', 'POINT', '1', '好的产品');
INSERT INTO `task_order_sub_task_info` VALUES ('3', 'GOOD_COMMENT_TIME_LIMIT', 'BASIC', '0.00', 'FREE', 'POINT', '2', 'ONE_DAY_LATER');
INSERT INTO `task_order_sub_task_info` VALUES ('4', 'GOOD_COMMENT_CONTENT', 'BASIC', '0.00', 'FREE', 'POINT', '2', '产品质量不错');
INSERT INTO `task_order_sub_task_info` VALUES ('5', 'GOOD_COMMENT_TIME_LIMIT', 'BASIC', '0.00', 'FREE', 'POINT', '3', 'THIRTYMMinuteLater');
INSERT INTO `task_order_sub_task_info` VALUES ('6', 'NEED_WANGWANG_TALK', 'APPRECIATION', '0.50', 'RECEIVER', 'POINT', '3', '1');
INSERT INTO `task_order_sub_task_info` VALUES ('7', 'NO_REPEAT_TASK', 'APPRECIATION', '0.50', 'PLATFORM', 'POINT', '3', '1');
INSERT INTO `task_order_sub_task_info` VALUES ('8', 'GOOD_COMMENT_CONTENT', 'BASIC', '0.00', 'FREE', 'POINT', '3', '好的产品2');
INSERT INTO `task_order_sub_task_info` VALUES ('9', 'GOOD_COMMENT_TIME_LIMIT', 'BASIC', '0.00', 'FREE', 'POINT', '4', 'ONE_DAY_LATER');
INSERT INTO `task_order_sub_task_info` VALUES ('10', 'NEED_WANGWANG_TALK', 'APPRECIATION', '0.50', 'RECEIVER', 'POINT', '4', '1');
INSERT INTO `task_order_sub_task_info` VALUES ('11', 'PI_LIANG_FA_BU', 'APPRECIATION', '0.50', 'PLATFORM', 'POINT', '4', '2');
INSERT INTO `task_order_sub_task_info` VALUES ('12', 'ZHI_DING_SHOU_HUO_DI_ZHI', 'APPRECIATION', '0.50', 'RECEIVER', 'POINT', '4', '北京朝阳');
INSERT INTO `task_order_sub_task_info` VALUES ('13', 'ZHI_DING_JIE_SHOU_REN', 'APPRECIATION', '0.50', 'PLATFORM', 'POINT', '4', '2');
INSERT INTO `task_order_sub_task_info` VALUES ('14', 'GOOD_COMMENT_CONTENT', 'BASIC', '0.00', 'FREE', 'POINT', '4', '产品不错');

-- ----------------------------
-- Table structure for `user_account`
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `current_balance` double(10,2) DEFAULT NULL,
  `current_rest_points` double(10,2) DEFAULT NULL,
  `locked_balance` double(10,2) DEFAULT NULL,
  `locked_points` double(10,2) DEFAULT NULL COMMENT '帐户表',
  `user_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user_base` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES ('1', '142.00', '4.00', '36.00', '4.00', '1');
INSERT INTO `user_account` VALUES ('2', '0.00', '0.00', '0.00', '0.00', '2');
INSERT INTO `user_account` VALUES ('3', '0.00', '0.00', '0.00', '0.00', '3');

-- ----------------------------
-- Table structure for `user_account_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_account_log`;
CREATE TABLE `user_account_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `createTime` datetime NOT NULL,
  `user_id` int(10) NOT NULL,
  `type` varchar(30) NOT NULL,
  `before_rest_amount` double(10,2) NOT NULL DEFAULT '0.00',
  `before_rest_points` double(10,2) NOT NULL DEFAULT '0.00',
  `before_locked_amount` double(10,2) NOT NULL DEFAULT '0.00',
  `before_locked_points` double(10,2) NOT NULL DEFAULT '0.00',
  `after_rest_amount` double(10,2) NOT NULL,
  `after_rest_points` double(10,2) NOT NULL DEFAULT '0.00',
  `after_locked_amount` double(10,2) NOT NULL,
  `after_locked_points` double(10,2) NOT NULL DEFAULT '0.00',
  `admin_user_id` int(10) DEFAULT NULL,
  `task_basic_id` int(10) DEFAULT '0',
  `task_order_id` int(10) DEFAULT NULL,
  `draw_log_id` int(10) DEFAULT NULL,
  `deposit_apply_log_id` int(11) DEFAULT NULL,
  `pay_amount` double(10,2) DEFAULT '0.00',
  `lock_amount` double(10,2) DEFAULT '0.00',
  `pay_point` double(10,2) DEFAULT '0.00',
  `lock_point` double(10,2) DEFAULT '0.00',
  `reason` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `draw_id` (`draw_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_account_log
-- ----------------------------
INSERT INTO `user_account_log` VALUES ('1', '2015-04-13 10:22:43', '1', 'DEPOSIT', '0.00', '0.00', '0.00', '0.00', '200.00', '0.00', '0.00', '0.00', '1', null, null, null, '1', '200.00', '0.00', '0.00', '0.00', '充值成功');
INSERT INTO `user_account_log` VALUES ('2', '2015-04-13 13:57:40', '1', 'BUY_POINTS', '200.00', '0.00', '0.00', '0.00', '190.00', '10.00', '0.00', '0.00', null, null, null, null, null, '10.00', '0.00', '10.00', '0.00', '购买点卡');
INSERT INTO `user_account_log` VALUES ('3', '2015-04-13 13:58:31', '1', 'Task_Order_SURE', '190.00', '10.00', '0.00', '0.00', '178.00', '9.50', '12.00', '0.00', null, null, '2', null, null, '0.00', '12.00', '0.50', '0.00', '订单【2】完成，支付资金，担保金绑定');
INSERT INTO `user_account_log` VALUES ('4', '2015-04-13 14:09:28', '2', 'Task_SURE', '0.00', '0.00', '0.00', '0.00', '12.00', '0.00', '0.00', '0.00', null, '1', null, null, null, '12.00', null, '0.00', null, '完成任务【1】，获得担保金【10.00】获得佣金【2.00】');
INSERT INTO `user_account_log` VALUES ('5', '2015-04-13 14:09:28', '1', 'Task_SURE', '178.00', '9.50', '12.00', '0.00', '178.00', '9.50', '0.00', '0.00', null, '1', null, null, null, '12.00', '0.00', '0.00', '0.00', '任务【1】完成，支付资金');
INSERT INTO `user_account_log` VALUES ('6', '2015-04-13 14:14:09', '2', 'WITHDRAW_APPLY', '12.00', '0.00', '0.00', '0.00', '0.00', '0.00', '12.00', '0.00', null, null, null, '1', null, '0.00', '12.00', '0.00', '0.00', '取款申请，锁定资金【12】元');
INSERT INTO `user_account_log` VALUES ('7', '2015-04-13 14:15:17', '2', 'WITHDRAW_SUCCESS', '0.00', '0.00', '12.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1', null, null, '1', null, '12.00', '0.00', '0.00', '0.00', '取款成功，取出资金【12.00】元');
INSERT INTO `user_account_log` VALUES ('8', '2015-04-13 14:28:23', '1', 'Task_Order_SURE', '178.00', '9.50', '0.00', '0.00', '166.00', '8.00', '12.00', '1.00', null, null, '3', null, null, '0.00', '12.00', '0.50', '1.00', '订单【3】完成，支付资金，担保金绑定');
INSERT INTO `user_account_log` VALUES ('9', '2015-04-13 14:51:26', '1', 'Task_Order_SURE', '166.00', '8.00', '12.00', '1.00', '142.00', '4.00', '36.00', '4.00', null, null, '4', null, null, '0.00', '24.00', '1.00', '3.00', '订单【4】完成，支付资金，担保金绑定');

-- ----------------------------
-- Table structure for `user_base`
-- ----------------------------
DROP TABLE IF EXISTS `user_base`;
CREATE TABLE `user_base` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(36) NOT NULL,
  `regTime` datetime NOT NULL COMMENT '用户基本信息表',
  `status` varchar(10) NOT NULL,
  `cao_zuo_ma` varchar(50) DEFAULT NULL,
  `bind_email` varchar(50) DEFAULT NULL,
  `bind_telphone` varchar(20) DEFAULT NULL,
  `bind_qq` varchar(20) DEFAULT NULL,
  `bind_alipay` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_base
-- ----------------------------
INSERT INTO `user_base` VALUES ('1', '01jiangwei01@163.com', '96e79218965eb72c92a549dd5a330112', '2015-02-13 14:36:22', 'NORMAL', '8e683187a00e5d462a4aeee69e9d3d9c', '01jiangwei01@163.com', '', '346745719', '02jiangwei02');
INSERT INTO `user_base` VALUES ('2', '02jiangwei02', '96e79218965eb72c92a549dd5a330112', '2015-03-13 22:31:23', 'NORMAL', '6dbf9ac2da09ee1d3debf5a51873ec6d', '02jiangwei02@163.com', null, '222222222', '02jiangwei02');
INSERT INTO `user_base` VALUES ('3', 'x12345', '96e79218965eb72c92a549dd5a330112', '2015-03-16 22:15:43', 'NORMAL', '8e683187a00e5d462a4aeee69e9d3d9c', null, '15210578828', null, null);

-- ----------------------------
-- Table structure for `user_link`
-- ----------------------------
DROP TABLE IF EXISTS `user_link`;
CREATE TABLE `user_link` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `link_type` varchar(30) NOT NULL,
  `link_value` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_link
-- ----------------------------

-- ----------------------------
-- Table structure for `valid_info`
-- ----------------------------
DROP TABLE IF EXISTS `valid_info`;
CREATE TABLE `valid_info` (
  `id` int(10) NOT NULL DEFAULT '0',
  `type` varchar(255) NOT NULL,
  `type_value` varchar(100) NOT NULL,
  `code` varchar(8) NOT NULL,
  `create_time` datetime NOT NULL,
  `invalid_time` datetime NOT NULL,
  `active_time` datetime DEFAULT NULL,
  `enabled` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of valid_info
-- ----------------------------

-- ----------------------------
-- Table structure for `yanzhengma_log`
-- ----------------------------
DROP TABLE IF EXISTS `yanzhengma_log`;
CREATE TABLE `yanzhengma_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `value` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `active_time` datetime DEFAULT NULL,
  `exp_time` datetime NOT NULL,
  `enabled` int(1) NOT NULL,
  `user_id` int(10) DEFAULT NULL,
  `tran_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yanzhengma_log
-- ----------------------------
INSERT INTO `yanzhengma_log` VALUES ('1', '248936', 'email', '01jiangwei01@163.com', '2015-02-13 14:35:04', '2015-02-13 14:36:22', '2015-02-13 14:35:04', '0', null, '1');
INSERT INTO `yanzhengma_log` VALUES ('2', '986403', 'email', '01jiangwei01@163.com', '2015-02-14 21:23:09', null, '2015-02-14 21:23:09', '0', null, '1');
INSERT INTO `yanzhengma_log` VALUES ('4', '921853', 'email', '01jiangwei01@163.com', '2015-02-14 21:43:12', null, '2015-02-14 21:43:12', '0', null, '1');
INSERT INTO `yanzhengma_log` VALUES ('5', '629130', 'email', '01jiangwei01@163.com', '2015-02-15 11:50:25', '2015-02-15 13:21:39', '2015-02-15 11:50:25', '1', '1', 'Update_bind');
INSERT INTO `yanzhengma_log` VALUES ('6', '502941', 'email', '346745719@qq.com', '2015-02-15 16:23:50', '2015-02-15 16:24:14', '2015-02-15 16:23:50', '0', '1', 'Update_bind');
INSERT INTO `yanzhengma_log` VALUES ('7', '739652', 'email', '346745719@qq.com', '2015-03-09 14:57:03', null, '2015-03-09 14:57:03', '0', '1', 'FIND_BACK_PASSWORD');
INSERT INTO `yanzhengma_log` VALUES ('8', '829610', 'email', '346745719@qq.com', '2015-03-09 14:58:34', null, '2015-03-09 14:58:34', '0', '1', 'FIND_BACK_PASSWORD');
INSERT INTO `yanzhengma_log` VALUES ('9', '861095', 'email', '346745719@qq.com', '2015-03-09 15:03:45', null, '2015-03-09 15:03:45', '0', '1', 'FIND_BACK_PASSWORD');
INSERT INTO `yanzhengma_log` VALUES ('10', '831274', 'email', '346745719@qq.com', '2015-03-09 15:08:37', null, '2015-03-09 15:08:37', '1', '1', 'FIND_BACK_PASSWORD');
INSERT INTO `yanzhengma_log` VALUES ('11', '392107', 'phone', '15001279241', '2015-03-13 14:12:31', '2015-03-13 14:17:04', '2015-03-13 14:12:31', '0', '1', 'Update_bind');
INSERT INTO `yanzhengma_log` VALUES ('12', '118365', 'phone', '15001279241', '2015-03-13 15:07:57', null, '2015-03-13 15:07:57', '0', '1', 'Update_bind');
INSERT INTO `yanzhengma_log` VALUES ('13', '157432', 'phone', '15001279241', '2015-03-13 15:34:56', null, '2015-03-13 15:34:56', '0', '1', 'Update_bind');
INSERT INTO `yanzhengma_log` VALUES ('14', '124368', 'phone', '15001279241', '2015-03-13 15:43:05', null, '2015-03-13 15:43:05', '0', '1', 'Update_bind');
INSERT INTO `yanzhengma_log` VALUES ('15', '830157', 'phone', '15001279241', '2015-03-13 16:38:26', '2015-03-13 16:42:56', '2015-03-13 16:38:26', '0', '1', 'Update_bind');
INSERT INTO `yanzhengma_log` VALUES ('16', '578301', 'email', '02jiangwei02@163.com', '2015-03-13 22:24:59', null, '2015-03-13 22:24:59', '0', null, null);
INSERT INTO `yanzhengma_log` VALUES ('17', '290657', 'email', '02jiangwei02@163.com', '2015-03-13 22:31:04', '2015-03-13 22:31:23', '2015-03-13 22:31:04', '0', null, 'Reg');
INSERT INTO `yanzhengma_log` VALUES ('18', '716458', 'phone', '15210578828', '2015-03-16 21:34:51', null, '2015-03-16 21:34:51', '0', null, 'Reg');
INSERT INTO `yanzhengma_log` VALUES ('19', '246587', 'phone', '15210578828', '2015-03-16 22:01:02', '2015-03-16 22:15:43', '2015-03-16 22:01:02', '0', null, 'Reg');
INSERT INTO `yanzhengma_log` VALUES ('20', '890653', 'phone', '15210578828', '2015-03-16 23:04:57', '2015-03-17 09:46:57', '2015-03-16 23:04:57', '0', null, 'Update_CaoZuoMa');
INSERT INTO `yanzhengma_log` VALUES ('21', '175046', 'phone', '15001279241', '2015-03-20 11:28:09', null, '2015-03-20 11:28:09', '0', null, 'Reg');
INSERT INTO `yanzhengma_log` VALUES ('22', '127615', 'phone', '15001279241', '2015-03-28 13:53:51', null, '2015-03-28 13:53:51', '1', null, 'Reg');
