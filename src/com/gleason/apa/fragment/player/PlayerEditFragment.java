package com.gleason.apa.fragment.player;

import com.gleason.apa.R;
import com.gleason.apa.fragment.BaseEditFragment;
import com.gleason.apa.fragment.player.PlayerEditFragment;
import com.gleason.apa.model.Player;
import com.gleason.apa.model.Player.Players;
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

public class PlayerEditFragment extends BaseEditFragment {
	public static final String UPDATE_FLAG = "update";
	public static final String TEAM_ID_FLAG = "playerid";
	private EditText name;
	private EditText rank;
	private Integer id;
	
	public static PlayerEditFragment newInstance(){
		return new PlayerEditFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.player_edit, container, false);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	public void updateEdit(Player currentPlayer){
		name.setText(currentPlayer.getName());
		rank.setText(currentPlayer.getRank().toString());
		id = currentPlayer.getId();
	}
	
	public void updateEdit(Integer playerId){

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
		name = (EditText) activity.findViewById(R.id.player_name);
		rank = (EditText) activity.findViewById(R.id.player_ranking);
		Button submit = (Button) activity.findViewById(R.id.submitPlayer);
		if (isEdit != null && isEdit) {
			Integer playerId = activity.getIntent().getExtras().getInt(TEAM_ID_FLAG);
			if (playerId == null) {
				Log.d(Util.TAG, "Player could not be found");
			} else {
				updateEdit(playerId);
				submit.setOnClickListener(updateListener);
			}
		} else {
			if(submit != null){
				submit.setOnClickListener(insertListener);
			}
		}
	}

	private void insertPlayer(String name, String playerNumber) {
		ContentValues values = new ContentValues();
		values.put(Players.NAME, name);
		values.put(Players.RANKING, playerNumber);
		Uri uri = getActivity().getContentResolver().insert(Players.CONTENT_URI, values);
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
			insertPlayer(name.getEditableText().toString(),
					rank.getEditableText().toString());
			getActivity().finish();
		}
	};
}
