package com.emps.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.emps.dao.SystemDDDao;
import com.emps.dao.SystemDDItemDao;
import com.emps.model.SystemDD;
import com.emps.model.SystemDDItem;
import com.emps.pojo.SystemDDForm;
import com.emps.pojo.SystemDDItemForm;
import com.emps.service.SystemDDService;
import com.emps.util.PageInfo;
@Repository(SystemDDService.SERVICE_NAME)
public class SystemDDServiceImpl implements SystemDDService {
    @Resource(name=SystemDDDao.SERVICE_NAME)
	SystemDDDao systemDDDao;
    @Resource(name=SystemDDItemDao.SERVICE_NAME)
    SystemDDItemDao systemDDItemDao;
	@Override
	public List<SystemDDForm> findSystemDDByConditionWithPage(SystemDDForm systemDDForm, HttpServletRequest request) {
		String hqlwhere ="";
		List<Object> paramList = new ArrayList<Object>();
		PageInfo page = new PageInfo(request);
		if(systemDDForm !=null && !"".equals(systemDDForm)){
			if(systemDDForm.getName()!=null && !"".equals(systemDDForm.getName())){
				hqlwhere=" and o.name like ? ";
				paramList.add("%" + systemDDForm.getName() +"%");
			}
			if(systemDDForm.getModule() !=null && !"".equals(systemDDForm.getModule())){
				hqlwhere =" and o.module like ? ";
				paramList.add("%" + systemDDForm.getModule() +"%");
			}
		}
		
		Object[] params = paramList.toArray();
		List<SystemDD> list = systemDDDao.findCollectionByConditionWithPage(hqlwhere, params, null, page);
		
		return SystemDDlDaoPOTOVO(list);
	}

	private List<SystemDDForm> SystemDDlDaoPOTOVO(List<SystemDD> list) {
		List<SystemDDForm> listForm = new ArrayList<SystemDDForm>();
		for(int i=0;i<list.size();i++){
		SystemDDForm systemDDForm = new SystemDDForm();
		systemDDForm.setId(list.get(i).getId());
		systemDDForm.setName(list.get(i).getName());
		systemDDForm.setCode(list.get(i).getCode());
		systemDDForm.setEnable_flag(list.get(i).getEnable_flag());
		systemDDForm.setCreate_date(list.get(i).getCreate_date());
		systemDDForm.setUpdate_time(list.get(i).getUpdate_time());
		listForm.add(systemDDForm);
			
		}
		return listForm;
	}

	@Override
	public boolean saveOrUpdate(SystemDDForm systemDDForm) {
		  SystemDD systemDD = new SystemDD();
		  systemDD.setCode(systemDDForm.getCode());
		  systemDD.setName(systemDDForm.getName());
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date date = new Date();
		  String datestring = sdf.format(date);
		  if(!existSystemDD(systemDDForm)){
		  if(systemDDForm.getId()!=null && !"".equals(systemDDForm.getId())){
			 systemDD.setEnable_flag(systemDDForm.getEnable_flag());
			 try {
				systemDD.setUpdate_time(sdf.parse(datestring));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 systemDDDao.update(systemDD);
			 return true;
		  } else {
			  try {
				systemDDForm.setCreate_date(sdf.parse(datestring));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  systemDD.setEnable_flag("1");
			 
			  systemDDDao.save(systemDD);
			  return true;
		  }
		  }else {
			  return false;
		  }
	
	}

	private boolean existSystemDD(SystemDDForm systemDDForm) {
		List<SystemDD> list = systemDDDao.findSystemDDs(systemDDForm);
		if(list != null && list.size()!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<SystemDDItemForm> findSystemDDItemById(String id) {
		List<SystemDDItem> systemDDItemlist = systemDDItemDao.findSystemDDItemsById(id);
		return systemDDItemsPOTOVO(systemDDItemlist);
		
	}

	private List<SystemDDItemForm> systemDDItemsPOTOVO(List<SystemDDItem> systemDDItemlist) {
		List<SystemDDItemForm> systemDDItemFormList = new ArrayList<SystemDDItemForm>();
		for(int i=0;i<systemDDItemlist.size();i++){
			SystemDDItemForm systemDDItemForm = new SystemDDItemForm();
			systemDDItemForm.setParent_id(systemDDItemlist.get(i).getSystemdd().getId());
			systemDDItemForm.setDditem_id(systemDDItemlist.get(i).getDditem_id());
			systemDDItemForm.setName(systemDDItemlist.get(i).getName());
			systemDDItemForm.setSort(systemDDItemlist.get(i).getSort());
			systemDDItemForm.setEnable_flag(systemDDItemlist.get(i).getEnable_flag());
			systemDDItemForm.setValue(systemDDItemlist.get(i).getValue());
			systemDDItemFormList.add(systemDDItemForm);
		}
		return systemDDItemFormList;
	}

	@Override
	public boolean saveOrUpdate(SystemDDItemForm systemDDItemForm) {
		SystemDDItem systemDDItem = new SystemDDItem();
		SystemDD systemDD = systemDDDao.findObjectById(systemDDItemForm.getParent_id());
		systemDDItem.setName(systemDDItemForm.getName());
		systemDDItem.setValue(systemDDItemForm.getValue());
		systemDDItem.setEnable_flag(systemDDItemForm.getEnable_flag());
		systemDDItem.setCreate_date(systemDDItemForm.getCreate_date());
		systemDDItem.setSort(systemDDItemForm.getSort());
		systemDDItem.setUpdate_time(systemDDItemForm.getUpdate_time());	
		systemDDItem.setDditem_id(systemDDItemForm.getDditem_id());
		systemDDItem.setSystemdd(systemDD);
		systemDDItem.setDditem_id(systemDDItemForm.getDditem_id());
//		systemDDItem.setSystemdd(systemDDDao.findObjectById(systemDDItemForm.getParent_id()));
	
	   if(systemDDItemForm.getDditem_id()!=null && !"".equals(systemDDItemForm.getDditem_id())){
		   if(!existSystemDDItem(systemDDItemForm)){
			   systemDDItemDao.update(systemDDItem);
				return true;   
		   }else return true;
		}else if(!existSystemDDItem(systemDDItemForm)){
			systemDDItemDao.save(systemDDItem);
			return true;
		}
			return false;
		

	
	}

	private boolean existSystemDDItem(SystemDDItemForm systemDDItemForm) {
		List<SystemDDItem> list = systemDDItemDao.findSystemDDItems(systemDDItemForm);
		if(list != null && list.size() != 0){
			return true;
		}
		return false;
	}

	@Override
	public SystemDDItemForm findSystemDDItemByDDItemId(String dditem_id) {
		SystemDDItem systemDDItem = systemDDItemDao.findObjectById(dditem_id);
		SystemDDItemForm systemDDItemForm = systemDDItemPOTOVO(systemDDItem);
		return systemDDItemForm;
	}

	private SystemDDItemForm systemDDItemPOTOVO(SystemDDItem systemDDItem) {
		SystemDDItemForm systemDDItemForm = new SystemDDItemForm();
		systemDDItemForm.setName(systemDDItem.getName());
		systemDDItemForm.setValue(systemDDItem.getValue());
		systemDDItemForm.setSort(systemDDItem.getSort());
		systemDDItemForm.setEnable_flag(systemDDItem.getEnable_flag());
		systemDDItemForm.setCreate_date(systemDDItem.getCreate_date());
		systemDDItemForm.setUpdate_time(systemDDItem.getUpdate_time());
		systemDDItemForm.setDditem_id(systemDDItem.getDditem_id());
		systemDDItemForm.setParent_id(systemDDItem.getSystemdd().getId());
		return systemDDItemForm;
	}

	@Override
	public void deleteSystemDDItemById(String dditem_id) {	
		SystemDDItem systemDDItem = systemDDItemDao.findObjectById(dditem_id);
		systemDDItemDao.delete(systemDDItem);		
	}

	@Override
	public SystemDDForm findSystemDDById(String seqid) {
		SystemDD systemdd = systemDDDao.findObjectById(seqid);
		SystemDDForm systemddForm = new SystemDDForm();
		systemddForm.setCode(systemdd.getCode());
		systemddForm.setCreate_date(systemdd.getCreate_date());
		systemddForm.setCreate_dept(systemdd.getCreate_dept());
		systemddForm.setCreate_employee(systemdd.getCreate_employee());
		systemddForm.setId(systemdd.getId());
		systemddForm.setModule(systemdd.getModule());
		systemddForm.setName(systemdd.getName());
		systemddForm.setUpdate_dept(systemdd.getUpdate_dept());
		systemddForm.setUpdate_employee(systemdd.getUpdate_employee());
		systemddForm.setUpdate_time(systemdd.getUpdate_time());
		
		return systemddForm;
	}
    
    
}
