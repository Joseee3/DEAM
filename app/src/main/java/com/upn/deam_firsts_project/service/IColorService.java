package com.upn.deam_firsts_project.service;

import com.upn.deam_firsts_project.entities.Color;
import com.upn.deam_firsts_project.entities.ColorResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IColorService {

    //https://67ff053258f18d7209efd124.mockapi.io/colores

    @GET("/colores")
    Call<List<Color>> getColors();
    @POST("/colores")
    Call <Color> create (@Body Color color);


}
