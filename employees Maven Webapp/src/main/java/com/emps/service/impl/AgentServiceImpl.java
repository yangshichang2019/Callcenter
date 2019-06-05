package com.emps.service.impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.emps.dao.AgentDao;
import com.emps.model.Agent;
import com.emps.pojo.AgentForm;
import com.emps.service.AgentService;
import com.emps.util.PageInfo;

@Service(AgentService.SERVICE_NAME)
public class AgentServiceImpl implements AgentService {
    @Resource(name=AgentDao.SERVICE_NAME)
	AgentDao agentDao;
    
    public List<AgentForm> listCollections(){
    	List<Agent> list = agentDao.listCollections();
    	List<AgentForm> listForm = ListAgentPOTOVO(list); 
    	return listForm;
    }
    public List<AgentForm> findCollectionByConditionWithPage(HttpServletRequest request,AgentForm agentForm){
    	PageInfo page = new PageInfo(request);
    	String hqlwhere ="";
    	List<String> paramList = new ArrayList<String>();
    	if(agentForm!=null){
    		if(agentForm.getName()!=null && !agentForm.getName().equals("")){
    			hqlwhere = " and o.name like ? ";
    			paramList.add("%" + agentForm.getName() + "%");
    		}
    		if(agentForm.getAccount()!=null && !agentForm.getAccount().equals("")){
    			hqlwhere = " and o.account like ? ";
    			paramList.add("%" + agentForm.getAccount() + "%");
    		}
    		if(agentForm.getCor()!=null && !agentForm.getCor().equals("")){
    			hqlwhere = " and o.cor like ? ";
    			paramList.add("%" + agentForm.getCor() + "%");
    		}
    		if(agentForm.getType()!=null && !agentForm.getType().equals("")){
    			hqlwhere = " and o.type like ? ";
    			paramList.add("%" + agentForm.getType() + "%");
    		}
    	}
    	Object[] params = paramList.toArray();
    	LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
    	orderby.put("account", "asc");
    	List<Agent> listAgent = agentDao.findCollectionByConditionWithPage(hqlwhere, params, orderby, page);
    	agentForm.setPage(page);
        List<AgentForm> listForm = this.ListAgentPOTOVO(listAgent);
		return listForm;
    	
    }
	private List<AgentForm> ListAgentPOTOVO(List<Agent> list) {
		  List<AgentForm> listForm = new ArrayList<AgentForm>();
		  AgentForm agentForm = null;
		  for(int i=0;i<list.size();i++){
			  agentForm = new AgentForm();
			  agentForm.setId(Integer.toString(list.get(i).getId()));
			  agentForm.setAccount(list.get(i).getAccount());
			  agentForm.setName(list.get(i).getName());
			  agentForm.setCor(list.get(i).getCor());
			  agentForm.setType(list.get(i).getType());
			
			  listForm.add(agentForm);
		  }
		return listForm;
	}
	public void deleteAgent(int id) {
		Agent agent = agentDao.findObjectById(id);
		agentDao.delete(agent);
	}
	@Override
	public AgentForm findEmployeeById(int id) {
		Agent agent = agentDao.findObjectById(id);
		AgentForm agentForm = this.AgentPOTOVO(agent);
		return agentForm;
	}
	private AgentForm AgentPOTOVO(Agent agent) {
		AgentForm agentForm = new AgentForm();
		agentForm.setAccount(agent.getAccount());
		agentForm.setName(agent.getName());
		agentForm.setId(Integer.toString(agent.getId()));
		agentForm.setCor(agent.getCor());
		agentForm.setType(agent.getType());
		return agentForm;
	}
	@Override
	public boolean saveOrUpdateAgent(AgentForm agentForm) {
		 Agent agent = new Agent();
		 if(!existAgent(agentForm)){
			 agent.setAccount(agentForm.getAccount());
			 agent.setName(agentForm.getName());
			 agent.setCor(agentForm.getCor());
			 agent.setType(agentForm.getType());
			
			 if(agentForm.getId()!=null && ! "".equals(agentForm.getId())){
				 agent.setId(Integer.parseInt(agentForm.getId()));
				 agentDao.update(agent);
				 return true;
			 }
			 agentDao.save(agent);
			 return true;
		 }
		 
		return false;
	}
	private boolean existAgent(AgentForm agentForm) {
		List<Agent> list = agentDao.findAgent(agentForm); 
		if(list==null || list.size()==0){
			return false;
		}
		return true;
	}
	


}
