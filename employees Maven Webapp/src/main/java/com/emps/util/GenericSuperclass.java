package com.emps.util;

import java.lang.reflect.ParameterizedType;

import org.hibernate.usertype.DynamicParameterizedType.ParameterType;

public class GenericSuperclass {
  public static Class getClass(Class tclass){
	  ParameterizedType pt =(ParameterizedType) tclass.getGenericSuperclass();
	  Class entity = (Class) pt.getActualTypeArguments()[0];
	  return entity;
  }
}
