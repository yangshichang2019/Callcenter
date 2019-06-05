package com.kboss.model.database;

import com.jfinal.plugin.activerecord.Model;

public class DataBase extends Model<DataBase> {
	public static DataBase dao = new DataBase();
	public static String tablename = "KB_DATABASE";
}
