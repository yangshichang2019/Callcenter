package com.emps.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emps.model.Department;
import com.emps.pojo.AjaxDone;
import com.emps.pojo.UserForm;
import com.emps.pojo.UserForm2;
import com.emps.service.UserService;


@Controller
@RequestMapping("/userManage")
public class UserController extends BaseController{
	@Resource(name=UserService.SERVICE_NAME)
	UserService userService;
	
	@RequestMapping("/listUsers")
   public String listUsers(Map<String,Object> map, @ModelAttribute(value="vo")UserForm userForm,HttpServletRequest request){
//		List<UserForm> list = userService.ListUser();
		List<UserForm> list = userService.findUserByConditionWithPage(request, userForm);
		map.put("userlist", list);
	return "konka/user/userManage";
	   
   }
	@RequestMapping(value="/display/{user_id}")
  public String display(@PathVariable(value="user_id") int id,Map<String,Object> map){
	     UserForm userForm = userService.findUserById(id);
	     map.put("userForm", userForm);	
		return "konka/user/useredit";
  }
	@RequiresRoles("admin")
	@RequestMapping(value="/saveOrUpdate")
  public String saveOrUpdate(UserForm userForm,Map<String,Object> map){
		AjaxDone ajaxDone = new AjaxDone();
		if(!userService.saveOrUpdate(userForm)){	
			ajaxDone.setStatusCode("300");
			ajaxDone.setMessage("新增或更新内容不能重复");
		}
		map.put("ajaxDone", ajaxDone);	
		return "konka/ajaxDone";
	}
}
