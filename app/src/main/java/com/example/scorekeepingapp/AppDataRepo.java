package com.example.scorekeepingapp;

import android.provider.BaseColumns;

public class AppDataRepo {
    private AppDataRepo() {}

    public static  class  TeamScoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "TEAM_SCORE";
        public static final String COLUMN_NAME_TEAM_NAME1 = "TeamName1";
        public static final String COLUMN_NAME_TEAM_NAME2 = "TeamName2";
        public static final String COLUMN_NAME_SCORE1 = "Score1";
        public static final String COLUMN_NAME_SCORE2 = "Score2";
        public static final String COLUMN_NAME_DATETIME = "DateAndTime";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TeamScoreEntry.TABLE_NAME + " (" +
                    TeamScoreEntry._ID + " INTEGER PRIMARY KEY," +
                    TeamScoreEntry.COLUMN_NAME_TEAM_NAME1 + " TEXT," +
                    TeamScoreEntry.COLUMN_NAME_TEAM_NAME2 + " TEXT," +
                    TeamScoreEntry.COLUMN_NAME_SCORE1 + " INT" +
                    TeamScoreEntry.COLUMN_NAME_SCORE2 + " INT" +
                    TeamScoreEntry.COLUMN_NAME_DATETIME + " DATETIME" +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TeamScoreEntry.TABLE_NAME;

}