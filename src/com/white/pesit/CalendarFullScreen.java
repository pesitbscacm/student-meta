package com.white.pesit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class CalendarFullScreen extends Activity{


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_fullscreen);
		ImageView imgview = (ImageView)findViewById(R.id.calendarid);
		imgview.setClickable(true);
		imgview.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		       finish();
		    }
		});
	}
	
	}
