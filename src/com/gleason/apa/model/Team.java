package com.gleason.apa.model;

import android.net.Uri;
import android.provider.BaseColumns;

import com.gleason.apa.model.data.TeamContentProvider;
import com.gleason.apa.model.util.Util;

public class Team {
	public static final class Teams implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ Util.AUTHORITY + "/" +TeamContentProvider.TABLE_NAME);
		public static final String CONTENT_TYPE = "apashooter/team";
		public static final String ID = "_id";
		public static final String NAME = "name";
		public static final String TEAM_NUMBER = "teamNumber";
	}
	private int id;
	private String name;
	private int teamNumber;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
}
