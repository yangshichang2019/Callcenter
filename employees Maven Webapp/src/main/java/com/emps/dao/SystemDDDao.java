package com.emps.dao;

import java.util.List;

import com.emps.model.SystemDD;
import com.emps.model.SystemDDl;
import com.emps.pojo.SystemDDForm;

public interface SystemDDDao extends BaseDao<SystemDD> {
public final String  SERVICE_NAME="com.emps.dao.impl.SystemDDDaoImpl";

public List<SystemDD> findSystemDDs(SystemDDForm systemDDForm);
}
