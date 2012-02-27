package com.gleason.apa.model;

import com.gleason.apa.model.data.BarContentProvider;
import com.gleason.apa.model.util.Util;

import android.net.Uri;
import android.provider.BaseColumns;

public class Bar {
	public static final class Bars implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ Util.AUTHORITY + "/" +BarContentProvider.TABLE_NAME);

		public static final String CONTENT_TYPE = "apashooter/bar";
		public static final String ID = "_id";

		public static final String NAME = "name";

		public static final String ADDRESS = "address";
	}
	private int id;
	private String name;
	private String address;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
