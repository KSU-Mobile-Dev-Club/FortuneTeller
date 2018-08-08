package com.example.laurel.fortuneteller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class FortuneActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String MEETING_COUNT = "MeetingCount";
    private static final String ANSWERS = "Answers";
    private int meetingCount;
    private HashMap<String, String> answers;
    private Button AddMeetingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);
        answers = (HashMap<String, String>) getIntent().getSerializableExtra(ANSWERS);
        meetingCount = getIntent().getIntExtra(MEETING_COUNT, 0);
        setMeetingCountLabel();
        setFortune();
        AddMeetingButton = findViewById(R.id.addMeetingButton);
        AddMeetingButton.setOnClickListener(this);
    }

    private void setMeetingCountLabel() {
        TextView label = findViewById(R.id.meetingCountLabel);
        if (meetingCount < 7)
        {
            label.setText(getResources().getString(R.string.low_meeting_count_label));
        }
        else if (meetingCount < 10)
        {
            label.setText(getResources().getString(R.string.medium_meeting_count_label));
        }
        else
        {
            label.setText(getResources().getString(R.string.high_meeting_count_label));
        }
    }

    private void setFortune()
    {
        TextView fortune = findViewById(R.id.fortune);
        fortune.setText(getResources().getStringArray(R.array.fortunes)[meetingCount]);
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
        setFortune();
        setMeetingCountLabel();
        if (meetingCount == 10)
        {
            AddMeetingButton.setEnabled(false);
            AddMeetingButton.setAlpha(.5f); //gray out the button when disabled
        }
    }
}
