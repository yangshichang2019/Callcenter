package com.kboss.system.cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kboss.config.Constant;
import com.kboss.model.database.DataBase;
import com.kboss.model.right.Dept;

public class InitCache {
	public static void init(){
		loadDeptCache();
	}
	
	
	public static void loadDeptCache(){
		try {
			List<Dept> list = Dept.dao.find("select * from "+Dept.tablename);
			Map<String,Dept> map = new HashMap<String, Dept>();
			for(int i=0;i<list.size();i++) {
				Dept dept = list.get(i);
				map.put(dept.get("ID").toString(), dept);
			}
			Constant.deptCache.clear();
			Constant.deptCache.putAll(map);
			System.out.println("部门名称已缓存："+map.size()+"个");
		}catch (Exception e) {
			System.out.println("部门名称缓存失败");
		}
	}
	
	public static void loadUserCache(){
		
	}
}
