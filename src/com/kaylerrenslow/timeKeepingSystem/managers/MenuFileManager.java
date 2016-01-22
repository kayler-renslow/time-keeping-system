package com.kaylerrenslow.timeKeepingSystem.managers;

import java.io.File;

import com.kaylerrenslow.timeKeepingSystem.app.TimeKeeper;
import com.kaylerrenslow.timeKeepingSystem.eventHandlers.MenuFileClickEvent;
import com.kaylerrenslow.timeKeepingSystem.util.FilePicker;

import javafx.scene.control.MenuItem;

public class MenuFileManager{

	public enum ClickItem {
		NEW, OPEN, SAVE, SAVE_AS, EXIT;
	}

	public MenuFileManager(MenuItem neww, MenuItem open, MenuItem save, MenuItem saveAs, MenuItem exit) {
		neww.setOnAction(new MenuFileClickEvent(this, ClickItem.NEW));
		open.setOnAction(new MenuFileClickEvent(this, ClickItem.OPEN));
		save.setOnAction(new MenuFileClickEvent(this, ClickItem.SAVE));
		saveAs.setOnAction(new MenuFileClickEvent(this, ClickItem.SAVE_AS));
		exit.setOnAction(new MenuFileClickEvent(this, ClickItem.EXIT));
	}

	public void click(ClickItem id) {
		if (id == ClickItem.NEW){
			TimeKeeper.newSave();
		}else if (id == ClickItem.OPEN){
			File file = new FilePicker("Open from File", "*.tk").open();
			if (file == null){
				return;
			}
			TimeKeeper.open(file);
		}else if (id == ClickItem.SAVE){
			if (TimeKeeper.appData.saveLocation == null){
				File file = new FilePicker("Save", "*.tk").save();
				if (file == null){
					return;
				}
				TimeKeeper.save(file);
			}else{
				TimeKeeper.save(TimeKeeper.appData.saveLocation);
			}
		}else if (id == ClickItem.SAVE_AS){
			File file = new FilePicker("Save", "*.tk").save();
			if (file == null){
				return;
			}
			TimeKeeper.save(file);
		}else if (id == ClickItem.EXIT){
			TimeKeeper.requestClose();
		}
	}

}
