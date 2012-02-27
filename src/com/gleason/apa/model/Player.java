package com.gleason.apa.model;

import android.net.Uri;
import android.provider.BaseColumns;

import com.gleason.apa.model.data.PlayerContentProvider;
import com.gleason.apa.model.data.TeamContentProvider;
import com.gleason.apa.model.enums.Ranking;
import com.gleason.apa.model.util.Util;

public class Player {
	public static final class Players implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ Util.AUTHORITY + "/" + PlayerContentProvider.TABLE_NAME);
		public static final String CONTENT_TYPE = "apashooter/player";
		public static final String ID = "_id";
		public static final String NAME = "name";
		public static final String RANKING = "ranking";
	}
	
	private int id;
	private String name;
	private Ranking rank;
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
 
	public Ranking getRank() {
		return rank;
	}
	public void setRank(Ranking rank) {
		this.rank = rank;
	}
}
