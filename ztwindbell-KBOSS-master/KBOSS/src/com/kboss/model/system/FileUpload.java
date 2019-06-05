package com.kboss.model.system;

import com.jfinal.plugin.activerecord.Model;

public class FileUpload extends Model<FileUpload> {
	public static FileUpload dao = new FileUpload();
	public static String tablename = "kb_sys_uploadfile";
}
