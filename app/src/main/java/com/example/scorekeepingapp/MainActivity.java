package com.example.scorekeepingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button newgamebutton = findViewById(R.id.button);
        final Button previousgamebutton = findViewById(R.id.button2);
        final Button losergamebutton = findViewById(R.id.button4);
        newgamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), NewGameActivity.class);
                startActivity(intent);
            }
        });
        previousgamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PreviousGames.class);
                startActivity(intent);
            }
        });
        losergamebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LosersPage.class);
                startActivity(intent);
            }
        });

    }
}