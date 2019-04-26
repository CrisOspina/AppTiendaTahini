package com.example.app_proyecto1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Main5Activity_listar extends AppCompatActivity
{
    ArrayList<String> lo_datos;
    RecyclerView rvVista;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5_listar);

        //Flecha atras
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Regresar");
        }

        //BD
        db = FirebaseFirestore.getInstance();

        //Referencia de objetos
        rvVista = findViewById(R.id.rvVista);
        rvVista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        //Busqueda de la propiedad 'Proteina vegetal'
        db.collection("Productos")
                .addSnapshotListener(new EventListener<QuerySnapshot>()
                {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e)
                    {
                        if (e != null)
                        {
                            Log.w("veg", "Listen failed.", e);
                            return;
                        }
                        lo_datos = new ArrayList<String>();
                        //List<String> productos = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value)
                        {
                            if (doc.get("Nombre") != null || doc.get("Valor") != null)
                            {
                                //lo_datos.add(doc.getData().get("Nombre").toString());
                                //lo_datos.add(doc.getData().get("Valor").toString());
                                //Listar todos los productos
                                lo_datos.add(doc.getData().toString());

                                adapter_datos adapter = new adapter_datos(lo_datos);
                                rvVista.setAdapter(adapter);
                            }
                        }
                        Log.d("veg", "Nombre y valor de productos 'bebidas': " + lo_datos);
                    }
                });
    }
}
