package com.white.pesit;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;

public class Attendance extends android.support.v4.app.Fragment {

	DatabaseHelper db;
	CursorFactory factory;
	String usn = null;
	JSONObject json;
	ListView lv;
	AttendanceAdapter attendance_adapter;
	ArrayList<AttendanceDetail> array_list;
	Display display;
	int screen_height;
	int screen_width;
	ProgressBar loading;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.attendance, container, false);
		lv = (ListView) v.findViewById(R.id.lv);
		loading = (ProgressBar) v.findViewById(R.id.progressBar1);
		factory = null;
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		db = new DatabaseHelper(getActivity().getApplicationContext(), " ",
				factory, 1);
		array_list = new ArrayList<AttendanceDetail>();
		display = getActivity().getWindowManager().getDefaultDisplay();
		screen_height = display.getHeight();
		screen_width = display.getWidth();
		if (db.getDetails(3) != null) {
			loading.setVisibility(View.GONE);
			lv.setVisibility(View.VISIBLE);
		} else {
			return v;
		}

		

		try {
			json = new JSONObject(db.getDetails(3).getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			setupListView();
		} catch (Exception e) {
			System.out.println("Attendace dot java" + e.getMessage());
		}
		return v;
	}

	public void refresh() {
		System.out.println("Trying to do some refreshing scenes 1");
		if (this.isDetached()) {
			getFragmentManager().beginTransaction().detach(this).attach(this)
					.commit();
		}
		System.out.println("Trying to do some refreshing scenes 2");
	}

	private void setupListView() {
		try {
			int flag = 0, c = 0;
			Iterator<Object> keys = json.keys();
			String subj;
			for (int i = 0;; i++) {
				flag = 0;
				subj = String.valueOf(keys.next());
				if (subj.equalsIgnoreCase("college")
						|| subj.equalsIgnoreCase("name")
						|| subj.equalsIgnoreCase("section")
						|| subj.equalsIgnoreCase("course")
						|| subj.equalsIgnoreCase("semester")
						|| subj.equals("usn")) {
					flag = 1;
				}
				if (flag == 0) {

					array_list.add(new AttendanceDetail(c, subj, json
							.getJSONObject(subj).getString("percent"), json
							.getJSONObject(subj).getString("attn"), json
							.getJSONObject(subj).getString("total")));
					c++;
				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			db.addDetails(new Details(0, "name", json.getString("name")+" "));
			db.addDetails(new Details(4, "semester", json.getString("semester")
					.substring(3)+" "));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		attendance_adapter = new AttendanceAdapter(getActivity()
				.getApplicationContext(), 0, array_list, screen_height,
				screen_width);
		lv.setAdapter(attendance_adapter);

	}

	static Attendance newInstance() {
		Attendance attendance = new Attendance();
		return attendance;

	}

}
