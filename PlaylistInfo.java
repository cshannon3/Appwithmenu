package com.ratingrocker.appwithmenu;


public class PlaylistInfo {
    private int _id;
    private int _avgval;
    private int _stdev;
    private int _songleng;
    private String _playlistid;
    private String _playlistname;
    private int _gval;

    public PlaylistInfo() {

    }

    public PlaylistInfo(int _avgval, int _stdev, int _songleng, String _playlistid, String _playlistname, int _gval) {
        this._avgval = _avgval;
        this._stdev = _stdev;
        this._songleng = _songleng;
        this._playlistid = _playlistid;
        this._playlistname = _playlistname;
        this._gval = _gval;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_avgval() {
        return _avgval;
    }

    public void set_avgval(int _avgval) {
        this._avgval = _avgval;
    }

    public int get_stdev() {
        return _stdev;
    }

    public void set_stdev(int _stdev) {
        this._stdev = _stdev;
    }

    public int get_songleng() {
        return _songleng;
    }

    public void set_songleng(int _songleng) {
        this._songleng = _songleng;
    }

    public String get_playlistid() {
        return _playlistid;
    }

    public void set_playlistid(String _playlistid) {
        this._playlistid = _playlistid;
    }

    public String get_playlistname() {
        return _playlistname;
    }

    public void set_playlistname(String _playlistname) {
        this._playlistname = _playlistname;
    }

    public int get_gval() {
        return _gval;
    }

    public void set_gval(int _gval) {
        this._gval = _gval;
    }
}

