package com.emps.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.emps.pojo.SystemDDForm;
import com.emps.pojo.SystemDDItemForm;
import com.emps.pojo.SystemDDlForm;

public interface SystemDDService {
  public final String SERVICE_NAME="com.emps.service.SystemDDServiceImpl";

public boolean saveOrUpdate(SystemDDForm systemDDForm) throws ParseException;

public List<SystemDDForm> findSystemDDByConditionWithPage(SystemDDForm systemDDForm, HttpServletRequest request);

public List<SystemDDItemForm> findSystemDDItemById(String id);

public boolean saveOrUpdate(SystemDDItemForm systemDDItemForm);

public SystemDDItemForm findSystemDDItemByDDItemId(String dditem_id);
public SystemDDForm findSystemDDById(String seqid);

public void deleteSystemDDItemById(String dditem_id);
  
}
