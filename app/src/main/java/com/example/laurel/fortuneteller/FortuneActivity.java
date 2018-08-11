package com.example.laurel.fortuneteller;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FortuneActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String MEETING_COUNT = "MeetingCount";
    private static final String ANSWERS = "Answers";
    private static final int HIGH_FORTUNE_QUALITY = 2;
    private static final int MEDIUM_FORTUNE_QUALITY = 1;
    private static final int LOW_FORTUNE_QUALITY = 0;
    private static final int ANIMATION_TIME_OUT = 1200;
    private int fortuneQuality;
    private HashMap<String, String> answers;
    private Button AddMeetingButton;
    private TextView meetingCountTextView;
    private TextView fortuneTextView;
    private AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);
        answers = (HashMap<String, String>) getIntent().getSerializableExtra(ANSWERS);
        fortuneQuality = getIntent().getIntExtra(MEETING_COUNT, 0);
        AddMeetingButton = findViewById(R.id.addMeetingButton);
        meetingCountTextView = findViewById(R.id.meetingCountLabel);
        fortuneTextView = findViewById(R.id.fortune);
        animation = (AnimationDrawable) findViewById(R.id.crystalBall)
                        .getBackground();
        setFortuneAndLabel();
        AddMeetingButton.setOnClickListener(this);
    }

    public static Intent makeIntent(Context context, int meetingCount, HashMap<String, String> answers)
    {
        Intent intent = new Intent(context, FortuneActivity.class);
        intent.putExtra(MEETING_COUNT, meetingCount);
        intent.putExtra(ANSWERS, answers);
        return intent;
    }

    @Override
    public void onClick(View v) {
        fortuneQuality++;
        clearTextViews();
        animation.start();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                animation.stop();
                animation.selectDrawable(0); //reset animation to first frame (no stars)
                setFortuneAndLabel();
            }
        },ANIMATION_TIME_OUT);
    }

    private void setFortuneAndLabel() {
        String label;
        String[] fortunes;

        if (fortuneQuality == LOW_FORTUNE_QUALITY)
        {
            fortunes = getResources().getStringArray(R.array.bad_fortunes);
            label = getResources().getString(R.string.low_meeting_count_label);
        }
        else if (fortuneQuality == MEDIUM_FORTUNE_QUALITY)
        {
            fortunes = getResources().getStringArray(R.array.okay_fortunes);
            label = getResources().getString(R.string.medium_meeting_count_label);
        }
        else
        {
            fortunes = getResources().getStringArray(R.array.good_fortunes);
            label = getResources().getString(R.string.high_meeting_count_label);
        }
        meetingCountTextView.setText(label);
        fortuneTextView.setText(fortunes[new Random().nextInt(fortunes.length)]);
        checkDisableMeetingButton();
    }

    private void checkDisableMeetingButton()
    {
        if (fortuneQuality == HIGH_FORTUNE_QUALITY)
        {
            AddMeetingButton.setEnabled(false);
            AddMeetingButton.setAlpha(.5f); //gray out the button when disabled
        }
    }

    private void clearTextViews()
    {
        fortuneTextView.setText("");
        meetingCountTextView.setText("");
    }
}
