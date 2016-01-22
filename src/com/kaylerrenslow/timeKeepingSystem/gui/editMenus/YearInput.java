package com.kaylerrenslow.timeKeepingSystem.gui.editMenus;

import com.kaylerrenslow.timeKeepingSystem.data.IEditMenu;

public class YearInput extends NumberField implements IEditMenu{

	private YearEditData data = new YearEditData();

	public YearInput() {
		super(false);
		this.setPromptText("Year");
		this.setMaxWidth(111);
	}
	
	public void reset(){
		this.setText("");
	}

	@Override
	public YearEditData getData() {
		data.data[0] = this.getText();
		return data;
	}
}
