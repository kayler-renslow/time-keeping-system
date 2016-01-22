package com.kaylerrenslow.timeKeepingSystem.gui.editMenus;

import com.kaylerrenslow.timeKeepingSystem.data.EditData;

public class MonthEditData extends EditData{

	public MonthEditData() {
		super(1);
	}

	public int getMonth() {
		return Integer.parseInt(this.data[0]);
	}
}
