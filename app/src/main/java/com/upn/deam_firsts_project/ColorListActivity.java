package com.upn.deam_firsts_project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.deam_firsts_project.adapters.ColorAdapter;
import com.upn.deam_firsts_project.entities.Color;
import com.upn.deam_firsts_project.entities.ColorResponse;
import com.upn.deam_firsts_project.service.IColorService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ColorListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        List<Color> datacolor = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://67ff053258f18d7209efd124.mockapi.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IColorService service = retrofit.create(IColorService.class);

        service.getColors().enqueue(new Callback<List<Color>>() {
            @Override
            public void onResponse(Call<List<Color>> call, Response<List<Color>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Asignar la lista de colores directamente
                    List<Color> data = response.body();

                    ColorAdapter adapter = new ColorAdapter(data);
                    RecyclerView rvcolor = findViewById(R.id.rvcolor);
                    rvcolor.setLayoutManager(new LinearLayoutManager(ColorListActivity.this));
                    rvcolor.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Color>> call, Throwable throwable) {
                // Manejar error
            }
        });
    }
}