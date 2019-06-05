package com.emps.container;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("static-access")
public class ServiceProvider {
   public static ServiceProviderCord spc;
   static{
	   spc = new ServiceProviderCord();
	   spc.load("beans.xml");
   }
   
   public static Object getService(String serviceName){
	 if(StringUtils.isBlank(serviceName)){
		 throw new RuntimeException("error");
	 }
	  Object object = null;
	  if(spc.ac.containsBean(serviceName)){
		  object = spc.ac.getBean(serviceName);
		  
	  }
	  if(object==null){
		  throw new RuntimeException("error:" + serviceName );
	  }
	  return object;
   }
}
