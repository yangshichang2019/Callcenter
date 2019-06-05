package com.kboss.config;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.kboss.controller.DataBaseController;
import com.kboss.controller.IndexController;
import com.kboss.controller.RightController;
import com.kboss.controller.SysController;
import com.kboss.interceptors.DirectiveInterceptor;
import com.kboss.model.database.DBParam;
import com.kboss.model.database.DataBase;
import com.kboss.model.right.Dept;
import com.kboss.model.right.Group;
import com.kboss.model.right.GroupUser;
import com.kboss.model.right.Right;
import com.kboss.model.right.Role;
import com.kboss.model.right.RoleRight;
import com.kboss.model.right.User;
import com.kboss.model.right.UserLogin;
import com.kboss.model.right.UserRole;
import com.kboss.model.system.FileUpload;
import com.kboss.system.cache.InitCache;
import com.zhangtao.tool.DateTool;


public class Config extends JFinalConfig {
	public void configConstant(Constants me) {
		me.setDevMode(false);
		me.setViewType(ViewType.FREE_MARKER);
	}
	//KBOSS模式，部署项目时，请注意
	public void configPlugin(Plugins me) {
		loadPropertyFile("config.properties");
		ActiveRecordPlugin arp = null;
		if(getProperty("dbtype").equals("mysql")){
			C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("mysql_"+getProperty("dbname")+"_jdbcURL"),getProperty("mysql_username"),getProperty("mysql_password"));
			me.add(c3p0Plugin);
			arp = new ActiveRecordPlugin(c3p0Plugin);
			me.add(arp);
		}else if(getProperty("dbtype").equals("jndi")){
			DataSource datasource = null;
			try {
				datasource = (DataSource)new InitialContext().lookup("java:/comp/env/Konka/Zs");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			arp = new ActiveRecordPlugin(datasource);
		}
		/*

		*/
		arp.setShowSql(false);
		me.add(arp);
		arp.setDialect(new AnsiSqlDialect());
		//权限模块
		arp.addMapping(Right.tablename,"ID",Right.class);
		arp.addMapping(User.tablename,"ID",User.class);
		arp.addMapping(Role.tablename,"ID",Role.class);
		arp.addMapping(UserRole.tablename,"ROLE_ID",UserRole.class);
		arp.addMapping(RoleRight.tablename,"RIGHT_ID",RoleRight.class);
		arp.addMapping(Dept.tablename,"ID",Dept.class);
		arp.addMapping(Group.tablename,"ID", Group.class);
		arp.addMapping(GroupUser.tablename, GroupUser.class);
		arp.addMapping(UserLogin.tablename,"ID", UserLogin.class);
		//基础数据
		arp.addMapping(DataBase.tablename,"ID", DataBase.class);
		arp.addMapping(DBParam.tablename,"ID", DBParam.class);
		//系统功能
		arp.addMapping(FileUpload.tablename,"ID", FileUpload.class);
	}
	
	public void configInterceptor(Interceptors me) { 
		me.add(new DirectiveInterceptor());
	}
	
	public void configHandler(Handlers me) {
		
	}
	
	public void configRoute(Routes me) {

		me.add("/", IndexController.class);
		me.add("/right", RightController.class);
		me.add("/database", DataBaseController.class);
		me.add("/sys", SysController.class);
	}
	
	public void afterJFinalStart() {
		Db.update("update "+UserLogin.tablename+" set destory_time=? where destory_time is null and app='KBOSS'",DateTool.getNowTime());
		InitCache.init();
	}
	public void beforeJFinalStop() {
		
	}
	@Override
	public void configEngine(Engine arg0) {
		// TODO Auto-generated method stub
		
	}
}
