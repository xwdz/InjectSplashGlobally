package com.xwdz.splash.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView textView = new TextView(this);
        textView.setTextColor(Color.RED);
        textView.setTextSize(50);
        textView.setText("TestMainActivity");

        setContentView(textView);
    }
}
