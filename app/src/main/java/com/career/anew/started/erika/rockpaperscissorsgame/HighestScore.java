package com.career.anew.started.erika.rockpaperscissorsgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HighestScore extends AppCompatActivity {

    TextView high_score, txtViewMessages;
    TextView scoreTop;
    EditText txtFileName;
    int lastScore;
    int best1, best2, best3;
    String viewScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highest_score);

        txtFileName = findViewById(R.id.etFileName);
        txtViewMessages = findViewById(R.id.txtViewMessages);

        high_score = findViewById(R.id.score);
        scoreTop = findViewById(R.id.scoreTop);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastScore = preferences.getInt("Player Score", 0);
        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);

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
        viewScores = scoreTop.getText().toString();
    }

    public void onSaveToNextPage(View view) {
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("scoreTop", viewScores);
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), SaveScoreToExternalFile.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }


    public void goToGame(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }
}