package com.upn.deam_firsts_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            Button button = findViewById(R.id.button);

            TextView textView = findViewById(R.id.textView);

            TextView textView2 = findViewById(R.id.textView2);

            button.setOnClickListener(v -> {
               contador++;
                textView.setText("Hola Clase 2025 -1 !");
                textView2.setText(String.valueOf(contador));

//                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//
//                startActivity(intent);
            });
    }




}