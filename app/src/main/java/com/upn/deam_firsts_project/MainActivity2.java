package com.upn.deam_firsts_project;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.deam_firsts_project.adapters.basicAdapter;
import com.upn.deam_firsts_project.entities.contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);



        List<contact> data = new ArrayList<>();

        data.add(new contact("Juan", "123456789"));
        data.add(new contact("Maria", "987654321"));
        data.add(new contact("Pedro", "456789123"));
        data.add(new contact("Ana", "321654987"));
        data.add(new contact("Luis", "789123456"));
        data.add(new contact("Sofia", "654987321"));

        RecyclerView rvBasic = findViewById(R.id.rvbasic);
        rvBasic.setLayoutManager(new LinearLayoutManager(this));

        basicAdapter adapter = new basicAdapter(data);
        rvBasic.setAdapter(adapter);
    }


    }
