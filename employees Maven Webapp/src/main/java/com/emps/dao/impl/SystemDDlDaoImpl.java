package com.emps.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.emps.dao.SystemDDlDao;

import com.emps.model.SystemDDl;
import com.emps.pojo.SystemDDlForm;

@Repository(SystemDDlDao.SERVICE_NAME)
public class SystemDDlDaoImpl extends BaseDaoImpl<SystemDDl> implements SystemDDlDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemDDl> findSystemDDls(SystemDDlForm systemDDlForm) {
		  String keyword = systemDDlForm.getKeyword();
		//  Integer ddlcode = systemDDlForm.getDdlcode();
		  String ddlname = systemDDlForm.getDdlname();
		  String hql = "from SystemDDl s where  s.keyword = ? and s.ddlname = ?";
		   Session session = sessionFactory.getCurrentSession();
		   Query query = session.createQuery(hql);
		         query.setParameter(0, keyword);
		         query.setParameter(1, ddlname);
		        
		return query.list();
	}



}
