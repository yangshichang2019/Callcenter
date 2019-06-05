package com.emps.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.emps.pojo.AgentForm;

public interface AgentService {
public final String SERVICE_NAME="com.emps.service.impl.AgentServiceImpl";
public List<AgentForm> listCollections();
public List<AgentForm> findCollectionByConditionWithPage(HttpServletRequest request,AgentForm agentForm);

public void deleteAgent(int id);
public AgentForm findEmployeeById(int id);
public boolean saveOrUpdateAgent(AgentForm agentForm);


}
