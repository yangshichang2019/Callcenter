package com.emps.handler;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.emps.pojo.AgentForm;
import com.emps.pojo.AjaxDone;
import com.emps.service.UserService;


@Controller
@RequestMapping(value="/role")
public class ErrorController extends BaseController{
	@Resource(name=UserService.SERVICE_NAME)
	UserService  userService;
	@RequestMapping(value="/roleerror")
	public String roleerror(AgentForm agentForm,Map<String,Object> map){
		AjaxDone ajaxDone = new AjaxDone();
		 ajaxDone.setMessage("没有角色");
		 ajaxDone.setStatusCode("300");
		 map.put("ajaxDone", ajaxDone);
		 return "konka/ajaxDone"; 
	  }	 

	 @RequestMapping(value="/permerror")
	public String permerror(AgentForm agentForm,Map<String,Object> map){
		 AjaxDone ajaxDone = new AjaxDone();
		 ajaxDone.setMessage("没有权限");
		 ajaxDone.setStatusCode("300");
		 map.put("ajaxDone", ajaxDone);
		 return "konka/ajaxDone";
	}

	}

