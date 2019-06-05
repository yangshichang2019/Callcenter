package com.kboss.directive;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kboss.config.Constant;
import com.kboss.model.database.DataBase;



import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class DataBaseDirective implements TemplateDirectiveModel {
	public void execute(Environment env, Map params, TemplateModel[] loopVars,TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			String str = "";
			String type = params.get("type").toString();
			String code = params.get("code").toString();
			String value = "";
			if (params.containsKey("value") && params.get("value") != null&&!params.get("value").equals("")) {
				value = params.get("value").toString();
			}
			String name = "";
			if (params.containsKey("name") && params.get("name") != null&&!params.get("name").equals("")) {
				name = params.get("name").toString();
			}
			String cssClass = "";
			if (params.containsKey("cssClass") && params.get("cssClass") != null&&!params.get("cssClass").equals("")) {
				cssClass = params.get("cssClass").toString();
			}
			
			LinkedHashMap<String, DataBase> map = Constant.dataBaseCache.get(code);
			if(map==null) {
				List<DataBase> list = DataBase.dao.find("select * from "+DataBase.tablename+" where parent_id=(select id from "
									+ DataBase.tablename+" where code=?) and enable_flag='T' order by sort_id asc,id asc",code);
				if(list.size()>0) {
					map = new LinkedHashMap<String, DataBase>();
					DataBase dataBase = null;
					for(int i=0;i<list.size();i++) {
						dataBase = list.get(i);
						map.put(dataBase.getStr("CODE"), dataBase);
					}
					Constant.dataBaseCache.put(code,map);
				}
			}
			if(map!=null) {
				if(type.equals("text")) {
					if(!value.equals("")) {
						DataBase dataBase = map.get(value);
						if(dataBase!=null) {
							str = str + "<font color=\""+dataBase.getStr("COLOR")+"\">"+dataBase.getStr("NAME")+"</font>";
						}else {
							str = str + value;
						}
					}
				}else if(type.equals("radio")) {
					for(String key:map.keySet()) {
						DataBase dataBase = map.get(key);
						str = str +"<input type=\"radio\" name=\""+name+"\" value=\""+dataBase.get("CODE")+"\"";
						if(!cssClass.equals("")) {
							str = str + " class=\""+cssClass+"\"";
						}
						if(dataBase.get("CODE").equals(value)) {
							str = str+" checked=\"checked\"";
						}
						str = str + "/>"+dataBase.get("NAME");
					}
				}else if(type.equals("select")) {
					for(String key:map.keySet()) {
						DataBase dataBase = map.get(key);
						str = str +"<option value=\""+dataBase.get("CODE")+"\"";
						if(dataBase.get("CODE").equals(value)) {
							str = str+" selected=\"selected\"";
						}
						str = str + ">"+dataBase.get("NAME")+"</option>";
					}
				}
				
			}
			
			
	        env.getOut().write(str);
	        env.getOut().flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
