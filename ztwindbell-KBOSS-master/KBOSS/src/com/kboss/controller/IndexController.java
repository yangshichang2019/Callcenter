package com.kboss.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.kboss.config.Constant;
import com.kboss.interceptors.AuthInterceptor;
import com.kboss.model.right.Right;
import com.kboss.model.right.RoleRight;
import com.kboss.model.right.User;
import com.kboss.model.right.UserLogin;
import com.kboss.model.right.UserRole;
import com.kboss.system.tool.Tree;
import com.kboss.system.tool.ZTool;
import com.zhangtao.tool.DateTool;
import com.zhangtao.tool.DeviceTool;
import com.zhangtao.tool.RequestTool;

public class IndexController extends Controller {
	@Before(AuthInterceptor.class)
	public void index(){
		User loginUser  = getSessionAttr(Constant.SESSION_USER);
		List<Right> headRight = new ArrayList<Right>();
		String menuStr = "";
		if(loginUser.get("USERNAME").equals("admin")||loginUser.get("USERNAME").equals("1802")) {
			headRight = Right.dao.find("select * from "+
					Right.tablename+" where parent_id=0 order by sort_id asc,id asc");

			Right first = headRight.get(0);
			List<Right> leftRight = Right.dao.find("select * from "+
					Right.tablename+" where parent_id>0 and num like '"+
					first.getStr("NUM")+"%' order by sort_id asc,id asc");
			menuStr =Tree.getLeftMenuTree(leftRight,first.getInt("ID"));
		}else {
			headRight = Right.dao.find("select * from "+
				Right.tablename+" where id in (select right_id from "+
				RoleRight.tablename+" where role_id in (select role_id from "+
				UserRole.tablename+" where username =?) ) and parent_id=0 order by sort_id asc,id asc",loginUser.get("USERNAME"));
			if(headRight.size()>0){
				Right first = headRight.get(0);
				List<Right> leftRight = Right.dao.find("select * from "+
						Right.tablename+" where id in (select right_id from "+
						RoleRight.tablename+" where role_id in (select role_id from "+
						UserRole.tablename+" where username =?) ) and parent_id>0 and num like '"+
						first.getStr("NUM")+"%' order by sort_id asc,id asc",loginUser.get("USERNAME"));
				menuStr =Tree.getLeftMenuTree(leftRight,first.getInt("ID"));
			}
		}

		setAttr("headRight", headRight);
		setAttr("menuStr", menuStr);
		render("/view/index.html");
	}
	
	/**
	 * 左侧菜单加载
	 */
	@Before(AuthInterceptor.class)
	public void leftRight() {
		User loginUser = getSessionAttr(Constant.SESSION_USER);
		Integer PARENT_ID = getParaToInt("PARENT_ID",0);
		if(loginUser.get("USERNAME").equals("admin")||loginUser.get("USERNAME").equals("1802")) {
			Right right = Right.dao.findById(PARENT_ID);
			List<Right> leftRight = Right.dao.find("select * from "+
					Right.tablename+" where parent_id>0 and num like '"+
					right.getStr("NUM")+"%' order by sort_id asc,id asc");
			String menuStr =Tree.getLeftMenuTree(leftRight,right.getInt("ID"));
			renderText(menuStr);
		}else {
			Right right = Right.dao.findById(PARENT_ID);
			List<Right> leftRight = Right.dao.find("select * from "+
					Right.tablename+" where id in (select right_id from "+
					RoleRight.tablename+" where role_id in (select role_id from "+
					UserRole.tablename+" where username =?) ) and parent_id>0 and num like '"+
					right.getStr("NUM")+"%' order by sort_id asc,id asc",loginUser.get("USERNAME"));
			String menuStr =Tree.getLeftMenuTree(leftRight,right.getInt("ID"));
			renderText(menuStr);
		}
	}
	public void login(){
		render("/view/login.html");
	}

	public void check(){
		String username = getPara("username","").trim();
		String password =ZTool.MD5(getPara("password","").trim());
		User user = User.dao.findFirst("select * from "+
				User.tablename+" where username=? and password=? and ENABLE_FLAG='T'",username,password);
		if(user!=null) {
			setSessionAttr(Constant.SESSION_USER, user);
			setCookie("KBOSS_LOGIN_USER", username, -1,"/");
			user.set("LAST_LOGIN_TIME", DateTool.getNowTime()).update();
			
			//记录登陆信息
			UserLogin userLogin = new UserLogin();
			userLogin.set("APP", "KBOSS");
			userLogin.set("IP", RequestTool.getIp(getRequest()));
			if(DeviceTool.isMobileDevice(getRequest())) {
				userLogin.set("CLIENT", "MOBILE");
			}else {
				userLogin.set("CLIENT", "PC");
			}
			userLogin.set("SESSIONID", getSession().getId());
			userLogin.set("CREATE_USER", user.get("USERNAME"));
			userLogin.set("CREATE_TIME", DateTool.getNowTime());
			userLogin.save();
			System.out.println(user.get("REALNAME")+"("+user.get("USERNAME")+")登陆系统："+DateTool.getNowTime());
			renderText("{\"result\":\"success\",\"message\":\"登录成功\"}");
		}else {

			renderText("{\"result\":\"error\",\"message\":\"登录失败\"}");
		}
	}

	@Before(AuthInterceptor.class)
	public void editPassword(){

		render("/view/editPassword.html");
	}
	
	@Before(AuthInterceptor.class)
	public void savePassword(){
		User loginUser = getSessionAttr(Constant.SESSION_USER);
		User user = User.dao.findById(loginUser.getInt("ID"));
		String newpassword = getPara("newpassword");
		String olderpassword = getPara("olderpassword");
		if(!user.getStr("PASSWORD").equals(ZTool.MD5(olderpassword))){
			renderText("{\"statusCode\":\"300\",\"message\":\"原密码不正确\"}");
			return;
		}

		loginUser.set("PASSWORD", ZTool.MD5(newpassword)).update();
		renderText("{\"statusCode\":\"200\",\"message\":\"修改成功！\",\"callbackType\":\"closeCurrent\"}");
	}
	
	
	@Before(AuthInterceptor.class)
	public void page(){
		String num = getPara("num","");
		if(!num.equals("")) {
			Right right = Right.dao.findFirst("select * from "+Right.tablename+" where num=?",num);
			setAttr("right",right);
		}else {
			String url = getPara("url","");
			setAttr("url",url);
		}
		render("/view/page.html");
	}
	@Before(AuthInterceptor.class)
	public void logout(){
		getSession().invalidate();
		removeCookie("KBOSS_LOGIN_USER");
		redirect("/login");
	}
	
}
