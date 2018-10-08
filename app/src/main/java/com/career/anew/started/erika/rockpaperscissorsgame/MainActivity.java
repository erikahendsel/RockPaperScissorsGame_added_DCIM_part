package com.career.anew.started.erika.rockpaperscissorsgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b_rock, b_scissors, b_paper, btn_highScore;
    TextView high_score, player_name;
    ImageView iv_EnemyChoice, iv_HumanChoice;
    int HumanScore, ComputerScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_rock = (Button) findViewById(R.id.btn_rock);
        b_paper = (Button) findViewById(R.id.btn_paper);
        b_scissors = (Button) findViewById(R.id.btn_scissors);
        btn_highScore = (Button) findViewById(R.id.btn_highScore);

        iv_EnemyChoice = (ImageView) findViewById(R.id.enemy_choice);
        iv_HumanChoice = (ImageView) findViewById(R.id.player_choice);

        player_name = (TextView) findViewById(R.id.player_name);
        SharedPreferences result = getSharedPreferences("SaveData", 0);
        String value = result.getString("Value", "Data not Found");
        player_name.setText(value);


        btn_highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("Player Score", HumanScore);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), HighestScore.class);
                startActivity(intent);
                finish();
            }
        });

        high_score = (TextView) findViewById(R.id.score);

        b_paper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.paper);
                String message = play_turn("paper");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                high_score.setText("Score player: " + Integer.toString(HumanScore) + " Enemy: " + Integer.toString(ComputerScore));
            }
        });

        b_rock.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.rock);
                String message = play_turn("rock");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                high_score.setText("Score player: " + Integer.toString(HumanScore) + " Enemy: " + Integer.toString(ComputerScore));
            }
        });

        b_scissors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.scissors);
                String message = play_turn("scissors");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                high_score.setText("Score player: " + Integer.toString(HumanScore) + " Enemy: " + Integer.toString(ComputerScore));
            }
        });
    }
    public String play_turn(String player_choice) {
        String computer_choice = "";
        Random r = new Random();
        int computer_choice_number = r.nextInt(3) + 1;

        if (computer_choice_number == 1) {
            computer_choice = "rock";
        }
        else if (computer_choice_number == 2) {
            computer_choice = "scissors";
        }
        else if (computer_choice_number == 3) {
            computer_choice = "paper";
        }

        if (computer_choice == "rock") {
            iv_EnemyChoice.setImageResource(R.drawable.rock);
        }
        else if (computer_choice == "scissors") {
            iv_EnemyChoice.setImageResource(R.drawable.scissors);
        }
        else if (computer_choice == "paper") {
            iv_EnemyChoice.setImageResource(R.drawable.paper);
        }



        if (computer_choice == player_choice) {
            return "Draw";
        }
        else if (player_choice == "rock" && computer_choice == "scissors") {
            HumanScore++;
            return "WIN! Rock crushes scissors";
        }
        else if (player_choice == "rock" && computer_choice == "paper") {
            ComputerScore++;
            return "LOSE! Rock gets wrapped by paper";
        }
        else if (player_choice == "scissors" && computer_choice == "rock") {
            ComputerScore++;
            return "LOSE! Rock crushes scissors";
        }
        else if (player_choice == "scissors" && computer_choice == "paper") {
            HumanScore++;
            return "WIN! Scissors cut paper";
        }
        else if (player_choice == "paper" && computer_choice == "rock") {
            HumanScore++;
            return "WIN! Paper wraps rock";
        }
        else if (player_choice == "paper" && computer_choice == "scissors") {
            ComputerScore++;
            return "LOSE! Paper gets cut by scissors";
        }
        else return "Very, very big error";
    }
}
