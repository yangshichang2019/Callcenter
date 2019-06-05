package com.emps.handler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.emps.pojo.AgentForm;
import com.emps.pojo.EmployeesForm;
import com.emps.service.AgentService;
import com.emps.service.EmployeesService;
@Controller
@RequestMapping(value="/agent")
public class AgentController extends BaseController {
	@Resource(name=AgentService.SERVICE_NAME)
	AgentService agentservice;
	@Resource(name=EmployeesService.SERVICE_NAME)
	EmployeesService employeesService;
	
	@RequestMapping(value="/test1")
	public String test1(Map<String,Object> map){
		List<EmployeesForm> list = new ArrayList<EmployeesForm>();
		list =  employeesService.listEmployees();
		map.put("employees", list);
		//System.out.println(employeesForm.getDepartment());
		return "success";
	
	}
	
	@RequestMapping(value="/agentList")
       public String agentList(Map<String,Object> map,HttpServletRequest request,@ModelAttribute(value="vo") AgentForm agentForm){
		List<AgentForm> list  = new ArrayList<AgentForm>();
		List<AgentForm> agentList = agentservice.findCollectionByConditionWithPage(request,  agentForm);
		map.put("agentList", agentList);
    	   return "konka/business";
       }
    @RequiresRoles("admin")
	@RequestMapping(value="/delete/{id}")
	public String  delete(@PathVariable(value="id")String id){
		agentservice.deleteAgent(Integer.parseInt(id));
		return "konka/ajaxDone";
		
	}
	@RequestMapping(value="/success")
	public String success(Map<String,Object> map){	
		List<EmployeesForm> list = new ArrayList<EmployeesForm>();
		list =  employeesService.listEmployees();
		map.put("employees", list);
		return "konka/home";	
	}
	@RequestMapping(value="/query/{id}")
	public String query(@PathVariable(value="id")String id,Map<String,Object> map){
		AgentForm agentForm = agentservice.findEmployeeById(Integer.parseInt(id));
		map.put("agentForm", agentForm);
		return "konka/update";
	}
	@RequiresPermissions("user:create")
	@RequestMapping(value="/update1")
	public String update1(AgentForm agentForm){
		if(!agentservice.saveOrUpdateAgent(agentForm)){
			agentForm.getAjaxDone().setMessage("更新/新增内容不能重复");
			agentForm.getAjaxDone().setStatusCode("300");
		}
		return "konka/ajaxDone";
	}
	@RequestMapping(value="/add")
	public String add(AgentForm agentForm){
		return "konka/update";
	}
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		//Subject subject = SecurityUtils.getSubject();
	   // PrincipalCollection principals = subject.getPrincipals();
	 
//		if(principals !=null){
//			subject.logout();
//		}

		return "redirect:/login";
		
	}

	
}