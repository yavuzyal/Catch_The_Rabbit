package com.yavuz.catchtherabbit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    TextView textView;
    TextView textView2;
    int score = 0;
    SharedPreferences sharedPreferences;
    Intent intent;
    Drawable drawable;

    ImageView imageArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        sharedPreferences = this.getSharedPreferences("com.yavuz.catchthekenny", Context.MODE_PRIVATE);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView7,imageView6,imageView8,imageView9};
        hideImages();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView2.setText("Time: " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                imageView.setClickable(false);
                imageView2.setClickable(false);
                imageView3.setClickable(false);
                imageView4.setClickable(false);
                imageView5.setClickable(false);
                imageView6.setClickable(false);
                imageView7.setClickable(false);
                imageView8.setClickable(false);
                imageView9.setClickable(false);
                textView2.setText("Time is off :(");
                alert.setTitle("Restart");
                alert.setMessage("Do you want to restart?");
                alert.setCancelable(false);
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText("Final Score: " + score);
                        intent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                alert.show();
                Toast.makeText(getApplicationContext(),"Well Done!", Toast.LENGTH_LONG).show();

                if(score > sharedPreferences.getInt("highScore", 0))
                    sharedPreferences.edit().putInt("highScore", score).apply();
            }
        }.start();  //CountDown Timer end.
    }

    public void catchKenny(View view){
        score++;
        textView.setText("Score: " + score);
        hideImages();

        /*int randomX = new Random().nextInt(600);
        int randomY = new Random().nextInt(600);
        imageView.setX(randomX);
        imageView.setY(randomY);*/
    }

    public void hideImages(){

        for(int i = 0; i < 9 ; i++){
            imageArray[i].setVisibility(View.INVISIBLE);
        }
        Random random = new Random();
        int rand = random.nextInt(9);
        imageArray[rand].setVisibility(View.VISIBLE);
    }
}