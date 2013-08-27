package com.white.pesit;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Calendar extends android.support.v4.app.Fragment implements OnClickListener {

	
	DatabaseHelper db;
	String result;
	CursorFactory factory;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.calendar, container, false);
	  /*  TouchImageView img = (TouchImageView) v.findViewById(R.id.calendarid);
        img.setImageResource(R.drawable.pics);
        img.setMaxZoom(4f);*/
		Toast.makeText(getActivity(), "Click to enlarge!", 3000).show();
		ImageView imgview = (ImageView) v.findViewById(R.id.calendarid);
	
		//imgview.setScaleType(ScaleType.FIT_CENTER);
		imgview.setClickable(true);
		imgview.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		        Toast.makeText(getActivity(), "clicked",3000).show();
		        Intent i = new Intent(v.getContext(),CalendarFullScreen.class);
		        startActivity(i);
		    }
		});
		return v;
		//setContentView(R.layout.calendar);
		//Call 
}
	static Calendar newInstance() {
		Calendar calendar = new Calendar();
		return calendar;

	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}