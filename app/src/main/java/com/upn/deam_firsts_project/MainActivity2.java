package com.upn.deam_firsts_project;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.deam_firsts_project.adapters.BasicAdapter;
import com.upn.deam_firsts_project.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);



        List<Contact> data = new ArrayList<>();

//        data.add(new Contact("Juan", "123456789"));
//        data.add(new Contact("Maria", "987654321"));
//        data.add(new Contact("Pedro", "456789123"));
//        data.add(new Contact("Ana", "321654987"));
//        data.add(new Contact("Luis", "789123456"));
//        data.add(new Contact("Sofia", "654987321"));

        RecyclerView rvBasic = findViewById(R.id.rvbasic);
        rvBasic.setLayoutManager(new LinearLayoutManager(this));

        BasicAdapter adapter = new BasicAdapter(data);
        rvBasic.setAdapter(adapter);
    }


    }
