package com.emps.dao;

import java.util.List;

import com.emps.model.User;
import com.emps.pojo.UserForm;

public interface UserDao extends BaseDao<User> {
public final String SERVICE_NAME="com.emps.dao.impl.UserDaoImpl";

public List<User> findUsers(UserForm userForm);



}
