package com.emps.handler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emps.model.SystemDDItem;
import com.emps.pojo.AjaxDone;
import com.emps.pojo.SystemDDForm;
import com.emps.pojo.SystemDDItemForm;
import com.emps.service.SystemDDService;
import com.emps.service.SystemDDlService;


@Controller
@RequestMapping("/systemdd")
public class SystemDDController extends BaseController {
	@Resource(name=SystemDDService.SERVICE_NAME)
	SystemDDService systemDDService;
	
    @RequestMapping("/listSystemDD")
	public String listSystemDDl(Map<String,Object> map, @ModelAttribute(name="vo")SystemDDForm systemDDForm,HttpServletRequest request) {
    	     List<SystemDDForm> list = systemDDService.findSystemDDByConditionWithPage(systemDDForm,request);   
    	     map.put("listSystemdd", list);
		return "konka/systemdd/systemDDManage";
	}
 
    @RequestMapping("/display/{seqid}")
    public String display(@PathVariable(value="seqid") String seqid,Map<String,Object> map){
    	SystemDDForm  systemDDForm = systemDDService.findSystemDDById(seqid);
    	map.put("systemDD", systemDDForm);
		return "konka/systemdd/systemDDAdd";
    	
    	
    }

    @RequestMapping("/addSystemDD")
    public String addSystemDDl(){
    	return "konka/systemdd/systemDDAdd";
    }
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(SystemDDForm systemDDForm,Map<String,Object> map){
    	AjaxDone ajaxDone = new AjaxDone();
    	try {
			if(!systemDDService.saveOrUpdate(systemDDForm)){
				   ajaxDone.setMessage("新增/更新内容重复");
				   ajaxDone.setStatusCode("300");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	map.put("ajaxDone", ajaxDone);
       return "konka/ajaxDone";	
    }
    
    @RequestMapping("/systemDDItemManage/{id}")
    public String systemDDItemManage(@PathVariable(value="id")String id,Map<String,Object> map){
    	List<SystemDDItemForm> systemDDItemsForm = systemDDService.findSystemDDItemById(id);
    	map.put("systemDDItemsForm", systemDDItemsForm);
    	return "konka/systemdd/systemDDItemManage";
    	
    }
    @RequestMapping("/systemDDItemAdd/{id}")
    public String systemDDItemAdd( @PathVariable(value="id")String id, Map<String,Object> map){
    	
    	map.put("parent_id",id);
		return "konka/systemdd/systemDDItemEdit";
    	
    }
    @RequestMapping("/systemDDItemEdit/{dditem_id}")
    public String systemDDItemEdit(@PathVariable(value="dditem_id")String dditem_id,Map<String,Object> map){
    	SystemDDItemForm systemDDItemForm = systemDDService.findSystemDDItemByDDItemId(dditem_id);
    	map.put("systemDDItemForm", systemDDItemForm);
		return "konka/systemdd/systemDDItemEdit";
    	
    }
    
    @RequestMapping("/systemDDItemDelete/{dditem_id}")
    public String systemDDItemDelete(@PathVariable(value="dditem_id")String dditem_id,Map<String,Object> map){
    	systemDDService.deleteSystemDDItemById(dditem_id);
    //	AjaxDone ajaxDone = new AjaxDone();
    //	map.put("ajaxDone", ajaxDone);
    	return "konka/ajaxDone";
	
    	
    }
    @RequestMapping("/systemDDItemSaveOrUpdate")
    public String systemDDItemSaveOrUpdate(SystemDDItemForm systemDDItemForm,Map<String,Object> map){
    	AjaxDone ajaxDone = new AjaxDone();
    	if(!systemDDService.saveOrUpdate(systemDDItemForm)){
    		ajaxDone.setMessage("更新/添加内容重复！");
    		ajaxDone.setStatusCode("300");	
    	}
		map.put("ajaxDone", ajaxDone);
		return "konka/ajaxDone";
    	
    }
    
}
