package com.emps.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.emps.dao.EmployeesDao;
import com.emps.model.Employees;
import com.emps.pojo.EmployeesForm;
import com.emps.service.EmployeesService;
@Service(EmployeesService.SERVICE_NAME)
public class EmployeesServiceImpl implements EmployeesService {
	  @Resource(name=EmployeesDao.SERVICE_NAME)
	  EmployeesDao employeesDao;
	  
	  @Resource(name="sessionFactory")
	  SessionFactory sessionFactory;
	  
	  public List<EmployeesForm> listEmployees(){
		  
		  String hql = "from Employees e";
		  Session session = sessionFactory.getCurrentSession();
		 // Transaction tx =  session.beginTransaction();
		//  tx.begin();
		  @SuppressWarnings("unchecked")
		List<Employees> list = session.createQuery(hql).list();
		 // List<Employees> list = employeesDao.findCollectionNoCondition();
		 // session.getTransaction().commit();
	//	  tx.commit();
	//	  session.close();
	//	  sessionFactory.close();
	//	  List<Employees> list = employeesDao.findCollectionNoCondition();
//		  
		  return employeesVOTOPO(list);
		  
		  
	  }
	   
  public List<EmployeesForm> findEmployees(EmployeesForm employeesform){  
	  String hqlwhere = "";
	  List<String> paramsList = new ArrayList<String>();
	  if(employeesform != null){
		  if(employeesform.getName() !=null && !"".equals(employeesform.getName())){
			  hqlwhere = " and o.name like ? ";
			  paramsList.add("%" + employeesform.getName() + "%");
		  }
		  else if(employeesform.getGender()!=null && !"".equals(employeesform.getGender())){
			  hqlwhere = " and o.gender like ? ";
			  paramsList.add("%" + employeesform.getGender() + "%");
		  }
		  else if(employeesform.getEmail()!=null && !"".equals(employeesform.getEmail())){
			  hqlwhere = " and o.email like ? ";
			  paramsList.add("%" + employeesform.getEmail() + "%");
		  }
		  else if(employeesform.getDepartment()!=null && !"".equals(employeesform.getDepartment())){
			  hqlwhere = " and o.department like ? ";
			  paramsList.add("%" + employeesform.getDepartment() + "%");
		  }
	  } 
	     Object[] params = paramsList.toArray();
		List<Employees> list = employeesDao.findCollectionByCondition(hqlwhere, params, null);
		return employeesVOTOPO(list);  
  }
  /*
   * voת��Ϊpo
   */
 private List<EmployeesForm> employeesVOTOPO(List<Employees> list){
	  List<EmployeesForm> listForm = new ArrayList<EmployeesForm>();
	  EmployeesForm employeesForm = null;
	  for(int i=0;list!=null && !"".equals(list) && i<list.size();i++){
		  employeesForm = new EmployeesForm();
		  employeesForm.setId(list.get(i).getId());
		  employeesForm.setName(list.get(i).getName());
		  employeesForm.setGender(list.get(i).getGender());
		  employeesForm.setEmail(list.get(i).getEmail());
		  employeesForm.setDepartment(list.get(i).getDepartment());
		  listForm.add(i,employeesForm );
	  }
	  return listForm;
  }

@Override
public EmployeesForm findEmployeeById(String id) {
	employeesDao.findObjectById(id);
	return null;
}
}
