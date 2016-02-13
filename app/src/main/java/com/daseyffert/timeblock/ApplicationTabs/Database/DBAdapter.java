package com.daseyffert.timeblock.ApplicationTabs.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.daseyffert.timeblock.ApplicationTabs.Apps.Applications;
import com.daseyffert.timeblock.ApplicationTabs.List.NotesItem;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Daniel on 2/10/2016.
 */
public class DBAdapter {

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "TimeBlockDB.db";
    private static final int DATABASE_VERSION = 1;

    /*-------------------- To Do List Table -------------------*/
    private static final String TABLE_NAME_TODO = "ToDoList";
    private static final String COL_ID_TODO = "_id";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_DATE = "date";

    /*-------------------- Apps List Table -------------------*/
    private static final String TABLE_NAME_APPS = "AppsList";
    private static final String COL_ID_APPS = "_id";
    private static final String COL_APP_NAME = "app_name";
    //private static final String COL_DATE = "DATE";

    /*-------------------- Create Table Strings -------------------*/
    private static final String TODO_CREATE = "create table " + TABLE_NAME_TODO + "( " +
            COL_ID_TODO + " int primary key, " +
            COL_DESCRIPTION + " text, " +
            COL_DATE + " date );";
    private static final String APPS_CREATE = "create table " + TABLE_NAME_APPS + "( " +
            COL_ID_APPS + " int primary key, " +
            COL_APP_NAME + " text );";

    private final Context mContext;
    public DatabaseHelper mDatabaseHelper;

    public DBAdapter(Context context) {
        mContext = context;
        mDatabaseHelper = new DatabaseHelper(mContext);
    }

    /**
     * Helper Creates and Upgrades two different Table
     */
    public static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * Called during Creation of Database
         */
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(TODO_CREATE);
            sqLiteDatabase.execSQL(APPS_CREATE);
        }

        /**
         * Called during upgrading Database
         */
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.w(TAG, "Upgrading database form version " + i + " to " + i1);

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TODO);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_APPS);

            onCreate(sqLiteDatabase);
        }
    }

    /**
     * Insert new Task into Database
     * @return if successful true else false
     */
    public boolean insertTask(NotesItem mNotesItem) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_TODO, mNotesItem.getId().toString());
        contentValues.put(COL_DESCRIPTION, mNotesItem.getDescription());
        contentValues.put(COL_DATE, formattedDate(mNotesItem.getDate()));

        long result = db.insert(TABLE_NAME_TODO, null, contentValues);
        db.close();
        if(result == -1)
            return false;
        else {
            Log.d("DATABASE OPERATION", "New user added");
            return true;
        }
    }

    /**
     * Insert new Application to Database
     * @return true if successful else false
     */
    public boolean insertApplication(Applications mApp) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_APPS, mApp.getId().toString());
        contentValues.put(COL_APP_NAME, mApp.getApplicationLabel());

        long result = db.insert(TABLE_NAME_APPS, null, contentValues);
        db.close();
        if(result == -1)
            return false;
        else {
            Log.d("DATABASE OPERATION", "New application added:" + mApp.getApplicationLabel());
            return true;
        }
    }

    /**
     * @return all data stored in TO-DO table from the database
     */
    public Cursor storedTasks(SQLiteDatabase db) {
        Cursor cursor;
        String query = "Select * FROM " + TABLE_NAME_TODO;

        cursor = db.rawQuery(query, null);
        return cursor;
    }

    /**
     * @return all data stored in Applications table from the database
     */
    public Cursor storedApplications(SQLiteDatabase db) {
        Cursor cursor;
        String query = "Select * FROM " + TABLE_NAME_APPS;

        cursor = db.rawQuery(query, null);
        return cursor;
    }

    /**
     * Delete Tasks from Database
     * @return True if successful, else false
     */
    public boolean deleteTask(NotesItem mNotesItem) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        //delete method from SQLite class
        boolean result = db.delete(TABLE_NAME_TODO, COL_ID_TODO + " = ?",
                new String[] { String.valueOf(mNotesItem.getId())}) > 0;

        db.close();
        return result;
    }

    public boolean deleteApplication(Applications mApp) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        boolean result = db.delete(TABLE_NAME_APPS, COL_ID_APPS + " = ?",
                new String[] { String.valueOf(mApp.getId())}) > 0;

        db.close();
        return result;
    }

    /**
     * Format Date to dd/mm/yyyy from whatever date given
     * Copied from NoteHolder class
     */
    private String formattedDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String formatD = format.format(date);
        return formatD;
    }

}
