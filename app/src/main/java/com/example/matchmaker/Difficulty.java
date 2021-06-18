package com.example.matchmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Difficulty extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_difficulty);

        button = (Button)findViewById(R.id.easybtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easy();
            }
        });

        button = (Button)findViewById(R.id.mediumbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                medium();
            }
        });

        button = (Button)findViewById(R.id.hardbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                hard();
            }
        });

        button = (Button)findViewById(R.id.backbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public void easy() {
        Intent intent = new Intent(this, Cards.class);
        startActivity(intent);
    }

    public void medium() {
        Intent intent = new Intent(this, Cards_Medium.class);
        startActivity(intent);
    }

    public void hard() {
        Intent intent = new Intent(this, Cards_Hard.class);
        startActivity(intent);
    }

    public void back() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}