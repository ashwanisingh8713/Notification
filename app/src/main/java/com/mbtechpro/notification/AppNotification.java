package com.mbtechpro.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.RemoteViews;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import androidx.core.app.NotificationCompat;

/**
 * Created by ashwanisingh on 15/10/18.
 */

public class AppNotification {

    public static void createNotification (Context context, Map<String, String> getData) {

        String body = getData.get("body");
        String title = getData.get("title");

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

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
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent intent = new Intent(context , MainActivity.class );
        intent.putExtra("from", "pushNotification");
        for (Map.Entry<String, String> entry : getData.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            intent.putExtra(key, val);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent resultIntent = PendingIntent.getActivity( context , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_stat_info);

        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder( context, ChannelID)
                .setSmallIcon(getNotificationIcon())
                .setLargeIcon(largeIcon)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel( true )
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        notificationManager.notify(0, mNotificationBuilder.build());
    }


    /**
     * This is RemoteView Notification with Image
     * @param context
     * @param getData
     */
    public static void customNotificationWithImage(Context context, Map<String, String> getData) {

        String body = getData.get("body");
        String title = getData.get("title");

        int icon = R.mipmap.ic_launcher;
        long when = System.currentTimeMillis();

        //generating unique ID
        int uniqueID = (int) System.currentTimeMillis();

        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

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


        RemoteViews smallView=new RemoteViews(context.getPackageName(),R.layout.notification_small);
        smallView.setImageViewResource(R.id.smallImg, R.mipmap.ic_launcher);
        smallView.setTextViewText(R.id.titleTxt, title);
        smallView.setTextViewText(R.id.msgTxt, body);

        RemoteViews largeView = new RemoteViews(context.getPackageName(), R.layout.notification_large);
        largeView.setImageViewResource(R.id.smallImg, R.mipmap.ic_launcher);
        largeView.setImageViewResource(R.id.largeImg, R.drawable.banner);
        largeView.setTextViewText(R.id.titleTxt, title);
        largeView.setTextViewText(R.id.msgTxt, body);

        Notification notification = new NotificationCompat.Builder(context, ChannelID)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setWhen(when)
                .setCustomContentView(smallView)
                .setCustomBigContentView(largeView)
                .build();


        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, uniqueID, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);
        notification.contentIntent = contentIntent;


        notification.flags |= Notification.FLAG_AUTO_CANCEL; // clear the notification
        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
        notification.defaults |= Notification.DEFAULT_SOUND; // Sound



        mNotificationManager.notify(uniqueID, notification);

    }


    private static void customNotificationWithImageUrl(Context context, Map<String, String> getData, Bitmap bitmap) {

        String body = getData.get("body");
        String title = getData.get("title");

        int icon = R.mipmap.ic_launcher;
        long when = System.currentTimeMillis();

        //generating unique ID
        int uniqueID = (int) System.currentTimeMillis();

        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

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


        RemoteViews smallView=new RemoteViews(context.getPackageName(),R.layout.notification_small);
        smallView.setImageViewBitmap(R.id.smallImg, bitmap);
        smallView.setTextViewText(R.id.titleTxt, title);
        smallView.setTextViewText(R.id.msgTxt, body);

        RemoteViews largeView = new RemoteViews(context.getPackageName(), R.layout.notification_large);
        largeView.setImageViewBitmap(R.id.smallImg, bitmap);
        largeView.setImageViewBitmap(R.id.largeImg, bitmap);
        largeView.setTextViewText(R.id.titleTxt, title);
        largeView.setTextViewText(R.id.msgTxt, body);

        Notification notification = new NotificationCompat.Builder(context, ChannelID)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setWhen(when)
                .setCustomContentView(smallView)
                .setCustomBigContentView(largeView)
                .build();


        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, uniqueID, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);
        notification.contentIntent = contentIntent;


        notification.flags |= Notification.FLAG_AUTO_CANCEL; // clear the notification
        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
        notification.defaults |= Notification.DEFAULT_SOUND; // Sound



        mNotificationManager.notify(uniqueID, notification);

    }

    public static void customNotificationWithImageUrl(Context context, Map<String, String> getData) {
        String imgUrl = getData.get("icon");
        new CustomNotificationWithImgUrlTask(context, getData).execute(imgUrl);
    }
    /**
     * It gives notification small icon.
     * @return
     */
    private static int getNotificationIcon() {
        //"Look, white in Lollipop, else color!"
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.ic_stat_info : R.mipmap.ic_launcher;
    }

    /**
     * It creates Default Big Picture Notification
     * @param context
     * @param getData
     */
    public static void bigPictureNotification(Context context, Map<String, String> getData) {
        String imgUrl = getData.get("icon");
        new DefaultBigPictureNotificationTask(context, getData).execute(imgUrl);
    }

    /**
     * It creates Default BigPicture Style Notification
     * @param getData
     * @param context
     * @param bigPicture
     */
    private static void createBigPictureNotification(Map<String, String> getData, Context context, Bitmap bigPicture) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        String articleId = getData.get("articleId");
        String imgUrl = getData.get("icon");
        String body = getData.get("body");
        String title = getData.get("title");

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
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        notificationIntent.putExtra("from", "pushNotification");
//        boolean isAutoOpen = Boolean.parseBoolean(getData.get("isAutoOpen"));

        notificationIntent.putExtra("articleId", articleId);
        notificationIntent.putExtra("body", body);
        notificationIntent.putExtra("icon", imgUrl);
        notificationIntent.putExtra("title", title);

        //generating unique ID
        int uniqueID = (int) System.currentTimeMillis();

        PendingIntent resultIntent = PendingIntent.getActivity( context.getApplicationContext() , uniqueID, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        long when = System.currentTimeMillis();

        Notification notification = new NotificationCompat.Builder(context, ChannelID)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(resultIntent)
                .setSmallIcon(getNotificationIcon())
                .setWhen(when)
                .setAutoCancel( true )
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bigPicture).setSummaryText(body))
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        notificationManager.notify(uniqueID, notification);
    }

    /**
     * Downloads Bitmap image from URL and shows notification.
     */
    private static class DefaultBigPictureNotificationTask extends AsyncTask<String, Void, Bitmap> {

        Context ctx;
        Map<String, String> getData;

        public DefaultBigPictureNotificationTask(Context context, Map<String, String> getData) {
            super();
            this.ctx = context;
            this.getData = getData;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            InputStream in;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            try {
                createBigPictureNotification(getData, ctx, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Downloads Bitmap image from URL and shows notification.
     */
    private static class CustomNotificationWithImgUrlTask extends AsyncTask<String, Void, Bitmap> {

        Context ctx;
        Map<String, String> getData;

        public CustomNotificationWithImgUrlTask(Context context, Map<String, String> getData) {
            super();
            this.ctx = context;
            this.getData = getData;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            InputStream in;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            try {
                customNotificationWithImageUrl(ctx, getData, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, String> makeData() {
        Map<String, String> data = new HashMap<>();
        data.put("icon", "https://i1.wp.com/digital-photography-school.com/wp-content/uploads/2013/07/ar-12.jpg?ssl=1");
        data.put("title", "This is notification title");
        data.put("body", "This is notification Message");
        data.put("articleId", "12344567");
        return data;
    }

}
