package com.kboss.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.jfinal.plugin.activerecord.Db;
import com.kboss.model.right.UserLogin;
import com.zhangtao.tool.DateTool;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		Db.update("update "+UserLogin.tablename+" set destory_time=? where sessionid=?",DateTool.getNowTime(),arg0.getSession().getId());
	}

}
