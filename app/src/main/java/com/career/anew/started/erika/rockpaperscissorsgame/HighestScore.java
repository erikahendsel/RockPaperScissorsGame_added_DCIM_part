package com.career.anew.started.erika.rockpaperscissorsgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighestScore extends AppCompatActivity {

    TextView high_score;
    TextView scoreTop;

    int lastScore;
    int best1, best2, best3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        high_score = (TextView) findViewById(R.id.score);
        scoreTop = (TextView) findViewById(R.id.scoreTop);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastScore = preferences.getInt("Player Score", 0);
        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);

        //replace if there is a high score
        if(lastScore > best3) {
            best3 = lastScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.apply();
        }

        if(lastScore > best2) {
            int temp = best2;
            best2 = lastScore;
            best3 = temp;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.putInt("best2", best2);
            editor.apply();
        }

        if(lastScore > best1) {
            int temp = best1;
            best1 = lastScore;
            best2 = temp;

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best2", best2);
            editor.putInt("best1", best1);
            editor.apply();
        }
        //display scores
        high_score.setText("YOUR LAST SCORE: " + lastScore + "\n");
        scoreTop.setText("Top 1: " + best1 + "\n" + "\n" +
                "Top 2: " + best2 + "\n" + "\n" +
                "Top 3: " + best3);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
