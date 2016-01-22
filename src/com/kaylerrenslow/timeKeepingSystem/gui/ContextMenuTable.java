package com.kaylerrenslow.timeKeepingSystem.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

import com.kaylerrenslow.timeKeepingSystem.app.TimeKeeper;
import com.kaylerrenslow.timeKeepingSystem.data.TimeRecording;

public class ContextMenuTable extends ContextMenu{

	private enum Item {
		EDIT;
	}
	
	private TableView<TimeRecording> table;

	public ContextMenuTable(TableView<TimeRecording> table) {
		this.table = table;
		MenuItem edit = new MenuItem("Edit");
		edit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				clicked(Item.EDIT);
			}
		});
		
		this.getItems().add(edit);
	}

	public void clicked(Item item) {
		if (item == Item.EDIT){
			TimeKeeper.appData.editManager.startEdit(table.getSelectionModel().getSelectedItem());
		}
	}

}
