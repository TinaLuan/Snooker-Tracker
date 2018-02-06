package com.example.tina.snookertracker_1899271;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String NAME1 = "name1";
    public static final String NAME2 = "name2";
    public static final String NAME3 = "name3";
    public static final String NAME4 = "name4";

    public static final int FOUL_PENALTY = 4;

    private int activeScoreId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String name1 = intent.getStringExtra(NAME1);System.out.println("M|"+name1+"|");
        String name2 = intent.getStringExtra(NAME2);
        String name3 = intent.getStringExtra(NAME3);
        String name4 = intent.getStringExtra(NAME4);
        ((TextView)findViewById(R.id.nameDisplay1)).setText(name1);
        ((TextView)findViewById(R.id.nameDisplay2)).setText(name2);
        ((TextView)findViewById(R.id.nameDisplay3)).setText(name3);
        ((TextView)findViewById(R.id.nameDisplay4)).setText(name4);

        //System.out.println(name1 + "  " + name2 + name3 + name4);
        activeScoreId = R.id.score1;

    }

    // unfinished. could have overloading
    private void setTeamScore(int scoreId) {
        // Set team sore for team 1
        int score1 = Integer.parseInt( ((TextView)findViewById(R.id.score1)).getText().toString() );
        int score2 = Integer.parseInt( ((TextView)findViewById(R.id.score2)).getText().toString() );
        ((TextView)findViewById(R.id.teamScore1)).setText(Integer.toString(score1 + score2));
    }

    private void setPlayerScore(int scoreId, int score) {
        System.out.println(" id: "+ scoreId + " score: " + score);

        ((TextView)findViewById(scoreId)).setText(Integer.toString(score));
    }

    private void updatePlayerScore(int scoreId, int increment) {
        System.out.println(" id: "+ scoreId + " score: " + increment);

        ((TextView)findViewById(scoreId)).setText( Integer.toString(getPlayerScore(activeScoreId) + increment) );

        updateTeamScore(scoreId, increment);
    }

    private int getPlayerScore(int scoreId) {
        return Integer.parseInt( ((TextView)findViewById(scoreId)).getText().toString() );
    }

    private int getTeamScore(int teamScoreId) {
        return Integer.parseInt( ((TextView)findViewById(teamScoreId)).getText().toString() );
    }

    private void updateTeamScore(int playerScoreId, int increment) {
        int teamScoreId = R.id.teamScore1;;
        switch(playerScoreId) {
            case R.id.score1:
            case R.id.score2:
                teamScoreId = R.id.teamScore1;
                break;
            case R.id.score3:
            case R.id.score4:
                teamScoreId = R.id.teamScore2;
                break;
            default:
                break;
        }

        ((TextView)findViewById(teamScoreId)).setText( Integer.toString(getTeamScore(teamScoreId) + increment) );
    }

    private void switchActivePlayer() {
        switch (activeScoreId) {
            case R.id.score1:
                activeScoreId = R.id.score3;
                System.out.println("switch from 1" + " to 3");
                break;
            case R.id.score2:
                activeScoreId = R.id.score4;
                System.out.println("switch from 2" + " to 4");
                break;
            case R.id.score3:
                activeScoreId = R.id.score2;
                System.out.println("switch from 3" + " to 2");
                break;
            case R.id.score4:
                activeScoreId = R.id.score1;
                System.out.println("switch from 4" + " to 1");
                break;
            default:
                break;
        }

    }

    public void onBall(View view) {
        System.out.println("Ball----");
        int ballPoints = Integer.parseInt( ((Button)view).getText().toString() );

        updatePlayerScore(activeScoreId, ballPoints);

    }

    public void onToggle(View view) {
        System.out.println("toggle-----");
        switchActivePlayer();
    }

    public void onFoul(View view) {
        System.out.println("FOUL-----");
        switchActivePlayer();
        updatePlayerScore(activeScoreId, FOUL_PENALTY);

    }

    public void onEnd(View view) {
        System.out.println("END-----");
    }

}
