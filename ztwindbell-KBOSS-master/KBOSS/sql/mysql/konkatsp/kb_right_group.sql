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