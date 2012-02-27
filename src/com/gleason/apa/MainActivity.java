package com.gleason.apa;


import com.gleason.apa.fragment.bar.BarEditFragment;
import com.gleason.apa.fragment.bar.BarFragment;
import com.gleason.apa.fragment.player.PlayerFragment;
import com.gleason.apa.fragment.team.TeamFragment;


import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitleProvider;

public class MainActivity extends FragmentActivity implements BarFragment.OnBarSelectedListener,OnNavigationListener {

	private ViewPager viewPager;  
    private MainPageAdapter mainPagerAdapter;
    private PageIndicator mIndicator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		viewPager = (ViewPager) findViewById(R.id.mainpager); 
		refreshPageAdapter();
		mIndicator = (PageIndicator)findViewById(R.id.indicator);
		mIndicator.setViewPager(viewPager);
		
		
		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.action_list,
		          android.R.layout.simple_spinner_dropdown_item);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setListNavigationCallbacks(mSpinnerAdapter, this);
	}
	
	MainActivity getActivity() {
		return this;
	}
	
	private void refreshPageAdapter(){
		mainPagerAdapter = new MainPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPagerAdapter); 
	}

	
	private enum Screen{
		BAR("Bar"),
		TEAM("Team"),
		PLAYER("Player");
//		SCHEDULE("Schedule");
		
		private Screen(String title){
			this.title = title;
		}
		
		private final String title;
		
		public String getTitle(){
			return title;
		}
		
		public static Screen getScreenById(Integer id){
			for (Screen s : Screen.values()) {
				if(s.ordinal()==id.intValue()){
					return s;
				}
			}
			return null;
		}
	}
	
	protected class MainPageAdapter extends FragmentPagerAdapter implements TitleProvider{
		public MainPageAdapter(FragmentManager fm) {
			super(fm);
		}
		
		@Override
		public Fragment getItem(int arg0) {
			Screen desiredScreen = Screen.getScreenById(arg0);
			if(desiredScreen != null){
				switch (desiredScreen){
					case BAR:
						BarFragment fragment = BarFragment.newInstance();
						fragment.attach(getActivity());
						return fragment;
					case TEAM:
						return TeamFragment.newInstance();
					case PLAYER:
						return PlayerFragment.newInstance();
//					case SCHEDULE:
//						return ScheduleFragment.newInstance();
				}
			}
			return null;
		}

		@Override
		public int getCount() {
			// Screens should be same as enum
			return Screen.values().length;
		}

		
		
		public String getTitle(int position) {
			// TODO Auto-generated method stub
			return Screen.getScreenById(position).getTitle();
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
		if(!parent){
			switch (item.getItemId()) {
			case R.id.create:
				Intent i = new Intent(this, NewActivity.class);
				i.putExtra("type", "bar");
		        i.putExtra(BarEditFragment.UPDATE_FLAG, false);
		        startActivityForResult(i, 0);
				break;
			default:
				break;
			}
			return true;
		}
		else{
			return parent;
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
