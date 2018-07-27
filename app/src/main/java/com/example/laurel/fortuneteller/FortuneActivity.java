package com.example.laurel.fortuneteller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FortuneActivity extends AppCompatActivity {
    private static final String MEETING_COUNT = "MeetingCount";
    private int meetingCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune);

        setMeetingCountLabel();
    }

    private void setMeetingCountLabel() {
        meetingCount = getIntent().getIntExtra(MEETING_COUNT, 0);
        TextView label = findViewById(R.id.meetingCountLabel);
        label.setText(getResources().getString(R.string.meeting_count_label) + meetingCount);
    }

    public static Intent makeIntent(Context context, int meetingCount)
    {
        Intent intent = new Intent(context, FortuneActivity.class);
        intent.putExtra(MEETING_COUNT, meetingCount);
        return intent;
    }
}
