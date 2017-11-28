package com.example.android.footballscoretracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

        if (savedInstanceState != null) {
            scoreTeamA = savedInstanceState.getInt("StateScoreA");
            scoreTeamB = savedInstanceState.getInt("StateScoreB");
            foulsTeamA = savedInstanceState.getInt("StateFoulsTeamA");
            foulsTeamB = savedInstanceState.getInt("StateFoulsTeamB");
            yellowCardsTeamA = savedInstanceState.getInt("StateYellowCardsTeamA");
            yellowCardsTeamB = savedInstanceState.getInt("StateYellowCardsTeamB");
            redCardsTeamA = savedInstanceState.getInt("StateRedCardsTeamA");
            redCardsTeamB = savedInstanceState.getInt("StateRedCardsTeamA");
            lastChange = savedInstanceState.getIntegerArrayList("StateLastChange");
            lastChangePosition = savedInstanceState.getInt("StateLastChangePosition");
        }

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

        savedInstanceState.putInt("StateScoreA", scoreTeamA);
        savedInstanceState.putInt("StateScoreB",scoreTeamB);
        savedInstanceState.putInt("StateFoulsTeamA",foulsTeamA);
        savedInstanceState.putInt("StateFoulsTeamB",foulsTeamB);
        savedInstanceState.putInt("StateYellowCardsTeamA",yellowCardsTeamA);
        savedInstanceState.putInt("StateYellowCardsTeamB",yellowCardsTeamB);
        savedInstanceState.putInt("StateRedCardsTeamA",redCardsTeamA);
        savedInstanceState.putInt("StateRedCardsTeamA",redCardsTeamB);
        savedInstanceState.putIntegerArrayList("StateLastChange", (ArrayList<Integer>)lastChange);
        savedInstanceState.putInt("StateLastChangePosition",lastChangePosition);
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
