package com.kboss.model.right;

import com.jfinal.plugin.activerecord.Model;

public class Group extends Model<Group> {
	public static Group dao = new Group();
	public static String tablename = "KB_RIGHT_GROUP";
}
