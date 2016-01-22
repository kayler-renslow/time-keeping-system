package com.kaylerrenslow.timeKeepingSystem.app;

import java.io.File;

import com.kaylerrenslow.timeKeepingSystem.data.DataReader;
import com.kaylerrenslow.timeKeepingSystem.data.DataSaver;
import com.kaylerrenslow.timeKeepingSystem.data.TimeRecording;
import com.kaylerrenslow.timeKeepingSystem.gui.AlertDialog;
import com.kaylerrenslow.timeKeepingSystem.gui.AlertDialogActionEvent;
import com.kaylerrenslow.timeKeepingSystem.gui.AlertDialog.Response;
import com.kaylerrenslow.timeKeepingSystem.util.FilePicker;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TimeKeeper extends Application{

	public static AppData appData = new AppData();
	public static Stage stage;

	private static DataReader reader;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		TimeKeeper.stage = stage;
		Parent root = FXMLLoader.load(getClass().getResource("/com/kaylerrenslow/timeKeepingSystem/app/TimeKeepingWindow.fxml"));
		Scene scene = new Scene(root, 1000, 600);
		stage.setTitle("Kayler's Time Keeping System");
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
			@Override
			public void handle(WindowEvent event) {
				boolean close = requestClose();
				if (close){
					TimeKeeper.exit();
				}else{
					event.consume();
				}
			}
		});
	}

	public static void exit() {
		System.exit(0);
	}

	/** Ran when the FXML has been fully injected and ready to add handlers, attributes, etc. */
	public static void initialized(TimeKeepWindow window) {
		appData.prepare(window);
	}

	public static void save(File saveLocation) {
		if (!saveLocation.getName().endsWith(".tk")){
			saveLocation = new File(saveLocation.getAbsolutePath() + ".tk");
		}

		DataSaver saver = new DataSaver(saveLocation);
		for (TimeRecording rec : appData.tableManager.tableView.getItems()){
			saver.saveRecording(rec);
		}
		saver.close();
		appData.saveLocation = saveLocation;
		appData.needsSaving = false;
	}

	public static void open(File saveLocation) {
		if (appData.needsSaving){
			requestSave(new AlertDialogActionEvent(){

				@Override
				public void responded(Response type) {
					if (type == Response.NO || type == Response.CANCEL){
						return;
					}
					if (appData.saveLocation == null){
						File file = new FilePicker("Save", "*.tk").save();
						if (file == null){
							return;
						}
						TimeKeeper.save(file);
					}else{
						TimeKeeper.save(appData.saveLocation);
					}
					openFromFile(saveLocation);
				}
			}, "Save changes before continuing?");
		}else{
			openFromFile(saveLocation);
		}
	}
	
	private static void openFromFile(File saveLocation){
		reader = new DataReader(saveLocation);
		addAllData();
		if (appData.tableManager.tableView.getItems().get(0).getEndTime() == null){
			appData.recordingManager.setShiftStarted(true);
		}
		appData.saveLocation = saveLocation;
		appData.needsSaving = false;
	}

	/** Adds more data to the table. The amount of entries added is specified by number.
	 * 
	 * @param number
	 *            how many entries to read from data
	 * @return true if there was the parameter number's value entries added, false if it could not fulfill the quota. */
	@Deprecated
	public static boolean addMoreData(int number) {
		if (reader == null){
			throw new IllegalStateException("Can not add more data with out first opening a save file");
		}
		TimeRecording data;
		for (int i = 0; i < number; i++){
			if (reader.hasNextLine()){
				data = reader.readNextLine();
				appData.tableManager.tableView.getItems().add(data);
			}else{
				return false;
			}
		}
		return true;
	}

	/** Adds all data to the table.*/
	public static void addAllData() {
		if (reader == null){
			throw new IllegalStateException("Can not add more data with out first opening a save file");
		}
		TimeRecording data;
		while (reader.hasNextLine()){
			data = reader.readNextLine();
			appData.tableManager.tableView.getItems().add(data);
		}

	}

	public static void newSave() {
		if (appData.needsSaving){
			requestSave(new AlertDialogActionEvent(){

				@Override
				public void responded(Response type) {
					if (type == Response.NO || type == Response.CANCEL){
						return;
					}
					if (appData.saveLocation == null){
						File file = new FilePicker("Save", "*.tk").save();
						if (file == null){
							return;
						}
						TimeKeeper.save(file);
					}else{
						TimeKeeper.save(appData.saveLocation);
					}
					createNewSave();
					
				}
			}, "Save changes before continuing?");
		}else{
			createNewSave();
		}
	}
	
	private static void createNewSave(){
		appData.tableManager.tableView.getItems().clear();
		appData.tableManager.tableView.getColumns().get(0).setVisible(false);
		appData.tableManager.tableView.getColumns().get(0).setVisible(true);
		appData.editManager.cancelEdit();
		appData.recordingManager.setShiftStarted(false);
		appData.saveLocation = null;
		appData.needsSaving = false;
	}

	private static void requestSave(AlertDialogActionEvent event, String message) {
		AlertDialog.showConfirmDialog("There is Unsaved Data.", message, "All unsaved data will be lost...forever.", event);
	}

	public static boolean requestClose() {
		if (appData.needsSaving){
			requestSave(new AlertDialogActionEvent(){

				@Override
				public void responded(Response type) {
					if (type == Response.NO){
						TimeKeeper.exit();
						return;
					}
					if (type == Response.CANCEL){
						return;
					}
					if (appData.saveLocation == null){
						File file = new FilePicker("Save", "*.tk").save();
						if (file == null){
							return;
						}
						TimeKeeper.save(file);
					}else{
						TimeKeeper.save(appData.saveLocation);
					}
					TimeKeeper.exit();
				}
			}, "Save changes before exiting?");
			return false;
		}
		return true;
	}

}
