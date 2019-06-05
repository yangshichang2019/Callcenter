package com.kboss.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.kboss.config.Constant;
import com.kboss.model.right.User;
import com.kboss.model.right.UserLogin;
import com.kboss.system.tool.ZTool;
import com.zhangtao.tool.DateTool;
import com.zhangtao.tool.DeviceTool;
import com.zhangtao.tool.RequestTool;
public class AuthInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		User loginUser = controller.getSessionAttr(Constant.SESSION_USER);
		/**
		if(loginUser==null) {
			String kboss = ZTool.getCookiesByName(controller.getRequest(), "KBOSS_LOGIN_USER");
			if(!"".equals(kboss)) {
				loginUser = User.dao.findFirst("select * from "+User.tablename+" where username=?",kboss);
				controller.setSessionAttr(Constant.SESSION_USER,loginUser);
			}
		}
		
		if(loginUser==null) {
			loginUser = User.dao.findById(1);
			controller.setSessionAttr(Constant.SESSION_USER,loginUser);
		}
		**/
		if(loginUser==null) {
			String openeap = ZTool.getCookiesByName(controller.getRequest(), "openeap.UserID");
			if(!"".equals(openeap)) {
				loginUser = User.dao.findFirst("select * from "+User.tablename+" where username=?",openeap);
				controller.setSessionAttr(Constant.SESSION_USER,loginUser);
				controller.setCookie("KBOSS_LOGIN_USER", openeap, -1,"/");
				loginUser.set("LAST_LOGIN_TIME", DateTool.getNowTime()).update();
				
				//记录登陆信息
				UserLogin userLogin = new UserLogin();
				userLogin.set("APP", "KBOSS");
				userLogin.set("IP", RequestTool.getIp(controller.getRequest()));
				if(DeviceTool.isMobileDevice(controller.getRequest())) {
					userLogin.set("CLIENT", "MOBILE");
				}else {
					userLogin.set("CLIENT", "PC");
				}
				userLogin.set("SESSIONID", controller.getSession().getId());
				userLogin.set("CREATE_USER", loginUser.get("USERNAME"));
				userLogin.set("CREATE_TIME", DateTool.getNowTime());
				userLogin.save();
				System.out.println(loginUser.get("REALNAME")+"("+loginUser.get("USERNAME")+")登陆系统："+DateTool.getNowTime());
				
			}
		}
		
		//KBOSS不允许通过cookies登录
		
		String requestType = controller.getRequest().getHeader("X-Requested-With");
		if(loginUser!=null) {
			controller.setAttr("loginUser", loginUser);
			inv.invoke();
		}else {
			if(requestType!=null&&requestType.equals("XMLHttpRequest")) {
				controller.renderText("{\"statusCode\":\"301\",\"message\":\"登录超时！\"}");
			}else {
				controller.redirect("/login");
			}
		}
		
	}
}
