package com.example.pmdm_08_retrofit.conexiones;

import com.example.pmdm_08_retrofit.modelos.Album;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiConexiones {


    @GET("/albums") //le meteremos el endpoint al que queremos acceder
    Call<ArrayList<Album>> getAlbums(); //Devolverá un CALL DE RETROFIT!!!




}
