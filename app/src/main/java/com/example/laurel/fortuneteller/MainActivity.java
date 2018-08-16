package com.example.laurel.fortuneteller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 1000;
    private AnimationDrawable animation;
    private static final int ANIMATION_TIME_OUT = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animation = (AnimationDrawable) findViewById(R.id.crystal_ball).getBackground();
        findViewById(R.id.yes_button).setOnClickListener(this);
        askPermissionToWrite();

    }

    @Override
    public void onClick(View v) {
        animation.start();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                animation.stop();
                startActivity(new Intent(MainActivity.this, DataEntryActivity.class));
            }
        }, ANIMATION_TIME_OUT);
    }

    private void askPermissionToWrite()
    {
        // Check whether this app has write external storage permission or not.
        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(writeExternalStoragePermission!= PackageManager.PERMISSION_GRANTED)
        {
            // Request user to grant write external storage permission.
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
        }
    }
}
