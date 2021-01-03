package com.example.scorekeepingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewGameActivity extends AppCompatActivity {

    private long newGameId;
    AppDataDBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);

        dbHelper = new AppDataDBHelper(getBaseContext());

        final Button addpointteam1 = findViewById(R.id.addpointteam1);
        final Button addpointteam2 = findViewById(R.id.addpointteam2);
        final EditText edittext1 = findViewById(R.id.simpleEditText);
        final EditText edittext2 = findViewById(R.id.simpleEditText2);
        final Button subpoint1 = findViewById(R.id.subpoint1);
        final Button subpoint2 = findViewById(R.id.subpoint2);
        final Button reset = findViewById(R.id.resetbutton);
        final Button backtohomepage = findViewById(R.id.backtohomepage);
        TextView pointviewteam1 = findViewById(R.id.textView3);
        TextView pointviewteam2 = findViewById(R.id.textView4);
        final int[] team1point = new int[1];
        final int[] team2point = new int[1];
        edittext1.setText("Team 1");
        edittext2.setText("Team 2");
        edittext1.setHint("Team 1 Name");
        edittext2.setHint("Team 2 Name");

        addpointteam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                team1point[0]=team1point[0]+1;
                pointviewteam1.setText(team1point[0]+"");
                updateTeam1Info(edittext1.getText().toString(), team1point[0]);
                if(team1point[0] == 29) {
                    Intent intent = new Intent(getBaseContext(), WinPage.class);
                    startActivity(intent);
                    updateLoserTeamInfo(edittext2.getText().toString(), team2point[0]);
                }
            }
        });
        addpointteam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                team2point[0] = team2point[0] +1;
                pointviewteam2.setText(team2point[0]+"");
                updateTeam2Info(edittext1.getText().toString(), team2point[0]);
                if(team2point[0] == 29) {
                    Intent intent = new Intent(getBaseContext(), WinPage.class);
                    startActivity(intent);
                    updateLoserTeamInfo(edittext1.getText().toString(), team1point[0]);

                }
            }
        });
        subpoint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                team1point[0] = team1point[0]-1;
                pointviewteam1.setText(team1point[0]+"");
                updateTeam1Info(edittext1.getText().toString(), team1point[0]);
            }
        });
        subpoint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                team2point[0] = team2point[0]-1;
                pointviewteam2.setText(team2point[0]+"");
                updateTeam1Info(edittext2.getText().toString(), team2point[0]);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                long score1 = readFromDB();
//                Toast toast = Toast.makeText(getApplicationContext(), score1+"", Toast.LENGTH_SHORT);
//                toast.show();

                team1point[0]=0;
                team2point[0]=0;
                pointviewteam1.setText("0");
                pointviewteam2.setText("0");
                updateTeam1Info(edittext1.getText().toString(), team1point[0]);
                updateTeam1Info(edittext2.getText().toString(), team2point[0]);
            }
        });
        backtohomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        writeToDb(edittext1.getText().toString(), edittext2.getText().toString());
    }

    private void writeToDb(String teamname1, String teamname2) {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_TEAM_NAME1, teamname1);
        values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_TEAM_NAME2, teamname2);
        values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_SCORE1, 0);
        values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_SCORE2, 0);
        values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_LOSERSCORE, 0);
        values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_LOSER, "");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(AppDataRepo.TeamScoreEntry.TABLE_NAME, null, values);
        newGameId = newRowId;
    }

    public void updateTeam1Info(String teamName, int value) {
        updateDb(1, teamName, value);
    }
    public void updateTeam2Info(String teamName, int value) {
        updateDb(2, teamName, value);
    }
    public void updateLoserTeamInfo(String teamName, int value){
        updateDb(3, teamName, value);
    }
    private void updateDb( int team1, String teamname, int value) {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(team1==1) {
            values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_TEAM_NAME1, teamname);
            values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_SCORE1, value);
        } else if (team1==2)
        {
            values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_TEAM_NAME2, teamname);
            values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_SCORE2, value);
        } else
        {
            values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_LOSER, teamname);
            values.put(AppDataRepo.TeamScoreEntry.COLUMN_NAME_LOSERSCORE, value);
        }
        db.update(AppDataRepo.TeamScoreEntry.TABLE_NAME, values, "_id = ?", new String[]{String.valueOf(newGameId)});
    }

}