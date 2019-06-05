package com.emps.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
   public static Date StringToDate(String str){
	   Date date = null;
	   if(str != null && !"".equals(str)){
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		   try {
			
			date = sdf.parse(str);
			   sdf.setLenient(false);
		} catch (ParseException e) {
		     try{
		    	 sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	   
		    	 date = sdf.parse(str);
		    	 sdf.setLenient(false);
		     }catch (Exception e1) {
				// TODO: handle exception
			}
		}
	   }
	return date;
   }
   public static String DateToString(Date date){
	   String str = null;
	   if(date != null && !"".equals(date)){
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 try{
		   str = sdf.format(date);
		   sdf.setLenient(false);
		 } catch (Exception e) {
			 sdf = new SimpleDateFormat("yyyy-MM-dd");
			 str = sdf.format(date);
			 sdf.setLenient(false);
		}
		 }
	return str;
   }
}
