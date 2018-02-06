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

    private int activeScoreId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String name1 = intent.getStringExtra(NAME1);
        String name2 = intent.getStringExtra(NAME2);
        String name3 = intent.getStringExtra(NAME3);
        String name4 = intent.getStringExtra(NAME4);
        ((TextView)findViewById(R.id.nameDisplay1)).setText(name1);
        ((TextView)findViewById(R.id.nameDisplay2)).setText(name2);
        ((TextView)findViewById(R.id.nameDisplay3)).setText(name3);
        ((TextView)findViewById(R.id.nameDisplay4)).setText(name4);

        //System.out.println(name1 + "  " + name2 + name3 + name4);
        activeScoreId = R.id.score3;

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

    private int getPlayerScore(int scoreId) {
        return Integer.parseInt( ((TextView)findViewById(scoreId)).getText().toString() );
    }
    public void onBalls(View view) {
        int ballPoints = Integer.parseInt( ((Button)view).getText().toString() );

        setPlayerScore(activeScoreId, ballPoints + getPlayerScore(activeScoreId));

    }

}
