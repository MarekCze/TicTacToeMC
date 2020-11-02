package com.example.tictactoemc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    private EditText p1NameTextView;
    private EditText p2NameTextView;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setP1NameTextView((EditText) findViewById(R.id.p1NameExitText));
        setP2NameTextView((EditText) findViewById(R.id.p2NameEditText));
        setSendBtn((Button) findViewById(R.id.sendBtn));

        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("p1Name", p1NameTextView.getText());
        intent.putExtra("p2Name", p2NameTextView.getText());
        setResult(RESULT_OK, intent);
        finish();
    }

    public EditText getP1NameTextView() {
        return p1NameTextView;
    }

    public void setP1NameTextView(EditText p1NameTextView) {
        this.p1NameTextView = p1NameTextView;
    }

    public EditText getP2NameTextView() {
        return p2NameTextView;
    }

    public void setP2NameTextView(EditText p2NameTextView) {
        this.p2NameTextView = p2NameTextView;
    }

    public Button getSendBtn() {
        return sendBtn;
    }

    public void setSendBtn(Button sendBtn) {
        this.sendBtn = sendBtn;
    }


}