package com.konka.emps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.emps.model.Permission;
import com.emps.model.Role;
import com.emps.model.User;
@Transactional
public class RoleTest extends BaseTest {
  @Resource
  private SessionFactory sessionFactory;
@Test
public void findRolesByName() {
	String user_name="jacky";

	//String hql = " from User u where u.user_name= ? ";
	String hql = "from Role r join fetch r.user_role ur  where ur.user.user_name = ? ";
	Session session = sessionFactory.getCurrentSession();
	Query query = session.createQuery(hql);
	query.setParameter(0, user_name);

	  @SuppressWarnings("unchecked")
	List<Role> roles = query.list();
	System.out.println(query.list().get(0));
}
@Test
public void findPermissionByName() throws ParseException{
	String user_name="jacky";
	String role_id = "1";
	String hql = " select p from Permission p "
			+ "join fetch p.role_permission rp "
			+ "join  rp.role r "
			+ "join fetch r.user_role ur   "
			+ "where ur.user.user_name = ? ";
//			+ "join  rp.role r "
//			+ "join fetch r.user_role ur   "
//			+ "where ur.user.user_name = ? ";
//	String hql= " select p.permission_id,p.permission_name,p.created_time,p.update_time from permission p,"
//			+ "role_permission rp,"
//			+ "user_role ur, "
//			+ "user u"
//			+ " where p.permission_id = rp.permission_id "
//			+ "and rp.role_id=ur.role_id "
//			+ "and ur.user_id=u.user_id "
//			+ "and u.user_name= " + user_name;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  System.out.println("test2��");
	Session session = sessionFactory.getCurrentSession();
	//Query query = session.createSQLQuery(hql);
	Query query = session.createQuery(hql); 
	query.setParameter(0, user_name);
	@SuppressWarnings("unchecked")
	List<Permission> list = query.list();
	System.out.println(list.get(0).getPermission_name());
}
}
