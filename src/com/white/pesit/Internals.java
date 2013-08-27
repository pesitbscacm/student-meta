package com.white.pesit;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

public class Internals extends android.support.v4.app.Fragment {

	String server_result;
	DatabaseHelper db;
	CursorFactory factory;
	String usn = null;
	JSONObject json,json2,json3;
	ListView lv;
	InternalsAdapter internals_adapter;
	Display display;
	int screen_height;
	int screen_width;
	ProgressBar loading;
	ArrayList<InternalsDetail> array_list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		display = getActivity().getWindowManager().getDefaultDisplay();
		screen_height = display.getHeight();
		screen_width = display.getWidth();
		View v = inflater.inflate(R.layout.internals, container, false);
		loading = (ProgressBar) v.findViewById(R.id.progressBar1);
		factory = null;
		db = new DatabaseHelper(getActivity().getApplicationContext(), " ",
				factory, 1);
		lv = (ListView) v.findViewById(R.id.lv);
		try {
			if (db.getDetails(2) == null) {
				return v;
			} else {

				loading.setVisibility(View.GONE);
				lv.setVisibility(View.VISIBLE);
			}
			json = new JSONObject(db.getDetails(2).getValue());
			json2= new JSONObject(db.getDetails(6).getValue());
			json3= new JSONObject(db.getDetails(7).getValue());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			array_list = new ArrayList<InternalsDetail>();
			array_list.add(new InternalsDetail(0, "Subject", "T1", "T2", "T3"));
			array_list.add(new InternalsDetail(1, json.getJSONObject(
					"subject 1").getString("name"), json.getJSONObject(
					"subject 1").getString("marks"), json2.getJSONObject("subject 1").getString("marks"), json3.getJSONObject("subject 1").getString("marks")));
			array_list.add(new InternalsDetail(2, json.getJSONObject(
					"subject 2").getString("name"), json.getJSONObject(
					"subject 2").getString("marks"), json2.getJSONObject("subject 2").getString("marks"), json3.getJSONObject("subject 2").getString("marks")));
			array_list.add(new InternalsDetail(3, json.getJSONObject(
					"subject 3").getString("name"), json.getJSONObject(
					"subject 3").getString("marks"), json2.getJSONObject("subject 3").getString("marks"), json3.getJSONObject("subject 3").getString("marks")));
			array_list.add(new InternalsDetail(4, json.getJSONObject(
					"subject 4").getString("name"), json.getJSONObject(
					"subject 4").getString("marks"), json2.getJSONObject("subject 4").getString("marks"), json3.getJSONObject("subject 4").getString("marks")));
			array_list.add(new InternalsDetail(5, json.getJSONObject(
					"subject 5").getString("name"), json.getJSONObject(
					"subject 5").getString("marks"), json2.getJSONObject("subject 5").getString("marks"), json3.getJSONObject("subject 5").getString("marks")));
			array_list.add(new InternalsDetail(6, json.getJSONObject(
					"subject 6").getString("name"), json.getJSONObject(
					"subject 6").getString("marks"), json2.getJSONObject("subject 6").getString("marks"), json3.getJSONObject("subject 6").getString("marks")));
			if (!(json.getJSONObject("subject 7").getString("name")
					.equalsIgnoreCase("NA")))

			{
				array_list.add(new InternalsDetail(7, json.getJSONObject(
						"subject 7").getString("name"), json.getJSONObject(
						"subject 7").getString("marks"), json2.getJSONObject("subject 7").getString("marks"), json3.getJSONObject("subject 7").getString("marks")));
			}

			internals_adapter = new InternalsAdapter(getActivity()
					.getApplicationContext(), 0, array_list, screen_height,
					screen_width);

		} catch (Exception e) {
			e.printStackTrace();
		}
		lv.setAdapter(internals_adapter);
		return v;
	}

	private Object getWindowManager() {
		return null;
	}

	public static Internals newInstance() {
		Internals internals = new Internals();
		return internals;
	}

}