package com.kboss.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.kboss.base.BaseController;
import com.kboss.config.Constant;
import com.kboss.interceptors.AuthInterceptor;
import com.kboss.model.right.Dept;
import com.kboss.model.right.Group;
import com.kboss.model.right.GroupUser;
import com.kboss.model.right.Right;
import com.kboss.model.right.Role;
import com.kboss.model.right.RoleRight;
import com.kboss.model.right.User;
import com.kboss.model.right.UserLogin;
import com.kboss.model.right.UserRole;
import com.kboss.system.cache.InitCache;
import com.kboss.system.tool.Tree;
import com.kboss.system.tool.ZTool;

@Before(AuthInterceptor.class)
public class RightController extends BaseController {
	/*
	 * 权限管理
	 */
	public void toRightManage() {
		
		render("/view/right/toRightManage.html");
	}
	public void toChildrenRight() {
		String num = getPara("id","");
		List<Right> dataList = Right.dao.find("select * from "+Right.tablename+" where num like '"+num+"%' order by sort_id asc,id asc");
		renderText(Tree.makeZTree(dataList,num,"subRightListBox",getPara("navTabId"),"right/toAddEditRight"));
	}
	
	public void toAddEditRight() {
		Integer id = getParaToInt("id",0);
		Right right = new Right();
		if(id>0) {
			right = Right.dao.findById(id);
		}else {
			Integer parent_id = getParaToInt("parent_id",0);
			right.set("PARENT_ID", parent_id);
		}
		Right parentRight = Right.dao.findById(right.get("PARENT_ID"));
		setAttr("right", right);
		setAttr("parentRight", parentRight);
		
		render("/view/right/toAddEditRight.html");
	}
	public void toSaveRight(){
		User loginUser = getSessionAttr(Constant.SESSION_USER);
		Integer id = getParaToInt("id",0);
		Right right = new Right();
		right.set("NAME", getParaFilter("name"));
		right.set("HREF", getPara("href"));
		right.set("TARGET", getPara("target"));
		right.set("FRESH", getPara("fresh"));
		right.set("EXTERNAL", getPara("external"));
		right.set("SORT_ID", getPara("sort_id"));
		if(id>0) {
			right.set("ID", getPara("id"));
			if(getPara("rel").equals("")) {
				right.set("REL", getPara("num"));
			}else {
				right.set("REL", getPara("rel"));
			}
			right.set("UPDATE_USER", loginUser.get("USERNAME"));
			right.set("UPDATE_TIME", ZTool.getNowDateTime());
			right.update();
		}else {
			Integer parent_id = getParaToInt("parent_id",0);
			right.set("PARENT_ID", parent_id);
			Right maxNum = Right.dao.findFirst("select * from "+Right.tablename+" where parent_id=? order by num desc",parent_id);
			if(maxNum==null) {
				if(parent_id==0) {
					right.set("NUM", "001");
				}else {
					Right parentRight = Right.dao.findById(parent_id);
					right.set("NUM", parentRight.get("NUM")+"001");
					parentRight.set("IS_PARENT","true").update();
				}
			}else {
				right.set("NUM", ZTool.getNextNum(maxNum.getStr("NUM")));
			}
			if(getPara("rel").equals("")) {
				right.set("REL", right.get("NUM"));
			}else {
				right.set("REL", getPara("rel"));
			}
			right.set("CREATE_USER", loginUser.get("USERNAME"));
			right.save();
		}
		String parentNum = "";
		if(getParaToInt("parent_id")>0){
			Right parentRight = Right.dao.findById(getParaToInt("parent_id"));
			parentNum = parentRight.getStr("NUM");
		}
		
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"navTabId\":\""+getPara("navTabId","")+"\",\"parentNum\":\""+parentNum+"\"}");
	}
	//Ztree修改名称
	public void toZtreeRenameRight(){
		Db.update("update "+Right.tablename+" set name=? where num=?",getParaFilter("name"),getPara("num"));
	}
	//Ztree删除
	public void toZtreeDeleteRight(){
		String num = getPara("num","");
		Right right = Right.dao.findFirst("select * from "+Right.tablename+" where num=?",num);
		right.deleteById(right.get("ID"));
		Db.update("delete from "+RoleRight.tablename+" where right_id=?",right.get("ID"));
		List<Right> parentSubRight = Right.dao.find("select * from "+ Right.tablename+" where parent_id=?",right.get("PARENT_ID"));
		if(parentSubRight.size()==0) {
			Db.update("update "+Right.tablename+" set is_parent = 'false' where id=?",right.get("PARENT_ID"));
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"删除成功\"}");
	}
	
	/*
	 * 角色管理
	 */
	public void toRoleManage(){
		String where = " where 1=1";
		String name= getPara("name","");
		if(!name.equals("")) {
			where = where + " and name like '%"+name+"%'";
			setAttr("name", name);
		}
		String model= getPara("model","");
		if(!model.equals("")) {
			where = where + " and model = '"+model+"'";
			setAttr("model", model);
		}
		String orderby = " order by model asc,id asc";
		Page<Role> pd = Role.dao.paginate(getParaToInt("pageNumber",1), getParaToInt("pageSize",20),
				"select * ", " from "+Role.tablename+where+orderby);
		setAttr("pd", pd);
		render("/view/right/toRoleManage.html");
	}
	public void toAddEditRole() {
		Integer id = getParaToInt("id",0);
		Role role = new Role();
		if(id>0) {
			role = Role.dao.findById(id);
		}
		setAttr("role", role);
		render("/view/right/toAddEditRole.html");
	}
	public void toSaveRole() {
		User loginUser = getSessionAttr(Constant.SESSION_USER);
		
		Integer id = getParaToInt("id",0);
		
		Role role = new Role();
		role.set("MODEL", getPara("model").trim());
		role.set("NAME", getParaFilter("name").trim());
		role.set("DESC", getParaFilter("desc").trim());
		if(id>0) {
			role.set("ID", id);
			role.set("UPDATE_USER", loginUser.get("USERNAME"));
			role.set("UPDATE_TIME", ZTool.getNowDateTime());
			role.update();
		}else {
			role.set("CREATE_USER", loginUser.get("USERNAME"));
			role.save();
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"navTabId\":\""+getPara("navTabId","")+"\",\"callbackType\":\"closeCurrent\"}");
	}
	public void toRoleRightManage() {
		Integer id = getParaToInt("id",0);
		Role role = Role.dao.findById(id);
		setAttr("role", role);
		List<Right> rightList = Right.dao.find("select A.*,B.right_id as RIGHT_ID from "+
				Right.tablename+" A left join (select right_id from "+
				RoleRight.tablename+" where role_id=?) B ON A.id=B.right_id order by A.sort_id asc,A.id asc",id);
		String zNodes = "";
		String rights = "";
		Right vo = null;
		for(int i=0;i<rightList.size();i++) {
			vo = (Right)rightList.get(i);
			zNodes = zNodes + "{\"name\":\""+vo.get("NAME")+"\",";
			zNodes = zNodes + "\"pId\":\""+vo.get("PARENT_ID")+"\",";
			zNodes = zNodes + "\"id\":\""+vo.get("ID")+"\",";
			if(vo.get("RIGHT_ID")!=null) {
				zNodes = zNodes + "\"checked\":true,";
				rights = rights + vo.get("ID")+",";
			}
			zNodes = zNodes + "\"open\":true}";
			if(i<(rightList.size()-1)) {
				zNodes = zNodes + ",";
			}
		}
		if(!rights.equals("")) {
			rights = rights.substring(0,rights.length()-1);
		}
		setAttr("zNodes", zNodes);
		setAttr("rights", rights);
		render("/view/right/toRoleRightManage.html");
	}
	public void toSaveRoleRight(){
		Integer id = getParaToInt("id",0);
		String rights = getPara("rights").trim();
		Db.update("delete from "+RoleRight.tablename+" where role_id=?",id);
		if(rights.length()>0) {
			RoleRight roleRight = null;
			String[] arr = rights.split(",");
			for(int i =0;i<arr.length;i++) {
				roleRight = new RoleRight();
				roleRight.set("RIGHT_ID", arr[i].trim());
				roleRight.set("ROLE_ID", id);
				roleRight.save();
			}
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"callbackType\":\"closeCurrent\"}");
	}
	public void toRoleUserManage() {
		Integer id = getParaToInt("id",0);
		Role role = Role.dao.findById(id);
		setAttr("role", role);
		List<User> leftList = User.dao.find("select * from "+User.tablename+" where username not in (select username from "+
				UserRole.tablename+" where role_id=?) and enable_flag='T' order by username asc",id);
		List<User> rightList = User.dao.find("select * from "+User.tablename+" where username in (select username from "+
					UserRole.tablename+" where role_id=?) and enable_flag='T' order by username asc",id);
		setAttr("leftList", leftList);
		setAttr("rightList",rightList);
		String users = "";
		User user = null;
		for(int i=0;i<rightList.size();i++) {
			user = rightList.get(i);
			users = users + user.get("USERNAME");
			if(i<(rightList.size()-1)){
				users = users + ",";
			}
		}
		setAttr("users", users);
		render("/view/right/toRoleUserManage.html");
	}
	public void toSaveRoleUser() {
		Integer id = getParaToInt("id",0);
		String users = getPara("users").trim();
		Db.update("delete from "+UserRole.tablename+" where role_id=?",id);
		if(users.length()>0) {
			UserRole userRole = null;
			String[] arr = users.split(",");
			for(int i =0;i<arr.length;i++) {
				userRole = new UserRole();
				userRole.set("USERNAME", arr[i].trim());
				userRole.set("ROLE_ID", id);
				userRole.save();
			}
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"callbackType\":\"closeCurrent\"}");
	}
	
	/**
	 * 用户管理
	 */
	public void toUserManage() {
		String where = " where 1=1";
		String username= getPara("username","");
		if(!username.equals("")) {
			where = where + " and username='"+username+"'";
			setAttr("username", username);
		}
		String realname= getPara("realname","");
		if(!realname.equals("")) {
			where = where + " and realname like '%"+realname+"%'";
			setAttr("realname", realname);
		}
		String dept_id = getPara("user.dept_id","");
		if(!dept_id.equals("")) {
			where =where + " and dept_id="+dept_id+" ";
			setAttr("DEPT_ID", dept_id);
		}
		
		String orderby = " order by id desc";
		Page<User> pd = User.dao.paginate(getParaToInt("pageNumber",1), getParaToInt("pageSize",20),
				"select * ", " from "+User.tablename+where+orderby);
		setAttr("pd", pd);
		render("/view/right/toUserManage.html");
	}
	public void toAddEditUser() {
		Integer id = getParaToInt("id",0);
		User user = new User();
		if(id>0) {
			user = User.dao.findById(id);
		}
		setAttr("user", user);
		render("/view/right/toAddEditUser.html");
	}
	public void toSaveUser() {
		User loginUser = getSessionAttr(Constant.SESSION_USER);
		Integer id = getParaToInt("id",0);
		User user = new User();
		User is_repeat = User.dao.findFirst("select * from "+User.tablename+" where realname = ? and id<>?",getParaFilter("realname",""),id);
		if(is_repeat!=null) {
			renderText("{\"statusCode\":\"300\",\"message\":\"真实姓名不能重复！\",\"navTabId\":\""+getPara("navTabId","")+"\"}");				
			return;
		}
		user.set("REALNAME", getParaFilter("realname",""));
		user.set("DEPT_ID", getParaToInt("user.dept_id",0));
		user.set("EMAIL", getPara("email",""));
		user.set("PHONE", getPara("phone",""));
		user.set("ENABLE_FLAG", getPara("enable_flag","T"));
		String password = getPara("password","");
		if(id>0) {
			if(!password.equals("")) {
				user.set("PASSWORD", ZTool.MD5(password));
			}			
			user.set("ID", id);
			user.set("UPDATE_USER", loginUser.get("username"));
			user.set("UPDATE_TIME", ZTool.getNowDateTime());
			user.update();
		}else {
			String username = getPara("username");
			is_repeat = User.dao.findFirst("select * from "+User.tablename+" where username = ?",username);
			if(is_repeat!=null) {
				renderText("{\"statusCode\":\"300\",\"message\":\"用户名不能重复！\",\"navTabId\":\""+getPara("navTabId","")+"\"}");				
				return;
			}
			user.set("USERNAME", username);
			user.set("PASSWORD", ZTool.MD5(password));
			user.set("CREATE_USER", loginUser.get("USERNAME"));
			user.set("CREATE_TIME", ZTool.getNowDateTime());
			user.save();
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"navTabId\":\""+getPara("navTabId","")+"\",\"callbackType\":\"closeCurrent\"}");
	}
	public void deleteUser(){
		Integer id = getParaToInt("id",0);
		Db.update("delete from "+User.tablename+" where id=?",id);
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"navTabId\":\""+getPara("navTabId","")+"\"}");
	}
	public void toUserRoleManage() {
		Integer id = getParaToInt("id",0);
		User user = User.dao.findById(id);
		setAttr("user", user);
		List<Role> leftList = Role.dao.find("select * from "+Role.tablename+" where id not in (select role_id from "+
				UserRole.tablename+" where username=?) order by id asc",user.get("USERNAME"));
		List<Role> rightList = Role.dao.find("select * from "+Role.tablename+" where id in (select role_id from "+
					UserRole.tablename+" where username=?) order by id asc",user.get("USERNAME"));
		setAttr("leftList", leftList);
		setAttr("rightList",rightList);
		String roles = "";
		Role role = null;
		for(int i=0;i<rightList.size();i++) {
			role = rightList.get(i);
			roles = roles + role.get("ID");
			if(i<(rightList.size()-1)){
				roles = roles + ",";
			}
		}
		setAttr("roles", roles);
		render("/view/right/toUserRoleManage.html");
	}
	public void toSaveUserRole() {
		Integer id = getParaToInt("id",0);
		User user = User.dao.findById(id);
		String roles = getPara("roles").trim();
		Db.update("delete from "+UserRole.tablename+" where username=?",user.get("USERNAME"));
		if(roles.length()>0) {
			UserRole userRole = null;
			String[] arr = roles.split(",");
			for(int i =0;i<arr.length;i++) {
				userRole = new UserRole();
				userRole.set("USERNAME", user.get("USERNAME"));
				userRole.set("ROLE_ID", arr[i].trim());
				userRole.save();
			}
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"callbackType\":\"closeCurrent\"}");
	}
	public void toUserGroupManage() {
		Integer id = getParaToInt("id",0);
		User user = User.dao.findById(id);
		setAttr("user", user);
		List<Group> leftList = Group.dao.find("select * from "+Group.tablename+" where id not in (select group_id from "+
				GroupUser.tablename+" where username=?) order by id asc",user.get("USERNAME"));
		List<Group> rightList = Group.dao.find("select * from "+Group.tablename+" where id in (select group_id from "+
				GroupUser.tablename+" where username=?) order by id asc",user.get("USERNAME"));
		setAttr("leftList", leftList);
		setAttr("rightList",rightList);
		String groups = "";
		Group group = null;
		for(int i=0;i<rightList.size();i++) {
			group = rightList.get(i);
			groups = groups + group.get("ID");
			if(i<(rightList.size()-1)){
				groups = groups + ",";
			}
		}
		setAttr("groups", groups);
		render("/view/right/toUserGroupManage.html");
	}
	public void toSaveUserGroup() {
		Integer id = getParaToInt("id",0);
		User user = User.dao.findById(id);
		String groups = getPara("groups").trim();
		Db.update("delete from "+GroupUser.tablename+" where username=?",user.get("USERNAME"));
		if(groups.length()>0) {
			GroupUser groupUser = null;
			String[] arr = groups.split(",");
			for(int i =0;i<arr.length;i++) {
				groupUser = new GroupUser();
				groupUser.set("GROUP_ID", arr[i].trim());
				groupUser.set("USERNAME", user.get("USERNAME"));
				groupUser.save();
			}
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"callbackType\":\"closeCurrent\"}");
	}
	/**
	 * 部门管理
	 */
	public void toDeptManage() {

		render("/view/right/toDeptManage.html");
	}
	public void toChildrenDept() {
		String num = getPara("id","");
		List<Dept> dataList = Dept.dao.find("select * from "+Dept.tablename+" where num like '"+num+"%' order by sort_id asc,id asc");
		renderText(Tree.makeZTree(dataList,num,"subDeptListBox",getPara("navTabId"),"right/toAddEditDept"));
	}
	public void toAddEditDept() {
		Integer id = getParaToInt("id",0);
		Dept dept = new Dept();
		if(id>0) {
			dept = Dept.dao.findById(id);
		}else {
			Integer parent_id = getParaToInt("parent_id",0);
			dept.set("PARENT_ID", parent_id);
		}
		Dept parentDept = Dept.dao.findById(dept.get("PARENT_ID"));
		setAttr("dept", dept);
		
		User amountUser = User.dao.findFirst("select count(ID) as id from "+User.tablename+" where dept_id=?",id);
		Long amount = 0l;
		if(amountUser!=null) {
			amount = amountUser.getLong("id");
		}
		setAttr("amount", amount);
		setAttr("parentDept", parentDept);
		
		render("/view/right/toAddEditDept.html");
	}
	public void toSaveDept(){
		User loginUser = getSessionAttr(Constant.SESSION_USER);
		Integer id = getParaToInt("id",0);
		Dept dept = new Dept();
		dept.set("NAME", getParaFilter("name"));
		dept.set("DESC", getParaFilter("desc"));
		dept.set("CATE", getPara("cate"));
		dept.set("SORT_ID", getPara("sort_id"));
		if(id>0) {
			dept.set("ID", getPara("id"));
			dept.set("UPDATE_USER", loginUser.get("USERNAME"));
			dept.set("UPDATE_TIME", ZTool.getNowDateTime());
			dept.update();
		}else {
			Integer parent_id = getParaToInt("parent_id",0);
			dept.set("PARENT_ID", parent_id);
			Dept maxNum = Dept.dao.findFirst("select * from "+Dept.tablename+" where parent_id=? order by num desc",parent_id);
			if(maxNum==null) {
				if(parent_id==0) {
					dept.set("NUM", "001");
				}else {
					Dept parentDept = Dept.dao.findById(parent_id);
					dept.set("NUM", parentDept.get("NUM")+"001");
					
					parentDept.set("IS_PARENT","true").update();
				}
			}else {
				dept.set("NUM", ZTool.getNextNum(maxNum.getStr("NUM")));
			}
			dept.set("CREATE_USER", loginUser.get("USERNAME"));
			dept.save();
		}
		String parentNum = "";
		if(getParaToInt("parent_id")>0){
			Dept parentDept = Dept.dao.findById(getParaToInt("parent_id"));
			parentNum = parentDept.getStr("NUM");
		}
		
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"navTabId\":\""+getPara("navTabId","")+"\",\"parentNum\":\""+parentNum+"\"}");
	}
	//Ztree修改名称
	public void toZtreeRenameDept(){
		Db.update("update "+Dept.tablename+" set name=? where num=?",getParaFilter("name"),getPara("num"));
		renderText("{\"statusCode\":\"200\",\"message\":\"修改成功\"}");
	}
	//Ztree删除
	public void toZtreeDeleteDept(){
		String num = getPara("num","");
		Dept dept = Dept.dao.findFirst("select * from "+Dept.tablename+" where num=?",num);
		dept.deleteById(dept.get("ID"));
		//属于此部门的会员部门设置为null
		Db.update("update "+User.tablename+" set dept_id=null where dept_id=?",dept.get("ID"));
		List<Dept> parentSubDept = Dept.dao.find("select * from "+ Dept.tablename+" where parent_id=?",dept.get("PARENT_ID"));
		if(parentSubDept.size()==0) {
			Db.update("update "+Dept.tablename+" set is_parent = 'false' where id=?",dept.get("PARENT_ID"));
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"删除成功\"}");
	}
	
	public void toLookUpDept(){
		render("/view/right/toLookUpDept.html");
	}
	public void toChildrenLookUpDept() {
		String num = getPara("id","");
		List<Dept> dataList = Dept.dao.find("select * from "+Dept.tablename+" where num like '"+num+"%' order by sort_id asc,id asc");
		renderText(Tree.makeLookUpZTree(dataList,num));
	}
	public void toLoadDeptCache(){
		InitCache.loadDeptCache();
		renderText("{\"statusCode\":\"200\",\"message\":\"更新成功\"}");
	}
	
	public void toDeptUser(){
		Integer id = getParaToInt("id");
		Dept dept = Dept.dao.findById(id);
		setAttr("dept",dept);
		List<User> leftList = User.dao.find("select * from "+User.tablename+" where (dept_id is null or dept_id=0) and enable_flag='T' order by realname asc,username asc");
		List<User> rightList = User.dao.find("select * from "+User.tablename+" where dept_id=? and enable_flag='T' order by realname asc,username asc",id);
		setAttr("leftList", leftList);
		setAttr("rightList",rightList);
		String users = "";
		User user = null;
		for(int i=0;i<rightList.size();i++) {
			user = rightList.get(i);
			users = users + user.get("ID");
			if(i<(rightList.size()-1)){
				users = users + ",";
			}
		}
		setAttr("users", users);
		render("/view/right/toDeptUser.html");
	}
	public void toSaveDeptUser(){
		Integer id = getParaToInt("id",0);
		String users = getPara("users").trim();
		if(users.length()>0) {
			Db.update("update "+User.tablename+" set dept_id=null where dept_id=? and id not in ("+users+")",id);
			Db.update("update "+User.tablename+" set dept_id=? where (dept_id is null or dept_id =0) and id in ("+users+")",id);
		}else {
			Db.update("update "+User.tablename+" set dept_id=null where dept_id=?",id);
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"更新成功\",\"callbackType\":\"closeCurrent\"}");
	}
	
	/**
	 * 工作组管理
	 */
	public void toGroupManage(){
		String where = " where 1=1";
		String orderby = " order by model asc,id asc";
		Page<Group> pd = Group.dao.paginate(getParaToInt("pageNumber",1), getParaToInt("pageSize",20),
				"select * ", " from "+Group.tablename+where+orderby);
		setAttr("pd", pd);
		render("/view/right/toGroupManage.html");
	}
	public void toAddEditGroup() {
		Integer id = getParaToInt("id",0);
		Group group = new Group();
		if(id>0) {
			group = Group.dao.findById(id);
		}
		setAttr("group", group);
		render("/view/right/toAddEditGroup.html");
	}
	public void toSaveGroup() {
		User loginUser = getSessionAttr(Constant.SESSION_USER);
		
		Integer id = getParaToInt("id",0);
		
		Group group = new Group();
		group.set("MODEL", getPara("model").trim());
		group.set("NAME", getParaFilter("name").trim());
		group.set("DESC", getParaFilter("desc").trim());
		
		Group is_repeat = Group.dao.findFirst("select * from "+Group.tablename+" where name=? and id<>?",group.get("NAME"),id);
		if(is_repeat!=null) {
			renderText("{\"statusCode\":\"300\",\"message\":\"名称不能重复！\"}");
			return;
		}
		if(id>0) {
			group.set("ID", id);
			group.set("UPDATE_USER", loginUser.get("USERNAME"));
			group.set("UPDATE_TIME", ZTool.getNowDateTime());
			group.update();
		}else {
			group.set("CREATE_USER", loginUser.get("USERNAME"));
			group.save();
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"navTabId\":\""+getPara("navTabId","")+"\",\"callbackType\":\"closeCurrent\"}");
	}
	public void toGroupUserManage() {
		Integer id = getParaToInt("id",0);
		Group group = Group.dao.findById(id);
		setAttr("group", group);
		List<User> leftList = User.dao.find("select * from "+User.tablename+" where username not in (select username from "+
				GroupUser.tablename+" where group_id=?) and enable_flag='T' order by username asc",id);
		List<User> rightList = User.dao.find("select * from "+User.tablename+" where username in (select username from "+
				GroupUser.tablename+" where group_id=?) and enable_flag='T' order by username asc",id);
		setAttr("leftList", leftList);
		setAttr("rightList",rightList);
		String users = "";
		User user = null;
		for(int i=0;i<rightList.size();i++) {
			user = rightList.get(i);
			users = users + user.get("USERNAME");
			if(i<(rightList.size()-1)){
				users = users + ",";
			}
		}
		setAttr("users", users);
		render("/view/right/toGroupUserManage.html");
	}
	public void toSaveGroupUser() {
		Integer id = getParaToInt("id",0);
		String users = getPara("users").trim();
		Db.update("delete from "+GroupUser.tablename+" where group_id=?",id);
		if(users.length()>0) {
			GroupUser groupUser = null;
			String[] arr = users.split(",");
			for(int i =0;i<arr.length;i++) {
				groupUser = new GroupUser();
				groupUser.set("USERNAME", arr[i].trim());
				groupUser.set("GROUP_ID", id);
				groupUser.save();
			}
		}
		renderText("{\"statusCode\":\"200\",\"message\":\"保存成功\",\"callbackType\":\"closeCurrent\"}");
	}
	//全角色查询
	public void toLookupAll(){
		String groupName = getPara("groupName","");
		setAttr("groupName", groupName);
		render("/view/right/toLookupAll.html");
	}
	//全角色查询-获取子部门
	public void toChildrenLookUpAllUser(){
		List dataList = Dept.dao.find("select * from "+Dept.tablename);
		String num = getPara("num","");
		String str = "";
		if(dataList!=null&&dataList.size()>0) {
			dataList = Tree.treeFilter(dataList,num);
			str = Tree.makeZTree(dataList,num,"subLookUpAllUserBox",getPara("navTabId"),"right/toViewLookUpDeptUser");
		}
		if(num.equals("")) {
			List<Group> tempList = Group.dao.find("select * from "+Group.tablename);
			if(tempList!=null&&tempList.size()>0) {
				Group vo = null;
				String gstr = "";
				for(int i=0;i<tempList.size();i++) {
					vo = (Group)tempList.get(i);
					gstr = gstr + "{\"name\":\""+vo.get("NAME")+"\",";
					gstr = gstr + "\"id\":\""+vo.get("ID")+"\",";
					gstr = gstr + "\"box\":\"subLookUpAllUserBox\",";
					gstr = gstr + "\"href\":\"right/toViewLookUpGroupUser?id="+vo.get("ID")+"&navTabId="+getPara("navTabId")+"\",";
					gstr = gstr + "\"isParent\":\"false\"},";
				}
				if(!gstr.equals("")) {
					
					gstr = gstr.substring(0,gstr.length()-1);
					if(!str.equals("")){
						str = str.substring(0, str.length()-1);
						str = str + ","+gstr + "]";
					}else {
						str = "["+gstr+"]";
					}
				}
			}

		}
		renderText(str);
	}
	//全角色查询-获取部门人员列表
	public void toViewLookUpDeptUser(){
		Integer id = getParaToInt("id",0);
		Dept dept = Dept.dao.findById(id);
		List<Dept> deptlist = Dept.dao.find("select * from "+Dept.tablename+" where parent_id=?",id);
		
		setAttr("dept",dept);
		setAttr("deptlist",deptlist);
		List<User> userList = User.dao.find("select * from "+User.tablename+" where dept_id=? and enable_flag='T' order by username asc",id);
		setAttr("userList", userList);
		render("/view/right/toViewLookUpDeptUser.html");
	}
	public void toViewLookUpGroupUser(){
		Integer id = getParaToInt("id",0);
		Group group = Group.dao.findById(id);
		setAttr("group", group);
		List<User> userList = User.dao.find("select B.* from "+GroupUser.tablename+" A left join "+User.tablename+" B ON A.username=B.username where A.group_id=? and B.enable_flag='T' order by B.username asc",id);
		setAttr("userList", userList);
		render("/view/right/toViewLookUpGroupUser.html");
	}
	//查看登陆记录
	public void userLogin(){		
		String where = " where 1=1";
		String username = getPara("username","");
		if(!username.equals("")) {
			where = where + " and CREATE_USER='"+username+"' ";
			setAttr("username", username);
		}
		String start_time = getPara("start_time","");
		if(!start_time.equals("")) {
			where=  where + " and create_time>='"+start_time+"'";
			setAttr("start_time",start_time);
		}
		String end_time = getPara("end_time","");
		if(!end_time.equals("")) {
			where=  where + " and create_time<='"+end_time+"'";
			setAttr("end_time",end_time);
		}
		String ip = getPara("ip","");
		if(!ip.equals("")) {
			where = where + " and ip='"+ip+"'";
			setAttr("ip",ip);
		}
		
		String orderby = " order by id desc";
		Page<UserLogin> pd = UserLogin.dao.paginate(getParaToInt("pageNumber",1), getParaToInt("pageSize",20),
			"select * ", " from "+UserLogin.tablename+where+orderby);
		setAttr("pd", pd);
		render("/view/right/userLogin.html");
	}
}
