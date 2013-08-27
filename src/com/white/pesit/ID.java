package com.white.pesit;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ID extends android.support.v4.app.Fragment implements
		OnClickListener {
	OnArticleSelectedListener mListener;
	TextView name_text;
	TextView usn_text;
	TextView semester_text;
	EditText year;
	EditText roll;
	Spinner department;
	Button go;
	String usn = null;
	DatabaseHelper db;
	CursorFactory factory;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.id, container, false);
		factory = null;
		db = new DatabaseHelper(getActivity().getApplicationContext(), " ",
				factory, 1);
		go = (Button) v.findViewById(R.id.go);
		year = (EditText) v.findViewById(R.id.year);
		roll = (EditText) v.findViewById(R.id.roll);
		department = (Spinner) v.findViewById(R.id.dept);
		usn_text = (TextView) v.findViewById(R.id.usn_text);
		name_text = (TextView) getActivity().findViewById(R.id.title);
		semester_text = (TextView) getActivity().findViewById(R.id.sem);
		go.setOnClickListener(this);
		updateFromDatabase();
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

		return v;
	}

	private void updateFromDatabase() {

		if (db.getDetails(0) != null)
			name_text.setText(db.getDetails(0).getValue());
		if (db.getDetails(1) != null)
			usn_text.setText("Usn : " + db.getDetails(1).getValue());
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

	public interface OnArticleSelectedListener {
		public void onArticleSelected(Uri articleUri);
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

	static ID newInstance() {
		ID id = new ID();
		return id;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.go:
			usn = "1pe" + year.getText().toString()
					+ department.getSelectedItem().toString()
					+ roll.getText().toString();
			Log.d("ID.java", "Usn :" + usn);
			if (usn.length() != 10)
				Toast.makeText(getActivity().getApplicationContext(),
						"Please enter valid data", 3000).show();
			else {

				try {
					if (db.getDetails(1) == null) {
						db.addDetails(new Details(1, "usn", usn));
						Log.d("ID", "Added usn to db");
					} else {
						db.updateDetails(new Details(1, "usn", usn));
						Log.d("ID", "Updated usn in db");
					}

				} catch (Exception e) {
					e.printStackTrace();
					Log.d("ID", "Couldnt add usn to db");
				}

				Uri articleUri = Uri.parse(usn);
				mListener.onArticleSelected(articleUri);
			}

		}
	}
}
