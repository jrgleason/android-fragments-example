package com.gleason.apa.model.data;

import java.util.HashMap;

import com.gleason.apa.model.Player.Players;
import com.gleason.apa.model.util.Util;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class PlayerContentProvider extends ContentProvider {

	public static final String TABLE_NAME = "player";
	private static final int PLAYERS = 1;
	private static final UriMatcher sUriMatcher;
	private DatabaseHelper dbHelper;
	private static HashMap<String, String> playersProjectionMap;
	
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(Util.AUTHORITY, TABLE_NAME, PLAYERS);
		
		playersProjectionMap = new HashMap<String, String>();
		playersProjectionMap.put(Players.ID, Players.ID);
		playersProjectionMap.put(Players.NAME, Players.NAME);
		playersProjectionMap.put(Players.RANKING, Players.RANKING);
	}
	
	public static final String[] PROJECTION_MAP= {
		Players.ID,
		Players.NAME,
		Players.RANKING
	};
	
	public static final String[] PROJECTION_MAP_NO_ID= {
		Players.NAME,
		Players.RANKING
	};
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + Players.ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT," + Players.NAME
					+ " VARCHAR(255)," + Players.RANKING + " VARCHAR(255)" + ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(Util.TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}
	
	@Override
	public boolean onCreate() {
		dbHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		switch (sUriMatcher.match(uri)) {
		case PLAYERS:
			qb.setTables(TABLE_NAME);
			qb.setProjectionMap(playersProjectionMap);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null,
				null, sortOrder);

		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case PLAYERS:
			return Players.CONTENT_TYPE;
		default:
			return null;
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		} else {
			values = new ContentValues();
		}

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId = db.insert(TABLE_NAME, Players.RANKING, values);
		if (rowId > 0) {
			Uri noteUri = ContentUris.withAppendedId(Players.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(noteUri, null);
			return noteUri;
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) {
		case PLAYERS:
			count = db.delete(TABLE_NAME, where, whereArgs);
			break;
		default:
			return -1;
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) {
		case PLAYERS:
			count = db.update(TABLE_NAME, values, where, whereArgs);
			break;

		default:
			return -1;
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
