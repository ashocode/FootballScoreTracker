package com.example.android.footballscoretracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_SCORE_A = "StateScoreA";
    private static final String KEY_SCORE_B = "StateScoreB";
    private static final String KEY_FOULS_A = "StateFoulsTeamA";
    private static final String KEY_FOULS_B = "StateFoulsTeamB";
    private static final String KEY_YELLOW_A = "StateYellowCardsTeamA";
    private static final String KEY_YELLOW_B = "StateYellowCardsTeamB";
    private static final String KEY_RED_A = "StateRedCardsTeamA";
    private static final String KEY_RED_B = "StateRedCardsTeamB";
    private static final String KEY_LAST_CHANGE_LIST = "StateLastChange";
    private static final String KEY_LAST_CHANGE_POSITION = "StateLastChangePosition";

    private int scoreTeamA = 0;
    private int scoreTeamB = 0;
    private int foulsTeamA = 0;
    private int foulsTeamB = 0;
    private int yellowCardsTeamA = 0;
    private int yellowCardsTeamB = 0;
    private int redCardsTeamA = 0;
    private int redCardsTeamB = 0;
    private List<Integer> lastChange = new ArrayList<>();
    private int lastChangePosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    This method is restoring the saved state in key-value pairs.
    */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

        scoreTeamA = savedInstanceState.getInt(KEY_SCORE_A);
        scoreTeamB = savedInstanceState.getInt(KEY_SCORE_B);
        foulsTeamA = savedInstanceState.getInt(KEY_FOULS_A);
        foulsTeamB = savedInstanceState.getInt(KEY_FOULS_B);
        yellowCardsTeamA = savedInstanceState.getInt(KEY_YELLOW_A);
        yellowCardsTeamB = savedInstanceState.getInt(KEY_YELLOW_B);
        redCardsTeamA = savedInstanceState.getInt(KEY_RED_A);
        redCardsTeamB = savedInstanceState.getInt(KEY_RED_B);
        lastChange = savedInstanceState.getIntegerArrayList(KEY_LAST_CHANGE_LIST);
        lastChangePosition = savedInstanceState.getInt(KEY_LAST_CHANGE_POSITION);

        displayScoreA(scoreTeamA);
        displayScoreB(scoreTeamB);
        displayFoulsA(foulsTeamA);
        displayFoulsB(foulsTeamB);
        displayYellowCardsA(yellowCardsTeamA);
        displayYellowCardsB(yellowCardsTeamB);
        displayRedCardsA(redCardsTeamA);
        displayRedCardsB(redCardsTeamB);
    }

    /*
    This method is saving the current state, so it can be used upon rotation of the screen.
    */

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(KEY_SCORE_A, scoreTeamA);
        savedInstanceState.putInt(KEY_SCORE_B,scoreTeamB);
        savedInstanceState.putInt(KEY_FOULS_A,foulsTeamA);
        savedInstanceState.putInt(KEY_FOULS_B,foulsTeamB);
        savedInstanceState.putInt(KEY_YELLOW_A,yellowCardsTeamA);
        savedInstanceState.putInt(KEY_YELLOW_B,yellowCardsTeamB);
        savedInstanceState.putInt(KEY_RED_A,redCardsTeamA);
        savedInstanceState.putInt(KEY_RED_B,redCardsTeamB);
        savedInstanceState.putIntegerArrayList(KEY_LAST_CHANGE_LIST,(ArrayList<Integer>)lastChange);
        savedInstanceState.putInt(KEY_LAST_CHANGE_POSITION,lastChangePosition);
    }

    /*
    This section takes care of displaying the the values in the UI.
    */

    private void displayScoreA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score_team_a);
        scoreView.setText(String.valueOf(score));
    }

    private void displayScoreB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.score_team_b);
        scoreView.setText(String.valueOf(score));
    }

    private void displayFoulsA(int fouls) {
        TextView scoreView = (TextView) findViewById(R.id.fouls_team_a);
        scoreView.setText(String.valueOf(fouls));
    }

    private void displayFoulsB(int fouls) {
        TextView scoreView = (TextView) findViewById(R.id.fouls_team_b);
        scoreView.setText(String.valueOf(fouls));
    }

    private void displayYellowCardsA(int yellowCards) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_yellow_cards);
        scoreView.setText(String.valueOf(yellowCards));
    }

    private void displayYellowCardsB(int yellowCards) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_yellow_cards);
        scoreView.setText(String.valueOf(yellowCards));
    }

    private void displayRedCardsA(int redCards) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_red_cards);
        scoreView.setText(String.valueOf(redCards));
    }

    private void displayRedCardsB(int redCards) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_red_cards);
        scoreView.setText(String.valueOf(redCards));
    }

    /*
    This method saves the view id of the last button pressed by the user. Used for "undo".
    */

    private void saveLastChange(int viewID) {
        lastChange.add(viewID);
        lastChangePosition++;
    }

    /*
    This section takes care of incrementing and displaying the the values in the UI after a button is pressed.
    */

    public void scoreGoal(View view) {
        int viewID = view.getId();

        if (viewID == R.id.add_goal_team_a) {
            scoreTeamA++;
            displayScoreA(scoreTeamA);
        } else if (viewID == R.id.add_goal_team_b) {
            scoreTeamB++;
            displayScoreB(scoreTeamB);
        }
        saveLastChange(viewID);
    }

    public void addFoul(View view) {
        int viewID = view.getId();

        if (viewID == R.id.add_foul_team_a) {
            foulsTeamA++;
            displayFoulsA(foulsTeamA);
        } else if (viewID == R.id.add_foul_team_b) {
            foulsTeamB++;
            displayFoulsB(foulsTeamB);
        }
        saveLastChange(viewID);
    }

    public void addYellow(View view) {
        int viewID = view.getId();

        if (viewID == R.id.add_yellow_team_a) {
            yellowCardsTeamA++;
            displayYellowCardsA(yellowCardsTeamA);
        } else if (viewID == R.id.add_yellow_team_b) {
            yellowCardsTeamB++;
            displayYellowCardsB(yellowCardsTeamB);
        }
        saveLastChange(viewID);
    }

    public void addRed(View view) {
        int viewID = view.getId();

        if (viewID == R.id.add_red_team_a) {
            redCardsTeamA++;
            displayRedCardsA(redCardsTeamA);
        } else if (viewID == R.id.add_red_team_b) {
            redCardsTeamB++;
            displayRedCardsB(redCardsTeamB);
        }
        saveLastChange(viewID);
    }

    /*
    This method handles the "undo" operation. Depending on the action view ID the corresponding value is decreased by one and the entry is removed from the list.
    */

    public void undo(View view) {
        int changeViewID = 0;
        if (lastChangePosition >= 0) {
            changeViewID = lastChange.get(lastChangePosition);

            if (changeViewID == R.id.add_goal_team_a) {
                scoreTeamA--;
                displayScoreA(scoreTeamA);
            } else if (changeViewID == R.id.add_goal_team_b) {
                scoreTeamB--;
                displayScoreB(scoreTeamB);
            } else if (changeViewID == R.id.add_foul_team_a) {
                foulsTeamA--;
                displayFoulsA(foulsTeamA);
            } else if (changeViewID == R.id.add_foul_team_b) {
                foulsTeamB--;
                displayFoulsB(foulsTeamB);
            } else if (changeViewID == R.id.add_yellow_team_a) {
                yellowCardsTeamA--;
                displayYellowCardsA(yellowCardsTeamA);
            } else if (changeViewID == R.id.add_yellow_team_b) {
                yellowCardsTeamB--;
                displayYellowCardsB(yellowCardsTeamB);
            } else if (changeViewID == R.id.add_red_team_a) {
                redCardsTeamA--;
                displayRedCardsA(redCardsTeamA);
            } else if (changeViewID == R.id.add_red_team_b) {
                redCardsTeamB--;
                displayRedCardsB(redCardsTeamB);
            }

            lastChange.remove(lastChangePosition);
            lastChangePosition--;
        }

    }

    /*
    This method resets all values.
    */

    public void resetAll(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        foulsTeamA = 0;
        foulsTeamB = 0;
        yellowCardsTeamA = 0;
        yellowCardsTeamB = 0;
        redCardsTeamA = 0;
        redCardsTeamB = 0;
        displayScoreA(scoreTeamA);
        displayScoreB(scoreTeamB);
        displayFoulsA(foulsTeamA);
        displayFoulsB(foulsTeamB);
        displayYellowCardsA(yellowCardsTeamA);
        displayYellowCardsB(yellowCardsTeamB);
        displayRedCardsA(redCardsTeamA);
        displayRedCardsB(redCardsTeamB);
        lastChange.clear();
        lastChangePosition = -1;
    }
}
