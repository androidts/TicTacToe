package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GameTime extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    String player1;
    String player2;
    String option1;
    String option2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_time);

        Intent intent = getIntent();
        // player1Name details
        player1 = intent.getStringExtra("Player1Name");
        option1 = intent.getStringExtra("Player1Option");

        //player2Name details
        player2 = intent.getStringExtra("Player2Name");
        option2 = intent.getStringExtra("Player2Option");


        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer1.setText(player1+" :");
        textViewPlayer2 = findViewById(R.id.text_view_p2);
        textViewPlayer2.setText(player2+" :");




        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });


    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            System.out.println(v);
            if(option1.equals("x"))
            {
                v.setBackgroundResource(R.drawable.x);
            }
            else if(option1.equals("o"))
            {
                 v.setBackgroundResource(R.drawable.o);
            }
            else if(option1.equals("superman"))
            {
                 v.setBackgroundResource(R.drawable.superman);
            }
            else if(option1.equals("batman"))
            {
                 v.setBackgroundResource(R.drawable.batman);
            }

            ((Button) v).setText(option1);

        } else if(!player1Turn) {
            System.out.println(option2);
            if(option2.equals("x"))
            {
                 v.setBackgroundResource(R.drawable.x);
            }
            else if(option2.equals("o"))
            {
                 v.setBackgroundResource(R.drawable.o);
            }
            else if(option2.equals("superman"))
            {
                 v.setBackgroundResource(R.drawable.superman);
            }
            else if(option2.equals("batman"))
            {
                 v.setBackgroundResource(R.drawable.batman);
            }
            ((Button) v).setText(option2);
        }
        roundCount++;
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }
    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }
    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, player1+" wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);

            }
        }

       // resetBoard();
    }
    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, player2+" wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);

            }
        }

       // resetBoard();
    }
    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePointsText() {
        textViewPlayer1.setText(player1+" :" + player1Points);
        textViewPlayer2.setText(player2+" :" + player2Points);
    }
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackgroundResource(android.R.drawable.btn_default_small);
                buttons[i][j].setEnabled(true);
            }
        }
        roundCount = 0;
        player1Turn = true;
    }
    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}