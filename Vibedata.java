package com.ratingrocker.appwithmenu;




public class Vibedata {

    private int _id;
    private int _ratingval;
    private int _ratecount;
    private int _freshvalue;
    private String _songid;
    private String _songname;
    private int _gval;


    public Vibedata() {

    }

    public Vibedata(int ratingval, String songid, String songname, int ratecount, int freshvalue, int gval ) {

        this._ratingval = ratingval;
        this._songid = songid;
        this._songname = songname;
        this._ratecount = ratecount;
        this._freshvalue = freshvalue;
        this._gval = gval;

    }
    /*
        public Vibeonedata(int _id, String _songid, String _songname, int _ratingval, int _ratecount, int _freshvalue) {
            this._id = _id;
            this._songid = _songid;
            this._ratingval = _ratingval;
            this._ratecount = _ratecount;
            this._freshvalue = _freshvalue;
            this._songname = _songname;
        }
    */

    public int get_gval() {
        return _gval;
    }

    public void set_gval(int _gval) {
        this._gval = _gval;
    }

    public String get_songname() {
        return _songname;
    }

    public void set_songname(String _songname) {
        this._songname = _songname;
    }

    public String get_songid() {
        return _songid;
    }

    public void set_songid(String _songid) {
        this._songid = _songid;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_ratingval() {
        return _ratingval;
    }

    public void set_ratingval(int _ratingval) {
        this._ratingval = _ratingval;
    }

    public int get_ratecount() {
        return _ratecount;
    }

    public void set_ratecount(int _ratecount) {
        this._ratecount = _ratecount;
    }

    public int get_freshvalue() {
        return _freshvalue;
    }

    public void set_freshvalue(int _freshvalue) {
        this._freshvalue = _freshvalue;
    }
}



