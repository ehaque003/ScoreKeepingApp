package com.example.scorekeepingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PreviousGames extends AppCompatActivity {

    AppDataDBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_games);
        TableLayout scoreTable = (TableLayout)findViewById(R.id.scoretable);
        dbHelper = new AppDataDBHelper(getBaseContext());
        Cursor cursor = readFromDB();
        cursor.moveToFirst();
        do {
           View tableRow = LayoutInflater.from(this).inflate(R.layout.table_layout,null,false);
            TextView name  = (TextView) scoreTable.findViewById(R.id.name1);
            TextView title  = (TextView) scoreTable.findViewById(R.id.score1);
            name.setText(cursor.getString(0));
            title.setText(cursor.getInt(1)+"");
            scoreTable.addView(scoreTable);
//            Toast toast = Toast.makeText(getApplicationContext(), cursor.getString(0) + " : "+cursor.getInt(1)+"", Toast.LENGTH_SHORT);
//            toast.show();

        } while (cursor.moveToNext());
        cursor.close();

    }

    private Cursor readFromDB(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                AppDataRepo.TeamScoreEntry.COLUMN_NAME_TEAM_NAME1,
                AppDataRepo.TeamScoreEntry.COLUMN_NAME_SCORE1,
                AppDataRepo.TeamScoreEntry.COLUMN_NAME_TEAM_NAME2,
                AppDataRepo.TeamScoreEntry.COLUMN_NAME_SCORE2,
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                AppDataRepo.TeamScoreEntry._ID + " DESC";

        Cursor cursor = db.query(
                AppDataRepo.TeamScoreEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        return cursor;
    }
}