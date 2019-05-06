package com.example.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class NotificationHandler extends ContextWrapper {

    private NotificationManager manager;

    public static final String CHANEL_HIGH_ID = "1";
    private final String CHANEL_HIGH_NAME = "HIGH CHANNEL";
    public static final String CHANEL_LOW_ID = "2";
    private final String CHANEL_LOW_NAME = "LOW CHANNEL";

    public NotificationHandler(Context context) {
        super(context);
        createChanel();
    }

    public NotificationManager getManager(){
        if(manager == null){
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return  manager;
    }

    private void createChanel(){
        if(Build.VERSION.SDK_INT >= 26 ) {

            NotificationChannel highChannel =  new NotificationChannel(CHANEL_HIGH_ID,CHANEL_HIGH_NAME,NotificationManager.IMPORTANCE_HIGH);

            highChannel.enableLights(true);// Activate front leds on notifications
            highChannel.setLightColor(Color.YELLOW); // Set color of the notification led
            highChannel.setShowBadge(true);//set animation on app icon when we have notification
            highChannel.enableVibration(true);
            //highChannel.setVibrationPattern(new Long[](100L, 200L, 300L, 400L, 500L));
            highChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            //Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            //highChannel.setSound(ringtoneUri, null);

            NotificationChannel lowChannel =  new NotificationChannel(CHANEL_LOW_ID,CHANEL_LOW_NAME,NotificationManager.IMPORTANCE_LOW);
            lowChannel.enableLights(true);// Activate front leds on notifications
            lowChannel.setLightColor(Color.YELLOW); // Set color of the notification led
            lowChannel.setShowBadge(true);//set animation on app icon when we have notification

            //creating channels
            getManager().createNotificationChannel(highChannel);
            getManager().createNotificationChannel(lowChannel);

        }

    }

    /**
     *
     * @param title
     * @param message
     * @param isHighImportance
     * @return
     */
    public Notification.Builder createNotification (String title, String message, boolean isHighImportance){
        if(Build.VERSION.SDK_INT >= 26){
            if(isHighImportance == true){
                return createNotificationWithChannel(title,message,CHANEL_HIGH_ID);
            }

            return createNotificationWithChannel(title,message,CHANEL_LOW_ID);
        }

        return createNotificationWithOutChannel(title,message);

    }

    private Notification.Builder createNotificationWithChannel(String title, String message, String channelId){
        if(Build.VERSION.SDK_INT >= 26){

            PendingIntent pendingIntent = setIntent(title,message);
            Notification.Action action = new Notification.Action.Builder(
                    Icon.createWithResource(this,android.R.drawable.ic_menu_send),
                    "See details",
                    pendingIntent
                    ).build();

            return  new Notification.Builder(getApplicationContext(),channelId)
                    .setContentTitle(title)
                    .setContentText(message)
                    .addAction(action)
                    .setColor(getColor(R.color.colorPrimary))
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setAutoCancel(true);
        }
        return null;
    }

    /**
     * To version below 26
     * @param title
     * @param message
     * @return
     */
    private Notification.Builder createNotificationWithOutChannel(String title, String message){
        PendingIntent pendingIntent = setIntent(title,message);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
            Notification.Action action = new Notification.Action.Builder(
                    android.R.drawable.ic_menu_send,
                    "See details",
                    pendingIntent
            ).build();
        }

        return  new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setContentText(message)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setAutoCancel(true);
    }

    private PendingIntent setIntent(String title, String message){

        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("message", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        return  pendingIntent;
    }



}
