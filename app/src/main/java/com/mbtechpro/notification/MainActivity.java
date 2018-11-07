package com.mbtechpro.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int icon = R.mipmap.ic_launcher;
        long when = System.currentTimeMillis();



        NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        CharSequence name="APP ss";
        String desc="this is notific";
        int imp=NotificationManager.IMPORTANCE_HIGH;
        final String ChannelID="my_channel_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(ChannelID, name, imp);
            mChannel.setDescription(desc);
            mChannel.setLightColor(Color.CYAN);
            mChannel.canShowBadge();
            mChannel.setShowBadge(true);
            mNotificationManager.createNotificationChannel(mChannel);
        }

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_layout_notification);
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        contentView.setTextViewText(R.id.title, "Custom notification");
        contentView.setTextViewText(R.id.text, "This is a custom layout");

        RemoteViews smallView=new RemoteViews(getPackageName(),R.layout.notification_small);
        smallView.setImageViewResource(R.id.smallImg, R.mipmap.ic_launcher);
        smallView.setTextViewText(R.id.titleTxt, "msgnotification.getTitle()");
        smallView.setTextViewText(R.id.msgTxt, "msgnotification.getBody()");

        RemoteViews largeView = new RemoteViews(getPackageName(), R.layout.notification_large);
        largeView.setImageViewResource(R.id.smallImg, R.mipmap.ic_launcher);
        largeView.setTextViewText(R.id.titleTxt, "msgnotification.getTitle()");
        largeView.setTextViewText(R.id.msgTxt, "msgnotification.getBody()");

        Notification notification = new NotificationCompat.Builder(this, ChannelID)
                .setSmallIcon(icon)
                .setContent(contentView)
                .setContentTitle("Custom Notification")
                .setWhen(when)
                .setCustomContentView(smallView)
                .setCustomBigContentView(largeView)
                .build();


        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.contentIntent = contentIntent;


        notification.flags |= Notification.FLAG_NO_CLEAR; //Do not clear the notification
        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
        notification.defaults |= Notification.DEFAULT_SOUND; // Sound



        mNotificationManager.notify(1, notification);

    }


}
