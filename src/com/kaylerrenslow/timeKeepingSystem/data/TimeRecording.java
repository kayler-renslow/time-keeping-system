package com.kaylerrenslow.timeKeepingSystem.data;

import com.kaylerrenslow.timeKeepingSystem.app.TimeKeeper;
import com.kaylerrenslow.timeKeepingSystem.util.Date;

public class TimeRecording{

	private Date date;
	private double money;
	private double hours;
	private TimeStamp startTime;
	private TimeStamp endTime;
	private boolean syncedWithServer = false;

	public TimeRecording(Date date, TimeStamp startTime, TimeStamp endTime) {
		this(date, startTime, endTime, false);
	}

	public TimeRecording(Date date, TimeStamp startTime, TimeStamp endTime, boolean sycnedWithServer) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		calcHours();
		calcMoney();
		this.syncedWithServer = sycnedWithServer;
	}

	/** Get the total number of hours from the start time to the end time of this time recording.
	 * 
	 * @return hours between start time and end */
	public double getHours() {
		return hours;
	}

	private void calcHours() {
		if (endTime == null){
			return;
		}
		TimeStamp difference = startTime.getDifference(endTime);
		this.hours = difference.getHour();
		this.hours += difference.getMinute() / 60.0;
	}

	private void calcMoney() {
		this.money = this.hours * TimeKeeper.appData.hourlyRate;
	}

	public double getMoney() {
		return this.money;
	}

	public boolean syncedWithServer() {
		return this.syncedWithServer;
	}

	public void setSyncedWithServer(boolean b) {
		this.syncedWithServer = b;
	}

	public int getDay() {
		return date.day;
	}

	public void setDay(int day) {
		this.date.day = day;
		this.syncedWithServer = false;
	}

	public long getEpoch() {
		return date.epoch;
	}

	public void setStartTime(TimeStamp startTime) {
		this.startTime = startTime;
		calcHours();
		calcMoney();
		syncedWithServer = false;
	}

	public void setEndTime(TimeStamp endTime) {
		if (endTime == null){
			syncedWithServer = false;
		}
		this.endTime = endTime;
		calcHours();
		calcMoney();
	}

	public TimeStamp getStartTime() {
		return startTime;
	}

	public TimeStamp getEndTime() {
		return endTime;
	}

	public int getMonth() {
		return date.month;
	}

	public void setMonth(int month) {
		this.date.month = month;
		this.syncedWithServer = false;
	}

	public int getYear() {
		return date.year;
	}

	public void setYear(int year) {
		this.date.year = year;
		this.syncedWithServer = false;
	}

	@Override
	public String toString() {
		return "TimeRecording [day=" + date.day + ", startTime=" + startTime + ", endTime=" + endTime + ", month=" + date.month + ", year=" + date.year + ", money=" + money + ", hours=" + hours + ", epoch=" + date.epoch + "]";
	}

	public String getFormattedString() {
		return date.month + "/" + date.day + "/" + date.year;
	}

	public String getSaveString() {
		return date.day + " " + date.month + " " + date.year + " " + date.epoch + " " + syncedWithServer;
	}

}
