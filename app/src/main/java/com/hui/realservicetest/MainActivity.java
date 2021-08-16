package com.hui.realservicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button start_service_btn,stop_service_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_service_btn=findViewById(R.id.start_service);
        stop_service_btn=findViewById(R.id.stop_service);
        start_service_btn.setOnClickListener(this);
        stop_service_btn.setOnClickListener(this);
    }

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
            default:
                break;
        }
    }
}