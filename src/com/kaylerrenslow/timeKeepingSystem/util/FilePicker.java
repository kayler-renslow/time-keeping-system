package com.kaylerrenslow.timeKeepingSystem.util;

import java.io.File;

import com.kaylerrenslow.timeKeepingSystem.app.TimeKeeper;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FilePicker{
	private FileChooser chooser = new FileChooser();

	public FilePicker(String title, String... extensionFilter) {
		chooser.getExtensionFilters().add(new ExtensionFilter("Allowed Extensions", extensionFilter));
		chooser.setTitle(title);
	}

	public File open() {
		return chooser.showOpenDialog(TimeKeeper.stage);
	}

	public File save() {
		return chooser.showSaveDialog(TimeKeeper.stage);
	}
}
