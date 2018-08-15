package com.example.laurel.fortuneteller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        InitializeMeetingCountPicker();
        findViewById(R.id.submitDataButton).setOnClickListener(this);
        SetEditTextListeners();
    }

    @Override
    public void onClick(View v) {
        //launch crystal ball animation as transition to FortuneActivity
        startActivity(SplashScreenActivity
                .makeIntent(this, meetingCountPicker.getSelectedItemPosition(), getAnswers()));
    }

    private void SetEditTextListeners()
    {
        findViewById(R.id.nameEditText).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        findViewById(R.id.emailEditText).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    private void InitializeMeetingCountPicker() {
        meetingCountPicker = findViewById(R.id.meetingCountPicker);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.meetingCountOptions,
                R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        meetingCountPicker.setAdapter(adapter);
    }

    private HashMap<String, String> getAnswers() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", ((EditText) findViewById(R.id.nameEditText)).getText().toString());
        data.put("email", ((EditText) findViewById(R.id.emailEditText)).getText().toString());
        return data;
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
