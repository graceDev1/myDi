package com.example.codler_fun.mycoachdbt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.jjoe64.graphview.GraphView;

public class MainActivity extends AppCompatActivity {

    CardView food,sport,glycemie,medecine,assistance,insiline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        food =  findViewById(R.id.food);
        sport =  findViewById(R.id.sport);
        glycemie = findViewById(R.id.glycenie);
        medecine =  findViewById(R.id.medecine);
        assistance = findViewById(R.id.assistance);



        // for start glycemie activity
        glycemie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,Glycemie.class);
                startActivity(i);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Food.class);
                startActivity(intent);
            }
        });
        // for start sport



      //  medecine

        medecine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(MainActivity.this,Medoc.class);
                startActivity(m);
            }
        });

        // start sport
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sport = new Intent(MainActivity.this, Sport.class);
                startActivity(sport);
            }
        });

        // assistance

        assistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent chart = new Intent(MainActivity.this, GrapActivity.class);
                startActivity(chart);
            }
        });

   }
}
