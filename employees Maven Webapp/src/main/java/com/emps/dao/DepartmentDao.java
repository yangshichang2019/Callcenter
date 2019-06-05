package com.emps.dao;

import com.emps.model.Department;

public interface DepartmentDao extends BaseDao<Department> {
   public final String SERVICE_NAME="com.emps.dao.impl.DepartmentDaoImpl";
}
