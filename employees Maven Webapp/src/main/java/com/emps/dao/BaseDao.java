package com.emps.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.emps.model.Agent;
import com.emps.util.PageInfo;

public interface BaseDao<T> {
  public void save(T t);
  public void update(T t);
  public void delete(T t);
  public T findObjectById(Serializable id);
  public List<T> findCollectionByCondition(String hqlwhere,
		                                    final Object[] params,
		                                    LinkedHashMap<String, String> orderby);
  public List<T> findCollectionByConditionWithPage(String hqlwhere,
          final Object[] params,
          LinkedHashMap<String, String> orderby,
          PageInfo page);
  public List<T> findCollectionNoCondition();
  public void saveOrUpdate(Agent agent);
}
