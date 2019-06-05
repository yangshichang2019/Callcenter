package com.kboss.controller;

import java.io.File;
import java.util.Date;

import com.jfinal.aop.Before;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.kboss.config.Constant;
import com.kboss.interceptors.AuthInterceptor;
import com.kboss.model.right.User;
import com.kboss.model.system.FileUpload;
import com.zhangtao.jfinal.ExtController;
import com.zhangtao.tool.DateTool;
@Before(AuthInterceptor.class)
public class SysController extends ExtController {
	//xheditor上传
	public void upload(){
		User loginUser = getSessionAttr(Constant.SESSION_USER);
		String model = getPara("model","other");
		//用于防止乱上传文件
		String seq = getPara("seq","none");
		String fun = getPara("fun","none");
		String filedir=PathKit.getWebRootPath().replace("\\KBOSS", "") + "\\upload\\"+model+"\\"+loginUser.get("ID")+"\\"+DateTool.formatDateToStr("yyyy-MM", new Date())+"\\";
		String err = "";
		String newFileUrl= "";
		String filedataFileName = "";
		try {
			UploadFile file = getFile("filedata", filedir, 30*1024*1024);
			if(file!=null) {
				filedataFileName = file.getFileName();
				String ext = filedataFileName.substring(filedataFileName.lastIndexOf("."));
				String newfileName = File.separator + System.currentTimeMillis() + ext;
				File dest = new File(filedir+ newfileName);
				file.getFile().renameTo(dest);
				newfileName = newfileName.replace("\\", "");
				newFileUrl= "/upload/"+model+"/"+loginUser.get("ID")+"/"+DateTool.formatDateToStr("yyyy-MM", new Date())+"/"+newfileName;
				
				FileUpload fileUpload = new FileUpload();
				fileUpload.set("MODEL", model);
				fileUpload.set("FUN",fun );
				fileUpload.set("FILENAME", filedataFileName);
				fileUpload.set("FILEURL", newFileUrl);
				fileUpload.set("FILESIZE", dest.length());
				fileUpload.set("EXT",ext );
				fileUpload.set("SEQ",seq );
				fileUpload.set("CREATE_USER",loginUser.get("USERNAME") );
				fileUpload.save();
			}else {
				err = "没有找到文件";
			}
		}catch (Exception e) {
	        e.printStackTrace();
	        newFileUrl = "";  
	        if(e.getMessage()!=null) {
	        	err = e.getMessage();
	        } else {
	        	err = "上传失败";
	        }
		}
		renderText("{\"err\":\"" + err + "\",\"msg\":{\"url\":\"" + newFileUrl + "\",\"localfile\":\""+filedataFileName+"\"}}");  
	}
	//uploadify上传
	public void uploadify(){
		User loginUser = getSessionAttr(Constant.SESSION_USER);
		String model = getPara("model","other");
		String filedir=PathKit.getWebRootPath().replace("\\KBOSS", "") + "\\upload\\"+model+"\\";
		String err = "";
		String newFileUrl= "";
		String filedataFileName = "";
		try {
			UploadFile file = getFile("Filedata", filedir, 5*1024*1024);
			if(file!=null) {
				filedataFileName = file.getFileName();
				String ext = filedataFileName.substring(filedataFileName.lastIndexOf("."));
				if(".jpg.jpeg.gif.png".indexOf(ext)==-1) {
					renderText("文件格式必须是jpg、jpeg、gif、png");
					return;
				}
				
				String newfileName = File.separator + System.currentTimeMillis() + ext;
				File dest = new File(filedir+ newfileName);
				file.getFile().renameTo(dest);
				newFileUrl= "/upload/"+model+"/"+newfileName.replace("\\", "");
				
				FileUpload fileUpload = new FileUpload();
				fileUpload.set("MODEL", model);
				fileUpload.set("FILENAME", filedataFileName);
				fileUpload.set("FILEURL", newFileUrl);
				fileUpload.set("FILESIZE", dest.length());
				fileUpload.set("EXT",ext );
				fileUpload.set("CREATE_USER",loginUser.get("USERNAME") );
				fileUpload.save();
				renderText("1");
				return;
			}else {
				renderText("没有找到文件！");
				return;
			}
		}catch (Exception e) {
	        e.printStackTrace();
	        newFileUrl = "";  
	        if(e.getMessage()!=null) {
	        	renderText( e.getMessage());
				return;
	        } else {
	        	renderText("上传失败！");
				return;
	        }
		}
		
	}
	/***
	 * 上传文件管理
	 */
	public void toFileUploadManage(){
		String where = " where 1=1";
		String filename = getPara("filename","");
		if(!filename.equals("")) {
			where = where + " and filename like '%"+filename+"%' ";
			setAttr("filename", filename);
		}
		String create_user = getPara("create_user","");
		if(!create_user.equals("")) {
			where = where + " and create_user = '"+create_user+"' ";
			setAttr("create_user", create_user);
		}
		String is_use = getPara("is_use","");
		if(!is_use.equals("")) {
			where = where + " and is_use='"+is_use+"' ";
			setAttr("is_use",is_use);
		}
		
		String orderby = " order by id desc";
		Page<FileUpload> pd = FileUpload.dao.paginate(getParaToInt("pageNumber",1), getParaToInt("pageSize",20),
				"select * ", " from "+FileUpload.tablename+where+orderby);
		setAttr("pd", pd);
		render("/view/sys/toFileUploadManage.html");
	}
	/***
	 * 上传文件删除
	 */
	public void deleteFileUpload(){
		Integer id = getParaToInt("id",0);
		FileUpload fileUpload = FileUpload.dao.findById(id);
		if(fileUpload!=null) {
			String filedir=PathKit.getWebRootPath().replace("\\KBOSS", "") + fileUpload.getStr("FILEURL").replace("/","\\\\");
			File file = new File(filedir);
			if(file.isFile()&&file.exists()) {
				file.delete();
			}
			Db.update("delete from "+FileUpload.tablename+" where id = ?",id);
		}
		renderDwzSucAndFresh();
	}
}
