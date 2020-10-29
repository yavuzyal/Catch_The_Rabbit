package com.yavuz.catchtherabbit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;
    SharedPreferences sharedPreferences;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView2);
        sharedPreferences = this.getSharedPreferences("com.yavuz.catchthekenny", Context.MODE_PRIVATE);

        int high = sharedPreferences.getInt("highScore", 0);
        textView.setText("Highest Score: " + high);

    }

    public void onClick(View view){

        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        imageView.animate().setDuration(2000).rotationY(2160);

        CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(intent);
            }
        }.start();



    }
}