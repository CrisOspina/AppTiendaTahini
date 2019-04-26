package com.example.app_proyecto1;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Main6Activity_tipos extends AppCompatActivity implements View.OnClickListener
{
    //Objeto para pasar de actividad.
    Intent lo_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6_tipos);

        //Flecha atras
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Regresar");
        }

        //Rerencio objetos.
        Button btnProteina = findViewById(R.id.btnProteina);
        Button btnTofus    = findViewById(R.id.btnTofu);
        Button btnBebidas  = findViewById(R.id.btnBebida);
        Button btnDulces   = findViewById(R.id.btnDulces);
        Button btnAseo     = findViewById(R.id.btnAseo);
        Button btnImagenes = findViewById(R.id.btnImagenes);

        btnProteina.setOnClickListener(this);
        btnTofus.setOnClickListener(this);
        btnBebidas.setOnClickListener(this);
        btnDulces.setOnClickListener(this);
        btnAseo.setOnClickListener(this);
    }

    //Botones.
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnProteina:
                lo_intent = new Intent(Main6Activity_tipos.this, Main7Activity_proteina.class);
                startActivity(lo_intent);
                break;

            case R.id.btnTofu:
                lo_intent = new Intent(Main6Activity_tipos.this, Main8Activity_tofus.class);
                startActivity(lo_intent);
                break;

            case R.id.btnBebida:
                lo_intent = new Intent(Main6Activity_tipos.this, Main9Activity_bebidas.class);
                startActivity(lo_intent);
                break;

            case R.id.btnDulces:
                lo_intent = new Intent(Main6Activity_tipos.this, Main10Activity_dulces.class);
                startActivity(lo_intent);
                break;

            case R.id.btnAseo:
                lo_intent = new Intent(Main6Activity_tipos.this, Main11Activity_aseo.class);
                startActivity(lo_intent);
                break;
        }
    }
}
