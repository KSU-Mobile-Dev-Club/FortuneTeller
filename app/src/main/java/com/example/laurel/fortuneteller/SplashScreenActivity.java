package com.example.laurel.fortuneteller;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class SplashScreenActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT  = 2000;
    private static final String MEETING_COUNT = "MeetingCount";
    private static final String ANSWERS = "Answers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        AnimationDrawable animation = (AnimationDrawable) findViewById(
                R.id.crystal_ball_image_view).getBackground();
        animation.start();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                int meetingCount = getIntent().getIntExtra(MEETING_COUNT,0);
                HashMap<String,String> answers = (HashMap<String,String>) getIntent().getSerializableExtra(ANSWERS);
                Intent intent = FortuneActivity.makeIntent(SplashScreenActivity.this, meetingCount, answers);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    public static Intent makeIntent(Context context, int meetingCount, HashMap<String, String> answers)
    {
        Intent intent = new Intent(context, SplashScreenActivity.class);
        intent.putExtra(MEETING_COUNT, meetingCount);
        intent.putExtra(ANSWERS, answers);
        return intent;
    }
}
