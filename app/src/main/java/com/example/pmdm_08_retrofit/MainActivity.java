package com.example.pmdm_08_retrofit;

import android.content.DialogInterface;
import android.os.Bundle;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.pmdm_08_retrofit.adapters.AlbumsAdapters;
import com.example.pmdm_08_retrofit.conexiones.ApiConexiones;
import com.example.pmdm_08_retrofit.conexiones.RetrofitObject;
import com.example.pmdm_08_retrofit.databinding.ActivityMainBinding;
import com.example.pmdm_08_retrofit.modelos.Album;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    private List<Album> albums;
    private AlbumsAdapters adapter;
    private RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        albums = new ArrayList<>();
        adapter = new AlbumsAdapters(albums, R.layout.album_view_holder, this);
        lm = new LinearLayoutManager(this);

        binding.contentMain.contenedorAlbums.setAdapter(adapter);
        binding.contentMain.contenedorAlbums.setLayoutManager(lm);


        //No rellenaremos de info este recycler view con el alert dialog ni actividad, se rellenará conectandonos a la API:
        //Antes de hacer la llamada tiene que estar el adapter construido o petará

        doGetAlbums();



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarAlbum().show();

            }
        });
    }

    private AlertDialog insertarAlbum() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Crear album");
        EditText txtTitulo = new EditText(this);
        builder.setView(txtTitulo);
        builder.setCancelable(false);
        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("INSERTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!txtTitulo.getText().toString().isEmpty()){
                    Album a = new Album();
                    a.setUserId(1);
                    a.setTitulo(txtTitulo.getText().toString());
                    doPostAlbum(a);
                }

            }
        });

        return builder.create();
    }

    private void doPostAlbum(Album album) {
        Retrofit retrofit = RetrofitObject.getConexion();
        ApiConexiones api = retrofit.create(ApiConexiones.class);
        Call<Album> postAlbum = api.postAlbum(album);

        postAlbum.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                if(response.code() == HttpsURLConnection.HTTP_CREATED){
                    albums.add(0, response.body()); //insertamos el que nos devuelve la api en el body del response
                    adapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {

            }
        });

    }

    private void doGetAlbums() {
        //retrofit se encargará de coger los objetos
        Retrofit retrofit = RetrofitObject.getConexion();
        //no creamos un new apiconexiones porque será más dificil acceder, lo hacemos asi:
        ApiConexiones api = retrofit.create(ApiConexiones.class);

        //retorna un objeto de tipo Call de un arraylist de objeto album
        Call<ArrayList<Album>> getAlbums = api.getAlbums();

        getAlbums.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                //si va bien
                if(response.code() == HttpsURLConnection.HTTP_OK) //200 es el codigo de que esta OK
                {
                    ArrayList<Album> temp = response.body(); //si esta bien, el arraylist se encontrará en el body de response
                    albums.addAll(temp); //se añade al array
                    adapter.notifyItemRangeInserted(0, temp.size()); //se notifican el insertado del array

                }else{
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
                //si algo falla
                Toast.makeText(MainActivity.this, "NO TIENES INTERNET", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE", t.getLocalizedMessage()); //para que imprima el error de lo sucedido
            }
        });


    }

}