package com.kaylerrenslow.timeKeepingSystem.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.kaylerrenslow.timeKeepingSystem.util.Date;

public class DataReader{
	private Scanner scan;

	public DataReader(File saveLocation) {
		try{
			scan = new Scanner(saveLocation);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

	public boolean hasNextLine() {
		return scan.hasNext();
	}

	public TimeRecording readNextLine() {
		if(!scan.hasNextInt()){
			throw new IllegalStateException("Can not read any more data");
		}
		int day = scan.nextInt();
		int month = scan.nextInt();
		int year = scan.nextInt();
		long epoch = scan.nextLong();
		boolean updated = scan.nextBoolean();
		TimeStamp startTime = getTimeStamp();
		TimeStamp endTime = getTimeStamp();
		return new TimeRecording(new Date(day, month, year, -1, -1, epoch), startTime, endTime, updated);
	}

	private TimeStamp getTimeStamp() {
		String first = scan.next();
		if (first.equals("null")){
			return null;
		}
		int hour = Integer.parseInt(first);
		int minute = scan.nextInt();
		return new TimeStamp(hour, minute);
	}
}
