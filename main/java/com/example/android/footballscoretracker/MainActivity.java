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
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

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

    private void saveLastChange(int viewID) {
        lastChange.add(viewID);
        lastChangePosition++;
    }

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
