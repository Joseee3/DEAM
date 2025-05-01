package com.upn.deam_firsts_project.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upn.deam_firsts_project.R;
import com.upn.deam_firsts_project.adapters.PokemonAdapter;
import com.upn.deam_firsts_project.entities.Pokemon;
import com.upn.deam_firsts_project.entities.PokemonResponse;
import com.upn.deam_firsts_project.service.IPokemonService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class PokemonActivity extends AppCompatActivity {
    private int offset = 0;
    private final int limit = 20;
    private boolean isLoading = false;
    private List<Pokemon> pokemonList = new ArrayList<>();
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        RecyclerView recyclerView = findViewById(R.id.rvpokemon);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PokemonAdapter(pokemonList, pokemon -> {
            Intent intent = new Intent(this, PokemonDetailActivity.class);
            intent.putExtra("pokemon_name", pokemon.getName());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        // Agregar el scroll listener para la paginación
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1) && !isLoading) { // Verifica si llegó al final
                    offset += limit; // Incrementa el offset
                    loadPokemon(offset, limit); // Carga más datos
                }
            }
        });

        loadPokemon(offset, limit);
    }

    private void loadPokemon(int offset, int limit) {
        isLoading = true; // Indica que se está cargando
        IPokemonService service = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IPokemonService.class);

        service.getPokemonList(offset, limit).enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pokemon> newPokemonList = response.body().getResults();
                    pokemonList.addAll(newPokemonList); // Agrega los nuevos datos
                    adapter.notifyDataSetChanged(); // Notifica al adaptador
                }
                isLoading = false; // Finaliza la carga
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                isLoading = false; // Finaliza la carga en caso de error
            }
        });
    }
}