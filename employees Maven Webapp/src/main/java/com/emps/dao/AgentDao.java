package com.emps.dao;

import java.util.List;

import com.emps.model.Agent;
import com.emps.pojo.AgentForm;


public interface AgentDao extends BaseDao<Agent> {
public final String SERVICE_NAME="com.emps.dao.impl.AgentDaoImpl";

public List<Agent> listCollections();

public List<Agent> findAgent(AgentForm agentForm);
public void save(Agent agent)   ;



}
