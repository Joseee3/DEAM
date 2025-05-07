package com.upn.deam_firsts_project.entities;

public class Contact {
    public String id;
    public String name;
    public String phone;
    public String gender;
    public String address;
    public String imageUrl;

    public Contact() {
        // Constructor vacío requerido por Firebase
    }

    public Contact(String id, String name, String phone, String gender, String address, String imageUrl) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.imageUrl = imageUrl;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


}