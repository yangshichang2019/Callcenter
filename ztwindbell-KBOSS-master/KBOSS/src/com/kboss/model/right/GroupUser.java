package com.kboss.model.right;

import com.jfinal.plugin.activerecord.Model;

public class GroupUser extends Model<GroupUser> {
	public static GroupUser dao = new GroupUser();
	public static String tablename = "KB_RIGHT_GROUP_USER";
}
