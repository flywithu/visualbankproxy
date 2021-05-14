package com.flywithu.forvisualbank;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.flywithu.forvisualbank.gennoti.KBPush_Gen;
import com.flywithu.forvisualbank.gennoti.MessageGenerator;
import com.flywithu.forvisualbank.parser.KYPAY_Parser;
import com.flywithu.forvisualbank.parser.MessageParser;
import com.flywithu.forvisualbank.parser.SpendInfo;

import java.util.List;


public class NotificationListenerCardCheckService extends NotificationListenerService {

    SharedPreferences sharedPref = null;
    private static final class AppPackageNames {
        public static final String KYOUNGIPAY_NAME="gov.gyeonggi.ggcard";

    }
    private MessageGenerator msgGen = new KBPush_Gen();

    private void sendNotify(String title,String msg)
    {
        //kakao
        //String msg = "삼성(6830) 05/11 00:31\n출금 10000원 어린이날20";


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(title, title,
                            NotificationManager.IMPORTANCE_MIN);
            notificationChannel.setSound(null,null);
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            if(notificationManager.getNotificationChannel(notificationChannel.toString())==null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        Notification notification = new NotificationCompat.Builder(getBaseContext(),title)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .build();
        notificationManager.notify(0, notification);

    }

    private void generationNotification(StatusBarNotification sbn)
    {
        MessageParser parser = null;
        if(sbn.getPackageName().equals(AppPackageNames.KYOUNGIPAY_NAME))
        {

            parser = new KYPAY_Parser();
        }
        else
        {

        }
        if (parser!=null)
        {
            if(sbn.getPackageName().equals(AppPackageNames.KYOUNGIPAY_NAME))
            {
                Bundle extras = sbn.getNotification().extras;
                String title = extras.getString(Notification.EXTRA_TITLE,"None")+extras.getString(Notification.EXTRA_SUB_TEXT,"")+extras.getString(Notification.EXTRA_SUMMARY_TEXT,"");

                String contents = extras.getString(Notification.EXTRA_TEXT);
                parser.setAccount(sharedPref.getString(AppPackageNames.KYOUNGIPAY_NAME,"1111"));
                String noti = msgGen.genMessage(parser.getInfo(title,contents,sbn.getPostTime()));


                sendNotify((AppPackageNames.KYOUNGIPAY_NAME),noti);

            }
       }
    }



    @Override
    public void onListenerConnected()
    {
//        Log.i("SEUNG","onListenerConnected");
        super.onListenerConnected();
        sharedPref = getSharedPreferences(
                getPackageName(), Context.MODE_PRIVATE);


 //       sendNotify((AppPackageNames.KYOUNGIPAY_NAME),"05/11 00:00 \n1111 \n상점명 \n체크카드출금 3,000원 \n잔액 1,000원");
//        for (  StatusBarNotification sbn:super.getActiveNotifications())
//        {
////            Log.i("SEUNG",sbn.getPackageName());
//            generationNotification(sbn);
//
//        }

    }


    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        generationNotification(sbn);
    }

}