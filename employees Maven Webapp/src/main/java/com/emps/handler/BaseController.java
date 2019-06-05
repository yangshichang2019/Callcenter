package com.emps.handler;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.emps.editor.DateEditor;
import com.emps.editor.DoubleEditor;
import com.emps.editor.IntegerEditor;

public class BaseController {
	@InitBinder
  protected void initBinder(WebDataBinder binder){
	
	  binder.registerCustomEditor(Date.class, new DateEditor());
	  binder.registerCustomEditor(Integer.class, new IntegerEditor());
	  binder.registerCustomEditor(Double.class, new DoubleEditor());
  }
	protected String renderSuccess(){
		return "";
	}
	
	protected String renderError(String message){
		return message;
	}
}
