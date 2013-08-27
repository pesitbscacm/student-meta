package com.white.pesit;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.white.pesit.ID.OnArticleSelectedListener;

public class MainActivity extends FragmentActivity implements
		OnArticleSelectedListener, OnClickListener {
	static final int NUM_ITEMS = 10;

	MainPagerAdapter mAdapter;

	ViewPager mPager;
	String internals[] = new String[5];
	String server_result;
	DatabaseHelper db;
	CursorFactory factory;
	String usn = null;
	JSONObject json;
	Display display;
	int screen_height;
	int screen_width;
	ProgressBar loading;
	ImageButton edit;
	ImageButton refresh;
	TextView name_text;
	TextView usn_text;
	TextView semester_text;

	String filename;
	int internal_status[] = new int[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mAdapter = new MainPagerAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager);
		PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
		// pagerTabStrip.setDrawFullUnderline(true);
		pagerTabStrip.setTabIndicatorColor(Color.GRAY);
		factory = null;
		db = new DatabaseHelper(getApplicationContext(), " ", factory, 1);
		edit = (ImageButton) findViewById(R.id.ibtn_edit);
		refresh = (ImageButton) findViewById(R.id.ibtn_refresh);
		edit.setOnClickListener(this);
		refresh.setOnClickListener(this);
		name_text = (TextView) findViewById(R.id.title);
		semester_text = (TextView) findViewById(R.id.sem);
		updateFromDatabase();
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		mPager.setAdapter(mAdapter);

	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	private void updateFromDatabase() {

		if (db.getDetails(0) != null)
			name_text.setText(db.getDetails(0).getValue());
		if (db.getDetails(4) != null) {
			String semester = "1";
			int sem = 1;
			try {
				semester = db.getDetails(4).getValue().replace("SEM", "");
				sem = Integer.parseInt(semester);
			} catch (Exception e) {
				e.printStackTrace();
				sem = 1;
				semester = "1";
			}
			switch (sem) {
			case 1:
				semester += "st Semester";
				break;
			case 2:
				semester += "nd Semester";
				break;
			case 3:
				semester += "rd Semester";
				break;
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			default:
				semester += "th Semester";
				break;

			}
			semester_text.setText(semester);
		}
		Log.d("ID.java", "Setting name, semester"
				+ name_text.getText().toString());
	}

	private String fetchAttendance() throws JSONException {
		System.out.println("fetchAttendance() stuff1");
		ServerCode Attendance = new ServerCode();

		try {
			server_result = Attendance
					.newLink("http://pesitbscattnjson.herokuapp.com/?usn="
							+ usn);
			System.out.println("Result is " + server_result);
		} catch (Exception e) {
			System.out.println("Excepshin" + e);
			return "Error";
		}
		json = new JSONObject(server_result);
		return "Success";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	private class BackgroundStuff extends AsyncTask<String, Integer, String> {
		FragmentManager fm = getSupportFragmentManager();

		// private ProgressDialog dialog = new
		// ProgressDialog(MainActivity.this);
		@Override
		protected void onPreExecute() {
			if (isOnline()) {
				if (db.getDetails(3) != null)
					db.deleteDetails(3); // attendance
				if (db.getDetails(0) != null)
					db.deleteDetails(0); // name
				// if (db.getDetails(1) != null)
				// db.deleteDetails(1); //usn
				// System.out.println("In here");
				mAdapter.notifyDataSetChanged();
			}
		}

		@Override
		protected String doInBackground(String... arg0) {
			if (isOnline() == true) {

				String fetch_status = null;
				try {
					// See here

					fetch_status = fetchAttendance();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (Exception e) {
					Log.d("MainActivity", "Attendance oh crap");
					return "No internet";
				}

				return fetch_status;
			} else {
				return "No internet";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			// if (dialog.isShowing())
			// dialog.dismiss();
			if (result.equalsIgnoreCase("No Internet"))
				Toast.makeText(getApplicationContext(),
						"Check Internet Connectivity", 3000).show();
			if (result.equalsIgnoreCase("Success")) {
				if (db.getDetails(3) == null) {
					db.addDetails(new Details(3, "attendance string",
							server_result));
					mAdapter.notifyDataSetChanged();
				} else {
					db.updateDetails(new Details(3, "attendance string",
							server_result));
				}
				System.out.println("Adding string to database");

				if (db.getDetails(0) != null) {
					try {
						db.updateDetails(new Details(0, "name", " "
								+ json.getString("name")));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					try {
						db.addDetails(new Details(0, "name", " "
								+ json.getString("name")));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				if (db.getDetails(4) != null) {
					try {
						db.updateDetails(new Details(4, "semester", json
								.getString("semester")));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {

					try {
						db.addDetails(new Details(4, "semester", ""
								+ json.getString("semester")));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (db.getDetails(5) != null) {
					try {
						db.updateDetails(new Details(5, "section", json
								.getString("section")));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

					try {
						db.addDetails(new Details(5, "section", json
								.getString("section")));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Added section: "
						+ db.getDetails(5).getValue());
				mAdapter.notifyDataSetChanged();
				BackgroundStuff_internals internaldownload = new BackgroundStuff_internals();
				internaldownload.execute();
			}

		}
	}

	@Override
	public void onArticleSelected(Uri articleUri) {
		usn = articleUri.toString();
		mPager.setCurrentItem(1);
		usn = db.getDetails(1).getValue();
		downloadStuff();
	}

	private void downloadStuff() {
		BackgroundStuff attendacedownload = new BackgroundStuff();
		attendacedownload.execute();

	}

	private String fetchInternals(int i) throws JSONException {
		System.out.println("fetchInternals() stuff1");
		ServerCode Internalmarks = new ServerCode();
		filename = getFileName(i);
		try {
			server_result = Internalmarks.serverInteract("example.php?usn="
					+ usn + "&xlsfile=" + filename, null);
			System.out.println("Reesult is " + server_result);

		} catch (Exception e) {
			System.out.println("Excepshin" + e);
			return "Error";
		}
		internals[i] = server_result.substring(server_result.indexOf('{'),
				server_result.lastIndexOf('}') + 1);
		return "Success";
	}

	private String getFileName(int i) {
		// String filename = "6A";
		String filename = db.getDetails(4).getValue().replace("SEM", "")
				.toString()
				+ db.getDetails(5).getValue();
		filename += "_IA" + i + ".xls";
		Log.d("MainActivity", "File name is " + filename);
		return filename;

	}

	private class BackgroundStuff_internals extends
			AsyncTask<String, Integer, String> {

		private ProgressDialog dialog = new ProgressDialog(getBaseContext());

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Fetching your internal marks");
			dialog.setCancelable(true);
			if (isOnline()) {
				if (db.getDetails(2) != null)
					db.deleteDetails(2);
				if (db.getDetails(6) != null)
					db.deleteDetails(6);
				if (db.getDetails(7) != null)
					db.deleteDetails(7);
				mAdapter.notifyDataSetChanged();
			}
		}

		@Override
		protected String doInBackground(String... arg0) {
			if (isOnline() == true) {
				String fetch_status = null;
				int flag = 0;
				try {
					for (int i = 1; i <= 3; i++) {
						fetch_status = fetchInternals(i);
						Log.d("BGInternals", "Success" + i);
						if (fetch_status.equalsIgnoreCase("Success")) {
							internal_status[i] = 1; // Got the i-th internal
													// marks
							flag = 1; // Got atleast one internal
							Log.d("BGInternals", "Setting internal status" + i);
						} else
							internal_status[i] = 0;
					}
				} catch (JSONException e) {
					Log.d("BGInternals", "Woops");
					e.printStackTrace();
					return "No internet";
				}
				if (flag == 1)
					return "Success";
				else
					return "Error";
			} else {
				return "No internet";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			if (dialog.isShowing())
				dialog.dismiss();

			if (result.equalsIgnoreCase("Success")
					|| result.equalsIgnoreCase("No Internet")) {

				if (internal_status[1] == 1)
					db.addDetails(new Details(2, "internalmarks", internals[1]));
				if (internal_status[2] == 1)
					db.addDetails(new Details(6, "internalmarks2", internals[2]));
				if (internal_status[3] == 1)
					db.addDetails(new Details(7, "internalmarks3", internals[3]));
				mAdapter.notifyDataSetChanged();
			}
		}
	}

	private static String makeFragmentName(int viewId, int index) {
		return "android:switcher:" + viewId + ":" + index;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibtn_edit:
			mPager.setCurrentItem(0);
			break;
		case R.id.ibtn_refresh:
			downloadStuff();
			break;
		}

	}

	/*
	 * Database details
	 * 
	 * 0 name
	 * 
	 * 1 (should be usn)
	 * 
	 * 2 internalmarks
	 * 
	 * 3 attendance string
	 * 
	 * 4 semester
	 * 
	 * 5 section
	 * 
	 * 6 internalmarks2
	 * 
	 * 7 internalmarks3
	 * 
	 * 8
	 */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection

		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent i = new Intent(MainActivity.this, About.class);
			startActivity(i);
			break;
		}
		return true;

	}
}