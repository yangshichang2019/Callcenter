package com.emps.dao.impl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.emps.dao.AgentDao;
import com.emps.model.Agent;
import com.emps.pojo.AgentForm;
@Repository(AgentDao.SERVICE_NAME)
public class AgentDaoImpl extends BaseDaoImpl<Agent> implements AgentDao {

	public List<Agent> listCollections(){
	 String hql = " from Agent where 1=1";
	Session session =  sessionFactory.getCurrentSession();
	  Query query = session.createQuery(hql);
	   List<Agent> list = query.list();
	return list;
	}

	@Override
	public List<Agent> findAgent(AgentForm agentForm) {
		 String name = agentForm.getName();
		 String account = agentForm.getAccount();
		 String id = agentForm.getId();
		String hql ="from Agent a where (a.name=? or a.account=?) and a.id<>?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		      query.setString(0, name);
		      query.setString(1, account);
		      query.setString(2, id);
		      List<Agent> listAgent =query.list();
		return listAgent;
	}

   public void save(Agent agent){
	   Session session = sessionFactory.getCurrentSession();
	   session.save(agent);
   }
}
