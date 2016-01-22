package com.kaylerrenslow.timeKeepingSystem.gui.editMenus;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class NumberField extends TextField{
	private double min;
	private double max;
	private boolean hasLimit;
	private boolean allowDecimal;

	private boolean error;

	public NumberField(double min, double max, boolean allowDecimal) {
		this(allowDecimal);
		this.min = min;
		this.max = max;
		hasLimit = true;
	}

	public NumberField(boolean allowDecimal) {
		this.allowDecimal = allowDecimal;
		this.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				checkString();
			}
		});
		HBox.setMargin(this, new Insets(0, 4, 0, 0));
	}

	public void checkString() {
		String text = this.getText();
		if (text.length() == 0){
			setError(false);
			return;
		}
		for (int i = 0; i < text.length(); i++){
			if (!Character.isDigit(text.charAt(i))){
				if (allowDecimal && text.charAt(i) == '.'){
					continue;
				}
				setError(true);
				return;
			}
		}
		if (text.length() == 0){
			setError(true);
			return;
		}else{
			setError(false);
		}
		if (hasLimit){
			double val = Double.parseDouble(text);
			if (val <= max && val >= min){
				setError(false);
			}else{
				setError(true);
			}
		}
	}

	public boolean hasError() {
		return error;
	}

	public void setError(boolean val) {
		if (val){
			this.setStyle("-fx-background-color:red;-fx-text-fill:white;");
		}else{
			this.setStyle("");
		}
		error = val;
	}
}