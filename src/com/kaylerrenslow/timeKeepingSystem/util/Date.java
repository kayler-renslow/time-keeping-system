package com.kaylerrenslow.timeKeepingSystem.util;

import java.text.DateFormat;

public class Date{

	public int day;
	public int month;
	public int year;
	public final long epoch;
	public int hour;
	public int minute;

	/** Creates a new Date object. The epoch provided is non changing as it marks when the date was originally recorded.
	 * 
	 * @param day
	 *            day of the month
	 * @param month
	 *            month number (1-12)
	 * @param year
	 *            the year
	 * @param hour
	 *            hour in military time
	 * @param minute
	 *            minute of the day
	 * @param epoch
	 *            epoch of the date when initialized */
	public Date(int day, int month, int year, int hour, int minute, long epoch) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
		this.epoch = epoch;
	}

	public static String toString(Date date) {
		String ampm = date.hour > 12 ? "PM" : "AM";
		int hour = Math.abs(12 - date.hour);
		String minute = date.minute < 10 ? "0" + date.minute : date.minute + "";
		return date.month + "/" + date.day + "/" + date.year + " " + hour + ":" + minute + " " + ampm + " " + date.epoch;
	}

	public static Date getCurrentDate() {
		long epoch = System.currentTimeMillis();
		String dateString = DateFormat.getInstance().format(new java.util.Date(epoch));
		int month = Integer.parseInt(dateString.substring(0, dateString.indexOf('/')));
		int day = Integer.parseInt(dateString.substring(dateString.indexOf('/') + 1, dateString.lastIndexOf('/')));
		int year = Integer.parseInt(dateString.substring(dateString.lastIndexOf('/') + 1, dateString.indexOf(' ')));
		int hour = Integer.parseInt(dateString.substring(dateString.indexOf(' ') + 1, dateString.indexOf(':')));
		int minute = Integer.parseInt(dateString.substring(dateString.indexOf(':') + 1, dateString.lastIndexOf(' ')));
		if (dateString.toLowerCase().endsWith("pm")){
			hour += 12;
			if (hour == 24){
				hour = 12;
			}
		}
		Date date = new com.kaylerrenslow.timeKeepingSystem.util.Date(day, month, year, hour, minute, epoch);

		return date;
	}

	/** Gets a Date from the format as follows: 5/30/15 2:52 PM 0012679432
	 * 
	 * @param dateString
	 *            String to convert
	 * @return the Date represented from the String */
	public static Date getDateFromString(String dateString) {
		int month = Integer.parseInt(dateString.substring(0, dateString.indexOf('/')));
		int day = Integer.parseInt(dateString.substring(dateString.indexOf('/') + 1, dateString.lastIndexOf('/')));
		int year = Integer.parseInt(dateString.substring(dateString.lastIndexOf('/') + 1, dateString.indexOf(' ')));
		int hour = Integer.parseInt(dateString.substring(dateString.indexOf(' ') + 1, dateString.indexOf(':')));
		int minute = Integer.parseInt(dateString.substring(dateString.indexOf(':') + 1, dateString.lastIndexOf(' ')));
		long epoch = Long.parseLong(dateString.substring(dateString.lastIndexOf('M')) + 2, dateString.length());
		if (dateString.toLowerCase().endsWith("pm")){
			hour += 12;
		}
		if (hour == 24){
			hour = 12;
		}
		Date date = new com.kaylerrenslow.timeKeepingSystem.util.Date(day, month, year, hour, minute, epoch);

		return date;
	}

	@Override
	public String toString() {
		return Date.toString(this);
	}
}
