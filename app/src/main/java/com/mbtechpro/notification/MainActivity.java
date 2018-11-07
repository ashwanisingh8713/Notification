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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //https://i1.wp.com/digital-photography-school.com/wp-content/uploads/2013/07/ar-12.jpg?ssl=1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn1).setOnClickListener(v->{
            Map<String, String> data = new HashMap<>();
            data.put("icon", "https://i1.wp.com/digital-photography-school.com/wp-content/uploads/2013/07/ar-12.jpg?ssl=1");
            data.put("title", getResources().getString(R.string.notif1));
            data.put("body", "This is notification Message");
            data.put("articleId", "12344567");
            AppNotification.createNotification(this, data);
        });

        findViewById(R.id.btn2).setOnClickListener(v->{
            Map<String, String> data = new HashMap<>();
            data.put("icon", "https://i1.wp.com/digital-photography-school.com/wp-content/uploads/2013/07/ar-12.jpg?ssl=1");
            data.put("title", getResources().getString(R.string.notif2));
            data.put("body", "This is notification Message");
            data.put("articleId", "12344567");
            AppNotification.custom(this, data);
        });

        findViewById(R.id.btn3).setOnClickListener(v->{
            Map<String, String> data = new HashMap<>();
            data.put("icon", "https://i1.wp.com/digital-photography-school.com/wp-content/uploads/2013/07/ar-12.jpg?ssl=1");
            data.put("title", getResources().getString(R.string.notif3));
            data.put("body", "This is notification Message");
            data.put("articleId", "12344567");
            AppNotification.bigPictureNotification(this, data);
        });

        findViewById(R.id.btn4).setOnClickListener(v->{
            Map<String, String> data = new HashMap<>();
            data.put("icon", "https://i1.wp.com/digital-photography-school.com/wp-content/uploads/2013/07/ar-12.jpg?ssl=1");
            data.put("title", getResources().getString(R.string.notif4));
            data.put("body", "This is notification Message");
            data.put("articleId", "12344567");

        });


    }






}
