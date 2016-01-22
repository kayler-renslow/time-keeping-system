package com.kaylerrenslow.timeKeepingSystem.gui.editMenus;

import com.kaylerrenslow.timeKeepingSystem.data.EditData;

public class YearEditData extends EditData{

	public YearEditData() {
		super(1);
	}

	public int getYear() {
		return Integer.parseInt(this.data[0]);
	}
}