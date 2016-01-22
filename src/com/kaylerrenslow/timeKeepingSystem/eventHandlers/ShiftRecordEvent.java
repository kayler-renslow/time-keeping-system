package com.kaylerrenslow.timeKeepingSystem.eventHandlers;

import com.kaylerrenslow.timeKeepingSystem.managers.TimeRecordingManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ShiftRecordEvent implements EventHandler<ActionEvent>{

	private TimeRecordingManager manager;

	public ShiftRecordEvent(TimeRecordingManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void handle(ActionEvent event) {
		if(!manager.shiftStarted()){
			manager.startShift();
		}else{
			manager.endShift();
		}
		
	}

}
