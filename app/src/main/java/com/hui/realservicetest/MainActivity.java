package com.hui.realservicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button start_service_btn,stop_service_btn,bind_service_btn,unbind_service_btn;
    private MyService.DownloadBinder downloadBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_service_btn=findViewById(R.id.start_service);
        stop_service_btn=findViewById(R.id.stop_service);
        start_service_btn.setOnClickListener(this);
        stop_service_btn.setOnClickListener(this);
        bind_service_btn=findViewById(R.id.main_btn_bind);
        unbind_service_btn=findViewById(R.id.main_btn_unbind);
        bind_service_btn.setOnClickListener(this);
        unbind_service_btn.setOnClickListener(this);
    }

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder= (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent start_service_intent=new Intent(this,MyService.class);
                startService(start_service_intent);//启动服务
                break;
            case R.id.stop_service:
                Intent stop_service_intent=new Intent(this,MyService.class);
                stopService(stop_service_intent);//停止服务
                break;
            case R.id.main_btn_bind:
                Intent bind_service_intent=new Intent(this,MyService.class);
                bindService(bind_service_intent,connection,BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.main_btn_unbind:
                unbindService(connection);//解绑服务
                break;
            default:
                break;
        }
    }
}