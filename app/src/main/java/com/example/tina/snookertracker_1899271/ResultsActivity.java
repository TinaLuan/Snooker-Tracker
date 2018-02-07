package com.example.tina.snookertracker_1899271;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class ResultsActivity extends AppCompatActivity {

    public static final String TEAM_NAME = "teamname";
    public static final String PLAYER_NAMES = "playernames";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout( (int)(width*.7), (int)(height*.5) );


        Intent intent = getIntent();
        String teamName = intent.getStringExtra(TEAM_NAME);
        String playerNames = intent.getStringExtra(PLAYER_NAMES);
        System.out.println("win: " + teamName + " " + playerNames);
    }
}
