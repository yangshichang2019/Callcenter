package com.emps.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.emps.dao.SystemDDlDao;
import com.emps.model.SystemDDl;
import com.emps.pojo.SystemDDlForm;
import com.emps.service.SystemDDlService;
import com.emps.util.PageBean;
import com.emps.util.PageInfo;
@Repository(SystemDDlService.SERVICE_NAME)
public class SystemDDlServiceImpl implements SystemDDlService {
    @Resource(name=SystemDDlDao.SERVICE_NAME)
	SystemDDlDao systemDDlDao;
	@Override
	public List<SystemDDlForm> findSystemDDlByConditionWithPage(SystemDDlForm systemDDlForm,
			HttpServletRequest request) {
		String hqlwhere = "";
		PageInfo page = new PageInfo(request);
		List<Object> paramList = new ArrayList<Object>();
		if(systemDDlForm != null && !"".equals(systemDDlForm)){
			if(systemDDlForm.getKeyword() !=null){
				hqlwhere = " and o.keyword like ? ";
				paramList.add("%" + systemDDlForm.getKeyword() + "%");	
			}
			if(systemDDlForm.getDdlname()!=null){
				hqlwhere += " and o.ddlname like ? ";
				paramList.add("%" + systemDDlForm.getDdlname() + "%");
			}
		
		}
		Object[] params = paramList.toArray();
		List<SystemDDl> list = systemDDlDao.findCollectionByConditionWithPage(hqlwhere, params, null, page);
		List<SystemDDlForm> listForm = SystemDDlDaoPOTOVO(list);
		return listForm;
	}
	private List<SystemDDlForm> SystemDDlDaoPOTOVO(List<SystemDDl> list) {
		      List<SystemDDlForm> listForm = new ArrayList<SystemDDlForm>(); 
		     ; 
		      if(list !=null && list.size() !=0){
		    	 for(int i = 0;i<list.size();i++){
		    		 SystemDDlForm systemDDlForm = new SystemDDlForm();
		    		 systemDDlForm.setDdlcode(list.get(i).getDdlcode());
		    		 systemDDlForm.setDdlname(list.get(i).getDdlname());
		    		 systemDDlForm.setKeyword(list.get(i).getKeyword());
		    		 systemDDlForm.setSeqid(list.get(i).getSeqid());
		    		 listForm.add(systemDDlForm);
		    	 }
		      }
		
		
		
		return listForm;
	}
	@Override
	public boolean saveOrUpdate(SystemDDlForm systemDDlForm) {
		SystemDDl systemDDl = new SystemDDl();
 	    systemDDl.setKeyword(systemDDlForm.getKeyword());
 	    systemDDl.setDdlcode(systemDDlForm.getDdlcode());
 	    systemDDl.setDdlname(systemDDlForm.getDdlname());
       if(systemDDlForm.getSeqid() !=null && !"".equals(systemDDlForm.getSeqid())){
    	   systemDDl.setSeqid(systemDDlForm.getSeqid());
           systemDDlDao.update(systemDDl);
           return true;
       }else if(!existSystemDDl(systemDDlForm)){
    	   systemDDlDao.save(systemDDl);
    	   return true;
       }else{
    	   return false;
       }
	
	}
	private boolean existSystemDDl(SystemDDlForm systemDDlForm) {
	   List<SystemDDl> list = systemDDlDao.findSystemDDls(systemDDlForm);
	   if(list !=null && list.size() !=0){
		   return true;
	   }
		return false;
	}
	@Override
	public SystemDDlForm findSystemDDlById(Integer seqid) {
		SystemDDl systemDDl =systemDDlDao.findObjectById(seqid);
		SystemDDlForm systemDDlForm = new SystemDDlForm();
		systemDDlForm.setSeqid(systemDDl.getSeqid());
		systemDDlForm.setDdlcode(systemDDl.getDdlcode());
		systemDDlForm.setKeyword(systemDDl.getKeyword());
		systemDDlForm.setDdlname(systemDDl.getDdlname());
		return systemDDlForm;
	}

}
