package com.emps.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.emps.dao.UserDao;
import com.emps.model.User;
import com.emps.pojo.UserForm;

@Repository(UserDao.SERVICE_NAME)
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUsers(UserForm userForm) {
		 String name = userForm.getUser_name();
		 String email = userForm.getEmail();
		 Integer id = userForm.getUser_id();
		 String hql = "from User u where (u.user_name = ? or u.email = ?) and u.user_id <> ?";
		 Session session = sessionFactory.getCurrentSession();
		       Query query = session.createQuery(hql);
		       query.setString(0, name);
		       query.setString(1, email);
		       query.setInteger(2, id);
		      List<User> list =  query.list();
		      return list;
		
	}

}