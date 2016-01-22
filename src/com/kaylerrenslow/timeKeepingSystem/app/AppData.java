package com.kaylerrenslow.timeKeepingSystem.app;

import java.io.File;

import com.kaylerrenslow.timeKeepingSystem.managers.EditManager;
import com.kaylerrenslow.timeKeepingSystem.managers.MenuFileManager;
import com.kaylerrenslow.timeKeepingSystem.managers.TableViewManager;
import com.kaylerrenslow.timeKeepingSystem.managers.TimeRecordingManager;

public class AppData{

	public File saveLocation;
	
	public double hourlyRate = 10.5;
	public TimeRecordingManager recordingManager;
	public TableViewManager tableManager;
	public EditManager editManager;
	public MenuFileManager menuFileManager;
	
	public boolean needsSaving = false;

	public void prepare(TimeKeepWindow window) {
		recordingManager = new TimeRecordingManager(window.btnShiftStart, window.btnShiftStop, window.tableView);
		tableManager = new TableViewManager(window.tableView, window.tableViewDay, window.tableViewStartTime, window.tableViewEndTime, window.tableViewHours, window.tableViewMoney, window.tableViewMonth, window.tableViewYear, window.tableViewEpoch);
		menuFileManager = new MenuFileManager(window.menuFileNew, window.menuFileOpen, window.menuFileSave, window.menuFileSaveAs, window.menuFileClose);
		editManager = new EditManager(window.vboxEdit, window.tfEditValue, window.btnSubmitChanges, window.btnCancelChanges);
	}
	
	

}
