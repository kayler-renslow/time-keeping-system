package com.kaylerrenslow.timeKeepingSystem.data;

import java.util.Arrays;

public class EditData{
	public String[] data;
	
	public EditData(String...data) {
		this.data = data;
	}
	
	public EditData(int size) {
		data = new String[size];
	}
	
	@Override
	public String toString() {
		return Arrays.toString(data);
	}
}
