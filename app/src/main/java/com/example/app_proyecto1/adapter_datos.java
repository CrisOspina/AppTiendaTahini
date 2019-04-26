package com.example.app_proyecto1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_datos extends RecyclerView.Adapter<adapter_datos.ViewHolderDatos>
{
    ArrayList<String> lo_datos;

    public adapter_datos(ArrayList<String> lo_datos)
    {
        this.lo_datos = lo_datos;
    }

    //@NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int i)
    {
        viewHolderDatos.asignarDatos(lo_datos.get(i));
    }

    @Override
    public int getItemCount()
    {
        return lo_datos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder
    {
        TextView tvDatos;

        public ViewHolderDatos(@NonNull View itemView)
        {
            super(itemView);
            tvDatos = itemView.findViewById(R.id.tvDatos);
        }

        public void asignarDatos(String dato)
        {
            tvDatos.setText(dato);
        }
    }
}
