package com.kboss.model.right;

import com.jfinal.plugin.activerecord.Model;

public class UserRole extends Model<UserRole> {
	public static UserRole dao = new UserRole();
	public static String tablename = "kb_right_user_role";
}
