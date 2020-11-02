package com.example.tictactoemc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttonArray = new Button[3][3];

    private boolean p1Turn = true;
    private int p1Score = 0;
    private int p2Score = 0;
    private String p1Name = "Player 1";
    private String p2Name = "Player 2";
    private int turnNumber = 0;

    private TextView playerTurnTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerTurnTextView = (TextView) findViewById(R.id.playerTurnTextView);

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String btnId = "btn" + i + j;
                int resId = getResources().getIdentifier(btnId, "id", getPackageName());
                buttonArray[i][j] = findViewById(resId);
                buttonArray[i][j].setOnClickListener(this);
            }
        }

        Button newGameBtn = findViewById(R.id.newGameBtn);
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

}