package com.emps.handler;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emps.pojo.AjaxDone;
import com.emps.pojo.SystemDDlForm;
import com.emps.service.SystemDDlService;

@Controller
@RequestMapping("/systemddl")
public class SystemDDlController extends BaseController {
	@Resource(name=SystemDDlService.SERVICE_NAME)
	SystemDDlService systemDDlService;
	
    @RequestMapping("/listSystemDDl")
	public String listSystemDDl(Map<String,Object> map, @ModelAttribute(name="vo")SystemDDlForm systemDDlForm,HttpServletRequest request) {
    	     List<SystemDDlForm> list = systemDDlService.findSystemDDlByConditionWithPage(systemDDlForm,request);   
    	     map.put("listSystemddl", list);
		return "konka/systemddl/systemddlManage";
		
	}
    @RequestMapping("/display/{id}")
    public String display(@PathVariable(value="id") Integer id,Map<String,Object> map){
    	SystemDDlForm  systemDDlForm = systemDDlService.findSystemDDlById(id);
    	map.put("systemDDl", systemDDlForm);
		return "konka/systemddl/systemDDlAdd";
    	
    	
    }
    @RequestMapping("/addSystemDDl")
    public String addSystemDDl(){
    	return "konka/systemddl/systemDDlAdd";
    }
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(SystemDDlForm systemDDlForm,Map<String,Object> map){
    	AjaxDone ajaxDone = new AjaxDone();
    	if(!systemDDlService.saveOrUpdate(systemDDlForm)){
    		   ajaxDone.setMessage("新增/更新内容重复");
    		   ajaxDone.setStatusCode("300");
    	}
    	map.put("ajaxDone", ajaxDone);
       return "konka/ajaxDone";	
    }
}
