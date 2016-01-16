SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS  `admin_menu`;
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

insert into `admin_menu`(`id`,`name`,`path`,`orders`,`isbutton`,`btnflag`,`pid`) values
('1','系统管理',null,'1','0',null,'0'),
('2','菜单管理','/admin/menu/index','2','0',null,'1'),
('3','角色管理','/admin/role/index','3','0',null,'1'),
('4','管理员管理','/admin/user','11','0',null,'1'),
('5','增加管理员','/admin/user/doadd',null,'1','userdoadd','4'),
('6','修改管理员','/admin/user/doupdate',null,'1','userdoupdate','4'),
('7','删除管理员','/admin/user/dodel',null,'1','userdodel','4'),
('8','管理员查询','/admin/user/dopage',null,'1','userdopage','4'),
('9','查看菜单','/admin/menu/list',null,'1','menulist','2'),
('10','修改菜单','/admin/menu/doupdate',null,'1','menudoupdate','2'),
('11','添加菜单','/admin/menu/doadd',null,'1','menudoadd','2'),
('12','删除菜单','/admin/menu/dodel',null,'1','menudodel','2'),
('13','添加角色','/admin/role/doadd',null,'1','roledoadd','3'),
('14','修改角色','/admin/role/doupdate',null,'1','roledoupdate','3'),
('15','删除角色','/admin/role/setstatus',null,'1','rolesetstatus','3'),
('16','分页查看角色','/admin/role/dopage',null,'1','roledopage','3'),
('17','查看某个角色','/admin/role/get',null,'1','roleget','3'),
('18','密码重置','/admin/user/setpassword',null,'1','usersetpassword','4'),
('19','账单管理','',null,'0','','0'),
('20','充值申请管理','/admin/deposit',null,'0','','19'),
('21','取款申请管理','/admin/applydraw',null,'0','','19'),
('22','取款分页查询','/admin/applydraw/dopage',null,'1','admin_applydraw_dopage','21'),
('23','充值管理分页查询','/admin/deposit/dopage',null,'1','admin_deposit_dopage','20'),
('24','取款拒绝','/admin/applydraw/doarefuse',null,'1','admin_applydraw_doarefuse','21'),
('25','取款同意','/admin/applydraw/doagree',null,'1','admin_applydraw_doagree','21'),
('26','充值同意','/admin/deposit/doagree',null,'1','admin_deposit_doagree','20'),
('27','充值拒绝','/admin/deposit/doarefuse',null,'1','admin_deposit_doarefuse','20'),
('28','前台用户管理','/admin/siteuser',null,'0','','29'),
('29','用户列表','/admin/siteuser',null,'0','','0'),
('30','前台用户分页查看','/admin/siteuser/dopage',null,'1','admin_siteuser_dopage','28'),
('32','设置赞助用户点数','/admin/siteuser/supplypoint',null,'1','admin_siteuser_supplypoint','28'),
('39','赞助用户记录','/admin/log/COMPANY_SUPPLY',null,'0','','19'),
('40','赞助用户记录分页','/admin/log/dopage/COMPANY_SUPPLY',null,'1','admin_log_dopage_COMPANY_SUPPLY','39'),
('41','公司收支情况','/admin/company_account',null,'0','','19'),
('42','营销管理','',null,'0','','0'),
('43','邮件邮箱系统','',null,'0','','42'),
('44','邮箱模板管理','/admin/mail/templete',null,'0','','43'),
('45','邮件管理','/admin/mail/content',null,'0','','43'),
('46','创建邮件模板','/admin/mail/templete/doadd',null,'1','admin_mail_templete_doadd','44'),
('47','修改邮件模板','/admin/mail/templete/doupdate',null,'1','admin_mail_templete_doupdate','44'),
('48','删除邮件模板','/admin/mail/templete/dodel',null,'1','admin_mail_templete_dodel','44'),
('49','分页查看邮件模板','/admin/mail/templete/dopage',null,'1','admin_mail_templete_dopage','44'),
('50','分页查看邮件内容','/admin/mail/content/dopage',null,'1','admin_mail_content_dopage','45'),
('51','增加邮件','/admin/mail/content/doadd',null,'1','admin_mail_content_doadd','45'),
('52','修改邮件内容','/admin/mail/content/doupdate',null,'1','admin_mail_content_doupdate','45'),
('53','删除邮件内容','/admin/mail/content/dodel',null,'1','admin_mail_content_dodel','45'),
('54','查看邮件详情','/admin/mail/content/get',null,'1','admin_mail_content_get','45'),
('55','查看邮件模板详情','/admin/mail/templete/get',null,'1','admin_mail_templete_get','44'),
('56','工具箱','',null,'0','','0'),
('57','图片库','/admin/tool/pics',null,'0','','56'),
('58','分页查看图片','/admin/tool/pics/dopage',null,'1','admin_tool_pics_dopage','57'),
('59','上传图片','/admin/tool/pics/upload',null,'1','admin_tool_pics_upload','57'),
('60','删除图片','/admin/tool/pics/dodel',null,'1','admin_tool_pics_del','57'),
('61','通讯录管理','/admin/mail/addresslist',null,'0','','43'),
('62','分页查看通讯录','/admin/mail/addresslist/dopage',null,'1','admin_mail_addresslist_dopage','61'),
('65','增加通讯录联系人','/admin/mail/addresslist/doadd',null,'1','admin_mail_addresslist_doadd','61'),
('66','修改通讯录联系人','/admin/mail/addresslist/doupdate',null,'1','admin_mail_addresslist_doupdate','61'),
('67','删除通讯录联系人','/admin/mail/addresslist/dodel',null,'1','admin_mail_addresslist_dodel','61'),
('68','发送邮件管理','/admin/mail/sendertask',null,'0','','43'),
('69','分页查看发送邮件任务','/admin/mail/sendertask/dopage',null,'1','admin_mail_sendertask_dopage','68'),
('70','创建发送邮件任务','/admin/mail/sendertask/doadd',null,'1','admin_mail_sendertask_doadd','68'),
('71','修改发送邮件任务','/admin/mail/sendertask/doupdate',null,'1','admin_mail_sendertask_doupdate','68'),
('72','删除发生邮件','/admin/mail/sendertask/dodel',null,'1','admin_mail_sendertask_dodel','68'),
('73','发送邮件','/admin/mail/sendertask/dosend',null,'1','admin_mail_sendertask_dosend','68'),
('74','查看邮件发送情况','/admin/mail/sendertask/detailpage',null,'1','admin_mail_sendertask_detailpage','68'),
('75','查看任务详情','/admin/mail/sendertask/detail',null,'1','admin_mail_sendertask_dotail','68'),
('76','获取任务数据','/admin/mail/sendertask/get',null,'1','admin_mail_sendertask_get','68'),
('77','分页查看有效的通讯录','/admin/mail/addresslist/dopage',null,'1','admin_mail_addresslist_dopage_valid','68'),
('78','分页查看有效内容','/admin/mail/content/dopage',null,'1','admin_mail_content_dopage_valid','68'),
('79','分页查看有效邮件模板','/admin/mail/templete/dopage',null,'1','admin_mail_templete_dopage_valid','45'),
('80','产品管理','',null,'0','','0'),
('81','任务点卡管理','/admin/products/point_card','10','0','','80'),
('82','分页查看点卡','/admin/products/point_card/dopage',null,'1','adminProductsPoint_cardDopage','81'),
('83','增加点卡','/admin/products/point_card/doadd',null,'1','adminProductsPoint_cardDoadd','81'),
('84','修改点卡','/admin/products/point_card/doupdate',null,'1','adminProductsPoint_cardDoupdate','81'),
('85','删除点卡','/admin/products/point_card/dodel',null,'1','adminProductsPoint_cardDodel','81'),
('86','前台订单管理','/admin/order',null,'0','','29'),
('87','订单查看','/admin/order/dopage',null,'1','admin_oeder_dopage','86'),
('88','订单详情','/admin/order/detail',null,'1','admin_oeder_detail','86'),
('89','前台任务列表','/admin/task',null,'0','','29'),
('90','任务分页查看','/admin/task/dopage',null,'1','admin_tasks_dopage','89'),
('91','任务详情','/admin/task/detail',null,'1','admin_task_detail','89'),
('92','公司收支分页查看','/admin/company_account/dopage',null,'1','admin_company_account_dopage','41'),
('93','故事管理','',null,'0','','0'),
('94','神话故事','/admin/MYTH/index',null,'0','','93'),
('95','增加神话故事','/admin/MYTH/doadd',null,'1','admin_MYTH_doadd','94'),
('96','修改神话故事','/admin/MYTH/doupdate',null,'1','admin_MYTH_doupdate','94'),
('97','设置神话故事状态','/admin/MYTH/setstatus',null,'1','admin_MYTH_setstatus','94'),
('98','分页查看神话故事','/admin/MYTH/dopage',null,'1','admin_MYTH_dopage','94'),
('99','神话故事详情','/admin/MYTH/detail',null,'1','admin_MYTH_detail','94'),
('100','删除神话故事','/admin/MYTH/delete',null,'1','admin_MYTH_delete','94');
DROP TABLE IF EXISTS  `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

insert into `admin_role`(`id`,`name`,`status`) values
('8','系统管理员','1'),
('9','技术管理组','1'),
('10','test','4'),
('11','test','4'),
('12','test','4'),
('13','test','4'),
('14','test','4'),
('15','财务管理','1'),
('16','主管','1');
DROP TABLE IF EXISTS  `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `real_name` varchar(60) NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

insert into `admin_user`(`id`,`name`,`password`,`real_name`,`status`) values
('1','01jiangwei01','96e79218965eb72c92a549dd5a330112','管理员','1'),
('2','lisuli','96e79218965eb72c92a549dd5a330112','李素丽','1'),
('3','demo','96e79218965eb72c92a549dd5a330112','测试用户','1');
DROP TABLE IF EXISTS  `apply_draw_log`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `business_exception`;
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

DROP TABLE IF EXISTS  `caozuoma_log`;
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

DROP TABLE IF EXISTS  `company_account`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `deposit_apply_log`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `log4j_log`;
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

DROP TABLE IF EXISTS  `log_info`;
CREATE TABLE `log_info` (
  `id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `log_type` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `mail_address_list`;
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

insert into `mail_address_list`(`id`,`name`,`email`,`status`,`create_user_id`,`crete_time`,`come_from`,`gender`) values
('1','xu','01jiangwei01@163.com','DEL','1','2015-01-17 17:12:04','QQ','male'),
('2','1','01jiangwei01@163.com','NORMAL','1','2015-01-17 17:25:36','QQ','female'),
('3','12','ee@163.com','NORMAL','1','2015-01-17 17:07:46','QQ','male'),
('4','赵祥','zhaoxiang@ucredit.com','NORMAL','1','2015-01-21 19:12:37','QQ','male');
DROP TABLE IF EXISTS  `mail_content`;
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

insert into `mail_content`(`id`,`templete_id`,`content`,`title`,`update_time`,`status`,`updateUserId`) values
('1','1','内容1','标题1-1','2014-12-24 19:23:02','DELETE','1'),
('2','1','内容2','标题2-1','2014-12-24 19:43:50','DELETE','1'),
('3','1','<p>内容3<img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150116174648995.jpg" style="height:911px; width:610px" /></p>
','标题3','2015-01-16 17:49:46','NORMAL','1'),
('4','1','<p>内容5555</p>
','标题4','2015-01-06 18:52:58','NORMAL','1'),
('5','1','<p>欢迎注册聚来宝</p>
','聚来宝注册','2015-01-06 18:53:13','NORMAL','1');
DROP TABLE IF EXISTS  `mail_sender_ref_address_list`;
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

insert into `mail_sender_ref_address_list`(`id`,`mail_sender_task_id`,`email`,`mail_address_list_id`,`emai_user_name`,`gender`,`status`) values
('3','2','01jiangwei01@163.com','2','1','female','SUCCESS'),
('4','2','ee@163.com','3','12','male','SUCCESS'),
('5','3','01jiangwei01@163.com','2','1','female','SUCCESS'),
('6','3','ee@163.com','3','12','male','SUCCESS'),
('7','4','01jiangwei01@163.com','2','1','female','SUCCESS'),
('8','4','ee@163.com','3','12','male','SUCCESS'),
('9','5','01jiangwei01@163.com','2','1','female','SUCCESS'),
('13','6','zhaoxiang@ucredit.com','4','赵祥','male','SUCCESS');
DROP TABLE IF EXISTS  `mail_sender_task`;
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

insert into `mail_sender_task`(`id`,`create_user`,`create_time`,`title`,`content`,`status`,`content_id`) values
('2','1','2015-01-21 11:15:38','聚来宝注册','<p>欢迎注册聚来宝</p>
','ISEXECUTED','1'),
('3','1','2015-01-21 11:16:30','聚来宝注册','<p>欢迎注册聚来宝</p>
','ISEXECUTED','1'),
('4','1','2015-01-21 11:24:42','标题4','<p>内容5555</p>
','ISEXECUTED','1'),
('5','1','2015-01-21 14:14:36','标题3','<p>内容3<img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150116174648995.jpg" style="height:911px; width:610px" /></p>
','ISEXECUTED','1'),
('6','1','2015-01-21 19:13:24','标题3','<p>内容3<img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150116174648995.jpg" style="height:911px; width:610px" /></p>
','ISEXECUTED','3');
DROP TABLE IF EXISTS  `mail_templete`;
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

insert into `mail_templete`(`id`,`templete_name`,`templete_path`,`update_time`,`status`,`updateUserId`,`templete_type`,`templete_desc`) values
('1','测试模板','/templete/mail/v1','2015-01-06 13:44:31','NORMAL','1','JU_LAI_BAO_TUI_GUANG_V1','1'),
('2','模板2','/admin/123233','2014-12-24 19:44:25','DELETE','1',null,null);
DROP TABLE IF EXISTS  `notifications`;
CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `operate_log`;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

insert into `operate_log`(`id`,`user_id`,`operate_time`,`operate_type`,`before_value`,`after_value`,`ip`,`isused`) values
('1','2','2015-03-13 22:31:23','REG_EMAIL',null,'02jiangwei02@163.com',null,'0'),
('2','3','2015-03-16 18:36:09','REG_EMAIL',null,'305844838@qq.com',null,'0'),
('3','4','2015-03-17 13:01:58','ACTIVE_PHONE',null,'18975004759',null,'0'),
('4','5','2015-03-17 15:18:49','ACTIVE_PHONE',null,'18729283756',null,'0'),
('5','1001','2015-03-20 14:28:12','ACTIVE_PHONE',null,'15288786922',null,'0'),
('6','1002','2015-03-20 19:04:07','ACTIVE_PHONE',null,'18638270138',null,'0');
DROP TABLE IF EXISTS  `pics`;
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

insert into `pics`(`id`,`pic_path`,`status`,`pic_name`,`pic_desc`,`create_time`,`create_user`) values
('3','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150116174648995.jpg','NORMAL','阿里测试','阿里测试','2015-01-16 17:46:49','1'),
('4','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150724141414190.jpg','DELERATE','后羿射日','后羿射日','2015-07-24 14:14:14','1'),
('5','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150724141416536.jpg','NORMAL','后羿射日','后羿射日','2015-07-24 14:14:16','1'),
('6','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/2015081412405336.jpg','NORMAL','共工怒触不周山','共工怒触不周山','2015-08-14 12:40:53','1'),
('7','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150814125316215.jpg','NORMAL','女娲造人','女娲造人','2015-08-14 12:53:16','1'),
('8','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150814125932223.jpg','NORMAL','炎帝击石生火','炎帝击石生火','2015-08-14 12:59:32','1'),
('9','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150814130246470.jpg','NORMAL','神农试百草','神农试百草','2015-08-14 13:02:46','1'),
('10','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150814130751142.png','NORMAL','精卫填海','精卫填海','2015-08-14 13:07:51','1'),
('11','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150814131203366.jpg','NORMAL','刑天舞干戈','刑天舞干戈','2015-08-14 13:12:03','1'),
('12','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/2015081413173158.jpg','NORMAL','黄帝战蚩尤','黄帝战蚩尤','2015-08-14 13:17:31','1'),
('13','http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/2015081413233169.jpg','NORMAL','女娲补天','女娲补天','2015-08-14 13:23:31','1');
DROP TABLE IF EXISTS  `point_card`;
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

insert into `point_card`(`id`,`points`,`money`,`title`,`url`,`orders`,`status`,`admin_user_id`,`update_time`) values
('1','10','10','10元卡',null,'99','NORMAL','0','2015-02-27 14:12:33'),
('2','55','50','50元卡',null,'98','NORMAL','1','2015-02-27 15:15:00'),
('3','120','100','100元卡',null,'97','NORMAL','1','2015-02-27 15:16:03'),
('4','1100','1000','1000元卡',null,'100','DELERATE','1','2015-02-27 15:16:38'),
('5','100','100','1000',null,'100','DELERATE','1','2015-02-27 15:16:59');
DROP TABLE IF EXISTS  `rel_admin_user_role`;
CREATE TABLE `rel_admin_user_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `admin_user_id` int(10) DEFAULT NULL,
  `admin_role_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

insert into `rel_admin_user_role`(`id`,`admin_user_id`,`admin_role_id`) values
('8','1','8'),
('9','2','16'),
('10','3','8');
DROP TABLE IF EXISTS  `rel_role_menu`;
CREATE TABLE `rel_role_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `roleid` int(10) DEFAULT NULL,
  `menuid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=399 DEFAULT CHARSET=utf8;

insert into `rel_role_menu`(`id`,`roleid`,`menuid`) values
('20','10','19'),
('21','11','19'),
('22','12','19'),
('23','13','19'),
('24','14','19'),
('131','9','19'),
('132','9','20'),
('133','9','23'),
('134','9','26'),
('135','9','27'),
('136','9','21'),
('137','9','22'),
('138','9','24'),
('139','9','25'),
('140','9','1'),
('141','9','2'),
('142','9','9'),
('143','9','10'),
('144','9','11'),
('145','9','12'),
('146','9','3'),
('147','9','13'),
('148','9','14'),
('149','9','15'),
('150','9','16'),
('151','9','17'),
('152','9','4'),
('153','9','5'),
('154','9','6'),
('155','9','7'),
('156','9','8'),
('157','9','18'),
('158','15','19'),
('159','15','20'),
('160','15','23'),
('161','15','26'),
('162','15','27'),
('163','15','21'),
('164','15','22'),
('165','15','24'),
('166','15','25'),
('250','16','19'),
('251','16','20'),
('252','16','23'),
('253','16','26'),
('254','16','27'),
('255','16','21'),
('256','16','22'),
('257','16','24'),
('258','16','25'),
('259','16','41'),
('260','16','92'),
('261','16','29'),
('262','16','28'),
('263','16','30'),
('264','16','32'),
('265','16','86'),
('266','16','87'),
('267','16','88'),
('268','16','89'),
('269','16','90'),
('270','16','91'),
('271','16','42'),
('272','16','43'),
('273','16','44'),
('274','16','46'),
('275','16','47'),
('276','16','48'),
('277','16','49'),
('278','16','55'),
('279','16','45'),
('280','16','50'),
('281','16','51'),
('282','16','52'),
('283','16','53'),
('284','16','54'),
('285','16','79'),
('286','16','61'),
('287','16','62'),
('288','16','65'),
('289','16','66'),
('290','16','67'),
('291','16','68'),
('292','16','69'),
('293','16','70'),
('294','16','71'),
('295','16','72'),
('296','16','73'),
('297','16','74'),
('298','16','75'),
('299','16','76'),
('300','16','77'),
('301','16','78'),
('302','16','80'),
('303','16','81'),
('304','16','82'),
('305','16','1'),
('306','16','4'),
('307','16','8'),
('308','8','19'),
('309','8','20'),
('310','8','23'),
('311','8','26'),
('312','8','27'),
('313','8','21'),
('314','8','22'),
('315','8','24'),
('316','8','25'),
('317','8','39'),
('318','8','40'),
('319','8','41'),
('320','8','92'),
('321','8','29'),
('322','8','28'),
('323','8','30'),
('324','8','32'),
('325','8','86'),
('326','8','87'),
('327','8','88'),
('328','8','89'),
('329','8','90'),
('330','8','91'),
('331','8','42'),
('332','8','43'),
('333','8','44'),
('334','8','46'),
('335','8','47'),
('336','8','48'),
('337','8','49'),
('338','8','55'),
('339','8','45'),
('340','8','50'),
('341','8','51'),
('342','8','52'),
('343','8','53'),
('344','8','54'),
('345','8','79'),
('346','8','61'),
('347','8','62'),
('348','8','65'),
('349','8','66'),
('350','8','67'),
('351','8','68'),
('352','8','69'),
('353','8','70'),
('354','8','71'),
('355','8','72'),
('356','8','73'),
('357','8','74'),
('358','8','75'),
('359','8','76'),
('360','8','77'),
('361','8','78'),
('362','8','56'),
('363','8','57'),
('364','8','58'),
('365','8','59'),
('366','8','60'),
('367','8','80'),
('368','8','81'),
('369','8','82'),
('370','8','83'),
('371','8','84'),
('372','8','85'),
('373','8','93'),
('374','8','94'),
('375','8','95'),
('376','8','96'),
('377','8','97'),
('378','8','98'),
('379','8','99'),
('380','8','100'),
('381','8','1'),
('382','8','2'),
('383','8','9'),
('384','8','10'),
('385','8','11'),
('386','8','12'),
('387','8','3'),
('388','8','13'),
('389','8','14'),
('390','8','15'),
('391','8','16'),
('392','8','17'),
('393','8','4'),
('394','8','5'),
('395','8','6'),
('396','8','7'),
('397','8','8'),
('398','8','18');
DROP TABLE IF EXISTS  `story_article`;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

insert into `story_article`(`article_id`,`article_title`,`add_time`,`add_user_id`,`update_time`,`update_user_id`,`story_time`,`story_summary`,`hit_times`,`praise_number`,`tiresome_number`,`from_book_name`,`status`,`type`) values
('1','盘古开天','2015-07-23 15:03:23','1','2015-07-23 15:03:28','1','上古时期','传说混沌未开的时候，在混沌的中央，有一块浑圆就像是一枚鸡蛋。这块浑圆里有着一个巨人。','38','4','0','三五历纪、山海经、广博物志等','NORMAL','MYTH'),
('2','女娲造人','2015-07-23 15:07:06','1','2015-08-14 18:50:00','1','上古时期','在盘古肉身化为大地之后，大地上出现了很多种生物．多了很多年之后。。。','37','6','0','山海经','NORMAL','MYTH'),
('3','炎帝击石生火','2015-07-23 15:09:12','1','2015-08-14 13:00:29','1','上古时期','人类使用最早的火全都是天然火（大多是雷电击在大树上所产生的火）．有个人便想人类总不能一直依靠天然火吧。。。','21','2','0','山海经','NORMAL','MYTH'),
('4','神农试百草','2015-07-23 15:09:52','1','2015-08-14 13:04:10','1','上古时期','生老病死是最自然不过的事了，不过因为”病”而早早离开这个世界的人又实在值得同情','22','0','0','山海经','NORMAL','MYTH'),
('5','精卫填海','2015-07-23 15:10:50','1','2015-08-14 13:08:32','1','上古时期','炎帝有一个善良，可爱的小女儿，名字叫精卫．她从小就有一个梦想，....','19','0','0','山海经','NORMAL','MYTH'),
('6','刑天舞干戈','2015-07-23 15:12:01','1','2015-08-14 13:12:54','1','上古时期','义和起兵造反，炎帝被困荒山之中．兵变的事被炎帝最忠诚的战将刑天知道后。。。','17','3','0','山海经','NORMAL','MYTH'),
('7','黄帝战蚩尤','2015-07-23 15:13:00','1','2015-08-14 13:18:13','1','上古时期','华夏被分为五个大部落。这五个部落中以黄帝和蚩尤的部落势力最大','31','2','0','山海经','NORMAL','MYTH'),
('8','共工怒触不周山','2015-07-23 15:14:36','1','2015-08-14 13:27:41','1','上古时期','共工一头撞向不周仙山。。。。','27','11','9','山海经','NORMAL','MYTH'),
('9','女娲补天','2015-07-23 15:15:38','1','2015-08-14 13:24:10','1','上古时期','女娲四处收集五色石，再以五色石炼制成补天石','40','1','0','山海经','NORMAL','MYTH'),
('10','后羿射日','2015-07-23 15:16:48','1','2015-07-24 17:37:30','1','上古时期','天上同时出现十个太阳．后羿为解决大旱问题，于是。。。。','40','3','0','山海经','NORMAL','MYTH'),
('11','DASF','2015-10-12 09:58:39','1','2015-10-12 09:58:39','1','ASF','ASFD','0','0','0','ASFD','WAIT4REVIEW','MYTH');
DROP TABLE IF EXISTS  `story_article_desc`;
CREATE TABLE `story_article_desc` (
  `article_id` int(11) NOT NULL DEFAULT '0',
  `article_content` longtext,
  PRIMARY KEY (`article_id`),
  KEY `shenhua_id` (`article_id`),
  CONSTRAINT `shenhua_id` FOREIGN KEY (`article_id`) REFERENCES `story_article` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `story_article_desc`(`article_id`,`article_content`) values
('1','<div>话说最早的天地本是合在一起的，宇宙本是一团混沌。在这片混沌中沉睡着一个人，他的名字叫做盘古。</div>

<div>他的身边放着一把样子古怪的斧头。直到有一天盘古忽然醒来，但是觉得自己所在的空间是那么的压抑，于是拿起身旁的斧头对着周围的空间一气乱挥，也不知道挥了多久，终于把天和地给明确的划分出来了。但是天与地分开没多久就又开始慢慢合拢，盘古就用自己的身体来支撑着天与地，就这样支撑了很久很久，天与地的位置已经被固定住了。但是伟大的盘古却因疲劳过度，累死了。他死后身体开始变化，骨骼变成了山脉和丘陵，肉身变成了平原和盆地，血和汗水变成了江海，毛发变成了森林，草原和沼泽。他的元神到了天与地的中心处，却因疲倦睡去。</div>
'),
('2','<div>&nbsp; 在盘古肉身化为大地之后，大地上出现了很多种生物．很多年之后，大地上出现了一位同样伟大的人物－－女娲．女娲发现大地虽然有了很多生灵，但是它们缺少着创造能力，于是女娲便用黄泥按照自己的摸样捏出了很多小人，因为女娲觉得创造的这些人应该和自己有点区别才是，所以把泥人的尾巴改成了两条后肢（双足）。之后用施放了法术将这些小人变成有生命的活人，并且这些人都具有自己独立的思想。</div>

<div style="text-align: center;">&nbsp;<img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150814125316215.jpg" style="width: 400px; height: 265px;" /></div>
'),
('3','<div>&nbsp; 人类使用最早的火全都是天然火（大多是雷电击在大树上所产生的火）．有个人便想人类总不能一直依靠天然火吧，所以决定自己要创造一种取火的方法，在一次狩猎中，他一失手未将石制的标枪命中猎物，不过他发现标枪与地上的石头相击时产生了火花，于是他就捡了很多那里的石头来试验，结果成功的取得了火．也因为他发现了取火的方法，所以各个部落都一致拥他为王，这个人就是以火德为王的炎帝．之后他又创造了耕种方法并且教会了人类耕种，所以他又被称为神农。</div>

<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150814125932223.jpg" style="width: 400px; height: 303px;" /></div>
'),
('4','<div>生老病死是最自然不过的事了，不过因为&rdquo;病&rdquo;而早早离开这个世界的人又实在值得同情．神农觉得自己有保护好自己的子民的职责，所以他踏遍神州大地寻找治疗病患的药草，找到后他都先自己服用来确定有用与否或者有毒与否．最后终于找到了可以治疗很多常见病患的药草．神农试百草：生老病死是最自然不过的事了，不过因为&rdquo;病&rdquo;而早早离开这个世界的人又实在值得同情．神农觉得自己有保护好自己的子民的职责，所以他踏遍神州大地寻找治疗病患的药草，找到后他都先自己服用来确定有用与否或者有毒与否．最后终于找到了可以治疗很多常见病患的药草。</div>

<div style="text-align: center;"><img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150814130246470.jpg" style="width: 400px; height: 385px;" /></div>
'),
('5','<div>炎帝有一个善良，可爱的小女儿，名字叫精卫．她从小就有一个梦想，希望见道无边的大海．小女孩一天天张大，也一天比一天漂亮．终于在她认为自己有能力找到大海的时候，她悄悄的离开了部落．最终她找到了大海，她为大海的伟大而陶醉，正当她奔向大海的时候，大海同样卷着巨浪奔向精卫．最后，大海把精卫带走了，带着她离开了这个世界．精卫死前那一刻才醒悟过来．精卫的灵魂因为愤怒而化做一只青鸟，精卫为了报复大海，决定把大海填平，于是就日以继夜的含着西山的石头去填海。</div>

<div style="text-align: center;"><img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150814130751142.png" style="width: 250px; height: 398px;" /></div>
'),
('6','<div>炎帝得知心爱的小女儿死去的消息，悲痛的得了一种奇怪的病，全身无力，这件事被早想篡位的义和知道后，义和便起兵造反，炎帝被困荒山之中．兵变的事被炎帝最忠诚的战将刑天知道后，刑天立即带兵前往平乱．刑天的部下虽然勇猛，但是义和兵众实在太多，刑天的部队全部被灭．刑天一怒之下以自己的血祭战戈，战戈得饮主人之血不但变的锋利无比，而且威力无边．刑天凭一人一戈将数万叛军悉数杀尽，义和也死在了刑天的战戈下．刑天成功的救下了炎帝，但是自己却因身受多处致命伤，最终全身血淋淋的站着死去。</div>

<div style="text-align: center;"><img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150814131203366.jpg" style="width: 400px; height: 515px;" /></div>
'),
('7','<div>也许应验了那句话，天下分久必合，合久必分。华夏大地陷入了分裂的局面，战事连连，百姓生活在水生火热之中。华夏被分为五个大部落。这五个部落中以黄帝和蚩尤的部落势力最大，黄帝因不愿看到百姓因战争而受苦，便想停止这种战争局面，要想叫无休止的战争停止谈何容易？最后只得使用以暴制暴的方式来解决．先后分别消灭并收服了那三个较弱的部落。最后集中全力在逐鹿与蚩尤展开决战。蚩尤兵败逃跑。黄帝命应龙（应龙：龙族的一类，长着翅膀的龙．在机缘巧合下被黄帝收服。）前往追杀。应龙不负期望将蚩尤杀死并割下蚩尤的头颅献给黄帝</div>

<div style="text-align: center;"><img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/2015081413173158.jpg" style="width: 400px; height: 224px;" /></div>
'),
('8','<div>黄帝战胜蚩尤多年以后，黄帝的后裔颛顼登位。颛顼颁布了众多法典将华夏大地治理的很繁荣。法典给大部分人带来了利益，但是也侵害一小部分人的利益。共工就是被侵害的最严重一人，他纠结了很多人一起起兵造反。一路攻城掠地所向无敌，直逼不周山（王城所在）。祝融身为大将，带兵下山迎战。祝融武艺与法术厉害无比，而带兵之道更是远胜与共工，共工一方全军覆没。仅剩共工一人。共工宁死不投降，最后盛怒之下一头撞向不周仙山．共工法力何等之强？这一撞可不得了，竟将不周山撞翻。这不周山不仅仅是王城所在，也是擎天柱．不周山一倒，大灾难降临了。柱子一断，半边天空就坍塌下来，露出石骨嶙峋的大窟窿，顿时天河倾泻，洪水泛，洪水泛滥，生灵涂炭。</div>

<div style="text-align: center;"><img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/2015081412405336.jpg" style="width: 400px; height: 371px;" /></div>
'),
('9','<div>盘古开天辟地女娲造人之后，水神共工一向与火神祝融不合，他率领虾兵蟹将，向火神发动进攻。火神祝融驾着遍身冒着烈焰的火龙出来迎战。水神共工命令相柳和 浮游将三江五海的水汲上来，往祝融他们那里倾去。刹时间长空中浊浪飞泻，黑涛翻腾，白云被淹没，神火又被浇熄了。可是大水一退，神火又烧了起来，加上祝融 请来风神帮忙，风助火威，火乘风势，炽炽烈烈地直扑共工。共工他们想留住大水来御火，可是水泻千里，哪里留得住。火焰又长舌般地卷来，共工他们被烧得焦头 烂额，东倒西歪。共工率领水军且战且退，祝融直逼水宫，水神共工他们只好硬着头皮出来迎战。代表光明的火神祝融获得了全胜。浮游活活气死，相柳逃之夭夭， 共工心力交瘁，狼狈地向天边逃去。共工一直逃到不周山，回头一看，追兵已近。共工又羞又愤，就一头向山腰撞去，&ldquo;哗啦啦&rdquo;一声巨响，不周山竟给共工撞折 了。不周山一倒，大灾难降临了。原来不周山是根撑天的大柱，柱子一断，半边天空就坍塌下来，露出石骨嶙峋的大窟窿，顿时天河倾泻，洪水泛滥。著名的&ldquo;水火 不相容&rdquo;典故即源于这场大战。后来女娲为了补天四处收集五色石，炼了三万六千五百零一块石头将天门补上，用了三万六千五百块，但剩下了一块未用。</div>

<div style="text-align: center;"><img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/2015081413233169.jpg" style="width: 400px; height: 365px;" /></div>
'),
('10','<div>尧帝在位时，天下大旱，原因很简单，天上同时出现十个太阳．后羿为解决大旱问题，自己锻炼出一把威力无限的巨弓．后羿在弓上注入法力．然后携弓登上高山，以自己法力凝聚成无形之箭，引弓射日．本想把十个太阳全射下来，但是想到如果全都射下来，大地将陷入无止境的黑暗，所以便留了一个太阳在天上。</div>

<div>&nbsp;</div>

<div align="center"><img alt="" src="http://001taobaoservice.oss-cn-beijing.aliyuncs.com/img/20150724141416536.jpg" style="height: 220px; width: 220px; " /></div>
');
DROP TABLE IF EXISTS  `sub_task_info`;
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

insert into `sub_task_info`(`id`,`task_key`,`type`,`amount`,`status`,`priority`,`benefit_persion`,`benefit_type`,`desc`) values
('1','GOOD_COMMENT_TIME_LIMIT','BASIC','0','NORMAL','100','FREE','POINT','好评时效'),
('2','GOOD_COMMENT_CONTENT','BASIC','0','NORMAL','99','FREE','POINT','好评内容'),
('3','NEED_WANGWANG_TALK','APPRECIATION',0.5,'NORMAL','98','RECEIVER','POINT','需要旺旺聊天'),
('4','ZHI_DING_SHOU_HUO_DI_ZHI','APPRECIATION',0.5,'NORMAL','97','RECEIVER','POINT','指定收货地址'),
('5','ZHI_DING_JIE_SHOU_REN','APPRECIATION',0.5,'NORMAL','97','PLATFORM','POINT','指定接手人'),
('6','PI_LIANG_FA_BU','APPRECIATION',0.5,'NORMAL','96','PLATFORM','POINT','批量发布'),
('7','NO_REPEAT_TASK','APPRECIATION',0.5,'NORMAL','95','PLATFORM','POINT','一天内禁止重复接手');
DROP TABLE IF EXISTS  `task_basic`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `task_basic_log`;
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

DROP TABLE IF EXISTS  `task_order`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `task_order_sub_task_info`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `user_account`;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

insert into `user_account`(`id`,`current_balance`,`current_rest_points`,`locked_balance`,`locked_points`,`user_id`) values
('1','0','0','0','0','1'),
('2','169','0','0','0','2'),
('3','0','0','0','0','3'),
('4','0','0','0','0','4'),
('5','0','0','0','0','5'),
('6','0','0','0','0','1000'),
('7','0','0','0','0','1001'),
('8','0','0','0','0','1002');
DROP TABLE IF EXISTS  `user_account_log`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `user_base`;
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
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8;

insert into `user_base`(`id`,`user_name`,`password`,`regTime`,`status`,`cao_zuo_ma`,`bind_email`,`bind_telphone`,`bind_qq`,`bind_alipay`) values
('1','01jiangwei01@163.com','96e79218965eb72c92a549dd5a330112','2015-02-13 14:36:22','NORMAL','8e683187a00e5d462a4aeee69e9d3d9c','01jiangwei01@163.com','','346745719','02jiangwei02'),
('2','02jiangwei02','96e79218965eb72c92a549dd5a330112','2015-03-13 22:31:23','NORMAL','6dbf9ac2da09ee1d3debf5a51873ec6d','02jiangwei02@163.com',null,'222222222','02jiangwei02'),
('3','馋嘴猫1216','dfa3b5a3d2552f5138f0fd84a228e1d5','2015-03-16 18:36:09','NORMAL',null,'305844838@qq.com',null,null,null),
('4','么么哒∞纣王','a1563a41cb67498c664ca87a69d13c5c','2015-03-17 13:01:58','NORMAL',null,null,'18975004759',null,null),
('5','18729283756','b0b7520de8f1b973861f7d578dd156c9','2015-03-17 15:18:49','NORMAL',null,null,'18729283756',null,null),
('1000','03jiangwei03','96e79218965eb72c92a549dd5a330112','2015-03-17 15:35:43','NORMAL','8e683187a00e5d462a4aeee69e9d3d9c','01jiangwei01@163.com','','346745719','02jiangwei02'),
('1001','liyue6288','49c884257d5cad4b1fb5ce4fb9d432e8','2015-03-20 14:28:12','NORMAL',null,null,'15288786922',null,null),
('1002','huangkui','4bea4e74900bf9108adfcaca7807a56d','2015-03-20 19:04:07','NORMAL',null,null,'18638270138',null,null);
DROP TABLE IF EXISTS  `user_link`;
CREATE TABLE `user_link` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `link_type` varchar(30) NOT NULL,
  `link_value` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS  `valid_info`;
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

DROP TABLE IF EXISTS  `yanzhengma_log`;
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

insert into `yanzhengma_log`(`id`,`code`,`type`,`value`,`create_time`,`active_time`,`exp_time`,`enabled`,`user_id`,`tran_type`) values
('18','489157','email','305844838@qq.com','2015-03-16 18:35:24','2015-03-16 18:36:09','2015-03-16 18:35:24','0',null,'Reg'),
('19','145972','email','281013886@qq.com','2015-03-16 20:18:24',null,'2015-03-16 20:18:24','1',null,'Reg'),
('20','191857','phone','18975004759','2015-03-17 13:01:14','2015-03-17 13:01:58','2015-03-17 13:01:14','0',null,'Reg'),
('21','594267','phone','18729283756','2015-03-17 15:18:05','2015-03-17 15:18:49','2015-03-17 15:18:05','0',null,'Reg'),
('23','158320','phone','15288786922','2015-03-20 14:27:46','2015-03-20 14:28:12','2015-03-20 14:27:46','0',null,'Reg'),
('24','314695','phone','18638270138','2015-03-20 19:03:18','2015-03-20 19:04:07','2015-03-20 19:03:18','0',null,'Reg'),
('25','428613','phone','15001279241','2015-03-20 19:09:07',null,'2015-03-20 19:09:07','1',null,'Reg'),
('26','123746','phone','18532512030','2015-04-03 14:55:51',null,'2015-04-03 14:55:51','0',null,'Reg'),
('27','345768','phone','18532512030','2015-04-03 15:02:05',null,'2015-04-03 15:02:05','1',null,'Reg');
SET FOREIGN_KEY_CHECKS = 1;

