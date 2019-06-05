 package com.emps.handler;



import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.emps.service.EmployeesService;
import com.emps.service.impl.EmployeesServiceImpl;
@Controller
public class LoginController extends BaseController{
	@Resource(name=EmployeesService.SERVICE_NAME)
	private EmployeesService  employeesService;
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,Map<String,Object> map) throws Exception{
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if(exceptionClassName != null){
			if(UnknownAccountException.class.getName().equals(exceptionClassName)){
				map.put("error", "账号或密码错误");  
	        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {  
	              
	           map.put("error", "账号或密码错误");  
	       
	        }else {  
	        	map.put("error", "登录异常"); // 
	        }  
		}
		//return "login";
		return "konka/login";
	}
	

}
