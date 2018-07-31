package com.example.laurel.fortuneteller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class FortuneActivity extends AppCompatActivity {
    private static final String MEETING_COUNT = "MeetingCount";
    private static final String ANSWERS = "Answers";
    private int meetingCount;
    private HashMap<String, String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);
        answers = (HashMap<String, String>) getIntent().getSerializableExtra(ANSWERS);
        setMeetingCountLabel();
        setFortune();
    }

    private void setMeetingCountLabel() {
        meetingCount = getIntent().getIntExtra(MEETING_COUNT, 0);
        TextView label = findViewById(R.id.meetingCountLabel);
        label.setText(getResources().getString(R.string.meeting_count_label) + meetingCount);
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
}
