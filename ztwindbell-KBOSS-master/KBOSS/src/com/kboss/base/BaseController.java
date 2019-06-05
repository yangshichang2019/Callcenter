package com.kboss.base;

import com.jfinal.core.Controller;
import com.kboss.system.tool.ZTool;

public class BaseController extends Controller{
	//过滤特殊字符
	public String getParaFilter(String name) {
		return ZTool.StringFilter(super.getPara(name));
	}
	public String getParaFilter(String name,String defaultvalue) {
		return ZTool.StringFilter(super.getPara(name,defaultvalue));
	}
}
