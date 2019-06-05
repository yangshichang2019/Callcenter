package com.emps.editor;

import java.beans.PropertyEditorSupport;

public class IntegerEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		return getValue().toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text == null || text.equals("")){
			text = "0";
		}
		setValue(Integer.parseInt(text));
	}

}
