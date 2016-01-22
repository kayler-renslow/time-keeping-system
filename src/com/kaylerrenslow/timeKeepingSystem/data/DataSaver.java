package com.kaylerrenslow.timeKeepingSystem.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class DataSaver{

	private PrintWriter pw;

	public DataSaver(File saveLocation) {
		if (!saveLocation.exists()){
			try{
				saveLocation.createNewFile();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		try{
			pw = new PrintWriter(saveLocation);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

	public void saveRecording(TimeRecording recording) {
		pw.println(recording.getSaveString() + " " + recording.getStartTime().getSaveString() + " " + (recording.getEndTime() == null ? "null" : recording.getEndTime().getSaveString()));
	}

	public void close() {
		pw.flush();
		pw.close();
	}

}
