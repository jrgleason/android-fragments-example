package com.gleason.apa.fragment.bar;

import com.gleason.apa.R;
import com.gleason.apa.fragment.bar.BarListFragment.OnBarSelectedListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BarFragment extends Fragment implements OnBarSelectedListener{
	private BarListFragment mBarListFragment;
	private BarEditFragment mBarEditFragment;
	private Integer selectedBarIndex;
	private Boolean isDualPane;
	private OnBarSelectedListener listener;
	
	public static BarFragment newInstance(){
		return new BarFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.bar_list, container, false);
	}

	public enum State{
		LIST,
		EDIT
	}

	
	public void attach(OnBarSelectedListener b){
		listener = b;
	}
	
	public interface OnBarSelectedListener{
		public void onSelect(Integer index);
	}

	public void onBarSelected(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
