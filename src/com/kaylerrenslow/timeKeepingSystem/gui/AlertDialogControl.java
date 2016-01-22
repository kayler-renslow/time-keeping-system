package com.kaylerrenslow.timeKeepingSystem.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AlertDialogControl implements Initializable{

	@FXML
	Button cancelButton;

	@FXML
	Button button2;

	@FXML
	Button button3;

	@FXML
	Label detailsLabel;

	@FXML
	Label messageLabel;
	
	@FXML
	ImageView iconImage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AlertDialog dialog = ((AlertDialog) resources.getObject(""));
		dialog.dialogLoaded(this, dialog);
	}
}