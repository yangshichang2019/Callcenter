package com.emps.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.EntityEntry;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.emps.dao.BaseDao;
import com.emps.model.Agent;
import com.emps.pojo.AgentForm;
import com.emps.util.PageInfo;


public class BaseDaoImpl<T> implements BaseDao<T> {
	   Class clazz =this.getClass();
	   ParameterizedType type =  (ParameterizedType) clazz.getGenericSuperclass();
	   Class entity = (Class) type.getActualTypeArguments()[0];
	//Class entity = com.emps.util.GenericSuperclass.getClass(this.getClass());
	 @Resource(name="hibernateTemplate")
	 HibernateTemplate hibernateTemplete;
	  @Resource(name="sessionFactory")
	  SessionFactory sessionFactory;
    
	public HibernateTemplate getHibernateTemplete() {
		return hibernateTemplete;
	}

	public void setHibernateTemplete(HibernateTemplate hibernateTemplete) {
		this.hibernateTemplete = hibernateTemplete;
	}
    
   public void save(T t){
	   this.hibernateTemplete.save(t);
   }
   public void update(T t){
	   this.hibernateTemplete.update(t);
   }
   public void delete(T t){
	   this.hibernateTemplete.delete(t);
   }
   @SuppressWarnings("unchecked")
   
   public T findObjectById(Serializable id){
	   return (T) this.hibernateTemplete.get(entity, id);
   }
   
//  public String orderbyCondition(LinkedHashMap<String, String> orderby){
//    	   StringBuffer buffer = new StringBuffer();
//    	   buffer.append("order by");
//    	   for(Map.Entry<String, String> map: orderby.entrySet()){
//    		   buffer.append(" " + map.getKey() + map.getValue() +" " +",");
//    	   }
//    	   buffer.deleteCharAt(buffer.length()-1);
//    	   return buffer.toString();
//       }
   public List<T> findCollectionNoCondition(){
	   final String hql ="from " + entity.getSimpleName() ;
	   System.out.println(hql);
	   @SuppressWarnings("unchecked")
	
	List<T> list = (List<T>) this.getHibernateTemplete().execute(new HibernateCallback<Object>() {
		public Object doInHibernate(Session session) throws HibernateException {
               Query query = session.createQuery(hql);
			   return  query.list();
		}
	});
	   return list;
   }
   public  List<T> findCollectionByCondition(String hqlwhere,final Object[] params,LinkedHashMap<String, String> orderby){
      String hql="from " + entity.getSimpleName() + " o where 1=1 ";
      String hqlorderby = this.orderbyCondition(orderby);
       hql = hql + hqlwhere + hqlorderby;  
      final String finalhql = hql;
 
	   @SuppressWarnings("unchecked")
	List<T> list = (List<T>) this.getHibernateTemplete().execute(new HibernateCallback<Object>() {
		public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(finalhql);
			    setparams(query, params);
			    return  query.list();
		}
	});
	return list;
	  
   }
   
   public List<T> findCollectionByConditionWithPage(String hqlwhere, 
		                                            final Object[] params, 
		                                            LinkedHashMap<String, String> orderby,
		                                            final PageInfo page
		                                            ){
	       String hql = "from " + entity.getSimpleName() + " o where 1=1 ";
	      String hqlorderby = this.orderbyCondition(orderby);
	      if(hqlorderby !=null){
	      hql = hql + hqlwhere + hqlorderby;
	      }else{
	    	  hql=hql + hqlwhere;
	      }
	      final String finalhql = hql;
	      @SuppressWarnings("unchecked")
		List<T> list = (List<T>) this.getHibernateTemplete().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(finalhql);			
				setparams(query, params);			
				page.setTotalCount(query.list().size());
				page.calculatePage();
				query.setMaxResults(page.getNumPerPage());
				query.setFirstResult(page.getCurrentPageStartRow());
				
				return  query.list();
			
			}
	    	  
		});
	 return list;  
   }
   /**
    * orderby 条件设置
    * @param orderby
    * @return String
    */
   private String orderbyCondition(LinkedHashMap<String, String> orderby) {
	   StringBuffer sb = new StringBuffer();	  
	  if(orderby!=null){
		  sb.append(" order by ") ;
		  for(Map.Entry<String, String> map:orderby.entrySet()){
			 sb.append(" " + map.getKey() + " " + map.getValue() +",");
		  }
		  sb.deleteCharAt(sb.length() - 1);
		  return sb.toString();
	  }
	return null;
}

public void setparams(Query query,Object[] params){
	   for(int i =0;i<params.length;i++){
		   query.setParameter(i, params[i]);
	   }
   }
public void saveOrUpdate(Agent agent){
	this.hibernateTemplete.saveOrUpdate(agent);
}
}
