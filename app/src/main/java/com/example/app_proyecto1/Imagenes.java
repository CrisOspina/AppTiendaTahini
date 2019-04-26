package com.example.app_proyecto1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Imagenes extends AppCompatActivity
{
    ArrayList<ProductosTahini> listaProductos;
    RecyclerView rvProductos;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);

        listaProductos = new ArrayList<>();
        rvProductos = findViewById(R.id.rvProductos);
        rvProductos.setLayoutManager(new LinearLayoutManager(this));

        llenarProductos();

        AdaptadorProductos adapter = new AdaptadorProductos(listaProductos);
        rvProductos.setAdapter(adapter);
    }

    private void llenarProductos()
    {
        listaProductos.add(new ProductosTahini("Salchichas - $9,600","Embutido a base de soya con vegetales imitación salchicha. Listas para su consumo, libre de conservantes, \n" +
                "naturalmente libre de colesterol, buena fuente de proteína.",R.drawable.salchichavegetales));
        listaProductos.add(new ProductosTahini("Aceite - $20,900","Aceite de oliva de categoría superior, obtenido directamente de aceitunas y sólo \n" +
                "mediante procedimientos mecánicos. Procedente de Agricultura Ecológica.",R.drawable.aceite));
        listaProductos.add(new ProductosTahini("Queso - $12,300","Producto elaborado a base de almendras imitación queso vegano. Libre de caseina, lactosa, gluten y soya. \n" +
                "Ideal para consumir directamente o usar en sándwiches, pastas, ensaladas, hamburguesas o \n" +
                "cualquiera de tus platos favoritos.",R.drawable.quesoalmendras));
        listaProductos.add(new ProductosTahini("Espagueti - $4,900","Espagueti con quinua naturalmente libre de colesterol, elaborado con quinua cultivada artesanalmente \n" +
                "por campesinos colombianos. Producto Kosher vegano libre de MSG (glutamato monosódico), azucar, huevo, \n" +
                "conservantes y colorantes artificiales.",R.drawable.espague));
        listaProductos.add(new ProductosTahini("Crema mani - $20,900","Crema natural de maní con chocolate vegano semiamargo. Producto 100% natural, \n" +
                "libre de conservantes, aditivos, sal y aceites añadidos. Libre de colesterol, 25% de proteína. ",R.drawable.crema));
        listaProductos.add(new ProductosTahini("Leche de almendras - $11,500","Deliciosa bebida a base de almendra, libre de lácteos, gluten y soya.",R.drawable.lechealmendras));
        listaProductos.add(new ProductosTahini("Desodorante - $22,900","Desodorante con aroma a coco 100% natural. No antitranspirante. No contiene aluminio, \n" +
                "alcohol, parabenos ni fragancias artificiales.",R.drawable.desodorante));
    }
}
