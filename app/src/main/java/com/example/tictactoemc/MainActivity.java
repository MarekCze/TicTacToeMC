package com.example.tictactoemc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        setPlayerTurnTextView((TextView) findViewById(R.id.playerTurnTextView));

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String btnId = "btn" + i + j;
                int resId = getResources().getIdentifier(btnId, "id", getPackageName());
                getButtonArray()[i][j] = findViewById(resId);
                getButtonArray()[i][j].setOnClickListener(this);
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

    public Button[][] getButtonArray() {
        return buttonArray;
    }

    public void setButtonArray(Button[][] buttonArray) {
        this.buttonArray = buttonArray;
    }

    public boolean isP1Turn() {
        return p1Turn;
    }

    public void setP1Turn(boolean p1Turn) {
        this.p1Turn = p1Turn;
    }

    public int getP1Score() {
        return p1Score;
    }

    public void setP1Score(int p1Score) {
        this.p1Score = p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }

    public void setP2Score(int p2Score) {
        this.p2Score = p2Score;
    }

    public String getP1Name() {
        return p1Name;
    }

    public void setP1Name(String p1Name) {
        this.p1Name = p1Name;
    }

    public String getP2Name() {
        return p2Name;
    }

    public void setP2Name(String p2Name) {
        this.p2Name = p2Name;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public TextView getPlayerTurnTextView() {
        return playerTurnTextView;
    }

    public void setPlayerTurnTextView(TextView playerTurnTextView) {
        this.playerTurnTextView = playerTurnTextView;
    }
}