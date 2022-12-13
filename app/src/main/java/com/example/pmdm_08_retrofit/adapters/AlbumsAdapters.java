package com.example.pmdm_08_retrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_08_retrofit.PhotosActivity;
import com.example.pmdm_08_retrofit.R;
import com.example.pmdm_08_retrofit.conexiones.ApiConexiones;
import com.example.pmdm_08_retrofit.conexiones.RetrofitObject;
import com.example.pmdm_08_retrofit.modelos.Album;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AlbumsAdapters extends RecyclerView.Adapter<AlbumsAdapters.AlbumVH> {

    private List<Album> objects;
    private int resource;
    private Context context;

    public AlbumsAdapters(List<Album> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View albumView = LayoutInflater.from(context).inflate(resource,null); //el inflate es null porque no queremos que se enganche a otro, no es hijo de nadie
        albumView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new AlbumVH(albumView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumVH holder, int position) {
        Album a = objects.get(position);
        holder.lblTitulo.setText(a.getTitulo());

        holder.itemView.setOnClickListener(new View.OnClickListener() { //cuando seleccionemos, enviará el id al photosadapter
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , PhotosActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ALBUM_ID", String.valueOf(a.getId()));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //no le hago funcion porque no va a sacar el alertdialog para avisarnos si queremos cerrarlo, si lo quisieramos sí tendríamos
                Retrofit retrofit = RetrofitObject.getConexion();
                ApiConexiones api = retrofit.create(ApiConexiones.class);
                Call<Album> doDelete = api.deleteAlbum(String.valueOf(a.getId()));
                doDelete.enqueue(new Callback<Album>() {
                    @Override
                    public void onResponse(Call<Album> call, Response<Album> response) {
                        if(response.code() == HttpsURLConnection.HTTP_OK){
                            objects.remove(a);
                            notifyItemRemoved(holder.getAdapterPosition());
                        }
                    }

                    @Override
                    public void onFailure(Call<Album> call, Throwable t) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    public class AlbumVH extends RecyclerView.ViewHolder {

        TextView lblTitulo;
        ImageView btnEliminar;

        public AlbumVH(@NonNull View itemView) {
            super(itemView);

            lblTitulo = itemView.findViewById(R.id.lblTituloAlbumCard);
            btnEliminar = itemView.findViewById(R.id.btnEliminarAlbumCard);

        }
    }
}
