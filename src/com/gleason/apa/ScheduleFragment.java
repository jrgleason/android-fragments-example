package com.gleason.apa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.gleason.apa.fragment.DatePickerDialogFragment;
import com.gleason.apa.model.AndroidCalendar;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ScheduleFragment extends Fragment {

	/**
	 * For DatePicker
	 */

	private TextView mStartDateDisplay;
	private Button mPickStartDate;
	private Calendar startDate;
	static final int START_DATE_DIALOG_ID = 0;

	private Button submit;
	
	public static ScheduleFragment newInstance(){
		return new ScheduleFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.calendar_event, container, false);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		submit = (Button) getActivity().findViewById(R.id.addmatch);
		submit.setOnClickListener(submitListener);
		populateSpinner();
		populateStartDate();
	}

	private static void updateDisplay(Calendar date, TextView tv) {
		tv.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(date.get(Calendar.MONTH) + 1).append("-")
				.append(date.get(Calendar.DAY_OF_YEAR)).append("-")
				.append(date.get(Calendar.YEAR)).append(" "));
	}

	private void populateSpinner() {
		Spinner spinner = (Spinner) getActivity().findViewById(
				R.id.calanderSelector);
		String[] l_projection = new String[] { "_id", "displayName" };
		Uri l_calendars;
		Cursor cursor = null;
		if (Build.VERSION.SDK_INT < 8) {
			l_calendars = Uri.parse("content://calendar/calendars");
			cursor = getActivity().managedQuery(l_calendars, l_projection,
					null, null, null);
		} else if (Build.VERSION.SDK_INT < 15) {
			l_calendars = Uri.parse("content://com.android.calendar/calendars");
			cursor = getActivity().managedQuery(l_calendars, l_projection,
					null, null, null);
		} else {
			// ICS
			Uri uri = CalendarContract.Calendars.CONTENT_URI;
			l_projection = new String[] { CalendarContract.Calendars._ID,
					CalendarContract.Calendars.ACCOUNT_NAME,
					CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
					CalendarContract.Calendars.NAME,
					CalendarContract.Calendars.CALENDAR_COLOR };

			cursor = getActivity().managedQuery(uri, l_projection, null, null,
					null);
		}

		if (cursor != null) {
			cursor.moveToFirst();
			String[] calNames = new String[cursor.getCount()];
			List<AndroidCalendar> array = new ArrayList<AndroidCalendar>();
			for (int i = 0; i < calNames.length; i++) {
				array.add(new AndroidCalendar(cursor.getInt(0), cursor
						.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4)));
				cursor.moveToNext();
			}
			ArrayAdapter<AndroidCalendar> spinnerArrayAdapter = new ArrayAdapter<AndroidCalendar>(
					getActivity(), android.R.layout.simple_spinner_item, array);
			spinnerArrayAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(spinnerArrayAdapter);
			cursor.close();
		}
	}

	private void populateStartDate() {
		// capture our View elements
		mStartDateDisplay = (TextView) getActivity().findViewById(
				R.id.startDateDisplay);
		mPickStartDate = (Button) getActivity()
				.findViewById(R.id.pickStartDate);

		// add a click listener to the button
		mPickStartDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				DialogFragment newFragment = new DatePickerDialogFragment(
						mStartDateSetListener);
				newFragment.show(ft, "dialog");
			}
		});

		// get the current date
		startDate = Calendar.getInstance();

		// display the current date (this method is below)
		updateDisplay(startDate, mStartDateDisplay);
	}

	// Create an anonymous implementation of OnClickListener
	private OnClickListener submitListener = new OnClickListener() {
		public void onClick(View v) {
			// Insert Event
			ContentResolver cr = getActivity().getContentResolver();
			ContentValues values = new ContentValues();
			values.put(CalendarContract.Events.DTSTART,
					startDate.getTimeInMillis());
			Calendar endDate = new GregorianCalendar(
					startDate.get(Calendar.YEAR),
					startDate.get(Calendar.MONTH),
					startDate.get(Calendar.DAY_OF_YEAR),
					startDate.get(Calendar.HOUR),
					startDate.get(Calendar.MINUTE));
			endDate.add(Calendar.HOUR, 1);
			values.put(CalendarContract.Events.DTEND, endDate.getTimeInMillis());
			EditText title = (EditText) getActivity().findViewById(R.id.title);
			EditText desc = (EditText) getActivity().findViewById(
					R.id.description);
			Spinner spinner = (Spinner) getActivity().findViewById(
					R.id.calanderSelector);
			AndroidCalendar selectedValue = (AndroidCalendar) spinner
					.getSelectedItem();
			values.put(CalendarContract.Events.TITLE, title.getEditableText()
					.toString());
			values.put(CalendarContract.Events.DESCRIPTION, desc
					.getEditableText().toString());
			values.put(CalendarContract.Events.CALENDAR_ID,
					selectedValue.getId());
			values.put(CalendarContract.Events.EVENT_TIMEZONE, "Canada/Eastern");
			Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
			String eventID = uri.getLastPathSegment();
		}
	};
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			startDate.set(year, monthOfYear, dayOfMonth);
			updateDisplay(startDate, mStartDateDisplay);
		}
	};
}
