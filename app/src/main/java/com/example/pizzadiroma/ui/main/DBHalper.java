package com.example.pizzadiroma.ui.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHalper extends SQLiteOpenHelper {

    private static final String DBLOG = "LOG_DB";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products_db";
    public static final String TABLE_NAME = "products_table";


    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_TYPE = "type";
    public static final String KEY_IMAGEURL = "imageUrl";
    public static final String KEY_PRICE = "price";

    private static final String CREATE_QUERY =
            "CREATE TABLE "+ TABLE_NAME +" ( "
                    + KEY_ID + " INTEGER PRIMARY KEY, "
                    + KEY_NAME +" TEXT, "
                    + KEY_DESCRIPTION +" TEXT, "
                    + KEY_WEIGHT + " TEXT, "
                    + KEY_TYPE + " TEXT, "
                    + KEY_PRICE + " TEXT, "
                    + KEY_IMAGEURL +" TEXT);";


    public DBHalper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(DBLOG, "Database created or opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DBLOG, "Database created...");
        db.execSQL(CREATE_QUERY);

    }


    public void addInformation( String _id, String name, String description, String weight, String type, String imageUrl, String price, SQLiteDatabase db) {
        Log.d(DBLOG, "Start One row inserted ...");
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, _id);
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_DESCRIPTION, description);
        contentValues.put(KEY_WEIGHT, weight);
        contentValues.put(KEY_TYPE, type);
        contentValues.put(KEY_IMAGEURL, imageUrl);
        contentValues.put(KEY_PRICE, price);
        db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        Log.d(DBLOG, "One row inserted ...");

    }

    public void getInformations(ContentValues contentValues, SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {KEY_ID,KEY_NAME,KEY_DESCRIPTION,KEY_WEIGHT,KEY_TYPE,KEY_IMAGEURL, KEY_PRICE};
        cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);

    }
}
