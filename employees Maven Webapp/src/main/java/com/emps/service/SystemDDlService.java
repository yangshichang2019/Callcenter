package com.emps.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.emps.pojo.SystemDDlForm;


public interface SystemDDlService {
public final String SERVICE_NAME="com.emps.service.impl.SystemDDlServiceImpl";

public List<SystemDDlForm> findSystemDDlByConditionWithPage(SystemDDlForm systemDDlForm, HttpServletRequest request);

public boolean saveOrUpdate(SystemDDlForm systemDDlForm);

public SystemDDlForm findSystemDDlById(Integer seqid);

}
