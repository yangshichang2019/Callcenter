package com.emps.dao;

import com.emps.model.Employees;
public interface EmployeesDao extends BaseDao<Employees> {
	public final String SERVICE_NAME="com.emps.dao.impl.EmployeesDaoImpl";

}
