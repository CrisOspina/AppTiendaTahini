package com.example.app_proyecto1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    //Objeto para pasar de actividad.
    Intent lo_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencio objetos anteriores
        Button btnIngresar  = findViewById(R.id.btnIngresar);
        Button btnConsultar = findViewById(R.id.btnConsultar);

        btnIngresar.setOnClickListener(this);
        btnConsultar.setOnClickListener(this);



        /*
        //Notificaciones para versiones menores a 8.0
        Intent lo_intent2 = new Intent(this, MainActivity.class);
        lo_intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent lo_pendingIntent = PendingIntent.getActivity(this, 0, lo_intent2, PendingIntent.FLAG_ONE_SHOT);

        //El request en 0 permite reemplazar la actividad y que no se acumulen en la main activity.
        //El flag one shot nos permite que se ejecute una sola vez

        //Utilizo servicio de la notificación
        NotificationManager lo_notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        //Ringtone para la notificación - sonido
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification.Builder lo_notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("hola")
                .setContentText("Bienvenido")
                .setAutoCancel(true) // para que se cierre automaticamente al ser tocada por el ususrio
                .setSound(defaultSoundUri)
                .setContentIntent(lo_pendingIntent);

        //Si se posee android 8 o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = getString(R.string.channel_id);
            String chanelName = getString(R.string.normal_channel_name);
            NotificationChannel lo_channel = new NotificationChannel(channelId,chanelName,lo_notificationManager.IMPORTANCE_DEFAULT);
            lo_channel.enableVibration(true);
            lo_channel.setVibrationPattern(new long[]{100,200,200,50});

            if (lo_notificationManager != null)
            {
                lo_notificationManager.createNotificationChannel(lo_channel);
            }
            lo_notificationBuilder.setChannelId(channelId);
        }

        //preguntar
        if (lo_notificationManager != null)
        {
            lo_notificationManager.notify("",0,lo_notificationBuilder.build());
        }*/
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            //Botón ingresar.
            case R.id.btnIngresar:
                lo_intent = new Intent(MainActivity.this, Main2Activity_ingresar.class);
                startActivity(lo_intent);
                break;
             //Botón consultar.
            case R.id.btnConsultar:
                lo_intent = new Intent(MainActivity.this, Main3Activity_consultar.class);
                startActivity(lo_intent);
                break;
        }
    }
}
