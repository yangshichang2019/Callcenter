DROP TABLE IF EXISTS `kb_db_param`;
CREATE TABLE IF NOT EXISTS `kb_db_param` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MODEL` varchar(20),
  `NAME` varchar(50),
  `CODE` varchar(50),
  `VALUE` varchar(200),
  `DESC` varchar(200),
  `SORT_ID` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
insert into `kb_db_param` (`MODEL`,`NAME`,`CODE`,`VALUE`,`DESC`) values 
('SYS','系统名称','SYS_NAME','KBOSS',''),
('SYS','应用路径','SYS_PATH','/KBOSS','');

DROP TABLE IF EXISTS `kb_database`;
CREATE TABLE IF NOT EXISTS `kb_database` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11),
  `NAME` varchar(50),
  `CODE` varchar(50),
  `COLOR` varchar(20) NOT NULl DEFAULT 'none',
  `SORT_ID` int(11) NOT NULL DEFAULT 0,
  `ENABLE_FLAG` varchar(1) NOT NULL DEFAULT 'T',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

insert into kb_database (`PARENT_ID`,`NAME`,`CODE`,`COLOR`) values 
(0,'权限_打开方式','RIGHT_TARGET','none'),
(1,'面板','navTab','none'),
(1,'对话框','dialog','none'),
(0,'通用_是/否','TRUE/FALSE','none'),
(4,'是','true','none'),
(4,'否','false','none'),
(0,'权限_角色_所属模块','RIGHT_ROLE_MODEL','none'),
(7,'通用','COMMON','none'),
(7,'其它','OTHER','none'),
(0,'权限_部门_类别','RIGHT_DEPT_CATE','none'),
(10,'集团','JITUAN','none'),
(10,'公司','COMPANY','none'),
(10,'部门','DEPT','none'),
(10,'大组','B_GROUP','none'),
(10,'小组','S_GROUP','none'),
(10,'网点','NET','none'),
(10,'其它','OTHER','none'),
(0,'通用_可用/禁用','T/F','none'),
(18,'可用','T','none'),
(18,'禁用','F','none'),
(0,'权限_工作组_所属模块','RIGHT_GROUP_MODEL','none'),
(21,'通用','COMMON','none'),
(21,'其它','OTHER','none'),
(0,'基础数据_数据字典_颜色','DATABASE_COLOR','none'),
(24,'默认','none','none'),
(24,'红色','red','none'),
(24,'绿色','green','none'),
(24,'蓝色','blue','none'),
(24,'灰色','gray','none'),
(0,'基础数据_配置参数_所属模块','DATABASE_PARAM_MODEL','none'),
(30,'系统','SYS','none'),
(30,'其它','OTHER','none'),
(0,'通用_是/否','SHIFOU','none'),
(46,'是','T','none'),
(46,'否','F','none');