package com.example.app_proyecto1;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity_consultar extends AppCompatActivity implements View.OnClickListener
{
    //Objetos para pasar a otras actividades.
    Intent lo_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_consultar);

        //Flecha atras
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Regresar");
        }

        //Referencia de objetos
        Button btnProductos = findViewById(R.id.btnProductos);
        Button btnTipos     = findViewById(R.id.btnTipos);
        Button btnPrecios   = findViewById(R.id.btnPrecios);
        Button btnImagenes  = findViewById(R.id.btnImagenes);

        btnProductos.setOnClickListener(this);
        btnTipos.setOnClickListener(this);
        btnPrecios.setOnClickListener(this);
        btnImagenes.setOnClickListener(this);
    }

    //Botones.
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            //Listar todos los productos,
            case R.id.btnProductos:
                lo_intent = new Intent(Main3Activity_consultar.this, Main5Activity_listar.class);
                startActivity(lo_intent);
                break;

            //Escoger que tipos de productos listar.
            case R.id.btnTipos:
                lo_intent = new Intent(Main3Activity_consultar.this, Main6Activity_tipos.class);
                startActivity(lo_intent);
                break;

            //Rangos de precios a mostrar.
            case R.id.btnPrecios:
                lo_intent = new Intent(Main3Activity_consultar.this, Precios.class);
                startActivity(lo_intent);
                break;
            case R.id.btnImagenes:
                lo_intent = new Intent(Main3Activity_consultar.this, Imagenes.class);
                startActivity(lo_intent);
                break;
        }
    }
}

