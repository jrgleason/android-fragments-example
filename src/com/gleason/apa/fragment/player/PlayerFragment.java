package com.gleason.apa.fragment.player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gleason.apa.R;
import com.gleason.apa.fragment.player.PlayerEditFragment;
import com.gleason.apa.fragment.player.PlayerFragment;
import com.gleason.apa.fragment.player.PlayerListFragment;
import com.gleason.apa.fragment.player.PlayerListFragment.OnPlayerSelectedListener;

public class PlayerFragment extends Fragment implements OnPlayerSelectedListener{
	private PlayerListFragment mPlayerListFragment;
	private PlayerEditFragment mPlayerEditFragment;
	private Integer selectedPlayerIndex;
	private Boolean isDualPane;
	private OnPlayerSelectedListener listener;
	
	public static PlayerFragment newInstance(){
		return new PlayerFragment();
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.player_list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setUpFragments();
	}
	
	private void setUpFragments(){
		// find our fragments
		if(mPlayerListFragment != null){
        	mPlayerListFragment.attachListener(this);
        }
        mPlayerEditFragment = (PlayerEditFragment) ((FragmentActivity) getActivity()).getSupportFragmentManager().findFragmentById(
                R.id.player_edit_fragment);
        
        View playerView = getActivity().findViewById(R.id.player_edit_fragment);
        isDualPane = playerView != null && playerView.getVisibility() == View.VISIBLE;        
	}
	
	public enum State{
		LIST,
		EDIT
	}

	
	public void onPlayerSelected(Integer index) {
		selectedPlayerIndex = index;
        if (isDualPane) {
            // display it on the article fragment
            //mBarEditFragment.displayBar(index);
        }
        else {
        	if(listener != null){
        		listener.onSelect(index);
        	}
        }
		
	}
	
	public void attach(OnPlayerSelectedListener b){
		listener = b;
	}
	
	public interface OnPlayerSelectedListener{
		public void onSelect(Integer index);
	}
}
