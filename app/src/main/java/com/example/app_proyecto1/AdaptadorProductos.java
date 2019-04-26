package com.example.app_proyecto1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolderProductos>
{
    //Generar lista
    ArrayList<ProductosTahini> listaProductos;

    public AdaptadorProductos(ArrayList<ProductosTahini> listaProductos)
    {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ViewHolderProductos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_list_productos,null,false);
        return new ViewHolderProductos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProductos viewHolderProductos, int i)
    {
        viewHolderProductos.tvNombre.setText(listaProductos.get(i).getNombre());
        viewHolderProductos.tvInfo.setText(listaProductos.get(i).getInfo());
        viewHolderProductos.ivImagen.setImageResource(listaProductos.get(i).getFoto());
    }

    @Override
    public int getItemCount()
    {
        return listaProductos.size();
    }

    public class ViewHolderProductos extends RecyclerView.ViewHolder
    {
        TextView tvNombre, tvInfo;
        ImageView ivImagen;
        public ViewHolderProductos(@NonNull View itemView)
        {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvInfo = itemView.findViewById(R.id.tvInfo);
            ivImagen = itemView.findViewById(R.id.ivImagen);
        }
    }
}
