package com.gleason.apa.fragment.team;

import java.util.HashSet;
import java.util.Set;

import com.gleason.apa.R;
import com.gleason.apa.fragment.BaseListFragment;
import com.gleason.apa.model.Team.Teams;
import com.gleason.apa.model.data.TeamContentProvider;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TeamListFragment extends BaseListFragment implements LoaderManager.LoaderCallbacks<Cursor>{
	Set<OnTeamSelectedListener> listeners = 
			new HashSet<TeamListFragment.OnTeamSelectedListener>();
	
	private SimpleCursorAdapter adapter = null;
	
	int[] to = {
			R.id.bar_name_list,
			R.id.bar_address_list
	};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter = new SimpleCursorAdapter(
				getActivity(),
				R.layout.bar_list,
				null,
				TeamContentProvider.PROJECTION_MAP_NO_ID,
				to,
				0);
		setListAdapter(adapter);
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		for (OnTeamSelectedListener listener : listeners) {
			listener.onTeamSelected(position);
		}
	}

	public void attachListener(OnTeamSelectedListener listener) {
		listeners.add(listener);
	}
	
	public interface OnTeamSelectedListener {
        public void onTeamSelected(Integer id);
    }
	
	public Loader<Cursor> onCreateLoader(int paramInt, Bundle paramBundle) {
		// TODO Auto-generated method stub
		return new CursorLoader(getActivity(), 
				Teams.CONTENT_URI, 
				TeamContentProvider.PROJECTION_MAP, 
				null, 
				null, 
				null);
	}
 
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);     
    }
    
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
