package com.kboss.model.right;

import com.jfinal.plugin.activerecord.Model;

public class UserLogin extends Model<UserLogin> {
	public static UserLogin dao = new UserLogin();
	public static String tablename ="kb_user_login";
}
