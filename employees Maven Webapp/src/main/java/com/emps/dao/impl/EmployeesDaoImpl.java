package com.emps.dao.impl;

import org.springframework.stereotype.Repository;

import com.emps.dao.EmployeesDao;
import com.emps.model.Employees;

@Repository(EmployeesDao.SERVICE_NAME)
public class EmployeesDaoImpl extends BaseDaoImpl<Employees> implements EmployeesDao {

}
