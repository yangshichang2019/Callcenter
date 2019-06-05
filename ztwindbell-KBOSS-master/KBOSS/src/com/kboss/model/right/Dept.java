package com.kboss.model.right;

import com.jfinal.plugin.activerecord.Model;

public class Dept extends Model<Dept> {
	public static Dept dao = new Dept();
	public static String tablename = "kb_right_dept";
}
