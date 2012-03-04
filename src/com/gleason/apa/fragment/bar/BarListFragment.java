package com.gleason.apa.fragment.bar;

import java.util.HashSet;
import java.util.Set;

import com.gleason.apa.R;
import com.gleason.apa.fragment.BaseListFragment;
import com.gleason.apa.model.Bar.Bars;
import com.gleason.apa.model.data.BarContentProvider;

import android.content.ClipData;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

public class BarListFragment extends BaseListFragment implements
		LoaderManager.LoaderCallbacks<Cursor>, OnItemLongClickListener {
	Set<OnBarSelectedListener> listeners = new HashSet<BarListFragment.OnBarSelectedListener>();

	private SimpleCursorAdapter adapter = null;
	int[] to = { R.id.bar_name_list, R.id.bar_address_list };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter = new SimpleCursorAdapter(getActivity(), R.layout.bar_list,
				null, BarContentProvider.PROJECTION_MAP_NO_ID, to, 0);
		setListAdapter(adapter);
		getLoaderManager().initLoader(0, null, this);
		ListView lv = getListView();
		lv.setOnItemLongClickListener(this);
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		for (OnBarSelectedListener listener : listeners) {
			listener.onBarSelected(position);
		}
	}

	public void attachListener(OnBarSelectedListener listener) {
		listeners.add(listener);
	}

	public interface OnBarSelectedListener {
		public void onBarSelected(Integer id);
	}

	public Loader<Cursor> onCreateLoader(int paramInt, Bundle paramBundle) {
		// TODO Auto-generated method stub
		return new CursorLoader(getActivity(), Bars.CONTENT_URI,
				BarContentProvider.PROJECTION_MAP, null, null, null);
	}

	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor(cursor);
	}

	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}

	public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
		final String title = (String) ((TextView) v.findViewById(R.id.bar_name_list)).getText();
        // Set up clip data with the category||entry_id format.
        ClipData data = ClipData.newPlainText(title, "test");
        v.startDrag(data, new DragShadowBuilder(v), null, 0);
        return true;
	}
	
}
