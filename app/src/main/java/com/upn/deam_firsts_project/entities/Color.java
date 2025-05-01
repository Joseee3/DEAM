package com.upn.deam_firsts_project.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Color {

    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("color_hex")
    public String hexCode;

    public Color() {
        // Default constructor
    }

    public Color(String name, String hexCode) {
        this.name = name;
        this.hexCode = hexCode;
    }

}
