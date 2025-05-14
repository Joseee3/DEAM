package com.upn.deam_firsts_project.entities;

public class LocationData {
    public double latitude;
    public double longitude;

    // Constructor vacío requerido por Firebase
    public LocationData() {
    }

    public LocationData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}