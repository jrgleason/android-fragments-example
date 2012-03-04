package com.gleason.apa.fragment.gestures;

import com.gleason.apa.R;

import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GestureFragment extends Fragment implements OnGesturePerformedListener{
	private GestureLibrary gestureLib;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.gesture, 
				container, false);
		GestureOverlayView gestureOverlayView = new GestureOverlayView(container.getContext());
		gestureOverlayView.addView(root);
		gestureOverlayView.addOnGesturePerformedListener(this);
//		gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
		return gestureOverlayView;
	}
	
	public void onGesturePerformed(GestureOverlayView arg0, Gesture arg1) {
		// TODO Auto-generated method stub
		
	}

}
