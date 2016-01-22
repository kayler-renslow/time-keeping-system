package com.kaylerrenslow.timeKeepingSystem.gui.editMenus;

import com.kaylerrenslow.timeKeepingSystem.data.IEditMenu;

import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;

public class MonthPicker extends ChoiceBox<String> implements IEditMenu{

	private MonthEditData data = new MonthEditData();

	public MonthPicker() {
		for (int i = 1; i <= 12; i++){
			this.getItems().add(i + "");
		}
		HBox.setMargin(this, new Insets(0, 4, 0, 0));
	}

	public void setMonth(int month) {
		if (month > 12 || month < 1){
			throw new IllegalArgumentException("Can not set the month to " + month);
		}
		this.getSelectionModel().select(month - 1);
	}

	public void reset() {
		this.getSelectionModel().clearSelection();
	}

	@Override
	public MonthEditData getData() {
		data.data[0] = this.getSelectionModel().getSelectedItem();
		return data;
	}
}
