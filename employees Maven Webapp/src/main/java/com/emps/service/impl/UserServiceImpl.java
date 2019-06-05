package com.emps.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.emps.dao.DepartmentDao;
import com.emps.dao.UserDao;
import com.emps.model.Department;
import com.emps.model.Permission;
import com.emps.model.Role;
import com.emps.model.User;
import com.emps.pojo.UserForm;
import com.emps.service.UserService;
import com.emps.util.DateConvert;
import com.emps.util.PageInfo;
@Service(UserService.SERVICE_NAME)
public class UserServiceImpl implements UserService {
	  @Resource(name="sessionFactory")
	  SessionFactory sessionFactory;
	 @Resource(name=UserDao.SERVICE_NAME)
	  UserDao userDao;
	 @Resource(name=DepartmentDao.SERVICE_NAME)
	  DepartmentDao departmentDao;
	 public List<UserForm> ListUser(){
		 List<User> users = userDao.findCollectionNoCondition();
		 List<UserForm> listUser = ListUserPOTOVO(users);
		 return listUser;
	 }
	 
	 public List<UserForm> findUserByConditionWithPage(HttpServletRequest request, UserForm userForm){
		 PageInfo page = new PageInfo(request);
		 String hqlwhere ="";
		 List<Object> paramList = new ArrayList<Object>();

		 if(userForm != null){
			if(userForm.getUser_name() != null && !userForm.getUser_name().equals("")){
				hqlwhere = " and o.user_name like ? ";
				paramList.add("%" + userForm.getUser_name() + "%");
				
			}
			if(userForm.getStatus() != null && userForm.getStatus().equals("")){
				hqlwhere = hqlwhere + " and o.status like ? ";
				paramList.add(userForm.getStatus());
			}
		 }
		 Object[] params = paramList.toArray();
		 LinkedHashMap<String,String> orderby = new LinkedHashMap<String,String>();
		 orderby.put("user_name", "asc");
		 List<User> listUser = userDao.findCollectionByConditionWithPage(hqlwhere, params, orderby, page);
		 userForm.setPage(page);
		 List<UserForm> listForm = this.listUserPOTOVO(listUser);
		 return listForm;
	 }
	 
	  private List<UserForm> listUserPOTOVO(List<User> listUser) {
		List<UserForm> listForm = new ArrayList<UserForm>();
		UserForm userForm = null;
		for(int i = 0; i< listUser.size(); i++){
			userForm = new UserForm();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sdf.format(listUser.get(i).getCreated_time()));
			userForm.setUser_id(listUser.get(i).getUser_id());
			userForm.setUser_name(listUser.get(i).getUser_name());
			userForm.setCreated_time(DateConvert.DateToString(listUser.get(i).getCreated_time()));
			userForm.setUpdate_time(sdf.format(listUser.get(i).getUpdate_time()));
			userForm.setEmail(listUser.get(i).getEmail());
			userForm.setStatus(listUser.get(i).getStatus());
			
			listForm.add(userForm);
		}
		return listForm;
	}

	private List<UserForm> ListUserPOTOVO(List<User> users) {
       List<UserForm> list = new ArrayList<UserForm>();
       UserForm userForm = null;
       for(int i=0;i<users.size();i++){
    	   userForm = new UserForm();
    	   userForm.setUser_name(users.get(i).getUser_name());
    	   userForm.setUser_id(users.get(i).getUser_id());
    	   userForm.setStatus(users.get(i).getStatus());
    	   userForm.setEmail(users.get(i).getEmail());
    	   userForm.setCreated_time(DateConvert.DateToString(users.get(i).getCreated_time()));
    	   userForm.setUpdate_time(DateConvert.DateToString(users.get(i).getUpdate_time()));
    	   list.add(userForm);
       }
		return list;
	}



	public User findUserByName(String user_name){
		  String hql = " from User u where u.user_name= ? "  ;
		  
		  Session session = sessionFactory.getCurrentSession();
		  @SuppressWarnings("unchecked")
		Query query = session.createQuery(hql);
	    query.setParameter(0, user_name);
	    List<User> list = query.list();		 
	 System.out.println(list.get(0).getUser_name());
		  return list.get(0);
		  
	  }
	public UserForm findUserById(int id){
		User user = userDao.findObjectById(id);
		UserForm userForm = this.listUserPOTOVO(user);
		return userForm;
	}

	private UserForm listUserPOTOVO(User user) {
		UserForm userForm = new UserForm();
		Department dept = new Department();
		 userForm.setUser_id(user.getUser_id());
		 userForm.setUser_name(user.getUser_name());
		 userForm.setCreated_time(DateConvert.DateToString(user.getCreated_time()));
    	 userForm.setUpdate_time(DateConvert.DateToString(user.getUpdate_time()));
		 userForm.setStatus(user.getStatus());
		 userForm.setEmail(user.getEmail());
		 userForm.setPassword(user.getPassword());
		 if(user.getDept().getDeptId() == null){
	
		 }else{
			 dept.setDeptId(user.getDept().getDeptId());
			 dept.setDeptName(user.getDept().getDeptName());
			 userForm.setDept(dept);
		 }
		return userForm;
	}

	public List<Role> findRolesByName(String user_name) {
		String hql = "from Role r join fetch r.user_role ur  where ur.user.user_name = ?  ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, user_name);
		  List<Role> roles = query.list();
		return roles;
	}

	public List<Permission> findPermissionByRoles(List<String> roles) {
		String hql =  " from Permission p "
				+ "join fetch p.role_permission rp "
//				+ "join  rp.role r "
//				+ "join fetch r.user_role ur   "
				+ "where rp.role.role_name in (:roles) ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameterList("roles", roles);
		  List<Permission> permissions = query.list();
		return permissions;
	}

	@Override
	public boolean saveOrUpdate(UserForm userForm) {
		User user = new User();
		Department dept = departmentDao.findObjectById(userForm.getDept().getDeptId());

			user.setUser_name(userForm.getUser_name());
			user.setDept(dept);
			//user.setCreated_time(DateConvert.StringToDate(userForm.getCreated_time()));
			user.setCreated_time(DateConvert.StringToDate(userForm.getCreated_time()));
			user.setUpdate_time(DateConvert.StringToDate(userForm.getUpdate_time()));
			user.setEmail(userForm.getEmail());
			user.setPassword(userForm.getPassword());
			user.setStatus(userForm.getStatus());	
			if(!existUser(userForm)){
			if(userForm.getUser_id() !=null && ! "".equals(userForm.getUser_id())){
	
				user.setUser_id(userForm.getUser_id());
				userDao.update(user);
				return true;
			}else {
			userDao.save(user);
			return true;
		}
			}
		return false;
	}

	private boolean existUser(UserForm userForm) {
		List<User> list = userDao.findUsers(userForm);
		if(list !=null || list.size() !=0){
			return true;
		}
		return false;
	}
}
