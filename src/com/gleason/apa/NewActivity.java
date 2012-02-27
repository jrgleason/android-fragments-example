package com.gleason.apa;

import com.gleason.apa.fragment.bar.BarEditFragment;

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
		
		//Get the container and add fragment
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.edit_container, ((Fragment)new BarEditFragment()));
		if(ft != null){
			ft.commit();
		}
	}
}
