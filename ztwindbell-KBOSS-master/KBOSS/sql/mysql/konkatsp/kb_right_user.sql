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
  `IS_DELETE` varchar(1) NOT NULL DEFAULT 'F',
  `UPDATE_USER` varchar(50),
  `UPDATE_TIME` datetime default NULL,
	`CREATE_USER` varchar(50),
	`CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
insert into `kb_right_user` (`USERNAME`,`REALNAME`,`PASSWORD`,`EMAIL`,`CREATE_USER`) values 
('admin','管理员','7FEF6171469E80D32C0559F88B377245','ztwindbell@qq.com','admin');

ALTER TABLE `kb_right_user`
ADD INDEX `username` (`USERNAME`) USING BTREE,
ADD INDEX `realname` (`REALNAME`) USING BTREE,
ADD INDEX `dept_id` (`DEPT_ID`) USING BTREE;