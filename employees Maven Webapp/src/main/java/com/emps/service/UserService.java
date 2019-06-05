package com.emps.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.emps.model.Permission;
import com.emps.model.Role;
import com.emps.model.User;
import com.emps.pojo.UserForm;

public interface UserService {
public final String SERVICE_NAME ="com.emps.service.impl.UserServiceImpl";

public User findUserByName(String name);
public List<UserForm> ListUser();
List<Role> findRolesByName(String user_name);
public UserForm findUserById(int id);
public List<UserForm> findUserByConditionWithPage(HttpServletRequest request,UserForm userForm);
public List<Permission> findPermissionByRoles(List<String> rolesCollections);
public boolean saveOrUpdate(UserForm userForm);
}
