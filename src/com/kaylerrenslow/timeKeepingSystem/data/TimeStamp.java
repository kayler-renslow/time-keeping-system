package com.kaylerrenslow.timeKeepingSystem.data;

public class TimeStamp{
	/** The hour of this time in military time. */
	private final int hour;
	private final int minute;

	/** Creates a new Timestamp with the given hour and minute.
	 * 
	 * @param hour
	 *            hour of the TimeStamp in <b>military time</b>
	 * @param minute
	 *            minute of the TimeStamp */
	public TimeStamp(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public String toString() {
		String am_pm = hour >= 12 ? "PM" : "AM";
		String minute = this.minute < 10 ? "0" + this.minute : this.minute + "";
		if (hour > 12){
			return Math.abs(12 - hour) + ":" + minute + " " + am_pm;
		}
		return hour + ":" + minute + " " + am_pm;
	}

	/** Gets the difference of hours and minutes and puts it into a TimeStamp object
	 * 
	 * @param other
	 *            TimeStamp object to get the difference in hours and minutes of
	 * @return TimeStamp object with difference in hours and minutes */
	public TimeStamp getDifference(TimeStamp other) {
		int diffhour = Math.abs(other.hour - this.hour);
		int diffminute = Math.abs(other.minute - this.minute);

		if (this.hour > other.hour){
			diffhour = Math.abs(other.hour + 24 - this.hour);
		}

		int otherMinute = other.minute;

		if (this.minute > other.minute){
			diffhour -= 1;
			otherMinute += 60;
			diffminute = Math.abs(this.minute - otherMinute);
		}
		TimeStamp difference = new TimeStamp(diffhour, diffminute);

		return difference;

	}

	public int getHour() {
		return this.hour;
	}

	public int getMinute() {
		return this.minute;
	}

	public String getSaveString() {
		return this.hour + " " + this.minute;
	}

}
