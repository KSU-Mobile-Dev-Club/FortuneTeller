package com.example.laurel.fortuneteller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

public class DataEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        NumberPicker meetingCountPicker = findViewById(R.id.meetingCountPicker);
        meetingCountPicker.setMinValue(0);
        meetingCountPicker.setMaxValue(10);
    }
}
