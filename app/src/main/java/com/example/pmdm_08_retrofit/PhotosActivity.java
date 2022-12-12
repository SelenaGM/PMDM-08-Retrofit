package com.example.pmdm_08_retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pmdm_08_retrofit.adapters.PhotosAdapter;
import com.example.pmdm_08_retrofit.conexiones.ApiConexiones;
import com.example.pmdm_08_retrofit.conexiones.RetrofitObject;
import com.example.pmdm_08_retrofit.modelos.Photo;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PhotosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Photo> photos;
    private PhotosAdapter adapter;
    private RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        recyclerView = findViewById(R.id.contenedorPhotos);
        photos = new ArrayList<>();
        adapter = new PhotosAdapter(photos,R.layout.photo_view_holder, this);
        lm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false); //para que el scroll sea horizontal

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(lm);

        if(getIntent().getExtras().getString("ALBUM_ID")!=null){ //podremos hacer la llamada a la función
            doGetPhotos(getIntent().getExtras().getString("ALBUM_ID")); //enviará la llamada
        }
    }

    private void doGetPhotos(String albumId) {
        Retrofit retrofit = RetrofitObject.getConexion();
        ApiConexiones api = retrofit.create(ApiConexiones.class);

        Call<ArrayList<Photo>> getPhotos = api.getPhotosAlbum(albumId);

        getPhotos.enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                if(response.code() == HttpsURLConnection.HTTP_OK){
                    photos.addAll(response.body());
                    adapter.notifyItemRangeInserted(0,photos.size());
                }else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {

            }
        });

    }
}