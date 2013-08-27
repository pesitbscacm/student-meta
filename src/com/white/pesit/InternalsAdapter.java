package com.white.pesit;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class InternalsAdapter extends ArrayAdapter<InternalsDetail> {

	private Context context;
	private View rowView;
	private LayoutInflater inflater;
	private ArrayList<InternalsDetail> array_list;
	int screen_height;
	int screen_width;

	public InternalsAdapter(Context context, int textViewResourceId,
			List<InternalsDetail> objects,int screen_height,int screen_width) {
		super(context, textViewResourceId, objects);
		this.context = context;
		inflater = LayoutInflater.from(context);
		array_list = (ArrayList<InternalsDetail>) objects;
		this.screen_height=screen_height;
		this.screen_width=screen_width;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			v = inflater.inflate(R.layout.internals_row, parent, false);
			
		}
		TextView subject = (TextView) v.findViewById(R.id.subject);
		TextView marks1 = (TextView) v.findViewById(R.id.marks1);
		TextView marks2 = (TextView) v.findViewById(R.id.marks2);
		TextView marks3 = (TextView) v.findViewById(R.id.marks3);
		subject.setText(array_list.get(position).getSubject());
		marks1.setText(array_list.get(position).getMarks1());
		marks2.setText(array_list.get(position).getMarks2());
		marks3.setText(array_list.get(position).getMarks3());
		subject.getLayoutParams().width=(int)(0.3*screen_width);
		
		marks1.getLayoutParams().width=(int)(0.2*screen_width);
		marks2.getLayoutParams().width=(int)(0.2*screen_width);
		marks3.getLayoutParams().width=(int)(0.2*screen_width);
		return v;
	}

	/*
	 * Context context; private LayoutInflater inflater; DatabaseMarks dbm;
	 * CursorFactory factory; Cursor cursor;
	 * 
	 * public InternalsAdapter(Context context, Cursor c) { super(context, c);
	 * this.context = context; inflater = LayoutInflater.from(context); cursor =
	 * getCursor(); // TODO Auto-generated constructor stub }
	 * 
	 * @Override public void bindView(View view, Context context, Cursor cursor)
	 * { TextView subject = (TextView) view.findViewById(R.id.subject); TextView
	 * marks1 = (TextView) view.findViewById(R.id.marks1); TextView marks2 =
	 * (TextView) view.findViewById(R.id.marks2); TextView marks3 = (TextView)
	 * view.findViewById(R.id.marks3); subject.setText(cursor.getString(1));
	 * System.out.println(cursor.getCount() + "");
	 * System.out.println(cursor.getPosition());
	 * System.out.println(cursor.getColumnCount());
	 * marks1.setText(cursor.getString(2)); marks2.setText(cursor.getString(3));
	 * marks3.setText(cursor.getString(4));
	 * 
	 * }
	 * 
	 * @Override public View newView(Context context, Cursor cursor, ViewGroup
	 * parent) { System.out.println("like sup"); // /* View view =
	 * inflater.inflate(R.layout.internals_row, parent, false); TextView subject
	 * = (TextView) view.findViewById(R.id.subject); TextView marks1 =
	 * (TextView) view.findViewById(R.id.marks1); TextView marks2 = (TextView)
	 * view.findViewById(R.id.marks2); TextView marks3 = (TextView)
	 * view.findViewById(R.id.marks3); subject.setText(cursor.getString(1));
	 * System.out.println(cursor.getCount() + "");
	 * System.out.println(cursor.getPosition());
	 * System.out.println(cursor.getColumnCount());
	 * marks1.setText(cursor.getString(2)); marks2.setText(cursor.getString(3));
	 * marks3.setText(cursor.getString(4)); // return view; }
	 * 
	 * @Override public View getView(int position, View view, ViewGroup parent)
	 * {
	 * 
	 * if (cursor != null) { cursor.moveToPosition(position); view =
	 * newView(context, cursor, parent); // bindView(view, context, cursor);
	 * return view;
	 * 
	 * }
	 * 
	 * return view;
	 * 
	 * }
	 */

	/*
	 * public InternalsAdapter(Context context) { this.context = context; dbm =
	 * new DatabaseMarks(context, " ", factory, 1); inflater =
	 * LayoutInflater.from(context); }
	 * 
	 * @Override public int getCount() { // TODO Auto-generated method stub
	 * //return dbm.getReadableDatabase();
	 * 
	 * }
	 * 
	 * @Override public Object getItem(int position) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public long getItemId(int position) { // TODO Auto-generated
	 * method stub return 0; }
	 * 
	 * @Override public View getView(int position, View convertView, ViewGroup
	 * parent) { View v = convertView; if (v == null) {
	 * System.out.println("like sup"); rowView =
	 * inflater.inflate(R.layout.internals_row, parent, false); TextView subject
	 * = (TextView) rowView.findViewById(R.id.subject); TextView marks1 =
	 * (TextView) rowView.findViewById(R.id.marks1); TextView marks2 =
	 * (TextView) rowView.findViewById(R.id.marks2); TextView marks3 =
	 * (TextView) rowView.findViewById(R.id.marks3); if (position == 0) {
	 * subject.setText("Subject"); System.out.println("like sup1");
	 * marks1.setText("T1"); marks2.setText("T2"); marks3.setText("T3"); } else
	 * { subject.setText(dbm.getInternalsDetail(position).getSubject());
	 * System.out.println("like sup2");
	 * marks1.setText(dbm.getInternalsDetail(position).getMarks1());
	 * marks2.setText(dbm.getInternalsDetail(position).getMarks2());
	 * marks3.setText(dbm.getInternalsDetail(position).getMarks3()); } }
	 * System.out.println("like sup3"); return v; }
	 */

}
