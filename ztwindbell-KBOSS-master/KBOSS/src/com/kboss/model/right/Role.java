package com.kboss.model.right;

import com.jfinal.plugin.activerecord.Model;

public class Role extends Model<Role> {
	public static Role dao = new Role();
	public static String tablename = "kb_right_role";
}
