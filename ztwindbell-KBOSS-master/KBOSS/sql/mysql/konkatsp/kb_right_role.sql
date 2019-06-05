
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