package com.example.laurel.fortuneteller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.HashMap;

public class DataEntryActivity extends AppCompatActivity implements View.OnClickListener {

    private NumberPicker meetingCountPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        InitializeMeetingCountPicker();
        findViewById(R.id.submitDataButton).setOnClickListener(this);
    }

    private void InitializeMeetingCountPicker() {
        meetingCountPicker = findViewById(R.id.meetingCountPicker);
        meetingCountPicker.setMinValue(0);
        meetingCountPicker.setMaxValue(10);
    }

    private HashMap<String, String> get_answers() {
        HashMap<String, String> data = new HashMap<>();

        data.put("name", ((EditText) findViewById(R.id.question1)).getText().toString());
        data.put("email", ((EditText) findViewById(R.id.question2)).getText().toString());
        data.put("meeting_count", Integer.toString(meetingCountPicker.getValue()));

        return data;
    }

    @Override
    public void onClick(View v) {
        // fetch answers
        HashMap<String, String> data = get_answers();

        // launch FortuneActivity
        Intent intent = FortuneActivity
                .makeIntent(this, meetingCountPicker.getValue());
        startActivity(intent);
    }

}
