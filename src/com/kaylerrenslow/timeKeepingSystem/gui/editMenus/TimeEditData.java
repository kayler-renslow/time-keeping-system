package com.kaylerrenslow.timeKeepingSystem.gui.editMenus;

import com.kaylerrenslow.timeKeepingSystem.data.EditData;
import com.kaylerrenslow.timeKeepingSystem.data.TimeStamp;

public class TimeEditData extends EditData{
	public TimeEditData() {
		super(3);
	}

	public TimeStamp getTime() {
		if (this.data[0].length() == 0 || this.data[1].length() == 0 || this.data[2].length() == 0){
			return null;
		}
		int hour = Integer.parseInt(this.data[0]);
		int minute = Integer.parseInt(this.data[1]);
		String ampm = this.data[2];
		if (ampm.equalsIgnoreCase("pm")){
			hour += 12;
		}
		return new TimeStamp(hour, minute);
	}
}
