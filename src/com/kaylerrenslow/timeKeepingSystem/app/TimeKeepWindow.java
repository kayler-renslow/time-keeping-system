package com.kaylerrenslow.timeKeepingSystem.app;

import java.net.URL;
import java.util.ResourceBundle;

import com.kaylerrenslow.timeKeepingSystem.data.TimeRecording;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TimeKeepWindow implements Initializable{
	
	
	@FXML
	public MenuItem menuFileNew;

	@FXML
	public MenuItem menuFileOpen;

	@FXML
	public MenuItem menuFileSave;

	@FXML
	public MenuItem menuFileSaveAs;

	@FXML
	public MenuItem menuFileClose;

	/**/

	@FXML
	public MenuItem menuEditPay;

	/**/

	@FXML
	public TableView<TimeRecording> tableView;

	@FXML
	public TableColumn<TimeRecording, String> tableViewDay;

	@FXML
	public TableColumn<TimeRecording, String> tableViewStartTime;

	@FXML
	public TableColumn<TimeRecording, String> tableViewEndTime;

	@FXML
	public TableColumn<TimeRecording, String> tableViewHours;

	@FXML
	public TableColumn<TimeRecording, String> tableViewMoney;

	@FXML
	public TableColumn<TimeRecording, String> tableViewMonth;

	@FXML
	public TableColumn<TimeRecording, String> tableViewYear;

	@FXML
	public TableColumn<TimeRecording, String> tableViewEpoch;

	/**/

	@FXML
	public Button btnShiftStart;

	@FXML
	public Button btnShiftStop;

	/**/

	@FXML
	public TextField tfEditValue;
	
	@FXML
	public VBox vboxEdit;
	
	@FXML
	public Button btnSubmitChanges;
	
	@FXML
	public Button btnCancelChanges;

	/**/

	@FXML
	public TextField tfUploadHostName;

	@FXML
	public TextField tfUploadUsername;

	@FXML
	public TextField tfUploadPassword;

	@FXML
	public Button btnUpload;

	/**/

	@FXML
	public TextArea console;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		TimeKeeper.initialized(this);
		
	}
}
