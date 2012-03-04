package com.gleason.apa;

import com.gleason.apa.enumerations.Screen;
import com.gleason.apa.fragment.bar.BarEditFragment;
import com.gleason.apa.fragment.player.PlayerEditFragment;
import com.gleason.apa.fragment.team.TeamEditFragment;
import com.gleason.apa.util.Util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class NewActivity extends FragmentActivity {
Fragment editContainer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_activity);
		if(savedInstanceState != null){
			Screen selectedScreen = (Screen)savedInstanceState.get(Util.TYPE_KEY);
		}
		//Get the container and add fragment
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//		switch(selectedScreen){
//			case BAR:
				ft.add(R.id.edit_container, new BarEditFragment());
//				break;
//			case PLAYER:
//				ft.add(R.id.edit_container, new PlayerEditFragment());
//				break;
//			case TEAM:
//				ft.add(R.id.edit_container, new TeamEditFragment());
//				break;
//		}
		if(ft != null){
			ft.commit();
		}
	}
}
