package com.yljt.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;


/**
 * created by yangbs on 2019/2/19
 **/
public class BootReceiver extends BroadcastReceiver {
    private PendingIntent mAlarmSender;
    @Override
    public void onReceive(Context context, Intent intent2) {
        Log.d("dingding","BootReceiver我启动啦");
        System.out.print("BootReceiver我启动啦");
        Toast.makeText(context,"哈哈哈哈或或或或我开机了",Toast.LENGTH_SHORT).show();

//        // 开机启动activity
//        Intent intent = context.getPackageManager().getLaunchIntentForPackage(
//                "com.alibaba.android.rimet");
//        context.startActivity(intent);

//        // 开机启动服务
//        Intent service = new Intent(context, TimeService.class);
//        context.startService(service);



//        Context context1 =  getApplicationContext();

        Intent intent1 = new Intent(context,MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent1);


        //String pkg代表包名，String download代表下载url
//        final PackageManager pm = context.getPackageManager();
//        Intent intent = pm.getLaunchIntentForPackage("com.alibaba.android.rimet");
//        if (null == intent) {//没有获取到intent
//            return;
//        } else {
//            context.startActivity(intent);
//        }
    }



}
