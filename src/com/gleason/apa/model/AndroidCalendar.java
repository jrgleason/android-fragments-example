package com.gleason.apa.model;

import android.provider.CalendarContract;

public class AndroidCalendar {
	public static final String[] CALENDAR_PROJECTION = new String[] { 
		CalendarContract.Calendars._ID,
		CalendarContract.Calendars.ACCOUNT_NAME,
		CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
		CalendarContract.Calendars.NAME,
		CalendarContract.Calendars.CALENDAR_COLOR };
	
	private Integer id;
	private String accountName;
	private String displayName;
	private String name;
	private String color;
	
	public AndroidCalendar(){
		
	}
	public AndroidCalendar(Integer id, String accountName, String displayName, String name, String color){
		this.id = id;
		this.accountName = accountName;
		this.displayName = displayName;
		this.color = color;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getDisplayName();
	}
	
}
