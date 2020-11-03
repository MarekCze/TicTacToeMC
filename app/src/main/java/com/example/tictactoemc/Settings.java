package com.example.tictactoemc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    private EditText p1NameEditText;
    private EditText p2NameEditText;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setP1NameEditText((EditText) findViewById(R.id.p1NameEditText));
        setP2NameEditText((EditText) findViewById(R.id.p2NameEditText));
        setSendBtn((Button) findViewById(R.id.sendBtn));

        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("p1Name", p1NameEditText.getText().toString());
        intent.putExtra("p2Name", p2NameEditText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public EditText getP1NameEditText() {
        return p1NameEditText;
    }

    public void setP1NameEditText(EditText p1NameEditText) {
        this.p1NameEditText = p1NameEditText;
    }

    public EditText getP2NameEditText() {
        return p2NameEditText;
    }

    public void setP2NameEditText(EditText p2NameEditText) {
        this.p2NameEditText = p2NameEditText;
    }

    public Button getSendBtn() {
        return sendBtn;
    }

    public void setSendBtn(Button sendBtn) {
        this.sendBtn = sendBtn;
    }


}