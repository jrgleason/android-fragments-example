package com.gleason.apa.fragment.player;

import java.util.HashSet;
import java.util.Set;

import com.gleason.apa.R;
import com.gleason.apa.fragment.BaseListFragment;
import com.gleason.apa.fragment.team.TeamListFragment;
import com.gleason.apa.fragment.team.TeamListFragment.OnTeamSelectedListener;
import com.gleason.apa.model.Player.Players;
import com.gleason.apa.model.Team.Teams;
import com.gleason.apa.model.data.PlayerContentProvider;
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

public class PlayerListFragment extends BaseListFragment implements LoaderManager.LoaderCallbacks<Cursor>{
	public interface OnPlayerSelectedListener {
        public void onPlayerSelected(Integer id);
    }
	Set<OnPlayerSelectedListener> listeners = 
			new HashSet<PlayerListFragment.OnPlayerSelectedListener>();
	
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
				PlayerContentProvider.PROJECTION_MAP_NO_ID,
				to,
				0);
		setListAdapter(adapter);
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		for (OnPlayerSelectedListener listener : listeners) {
			listener.onPlayerSelected(position);
		}
	}

	public void attachListener(OnPlayerSelectedListener listener) {
		listeners.add(listener);
	}
	
	public Loader<Cursor> onCreateLoader(int paramInt, Bundle paramBundle) {
		// TODO Auto-generated method stub
		return new CursorLoader(getActivity(), 
				Players.CONTENT_URI, 
				PlayerContentProvider.PROJECTION_MAP, 
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
