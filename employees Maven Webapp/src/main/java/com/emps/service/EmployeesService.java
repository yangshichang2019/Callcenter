package com.emps.service;

import java.util.List;

import com.emps.pojo.EmployeesForm;

public interface EmployeesService {
public final String SERVICE_NAME="com.emps.service.impl.EmployeesServiceImpl";

public List<EmployeesForm> listEmployees();

public EmployeesForm findEmployeeById(String id);

}
