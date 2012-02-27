package com.gleason.apa.fragment.team;

import com.gleason.apa.R;
import com.gleason.apa.fragment.team.TeamListFragment.OnTeamSelectedListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TeamFragment extends Fragment implements OnTeamSelectedListener {
	private TeamListFragment mTeamListFragment;
	private TeamEditFragment mTeamEditFragment;
	private Integer selectedTeamIndex;
	private Boolean isDualPane;
	private OnTeamSelectedListener listener;
	
	public static TeamFragment newInstance(){
		return new TeamFragment();
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.team_list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setUpFragments();
	}
	
	private void setUpFragments(){
		// find our fragments
		if(mTeamListFragment != null){
        	mTeamListFragment.attachListener(this);
        }
        mTeamEditFragment = (TeamEditFragment) ((FragmentActivity) 
        		getActivity()).getSupportFragmentManager().findFragmentById(
                R.id.team_edit_fragment);
        
        View teamView = getActivity().findViewById(R.id.team_edit_fragment);
        isDualPane = teamView != null && teamView.getVisibility() == View.VISIBLE;

        
	}
	
	public enum State{
		LIST,
		EDIT
	}

	
	public void onTeamSelected(Integer index) {
		selectedTeamIndex = index;
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
	
	public void attach(OnTeamSelectedListener b){
		listener = b;
	}
	
	public interface OnTeamSelectedListener{
		public void onSelect(Integer index);
	}
}
