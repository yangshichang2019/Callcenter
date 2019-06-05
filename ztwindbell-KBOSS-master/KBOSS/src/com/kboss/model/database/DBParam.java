package com.kboss.model.database;

import com.jfinal.plugin.activerecord.Model;

public class DBParam extends Model<DBParam>{
	public static DBParam dao = new DBParam();
	public static String tablename = "KB_DB_PARAM";
}
