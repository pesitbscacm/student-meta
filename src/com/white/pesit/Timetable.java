package com.white.pesit;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.white.pesit.ID.OnArticleSelectedListener;

public class Timetable extends android.support.v4.app.Fragment {
	OnArticleSelectedListener mListener;

	String usn = null;
	DatabaseHelper db;
	CursorFactory factory;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.timetable, container, false);
		factory = null;
		db = new DatabaseHelper(getActivity().getApplicationContext(), " ",
				factory, 1);
		updateFromDatabase();
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		return v;
	}

	private void updateFromDatabase() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnArticleSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnArticleSelectedListener");
		}
	}

	static Timetable newInstance() {
		Timetable timetable = new Timetable();
		return timetable;

	}

}
