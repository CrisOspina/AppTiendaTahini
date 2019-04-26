package com.example.app_proyecto1;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Main11Activity_aseo extends AppCompatActivity
{
    ArrayList<String> lo_datos;
    RecyclerView rvVista;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11_aseo);

        //Referencia a firebase bd.
        db = FirebaseFirestore.getInstance();

        //Referencia de objetos
        rvVista = findViewById(R.id.rvVista);
        rvVista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        //Consulta nombre y valor del tipo 'Cósmeticos y aseo'
        busqueda_aseo();
    }

    //Consulta nombre y valor del tipo 'Cósmeticos y aseo'
    public void busqueda_aseo()
    {
        //Busqueda de la propiedad 'Proteina vegetal'
        db.collection("Productos")
                .whereEqualTo("Tipo", "Cósmeticos y aseo")
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
                        for (QueryDocumentSnapshot doc : value)
                        {
                            if (doc.get("Nombre") != null || doc.get("Valor") != null)
                            {
                                lo_datos.add(doc.getData().get("Nombre").toString());
                                lo_datos.add(doc.getData().get("Valor").toString());

                                adapter_datos adapter = new adapter_datos(lo_datos);
                                rvVista.setAdapter(adapter);
                            }
                        }
                        Log.d("veg", "Nombre y valor de productos 'Cósmeticos y aseo': " + lo_datos);
                    }
                });
    }
}
