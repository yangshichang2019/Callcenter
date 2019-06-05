package com.kboss.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.kboss.base.BaseController;
import com.kboss.config.Constant;
import com.kboss.interceptors.AuthInterceptor;
import com.kboss.model.database.DBParam;
import com.kboss.model.database.DataBase;
@Before(AuthInterceptor.class)
public class DataBaseController extends BaseController {
	/**
	 * 数据字典
	 */
	public void toDataBaseManage(){
		Integer ID = getParaToInt("ID",0);
		Page<DataBase> pd = DataBase.dao.paginate(getParaToInt("pageNumber",1), getParaToInt("pageSize",20),
				"select *", "from "+DataBase.tablename+" where parent_id=? order by sort_id asc,id asc",ID);
		setAttr("pd",pd);
		setAttr("ID",ID);
		render("/view/database/toDataBaseManage.html");
	}
	public void toAddEditDataBase(){
		Integer ID = getParaToInt("ID",0);
		DataBase database = new DataBase();
		if(ID>0) {
			database = DataBase.dao.findById(ID);
		}else {
			Integer PARENT_ID = getParaToInt("PARENT_ID",0);
			database.set("PARENT_ID", PARENT_ID);
		}
		setAttr("database", database);
		render("/view/database/toAddEditDataBase.html");
	}
	public void toSaveDataBase(){
		Integer ID = getParaToInt("ID",0);
		DataBase database = new DataBase();
		database.set("NAME", getParaFilter("NAME"));
		database.set("CODE", getPara("CODE"));
		database.set("COLOR", getPara("COLOR"));
		database.set("SORT_ID", getPara("SORT_ID"));
		database.set("ENABLE_FLAG", getPara("ENABLE_FLAG"));
		if(ID>0) {
			database.set("ID", ID);
			database.update();
		}else {
			database.set("PARENT_ID", getParaToInt("PARENT_ID",0));
			database.save();
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"navTabId\":\""+getPara("navTabId","")+"\",\"callbackType\":\"closeCurrent\"}");
	}
	public void toClearDataBase(){
		
		Constant.dataBaseCache.clear();
		renderText("{\"statusCode\":\"200\",\"message\":\"清空成功\"}");
	}
	/**
	 * 系统参数
	 */
	public void toDBParamManage() {
		List<DBParam> list = DBParam.dao.find("select * from "+DBParam.tablename+" order by sort_id asc,id asc");
		setAttr("list",list);
		render("/view/database/toDBParamManage.html");
	}
	public void toAddEditDBParam(){
		Integer ID = getParaToInt("ID",0);
		DBParam dbParam = new DBParam();
		if(ID>0) {
			dbParam = DBParam.dao.findById(ID);
		}
		setAttr("dbParam", dbParam);
		render("/view/database/toAddEditDBParam.html");
	}
	public void toSaveDBParam() {
		Integer ID = getParaToInt("ID",0);
		DBParam dbParam = new DBParam();
		dbParam.set("MODEL", getPara("MODEL"));
		dbParam.set("NAME", getParaFilter("NAME"));
		dbParam.set("CODE", getPara("CODE"));
		dbParam.set("VALUE", getPara("VALUE"));
		dbParam.set("DESC", getPara("DESC"));
		dbParam.set("SORT_ID", getPara("SORT_ID"));
		if(ID>0) {
			dbParam.set("ID", ID);
			dbParam.update();
		}else {
			dbParam.save();
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"navTabId\":\""+getPara("navTabId","")+"\",\"callbackType\":\"closeCurrent\"}");
	}
}
