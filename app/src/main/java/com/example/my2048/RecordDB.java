package com.example.my2048;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class RecordDB {

    public static final String DATABASE_NAME = "main.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "records";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SCORE = "score";

    private SQLiteDatabase  database;

    public RecordDB(Context context) {
        OpenHelper openHelper = new OpenHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public long insert(int score){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SCORE, score);
        return database.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Record> selectAll(){
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Record> records = new ArrayList<>();

        cursor.moveToFirst();

        if (cursor.isAfterLast()){
            return records;
        }

        while (!cursor.isAfterLast()){
            long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
            int score = cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE));

            records.add(new Record(id, score));
            cursor.moveToNext();
        }

        return records;
    }

    static class OpenHelper extends SQLiteOpenHelper{
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SCORE +" INT);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
