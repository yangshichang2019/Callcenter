package com.kboss.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import com.kboss.config.Constant;
import com.kboss.model.right.User;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class UserDirective implements TemplateDirectiveModel{
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars,TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			String str = "";
			String type = "REALNAME";
			
			if(Constant.userCache.size()==0) {
				List<User> userList = User.dao.find("select * from "+User.tablename);
				Map<String, User> map = new HashMap<String, User>();
				for(int i=0;i<userList.size();i++){
					User u = userList.get(i);
					map.put(u.get("USERNAME")+"", u);
				}
				Constant.userCache.clear();
				Constant.userCache.putAll(map);
			}
			User user = Constant.userCache.get(params.get("key").toString());
			if(user!=null) {
				if (params.containsKey("type") && params.get("type") != null&&!params.get("type").equals("")) {
					type = params.get("type").toString();
				}
				str = user.get(type);
				
			}else {
				str = "-";
			}
	        env.getOut().write(str);
	        env.getOut().flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
