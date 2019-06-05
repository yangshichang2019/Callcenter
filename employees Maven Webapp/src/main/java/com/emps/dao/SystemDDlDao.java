package com.emps.dao;

import java.util.List;

import com.emps.model.SystemDDl;
import com.emps.pojo.SystemDDlForm;

public interface SystemDDlDao extends BaseDao<SystemDDl> {
public final String  SERVICE_NAME="com.emps.dao.impl.SystemDDlDaoImpl";

public List<SystemDDl> findSystemDDls(SystemDDlForm systemDDlForm);
}
