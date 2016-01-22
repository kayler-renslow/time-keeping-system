package com.kaylerrenslow.timeKeepingSystem.eventHandlers;

import com.kaylerrenslow.timeKeepingSystem.managers.MenuFileManager;
import com.kaylerrenslow.timeKeepingSystem.managers.MenuFileManager.ClickItem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MenuFileClickEvent implements EventHandler<ActionEvent>{
	private MenuFileManager manager;
	private ClickItem id;

	public MenuFileClickEvent(MenuFileManager manager, ClickItem new1) {
		this.manager = manager;
		this.id = new1;
	}
	@Override
	public void handle(ActionEvent event) {
		manager.click(id);
	}
}
