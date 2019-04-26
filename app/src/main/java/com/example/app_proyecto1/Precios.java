package com.example.app_proyecto1;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Precios extends AppCompatActivity
{
    ArrayList<String> lo_datos;
    RecyclerView rvVista;
    FirebaseFirestore db;
    EditText etPrecios;
    Button btnConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precios);

        etPrecios = findViewById(R.id.etPrecios);
        btnConsultar = findViewById(R.id.btnConsultar);

        //BD
        db = FirebaseFirestore.getInstance();

        //Flecha atras
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Regresar");
        }

        //Referencia de objetos
        rvVista = findViewById(R.id.rvVista);
        rvVista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

    }

    public void consultar(View view)
    {
        //String precios = etPrecios.getText().toString(); //convertir a numerico
        int precios = Integer.parseInt(etPrecios.getText().toString());
        //Validación de los precios a buscar.
        /*if (precios.isEmpty())
        {
            Toast.makeText(this, "Debes ingresar un precio", Toast.LENGTH_SHORT).show();
        }
        else
        {*/
            //Intent lo_intent = new Intent(Precios.this, ConsultasDePrecios.class);
            //startActivity(lo_intent);
            //Busqueda de la propiedad 'Proteina vegetal'
            db.collection("Productos")
                    .whereEqualTo("Valor", precios)
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

                                    Toast.makeText(Precios.this, "Registros encontrados", Toast.LENGTH_SHORT).show();
                                }
                            }
                            Log.d("veg", "Nombre y valor de productos: " + lo_datos);
                            Toast.makeText(Precios.this, "Busca nuevamente", Toast.LENGTH_SHORT).show();
                        }
                    });
        //}
    }
}
