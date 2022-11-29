package com.example.pmdm_08_retrofit.modelos;

import com.google.gson.annotations.SerializedName;

public class Album {

    private int userId;
    private int id;
    @SerializedName(value = "title") //esto es para que el nombre concuerde con el de la API
    private String titulo;

    public Album() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
