package com.kaylerrenslow.timeKeepingSystem.data;

import java.util.Arrays;

import com.kaylerrenslow.timeKeepingSystem.gui.editMenus.DayEditData;
import com.kaylerrenslow.timeKeepingSystem.gui.editMenus.DayPicker;
import com.kaylerrenslow.timeKeepingSystem.gui.editMenus.MonthEditData;
import com.kaylerrenslow.timeKeepingSystem.gui.editMenus.MonthPicker;
import com.kaylerrenslow.timeKeepingSystem.gui.editMenus.TimeEditData;
import com.kaylerrenslow.timeKeepingSystem.gui.editMenus.TimePicker;
import com.kaylerrenslow.timeKeepingSystem.gui.editMenus.YearEditData;
import com.kaylerrenslow.timeKeepingSystem.gui.editMenus.YearInput;
import com.kaylerrenslow.timeKeepingSystem.managers.EditManager;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DataEditor{

	private EditData[] editData = new EditData[5];
	private boolean editing = false;

	private HBox level1 = new HBox();
	private HBox level2 = new HBox();
	private HBox level3 = new HBox();

	private DayPicker day = new DayPicker();
	private TimePicker startTime = new TimePicker();
	private TimePicker endTime = new TimePicker();
	private MonthPicker month = new MonthPicker();
	private YearInput year = new YearInput();

	private final Label[] labels = {getLabel("Day: "), getLabel("Month: "), getLabel("Start Time: "), getLabel("End Time:  ")};

	private static final int heightMargin = 4;

	public DataEditor(EditManager editManager) {
		setDisabled(true);

		level1.setPadding(new Insets(heightMargin, 0, 0, 0));
		level2.setPadding(new Insets(heightMargin, 0, 0, 0));
		level3.setPadding(new Insets(heightMargin, 0, 0, 0));

		level1.getChildren().add(labels[0]);
		level1.getChildren().add(day);
		level1.getChildren().add(labels[1]);
		level1.getChildren().add(month);
		level1.getChildren().add(year);

		level2.getChildren().add(labels[2]);
		level2.getChildren().add(startTime);

		level3.getChildren().add(labels[3]);
		level3.getChildren().add(endTime);

		editManager.vboxEdit.getChildren().add(level1);
		editManager.vboxEdit.getChildren().add(level2);
		editManager.vboxEdit.getChildren().add(level3);
	}

	private void setDisabled(boolean val) {
		day.reset();
		month.reset();
		year.reset();
		startTime.reset();
		endTime.reset();
		day.setDisable(val);
		month.setDisable(val);
		year.setDisable(val);
		startTime.setDisable(val);
		endTime.setDisable(val);

		for (Label label : labels){
			label.setDisable(val);
		}
	}

	public void startEdit(TimeRecording record) {
		setDisabled(false);
		day.setDay(record.getDay());
		month.setMonth(record.getMonth());
		year.setText(record.getYear() + "");
		startTime.setTime(record.getStartTime());
		if (record.getEndTime() != null){
			endTime.setTime(record.getEndTime());
		}
		editing = true;
	}

	public void endEdit() {
		if (!editing){
			throw new IllegalStateException("Can not end an edit when one was never started");
		}
		Arrays.fill(editData, null);
		editing = false;
		setDisabled(true);
		setDisabled(true);
	}

	public DayEditData getDayData() {
		return day.getData();
	}

	public MonthEditData getMonthData() {
		return month.getData();
	}

	public YearEditData getYearData() {
		return year.getData();
	}

	public TimeEditData getStartTimeData() {
		return startTime.getData();
	}

	public TimeEditData getEndTimeData() {
		return endTime.getData();
	}

	public boolean editing() {
		return editing;
	}

	private static Label getLabel(String str) {
		Label label = new Label(str);
		HBox.setMargin(label, new Insets(heightMargin, 4, 0, 0));
		return label;
	}

	public boolean noErrors() {
		return !year.hasError() && startTime.getData().getTime() != null;
	}
}
