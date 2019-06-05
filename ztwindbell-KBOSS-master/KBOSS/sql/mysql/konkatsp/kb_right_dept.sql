DROP TABLE IF EXISTS `kb_right_dept`;
CREATE TABLE IF NOT EXISTS `kb_right_dept` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11),
  `NUM` varchar(60),
	`NAME` varchar(50),
	`CATE` varchar(30),
	`DESC` varchar(200),
	`IS_PARENT` varchar(5) NOT NULL DEFAULT 'false',
	`USER_C` int(11) NOT NULL DEFAULT 0,
  `SORT_ID` int(11) NOT NULL default 0,
  `UPDATE_USER` varchar(50),
  `UPDATE_TIME` datetime default NULL,
	`CREATE_USER` varchar(50),
	`CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
