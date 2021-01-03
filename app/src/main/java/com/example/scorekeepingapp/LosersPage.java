package com.example.scorekeepingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LosersPage extends AppCompatActivity {
    AppDataDBHelper dbHelper = null;
    LoserTableAdapter LoserTableAdapter;
    List<LoserTableRow> LoserTableRow = new ArrayList<LoserTableRow>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losers_page);
        RecyclerView recyclerView = findViewById(R.id.rvScoreTable);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LoserTableAdapter = new LoserTableAdapter(this, LoserTableRow);
        recyclerView.setAdapter(LoserTableAdapter);
    }
    private Cursor readFromDB(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                AppDataRepo.TeamScoreEntry.COLUMN_NAME_LOSER,
                AppDataRepo.TeamScoreEntry.COLUMN_NAME_LOSERSCORE,
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
    private void createDataRow(){
        Cursor cursor = readFromDB();
        cursor.moveToFirst();
        do {
            LoserTableRow loserTableRow = new LoserTableRow();
            loserTableRow.loser = cursor.getString(6);
            loserTableRow.loserscore = cursor.getInt(6);

            LoserTableRow.add((com.example.scorekeepingapp.LoserTableRow) LoserTableRow);
        } while (cursor.moveToNext());
        cursor.close();
    }
}