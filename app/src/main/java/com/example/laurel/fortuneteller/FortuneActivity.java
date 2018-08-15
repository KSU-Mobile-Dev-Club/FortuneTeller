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

public class FortuneActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String MEETING_COUNT = "MeetingCount";
    private static final String ANSWERS = "Answers";
    private static final int HIGH_FORTUNE_QUALITY = 2;
    private static final int MEDIUM_FORTUNE_QUALITY = 1;
    private static final int LOW_FORTUNE_QUALITY = 0;
    private static final int ANIMATION_TIME_OUT = 1200;
    private int fortuneQuality;
    private HashMap<String, String> answers;
    private Button addMeetingButton;
    private TextView meetingCountTextView;
    private TextView fortuneTextView;
    private AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);

        //adding up button to action bar to go back to DataEntryActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //retrieving the answers the user entered in DataEntryActivity
        getAnswersFromIntent();

        //getting a reference to the view objects
        initializeWidgets();

        //set the crystal ball fortune and prompt based on the user's
        //"thoughts about MDC"
        setFortuneAndPrompt();

        addMeetingButton.setOnClickListener(this);
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

        //starting the "crystal ball sparkle" animation
        animation.start();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //stopping the animation here allows it to run each time the
                //button is clicked, instead of just once
                animation.stop();

                //reset animation to first frame (no stars)
                animation.selectDrawable(0);

                setFortuneAndPrompt();
            }
        }, ANIMATION_TIME_OUT);
    }

    private void getAnswersFromIntent() {
        answers = (HashMap<String, String>) getIntent().getSerializableExtra(ANSWERS);
        fortuneQuality = getIntent().getIntExtra(MEETING_COUNT, 0);
    }

    private void initializeWidgets() {
        addMeetingButton = findViewById(R.id.addMeetingButton);
        meetingCountTextView = findViewById(R.id.meetingCountLabel);
        fortuneTextView = findViewById(R.id.fortune);
        animation = (AnimationDrawable) findViewById(R.id.crystalBall)
                        .getBackground();
    }

    private void setFortuneAndPrompt() {
        String prompt;
        String[] fortunes;

        if (fortuneQuality == LOW_FORTUNE_QUALITY)
        {
            fortunes = getResources().getStringArray(R.array.bad_fortunes);
            prompt = getResources().getString(R.string.low_meeting_count_label);
        }
        else if (fortuneQuality == MEDIUM_FORTUNE_QUALITY)
        {
            fortunes = getResources().getStringArray(R.array.okay_fortunes);
            prompt = getResources().getString(R.string.medium_meeting_count_label);
        }
        else
        {
            fortunes = getResources().getStringArray(R.array.good_fortunes);
            prompt = getResources().getString(R.string.high_meeting_count_label);
        }
        meetingCountTextView.setText(prompt);
        fortuneTextView.setText(fortunes[new Random().nextInt(fortunes.length)]);
        checkDisableMeetingButton();
    }

    private void checkDisableMeetingButton()
    {
        if (fortuneQuality == HIGH_FORTUNE_QUALITY)
        {
            addMeetingButton.setEnabled(false);

            //gray out the button when disabled
            addMeetingButton.setAlpha(.5f);
        }
    }

    private void clearTextViews()
    {
        fortuneTextView.setText("");
        meetingCountTextView.setText("");
    }
}
