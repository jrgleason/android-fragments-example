package com.gleason.apa.fragment.bar;


import com.gleason.apa.R;
import com.gleason.apa.model.Bar;
import com.gleason.apa.model.Bar.Bars;
import com.gleason.apa.model.util.Util;

import android.app.Activity;
import android.content.ContentValues;
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

public class BarEditFragment extends Fragment {
	public static final String UPDATE_FLAG = "update";
	public static final String BAR_ID_FLAG = "barid";
	private EditText name;
	private EditText address;
	private Integer id;

	public static BarEditFragment newInstance(){
		return new BarEditFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.bar_edit, container, false);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	public void updateEdit(Bar currentBar){
		name.setText(currentBar.getName());
		address.setText(currentBar.getAddress());
		id = currentBar.getId();
	}
	
	public void updateEdit(Integer barId){
//		Bar currentBar = getBarFromId(barId);
//		if(currentBar != null){
//			updateEdit(currentBar);
//		}
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Boolean isEdit = null;
		Activity activity = getActivity();
		if(activity.getIntent() != null && activity.getIntent().getExtras() != null){
			isEdit = activity.getIntent().getExtras().getBoolean(UPDATE_FLAG);
		}
//		Object test1 = activity.getFragmentManager().findFragmentById(R.id.bar_edit_fragment);
//		Object test = activity.getFragmentManager().findFragmentById(R.id.bar_edit_fragment).getView().findViewById(R.id.bar_name);
		name = (EditText) activity.findViewById(R.id.bar_name);
		address = (EditText) activity.findViewById(R.id.bar_address);
		Button submit = (Button) activity.findViewById(R.id.submitBar);
		if (isEdit != null && isEdit) {
			Integer barId = activity.getIntent().getExtras().getInt(BAR_ID_FLAG);
			if (barId == null) {
				Log.d(Util.TAG, "Bar could not be found");
			} else {
				updateEdit(barId);
				submit.setOnClickListener(updateListener);
			}
			
		} else {
			if(submit != null){
				submit.setOnClickListener(insertListener);
			}
		}
	}

//	private Bar getBarFromId(int id) {
//		Uri mContacts = Bars.CONTENT_URI;
//		Cursor cur = getActivity().managedQuery(mContacts, BarContentProvider.PROJECTION_MAP, 
//				null, null, null);
//		return new Bar();
//	}

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
			getActivity().finish();
		}
	};
	
	// Create an anonymous implementation of OnClickListener
	private OnClickListener insertListener = new OnClickListener() {
		public void onClick(View v) {
			// Insert Event
			insertBar(name.getEditableText().toString(),
					address.getEditableText().toString());
			getActivity().finish();
		}
	};
}
