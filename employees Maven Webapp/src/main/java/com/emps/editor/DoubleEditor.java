package com.emps.editor;

import java.beans.PropertyEditorSupport;

public class DoubleEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		// TODO Auto-generated method stub
		return getValue().toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text == null || text.equals("")){
			text="0";
		}
		 setValue(Double.parseDouble(text));
	}

}
