package com.example.pmdm_08_retrofit.conexiones;

import com.example.pmdm_08_retrofit.modelos.Album;
import com.example.pmdm_08_retrofit.modelos.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConexiones {

    /**
     * Traernos información
     * GETS -> all para filtrarlos todos / filtrar por parametro/filtrar por path
     * @return
     */

    @GET("/albums") //le meteremos el endpoint para que nos traiga TODOS los albums
    Call<ArrayList<Album>> getAlbums(); //Devolverá un CALL DE RETROFIT!!!

    // /photos?albumId=2
    @GET("/photos?") //le decimos que necesita parametros con un ? sólamente
    Call<ArrayList<Photo>> getPhotosAlbum(@Query("albumId") String albumId); //el query nos pone el parametro y nosotros sólo tenemos que pasarle el número


    // /albums/{2}/photos -> con las llaves es la forma que tenemos para decirle que lo sustituya por otra cosa
    @GET("/albums/{albumId}/photos")
    Call<ArrayList<Photo>> getPhotosAlbumPath(@Path("albumId")String id);


    /**
     * CREAR INFORMACION
     * POST -> La petición tiene que ser tipo POST
     */
    @POST("/albums")
    Call<Album> postAlbum(@Body Album a);
    //sólo recibe un objeto

    //OTRA ALTERNATIVA CON FORMATO FORM

    @FormUrlEncoded
    @POST("/albums")
    Call<Album> postAlbumForm(@Field("userId")int idUser, @Field("title") String titulo );
    //se le pasaría el titulo y el id en la función del main


    /**
     * Eliminar
     * DELETE
     */

    @DELETE("/albums/{id}")
    Call<Album> deleteAlbum(@Path("id")String id);




}
