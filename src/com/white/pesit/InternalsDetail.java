package com.white.pesit;

public class InternalsDetail {
	int _id;
	String _subject;
	String _marks1;
	String _marks2;
	String _marks3;

	// Empty constructor
	public InternalsDetail() {

	}

	// constructor
	public InternalsDetail(int id, String subject, String _marks1,
			String _marks2, String _marks3) {
		this._id = id;
		this._subject = subject;
		this._marks1 = _marks1;
		this._marks2 = _marks2;
		this._marks3 = _marks3;
	}

	// constructor
	public InternalsDetail(String subject, String _marks1, String _marks2,
			String _marks3) {
		this._subject = subject;
		this._marks1 = _marks1;
		this._marks2 = _marks2;
		this._marks3 = _marks3;
	}

	// getting ID
	public int getID() {
		return _id;
	}

	// setting id
	public void setID(int id) {
		this._id = id;
	}

	// getting subject
	public String getSubject() {
		return _subject;
	}

	// setting subject
	public void setSubject(String subject) {
		this._subject = subject;
	}

	// getting marks1
	public String getMarks1() {
		return _marks1;
	}

	// setting marks1
	public void setMarks1(String marks1) {
		this._marks1 = marks1;
	}

	// getting marks1
	public String getMarks2() {
		return _marks2;
	}

	// setting marks1
	public void setMarks2(String marks2) {
		this._marks2 = marks2;
	}

	// getting marks1
	public String getMarks3() {
		return _marks3;
	}

	// setting marks1
	public void setMarks3(String marks3) {
		this._marks3 = marks3;
	}
}
