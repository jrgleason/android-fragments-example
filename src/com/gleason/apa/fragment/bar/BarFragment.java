package com.gleason.apa.fragment.bar;

import com.gleason.apa.R;
import com.gleason.apa.fragment.bar.BarListFragment.OnBarSelectedListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
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
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.main_layout, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setUpFragments();
	}
	
	private void setUpFragments(){
		// find our fragments
//        mBarListFragment = (BarListFragment) ((FragmentActivity) getActivity()).getSupportFragmentManager().findFragmentById(
//                R.id.bar_container);
        if(mBarListFragment != null){
        	mBarListFragment.attachListener(this);
        }
        mBarEditFragment = (BarEditFragment) ((FragmentActivity) getActivity()).getSupportFragmentManager().findFragmentById(
                R.id.bar_edit_fragment);
        
        View barView = getActivity().findViewById(R.id.bar_edit_fragment);
        isDualPane = barView != null && barView.getVisibility() == View.VISIBLE;

        
	}
	
	public enum State{
		LIST,
		EDIT
	}

	
	public void onBarSelected(Integer index) {
		selectedBarIndex = index;
        if (isDualPane) {
            // display it on the article fragment
            //mBarEditFragment.displayBar(index);
        }
        else {
        	FragmentTransaction ft = getFragmentManager()
        			.beginTransaction();
        	ft.replace(R.id.bar_container, new BarEditFragment());
        	ft.commit();
        	if(listener != null){
        		listener.onSelect(index);
        	}
        }
		
	}
	
	public void attach(OnBarSelectedListener b){
		listener = b;
	}
	
	public interface OnBarSelectedListener{
		public void onSelect(Integer index);
	}
}
