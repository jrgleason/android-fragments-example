package com.gleason.apa.fragment.team;

import com.gleason.apa.R;
import com.gleason.apa.fragment.BaseEditFragment;
import com.gleason.apa.model.Team;
import com.gleason.apa.model.Team.Teams;
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

public class TeamEditFragment extends BaseEditFragment {
	public static final String UPDATE_FLAG = "update";
	public static final String TEAM_ID_FLAG = "teamid";
	private EditText name;
	private EditText teamNumber;
	private Integer id;
	
	public static TeamEditFragment newInstance(){
		return new TeamEditFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.team_edit, container, false);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	public void updateEdit(Team currentTeam){
		name.setText(currentTeam.getName());
		teamNumber.setText(currentTeam.getTeamNumber());
		id = currentTeam.getId();
	}
	
	public void updateEdit(Integer teamId){
//		Team currentTeam = getTeamFromId(teamId);
//		if(currentTeam != null){
//			updateEdit(currentTeam);
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
		name = (EditText) activity.findViewById(R.id.team_name);
		teamNumber = (EditText) activity.findViewById(R.id.team_number);
		Button submit = (Button) activity.findViewById(R.id.submitTeam);
		if (isEdit != null && isEdit) {
			Integer teamId = activity.getIntent().getExtras().getInt(TEAM_ID_FLAG);
			if (teamId == null) {
				Log.d(Util.TAG, "Team could not be found");
			} else {
				updateEdit(teamId);
				submit.setOnClickListener(updateListener);
			}
		} else {
			if(submit != null){
				submit.setOnClickListener(insertListener);
			}
		}
	}

	private void insertTeam(String name, String teamNumber) {
		ContentValues values = new ContentValues();
		values.put(Teams.NAME, name);
		values.put(Teams.TEAM_NUMBER, teamNumber);
		Uri uri = getActivity().getContentResolver().insert(Teams.CONTENT_URI, values);
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
			insertTeam(name.getEditableText().toString(),
					teamNumber.getEditableText().toString());
			getActivity().finish();
		}
	};
}
