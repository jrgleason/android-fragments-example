package com.gleason.apa.fragment;


import com.gleason.apa.R;
import com.gleason.apa.model.Bar;
import com.gleason.apa.model.Bar.Bars;
import com.gleason.apa.model.data.BarContentProvider;
import com.gleason.apa.model.util.Util;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BarFragment extends Fragment {
	public static final String UPDATE_FLAG = "update";
	public static final String BAR_ID_FLAG = "barid";
	private EditText name;
	private EditText address;

	public static BarFragment newInstance(){
		return new BarFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.bar, container, false);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Boolean isEdit = null;
		if(getActivity().getIntent() != null && getActivity().getIntent().getExtras() != null){
			isEdit = getActivity().getIntent().getExtras().getBoolean(UPDATE_FLAG);
		}
		name = (EditText) getActivity().findViewById(R.id.barName);
		address = (EditText) getActivity().findViewById(R.id.address);
		Button submit = (Button) getActivity().findViewById(R.id.submitBar);
		if (isEdit != null && isEdit) {
			Integer barId = getActivity().getIntent().getExtras().getInt(BAR_ID_FLAG);
			if (barId == null) {
				Log.d(Util.TAG, "Bar could not be found");
			} else {
				Bar currentBar = getBarFromId(barId);
				name.setText(currentBar.getName());
				address.setText(currentBar.getAddress());
				submit.setOnClickListener(updateListener);
			}
			
		} else {
			submit.setOnClickListener(insertListener);
		}
	}

	private Bar getBarFromId(int id) {
		Uri mContacts = Bars.CONTENT_URI;
		Cursor cur = getActivity().managedQuery(mContacts, BarContentProvider.PROJECTION_MAP, 
				null, null, null);
		return new Bar();
	}

	private void insertBar(String name, String address) {
		ContentValues values = new ContentValues();
		values.put(Bars.NAME, name);
		values.put(Bars.ADDRESS, address);
		Uri uri = getActivity().getContentResolver().insert(Bars.CONTENT_URI, values);
		Log.d(Util.TAG, uri.toString());
	}

	private OnClickListener updateListener = new OnClickListener() {
		public void onClick(View v) {
			// Insert Event
			String test = "";
		}
	};
	// Create an anonymous implementation of OnClickListener
	private OnClickListener insertListener = new OnClickListener() {
		public void onClick(View v) {
			// Insert Event
			insertBar(name.getEditableText().toString(),
					address.getEditableText().toString());
		}
	};
}
