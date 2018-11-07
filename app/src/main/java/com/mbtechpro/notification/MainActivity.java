package com.mbtechpro.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
            data.put("title", getResources().getString(R.string.notif1));
            data.put("body", "This is notification Message");
            AppNotification.createNotification(this, data);
        });

        findViewById(R.id.btn2).setOnClickListener(v->{
            Map<String, String> data = new HashMap<>();
            data.put("icon", "https://i1.wp.com/digital-photography-school.com/wp-content/uploads/2013/07/ar-12.jpg?ssl=1");
            data.put("title", getResources().getString(R.string.notif2));
            data.put("body", "This is notification Message");
            data.put("articleId", "12344567");
            AppNotification.customNotificationWithImage(this, data);
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
            data.put("icon", "https://s11986.pcdn.co/wp-content/uploads/2016/03/widescreen-iphone-photo.jpg");
            data.put("title", getResources().getString(R.string.notif4));
            data.put("body", "This is notification Message");
            data.put("articleId", "12344567");
            AppNotification.customNotificationWithImageUrl(this, data);

        });


    }






}
