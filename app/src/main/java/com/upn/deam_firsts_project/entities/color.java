package com.upn.deam_firsts_project.entities;

public class color {
    private String name;
    private String hexCode;

    public color(String name, String hexCode) {
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
