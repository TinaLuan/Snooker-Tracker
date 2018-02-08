package com.example.tina.snookertracker_1899271;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    public static final String TEAM_NAME = "teamname";
    public static final String PLAYER_NAMES = "playernames";
    public static final String WINNER_TEAM_TEXT = "Winner Team: ";
    public static final String WINNER_PLAYERS_TEXT = "Player(s) with highest score: ";
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

        ((TextView)findViewById(R.id.winnerTeamDisplay)).setText(WINNER_TEAM_TEXT+teamName);
        ((TextView)findViewById(R.id.winnerPlayersDisplay)).setText(WINNER_PLAYERS_TEXT+playerNames);
    }
}
