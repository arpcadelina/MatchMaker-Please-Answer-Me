package com.example.matchmaker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.GridLayout;

import java.util.Random;

public class Cards extends AppCompatActivity implements  View.OnClickListener {
    Dialog settingsDialog, returnDialog;

    private int numberOfElements;

    private MemoryButton[] buttons;

    private int[] buttonGraphicsLocation;
    private int[] buttonGraphics;

    private MemoryButton selectedButton1;
    private MemoryButton selectedButton2;

    private boolean isBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_cards);

        settingsDialog = new Dialog(this);
        returnDialog = new Dialog(this);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.easy_4x4);

        int numCols = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        numberOfElements = numCols * numRows;

        buttons = new MemoryButton [numberOfElements];

        buttonGraphics = new int[numberOfElements / 2];

        buttonGraphics[0] = R.drawable.e1;
        buttonGraphics[1] = R.drawable.e2;
        buttonGraphics[2] = R.drawable.e3;
        buttonGraphics[3] = R.drawable.e4;
        buttonGraphics[4] = R.drawable.e5;
        buttonGraphics[5] = R.drawable.e6;
        buttonGraphics[6] = R.drawable.e7;
        buttonGraphics[7] = R.drawable.e8;

        buttonGraphicsLocation = new int[numberOfElements];

        shuffleButtonGraphics();

        for(int r = 0; r < numRows; r++) {
            for(int c = 0; c < numCols; c++) {
                MemoryButton tempButton = new MemoryButton(this, r, c, buttonGraphics[buttonGraphicsLocation[r * numCols + c]]);

                tempButton.setId(ViewCompat.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[r * numCols + c] = tempButton;
                gridLayout.addView(tempButton);
            }
        }
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

    protected void shuffleButtonGraphics() {
        Random rand = new Random();

        for(int i = 0; i < numberOfElements; i++) {
            buttonGraphicsLocation[i] = i % (numberOfElements / 2);
        }

        for(int i = 0; i < numberOfElements; i++) {
            int temp = buttonGraphicsLocation[i];

            int swapIndex = rand.nextInt(16);

            buttonGraphicsLocation[i] = buttonGraphicsLocation[swapIndex];
            buttonGraphics[swapIndex] = temp;
        }
    }

    @Override
    public void onClick(View view) {
        if(isBusy)
            return;

        MemoryButton button = (MemoryButton) view;

        if(button.isMatched)
            return;

        if(selectedButton1 == null) {
            selectedButton1 = button;
            selectedButton1.flip();
            return;
        }

        if(selectedButton1.getId() == button.getId()) {
            return;
        }

        if(selectedButton1.getFrontDrawableId() == button.getFrontDrawableId()) {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);

            selectedButton1.setEnabled(false);
            button.setEnabled(false);

            selectedButton1 = null;
            return;
        } else {
            selectedButton2 = button;
            selectedButton2.flip();
            isBusy = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectedButton2.flip();
                    selectedButton1.flip();
                    selectedButton1 = null;
                    selectedButton2 = null;
                    isBusy = false;
                }
            }, 500);
        }
    }
}