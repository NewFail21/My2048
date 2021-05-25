package com.example.my2048;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Board board = new Board(4);
        GameView gameView = new GameView(getApplicationContext(), board);
        gameView.setOnGameOver(new GameView.OnGameOver() {
            @Override
            public void onGameOver(int score) {
                RecordDB recordDB = new RecordDB(getApplicationContext());
                recordDB.insert(score);
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        setContentView(gameView);
    }
}