package com.example.pmdm_08_retrofit.conexiones;

import com.example.pmdm_08_retrofit.constantes.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {

    //LIBRERIA DE RETROFIT (dependencias, add library)--> Retrofit square-retrofit2 y el artefacto converter-json de square tambien

    public static Retrofit getConexion(){

        return new Retrofit.Builder()
                //saber la url y la herramienta
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
