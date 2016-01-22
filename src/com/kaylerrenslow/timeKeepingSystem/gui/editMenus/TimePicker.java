package com.kaylerrenslow.timeKeepingSystem.gui.editMenus;

import com.kaylerrenslow.timeKeepingSystem.data.IEditMenu;
import com.kaylerrenslow.timeKeepingSystem.data.TimeStamp;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TimePicker extends HBox implements IEditMenu{
	private NumberField hour = new NumberField(0, 24, true);
	private NumberField minute = new NumberField(0, 59, false);
	private ChoiceBox<String> am_pm = new ChoiceBox<>();
	private TimeEditData data = new TimeEditData();

	public TimePicker() {
		this.getChildren().add(hour);
		this.getChildren().add(new Label(": "));
		this.getChildren().add(minute);
		this.getChildren().add(am_pm);

		am_pm.getItems().add("AM");
		am_pm.getItems().add("PM");

		hour.setMaxWidth(80);
		minute.setMaxWidth(80);
		am_pm.setMaxWidth(50);

		hour.setPromptText("Hour");
		minute.setPromptText("Minute");
	}

	public void setTime(TimeStamp time) {
		if (time.getHour() > 12){
			hour.setText(Math.abs(12 - time.getHour()) + "");
		}else{
			hour.setText(time.getHour() + "");
		}
		String min = time.getMinute() > 10 ? time.getMinute() + "" : "0" + time.getMinute();
		minute.setText(min);
		if (time.getHour() > 12){
			am_pm.getSelectionModel().select(1);
		}else{
			am_pm.getSelectionModel().select(0);
		}
	}

	public void reset() {
		hour.setText("");
		minute.setText("");
		am_pm.getSelectionModel().clearSelection();
	}
	

	@Override
	public TimeEditData getData() {
		data.data[0] = hour.getText();
		data.data[1] = minute.getText();
		data.data[2] = am_pm.getSelectionModel().getSelectedItem();
		return data;
	}
}