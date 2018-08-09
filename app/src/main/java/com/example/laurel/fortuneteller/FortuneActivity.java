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

public class FortuneActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String MEETING_COUNT = "MeetingCount";
    private static final String ANSWERS = "Answers";
    private static final int ANIMATION_TIME_OUT = 1200;
    private int meetingCount;
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
        meetingCount = getIntent().getIntExtra(MEETING_COUNT, 0);
        AddMeetingButton = findViewById(R.id.addMeetingButton);
        meetingCountTextView = findViewById(R.id.meetingCountLabel);
        fortuneTextView = findViewById(R.id.fortune);
        animation = (AnimationDrawable) findViewById(R.id.crystalBall)
                        .getBackground();
        setMeetingCountLabel();
        setFortune();
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
        meetingCount++;
        checkDisableMeetingButton();
        clearTextViews();
        animation.start();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                animation.stop();
                animation.selectDrawable(0); //reset animation to first frame (no stars)
                setFortune();
                setMeetingCountLabel();
            }
        },ANIMATION_TIME_OUT);
    }

    private void checkDisableMeetingButton()
    {
        if (meetingCount == 10)
        {
            AddMeetingButton.setEnabled(false);
            AddMeetingButton.setAlpha(.5f); //gray out the button when disabled
        }
    }

    private void setMeetingCountLabel() {
        if (meetingCount < 7)
        {
            meetingCountTextView.setText(getResources().getString(R.string.low_meeting_count_label));
        }
        else if (meetingCount < 10)
        {
            meetingCountTextView.setText(getResources().getString(R.string.medium_meeting_count_label));
        }
        else
        {
            meetingCountTextView.setText(getResources().getString(R.string.high_meeting_count_label));
        }
    }

    private void setFortune()
    {
        fortuneTextView.setText(getResources()
                .getStringArray(R.array.fortunes)[meetingCount]);
    }

    private void clearTextViews()
    {
        fortuneTextView.setText("");
        meetingCountTextView.setText("");
    }
}
