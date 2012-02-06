package com.gleason.apa.fragment;

import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DatePickerDialogFragment extends DialogFragment {
	private OnDateSetListener listener;
	public DatePickerDialogFragment(OnDateSetListener callback){
		listener = callback;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		String value = savedInstanceState.getString("date");
		Calendar date = null;
		if(value != null){
			date = Calendar.getInstance();
			Date holder = new Date(value);
			date.setTime(holder);
		}
		else{
			date = Calendar.getInstance();
		}
		if(date!= null){
			return getPickerDialog(date, getActivity().getApplicationContext(), listener);
		}
		else{
			return null;
		}
		
	}
	private static DatePickerDialog getPickerDialog(Calendar date, Context context, OnDateSetListener dsl){
		return new DatePickerDialog(context, dsl, 
				date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_YEAR));
	}
}
