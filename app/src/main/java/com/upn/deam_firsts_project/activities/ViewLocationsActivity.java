package com.upn.deam_firsts_project.activities;

// File: ViewLocationsActivity.java
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upn.deam_firsts_project.R;
import com.upn.deam_firsts_project.entities.LocationData;

public class ViewLocationsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        String pokemonName = getIntent().getStringExtra("pokemon_name"); // Receive the Pokémon name
        if (pokemonName == null || pokemonName.isEmpty()) {
            Toast.makeText(this, "Error: Pokémon name is missing.", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("pokemon_locations").child(pokemonName);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Log.d("ViewLocationsActivity", "DataSnapshot exists: " + snapshot.toString());
                    boolean hasMarkers = false;

                    for (DataSnapshot locationSnapshot : snapshot.getChildren()) {
                        LocationData location = locationSnapshot.getValue(LocationData.class);
                        if (location != null) {
                            LatLng latLng = new LatLng(location.latitude, location.longitude);
                            mMap.addMarker(new MarkerOptions().position(latLng).title("Pokemon Location"));
                            Log.d("ViewLocationsActivity", "Marker added at: " + location.latitude + ", " + location.longitude);
                            hasMarkers = true;
                        }
                    }

                    if (hasMarkers) {
                        // Move the camera to the first location
                        DataSnapshot firstLocation = snapshot.getChildren().iterator().next();
                        LocationData firstLocationData = firstLocation.getValue(LocationData.class);
                        if (firstLocationData != null) {
                            LatLng firstLatLng = new LatLng(firstLocationData.latitude, firstLocationData.longitude);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 1));
                        }
                    } else {
                        Log.e("ViewLocationsActivity", "No markers were added to the map.");
                    }
                } else {
                    Log.e("ViewLocationsActivity", "DataSnapshot does not exist or is empty.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ViewLocationsActivity", "Database error: " + error.getMessage());
                Toast.makeText(ViewLocationsActivity.this, "Failed to load locations: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}