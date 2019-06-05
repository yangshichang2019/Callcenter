package com.kboss.directive;

import java.io.IOException;
import java.util.Map;

import com.kboss.config.Constant;
import com.kboss.model.right.Dept;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class DeptDirective  implements TemplateDirectiveModel  {

	@Override
	public void execute(Environment env, Map params, TemplateModel[] arg2,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			String str = "";
			String key = params.get("key").toString();
			if(!key.equals("")) {
				Dept dept = Constant.deptCache.get(key);
				if(dept!=null) {
					str = dept.get("NAME");
				}
			}
	        env.getOut().write(str);
	        env.getOut().flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
