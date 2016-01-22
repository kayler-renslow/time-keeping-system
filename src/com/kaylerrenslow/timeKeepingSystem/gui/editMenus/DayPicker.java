package com.kaylerrenslow.timeKeepingSystem.gui.editMenus;

import com.kaylerrenslow.timeKeepingSystem.data.IEditMenu;

import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;

public class DayPicker extends ChoiceBox<String> implements IEditMenu{

	private DayEditData data = new DayEditData();

	public DayPicker() {
		for (int i = 1; i <= 31; i++){
			this.getItems().add(i + "");
		}
		HBox.setMargin(this, new Insets(0, 4, 0, 0));
	}

	public void setDay(int day) {
		if (day > 31 || day < 1){
			throw new IllegalArgumentException("Can not set the day to " + day);
		}
		this.getSelectionModel().select(day - 1);
	}

	public void reset() {
		this.getSelectionModel().clearSelection();
	}

	@Override
	public DayEditData getData() {
		data.data[0] = this.getSelectionModel().getSelectedItem();
		return data;
	}
}
