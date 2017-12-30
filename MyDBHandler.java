package com.ratingrocker.appwithmenu;


//TODO Create Tables for vibe 2 and 3...maybe 4/ manyby make 1 solo
// TODO Create table for playlists
//TODO create table for vibe playlist average/ size/ st dev




        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;
//import android.util.Log;

        import java.util.ArrayList;
        import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "songdata.db";
    // Table Names
    //public static final String TABLE_MAIN = "main";
    //public static final String TABLE_GENRES = "genres";
    private static final String TABLE_VIBE1 = "vibe1";
    private static final String TABLE_VIBE2 = "vibetwo";
    private static final String TABLE_VIBE3 = "vibethree";
    private static final String TABLE_PLAYLIST_INFO = "playlistinfo";
    // Common Column Names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FRESHVAL = "freshval";
    private static final String COLUMN_RATINGVAL = "ratingval";
    private static final String COLUMN_RATECOUNT = "ratecount";
    private static final String COLUMN_SONGNAME = "songname";
    private static final String COLUMN_GVAL = "gval";

    // Playlist info
    private static final String COLUMN_PLAYLIST_NAME = "playlistname";
    private static final String COLUMN_PLAYLIST_ID = "playlist_id";
    private static final String COLUMN_PLAYLIST_SIZE = "playlist_size";
    private static final String COLUMN_PLAYLIST_AVG = "p_avg";
    private static final String COLUMN_PLAYLIST_STDEV = "p_stdev";

    // SONG Data Table MAIN - column names
    private static final String COLUMN_SONGID = "songid";
    //private static final String KEY_VIBEONE_ID = "vibeone_id";
    //private static final String KEY_VIBETWO_ID = "vibetwo_id";
    //private static final String KEY_VIBETHREE_ID = "vibethree_id";



    public MyDBHandler(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE "
                + TABLE_VIBE1 + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_RATINGVAL + " INTEGER,"
                + COLUMN_SONGID + " TEXT,"
                + COLUMN_SONGNAME + " TEXT,"
                + COLUMN_RATECOUNT + " INTEGER,"
                + COLUMN_FRESHVAL + " INTEGER,"
                + COLUMN_GVAL + " INTEGER" + ");";

        String query2 = "CREATE TABLE "
                + TABLE_VIBE2 + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_RATINGVAL + " INTEGER,"
                + COLUMN_SONGID + " TEXT,"
                + COLUMN_SONGNAME + " TEXT,"
                + COLUMN_RATECOUNT + " INTEGER,"
                + COLUMN_FRESHVAL + " INTEGER,"
                + COLUMN_GVAL + " INTEGER" + ");";

        String query3 = "CREATE TABLE "
                + TABLE_VIBE3 + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_RATINGVAL + " INTEGER,"
                + COLUMN_SONGID + " TEXT,"
                + COLUMN_SONGNAME + " TEXT,"
                + COLUMN_RATECOUNT + " INTEGER,"
                + COLUMN_FRESHVAL + " INTEGER,"
                + COLUMN_GVAL + " INTEGER" + ");";

        String query4 = "CREATE TABLE " +
                TABLE_PLAYLIST_INFO + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PLAYLIST_ID + " TEXT,"
                + COLUMN_PLAYLIST_NAME + " TEXT,"
                + COLUMN_PLAYLIST_SIZE + " INTEGER,"
                + COLUMN_PLAYLIST_AVG + " INTEGER,"
                + COLUMN_PLAYLIST_STDEV + " INTEGER,"
                + COLUMN_GVAL + " INTEGER" + ");";


        // creating required tables
        //db.execSQL(CREATE_TABLE_GENRES);
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        // db.execSQL(CREATE_TABLE_VIBETWO);
        //db.execSQL(CREATE_TABLE_VIBETHREE);
        //db.execSQL(CREATE_TABLE_MAIN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENRES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIBE1);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIBE2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIBE3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYLIST_INFO);

        // create new tables
        onCreate(db);
    }


//Todo make list actually ordered
// for now just going to add songs to bottom so i can test sooner


    /*public long createGenre(Genre genres) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONGID,genres.get_song_id());
        //long genre_id = db.insert(T)
        values.put(COLUMN_G_ONE_VAL,genres.get_genreonevalue());
        values.put(COLUMN_G_TWO_VAL,genres.get_genretwovalue());
        values.put(COLUMN_G_THREE_VAL,genres.get_genrethreevalue());
        values.put(COLUMN_G_FOUR_VAL,genres.get_genrefourvalue());
        values.put(COLUMN_G_FIVE_VAL,genres.get_genrefivevalue());
        long genre_id = db.insert(TABLE_VIBETHREE, null, values);
        return genre_id;
    }
    */
    public void addVibeonesong(Vibedata vibeone, int tablenum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RATINGVAL, vibeone.get_ratingval());
        values.put(COLUMN_SONGID, vibeone.get_songid());
        values.put(COLUMN_SONGNAME, vibeone.get_songname());
        values.put(COLUMN_RATECOUNT, vibeone.get_ratecount());
        values.put(COLUMN_FRESHVAL, vibeone.get_freshvalue());
        values.put(COLUMN_GVAL, vibeone.get_gval());
        if (tablenum == 1) {
            db.insert(TABLE_VIBE1, null, values);
        } else if (tablenum == 2) {
            db.insert(TABLE_VIBE2, null, values);
        } else {
            db.insert(TABLE_VIBE3, null, values);

            //return vibeone_id;
        }
    }

    public void deleteVibeonesong(String song_id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VIBE1 + " WHERE " + COLUMN_SONGID + "=\"" + song_id + "\";");
    }
    public void deletetable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_VIBE1);
    }
    public void databaseToString(int tablenum) {
        //String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String tableq = TABLE_VIBE1;
        if (tablenum ==2){
            tableq = TABLE_VIBE2;
        }else if (tablenum == 3){
            tableq = TABLE_VIBE3;
        }
        String query = "Select * from " + tableq;

        Cursor c = db.rawQuery(query, null);
        Log.e("TAG", "Database1");
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                int ratingval = c.getInt(1);
                String songid = c.getString(2);
                String songname = c.getString(3);
                int ratecount = c.getInt(4);
                Log.e("ID", String.valueOf(id));
                Log.e("Rating", String.valueOf(ratingval));
                Log.e("Songid", songid);
                Log.e("Song Name", songname);
                Log.e("Rate Count", String.valueOf(ratecount));

            } while (c.moveToNext());
        }
        db.close();
    }

    /*
 * getting all todos
 * */

    public List<Vibedata> getAllSongs() {
        List<Vibedata> songs = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_VIBE1;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Vibedata song = new Vibedata();
                song.set_songid(c.getString((c.getColumnIndex(COLUMN_SONGID))));
                song.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
                song.set_ratingval(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL)));
                song.set_ratecount(c.getInt(c.getColumnIndex(COLUMN_RATECOUNT)));
                song.set_freshvalue((c.getInt(c.getColumnIndex(COLUMN_FRESHVAL))));


                // adding to todo list
                songs.add(song);
            } while (c.moveToNext());
        }
        c.close();

        db.close();
        return songs;
    }
    // insert row - not sure if I need to give it an id cuz song id works

    /*/ assigning tags to todo
    /
    for (long tag_id : tag_ids) {
        createTodoTag(todo_id, tag_id);
    }
    *///TODO Make null case and better loop
    public void addnewrating( Vibedata song, int tablenum) {
        SQLiteDatabase db = getWritableDatabase();

        String tableq;
        if (tablenum ==2){
            tableq = TABLE_VIBE2;
        }else if (tablenum == 3){
            tableq = TABLE_VIBE3;
        } else {tableq = TABLE_VIBE1;}
        String selectquery = "Select * from " + tableq + " WHERE " + COLUMN_SONGID + "=\"" + song.get_songid() + "\";";
            Cursor c = db.rawQuery(selectquery, null);
            int p = 0;
            if (c.moveToFirst()) {
                p = 1;
                do {
                    int oldval = c.getInt(1);
                    int ratingcount = c.getInt(4);
                    int thisval = song.get_ratingval();
                    int newratingcount = ratingcount + 1;
                    int newval = (thisval + oldval * ratingcount) / (newratingcount);
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_RATINGVAL, newval);
                    values.put(COLUMN_SONGID, String.valueOf(song.get_songid()));
                    values.put(COLUMN_SONGNAME, String.valueOf(song.get_songname()));
                    values.put(COLUMN_RATECOUNT, newratingcount);
                    values.put(COLUMN_FRESHVAL, song.get_freshvalue());
                    values.put(COLUMN_GVAL, song.get_gval());
                    db.update(tableq, values, COLUMN_ID + " = ?",
                            new String[]{String.valueOf(c.getInt(c.getPosition()))});
                    c.close();
                    db.close();
                } while (c.moveToNext());
            }
            if (p != 1) {
                addVibeonesong(song, tablenum);
            }
            db.close();

    }



    public void getrequestlist(int tablenum, int rvalmin, int rvalmax, int fvalmin, int fvalmax, int gval){
        SQLiteDatabase db = getWritableDatabase();
        String tableq;
        if (tablenum ==2){
            tableq = TABLE_VIBE2;
            Log.e("Vibe", "Party");
        }else if (tablenum == 3){
            tableq = TABLE_VIBE3;
            Log.e("Vibe", "Study");
        } else {tableq = TABLE_VIBE1;
            Log.e("Vibe", "Chill");}
        //String selectquery = "Select * from " + TABLE_VIBEONE + " SQL ORDER BY " + COLUMN_RATINGVAL + " DESC ", new  {};
        Cursor c = db.query(tableq, null, null,
                null, null, null, COLUMN_RATINGVAL + " DESC", null);
        List<Integer> gtestlist = new ArrayList<>();
        gtestlist.add(3); gtestlist.add(5); gtestlist.add(7); gtestlist.add(11);gtestlist.add(13);
        Log.e("GList", String.valueOf(gtestlist));
        try {
            while (c.moveToNext()) {
                if ((rvalmin < c.getInt(1)) && (c.getInt(1) < rvalmax) && (fvalmin < c.getInt(5)) && (c.getInt(5) < fvalmax)) {
                    int i = 0;

                    while (i<5){
                        if ((gval%gtestlist.get(i)==0)&&(c.getInt(6)%gtestlist.get(i)==0)){
                            int id = c.getInt(0);
                            int ratingval = c.getInt(1);
                            String songid = c.getString(2);
                            String songname = c.getString(3);
                            int ratecount = c.getInt(4);
                            Log.e("ID", String.valueOf(id));
                            Log.e("Rating", String.valueOf(ratingval));
                            Log.e("Songid", songid);
                            Log.e("Song Name", songname);
                            Log.e("Rate Count", String.valueOf(ratecount));
                            i = 5;
                        }else {i +=1;}}
                }else {Log.e("Song not in rang", c.getString(3));}
            }
        } finally {
            c.close();
            db.close();
        }
    }

}

// c.close();
        /*
        Cursor tw = db.rawQuery(querytwo, null);
        Log.i("TAG", "Database2");
        if (c.moveToFirst()){
            do {
                int id = tw.getInt(0);
                int ratingval = tw.getInt(1);
                String songid = tw.getString(2);
                String songname = tw.getString(3);
                Log.e("ID", String.valueOf(id));
                Log.e("Rating", String.valueOf(ratingval));
                Log.e("Songid", songid);
                Log.e("Song Name", songname);

            } while( c.moveToNext());
        }
        tw.close();
        Cursor tr = db.rawQuery(querythree, null);
        Log.i("TAG", "Database3");
        if (c.moveToFirst()){
            do {
                int id = tr.getInt(0);
                int ratingval = tr.getInt(1);
                String songid = tr.getString(2);
                String songname = tr.getString(3);
                Log.e("ID", String.valueOf(id));
                Log.e("Rating", String.valueOf(ratingval));
                Log.e("Songid", songid);
                Log.e("Song Name", songname);

            } while( c.moveToNext());
        }
        tr.close();
        */
        /*while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("songname")) != null) {
                dbString += c.getString(c.getColumnIndex("songname"));
                dbString += "\n";
            }
        }*/

//c.close();
//db.close();
//return dbString;

//}
    /*
    public long createVibetwodata(Vibetwodata vibetwo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONGID, vibetwo.get_songid());
        values.put(COLUMN_RATINGVAL, vibetwo.get_ratingval());
        values.put(COLUMN_FRESHVAL, vibetwo.get_freshvalue());
        values.put(COLUMN_RATECOUNT, vibetwo.get_ratecount());
        long vibetwo_id= db.insert(TABLE_VIBETHREE, null, values);
        return vibetwo_id;
*/

/*
    }
    public long createVibethreedata(Vibethreedata vibethree) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONGID, vibethree.get_songid());
        values.put(COLUMN_RATINGVAL, vibethree.get_ratingval());
        values.put(COLUMN_FRESHVAL, vibethree.get_freshvalue());
        values.put(COLUMN_RATECOUNT, vibethree.get_ratecount());
        long vibethree_id = db.insert(TABLE_VIBETHREE, null, values);
        return vibethree_id;
    }*/
/*
    public Vibedata getVibeonedata(long song_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_VIBEONE + " WHERE "
                + COLUMN_SONGID + " = " + song_id;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        Vibedata td = new Vibedata();
        td.set_ratecount(c.getInt(c.getColumnIndex(COLUMN_RATECOUNT)));
        td.set_songname(c.getString(c.getColumnIndex(COLUMN_SONGNAME)));
        td.setratingval(c.getInt(c.getColumnIndex(COLUMN_RATINGVAL)));
        td.set_freshvalue(c.getInt(c.getColumnIndex(COLUMN_FRESHVAL)));
        return td;
    */