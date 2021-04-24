package com.example.guj_to_eng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
//number_three is for learn all number
public class number_three extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numbers_three);
        Button quiz = (Button) findViewById(R.id.getanswer); // button for quiz1
        quiz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(number_three.this, numbers_quiz.class);
                startActivity(familyIntent);
            }
        });
        Button quiz1 = (Button) findViewById(R.id.getanswer1); //button for quiz2
        quiz1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(number_three.this, number_quiz2.class);
                startActivity(familyIntent);
            }
        });
    }
}
