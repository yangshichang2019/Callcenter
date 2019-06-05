package com.kboss.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.kboss.model.database.DataBase;
import com.kboss.model.right.Dept;
import com.kboss.model.right.User;


public class Constant {
	public static String SESSION_USER = "KBOSS_USER";
	public static Map<String,User> userCache = new HashMap<String, User>();
	public static Map<String,LinkedHashMap<String, DataBase>> dataBaseCache = new HashMap<String, LinkedHashMap<String,DataBase>>();

	public static Map<String,Dept> deptCache = new HashMap<String , Dept>();
}
