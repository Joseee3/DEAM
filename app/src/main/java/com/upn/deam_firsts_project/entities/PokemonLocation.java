package com.upn.deam_firsts_project.entities;

public class PokemonLocation {
    private String pokemonName;
    private double latitude;
    private double longitude;

    public PokemonLocation() {
        // Constructor vacío requerido por Firebase
    }

    public PokemonLocation(String pokemonName, double latitude, double longitude) {
        this.pokemonName = pokemonName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}