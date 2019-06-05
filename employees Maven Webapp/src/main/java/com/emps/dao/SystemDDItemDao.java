package com.emps.dao;

import java.util.List;

import com.emps.model.SystemDD;
import com.emps.model.SystemDDItem;
import com.emps.model.SystemDDl;
import com.emps.pojo.SystemDDForm;
import com.emps.pojo.SystemDDItemForm;

public interface SystemDDItemDao extends BaseDao<SystemDDItem> {
public final String  SERVICE_NAME="com.emps.dao.impl.SystemDDItemDaoImpl";



public List<SystemDDItem> findSystemDDItemsById(String id);



public List<SystemDDItem> findSystemDDItems(SystemDDItemForm systemDDItemForm);
}
