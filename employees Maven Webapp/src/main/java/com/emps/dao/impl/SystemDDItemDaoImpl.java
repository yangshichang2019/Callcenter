package com.emps.dao.impl;

import java.util.List;

import org.apache.shiro.session.mgt.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.emps.dao.SystemDDDao;
import com.emps.dao.SystemDDItemDao;
import com.emps.dao.SystemDDlDao;
import com.emps.model.SystemDD;
import com.emps.model.SystemDDItem;
import com.emps.model.SystemDDl;
import com.emps.pojo.SystemDDForm;
import com.emps.pojo.SystemDDItemForm;
import com.emps.pojo.SystemDDlForm;

@Repository(SystemDDItemDao.SERVICE_NAME)
public class SystemDDItemDaoImpl extends BaseDaoImpl<SystemDDItem> implements SystemDDItemDao {


	@Override
	public List<SystemDDItem> findSystemDDItemsById(String id) {
	String hql="from SystemDDItem o where o.systemdd.id = ? ";
	Session session = sessionFactory.getCurrentSession();
	       Query query = session.createQuery(hql);
	       query.setParameter(0, id);
	      return query.list();
	}

	@Override
	public List<SystemDDItem> findSystemDDItems(SystemDDItemForm systemDDItemForm) {
		final String name = systemDDItemForm.getName();
		final String id = systemDDItemForm.getParent_id();
		final String dditem_id=systemDDItemForm.getDditem_id();
		String shql;
		if(dditem_id!=null && !"".equals(dditem_id)){
			  shql = "from SystemDDItem o where o.systemdd.id = ? and o.name = ? and o.dditem_id<>dditem_id";	
		} else{
		shql  = "from SystemDDItem o where o.systemdd.id = ? and o.name = ?";
		}
	
		final String hql = shql;
		@SuppressWarnings("unchecked")
		List<SystemDDItem> systemDDItemList = (List<SystemDDItem>) this.getHibernateTemplete().execute(new HibernateCallback<List<SystemDDItem>>() {
			@Override
			public List<SystemDDItem> doInHibernate(Session session) throws HibernateException {
		         Query query = session.createQuery(hql);
		         query.setParameter(0, id);
		         query.setParameter(1, name);
				return  query.list();
			}
		});
		   return systemDDItemList;	
		}
			





}
