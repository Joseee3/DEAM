package com.upn.deam_firsts_project.activities;

// File: AddLocationActivity.java
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upn.deam_firsts_project.R;
import com.upn.deam_firsts_project.entities.LocationData;

public class AddLocationActivity extends AppCompatActivity {

    EditText etLatitude, etLongitude;
    Button btnSaveLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        btnSaveLocation = findViewById(R.id.btnSaveLocation);

        btnSaveLocation.setOnClickListener(v -> {
            String latitude = etLatitude.getText().toString();
            String longitude = etLongitude.getText().toString();

            if (latitude.isEmpty() || longitude.isEmpty()) {
                Toast.makeText(this, "Please enter both latitude and longitude", Toast.LENGTH_SHORT).show();
                return;
            }

            String pokemonName = getIntent().getStringExtra("pokemon_name");
            if (pokemonName == null || pokemonName.isEmpty()) {
                Toast.makeText(this, "Error: Pokémon name is missing.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double lat = Double.parseDouble(latitude);
                double lon = Double.parseDouble(longitude);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("pokemon_locations").child(pokemonName);
                String key = ref.push().getKey(); // Genera una clave única
                if (key != null) {
                    LocationData locationData = new LocationData(lat, lon);
                    ref.child(key).setValue(locationData)
                        .addOnSuccessListener(aVoid -> {
                            Log.d("AddLocation", "Location added successfully");
                            Toast.makeText(this, "Location saved successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Log.e("AddLocation", "Failed to add location: " + e.getMessage());
                            Toast.makeText(this, "Failed to save location: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                } else {
                    Log.e("AddLocation", "Failed to generate a unique key.");
                    Toast.makeText(this, "Error: Could not generate a unique key.", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid latitude or longitude format.", Toast.LENGTH_SHORT).show();
                Log.e("AddLocation", "Invalid number format: " + e.getMessage());
            }
        });
    }

}