package com.yljt.myapplication;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;



/**
 * created by yangbs on 2019/2/19
 **/
public class FirstService extends Service {

    private MyBinder binder; //绑定服务需要Binder进行交互
    private MyConn conn;

    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
        if(conn==null)
            conn = new MyConn();

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        // 与SecondSevice绑定
        FirstService.this.bindService(new Intent(this,SecondService.class),conn, Context.BIND_IMPORTANT);
    }


    //使用aidl实现进程通信

    class MyBinder extends ProcessService.Stub{

        @Override
        public String getServiceName() throws RemoteException {
            return "I am FirstService";
        }
    }

    //建立相互绑定时的连接
    class  MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Log.i("Info","与SecondService连接成功");

        }

        //在异常断开的回调方法进行拉起对方服务并绑定
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Toast.makeText(FirstService.this,"SecondService被杀死",Toast.LENGTH_SHORT).show();
            // 启动FirstService
            FirstService.this.startService(new Intent(FirstService.this,SecondService.class));
            //绑定FirstService
            FirstService.this.bindService(new Intent(FirstService.this,SecondService.class),conn, Context.BIND_IMPORTANT);
        }
    }



}

