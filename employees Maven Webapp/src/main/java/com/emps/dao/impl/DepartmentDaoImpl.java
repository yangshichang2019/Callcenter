package com.emps.dao.impl;



import org.springframework.stereotype.Repository;

import com.emps.dao.DepartmentDao;
import com.emps.model.Department;

@Repository(DepartmentDao.SERVICE_NAME)
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {



}
