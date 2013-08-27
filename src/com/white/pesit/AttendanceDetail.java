package com.white.pesit;

public class AttendanceDetail {
    int _id;
    String _subject;
    String _percentage;
    String _attended;
    String _total;

    // Empty constructor
    public AttendanceDetail() {

    }
    // constructor
        public AttendanceDetail(int id, String subject, String _percentage,
                String _attended, String _total) {
            this._id = id;
            this._subject = subject;
            this._percentage = _percentage;
            this._attended = _attended;
            this._total = _total;
        }
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

        // getting attended
        public String getAttended() {
            return _attended;
        }

        // setting attended
        public void setAttended(String attended) {
            this._attended = attended;
        }

        // getting percentage
        public String getPercentage() {
            return _percentage;
        }

        // setting percentage
        public void setPercentage(String percentage) {
            this._percentage = percentage;
        }

        // getting total
        public String getTotal() {
            return _total;
        }

        // setting total
        public void setTotal(String total) {
            this._total = total;
        }
    

}