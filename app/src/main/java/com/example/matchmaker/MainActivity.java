package com.example.matchmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    Dialog dialog, creditsDialog, quit_gameDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.playbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        dialog = new Dialog(this);
        creditsDialog = new Dialog(this);
        quit_gameDialog = new Dialog(this);
    }

    public void play() {
        Intent intent = new Intent(this, Difficulty.class);
        startActivity(intent);
    }

    public void tutorial(View v) {
        Button closebtn2;
        dialog.setContentView(R.layout.activity_tutorial);
        closebtn2 = (Button) dialog.findViewById(R.id.closebtn2);
        closebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void credits(View v) {
        Button closebtn;
        creditsDialog.setContentView(R.layout.credits);
        closebtn = (Button) creditsDialog.findViewById(R.id.closebtn);
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creditsDialog.dismiss();
            }
        });
        creditsDialog.show();
    }

    public void quit_game(View v) {
        Button yesbtn, nobtn;
        quit_gameDialog.setContentView(R.layout.quit_game);
        nobtn = (Button) quit_gameDialog.findViewById(R.id.nobtn);
        yesbtn = (Button) quit_gameDialog.findViewById(R.id.yesbtn);
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quit_gameDialog.dismiss();
            }
        });

        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        quit_gameDialog.show();
    }
}