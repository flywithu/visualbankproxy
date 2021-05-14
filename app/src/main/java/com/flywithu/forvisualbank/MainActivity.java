package com.flywithu.forvisualbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.flywithu.forvisualbank.listview.ListViewAdapter;
import com.flywithu.forvisualbank.listview.ListViewItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isPermissionAllowed = isNotiPermissionAllowed();

        if(!isPermissionAllowed) {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            SharedPreferences sharedPref = getSharedPreferences(
                    getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
            ListView listview = (ListView)findViewById(R.id.listview1);




            ListViewAdapter adapter = new ListViewAdapter(new View.OnClickListener() {
                //Button Click
                @Override
                public  void onClick(View v) {
                    ListViewAdapter lv = (ListViewAdapter)listview.getAdapter();
                    Integer position = (Integer)v.getTag();
                    String pg_name = ((ListViewItem)lv.getItem(position)).getPg_name();
                    if (position == 0)
                    {
                        for (int i=1;i<lv.getCount();i++)
                        {
                            ListViewItem item = (ListViewItem)lv.getItem(i);
                            editor.putString(item.getPg_name(),item.getAccount_name());

                        }
                        editor.apply();
                    }
                    else {
                        lv.delItem(position);
                        lv.notifyDataSetChanged();
                        if (lv.getCount() <= 1) {
                            findViewById(R.id.listview1).setVisibility(View.GONE);
                        }
                        notificationManager.deleteNotificationChannel(pg_name);
                    }
                }
            });




            adapter.addItem(getString(R.string.channel_name),getString(R.string.channel_account));
            for(NotificationChannel channel: notificationManager.getNotificationChannels())
            {

                String account = sharedPref.getString(channel.getName().toString(),"1111");
                adapter.addItem(channel.getName().toString(),account);
            }
            listview.setAdapter(adapter);


            if(adapter.getCount()>1)
            {
                findViewById(R.id.listview1).setVisibility(View.VISIBLE);
            }

        }
        WebView browser = (WebView)findViewById(R.id.webview);
        browser.loadUrl("https://www.flywithu.com/visualbank/");



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
    private boolean isNotiPermissionAllowed() {
        Set<String> notiListenerSet = NotificationManagerCompat.getEnabledListenerPackages(this);
        String myPackageName = getPackageName();

        for(String packageName : notiListenerSet) {
            if(packageName == null) {
                continue;
            }
            if(packageName.equals(myPackageName)) {
                return true;
            }
        }

        return false;
    }
}