package com.hui.realservicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private DownloadBinder mBinder = new DownloadBinder();

    String CHANNEL_ONE_ID = "CHANNEL_ONE_ID";
//    String CHANNEL_ONE_NAME= "CHANNEL_ONE_ID";
//    NotificationChannel notificationChannel= null;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: MyService");

//        //进行8.0的判断
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            notificationChannel= new NotificationChannel(CHANNEL_ONE_ID,
//                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.setShowBadge(true);
//            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//            NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            manager.createNotificationChannel(notificationChannel);
//        }

        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        Notification notification=new NotificationCompat.Builder(this).setContentTitle("通知标题")
                .setContentText("内容显示").setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)).setContentIntent(pi)
                .setChannelId(CHANNEL_ONE_ID)//出现Bad notification for startForeground问题时添加此句
                .build();
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: onStartCommand executed");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //specific logic
                Log.e(TAG, "run: logic in start command run methods");
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class DownloadBinder extends Binder {
        private static final String TAG = "DownloadBinder";

        public void startDownload() {
            Log.e(TAG, "startDownload: 开启下载");
        }

        public int getProgress() {
            Log.e(TAG, "getProgress: 获取进度");
            return 0;
        }
    }
}