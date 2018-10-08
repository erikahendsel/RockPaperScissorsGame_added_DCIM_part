package com.career.anew.started.erika.rockpaperscissorsgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerName extends AppCompatActivity {

    EditText player_name;
    Button btnPlay;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        btnPlay = findViewById(R.id.save_name);
        player_name = (EditText)findViewById(R.id.player_name);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("SaveData", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Value", player_name.getText().toString());
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
