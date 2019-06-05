package com.kboss.model.right;

import com.jfinal.plugin.activerecord.Model;

public class User extends Model<User> {
	public static User dao = new User();
	public static String tablename = "kb_right_user";
}
