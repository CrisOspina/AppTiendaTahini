package com.example.app_proyecto1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService extends FirebaseMessagingService
{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size()>0 && remoteMessage.getNotification() != null)
        {
            String title = remoteMessage.getNotification().getTitle();
            String body   = remoteMessage.getNotification().getBody();
            createNotification(title, body);
        }
    }

    public void createNotification(String title, String body)
    {
        Intent lo_intent2 = new Intent(this, MainActivity.class);
        lo_intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent lo_pendingIntent = PendingIntent.getActivity(this, 0, lo_intent2, PendingIntent.FLAG_ONE_SHOT);

        NotificationManager lo_notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        //Ringtone para la notificación - sonido
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification.Builder lo_notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(body)
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
        }


    }
}
