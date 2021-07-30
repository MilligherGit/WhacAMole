package com.example.whac_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView your_score;
    TextView record;
    Button btn_again;
    Button btn_menu;

    String score;
    String last_record;
    final String SAVED_SCORE =  "saved_score";

    Intent intent;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        loadScore();

        your_score = findViewById(R.id.your_score);
        record = findViewById(R.id.record);
        btn_again = findViewById(R.id.btn_again);
        btn_menu = findViewById(R.id.btn_menu);

        intent = getIntent();
        score = intent.getStringExtra("score");
        your_score.setText("Your score: " + score);

        if (Integer.parseInt(last_record) <  Integer.parseInt(score)) {
            saveScore();
            record.setText("Max score: " + score);
        } else record.setText("Max score: " + last_record);



        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveScore() {
        sharedPreferences = getSharedPreferences("record", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_SCORE, score);
        editor.commit();
    }


    private void loadScore() {
        sharedPreferences = getSharedPreferences("record", MODE_PRIVATE);
        last_record = sharedPreferences.getString(SAVED_SCORE, "0");
    }
}