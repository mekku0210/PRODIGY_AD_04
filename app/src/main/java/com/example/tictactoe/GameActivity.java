package com.example.tictactoe;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];
    Button resetButton;
    TextView playerTurnText;
    String playerX, playerO;
    boolean isXTurn = true;
    int roundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerX = getIntent().getStringExtra("playerX");
        playerO = getIntent().getStringExtra("playerO");

        playerTurnText = findViewById(R.id.playerTurnText);
        resetButton = findViewById(R.id.resetButton);

        for(int i=0; i<9; i++){
            String buttonID = "button" + (i+1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(v -> onButtonClick((Button)v));
        }

        resetButton.setOnClickListener(v -> resetGame());
    }

    private void onButtonClick(Button button){
        if(!button.getText().toString().equals("")) return;

        if(isXTurn){
            button.setText("X");
            playerTurnText.setText(playerO + "'s Turn");
        } else {
            button.setText("O");
            playerTurnText.setText(playerX + "'s Turn");
        }

        roundCount++;

        if(checkWin()){
            if(isXTurn) playerWins(playerX);
            else playerWins(playerO);
        } else if(roundCount == 9){
            draw();
        } else {
            isXTurn = !isXTurn;
        }
    }

    private boolean checkWin(){
        String[][] field = new String[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                field[i][j] = buttons[i*3+j].getText().toString();
            }
        }

        for(int i=0; i<3; i++){
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) return true;
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) return true;
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) return true;
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) return true;

        return false;
    }

    private void playerWins(String player){
        Toast.makeText(this, player + " Wins!", Toast.LENGTH_LONG).show();
        disableButtons();
    }

    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show();
    }

    private void disableButtons(){
        for(Button b: buttons) b.setEnabled(false);
    }

    private void resetGame(){
        for(Button b: buttons){
            b.setText("");
            b.setEnabled(true);
        }
        roundCount = 0;
        isXTurn = true;
        playerTurnText.setText(playerX + "'s Turn");
    }
}
