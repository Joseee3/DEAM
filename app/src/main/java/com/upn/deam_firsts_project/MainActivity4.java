package com.upn.deam_firsts_project;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.deam_firsts_project.adapters.colorAdapter;
import com.upn.deam_firsts_project.entities.color;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        List<color> datacolor = new ArrayList<>();

        datacolor.add(new color("Rojo", "#FF0000"));
        datacolor.add(new color("Verde", "#00FF00"));
        datacolor.add(new color("Azul", "#0000FF"));
        datacolor.add(new color("Amarillo", "#FFFF00"));
        datacolor.add(new color( "Naranja", "#FFA500"));
        datacolor.add(new color("Violeta", "#800080"));
        datacolor.add(new color("Cyan", "#00FFFF"));
        datacolor.add(new color("Magenta", "#FF00FF"));
        datacolor.add(new color("Gris", "#808080"));
        datacolor.add(new color("Negro", "#000000"));
        datacolor.add(new color("Blanco", "#FFFFFF"));
        datacolor.add(new color("Marrón", "#A52A2A"));
        datacolor.add(new color("Rosa", "#FFC0CB"));
        datacolor.add(new color("Turquesa", "#40E0D0"));
        datacolor.add(new color("Lima", "#00FF00"));

        RecyclerView rvcolor = findViewById(R.id.rvcolor);
        rvcolor.setLayoutManager(new LinearLayoutManager(this));

        colorAdapter adapter = new colorAdapter(datacolor);
        rvcolor.setAdapter(adapter);
    }
}