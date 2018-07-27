package com.example.laurel.fortuneteller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

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

    @Override
    public void onClick(View v) {
        Intent intent = FortuneActivity
                .makeIntent(this, meetingCountPicker.getValue());
        startActivity(intent);
    }
}
