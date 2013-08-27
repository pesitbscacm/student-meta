package com.white.pesit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter{
	static final int NUM_ITEMS = 5;
	private String[] pageTitle = {
            "ID", "Attendance", "Internals","Calendar","TimeTable"
    };
	public MainPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		//return ID.newInstance();
		switch(position){
		case 0: return ID.newInstance();
		case 1: return Attendance.newInstance();
		case 2: return Internals.newInstance();
		case 3: return Calendar.newInstance();
		case 4: return Timetable.newInstance();
		default:return ID.newInstance();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return NUM_ITEMS;
	}
	
	

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

	@Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }
	private static String makeFragmentName(int viewId, int index)
	{
	     return "android:switcher:" + viewId + ":" + index;
	}
}
