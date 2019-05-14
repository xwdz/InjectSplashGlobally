package com.xwdz.splash.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {


    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setTextColor(Color.RED);
        textView.setTextSize(50);
        textView.setText("WelcomeActivity");
        textView.setGravity(Gravity.CENTER);
        setContentView(textView);


        CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("WelcomeActivity finish:" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        countDownTimer.start();
    }
}
