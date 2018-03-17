package com.example.hugh.processkeep.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.hugh.processkeep.KeepServiceInterface;

/**
 * Created by 60352 on 2018/3/17.
 */

public class LocalService extends Service {

    private MyBinder myBinder;
    private MyConn myConn;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();
        if (myConn == null){
            myConn = new MyConn();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent i = new Intent(this, RemoteService.class);
        this.bindService(i,myConn,BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    class MyBinder extends KeepServiceInterface.Stub{

        @Override
        public String getName() throws RemoteException {
            return "Local_service";
        }
    }

    class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Intent i = new Intent(LocalService.this, RemoteService.class);
            LocalService.this.startService(i);
            LocalService.this.bindService(i,myConn,BIND_IMPORTANT);
        }
    }
}
