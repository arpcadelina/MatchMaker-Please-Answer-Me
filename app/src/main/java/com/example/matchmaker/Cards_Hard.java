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

public class Cards_Hard extends AppCompatActivity {
    Dialog settingsDialog, returnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_cards__hard);

        settingsDialog = new Dialog(this);
        returnDialog = new Dialog(this);
    }

    public void pause(View v) {
        Button resumebtn, quitbtn;
        settingsDialog.setContentView(R.layout.activity_settings);
        resumebtn = (Button) settingsDialog.findViewById(R.id.resumebtn);
        quitbtn = (Button) settingsDialog.findViewById(R.id.quitbtn);
        resumebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsDialog.dismiss();
            }
        });
        quitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsDialog.dismiss();
                quit();
            }
        });
        settingsDialog.show();
    }

    public void quit() {
        Button yesbtn2, nobtn2;
        returnDialog.setContentView(R.layout.activity_return_confirmation);
        yesbtn2 = (Button) returnDialog.findViewById(R.id.yesbtn2);
        nobtn2 = (Button) returnDialog.findViewById(R.id.nobtn2);
        nobtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnDialog.dismiss();
                settingsDialog.show();
            }
        });
        yesbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnDialog.dismiss();
                yes();
            }
        });
        returnDialog.show();
    }

    public void yes() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}