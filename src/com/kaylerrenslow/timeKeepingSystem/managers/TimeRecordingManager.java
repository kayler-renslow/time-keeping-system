package com.kaylerrenslow.timeKeepingSystem.managers;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import com.kaylerrenslow.timeKeepingSystem.app.TimeKeeper;
import com.kaylerrenslow.timeKeepingSystem.data.TimeRecording;
import com.kaylerrenslow.timeKeepingSystem.data.TimeStamp;
import com.kaylerrenslow.timeKeepingSystem.eventHandlers.ShiftRecordEvent;
import com.kaylerrenslow.timeKeepingSystem.util.Date;

public class TimeRecordingManager{

	private boolean shiftStarted = false;
	private Button shiftStart;
	private Button shiftStop;
	private TableView<TimeRecording> tableView;

	public TimeRecordingManager(Button btnShiftStart, Button btnShiftStop, TableView<TimeRecording> tableView) {
		this.shiftStart = btnShiftStart;
		this.shiftStop = btnShiftStop;
		this.tableView = tableView;
		btnShiftStart.setOnAction(new ShiftRecordEvent(this));
		btnShiftStop.setOnAction(new ShiftRecordEvent(this));

		shiftStop.setDisable(true);
	}

	public void startShift() {
		if (shiftStarted){
			throw new IllegalStateException("Can not start a shift that has already been started.");
		}
		shiftStarted = true;
		Date date = Date.getCurrentDate();
		tableView.getItems().add(0, new TimeRecording(date, new TimeStamp(date.hour, date.minute), null));
		shiftStart.setDisable(true);
		shiftStop.setDisable(false);
		TimeKeeper.appData.needsSaving = true;
	}

	public void endShift() {
		if (!shiftStarted){
			throw new IllegalStateException("Can not end a shift that hasn't been started.");
		}
		shiftStarted = false;
		Date date = Date.getCurrentDate();

		tableView.getItems().get(0).setEndTime(new TimeStamp(date.hour, date.minute));
		tableView.getColumns().get(0).setVisible(false);
		tableView.getColumns().get(0).setVisible(true);
		shiftStart.setDisable(false);
		shiftStop.setDisable(true);
		TimeKeeper.appData.needsSaving = true;

	}
	
	public void setShiftStarted(boolean b){
		this.shiftStarted = b;
		shiftStart.setDisable(b);
		shiftStop.setDisable(!b);
	}

	public boolean shiftStarted() {
		return shiftStarted;
	}

}
