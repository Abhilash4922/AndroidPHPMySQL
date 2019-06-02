package com.example.androidphpmysql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class InternMessageActivity extends AppCompatActivity {
    private TextView textViewMessage, textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_message);
        textViewMessage = (TextView) findViewById(R.id.textViewInternMessage);
        textViewTime = (TextView) findViewById(R.id.textViewInternMessageTime);
        textViewMessage.setText(getIntent().getExtras().getString("message"));
        textViewTime.setText(getIntent().getExtras().getString("time"));

    }


}
