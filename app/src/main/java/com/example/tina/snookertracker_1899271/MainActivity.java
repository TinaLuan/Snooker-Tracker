package com.example.tina.snookertracker_1899271;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String NAME1 = "name1";
    public static final String NAME2 = "name2";
    public static final String NAME3 = "name3";
    public static final String NAME4 = "name4";

    public static final int FOUL_PENALTY = 4;
    public static final int TOTAL_NUM_RED_BALLS = 3;
    public static final int TIE = 0;
    public static final int INITIAL_SCORE = 0;

    private int activeScoreId;
    private int currentNumRed;
    private ArrayList<Integer> clrdBalls = new ArrayList<>();
    private HashMap<Integer, Integer> scoreIdToPlayerId = new HashMap<>();

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

        activeScoreId = R.id.score1;

        setupAllBalls();

        scoreIdToPlayerId.put(R.id.score1, R.id.nameDisplay1);
        scoreIdToPlayerId.put(R.id.score2, R.id.nameDisplay2);
        scoreIdToPlayerId.put(R.id.score3, R.id.nameDisplay3);
        scoreIdToPlayerId.put(R.id.score4, R.id.nameDisplay4);
    }

    private void setupAllBalls () {
        currentNumRed = TOTAL_NUM_RED_BALLS;
        clrdBalls.add(R.id.yellow);
        clrdBalls.add(R.id.green);
        clrdBalls.add(R.id.brown);
        clrdBalls.add(R.id.blue);
        clrdBalls.add(R.id.pink);
        clrdBalls.add(R.id.black);

        ((Button)findViewById(R.id.red)).setClickable(true);
        for (int ballId : clrdBalls) {
            ((Button)findViewById(ballId)).setClickable(false);
            //System.out.println(((Button)findViewById(ballId)).isClickable());
        }
    }

    // unfinished. could have overloading
    private void setTeamScore(int teamScoreId, int score) {
        // Set team sore for team 1
        /*int score1 = Integer.parseInt( ((TextView)findViewById(R.id.score1)).getText().toString() );
        int score2 = Integer.parseInt( ((TextView)findViewById(R.id.score2)).getText().toString() );
        ((TextView)findViewById(R.id.teamScore1)).setText(Integer.toString(score1 + score2));*/

        ((TextView)findViewById(teamScoreId)).setText(Integer.toString(score));
    }

    private void setPlayerScore(int playerScoreId, int score) {
        //System.out.println(" id: "+ scoreId + " score: " + score);

        ((TextView)findViewById(playerScoreId)).setText(Integer.toString(score));
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
                System.out.println("switch from 1 " + " to 3");
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

        // Each player should start with a red ball if possible
        if (currentNumRed > 0) {
            ((Button) findViewById(R.id.red)).setClickable(true);
            for (int ballId : clrdBalls) {
                ((Button)findViewById(ballId)).setClickable(false);
            }
        }
    }

    private int winnerTeamId() {
        if (getTeamScore(R.id.teamScore1) > getTeamScore(R.id.teamScore1))
            return R.id.teamName1;
        else if (getTeamScore(R.id.teamScore1) < getTeamScore(R.id.teamScore1))
            return R.id.teamName2;
        else
            return TIE;

    }

    private ArrayList<Integer> winnerPlayerScoreIds() {
        ArrayList<Integer> winnerPlayerScoreIds = new ArrayList<>();

        int max = Integer.MIN_VALUE;
        Iterator<Integer> ir = scoreIdToPlayerId.keySet().iterator();
        System.out.println(scoreIdToPlayerId.keySet().size());
        while (ir.hasNext()) {
            int currentId = ir.next();
            int currentScore = getPlayerScore(currentId);
            System.out.println(currentScore);
            if (currentScore > max) {
                max = currentScore;
                winnerPlayerScoreIds.clear();
                winnerPlayerScoreIds.add(currentId);
            } else if (currentScore == max) {
                winnerPlayerScoreIds.add(currentId);
            }
        }
        System.out.println(winnerPlayerScoreIds);
        return winnerPlayerScoreIds;

        //ArrayList<Integer> playerScoreIds =  new ArrayList<>(scoreIdToPlayerId.keySet());
//        for (int id : playerScoreIds) {
//            if (getPlayerScore(id) > max) {
//
//            }
//
//        }


        /*Collections.sort(playerScoreIds, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return 0;
            }
        });

        ArrayList<Integer> playerScores = new ArrayList<>(4);
        playerScores.add(getPlayerScore(R.id.score1));
        playerScores.add(getPlayerScore(R.id.score2));
        playerScores.add(getPlayerScore(R.id.score3));
        playerScores.add(getPlayerScore(R.id.score4));

        Collections.sort(playerScores, Collections.reverseOrder());

        int i=1;
        while (i < playerScores.size() && playerScores.get(i) == playerScores.get(i-1)){
            i++;
        }
        System.out.println(new ArrayList<Integer>(playerScores.subList(0, i)));
        return new ArrayList<Integer>(playerScores.subList(0, i));*/

    }


    public void reset() {
        setPlayerScore(R.id.score1, INITIAL_SCORE);
        setPlayerScore(R.id.score2, INITIAL_SCORE);
        setPlayerScore(R.id.score3, INITIAL_SCORE);
        setPlayerScore(R.id.score4, INITIAL_SCORE);
        setTeamScore(R.id.teamScore1, INITIAL_SCORE);
        setTeamScore(R.id.teamScore2, INITIAL_SCORE);

        activeScoreId = R.id.score1;

        setupAllBalls();
    }

    public void onBall(View view) {
        System.out.println("Ball----" + ((Button)view).getId());

        Button button = ((Button)view);
        button.setClickable(false);

        // Clicked on a red ball
        if (button.getId() == R.id.red && currentNumRed > 0) {

            currentNumRed--;

            if (currentNumRed == 0) {
                // Activate the first colour only
                for (int ballId : clrdBalls) {
                    ((Button) findViewById(ballId)).setClickable(false);
                }
                ((Button) findViewById(clrdBalls.get(0))).setClickable(true);

            } else {
                // Activate all colours
                for (int ballId : clrdBalls) {
                    ((Button) findViewById(ballId)).setClickable(true);
                }
            }
        // Clicked on any other-coloured ball
        } else {
            if (currentNumRed > 0) {
                // Activate all colours
                ((Button) findViewById(R.id.red)).setClickable(true);

                for (int ballId : clrdBalls) {
                    ((Button)findViewById(ballId)).setClickable(false);
                }
            } else {
                // Activate the next colour only
                int nextIndex = clrdBalls.indexOf((Integer) button.getId()) + 1;

                if (nextIndex < clrdBalls.size()) {
                    ((Button) findViewById(clrdBalls.get(nextIndex))).setClickable(true);
                } else {
                    // End of Game

                }

            }

        }
        int ballPoints = Integer.parseInt( button.getText().toString() );

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

        // Player(s) with highest scores
        String playerNames = "";
        //ArrayList<Integer> winnerPlayerIds = new ArrayList<>();
        for (int winnerScoreId : winnerPlayerScoreIds()) {
            //winnerPlayerIds.add( scoreIdToPlayerId.get(winnerScoreId) );
            int winnerPlayerId = scoreIdToPlayerId.get(winnerScoreId);
            playerNames += ((TextView)findViewById(winnerPlayerId)).getText().toString() + " ";
        }

        // Winner Team
        String teamName = "";
        if (winnerTeamId() == TIE) {
            teamName = "Nobody! It's a tie!";
        } else {
            teamName = ((TextView)findViewById(winnerTeamId())).getText().toString();
        }

        System.out.println("ply: "+playerNames);
        System.out.println("tm: " + teamName);
/*
        Intent intent = new Intent(this, ResultsActivity.class);
//        intent.putExtra(ResultsActivity.TEAM_NAME, teamName);
//        intent.putExtra(ResultsActivity.PLAYER_NAMES, playerNames);
        intent.putExtra(ResultsActivity.TEAM_NAME, "team");
        intent.putExtra(ResultsActivity.PLAYER_NAMES, "players");
        startActivity(intent);*/
        reset();
    }

}
