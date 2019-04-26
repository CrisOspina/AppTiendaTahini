package com.example.app_proyecto1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class    Main4Activity_registros extends AppCompatActivity
{
    private EditText etProducto, etValor;
    //private TextView tvTipo;
    private Spinner  spTipo;
    private Button   btnRegistrar;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_registros);

        //Flecha atras
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Regresar");
        }

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();

        //Referenciación de objetos del xml
        etProducto   = findViewById(R.id.etProducto);
        etValor      = findViewById(R.id.etValor);
        //tvTipo       = findViewById(R.id.tvTipo);
        spTipo       = findViewById(R.id.spTipo);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        //Spinner
        final ArrayList<String> lo_tipos = new ArrayList<>();

        //Inserto datos para elegir.
        lo_tipos.add("Escoge tipo de producto");
        lo_tipos.add("Proteina vegetal");
        lo_tipos.add("Tofus");
        lo_tipos.add("Bebidas");
        lo_tipos.add("Dulces y Snacks");
        lo_tipos.add("Cósmeticos y aseo");

        //Se crea adaptador
        ArrayAdapter lo_adp_tipos = new ArrayAdapter(Main4Activity_registros.this, android.R.layout.simple_spinner_dropdown_item, lo_tipos);
        spTipo.setAdapter(lo_adp_tipos);

        //Identificar cuando es presionado en alguno de los elementos.
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Marca posición sobre que elemento hemos seleccionado.
                String lo_tipos = (String) spTipo.getAdapter().getItem(position);
                //Mostrar mensaje
                //Toast.makeText(Main4Activity_registros.this, "Seleccionaste: " + lo_tipos, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    //Agrega registro después de ejecutar el botón.
    public void registrar(View view)
    {

        final String etproducto = etProducto.getText().toString();
        final String sptipo = spTipo.getSelectedItem().toString();

        //Se agrega datos a la Base de datos de Firebase

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();

        //Validar campos a registrar antes de enviar a la BD
        if(etproducto.isEmpty() || etValor.getText().toString().isEmpty())
        {
            Toast.makeText(Main4Activity_registros.this, "Ingresa todos los campos", Toast.LENGTH_SHORT).show();
        }
        else if(sptipo.isEmpty() || sptipo.equals("Escoge tipo de producto"))
        {
            Toast.makeText(Main4Activity_registros.this, "Elige tipo de producto", Toast.LENGTH_SHORT).show();
        }
        else
        {
            final int etvalor = Integer.parseInt(etValor.getText().toString());
            //Siempre y cuando los campos a registrar esten llenos se agregaran a la BD de firebase
            user.put("Nombre", etproducto);
            user.put("Tipo", sptipo);
            user.put("Valor", etvalor);

            // Add a new document with a generated ID
            db.collection("Productos")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Log.d("veg", "DocumentSnapshot added with ID: " + documentReference.getId());
                        //Mensaje exitoso.
                        Toast.makeText(Main4Activity_registros.this,"Registro exitoso", Toast.LENGTH_LONG).show();


                        /*
                        ****************************************************************************************************************/
                        //Notificaciones para versiones menores a 8.0
                        Intent lo_intent2 = new Intent(Main4Activity_registros.this, Main4Activity_registros.class);
                        lo_intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        PendingIntent lo_pendingIntent = PendingIntent.getActivity(Main4Activity_registros.this, 0, lo_intent2, PendingIntent.FLAG_ONE_SHOT);

                        //El request en 0 permite reemplazar la actividad y que no se acumulen en la main activity.
                        //El flag one shot nos permite que se ejecute una sola vez

                        /*
                        //Utilizo servicio de la notificación
                        NotificationManager lo_notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        //Ringtone para la notificación - sonido
                        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Notification.Builder lo_notificationBuilder = new Notification.Builder(Main4Activity_registros.this)
                                .setSmallIcon(R.drawable.ic_stat_name)
                                .setContentTitle("--- Por tiempo limitado ---")
                                .setContentText(etproducto + " con valor de " + etvalor + " " + " !APROVECHA¡ :D")
                                .setAutoCancel(true) // para que se cierre automaticamente al ser tocada por el ususrio
                                .setSound(defaultSoundUri)
                                .setContentIntent(lo_pendingIntent);

                        if (lo_notificationManager != null)
                        {
                            lo_notificationManager.notify("",0,lo_notificationBuilder.build());
                        }*/
                        /*
                         * **************************************************************************************************************/

                        FirebaseMessaging.getInstance().subscribeToTopic("todos");


                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.w("Error", "Error adding document", e);
                        Toast.makeText(Main4Activity_registros.this, "Error al ingresar, verifica", Toast.LENGTH_LONG).show();
                    }
                });
        }
    }

    //Botón inicio
    public void regresar(View view)
    {
        //Regresar al login.
        Intent lo_intent = new Intent(Main4Activity_registros.this, MainActivity.class);
        startActivity(lo_intent);
    }
}
