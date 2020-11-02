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
    private TextView p1TextView;
    private TextView p2TextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPlayerTurnTextView((TextView) findViewById(R.id.playerTurnTextView));
        setP1TextView((TextView) findViewById(R.id.p1TextView));
        setP2TextView((TextView) findViewById(R.id.p2TextView));

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
                newGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        if(isP1Turn()){
            ((Button)v).setText("X");
        } else {
            ((Button)v).setText("O");
        }
        this.setTurnNumber(this.getTurnNumber() + 1);

        if(hasWon()){
            if(isP1Turn()){
                p1Wins();
            } else {
                p2Wins();
            }
            setGrid(false);
            updateScoreboard();
        } else if(turnNumber == 9){
            draw();
        }

        nextTurn();
    }

    private void nextTurn(){
        if(this.isP1Turn()){
            this.setP1Turn(false);
        } else {
            this.setP1Turn(true);
        }


    }

    private void setGrid(boolean b){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                buttonArray[i][j].setEnabled(b);
            }
        }
    }

    private void updateScoreboard(){
        this.getP1TextView().setText(this.getP1Name() + " score: " + this.getP1Score());
        this.getP2TextView().setText(this.getP2Name() + " score: " + this.getP2Score());
    }

    private boolean hasWon(){
        String[][] field = new String[3][3];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                field[i][j] = getButtonArray()[i][j].getText().toString();
            }
        }

        for(int i = 0; i < 3; i++){
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }

        for(int i = 0; i < 3; i++){
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }

        if(field[2][0].equals(field[1][1]) && field[2][0].equals(field[0][2]) && !field[2][0].equals("")){
            return true;
        }

        return false;
    }

    private void p1Wins(){
        setP1Score(getP1Score() + 1);
        Toast.makeText(this,this.getP1Name() + " wins!",Toast.LENGTH_SHORT).show();
    }

    private void p2Wins(){
        setP2Score(getP2Score() + 1);
        Toast.makeText(this,this.getP2Name() + " wins!",Toast.LENGTH_SHORT).show();
    }

    private void draw(){
        Toast.makeText(this,"It's a draw!",Toast.LENGTH_SHORT).show();
    }

    private void newGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                buttonArray[i][j].setText("");
            }
        }
        setGrid(true);
        this.setTurnNumber(0);
        this.setP1Turn(true);
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

    public TextView getP1TextView() {
        return p1TextView;
    }

    public void setP1TextView(TextView p1TextView) {
        this.p1TextView = p1TextView;
    }

    public TextView getP2TextView() {
        return p2TextView;
    }

    public void setP2TextView(TextView p2TextView) {
        this.p2TextView = p2TextView;
    }
}