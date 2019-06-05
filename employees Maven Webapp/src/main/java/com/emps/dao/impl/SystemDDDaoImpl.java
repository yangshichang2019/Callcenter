package com.emps.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.emps.dao.SystemDDDao;
import com.emps.dao.SystemDDlDao;
import com.emps.model.SystemDD;
import com.emps.model.SystemDDl;
import com.emps.pojo.SystemDDForm;
import com.emps.pojo.SystemDDlForm;

@Repository(SystemDDDao.SERVICE_NAME)
public class SystemDDDaoImpl extends BaseDaoImpl<SystemDD> implements SystemDDDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemDD> findSystemDDs(SystemDDForm systemDDForm) {
		//return null;
		  String name = systemDDForm.getName();
		  String code = systemDDForm.getCode();
		//  String ddlname = systemDDForm.getDdlname();
		  String hql = "from SystemDD s where  s.name = ? or s.code = ?";
		   Session session = sessionFactory.getCurrentSession();
		   Query query = session.createQuery(hql);
		         query.setParameter(0, name);
		         query.setParameter(1, code);
		        
		return query.list();
		
	}



}
