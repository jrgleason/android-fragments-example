package com.gleason.apa.enumerations;


public enum Screen {
	TEAM("Team"),
	PLAYER("Player"),
	BAR("Bar"),	
//	SCHEDULE("Schedule"),
	GESTURE("Gesture");
	
	private Screen(String title){
		this.title = title;
	}
	
	private final String title;
	
	public String getTitle(){
		return title;
	}
	
	public static Screen getScreenById(Integer id){
		for (Screen s : Screen.values()) {
			if(s.ordinal()==id.intValue()){
				return s;
			}
		}
		return null;
	}
}
