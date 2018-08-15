package com.example.laurel.fortuneteller;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AnimationDrawable animation;
    private static final int ANIMATION_TIME_OUT = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animation = (AnimationDrawable) findViewById(R.id.crystal_ball).getBackground();
        findViewById(R.id.yes_button).setOnClickListener(this);

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
}
