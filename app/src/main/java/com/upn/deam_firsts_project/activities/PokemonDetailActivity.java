package com.upn.deam_firsts_project.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.upn.deam_firsts_project.R;
import com.upn.deam_firsts_project.entities.PokemonDetail;
import com.upn.deam_firsts_project.service.IPokemonService;

import retrofit2.Callback;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonDetailActivity extends AppCompatActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_pokemon);

        // Vincular elementos de la UI
        ImageView ivPokemonImage = findViewById(R.id.ivPokemonImage);
        TextView tvPokemonName = findViewById(R.id.tvPokemonName);
        TextView tvPokemonTypes = findViewById(R.id.tvPokemonTypes);

        String pokemonName = getIntent().getStringExtra("pokemon_name");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IPokemonService service = retrofit.create(IPokemonService.class);
        service.getPokemonDetail(pokemonName).enqueue(new Callback<PokemonDetail>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonDetail detail = response.body();

                    // Mostrar datos en la UI
                    tvPokemonName.setText(detail.getName());
                    StringBuilder typesBuilder = new StringBuilder();
                    for (PokemonDetail.TypeWrapper typeWrapper : detail.getTypes()) {
                        if (typesBuilder.length() > 0) {
                            typesBuilder.append(", ");
                        }
                        typesBuilder.append(typeWrapper.getType().getName());
                    }
                    tvPokemonTypes.setText(typesBuilder.toString());

                    Glide.with(PokemonDetailActivity.this)
                            .load(detail.getSprites().getFrontDefault())
                            .into(ivPokemonImage);
                                    }
                                }

            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {
                // Manejar error
            }
        });
    }
}
