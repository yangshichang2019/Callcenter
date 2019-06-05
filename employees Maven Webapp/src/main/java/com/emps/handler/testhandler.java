package com.emps.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emps.pojo.EmployeesForm;
import com.emps.service.AgentService;
import com.emps.service.EmployeesService;
@Controller
public class testhandler {
	@Resource(name=AgentService.SERVICE_NAME)
	AgentService agentservice;
	@Resource(name=EmployeesService.SERVICE_NAME)
	EmployeesService employeesService;
	
	@RequestMapping(value="/test2")
	public String test2(Map<String,Object> map){
		List<EmployeesForm> list = new ArrayList<EmployeesForm>();
		list =  employeesService.listEmployees();
		map.put("employees", list);
		//System.out.println(employeesForm.getDepartment());
		return "success";
	
	}
	
}
