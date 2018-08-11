package com.example.laurel.fortuneteller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.util.HashMap;

public class DataEntryActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner meetingCountPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        InitializeMeetingCountPicker();
        findViewById(R.id.submitDataButton).setOnClickListener(this);
    }

    private void InitializeMeetingCountPicker() {
        meetingCountPicker = findViewById(R.id.meetingCountPicker);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.meetingCountOptions,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meetingCountPicker.setAdapter(adapter);
    }

    private HashMap<String, String> getAnswers() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", ((EditText) findViewById(R.id.question1)).getText().toString());
        data.put("email", ((EditText) findViewById(R.id.question2)).getText().toString());
        return data;
    }

    @Override
    public void onClick(View v) {
        //launch crystal ball animation as transition to FortuneActivity
        startActivity(SplashScreenActivity
                .makeIntent(this, meetingCountPicker.getSelectedItemPosition(), getAnswers()));
    }

}
