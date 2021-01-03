package com.example.scorekeepingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PreviousGames extends AppCompatActivity {

    AppDataDBHelper dbHelper = null;
    ScoreTableAdapter scoreTableAdapter;
    List<ScoreTableRow> scoreTableRows = new ArrayList<ScoreTableRow>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_games);
        dbHelper = new AppDataDBHelper(getBaseContext());
        createDataRow();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvScoreTable);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scoreTableAdapter = new ScoreTableAdapter(this, scoreTableRows);
//        scoreTableAdapter.setClickListener((ScoreTableAdapter.ItemClickListener) this);
        recyclerView.setAdapter(scoreTableAdapter);
    }

    private Cursor readFromDB(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                AppDataRepo.TeamScoreEntry._ID,
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

    private void createDataRow(){
        Cursor cursor = readFromDB();
        cursor.moveToFirst();
        do {
            ScoreTableRow scoreTableRow = new ScoreTableRow();
            scoreTableRow.name1 = cursor.getString(1);
            scoreTableRow.score1 = cursor.getInt(2);
            scoreTableRow.name2 = cursor.getString(3);
            scoreTableRow.score2 = cursor.getInt(4);

            scoreTableRows.add(scoreTableRow);
        } while (cursor.moveToNext());
        cursor.close();
    }
}