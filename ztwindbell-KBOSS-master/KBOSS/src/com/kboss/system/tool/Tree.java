package com.kboss.system.tool;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.kboss.model.right.Right;

public class Tree {
	//左侧菜单
	public static String getLeftMenuTree(List list,Integer parent_id) {
		String str = "";
		str = str + "<div class=\"accordion\" fillSpace=\"sidebar\">";
		for(int i=0;i<list.size();i++) {
			Right vo = (Right)list.get(i);
			if(vo.get("PARENT_ID").equals(parent_id)) {
				str = str + "<div class=\"accordionHeader\">";
				str = str + "<h2><span>Folder</span>"+vo.get("NAME")+"</h2>";
				str = str + "</div>";
				str = str + "<div class=\"accordionContent\">";
				str = str + makeMenuTree(list,vo.getInt("ID"),0);
				str = str + "</div>";
			}
		}	
		str = str + "</div>";
		return str;
	}
	public static String makeMenuTree(List list,Integer parent_id,Integer level) {
		String str ="";
		if(level==0) {
			str = str + "<ul class=\"tree treeFolder\">";
		}else {
			str = str + "<ul>";
		}
		Integer n = 0;
		for(int i=0;i<list.size();i++) {
			Right vo = (Right)list.get(i);
			if(vo.get("PARENT_ID").equals(parent_id)) {
				n = n + 1;
				str = str + "<li><a";
				if(!"".equals(vo.get("HREF"))) {
					String url = ZTool.addParamer(vo.getStr("HREF"),"navTabId="+vo.get("REL"));
					str = str + " target=\""+vo.get("TARGET")+"\" external=\""+vo.get("EXTERNAL");
					str = str +"\" rel=\""+vo.get("REL") + "\" fresh=\""+vo.get("FRESH")+"\"";
					str = str + " href=\"" + url + "\"";
				}
				str = str + ">" + vo.get("NAME")+"</a>";
				str = str + makeMenuTree(list,vo.getInt("ID"),level+1);	
				str = str + "</li>";
			}
		}	
		str = str + "</ul>";
		if(n==0) {
			str = "";
		}
		return str;
	}
	//生成ZTree树：id、num、name、is_parent
	public static String makeZTree(List list,String num,String box,String navTabId,String view){
		String str = "[";
		int len = num.length()+3;
		for(int i=0;i<list.size();i++) {
			Model vo = (Model)list.get(i);
			if(vo.getStr("NUM").length()==len) {
				str = str + "{\"name\":\""+vo.get("NAME")+"\",";
				str = str + "\"id\":\""+vo.get("NUM")+"\",";
				str = str + "\"box\":\""+box+"\",";
				str = str + "\"href\":\""+view+"?id="+vo.get("ID")+"&navTabId="+navTabId+"\",";
				str = str + "\"isParent\":\""+vo.get("IS_PARENT")+"\"},";
			}
		}
		if(!"[".equals(str)) {
			str = str.substring(0,str.length()-1);
			str = str + "]";
		}else {
			str = "";
		}
		return str;
	}
	//生成ZTree树：id、num
	public static String makeLookUpZTree(List list,String num) {
		String str = "[";
		int len = num.length()+3;
		for(int i=0;i<list.size();i++) {
			Model vo = (Model)list.get(i);
			if(vo.getStr("NUM").length()==len) {
				str = str + "{\"name\":\""+vo.get("NAME")+"\",";
				str = str + "\"id\":\""+vo.get("NUM")+"\",";
				str = str + "\"nid\":\""+vo.get("ID")+"\",";
				str = str + "\"isParent\":\""+vo.get("IS_PARENT")+"\"},";
			}
		}
		if(!"[".equals(str)) {
			str = str.substring(0,str.length()-1);
			str = str + "]";
		}else {
			str = "";
		}
		return str;
	}
	
	//过滤节点
	public static List treeFilter(List list,String num) {
		int len = num.length();
		int clen = 0;
		List templist = new ArrayList();
		Model vo = null;
		for(int i=0;i<list.size();i++) {
			vo = (Model)list.get(i);
			clen = vo.getStr("NUM").length()-len;
			if(clen==6||clen==3){
				templist.add(vo);
			}
		}
		return templist;
	}
}
