package com.white.pesit;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AttendanceAdapter extends ArrayAdapter<AttendanceDetail> {
	private Context context;
	private View rowView;
	private LayoutInflater inflater;
	private ArrayList<AttendanceDetail> array_list;
	int screen_height;
	int screen_width;

	public AttendanceAdapter(Context context, int textViewResourceId,
			List<AttendanceDetail> objects, int screen_height, int screen_width) {
		super(context, textViewResourceId, objects);
		this.context = context;
		inflater = LayoutInflater.from(context);
		array_list = (ArrayList<AttendanceDetail>) objects;
		this.screen_height = screen_height;
		this.screen_width = screen_width;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			v = inflater.inflate(R.layout.attendance_row, parent, false);
		}
		TextView subject = (TextView) v.findViewById(R.id.subject);
		TextView percentage = (TextView) v.findViewById(R.id.percentage);
		TextView attended = (TextView) v.findViewById(R.id.attended);
		TextView total = (TextView) v.findViewById(R.id.total);
		subject.setText(array_list.get(position).getSubject());
		percentage.setText(array_list.get(position).getPercentage() + "%");
		attended.setText("Attended:" + array_list.get(position).getAttended());
		total.setText("Total:" + array_list.get(position).getTotal());

		return v;
	}

}