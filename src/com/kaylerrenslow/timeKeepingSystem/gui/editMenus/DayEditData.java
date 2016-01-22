package com.kaylerrenslow.timeKeepingSystem.gui.editMenus;

import com.kaylerrenslow.timeKeepingSystem.data.EditData;

public class DayEditData extends EditData{
	public DayEditData() {
		super(1);
	}

	public int getDay() {

		return Integer.parseInt(this.data[0]);
	}
}
