package com.example.whac_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_play;
    TextView record;

    final String SAVED_SCORE =  "saved_score";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = findViewById(R.id.btn_play);
        record = findViewById(R.id.record);

        loadScore();

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadScore() {
        sharedPreferences = getSharedPreferences("record", MODE_PRIVATE);
        String higest_score = sharedPreferences.getString(SAVED_SCORE, "0");
        record.setText("Max score: " + higest_score);
        Toast.makeText(MainActivity.this, higest_score, Toast.LENGTH_LONG).show();
    }
}