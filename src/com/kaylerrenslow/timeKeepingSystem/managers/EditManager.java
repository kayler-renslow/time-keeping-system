package com.kaylerrenslow.timeKeepingSystem.managers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import com.kaylerrenslow.timeKeepingSystem.app.TimeKeeper;
import com.kaylerrenslow.timeKeepingSystem.data.DataEditor;
import com.kaylerrenslow.timeKeepingSystem.data.TimeRecording;

public class EditManager{

	private DataEditor editor;
	public VBox vboxEdit;
	private Button btnSubmitChanges;
	private Button btnCancelChanges;
	private TimeRecording editing;

	public EditManager(VBox vboxEdit, TextField tfEditValue, Button btnSubmitChanges, Button btnCancelChanges) {
		this.vboxEdit = vboxEdit;
		this.editor = new DataEditor(this);
		this.btnSubmitChanges = btnSubmitChanges;
		this.btnCancelChanges = btnCancelChanges;
		btnSubmitChanges.setDisable(true);
		btnCancelChanges.setDisable(true);

		btnCancelChanges.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				cancelEdit();
			}

		});

		btnSubmitChanges.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if(editor.noErrors()){
					endEdit();					
				}
			}
		});
	}

	public void startEdit(TimeRecording record) {
		if (editor.editing()){
			return;
		}
		this.editing = record;
		editor.startEdit(record);
		btnSubmitChanges.setDisable(false);
		btnCancelChanges.setDisable(false);
	}

	public void cancelEdit() {
		if (!editor.editing()){
			return;
		}
		reset();
		editor.endEdit();
	}

	public void endEdit() {
		if (!editor.editing()){
			return;
		}
		reset();
		this.editing.setDay(editor.getDayData().getDay());
		this.editing.setMonth(editor.getMonthData().getMonth());
		this.editing.setYear(editor.getYearData().getYear());
		this.editing.setStartTime(editor.getStartTimeData().getTime());
		this.editing.setEndTime(editor.getEndTimeData().getTime());
		
		editor.endEdit();
		TimeKeeper.appData.tableManager.tableView.getColumns().get(0).setVisible(false);
		TimeKeeper.appData.tableManager.tableView.getColumns().get(0).setVisible(true);
		TimeKeeper.appData.needsSaving = true;		
	}

	private void reset() {
		btnSubmitChanges.setDisable(true);
		btnCancelChanges.setDisable(true);
	}

}
