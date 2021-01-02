package com.example.scorekeepingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDataDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TeamScoreEntry.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AppDataRepo.TeamScoreEntry.TABLE_NAME + " (" +
                    AppDataRepo.TeamScoreEntry._ID + " INTEGER PRIMARY KEY," +
                    AppDataRepo.TeamScoreEntry.COLUMN_NAME_TEAM_NAME1 + " TEXT," +
                    AppDataRepo.TeamScoreEntry.COLUMN_NAME_TEAM_NAME2 + " TEXT," +
                    AppDataRepo.TeamScoreEntry.COLUMN_NAME_SCORE1 + " INT," +
                    AppDataRepo.TeamScoreEntry.COLUMN_NAME_SCORE2 + " INT," +
                    AppDataRepo.TeamScoreEntry.COLUMN_NAME_DATETIME + "DATETIME)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AppDataRepo.TeamScoreEntry.TABLE_NAME;

    public AppDataDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
