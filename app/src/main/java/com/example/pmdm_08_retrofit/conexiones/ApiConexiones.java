package com.example.pmdm_08_retrofit.conexiones;

import com.example.pmdm_08_retrofit.modelos.Album;
import com.example.pmdm_08_retrofit.modelos.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiConexiones {


    @GET("/albums") //le meteremos el endpoint para que nos traiga TODOS los albums
    Call<ArrayList<Album>> getAlbums(); //Devolverá un CALL DE RETROFIT!!!

    ///photos?albumId=2

    @GET("/photos?") //le decimos que necesita parametros con un ? sólamente
    Call<ArrayList<Photo>> getPhotosAlbum(@Query("albumId") String albumId); //el query nos pone el parametro y nosotros sólo tenemos que pasarle el número


}
