package com.career.anew.started.erika.rockpaperscissorsgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void next(View view) {
        Intent nextPage = new Intent(this, PlayerName.class);
        startActivity(nextPage);
    }
}
