package com.kboss.interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.kboss.directive.DataBaseDirective;
import com.kboss.directive.DeptDirective;
import com.kboss.directive.UserDirective;

public class DirectiveInterceptor implements Interceptor {
	public static DataBaseDirective dataBaseDirective = new DataBaseDirective();
	public static DeptDirective deptDirective = new DeptDirective();
	public static UserDirective userDirective = new UserDirective();
	
	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		controller.setAttr("toDataBase", dataBaseDirective);
		controller.setAttr("toDept", deptDirective);
		controller.setAttr("toUser", userDirective);
		controller.setAttr("navTabId", controller.getPara("navTabId",""));
		inv.invoke();
	}
	
}
