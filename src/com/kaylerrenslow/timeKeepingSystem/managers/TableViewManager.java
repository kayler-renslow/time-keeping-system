package com.kaylerrenslow.timeKeepingSystem.managers;

import java.text.DecimalFormat;

import com.kaylerrenslow.timeKeepingSystem.data.TimeRecording;
import com.kaylerrenslow.timeKeepingSystem.gui.ContextMenuTable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class TableViewManager{
	public TableView<TimeRecording> tableView;

	public TableViewManager(TableView<TimeRecording> tableView, TableColumn<TimeRecording, String> day, TableColumn<TimeRecording, String> startTime, TableColumn<TimeRecording, String> endTime, TableColumn<TimeRecording, String> hours, TableColumn<TimeRecording, String> money, TableColumn<TimeRecording, String> month, TableColumn<TimeRecording, String> year, TableColumn<TimeRecording, String> epoch) {
		this.tableView = tableView;
		tableView.setContextMenu(new ContextMenuTable(tableView));
		setHandlers(day, startTime, endTime, hours, money, month, year, epoch);
	}

	public void setHandlers(TableColumn<TimeRecording, String> day, TableColumn<TimeRecording, String> startTime, TableColumn<TimeRecording, String> endTime, TableColumn<TimeRecording, String> hours, TableColumn<TimeRecording, String> money, TableColumn<TimeRecording, String> month, TableColumn<TimeRecording, String> year, TableColumn<TimeRecording, String> epoch) {
		day.setCellValueFactory(new Callback<CellDataFeatures<TimeRecording, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<TimeRecording, String> value) {
				return new SimpleStringProperty(value.getValue().getDay() + "");
			}
		});

		startTime.setCellValueFactory(new PropertyValueFactory<TimeRecording, String>("startTime"));
		endTime.setCellValueFactory(new PropertyValueFactory<TimeRecording, String>("endTime"));

		hours.setCellValueFactory(new Callback<CellDataFeatures<TimeRecording, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<TimeRecording, String> value) {
				return new SimpleStringProperty(new DecimalFormat("##.##").format(value.getValue().getHours()) + "");
			}
		});

		money.setCellValueFactory(new Callback<CellDataFeatures<TimeRecording, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<TimeRecording, String> value) {
				return new SimpleStringProperty(new DecimalFormat("###.##").format(value.getValue().getMoney()) + "");
			}
		});
		month.setCellValueFactory(new Callback<CellDataFeatures<TimeRecording, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<TimeRecording, String> value) {
				return new SimpleStringProperty(value.getValue().getMonth() + "");
			}
		});
		year.setCellValueFactory(new Callback<CellDataFeatures<TimeRecording, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<TimeRecording, String> value) {
				return new SimpleStringProperty(value.getValue().getYear() + "");
			}
		});
		epoch.setCellValueFactory(new Callback<CellDataFeatures<TimeRecording, String>, ObservableValue<String>>(){
			@Override
			public ObservableValue<String> call(CellDataFeatures<TimeRecording, String> value) {
				return new SimpleStringProperty(value.getValue().getEpoch() + "");
			}
		});
	}

}
