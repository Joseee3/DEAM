package com.upn.deam_firsts_project.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Color {
    @SerializedName("name")
    private String name;
    @SerializedName("color_hex")
    private String hexCode;

    public Color(String name, String hexCode) {
        this.name = name;
        this.hexCode = hexCode;
    }

    public String getName() {
        return name;
    }

    public String getHexCode() {
        return hexCode;
    }
}
