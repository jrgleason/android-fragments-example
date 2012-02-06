package com.gleason.apa.model;

import com.gleason.apa.model.data.BarContentProvider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Bar {
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
	public static final class Bars implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ BarContentProvider.AUTHORITY + "/" +BarContentProvider.TABLE_NAME + "s");

		public static final String CONTENT_TYPE = "apashooter/bar";
		public static final String ID = "id";

		public static final String NAME = "name";

		public static final String ADDRESS = "address";
	}
}
