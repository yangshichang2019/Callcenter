package com.emps.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor extends PropertyEditorSupport{

	@Override
	public String getAsText() {
		return getValue().toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	Date date = null;
	try {
		date = format.parse(text);
	} catch (ParseException e) {
		format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(text);
		} catch (ParseException e1) {

		}
		setValue(date);
	}
	}

}
