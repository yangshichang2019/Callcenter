DROP TABLE IF EXISTS `kb_right`;
CREATE TABLE IF NOT EXISTS `kb_right` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11),
  `NUM` varchar(60),
  `REL` varchar(30),
	`NAME` varchar(50),
	`HREF` varchar(100),
	`TARGET` varchar(10),
	`FRESH` varchar(10),
	`EXTERNAL` varchar(10),
	`IS_PARENT` varchar(5) NOT NULL DEFAULT 'false',
  `SORT_ID` int(11) NOT NULL default 0,
  `UPDATE_USER` varchar(50),
  `UPDATE_TIME` datetime default NULL,
	`CREATE_USER` varchar(50),
	`CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

insert into `kb_right` (`PARENT_ID`,`NUM`,`REL`,`NAME`,`HREF`,`TARGET`,`FRESH`,`EXTERNAL`,`IS_PARENT`,`CREATE_USER`) values 
(0,'001','001','系统配置','','navTab','false','false','true','admin'),
(1,'001001','001001','用户权限','','navTab','false','false','true','admin'),
(2,'001001001','001001001','权限管理','right/toRightManage','navTab','true','false','false','admin'),
(2,'001001002','001001002','角色管理','right/toRoleManage','navTab','true','false','false','admin'),
(2,'001001003','001001003','用户管理','right/toUserManage','navTab','true','false','false','admin'),
(2,'001001004','001001004','部门管理','right/toDeptManage','navTab','true','false','false','admin'),
(2,'001001005','001001005','工作组管理','right/toGroupManage','navTab','true','false','false','admin'),
(1,'001002','001002','基础数据','','navTab','false','false','false','admin'),
(8,'001002001','001002001','配置参数','database/toDBParamManage','navTab','true','false','false','admin'),
(8,'001002002','001002002','数据字典','database/toDataBaseManage','navTab','true','false','false','admin');

DROP TABLE IF EXISTS `kb_right_user`;
CREATE TABLE IF NOT EXISTS `kb_right_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50),
  `REALNAME` varchar(30),
  `PASSWORD` varchar(50),
  `DEPT_ID` int(11),
  `EMAIL` VARCHAR(50),
  `PHONE` VARCHAR(50),
  `LAST_LOGIN_TIME` datetime,
  `ENABLE_FLAG` varchar(1) NOT NULL DEFAULT 'T',
  `UPDATE_USER` varchar(50),
  `UPDATE_TIME` datetime default NULL,
	`CREATE_USER` varchar(50),
	`CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
insert into `kb_right_user` (`USERNAME`,`REALNAME`,`PASSWORD`,`CREATE_USER`) values 
('admin','管理员','7FEF6171469E80D32C0559F88B377245','admin');


DROP TABLE IF EXISTS `kb_right_role`;
CREATE TABLE IF NOT EXISTS `kb_right_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MODEL` varchar(20),
  `NAME` varchar(100),
  `DESC` varchar(200),
  `UPDATE_USER` varchar(50),
  `UPDATE_TIME` datetime default NULL,
	`CREATE_USER` varchar(50),
	`CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
insert into `kb_right_role` (`MODEL`,`NAME`,`DESC`) values ('COMMON','超级管理员','拥有系统所有功能的权限');

DROP TABLE IF EXISTS `kb_right_role_right`;
CREATE TABLE IF NOT EXISTS `kb_right_role_right` (
	`ROLE_ID` int(11),
	`RIGHT_ID` int(11)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;
insert into `kb_right_role_right` (`ROLE_ID`,`RIGHT_ID`) values 
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10);

DROP TABLE IF EXISTS `kb_right_user_role`;
CREATE TABLE IF NOT EXISTS `kb_right_user_role` (
	`USERNAME` varchar(50),
	`ROLE_ID` int(11)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;
insert into `kb_right_user_role` (`USERNAME`,`ROLE_ID`) values 
('admin',1);

DROP TABLE IF EXISTS `kb_right_dept`;
CREATE TABLE IF NOT EXISTS `kb_right_dept` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11),
  `NUM` varchar(60),
	`NAME` varchar(50),
	`CATE` varchar(30),
	`DESC` varchar(200),
	`IS_PARENT` varchar(5) NOT NULL DEFAULT 'false',
  `SORT_ID` int(11) NOT NULL default 0,
  `UPDATE_USER` varchar(50),
  `UPDATE_TIME` datetime default NULL,
	`CREATE_USER` varchar(50),
	`CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `kb_right_group`;
CREATE TABLE IF NOT EXISTS `kb_right_group` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MODEL` varchar(20),
  `NAME` varchar(100),
  `DESC` varchar(200),
  `UPDATE_USER` varchar(50),
  `UPDATE_TIME` datetime default NULL,
	`CREATE_USER` varchar(50),
	`CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
insert into `kb_right_group` (`MODEL`,`NAME`,`DESC`) values ('COMMON','通用工作组','默认');

DROP TABLE IF EXISTS `kb_right_group_user`;
CREATE TABLE IF NOT EXISTS `kb_right_group_user` (
	`USERNAME` varchar(50),
	`GROUP_ID` int(11)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8;
insert into `kb_right_group_user` (`USERNAME`,`GROUP_ID`) values ('admin',1);