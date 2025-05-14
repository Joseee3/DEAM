package com.upn.deam_firsts_project;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.upn.deam_firsts_project.databinding.ActivityMapsBinding;
import com.upn.deam_firsts_project.utilis.PermissionUtilis;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationManager mLocationManager;
    private ActivityMapsBinding binding;

    private Location lasLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        if (PermissionUtilis.checkGPSPermission(this)) {
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
             lasLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

             mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 300, 0, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    if (mMap == null) return;
                        mMap.clear();
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title("Mi Ubicacion"));

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


                }
             });

        } else {
            PermissionUtilis.requestGPSPermission(this);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lasLocation.getLatitude(), lasLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}