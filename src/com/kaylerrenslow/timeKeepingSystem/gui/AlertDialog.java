package com.kaylerrenslow.timeKeepingSystem.gui;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class AlertDialog{

	private Stage stage = new Stage(StageStyle.DECORATED);
	public AlertType alertType;
	private AlertDialog dialogInstance = this;
	private AlertDialogActionEvent dialogEvent;

	private String title, message, details;

	public enum AlertType {
		CONFIRM, ALERT;
	}

	public AlertDialog(AlertType type, String title, String message, String details, AlertDialogActionEvent event) {
		this.alertType = type;
		this.dialogEvent = event;
		this.title = title;
		this.message = message;
		this.details = details;
		createWindow(title, event);
	}

	public enum Response {
		CANCEL, OK, YES, NO;
	}

	private void createWindow(String title, AlertDialogActionEvent event) {
		Parent root = null;
		try{
			root = FXMLLoader.load(getClass().getResource("/com/kaylerrenslow/timeKeepingSystem/gui/alertDialog.fxml"), new ResourceBundle(){

				@Override
				protected Object handleGetObject(String key) {
					return dialogInstance;
				}

				@Override
				public Enumeration<String> getKeys() {
					return null;
				}
			});
		}catch (IOException e){
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
			@Override
			public void handle(WindowEvent wevent) {
				event.responded(Response.CANCEL);
			}
		});
	}

	public static void showConfirmDialog(String title, String message, String details, AlertDialogActionEvent event) {
		new AlertDialog(AlertType.CONFIRM, title, message, details, event);
	}

	public static void showAlertDialog(String title, String message, String details, AlertDialogActionEvent event) {
		new AlertDialog(AlertType.ALERT, title, message, details, event);
	}

	void dialogLoaded(AlertDialogControl control, AlertDialog diag) {
		control.iconImage.setImage(new Image(getClass().getResourceAsStream("/com/kaylerrenslow/timeKeepingSystem/gui/alertIcon.png")));

		control.cancelButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				diag.dialogEvent.responded(Response.CANCEL);
				diag.close();
			}
		});
		if (diag.alertType == AlertType.CONFIRM){
			control.button2.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					diag.dialogEvent.responded(Response.NO);
					diag.close();
				}
			});
			control.button3.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					diag.dialogEvent.responded(Response.YES);
					diag.close();
				}
			});

			control.button2.setText("No");
			control.button3.setText("Yes");
		}else if (diag.alertType == AlertType.ALERT){
			control.button3.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					diag.dialogEvent.responded(Response.OK);
					diag.close();
				}
			});

			control.button2.setVisible(false);
			control.button3.setText("Ok");
		}
		control.detailsLabel.setText(diag.details);
		control.messageLabel.setText(diag.message);
		diag.stage.setTitle(diag.title);
	}

	public void close() {
		this.stage.close();
	}
}
