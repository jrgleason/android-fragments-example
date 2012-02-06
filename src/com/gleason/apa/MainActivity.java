package com.gleason.apa;

import com.gleason.apa.fragment.BarFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;
import com.viewpagerindicator.TitleProvider;

public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;  
    private MainPageAdapter mainPagerAdapter;
    PageIndicator mIndicator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	
		viewPager = (ViewPager) findViewById(R.id.mainpager);  
        mainPagerAdapter = new MainPageAdapter(getSupportFragmentManager());  
        viewPager.setAdapter(mainPagerAdapter);  
        
        mIndicator = (PageIndicator)findViewById(R.id.indicator);
		mIndicator.setViewPager(viewPager);
	}
	
	private enum Screen{
		BAR("Bar"),
		SCHEDULE("Schedule");
		
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
	
	private class MainPageAdapter extends FragmentPagerAdapter implements TitleProvider{
		public MainPageAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			Screen desiredScreen = Screen.getScreenById(arg0);
			if(desiredScreen != null){
				switch (desiredScreen){
					case BAR:
						return BarFragment.newInstance();
					case SCHEDULE:
						return ScheduleFragment.newInstance();
				}
			}
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// Screens should be same as enum
			return Screen.values().length;
		}

		@Override
		public String getTitle(int position) {
			// TODO Auto-generated method stub
			return Screen.getScreenById(position).getTitle();
		}
		
	}
}
