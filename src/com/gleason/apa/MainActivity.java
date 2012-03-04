package com.gleason.apa;

import java.util.ArrayList;

import com.gleason.apa.enumerations.Screen;
import com.gleason.apa.fragment.bar.BarEditFragment;
import com.gleason.apa.fragment.bar.BarFragment;
import com.gleason.apa.fragment.bar.BarListFragment;
import com.gleason.apa.fragment.player.PlayerListFragment;
import com.gleason.apa.fragment.team.TeamListFragment;
import com.gleason.apa.util.Util;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity implements
		BarFragment.OnBarSelectedListener {

	private ViewPager viewPager;
	private MainPageAdapter mainPagerAdapter;
	private MainPageAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		viewPager = (ViewPager) findViewById(R.id.mainpager);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
//		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//		actionBar.setListNavigationCallbacks(mSpinnerAdapter, this);
		this.mAdapter = new MainPageAdapter(this, viewPager);
		if (savedInstanceState != null) {
			actionBar.setSelectedNavigationItem(savedInstanceState.getInt(
					"tab", 0));
		}
		View barView = findViewById(R.id.base_edit);
		Boolean isDualPane = barView != null && barView.getVisibility() == View.VISIBLE;
		if(isDualPane){
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(R.id.base_edit, new BarEditFragment());
			ft.commit();
		}
	}

	private static class MainPageAdapter extends FragmentPagerAdapter implements
			ViewPager.OnPageChangeListener, ActionBar.TabListener {
		private final Context mContext;
		private final ActionBar mActionBar;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
		private int position = 0;
		
		static final class TabInfo {
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(Class<?> _class, Bundle _args) {
				clss = _class;
				args = _args;
			}
		}

		public MainPageAdapter(Activity activity, ViewPager pager) {
			super(((FragmentActivity) activity).getSupportFragmentManager());
			mContext = activity;
			mActionBar = activity.getActionBar();
			mViewPager = pager;
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
			
			
			for (Screen screen : Screen.values()) {
				switch (screen) {
				case BAR:
					this.addTab(
							mActionBar.newTab().setText(Screen.BAR.getTitle()),
							BarListFragment.class, null);
					break;
				case TEAM:
					this.addTab( mActionBar.newTab().setText(Screen.TEAM.getTitle()),
							TeamListFragment.class, null);
					break;
				case PLAYER:
					this.addTab(
							mActionBar.newTab().setText(
									Screen.PLAYER.getTitle()),
							PlayerListFragment.class, null);
					break;
				case GESTURE:
//					this.addTab(
//							mActionBar.newTab().setText(
//									Screen.GESTURE.getTitle()),
//							GestureFragment.class, null);
					break;
				}
			}
		}

		public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
			TabInfo info = new TabInfo(clss, args);
			tab.setTag(info);
			tab.setTabListener(this);
			mTabs.add(info);
			mActionBar.addTab(tab);
			notifyDataSetChanged();
		}

		public String getTitle(int position) {
			// TODO Auto-generated method stub
			return Screen.getScreenById(position).getTitle();
		}

		public void onPageScrollStateChanged(int arg0) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			 mActionBar.setSelectedNavigationItem(position);
			 this.position = position;
		}

		@Override
        public Fragment getItem(int position) {
            TabInfo info = mTabs.get(position);
            return Fragment.instantiate(mContext, info.clss.getName(), info.args);
        }

		@Override
        public int getCount() {
            return mTabs.size();
		}

		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			Object tag = tab.getTag();
			for (int i = 0; i < mTabs.size(); i++) {
				if (mTabs.get(i) == tag) {
					mViewPager.setCurrentItem(i);
				}
			}
		}

		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
	}

	public void onSelect(Integer index) {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, EditActivity.class);
		i.putExtra("type", "bar");
		i.putExtra(BarEditFragment.UPDATE_FLAG, true);
		i.putExtra(BarEditFragment.BAR_ID_FLAG, index);
		startActivityForResult(i, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		boolean parent = super.onMenuItemSelected(featureId, item);
		if (!parent) {
			switch (item.getItemId()) {
			case R.id.create:
				Intent i = new Intent(this, NewActivity.class);
				// if(mainPagerAdapter. instanceof BarFragment){
				 i.putExtra(Util.TYPE_KEY, Screen.BAR.getTitle());
				// }
				// else if(mainPagerAdapter.currentFragment instanceof
				// PlayerFragment){
				// i.putExtra(Util.TYPE_KEY, Screen.PLAYER.getTitle());
				// }
				// else if(mainPagerAdapter.currentFragment instanceof
				// GestureFragment){
				// return false;
				// }
				// else if(mainPagerAdapter.currentFragment instanceof
				// TeamFragment){
				// i.putExtra(Util.TYPE_KEY, Screen.TEAM.getTitle());
				// }
				i.putExtra(BarEditFragment.UPDATE_FLAG, false);
				startActivityForResult(i, 0);
				break;
			default:
				break;
			}
			return true;
		} else {
			return parent;
		}
	}

}
