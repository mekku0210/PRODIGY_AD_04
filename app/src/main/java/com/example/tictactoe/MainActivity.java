package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText playerXName, playerOName;
    Button startGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerXName = findViewById(R.id.playerXName);
        playerOName = findViewById(R.id.playerOName);
        startGameButton = findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(v -> {
            String playerX = playerXName.getText().toString().trim();
            String playerO = playerOName.getText().toString().trim();

            if(playerX.isEmpty() || playerO.isEmpty()){
                Toast.makeText(this, "Please enter both player names", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("playerX", playerX);
            intent.putExtra("playerO", playerO);
            startActivity(intent);
        });
    }
}
