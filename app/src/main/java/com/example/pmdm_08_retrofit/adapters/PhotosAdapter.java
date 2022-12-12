package com.example.pmdm_08_retrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_08_retrofit.R;
import com.example.pmdm_08_retrofit.modelos.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoVH> {

    private List<Photo> objects;
    private int resource;
    private Context context;

    public PhotosAdapter(List<Photo> objects, int resources, Context context) {
        this.objects = objects;
        this.resource = resources;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PhotoVH(LayoutInflater.from(context).inflate(resource,null));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoVH holder, int position) {
        Photo p = objects.get(position);
        holder.lblTitulo.setText(p.getTitle());
        //Necesitaremos la libreria de Picasso (Project structure, depedencies, 2 +, library dependencies, squareup.picasso ) para poder descargar las imagenes
        Picasso.get()
                .load(p.getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_foreground) //mientras esta cargando
                .error(R.drawable.ic_launcher_background) //si da error
                .into(holder.imgFoto);

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PhotoVH  extends RecyclerView.ViewHolder {

        ImageView imgFoto;
        TextView lblTitulo;

        public PhotoVH(@NonNull View itemView) {
            super(itemView);

            imgFoto = itemView.findViewById(R.id.imgImagenPhotoCard);
            lblTitulo = itemView.findViewById(R.id.lblTituloPhotoCard);

        }
    }
}
