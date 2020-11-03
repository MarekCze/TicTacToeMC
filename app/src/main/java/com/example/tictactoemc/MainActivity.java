package com.example.tictactoemc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int SETTINGS_ACTIVITY_REQUEST_CODE = 0;

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
    private Button settingsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPlayerTurnTextView((TextView) findViewById(R.id.playerTurnTextView));
        setP1TextView((TextView) findViewById(R.id.p1TextView));
        setP2TextView((TextView) findViewById(R.id.p2TextView));

        // populate button array and apply listeners to each button
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                String btnId = "btn" + i + j;
                int resId = getResources().getIdentifier(btnId, "id", getPackageName());
                getButtonArray()[i][j] = findViewById(resId);
                getButtonArray()[i][j].setOnClickListener(this);
            }
        }

        // instantiate bottom buttons
        Button newGameBtn = findViewById(R.id.newGameBtn);
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

    }

    // create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    // onClick menu item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.settingsMenuItem:
                openSettings(settingsBtn);
        }

        return super.onOptionsItemSelected(item);
    }

    // save data on pause
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("p1Score", this.getP1Score());
        outState.putInt("p2Score", this.getP2Score());
        outState.putString("p1Name", this.getP1Name());
        outState.putString("p2Name", this.getP2Name());
        outState.putInt("turnNumber", this.getTurnNumber());
        outState.putBoolean("p1Turn", this.isP1Turn());
    }

    // get saved data on restore
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        this.setP1Score(savedInstanceState.getInt("p1Score"));
        this.setP2Score(savedInstanceState.getInt("p2Score"));
        this.setP1Name(savedInstanceState.getString("p1Name"));
        this.setP2Name(savedInstanceState.getString("p2Name"));
        this.setTurnNumber(savedInstanceState.getInt("turnNumber"));
        this.setP1Turn(savedInstanceState.getBoolean("p1Turn"));
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivityForResult(intent, SETTINGS_ACTIVITY_REQUEST_CODE);
    }

    // retrieve player names from settings activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SETTINGS_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                this.setP1Name(data.getStringExtra("p1Name"));
                this.setP2Name(data.getStringExtra("p2Name"));
                Log.d("onActivityResult", "P1Name: " + data.getStringExtra("p1Name"));
                p1TextView.setText(this.getP1Name() + " score:");
                p2TextView.setText(this.getP2Name() + " score:");
                this.setP1Score(0);
                this.setP2Score(0);
            }
        }
    }

    // handle grid button behaviour
    @Override
    public void onClick(View v) {
        // if button was already clicked, do nothing
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        // check turn and set button text
        if(isP1Turn()){
            ((Button)v).setText("X");
        } else {
            ((Button)v).setText("O");
        }
        // increment turn
        this.setTurnNumber(this.getTurnNumber() + 1);

        // check win condition and display appropriate message
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
            playerTurnTextView.setText(this.getP1Name() + "'s turn");
            this.setP1Turn(false);
        } else {
            playerTurnTextView.setText(this.getP2Name() + "'s turn");
            this.setP1Turn(true);
        }


    }

    // disable/enable button grid
    private void setGrid(boolean b){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                buttonArray[i][j].setEnabled(b);
            }
        }
    }

    // update player score at end of round
    private void updateScoreboard(){
        this.getP1TextView().setText(this.getP1Name() + " score: " + this.getP1Score());
        this.getP2TextView().setText(this.getP2Name() + " score: " + this.getP2Score());
    }

    private boolean hasWon(){
        String[][] field = new String[3][3];

        // instantiate field array with button values
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                field[i][j] = getButtonArray()[i][j].getText().toString();
            }
        }
        // rows
        for(int i = 0; i < 3; i++){
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }
        // columns
        for(int i = 0; i < 3; i++){
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }

        // diagonals
        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }

        if(field[2][0].equals(field[1][1]) && field[2][0].equals(field[0][2]) && !field[2][0].equals("")){
            return true;
        }

        return false;
    }

    // increment p1 score and display win message
    private void p1Wins(){
        setP1Score(getP1Score() + 1);
        Toast.makeText(this,this.getP1Name() + " wins!",Toast.LENGTH_SHORT).show();
    }

    // increment p2 score and display win message
    private void p2Wins(){
        setP2Score(getP2Score() + 1);
        Toast.makeText(this,this.getP2Name() + " wins!",Toast.LENGTH_SHORT).show();
    }

    // display draw message
    private void draw(){
        Toast.makeText(this,"It's a draw!",Toast.LENGTH_SHORT).show();
    }

    // clear grid buttons and set turn to 0
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

    public Button getSettingsBtn() {
        return settingsBtn;
    }

    public void setSettingsBtn(Button settingsBtn) {
        this.settingsBtn = settingsBtn;
    }
}