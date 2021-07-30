package com.example.whac_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView time_view;
    TextView score_view;

    long time;
    int score = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        time_view = findViewById(R.id.time_view);
        score_view = findViewById(R.id.score_view);

        ArrayList<View> targets = new ArrayList<>();

        ImageButton btn1 = (ImageButton) findViewById(R.id.btn1);
        ImageButton btn2 = (ImageButton) findViewById(R.id.btn2);
        ImageButton btn3 = (ImageButton) findViewById(R.id.btn3);
        ImageButton btn4 = (ImageButton) findViewById(R.id.btn4);
        ImageButton btn5 = (ImageButton) findViewById(R.id.btn5);
        ImageButton btn6 = (ImageButton) findViewById(R.id.btn6);
        ImageButton btn7 = (ImageButton) findViewById(R.id.btn7);
        ImageButton btn8 = (ImageButton) findViewById(R.id.btn8);
        ImageButton btn9 = (ImageButton) findViewById(R.id.btn9);

        targets.add(btn1);
        targets.add(btn2);
        targets.add(btn3);
        targets.add(btn4);
        targets.add(btn5);
        targets.add(btn6);
        targets.add(btn7);
        targets.add(btn8);
        targets.add(btn9);

        int randomTarget = new Random().nextInt(targets.size());
        targets.get(randomTarget).performClick();

        new CountDownTimer(30000, 500){

            @Override
            public void onTick(long millisUntilFinished) {
                for(int i = 0; i < 9; i++)
                    targets.get(i).setVisibility(View.GONE);
                int randomTarget = new Random().nextInt(targets.size());
                targets.get(randomTarget).setVisibility(View.VISIBLE);
                time = millisUntilFinished / 1000;
                time_view.setText(Long.toString(time));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                intent.putExtra("score", Integer.toString(score));
                score = -1;
                startActivity(intent);
            }
        }.start();

    }



    public void onTargetClick(View view) {
        view.setVisibility(View.GONE);
        score++;
        score_view.setText(Integer.toString(score));
    }
}