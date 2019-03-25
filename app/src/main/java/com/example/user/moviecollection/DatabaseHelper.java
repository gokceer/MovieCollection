package com.example.user.moviecollection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MovieCollection";

    // Table Names
    private static final String TABLE_MOVIES= "Movies";

    private static final String KEY_ID = "id";

    private static final String KEY_TITLE = "title";
    private static final String KEY_YEAR = "year";
    private static final String KEY_DIRECTOR = "director";
    private static final String KEY_CATEGORY= "category";

    private static final String CREATE_TABLE_MOVIES = "CREATE TABLE "
            + TABLE_MOVIES + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE
            + " TEXT," + KEY_YEAR + " TEXT," + KEY_DIRECTOR
            + " TEXT," + KEY_CATEGORY + " TEXT" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

        onCreate(db);
    }

    /*********************MOVIE METHODS************************/

    public long createMovie(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_YEAR, movie.getYear());
        values.put(KEY_DIRECTOR, movie.getDirector());
        values.put(KEY_CATEGORY, movie.getCategory());

        //insert row
        long movie_id = db.insert(TABLE_MOVIES,null, values);

        return movie_id;
    }

    public List<Movie> getAllMovie() {
        List<Movie> movies = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Movie mov = new Movie();
                mov.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                mov.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
                mov.setYear(c.getString(c.getColumnIndex(KEY_YEAR)));
                mov.setDirector(c.getString(c.getColumnIndex(KEY_DIRECTOR)));
                mov.setCategory(c.getString(c.getColumnIndex(KEY_CATEGORY)));

                // adding to tarif list
                movies.add(mov);
            } while (c.moveToNext());
        }

        return movies;
    }

    public int updateMovie (Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_YEAR, movie.getYear());
        values.put(KEY_DIRECTOR, movie.getDirector());
        values.put(KEY_CATEGORY, movie.getCategory());

        // updating row
        return db.update(TABLE_MOVIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(movie.getId()) });
    }

    public void deleteMovie(long movie_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, KEY_ID + " = ?",
                new String[] { String.valueOf(movie_id) });
    }

    /*
     * get single todo
     */
    public Movie getMovie(long movie_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES + " WHERE "
                + KEY_ID + " = " + movie_id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Movie mov = new Movie();
        mov.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        mov.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
        mov.setYear(c.getString(c.getColumnIndex(KEY_YEAR)));
        mov.setDirector(c.getString(c.getColumnIndex(KEY_DIRECTOR)));
        mov.setCategory(c.getString(c.getColumnIndex(KEY_CATEGORY)));

        return mov;
    }
}
