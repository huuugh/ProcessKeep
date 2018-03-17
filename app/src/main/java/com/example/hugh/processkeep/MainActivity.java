package com.example.hugh.processkeep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hugh.processkeep.service.LocalService;
import com.example.hugh.processkeep.service.RemoteService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i1 = new Intent(this, LocalService.class);
        this.startService(i1);

        Intent i2 = new Intent(this, RemoteService.class);
        this.startService(i2);
    }
}
